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

import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;

import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.BuildLogger;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;

/**
 * @author Brian Wing Shun Chan
 */
public class AntUtil {

	public static Project getProject() {
		Project project = new Project();

		BuildLogger buildLogger = new DefaultLogger() {

			@Override
			public void messageLogged(BuildEvent buildEvent) {
				int priority = buildEvent.getPriority();

				if (priority > msgOutputLevel) {
					return;
				}

				StringBundler sb = new StringBundler();

				try {
					boolean first = true;

					UnsyncBufferedReader unsyncBufferedReader =
						new UnsyncBufferedReader(
							new UnsyncStringReader(buildEvent.getMessage()));

					String line = unsyncBufferedReader.readLine();

					while (line != null) {
						if (!first) {
							sb.append(StringPool.OS_EOL);
						}

						first = false;

						sb.append(StringPool.DOUBLE_SPACE);
						sb.append(line);

						line = unsyncBufferedReader.readLine();
					}
				}
				catch (IOException ioe) {
				}

				String message = sb.toString();

				if (priority != Project.MSG_ERR) {
					printMessage(message, out, priority);
				}
				else {
					printMessage(message, err, priority);
				}

				log(message);
			}

		};

		buildLogger.setErrorPrintStream(System.err);
		buildLogger.setMessageOutputLevel(Project.MSG_INFO);
		buildLogger.setOutputPrintStream(System.out);

		project.addBuildListener(buildLogger);

		return project;
	}

}