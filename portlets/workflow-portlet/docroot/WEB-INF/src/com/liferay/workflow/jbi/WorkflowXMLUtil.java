/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.workflow.jbi;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.workflow.model.WorkflowDefinition;
import com.liferay.workflow.model.WorkflowInstance;
import com.liferay.workflow.model.WorkflowTask;
import com.liferay.workflow.model.WorkflowTaskFormElement;
import com.liferay.workflow.model.WorkflowToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="WorkflowXMLUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Charles May
 *
 */
public class WorkflowXMLUtil {

	public static Date parseDate(String date) {
		if (Validator.isNull(date)) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		return GetterUtil.getDate(date, sdf);
	}

	public static Date parseDateTime(String date) {
		if (Validator.isNull(date)) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aaa");

		return GetterUtil.getDate(date, sdf);
	}

	public static WorkflowDefinition parseDefinition(String xml)
		throws DocumentException {

		Document doc = SAXReaderUtil.read(xml);

		Element root = doc.getRootElement();

		return parseDefinition(root.element("definition"));
	}

	public static WorkflowDefinition parseDefinition(Element el) {
		long definitionId = GetterUtil.getLong(
			el.elementText("definitionId"));
		String name = el.elementText("name");
		String type = el.elementText("type");
		double version = GetterUtil.getDouble(el.elementText("version"));

		WorkflowDefinition definition = new WorkflowDefinition();

		definition.setDefinitionId(definitionId);
		definition.setName(name);
		definition.setType(type);
		definition.setVersion(version);

		return definition;
	}

	public static List parseDefinitions(Element root) {
		List definitions = new ArrayList();

		Iterator itr = root.elements("definition").iterator();

		while (itr.hasNext()) {
			Element el = (Element)itr.next();

			WorkflowDefinition definition = parseDefinition(el);

			definitions.add(definition);
		}

		return definitions;
	}

	public static Map parseErrors(String xml) throws DocumentException {
		Map errors = new LinkedHashMap();

		Document doc = SAXReaderUtil.read(xml);

		Element root = doc.getRootElement();

		Iterator itr = root.elements("error").iterator();

		while (itr.hasNext()) {
			Element el = (Element)itr.next();

			String name = el.elementText("name");
			String code = el.elementText("code");

			errors.put(name, code);
		}

		return errors;
	}

	public static WorkflowInstance parseInstance(String xml)
		throws DocumentException, ParseException {

		Document doc = SAXReaderUtil.read(xml);

		Element root = doc.getRootElement();

		return parseInstance(root.element("instance"));
	}

	public static WorkflowInstance parseInstance(Element el)
		throws ParseException {

		long instanceId = GetterUtil.getLong(el.elementText("instanceId"));
		Date startDate = parseDateTime(el.elementText("startDate"));
		Date endDate = parseDateTime(el.elementText("endDate"));
		boolean ended = GetterUtil.getBoolean(el.elementText("ended"));

		List definitions = parseDefinitions(el);

		WorkflowDefinition definition =
			(WorkflowDefinition)definitions.get(0);

		List tokens = parseTokens(el);

		WorkflowToken token = new WorkflowToken();

		if (tokens.size() > 0) {
			token = (WorkflowToken)tokens.get(0);
		}

		WorkflowInstance instance = new WorkflowInstance();

		instance.setInstanceId(instanceId);
		instance.setDefinition(definition);
		instance.setStartDate(startDate);
		instance.setEndDate(endDate);
		instance.setToken(token);
		instance.setEnded(ended);

		return instance;
	}

	public static List parseInstances(Element root) throws ParseException {
		List instances = new ArrayList();

		Iterator itr = root.elements("instance").iterator();

		while (itr.hasNext()) {
			Element el = (Element)itr.next();

			WorkflowInstance instance = parseInstance(el);

			instances.add(instance);
		}

		return instances;
	}

	public static int parseInt(String xml, String name)
		throws DocumentException {

		return GetterUtil.getInteger(parseString(xml, name));
	}

	public static List parseList(String xml, String name)
		throws DocumentException, ParseException {

		Document doc = SAXReaderUtil.read(xml);

		Element root = doc.getRootElement();

		if (name.equals("definitions")) {
			return parseDefinitions(root);
		}
		else if (name.equals("instances")) {
			return parseInstances(root);
		}
		else if (name.equals("taskFormElements")) {
			return parseTaskFormElements(root);
		}
		else if (name.equals("tasks")) {
			return parseTasks(root);
		}
		else if (name.equals("taskTransitions")) {
			return parseTaskTransitions(root);
		}
		else if (name.equals("tokens")) {
			return parseTokens(root);
		}
		else {
			throw new DocumentException("List name " + name + " not valid");
		}
	}

