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

import org.apache.tools.ant.taskdefs.Manifest;
import org.apache.tools.ant.taskdefs.ManifestException;

/**
 * @author Brian Wing Shun Chan
 */
public class ManifestTask {

	public static void manifest(File file, Manifest.Attribute[] attributes)
		throws ManifestException {

		String parentFile = file.getParent();

		if (parentFile != null) {
			MkdirTask.mkdir(parentFile);
		}

		org.apache.tools.ant.taskdefs.ManifestTask manifest =
			new org.apache.tools.ant.taskdefs.ManifestTask();

		manifest.setProject(AntUtil.getProject());
		manifest.setFile(file);

		for (int i = 0; i < attributes.length; i++) {
			manifest.addConfiguredAttribute(attributes[i]);
		}

		manifest.execute();
	}

	public static void manifest(String file, Manifest.Attribute[] attributes)
		throws ManifestException {

		manifest(new File(file), attributes);
	}

}