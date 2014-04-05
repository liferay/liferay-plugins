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

import com.liferay.portal.kernel.util.Validator;

import java.io.File;

import java.util.Map;

import org.apache.tools.ant.taskdefs.Copy;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.FilterSet;

/**
 * @author Brian Wing Shun Chan
 */
public class CopyTask {

	public static void copyDirectory(File source, File destination) {
		copyDirectory(source, destination, null, null);
	}

	public static void copyDirectory(
		File source, File destination, String includes, String excludes) {

		copyDirectory(source, destination, includes, excludes, false, true);
	}

	public static void copyDirectory(
		File source, File destination, String includes, String excludes,
		boolean overwrite, boolean preserveLastModified) {

		Copy copy = new Copy();

		FileSet fileSet = new FileSet();

		fileSet.setDir(source);

		if (Validator.isNotNull(excludes)) {
			fileSet.setExcludes(excludes);
		}

		if (Validator.isNotNull(includes)) {
			fileSet.setIncludes(includes);
		}

		copy.addFileset(fileSet);

		copy.setOverwrite(overwrite);
		copy.setPreserveLastModified(preserveLastModified);
		copy.setProject(AntUtil.getProject());
		copy.setTodir(destination);

		copy.execute();
	}

	public static void copyDirectory(String source, String destination) {
		copyDirectory(source, destination, null, null);
	}

	public static void copyDirectory(
		String source, String destination, String includes, String excludes) {

		copyDirectory(
			new File(source), new File(destination), includes, excludes);
	}

	public static void copyDirectory(
		String source, String destination, String includes, String excludes,
		boolean overwrite, boolean preserveLastModified) {

		copyDirectory(
			new File(source), new File(destination), includes, excludes,
			overwrite, preserveLastModified);
	}

	public static void copyFile(
		File sourceFile, File destinationDir, boolean overwrite,
		boolean preserveLastModified) {

		copyFile(
			sourceFile, destinationDir, null, overwrite, preserveLastModified);
	}

	public static void copyFile(
		File sourceFile, File destinationDir, Map<String, String> filterMap,
		boolean overwrite, boolean preserveLastModified) {

		copyFile(
			sourceFile, destinationDir, null, filterMap, overwrite,
			preserveLastModified);
	}

	public static void copyFile(
		File sourceFile, File destinationDir, String destinationFileName,
		Map<String, String> filterMap, boolean overwrite,
		boolean preserveLastModified) {

		Copy copy = new Copy();

		copy.setFile(sourceFile);
		copy.setFiltering(true);
		copy.setOverwrite(overwrite);
		copy.setPreserveLastModified(preserveLastModified);
		copy.setProject(AntUtil.getProject());

		if (destinationFileName == null) {
			copy.setTodir(destinationDir);
		}
		else {
			copy.setTofile(new File(destinationDir, destinationFileName));
		}

		if (filterMap != null) {
			FilterSet filterSet = copy.createFilterSet();

			for (Map.Entry<String, String> entry : filterMap.entrySet()) {
				String token = entry.getKey();
				String replacement = entry.getValue();

				filterSet.addFilter(token, replacement);
			}
		}

		copy.execute();
	}

}