	public static String parseString(String xml, String name)
		throws DocumentException {

		try {
			Document doc = SAXReaderUtil.read(xml);

			Element root = doc.getRootElement();

			Element el = root.element(name);

			if (el != null) {
				return el.getText();
			}
			else {
				return StringPool.BLANK;
			}
		}
		catch (DocumentException de) {
			_log.error("Error parsing " + name + " from:\n\n" + xml);

			throw de;
		}
	}

	public static List parseTaskFormElements(Element root) {
		List taskFormElements = new ArrayList();

		Iterator itr1 = root.elements("taskFormElement").iterator();

		while (itr1.hasNext()) {
			Element el = (Element)itr1.next();

			String type = el.elementText("type");
			String displayName = el.elementText("displayName");
			String variableName = el.elementText("variableName");
			String value = el.elementText("value");

			List valueList = new ArrayList();

			Iterator itr2 = el.element("values").elements("value").iterator();

			while (itr2.hasNext()) {
				Element valueEl = (Element)itr2.next();

				valueList.add(valueEl.getText());
			}

			boolean readable = GetterUtil.getBoolean(
				el.elementText("readable"));
			boolean writable = GetterUtil.getBoolean(
				el.elementText("writable"));
			boolean required = GetterUtil.getBoolean(
				el.elementText("required"));

			WorkflowTaskFormElement taskFormElement =
				new WorkflowTaskFormElement();

			taskFormElement.setType(type);
			taskFormElement.setDisplayName(displayName);
			taskFormElement.setVariableName(variableName);
			taskFormElement.setValue(value);
			taskFormElement.setValueList(valueList);
			taskFormElement.setReadable(readable);
			taskFormElement.setWritable(writable);
			taskFormElement.setRequired(required);

			taskFormElements.add(taskFormElement);
		}

		return taskFormElements;
	}

	public static WorkflowTask parseTask(String xml)
		throws DocumentException, ParseException {

		Document doc = SAXReaderUtil.read(xml);

		Element root = doc.getRootElement();

		return parseTask(root.element("task"));
	}

	public static WorkflowTask parseTask(Element el) throws ParseException {
		WorkflowTask task = new WorkflowTask();

		long taskId = GetterUtil.getLong(el.elementText("taskId"));

		if (taskId == 0) {
			return null;
		}

		String name = el.elementText("name");
		long assignedUserId = GetterUtil.getLong(
			el.elementText("assignedUserId"));
		Date createDate = parseDateTime(el.elementText("createDate"));
		Date startDate = parseDateTime(el.elementText("startDate"));
		Date endDate = parseDateTime(el.elementText("endDate"));

		List instances = parseInstances(el);

		WorkflowInstance instance = (WorkflowInstance)instances.get(0);

		task.setTaskId(taskId);
		task.setName(name);
		task.setInstance(instance);
		task.setAssignedUserId(assignedUserId);
		task.setCreateDate(createDate);
		task.setStartDate(startDate);
		task.setEndDate(endDate);

		return task;
	}

	public static List parseTasks(Element root) throws ParseException {
		List tasks = new ArrayList();

		Iterator itr = root.elements("task").iterator();

		while (itr.hasNext()) {
			Element el = (Element)itr.next();

			WorkflowTask task = parseTask(el);

			if (task != null) {
				tasks.add(task);
			}
		}

		return tasks;
	}

	public static List parseTaskTransitions(Element root) {
		List taskTransitions = new ArrayList();

		Iterator itr = root.elements("transition").iterator();

		while (itr.hasNext()) {
			Element el = (Element)itr.next();

			String name = el.getText();

			taskTransitions.add(name);
		}

		return taskTransitions;
	}

	public static List parseTokens(Element root) throws ParseException {
		List tokens = new ArrayList();

		Iterator itr = root.elements("token").iterator();

		while (itr.hasNext()) {
			Element el = (Element)itr.next();

			long tokenId = GetterUtil.getLong(el.elementText("tokenId"));
			String name = el.elementText("name");
			String type = el.elementText("type");

			List tasks = parseTasks(el);
			List children = parseTokens(el);

			WorkflowToken token = new WorkflowToken();

			token.setTokenId(tokenId);
			token.setName(name);
			token.setType(type);
			token.setTasks(tasks);
			token.setChildren(children);

			tokens.add(token);
		}

		return tokens;
	}

	private static Log _log = LogFactoryUtil.getLog(WorkflowXMLUtil.class);

}