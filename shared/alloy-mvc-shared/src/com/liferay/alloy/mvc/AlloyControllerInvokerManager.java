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

package com.liferay.alloy.mvc;

import com.liferay.portal.kernel.json.JSONSerializable;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceActionsManagerUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.theme.ThemeDisplay;

import java.io.File;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.net.URI;
import java.net.URL;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * @author Ethan Bustad
 */
public class AlloyControllerInvokerManager {

	public AlloyControllerInvokerManager(String contextPath) {
		_contextPath = contextPath;
	}

	public void registerController(
		ThemeDisplay themeDisplay, AlloyPortlet alloyPortlet, Portlet portlet,
		String controller,
		Class<? extends AlloyController> alloyControllerClass) {

		synchronized (_alloyControllerInvokers) {
			if (_alloyControllerInvokers.containsKey(controller)) {
				AlloyControllerInvoker alloyControllerInvoker =
					_alloyControllerInvokers.get(controller);

				JSONWebServiceActionsManagerUtil.
					unregisterJSONWebServiceActions(alloyControllerInvoker);
			}

			Class<? extends AlloyControllerInvoker>
				alloyControllerInvokerClass = null;

			AlloyControllerInvoker alloyControllerInvoker = null;

			try {
				alloyControllerInvokerClass = createAlloyControllerInvokerClass(
					alloyControllerClass);

				Constructor<? extends AlloyControllerInvoker> constructor =
					alloyControllerInvokerClass.getConstructor();

				alloyControllerInvoker = constructor.newInstance();

				alloyControllerInvoker.setProperties(
					themeDisplay, alloyPortlet, portlet, alloyControllerClass,
					controller);

				_alloyControllerInvokers.put(
					controller, alloyControllerInvoker);
			}
			catch (NoClassNecessaryException ncne) {
				return;
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}

			for (Method method :
					alloyControllerInvokerClass.getDeclaredMethods()) {

				JSONWebServiceActionsManagerUtil.registerJSONWebServiceAction(
					_contextPath, alloyControllerInvoker,
					alloyControllerInvokerClass, method,
					getAPIPath(controller, method), "GET");
			}
		}
	}

	public void unregisterControllers() {
		synchronized (_alloyControllerInvokers) {
			for (AlloyControllerInvoker alloyControllerInvoker :
					_alloyControllerInvokers.values()) {

				JSONWebServiceActionsManagerUtil.
					unregisterJSONWebServiceActions(alloyControllerInvoker);
			}

			_alloyControllerInvokers.clear();
		}
	}

