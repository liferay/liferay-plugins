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

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.War;

/**
 * @author Brian Wing Shun Chan
 */
public class WarTask {

	public static void war(
		File baseDir, File destination, String excludes, File webxml) {

		Project project = AntUtil.getProject();

		War war = new War();

		war.setBasedir(baseDir);
		war.setDestFile(destination);
		war.setExcludes(excludes);

		File manifestFile = new File(
			baseDir.getAbsolutePath() + "/META-INF/MANIFEST.MF");

		if (manifestFile.exists()) {
			war.setManifest(manifestFile);
		}

		war.setProject(project);
		war.setWebxml(webxml);

		war.execute();
	}

	public static void war(
		String baseDir, String destination, String excludes, String webxml) {

		war(
			new File(baseDir), new File(destination), excludes,
			new File(webxml));
	}

}