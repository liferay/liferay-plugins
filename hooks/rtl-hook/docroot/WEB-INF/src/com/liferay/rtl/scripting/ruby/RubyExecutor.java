/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.rtl.scripting.ruby;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.scripting.BaseScriptingExecutor;
import com.liferay.portal.kernel.scripting.ExecutionException;
import com.liferay.portal.kernel.scripting.ScriptingException;
import com.liferay.portal.kernel.util.AggregateClassLoader;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.NamedThreadFactory;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.rtl.util.PropsValues;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;

import jodd.io.ZipUtil;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.RubyInstanceConfig.CompileMode;
import org.jruby.embed.LocalContextScope;
import org.jruby.embed.ScriptingContainer;
import org.jruby.embed.internal.LocalContextProvider;
import org.jruby.exceptions.RaiseException;

/**
 * @author Alberto Montero
 * @author Raymond Augé
 * @author Eduardo Garcia
 * @see com.liferay.portal.scripting.ruby.RubyExecutor
 */
public class RubyExecutor extends BaseScriptingExecutor {

	public static final String LANGUAGE = "ruby";

	public RubyExecutor() {
		try {
			initRubyGems();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		_scriptingContainer = new ScriptingContainer(
			LocalContextScope.THREADSAFE);

		LocalContextProvider localContextProvider =
			_scriptingContainer.getProvider();

		RubyInstanceConfig rubyInstanceConfig =
			localContextProvider.getRubyInstanceConfig();

		if (PropsValues.SCRIPTING_JRUBY_COMPILE_MODE.equals(
				_COMPILE_MODE_FORCE)) {

			rubyInstanceConfig.setCompileMode(CompileMode.FORCE);
		}
		else if (PropsValues.SCRIPTING_JRUBY_COMPILE_MODE.equals(
					_COMPILE_MODE_JIT)) {

			rubyInstanceConfig.setCompileMode(CompileMode.JIT);
		}

		rubyInstanceConfig.setJitThreshold(
			PropsValues.SCRIPTING_JRUBY_COMPILE_THRESHOLD);
		rubyInstanceConfig.setLoader(PortalClassLoaderUtil.getClassLoader());

		_basePath = PropsValues.LIFERAY_LIB_PORTAL_DIR;

		_loadPaths = new ArrayList<String>(
			PropsValues.SCRIPTING_JRUBY_LOAD_PATHS.length);

		for (String gemLibPath : PropsValues.SCRIPTING_JRUBY_LOAD_PATHS) {
			_loadPaths.add(gemLibPath);
		}

		rubyInstanceConfig.setLoadPaths(_loadPaths);

		_scriptingContainer.setCurrentDirectory(_basePath);
	}

	@Override
	public Map<String, Object> eval(
			Set<String> allowedClasses, Map<String, Object> inputObjects,
			Set<String> outputNames, File scriptFile,
			ClassLoader... classLoaders)
		throws ScriptingException {

		return eval(
			allowedClasses, inputObjects, outputNames, scriptFile, null,
			classLoaders);
	}

	@Override
	public Map<String, Object> eval(
			Set<String> allowedClasses, Map<String, Object> inputObjects,
			Set<String> outputNames, String script, ClassLoader... classLoaders)
		throws ScriptingException {

		return eval(
			allowedClasses, inputObjects, outputNames, null, script,
			classLoaders);
	}

	@Override
	public String getLanguage() {
		return LANGUAGE;
	}

	public void setExecuteInSeparateThread(boolean executeInSeparateThread) {
		_executeInSeparateThread = executeInSeparateThread;
	}

	protected Map<String, Object> doEval(
			Set<String> allowedClasses, Map<String, Object> inputObjects,
			Set<String> outputNames, File scriptFile, String script,
			ClassLoader... classLoaders)
		throws ScriptingException {

		if (allowedClasses != null) {
			throw new ExecutionException(
				"Constrained execution not supported for Ruby");
		}

		try {
			LocalContextProvider localContextProvider =
				_scriptingContainer.getProvider();

			RubyInstanceConfig rubyInstanceConfig =
				localContextProvider.getRubyInstanceConfig();

			rubyInstanceConfig.setCurrentDirectory(_basePath);

			if (ArrayUtil.isNotEmpty(classLoaders)) {
				ClassLoader aggregateClassLoader =
					AggregateClassLoader.getAggregateClassLoader(
						PortalClassLoaderUtil.getClassLoader(), classLoaders);

				rubyInstanceConfig.setLoader(aggregateClassLoader);
			}

			rubyInstanceConfig.setLoadPaths(_loadPaths);

			for (Map.Entry<String, Object> entry : inputObjects.entrySet()) {
				String inputName = entry.getKey();
				Object inputObject = entry.getValue();

				if (!inputName.startsWith(StringPool.DOLLAR)) {
					inputName = StringPool.DOLLAR + inputName;
				}

				_scriptingContainer.put(inputName, inputObject);
			}

			if (scriptFile != null) {
				_scriptingContainer.runScriptlet(
					new FileInputStream(scriptFile), scriptFile.toString());
			}
			else {
				_scriptingContainer.runScriptlet(script);
			}

			if (outputNames == null) {
				return null;
			}

			Map<String, Object> outputObjects = new HashMap<String, Object>();

			for (String outputName : outputNames) {
				outputObjects.put(
					outputName, _scriptingContainer.get(outputName));
			}

			return outputObjects;
		}
		catch (RaiseException re) {
			throw new ScriptingException(
				re.getException().message.asJavaString() + "\n\n", re);
		}
		catch (FileNotFoundException fnfe) {
			throw new ScriptingException(fnfe);
		}
		finally {
			try {
				_globalRuntimeField.set(null, null);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
	}

	protected Map<String, Object> eval(
			Set<String> allowedClasses, Map<String, Object> inputObjects,
			Set<String> outputNames, File scriptFile, String script,
			ClassLoader... classLoaders)
		throws ScriptingException {

		if (!_executeInSeparateThread) {
			return doEval(
				allowedClasses, inputObjects, outputNames, scriptFile, script,
				classLoaders);
		}

		EvalCallable evalCallable = new EvalCallable(
			allowedClasses, inputObjects, outputNames, scriptFile, script,
			classLoaders);

		FutureTask<Map<String, Object>> futureTask =
			new FutureTask<Map<String, Object>>(evalCallable);

		Thread oneTimeExecutorThread = _threadFactory.newThread(futureTask);

		oneTimeExecutorThread.start();

		try {
			oneTimeExecutorThread.join();

			return futureTask.get();
		}
		catch (Exception e) {
			futureTask.cancel(true);
			oneTimeExecutorThread.interrupt();

			throw new ScriptingException(e);
		}
	}

	protected void initRubyGems() throws Exception {
		File rubyGemsJarFile = new File(
			PropsValues.LIFERAY_LIB_PORTAL_DIR, "ruby-gems.jar");

		if (!rubyGemsJarFile.exists()) {
			if (_log.isWarnEnabled()) {
				_log.warn(rubyGemsJarFile + " does not exist");
			}

			return;
		}

		String tmpDir = SystemProperties.get(SystemProperties.TMP_DIR);

		File rubyDir = new File(tmpDir + "/liferay/ruby");

		if (!rubyDir.exists() ||
			(rubyDir.lastModified() < rubyGemsJarFile.lastModified())) {

			FileUtil.deltree(rubyDir);

			rubyDir.mkdirs();

			ZipUtil.unzip(rubyGemsJarFile, rubyDir);

			rubyDir.setLastModified(rubyGemsJarFile.lastModified());
		}
	}

	private static final String _COMPILE_MODE_FORCE = "force";

	private static final String _COMPILE_MODE_JIT = "jit";

	private static Log _log = LogFactoryUtil.getLog(RubyExecutor.class);

	private static Field _globalRuntimeField;

	private static ThreadFactory _threadFactory =
		new NamedThreadFactory(
			RubyExecutor.class.getName(), Thread.NORM_PRIORITY,
			RubyExecutor.class.getClassLoader());

	static {
		try {
			_globalRuntimeField = ReflectionUtil.getDeclaredField(
				Ruby.class, "globalRuntime");
		}
		catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	private String _basePath;
	private boolean _executeInSeparateThread = true;
	private List<String> _loadPaths;
	private ScriptingContainer _scriptingContainer;

	private class EvalCallable implements Callable<Map<String, Object>> {

		public EvalCallable(
			Set<String> allowedClasses, Map<String, Object> inputObjects,
			Set<String> outputNames, File scriptFile, String script,
			ClassLoader[] classLoaders) {

			_allowedClasses = allowedClasses;
			_inputObjects = inputObjects;
			_outputNames = outputNames;
			_scriptFile = scriptFile;
			_script = script;
			_classLoaders = classLoaders;
		}

		@Override
		public Map<String, Object> call() throws Exception {
			return doEval(
				_allowedClasses, _inputObjects, _outputNames, _scriptFile,
				_script, _classLoaders);
		}

		private final Set<String> _allowedClasses;
		private final Map<String, Object> _inputObjects;
		private final Set<String> _outputNames;
		private final File _scriptFile;
		private final String _script;
		private final ClassLoader[] _classLoaders;

	}

}