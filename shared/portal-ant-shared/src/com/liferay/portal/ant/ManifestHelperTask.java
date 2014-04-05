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

package com.liferay.portal.ant;

import aQute.bnd.osgi.Analyzer;
import aQute.bnd.osgi.Constants;

import com.liferay.portal.kernel.util.OSDetector;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;

/**
 * @author Raymond Augé
 */
public class ManifestHelperTask extends Task {

	@Override
	public void execute() throws BuildException {
		try {
			doExecute();
		}
		catch (Exception e) {
			throw new BuildException(e);
		}
	}

	public void setAnalyze(boolean analyze) {
		_analyze = analyze;
	}

	public void setClasspathRef(Reference reference) {
		if (_path == null) {
			_path = new Path(getProject());
		}

		Path path = _path.createPath();

		path.setRefid(reference);
	}

	public void setProjectDirPropertyName(String projectDirPropertyName) {
		_projectDirPropertyName = projectDirPropertyName;
	}

	protected void doExecute() throws Exception {
		if (_projectDirPropertyName == null) {
			throw new BuildException(
				"Attribute projectDirPropertyName must be set");
		}

		Project project = getProject();

		project.setProperty("build.revision", getBuildRevision());
		project.setProperty("build.time", getDateString(new Date()));
		project.setProperty(
			"release.info.build.date",
			String.valueOf(ReleaseInfo.getBuildDate()));
		project.setProperty(
			"release.info.build.number",
			String.valueOf(ReleaseInfo.getBuildNumber()));
		project.setProperty(
			"release.info.code.name", ReleaseInfo.getCodeName());
		project.setProperty(
			"release.info.parent.build.number",
			String.valueOf(ReleaseInfo.getParentBuildNumber()));
		project.setProperty(
			"release.info.release.info", ReleaseInfo.getReleaseInfo());
		project.setProperty(
			"release.info.server.info", ReleaseInfo.getServerInfo());
		project.setProperty("release.info.vendor", ReleaseInfo.getVendor());

		String releaseInfoVersion = project.getProperty("release.info.version");

		if (Validator.isNull(releaseInfoVersion)) {
			project.setProperty(
				"release.info.version", ReleaseInfo.getVersion());
		}

		if (!_analyze) {
			return;
		}

		Analyzer analyzer = new Analyzer();

		analyzer.setBase(project.getBaseDir());

		File classesDir = new File(project.getBaseDir(), "classes");

		analyzer.setJar(classesDir);

		File file = new File(project.getBaseDir(), "bnd.bnd");

		if (file.exists()) {
			analyzer.setProperties(file);
		}
		else {
			analyzer.setProperty(Constants.EXPORT_PACKAGE, "*");
			analyzer.setProperty(
				Constants.IMPORT_PACKAGE, "*;resolution:=optional");
		}

		Manifest manifest = analyzer.calcManifest();

		Attributes attributes = manifest.getMainAttributes();

		project.setProperty(
			"export.packages", attributes.getValue(Constants.EXPORT_PACKAGE));
		project.setProperty(
			"import.packages", attributes.getValue(Constants.IMPORT_PACKAGE));

		analyzer.close();
	}

	protected String execute(String command) throws Exception {
		Runtime runtime = Runtime.getRuntime();

		Process process = runtime.exec(command);

		return StringUtil.read(process.getInputStream());
	}

	protected String getBuildRevision() throws Exception {
		Project project = getProject();

		File projectDir = new File(
			project.getBaseDir(), project.getProperty(_projectDirPropertyName));

		File gitDir = new File(projectDir, ".git");

		if (gitDir.exists()) {
			if (OSDetector.isWindows()) {
				return execute("cmd /c git rev-parse HEAD");
			}
			else {
				return execute("git rev-parse HEAD");
			}
		}

		File svnDir = new File(projectDir, ".svn");

		if (svnDir.exists()) {
			if (OSDetector.isWindows()) {
				return execute("cmd /c svnversion .");
			}
			else {
				return execute("svnversion .");
			}
		}

		return StringPool.BLANK;
	}

	protected String getDateString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat(_PATTERN);

		return dateFormat.format(date);
	}

	private static final String _PATTERN = "EEE MMM d HH:mm:ss z yyyy";

	private boolean _analyze;
	private Path _path;
	private String _projectDirPropertyName;

}