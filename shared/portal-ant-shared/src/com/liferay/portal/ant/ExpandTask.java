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

import org.apache.tools.ant.taskdefs.Expand;

/**
 * @author Brian Wing Shun Chan
 */
public class ExpandTask {

	public static void expand(File source, File destination) {
		Expand expand = new Expand();

		expand.setDest(destination);
		expand.setProject(AntUtil.getProject());
		expand.setSrc(source);

		expand.execute();
	}

	public static void expand(String source, String destination) {
		expand(new File(source), new File(destination));
	}

}