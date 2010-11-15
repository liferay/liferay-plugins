/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * <a href="DeploymentHelperContextListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Raymond Augé
 */
public class DeploymentHelperContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
	}

	public void contextInitialized(ServletContextEvent event) {
		ServletContext ctx = event.getServletContext();

		String deploymentFilesParam = ctx.getInitParameter("deployment-files");
		String deploymentPathParam = ctx.getInitParameter("deployment-path");

		if ((deploymentFilesParam == null) || deploymentFilesParam.equals("")) {
			ctx.log("No 'deployment-files' are defined in the web.xml");

			return;
		}

		if ((deploymentPathParam == null) || deploymentPathParam.equals("")) {
			ctx.log("No 'deployment-path' was defined in the web.xml");

			return;
		}

		File deploymentPath = new File(deploymentPathParam);

		if (!deploymentPath.exists()) {
			ctx.log(
				"The 'deployment-path' " + deploymentPathParam +
					" does not exist.");

			return;
		}

		if (!deploymentPath.isDirectory()) {
			ctx.log(
				"The 'deployment-path' " + deploymentPathParam +
					" is not a directory.");

			return;
		}

		if (!deploymentPath.canWrite()) {
			ctx.log(
				"The 'deployment-path' " + deploymentPathParam +
					" does not allow writing files.");

			return;
		}

		String[] deploymentFiles = deploymentFilesParam.split(",");

		for (String deploymentFile : deploymentFiles) {
			String fileName = deploymentFile.trim();

			if (deploymentFile.lastIndexOf("/") != -1) {
				fileName = deploymentFile.substring(
					deploymentFile.lastIndexOf("/") + 1);
			}

			try {
				InputStream is = ctx.getResourceAsStream(deploymentFile);

				if (is == null) {
					ctx.log(
						"Could not read '" + deploymentFile + "' from the war");

					continue;
				}

				ctx.log("Attempting to copy '" + deploymentFile + "'.");

				File file = new File(deploymentPath, fileName);

				copy(ctx, is, new FileOutputStream(file));

				ctx.log(
					"Successfully copied '" + deploymentFile + "' to '" +
						file.getAbsolutePath() + "'.");
			}
			catch (Exception e) {
				ctx.log(
					"An error occured while attempting to process the file '" +
						deploymentFile + "': \n" + e.getMessage(), e);
			}
		}
	}

	public void copy(
		ServletContext ctx, InputStream inputStream, OutputStream outputStream)
		throws IOException {

		if (inputStream == null) {
			throw new IllegalArgumentException("Input stream cannot be null");
		}

		if (outputStream == null) {
			throw new IllegalArgumentException("Output stream cannot be null");
		}

		try {
			byte[] bytes = new byte[8192];

			int value = -1;

			while ((value = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0 , value);
			}
		}
		finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
				}
			}
			catch (Exception e) {
				ctx.log(e.getMessage(), e);
			}

			try {
				if (outputStream != null) {
					outputStream.close();
				}
			}
			catch (Exception e) {
				ctx.log(e.getMessage(), e);
			}
		}
	}


}