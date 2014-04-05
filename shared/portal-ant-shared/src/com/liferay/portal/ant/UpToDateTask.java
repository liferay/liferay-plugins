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
import org.apache.tools.ant.taskdefs.UpToDate;

/**
 * @author Brian Wing Shun Chan
 */
public class UpToDateTask {

	public static boolean isUpToDate(File source, File target) {
		if (!source.exists() || !target.exists()) {
			return false;
		}

		Project project = AntUtil.getProject();

		UpToDate upToDate = new UpToDate();

		upToDate.setProject(project);
		upToDate.setProperty("uptodate");
		upToDate.setSrcfile(source);
		upToDate.setTargetFile(target);

		upToDate.execute();

		if (project.getProperty("uptodate") != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean isUpToDate(String source, String target) {
		return isUpToDate(new File(source), new File(target));
	}

}