	protected Class<? extends AlloyControllerInvoker>
		createAlloyControllerInvokerClass(
			Class<? extends AlloyController> alloyControllerClass)
		throws NoClassNecessaryException {

		ClassLoader classLoader = alloyControllerClass.getClassLoader();

		String alloyControllerInvokerClassName =
			getAlloyControllerInvokerClassName(alloyControllerClass);

		Class<? extends AlloyControllerInvoker> alloyControllerInvokerClass =
			null;

		synchronized (classLoader) {
			try {
				Method defineClassMethod = ReflectionUtil.getDeclaredMethod(
					ClassLoader.class, "defineClass", String.class,
					byte[].class, int.class, int.class);

				final byte[] classData =
					generateAlloyControllerInvokerClassData(
						alloyControllerClass, alloyControllerInvokerClassName);

				final String filename =
					PropsUtil.get(PropsKeys.LIFERAY_HOME) + "/data/alloy/" +
						getClassBinaryName(alloyControllerInvokerClassName) +
							".class";

				ClassLoader customClassLoader = new ClassLoader(classLoader) {

					@Override
					public URL getResource(String name) {
						if (filename.contains(name)) {
							File file = new File(filename);

							try {
								FileUtil.write(file, classData);

								URI uri = file.toURI();

								return uri.toURL();
							}
							catch (Exception e) {
								throw new RuntimeException(e);
							}
						}

						return super.getResource(name);
					}

				};

				alloyControllerInvokerClass =
					(Class)defineClassMethod.invoke(
						customClassLoader, alloyControllerInvokerClassName,
						classData, 0, classData.length);

				return alloyControllerInvokerClass;
			}
			catch (NoClassNecessaryException ncne) {
				throw ncne;
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	protected byte[] generateAlloyControllerInvokerClassData(
			Class<?> alloyControllerClass,
			String alloyControllerInvokerClassName)
		throws NoClassNecessaryException {

		boolean jsonWebServiceMethodsPresent = false;

		ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

		String alloyControllerInvokerClassBinaryName = getClassBinaryName(
			alloyControllerInvokerClassName);

		String baseAlloyControllerInvokerClassBinaryName = getClassBinaryName(
			BaseAlloyControllerInvokerImpl.class.getName());

		classWriter.visit(
			Opcodes.V1_5, Opcodes.ACC_PUBLIC | Opcodes.ACC_SUPER,
			alloyControllerInvokerClassBinaryName, null,
			baseAlloyControllerInvokerClassBinaryName, null);

		MethodVisitor methodVisitor = classWriter.visitMethod(
			Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);

		methodVisitor.visitCode();
		methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
		methodVisitor.visitMethodInsn(
			Opcodes.INVOKESPECIAL, baseAlloyControllerInvokerClassBinaryName,
			"<init>", "()V");
		methodVisitor.visitInsn(Opcodes.RETURN);
		methodVisitor.visitMaxs(1, 1);
		methodVisitor.visitEnd();

		Method[] methods = alloyControllerClass.getDeclaredMethods();

		for (Method method : methods) {
			if (!Modifier.isPublic(method.getModifiers())) {
				continue;
			}

			JSONWebServiceMethod jsonWebServiceMethod = method.getAnnotation(
				JSONWebServiceMethod.class);

			if (jsonWebServiceMethod == null) {
				continue;
			}

			jsonWebServiceMethodsPresent = true;

			Class<?>[] parameterTypes = jsonWebServiceMethod.parameterTypes();

			StringBundler sb = new StringBundler(parameterTypes.length + 3);

			sb.append(StringPool.OPEN_PARENTHESIS);

			for (Class<?> parameterType : parameterTypes) {
				sb.append(Type.getDescriptor(parameterType));
			}

			sb.append(StringPool.CLOSE_PARENTHESIS);
			sb.append(Type.getDescriptor(JSONSerializable.class));

			String methodDescriptor = sb.toString();

			methodVisitor = classWriter.visitMethod(
				Opcodes.ACC_PUBLIC, method.getName(), methodDescriptor, null,
				new String[] {getClassBinaryName(Exception.class.getName())});

			methodVisitor.visitCode();

			for (int i = 0; i < parameterTypes.length; i++) {
				String parameterName = jsonWebServiceMethod.parameterNames()[i];
				Class<?> parameterType = parameterTypes[i];

				methodVisitor.visitLocalVariable(
					parameterName, Type.getDescriptor(parameterType), null,
					new Label(), new Label(), i + 1);
			}

			methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
			methodVisitor.visitLdcInsn(jsonWebServiceMethod.lifecycle());

			methodVisitor.visitIntInsn(
				Opcodes.BIPUSH, parameterTypes.length * 2 + 2);
			methodVisitor.visitTypeInsn(
				Opcodes.ANEWARRAY, getClassBinaryName(Object.class.getName()));

			methodVisitor.visitInsn(Opcodes.DUP);
			methodVisitor.visitInsn(Opcodes.ICONST_0);
			methodVisitor.visitLdcInsn("action");
			methodVisitor.visitInsn(Opcodes.AASTORE);

			methodVisitor.visitInsn(Opcodes.DUP);
			methodVisitor.visitInsn(Opcodes.ICONST_1);
			methodVisitor.visitLdcInsn(method.getName());
			methodVisitor.visitInsn(Opcodes.AASTORE);

			for (int i = 0; i < parameterTypes.length; i++) {
				String parameterName = jsonWebServiceMethod.parameterNames()[i];

				methodVisitor.visitInsn(Opcodes.DUP);
				methodVisitor.visitIntInsn(Opcodes.BIPUSH, (i + 1) * 2);
				methodVisitor.visitLdcInsn(parameterName);
				methodVisitor.visitInsn(Opcodes.AASTORE);

				methodVisitor.visitInsn(Opcodes.DUP);
				methodVisitor.visitIntInsn(Opcodes.BIPUSH, (i + 1) * 2 + 1);
				methodVisitor.visitVarInsn(Opcodes.ALOAD, i + 1);
				methodVisitor.visitInsn(Opcodes.AASTORE);
			}

			sb = new StringBundler(5);

			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(Type.getDescriptor(String.class));
			sb.append(Type.getDescriptor(Object[].class));
			sb.append(StringPool.CLOSE_PARENTHESIS);
			sb.append(Type.getDescriptor(JSONSerializable.class));

			methodVisitor.visitMethodInsn(
				Opcodes.INVOKEVIRTUAL, alloyControllerInvokerClassBinaryName,
				"invokeAlloyController", sb.toString());

			methodVisitor.visitInsn(Opcodes.ARETURN);

			methodVisitor.visitMaxs(-1, -1);
			methodVisitor.visitEnd();
		}

		classWriter.visitEnd();

		if (!jsonWebServiceMethodsPresent) {
			throw new NoClassNecessaryException();
		}

		return classWriter.toByteArray();
	}

	protected String getAlloyControllerInvokerClassName(
		Class<? extends AlloyController> alloyControllerClass) {

		Package classPackage = alloyControllerClass.getPackage();

		String simpleName = StringPool.BLANK;

		Class<?> enclosingClass = alloyControllerClass.getEnclosingClass();

		if (enclosingClass != null) {
			String name = StringUtil.replace(
				enclosingClass.getSimpleName(), "005f", StringPool.BLANK);

			int trimIndex = name.indexOf("_controller");

			String[] wordElements = StringUtil.split(
				name.substring(0, trimIndex), CharPool.UNDERLINE);

			for (String wordElement : wordElements) {
				simpleName = simpleName.concat(
					StringUtil.upperCaseFirstLetter(wordElement));
			}

			simpleName = simpleName.concat(_BASE_CLASS_NAME);
		}
		else {
			simpleName = _BASE_CLASS_NAME + _counter.getAndIncrement();
		}

		return classPackage.getName() + StringPool.PERIOD + simpleName;
	}

	protected String getAPIPath(String controller, Method method) {
		StringBundler sb = new StringBundler(4);

		sb.append(StringPool.SLASH);
		sb.append(controller);
		sb.append(StringPool.SLASH);
		sb.append(method.getName());

		return sb.toString();
	}

	protected String getClassBinaryName(String className) {
		return className.replace('.', '/');
	}

	protected class NoClassNecessaryException extends Exception {

		public NoClassNecessaryException() {
		}

		public NoClassNecessaryException(String message) {
			super(message);
		}

	}

	private static final String _BASE_CLASS_NAME = "AlloyControllerInvokerImpl";

	private Map<String, AlloyControllerInvoker> _alloyControllerInvokers =
		new ConcurrentHashMap<String, AlloyControllerInvoker>();
	private String _contextPath;
	private AtomicInteger _counter = new AtomicInteger(0);

}