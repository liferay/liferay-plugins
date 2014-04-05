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

import aQute.bnd.build.Project;
import aQute.bnd.build.ProjectTester;

import java.io.File;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.tools.ant.BuildException;

/**
 * @author Raymond Augé
 */
public class TestTask extends BaseBndTask {

	public void setContinuous(boolean continuous) {
		_continuous = continuous;
	}

	public void setDir(File dir) {
		_dir = dir;
	}

	public void setRunFiles(String runFiles) {
		_runFiles = runFiles;
	}

	@Override
	protected void doExecute() throws Exception {
		File baseDir = project.getBaseDir();

		Project bndProject = getBndProject();

		try {
			List<Project> projects = null;

			if (_runFiles == null) {
				projects = Collections.singletonList(bndProject);
			}
			else {
				StringTokenizer stringTokenizer = new StringTokenizer(
					_runFiles, ",");

				projects = new LinkedList<Project>();

				while (stringTokenizer.hasMoreTokens()) {
					String runFilePath = stringTokenizer.nextToken();

					runFilePath = runFilePath.trim();

					Project runProject = null;

					if (runFilePath.equals(".")) {
						runProject = bndProject;
					}
					else {
						File runFile = new File(baseDir, runFilePath);

						if (!runFile.isFile()) {
							throw new BuildException(
								String.format(
									"Run file %s does not exist or is not a " +
										"file",
								runFile.getAbsolutePath()));
						}

						runProject = new Project(
							bndProject.getWorkspace(), baseDir, runFile);

						runProject.setParent(bndProject);
					}

					projects.add(runProject);
				}
			}

			for (Project project : projects) {
				project.clear();
			}

			for (Project project : projects) {
				executeProject(project);
			}
		}
		catch (Exception e) {
			throw new BuildException(e);
		}
	}

	protected void executeProject(Project project) throws Exception {
		this.project.log("Testing " + project.getPropertiesFile());

		ProjectTester projectTester = project.getProjectTester();

		projectTester.setContinuous(_continuous);

		if (_dir != null) {
			projectTester.setCwd(_dir);
		}

		String testerDir = project.getProperty("tester.dir", "test-reports");

		File reportDir = new File(project.getBase(), testerDir);

		projectTester.setReportDir(reportDir);

		projectTester.prepare();

		if (report(project)) {
			throw new BuildException("Unable to initialise for testing");
		}

		int errors = projectTester.test();

		if (errors == 0) {
			this.project.log("All tests passed");
		}
		else {
			if (errors == 1) {
				this.project.log(
					errors + " Error", org.apache.tools.ant.Project.MSG_ERR);
			}
			else {
				this.project.log(
					errors + " Errors", org.apache.tools.ant.Project.MSG_ERR);
			}

			throw new BuildException("Tests failed");
		}

		if (report(project)) {
			throw new BuildException("Tests failed");
		}
	}

	private boolean _continuous;
	private File _dir;
	private String _runFiles;

}