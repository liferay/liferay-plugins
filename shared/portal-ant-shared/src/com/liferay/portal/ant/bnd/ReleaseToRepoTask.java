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

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;

/**
 * @author Raymond Augé
 */
public class ReleaseToRepoTask extends BaseBndTask {

	public void setDeployRepo(String name) {
		_deployRepo = name;
	}

	public void setFile(File file) {
		_file = file;
	}

	@Override
	protected void doBeforeExecute() throws Exception {
		super.doBeforeExecute();

		if ((_file == null) || !_file.exists() || _file.isDirectory()) {
			if (_file != null) {
				project.log(
					"file is either missing or is a directory " +
						_file.getAbsolutePath(),
					Project.MSG_ERR);
			}

			throw new BuildException("file is invalid");
		}
	}

	@Override
	protected void doExecute() throws Exception {
		aQute.bnd.build.Project bndProject = getBndProject();

		try {
			if (_file.isFile() && _file.getName().endsWith(".jar")) {
				if (_deployRepo != null) {
					bndProject.deploy(_deployRepo, _file);
				}
				else {
					bndProject.deploy(_file);
				}
			}
			else {
				project.log(
					"Not a JAR file " + _file.getAbsolutePath(),
					Project.MSG_ERR);
			}
		}
		catch (Exception e) {
			throw new BuildException(
				e.getMessage(), e,
				new org.apache.tools.ant.Location(_file.getAbsolutePath()));
		}

		report(bndProject);

		if (bndProject.getErrors().size() > 0) {
			throw new BuildException(
				"Unable to deploy",
				new org.apache.tools.ant.Location(_file.getAbsolutePath()));
		}
	}

	private String _deployRepo;
	private File _file;

}