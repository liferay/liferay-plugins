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

package com.liferay.portal.ant.bnd;

import aQute.bnd.build.ProjectBuilder;
import aQute.bnd.differ.Baseline;
import aQute.bnd.differ.Baseline.BundleInfo;
import aQute.bnd.differ.Baseline.Info;
import aQute.bnd.differ.DiffPluginImpl;
import aQute.bnd.osgi.Builder;
import aQute.bnd.osgi.Jar;
import aQute.bnd.osgi.Resource;
import aQute.bnd.service.diff.Delta;
import aQute.bnd.service.diff.Diff;
import aQute.bnd.version.Version;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Path;

/**
 * @author Raymond Augé
 */
public class BaselineJarTask extends BaseBndTask {

	@Override
	public void addClasspath(Path classpath) {
		_classpath = classpath;
	}

	public void setFile(File file) {
		_file = file;
	}

	public void setOutputPath(File outputPath) {
		_outputPath = outputPath;
	}

	public void setSourcePath(File sourcePath) {
		_sourcePath = sourcePath;
	}

	@Override
	public void trace(String format, Object... args) {
	}

	protected void doBaselineJar(
			Jar jar, File output, aQute.bnd.build.Project bndProject)
		throws Exception {

		if (_reportLevelIsOff) {
			return;
		}

		ProjectBuilder projectBuilder = new ProjectBuilder(bndProject);

		projectBuilder.setClasspath(
			_classpathFiles.toArray(new File[_classpathFiles.size()]));
		projectBuilder.setPedantic(isPedantic());
		projectBuilder.setProperties(_file);
		projectBuilder.setSourcepath(new File[] {_sourcePath});

		Jar baselineJar = projectBuilder.getBaselineJar();

		try {
			if (baselineJar == null) {
				bndProject.deploy(output);

				return;
			}

			Baseline baseline = new Baseline(this, _diffPluginImpl);

			Set<Info> infos = baseline.baseline(jar, baselineJar, null);

			if (infos.isEmpty()) {
				return;
			}

			BundleInfo bundleInfo = baseline.getBundleInfo();

			Info[] infosArray = infos.toArray(new Info[infos.size()]);

			Arrays.sort(
				infosArray, new Comparator<Info>() {

					@Override
					public int compare(Info info1, Info info2) {
						return info1.packageName.compareTo(info2.packageName);
					}

				}
			);

			for (Info info : infosArray) {
				String warnings = "-";

				Version newerVersion = info.newerVersion;
				Version suggestedVersion = info.suggestedVersion;

				if (suggestedVersion != null) {
					if (newerVersion.compareTo(suggestedVersion) > 0) {
						warnings = "EXCESSIVE VERSION INCREASE";
					}
					else if (newerVersion.compareTo(suggestedVersion) < 0) {
						warnings = "VERSION INCREASE REQUIRED";
					}
				}

				Diff packageDiff = info.packageDiff;

				Delta delta = packageDiff.getDelta();

				if (delta == Delta.REMOVED) {
					warnings = "PACKAGE REMOVED";
				}
				else if (delta == Delta.UNCHANGED) {
					boolean newVersionSuggested = false;

					if ((suggestedVersion.getMajor() !=
							newerVersion.getMajor()) ||
						(suggestedVersion.getMicro() !=
							newerVersion.getMicro()) ||
						(suggestedVersion.getMinor() !=
							newerVersion.getMinor())) {

						warnings = "VERSION INCREASE SUGGESTED";

						newVersionSuggested = true;
					}

					if (!newVersionSuggested && !info.mismatch) {
						continue;
					}
				}

				if (((_reportLevelIsStandard || _reportOnlyDirtyPackages) &&
					 warnings.equals("-")) ||
					(_reportOnlyDirtyPackages && (delta == Delta.REMOVED))) {

					continue;
				}

				doInfo(bundleInfo, info, warnings);

				if (_reportLevelIsDiff && (delta != Delta.REMOVED)) {
					doPackageDiff(packageDiff);
				}
			}
		}
		finally {
			if (baselineJar != null) {
				baselineJar.close();
			}

			if (_printWriter != null) {
				_printWriter.close();
			}

			projectBuilder.close();
		}
	}

