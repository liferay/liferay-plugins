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

import aQute.bnd.ant.BndTask;
import aQute.bnd.build.Workspace;
import aQute.bnd.service.IndexProvider;

import java.io.File;

import java.util.Properties;
import java.util.Set;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;

/**
 * @author Raymond Augé
 */
public abstract class BaseBndTask extends BndTask {

	@Override
	public void execute() throws BuildException {
		try {
			project = getProject();

			doBeforeExecute();
			doExecute();
		}
		catch (Exception e) {
			if (e instanceof BuildException) {
				throw (BuildException)e;
			}

			throw new BuildException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public aQute.bnd.build.Project getBndProject() throws Exception {
		File bndRootFile = getBndRootFile();

		Workspace workspace = Workspace.getWorkspace(
			bndRootFile.getParentFile(), getBndDirName());

		workspace.setProperties(bndRootFile);

		aQute.bnd.build.Project bndProject = new aQute.bnd.build.Project(
			workspace, bndRootFile.getParentFile());

		bndProject.setFileMustExist(true);
		bndProject.setProperties(bndRootFile);

		Properties properties = new Properties();

		properties.putAll(project.getProperties());
		properties.putAll(bndProject.getProperties());

		bndProject.setProperties(properties);

		Set<Object> plugins = workspace.getPlugins();

		for (Object plugin : plugins) {
			if (plugin instanceof IndexProvider) {
				IndexProvider indexProvider = (IndexProvider)plugin;

				indexProvider.getIndexLocations();
			}
		}

		return bndProject;
	}

	public File getBndRootFile() {
		return _bndRootFile;
	}

	public void setBndRootFile(File bndRootFile) {
		_bndRootFile = bndRootFile;
	}

	protected void doBeforeExecute() throws Exception {
		if ((_bndRootFile == null) || !_bndRootFile.exists() ||
			_bndRootFile.isDirectory()) {

			if (_bndRootFile != null) {
				project.log(
					"bndRootFile is either missing or is a directory " +
						_bndRootFile.getAbsolutePath(),
					Project.MSG_ERR);
			}

			throw new Exception("bndRootFile is invalid");
		}

		_bndRootFile = _bndRootFile.getAbsoluteFile();

		File rootDir = _bndRootFile.getParentFile();

		_bndDir = new File(rootDir, getBndDirName());

		if (!rootDir.canWrite()) {
			return;
		}

		if (!_bndDir.exists() && !_bndDir.mkdir()) {
			return;
		}

		File buildFile = new File(_bndDir, "build.bnd");

		if (buildFile.exists() || !_bndDir.canWrite()) {
			return;
		}

		buildFile.createNewFile();
	}

	protected abstract void doExecute() throws Exception;

	protected String getBndDirName() {
		if (_bndDirName != null) {
			return _bndDirName;
		}

		_bndDirName = project.getProperty("baseline.jar.bnddir.name");

		if (_bndDirName == null) {
			_bndDirName = _BND_DIR;
		}

		return _bndDirName;
	}

	protected Project project;

	private static final String _BND_DIR = ".bnd";

	private File _bndDir;
	private String _bndDirName;
	private File _bndRootFile;

}