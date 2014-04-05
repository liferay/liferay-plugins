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

import org.apache.tools.ant.taskdefs.Delete;
import org.apache.tools.ant.types.FileSet;

/**
 * @author Brian Wing Shun Chan
 */
public class DeleteTask {

	public static void deleteDirectory(File dir) {
		Delete delete = new Delete();

		delete.setProject(AntUtil.getProject());
		delete.setDir(dir);
		delete.setFailOnError(false);

		delete.execute();
	}

	public static void deleteDirectory(String dir) {
		deleteDirectory(new File(dir));
	}

	public static void deleteFile(File file) {
		Delete delete = new Delete();

		delete.setProject(AntUtil.getProject());
		delete.setFile(file);
		delete.setFailOnError(false);

		delete.execute();
	}

	public static void deleteFile(String file) {
		deleteFile(new File(file));
	}

	public static void deleteFiles(File dir, String includes, String excludes) {
		Delete delete = new Delete();

		delete.setProject(AntUtil.getProject());
		delete.setFailOnError(false);

		FileSet fileSet = new FileSet();

		fileSet.setDir(dir);
		fileSet.setIncludes(includes);
		fileSet.setExcludes(excludes);

		delete.addFileset(fileSet);

		delete.execute();
	}

	public static void deleteFiles(
		String dir, String includes, String excludes) {

		deleteFiles(new File(dir), includes, excludes);
	}

}