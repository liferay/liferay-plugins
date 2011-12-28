/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.deploymenthelper.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Raymond Augé
 */
public class DeploymentHelperContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();

		String deploymentFileNames = servletContext.getInitParameter(
			"deployment-files");

		if ((deploymentFileNames == null) || deploymentFileNames.equals("")) {
			servletContext.log(
				"No deployment files are specified in the web.xml");

			return;
		}

		String deploymentPath = servletContext.getInitParameter(
			"deployment-path");

		if ((deploymentPath == null) || deploymentPath.equals("")) {
			servletContext.log(
				"No deployment path is specified in the web.xml");

			return;
		}

		File deploymentPathFile = new File(deploymentPath);

		if (!deploymentPathFile.exists()) {
			servletContext.log(
				"The deployment path " + deploymentPath + " does not exist");

			return;
		}

		if (!deploymentPathFile.isDirectory()) {
			servletContext.log(
				"The deployment path " + deploymentPath +
					" is not a directory");

			return;
		}

		if (!deploymentPathFile.canWrite()) {
			servletContext.log(
				"The deployment path " + deploymentPath + " is not writable");

			return;
		}

		for (String deploymentFileName : deploymentFileNames.split(",")) {
			String fileName = deploymentFileName.trim();

			if (deploymentFileName.lastIndexOf("/") != -1) {
				fileName = deploymentFileName.substring(
					deploymentFileName.lastIndexOf("/") + 1);
			}

			try {
				InputStream inputStream = servletContext.getResourceAsStream(
					deploymentFileName);

				if (inputStream == null) {
					servletContext.log(
						"Could not find " + deploymentFileName +
							" in the WAR file");

					continue;
				}

				servletContext.log("Copying " + deploymentFileName);

				File file = new File(deploymentPathFile, fileName);

				copy(servletContext, inputStream, new FileOutputStream(file));

				servletContext.log(
					"Successfully copied " + deploymentFileName + " to " +
						file.getAbsolutePath());
			}
			catch (Exception e) {
				servletContext.log(
					"An error occured while attempting to process " +
						deploymentFileName + ":\n" + e.getMessage(),
					e);
			}
		}
	}

	public void copy(
			ServletContext servletContext, InputStream inputStream,
			OutputStream outputStream)
		throws Exception {

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
				outputStream.write(bytes, 0, value);
			}
		}
		finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
				}
			}
			catch (Exception e) {
				servletContext.log(e.getMessage(), e);
			}

			try {
				if (outputStream != null) {
					outputStream.close();
				}
			}
			catch (Exception e) {
				servletContext.log(e.getMessage(), e);
			}
		}
	}

}