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

import org.apache.axis.tools.ant.wsdl.NamespaceMapping;
import org.apache.axis.tools.ant.wsdl.Wsdl2javaAntTask;

/**
 * @author Brian Wing Shun Chan
 */
public class Wsdl2JavaTask {

	public static void generateJava(String url, String output) {
		generateJava(url, output, null);
	}

	public static void generateJava(String url, String output, String mapping) {
		Wsdl2javaAntTask wsdl2Java = new Wsdl2javaAntTask();

		if (mapping != null) {
			NamespaceMapping namespaceMapping = new NamespaceMapping();

			namespaceMapping.setFile(new File(mapping));

			wsdl2Java.addMapping(namespaceMapping);
		}

		wsdl2Java.setFailOnNetworkErrors(true);
		wsdl2Java.setOutput(new File(output));
		wsdl2Java.setPrintStackTraceOnFailure(true);
		wsdl2Java.setProject(AntUtil.getProject());
		wsdl2Java.setServerSide(true);
		wsdl2Java.setTestCase(false);
		wsdl2Java.setURL(url);

		try {
			wsdl2Java.execute();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}