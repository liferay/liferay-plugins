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

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.xml.Attribute;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.io.File;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.axis.tools.ant.wsdl.Java2WsdlAntTask;
import org.apache.axis.tools.ant.wsdl.NamespaceMapping;
import org.apache.axis.tools.ant.wsdl.Wsdl2javaAntTask;
import org.apache.tools.ant.Project;

/**
 * @author Brian Wing Shun Chan
 */
public class Java2WsddTask {

	public static String[] generateWsdd(String className, String serviceName)
		throws Exception {

		// Create temp directory

		File tempDir = new File(Time.getTimestamp());

		tempDir.mkdir();

		// axis-java2wsdl

		String wsdlFileName = tempDir + "/service.wsdl";

		int pos = className.lastIndexOf(".");

		String packagePath = className.substring(0, pos);

		String[] packagePaths = StringUtil.split(packagePath, '.');

		String namespace = "urn:";

		for (int i = packagePaths.length - 1; i >= 0; i--) {
			namespace += packagePaths[i];

			if (i > 0) {
				namespace += ".";
			}
		}

		String location = "http://localhost/services/" + serviceName;

		String mappingPackage = packagePath.substring(
			0, packagePath.lastIndexOf(".")) + ".ws";

		Project project = AntUtil.getProject();

		Java2WsdlAntTask java2Wsdl = new Java2WsdlAntTask();

		NamespaceMapping mapping = new NamespaceMapping();

		mapping.setNamespace(namespace);
		mapping.setPackage(mappingPackage);

		java2Wsdl.setProject(project);
		java2Wsdl.setClassName(className);
		java2Wsdl.setOutput(new File(wsdlFileName));
		java2Wsdl.setLocation(location);
		java2Wsdl.setNamespace(namespace);
		java2Wsdl.addMapping(mapping);

		java2Wsdl.execute();

		// axis-wsdl2java

		Wsdl2javaAntTask wsdl2Java = new Wsdl2javaAntTask();

		wsdl2Java.setProject(project);
		wsdl2Java.setURL(wsdlFileName);
		wsdl2Java.setOutput(tempDir);
		wsdl2Java.setServerSide(true);
		wsdl2Java.setTestCase(false);
		wsdl2Java.setVerbose(false);

		wsdl2Java.execute();

		// Get content

		String deployContent = FileUtil.read(
			tempDir + "/" + StringUtil.replace(packagePath, ".", "/") +
				"/deploy.wsdd");

		deployContent = StringUtil.replace(
			deployContent, packagePath + "." + serviceName + "SoapBindingImpl",
			className);

		deployContent = _format(deployContent);

		String undeployContent = FileUtil.read(
			tempDir + "/" + StringUtil.replace(packagePath, ".", "/") +
				"/undeploy.wsdd");

		undeployContent = _format(undeployContent);

		// Delete temp directory

		DeleteTask.deleteDirectory(tempDir);

		return new String[] {deployContent, undeployContent};
	}

	private static void _addElements(
		Element element, Map<String, Element> elements) {

		for (Map.Entry<String, Element> entry : elements.entrySet()) {
			Element childElement = entry.getValue();

			element.add(childElement);
		}
	}

	private static String _format(String content) throws Exception {
		content = HtmlUtil.stripComments(content);

		Document document = SAXReaderUtil.read(content);

		Element rootElement = document.getRootElement();

		Element serviceElement = rootElement.element("service");

		Map<String, Element> arrayMappingElements =
			new TreeMap<String, Element>();
		Map<String, Element> typeMappingElements =
			new TreeMap<String, Element>();
		Map<String, Element> operationElements = new TreeMap<String, Element>();
		Map<String, Element> parameterElements = new TreeMap<String, Element>();

		for (Element element : serviceElement.elements()) {
			String elementName = element.getName();

			if (elementName.equals("arrayMapping")) {
				element.detach();

				arrayMappingElements.put(element.formattedString(), element);
			}
			else if (elementName.equals("operation")) {
				element.detach();

				List<Element> parameters = element.elements("parameter");

				StringBundler sb = new StringBundler(2 * parameters.size() + 2);

				String name = element.attributeValue("name");

				sb.append(name);
				sb.append("_METHOD_");

				for (Element parameterElement : parameters) {
					String type = parameterElement.attributeValue("type");

					sb.append(type);
					sb.append("_PARAMETER_");
				}

				operationElements.put(sb.toString(), element);
			}
			else if (elementName.equals("parameter")) {
				element.detach();

				String name = element.attributeValue("name");

				if (name.equals("allowedMethods")) {
					Attribute valueAttribute = element.attribute("value");

					String[] values = StringUtil.split(
						valueAttribute.getValue(), CharPool.SPACE);

					Arrays.sort(values);

					valueAttribute.setValue(
						StringUtil.merge(values, StringPool.SPACE));
				}
				else if (name.equals("schemaUnqualified")) {
					Attribute valueAttribute = element.attribute("value");

					String[] values = StringUtil.split(
						valueAttribute.getValue());

					Arrays.sort(values);

					valueAttribute.setValue(StringUtil.merge(values));
				}

				parameterElements.put(name, element);
			}
			else if (elementName.equals("typeMapping")) {
				element.detach();

				typeMappingElements.put(element.formattedString(), element);
			}
		}

		_addElements(serviceElement, arrayMappingElements);
		_addElements(serviceElement, typeMappingElements);
		_addElements(serviceElement, operationElements);
		_addElements(serviceElement, parameterElements);

		content = StringUtil.replace(
			document.formattedString(), "\"/>", "\" />");

		return content;
	}

}