	@Override
	protected void doBeforeExecute() throws Exception {
		super.doBeforeExecute();

		File bndRootFile = getBndRootFile();

		File rootDir = bndRootFile.getParentFile();

		if (_classpath == null) {
			throw new BuildException("classpath is null");
		}

		if ((_file == null) || !_file.exists() || _file.isDirectory()) {
			if (_file != null) {
				project.log(
					"file is either missing or is a directory " +
						_file.getAbsolutePath(),
					Project.MSG_ERR);
			}

			throw new BuildException("file is invalid");
		}

		if (_outputPath == null) {
			throw new BuildException("outputPath is invalid");
		}

		_reportLevel = project.getProperty("baseline.jar.report.level");

		_reportLevelIsDiff = Validator.equals(_reportLevel, "diff");
		_reportLevelIsOff = Validator.equals(_reportLevel, "off");
		_reportLevelIsPersist = Validator.equals(_reportLevel, "persist");
		_reportLevelIsStandard = Validator.equals(_reportLevel, "standard");

		if (_reportLevelIsPersist) {
			_reportLevelIsDiff = true;

			File baselineReportsDir = new File(
				rootDir, getBaselineResportsDirName());

			if (!baselineReportsDir.exists() && !baselineReportsDir.mkdir()) {
				throw new BuildException(
					"Unable tocreate " + baselineReportsDir.getName());
			}

			_logFile = new File(
				baselineReportsDir, _outputPath.getName() + ".log");

			if (_logFile.exists()) {
				_logFile.delete();
			}
		}

		_reportOnlyDirtyPackages = GetterUtil.getBoolean(
			project.getProperty("baseline.jar.report.only.dirty.packages"),
			false);

		if ((_sourcePath == null) || !_sourcePath.exists() ||
			!_sourcePath.isDirectory()) {

			if (_sourcePath != null) {
				project.log(
					"sourcePath is either missing or is not a directory " +
						_sourcePath.getAbsolutePath(),
					Project.MSG_ERR);
			}

			throw new BuildException("sourcePath is not set correctly");
		}

		for (String fileName : _classpath.list()) {
			_classpathFiles.add(new File(fileName.replace('\\', '/')));
		}
	}

	protected void doDiff(Diff diff, StringBuffer sb) {
		String output = String.format(
			"%s%-3s %-10s %s", sb, getShortDelta(diff.getDelta()),
			StringUtil.toLowerCase(String.valueOf(diff.getType())),
			diff.getName());

		project.log(output, Project.MSG_WARN);

		if (_printWriter != null) {
			_printWriter.println(output);
		}

		sb.append("\t");

		for (Diff curDiff : diff.getChildren()) {
			if (curDiff.getDelta() == Delta.UNCHANGED) {
				continue;
			}

			doDiff(curDiff, sb);
		}

		sb.deleteCharAt(sb.length() - 1);
	}

	@Override
	protected void doExecute() throws Exception {
		aQute.bnd.build.Project bndProject = getBndProject();

		Builder builder = new Builder(bndProject);

		builder.setClasspath(
			_classpathFiles.toArray(new File[_classpathFiles.size()]));
		builder.setPedantic(isPedantic());
		builder.setProperties(_file);
		builder.setSourcepath(new File[] {_sourcePath});

		Jar[] jars = builder.builds();

		// Report both task failures and bnd build failures

		boolean taskFailed = report();
		boolean bndFailed = report(builder);

		// Fail this build if failure is not ok and either the task failed or
		// the bnd build failed

		if (taskFailed || bndFailed) {
			throw new BuildException(
				"bnd failed",
				new org.apache.tools.ant.Location(_file.getAbsolutePath()));
		}

		for (Jar jar : jars) {
			String bsn = jar.getName();

			File outputFile = _outputPath;

			if (_outputPath.isDirectory()) {
				String path = builder.getProperty("-output");

				if (path != null) {
					outputFile = getFile(_outputPath, path);
				}
				else {
					outputFile = getFile(_outputPath, bsn + ".jar");
				}
			}

			if (!outputFile.exists() ||
				(outputFile.lastModified() <= jar.lastModified())) {

				jar.write(outputFile);

				Map<String, Resource> resources = jar.getResources();

				log(
					jar.getName() + " (" + outputFile.getName() + ") " +
						resources.size());

				doBaselineJar(jar, outputFile, bndProject);
			}
			else {
				Map<String, Resource> resources = jar.getResources();

				log(
					jar.getName() + " (" + outputFile.getName() + ") " +
						resources.size() + " (not modified)");
			}

			report();

			jar.close();
		}

		builder.close();
	}

	protected void doHeader(BundleInfo bundleInfo) {
		if (_headerPrinted) {
			return;
		}

		_headerPrinted = true;

		project.log(
			"[Baseline Report] Mode: " + _reportLevel, Project.MSG_WARN);

		if (bundleInfo.mismatch) {
			project.log(
				"[Baseline Warning] Bundle Version Change Recommended: " +
					bundleInfo.suggestedVersion,
				Project.MSG_WARN);
		}

		reportLog(
			" ", "PACKAGE_NAME", "DELTA", "CUR_VER", "BASE_VER", "REC_VER",
			"WARNINGS", "ATTRIBUTES");

		reportLog(
			"=", "==================================================",
			"==========", "==========", "==========", "==========",
			"==========", "==========");
	}

	protected void doInfo(BundleInfo bundleInfo, Info info, String warnings) {
		doHeader(bundleInfo);

		reportLog(
			String.valueOf(info.mismatch ? '*' : ' '), info.packageName,
			String.valueOf(info.packageDiff.getDelta()),
			String.valueOf(info.newerVersion),
			String.valueOf(info.olderVersion),
			String.valueOf(
				(info.suggestedVersion == null) ? "-" : info.suggestedVersion),
			warnings, String.valueOf(info.attributes));
	}

	protected void doPackageDiff(Diff diff) {
		StringBuffer sb = new StringBuffer();

		sb.append("\t");

		for (Diff curDiff : diff.getChildren()) {
			if (curDiff.getDelta() == Delta.UNCHANGED) {
				continue;
			}

			doDiff(curDiff, sb);
		}
	}

	protected String getBaselineResportsDirName() {
		if (_baselineResportsDirName != null) {
			return _baselineResportsDirName;
		}

		_baselineResportsDirName = project.getProperty(
			"baseline.jar.reports.dir.name");

		if (_baselineResportsDirName == null) {
			_baselineResportsDirName = _BASELINE_REPORTS_DIR;
		}

		return _baselineResportsDirName;
	}

	protected String getShortDelta(Delta delta) {
		if (delta == Delta.ADDED) {
			return "+";
		}
		else if (delta == Delta.CHANGED) {
			return "~";
		}
		else if (delta == Delta.MAJOR) {
			return ">";
		}
		else if (delta == Delta.MICRO) {
			return "0xB5";
		}
		else if (delta == Delta.MINOR) {
			return "<";
		}
		else if (delta == Delta.REMOVED) {
			return "-";
		}

		String deltaString = delta.toString();

		return String.valueOf(deltaString.charAt(0));
	}

	protected void reportLog(
		String string1, String string2, String string3, String string4,
		String string5, String string6, String string7, String string8) {

		String output = String.format(
			"%s %-50s %-10s %-10s %-10s %-10s %-10s", string1, string2, string3,
			string4, string5, string6, string7);

		project.log(output, Project.MSG_WARN);

		if (_reportLevelIsPersist) {
			try {
				if (_printWriter == null) {
					_logFile.createNewFile();

					_printWriter = new PrintWriter(_logFile);
				}

				_printWriter.println(output);
			}
			catch (IOException ioe) {
				throw new BuildException(ioe);
			}
		}
	}

	private static final String _BASELINE_REPORTS_DIR = "baseline-reports";

	private String _baselineResportsDirName;
	private Path _classpath;
	private List<File> _classpathFiles = new ArrayList<File>();
	private DiffPluginImpl _diffPluginImpl = new DiffPluginImpl();
	private File _file;
	private boolean _headerPrinted;
	private File _logFile;
	private File _outputPath;
	private PrintWriter _printWriter;
	private String _reportLevel;
	private boolean _reportLevelIsDiff;
	private boolean _reportLevelIsOff = true;
	private boolean _reportLevelIsPersist;
	private boolean _reportLevelIsStandard;
	private boolean _reportOnlyDirtyPackages;
	private File _sourcePath;

}