/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.admin.util;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.knowledgebase.model.KBStructureField;
import com.liferay.knowledgebase.model.KBStructureOption;
import com.liferay.knowledgebase.model.impl.KBStructureFieldImpl;
import com.liferay.knowledgebase.model.impl.KBStructureOptionImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.portlet.PortletRequest;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * @author Peter Shin
 */
public class KBStructureContentUtil {

	public static String addKBStructureFields(
			String localizedLanguageId,
			List<KBStructureField> kbStructureFields)
		throws SystemException {

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("kb-structure");

		rootElement.addAttribute("default-language-id", localizedLanguageId);

		Element kbStructureContentElement = rootElement.addElement(
			"kb-structure-content");

		kbStructureContentElement.addAttribute(
			"language-id", localizedLanguageId);

		for (KBStructureField kbStructureField : kbStructureFields) {
			String kbStructureFieldId = String.valueOf(
				CounterLocalServiceUtil.increment());
			String kbStructureFieldName = StringEscapeUtils.escapeXml(
				kbStructureField.getName());
			String kbStructureFieldLabel = StringEscapeUtils.escapeXml(
				kbStructureField.getLabel());
			String kbStructureFieldType = StringEscapeUtils.escapeXml(
				kbStructureField.getType());

			Element kbStructureFieldElement =
				kbStructureContentElement.addElement("kb-structure-field");

			kbStructureFieldElement.addAttribute(
				"kb-structure-field-id", kbStructureFieldId);
			kbStructureFieldElement.addAttribute("name", kbStructureFieldName);
			kbStructureFieldElement.addAttribute(
				"label", kbStructureFieldLabel);
			kbStructureFieldElement.addAttribute("type", kbStructureFieldType);

			for (KBStructureOption kbStructureOption :
					kbStructureField.getKbStructureOptions()) {

				String kbStructureOptionId = String.valueOf(
					CounterLocalServiceUtil.increment());
				String kbStructureOptionLabel = StringEscapeUtils.escapeXml(
					kbStructureOption.getLabel());
				String kbStructureOptionValue = StringEscapeUtils.escapeXml(
					kbStructureOption.getValue());

				Element kbStructureOptionElement =
					kbStructureFieldElement.addElement("kb-structure-option");

				kbStructureOptionElement.addAttribute(
					"kb-structure-option-id", kbStructureOptionId);
				kbStructureOptionElement.addAttribute(
					"label", kbStructureOptionLabel);
				kbStructureOptionElement.addAttribute(
					"value", kbStructureOptionValue);
			}
		}

		try {
			return document.formattedString();
		}
		catch (IOException ioe) {
			return document.asXML();
		}
	}

	public static String deleteKBStructureFields(
			String localizedLanguageId, String content)
		throws PortalException {

		Document document = null;

		try {
			document = SAXReaderUtil.read(content);
		}
		catch (DocumentException de) {
			throw new PortalException(de);
		}

		Element rootElement = document.getRootElement();

		for (Element kbStructureContentElement : rootElement.elements()) {
			String languageId = kbStructureContentElement.attributeValue(
				"language-id");

			if (!languageId.equals(localizedLanguageId)) {
				continue;
			}

			kbStructureContentElement.detach();

			try {
				return document.formattedString();
			}
			catch (IOException ioe) {
				return document.asXML();
			}
		}

		return content;
	}

	public static List<KBStructureField> getKBStructureFields(
			String localizedLanguageId, String content)
		throws Exception {

		if (!Validator.isXml(content)) {
			return new ArrayList<KBStructureField>();
		}

		Document document = SAXReaderUtil.read(content);

		List<KBStructureField> kbStructureFields =
			new ArrayList<KBStructureField>();

		Element rootElement = document.getRootElement();

		Element kbStructureContentElement =
			(Element)rootElement.selectSingleNode(
				"kb-structure-content[@language-id='" +
					localizedLanguageId + "']");

		if (kbStructureContentElement == null) {
			String value = rootElement.attributeValue("default-language-id");

			kbStructureContentElement = (Element)rootElement.selectSingleNode(
				"kb-structure-content[@language-id='" + value + "']");
		}

		for (Element kbStructureFieldElement :
				kbStructureContentElement.elements()) {

			String kbStructureFieldId = kbStructureFieldElement.attributeValue(
				"kb-structure-field-id");
			String kbStructureFieldName = StringEscapeUtils.unescapeXml(
				kbStructureFieldElement.attributeValue("name"));
			String kbStructureFieldLabel = StringEscapeUtils.unescapeXml(
				kbStructureFieldElement.attributeValue("label"));
			String kbStructureFieldType = StringEscapeUtils.unescapeXml(
				kbStructureFieldElement.attributeValue("type"));

			KBStructureField kbStructureField = new KBStructureFieldImpl();

			kbStructureField.setKbStructureFieldId(kbStructureFieldId);
			kbStructureField.setName(kbStructureFieldName);
			kbStructureField.setLabel(kbStructureFieldLabel);
			kbStructureField.setType(kbStructureFieldType);

			List<KBStructureOption> kbStructureOptions =
				new ArrayList<KBStructureOption>();

			for (Element kbStructureOptionElement :
					kbStructureFieldElement.elements()) {

				String kbStructureOptionId =
					kbStructureOptionElement.attributeValue(
						"kb-structure-option-id");
				String kbStructureOptionLabel = StringEscapeUtils.unescapeXml(
					kbStructureOptionElement.attributeValue("label"));
				String kbStructureOptionValue = StringEscapeUtils.unescapeXml(
					kbStructureOptionElement.attributeValue("value"));

				KBStructureOption kbStructureOption =
					new KBStructureOptionImpl();

				kbStructureOption.setKbStructureOptionId(kbStructureOptionId);
				kbStructureOption.setLabel(kbStructureOptionLabel);
				kbStructureOption.setValue(kbStructureOptionValue);

				kbStructureOptions.add(kbStructureOption);
			}

			kbStructureField.setKbStructureOptions(kbStructureOptions);

			kbStructureFields.add(kbStructureField);
		}

		return kbStructureFields;
	}

	public static List<KBStructureField> getKBStructureFields(
		PortletRequest portletRequest) {

		List<KBStructureField> kbStructureFields =
			new ArrayList<KBStructureField>();

		long[] kbStructureFieldsIndexes = StringUtil.split(
			ParamUtil.getString(
				portletRequest, "kbStructureFieldsIndexes"), 0L);

		for (long kbStructureFieldIndex : kbStructureFieldsIndexes) {
			String kbStructureFieldIdParam =
				"kbStructureFieldId" + kbStructureFieldIndex;
			String kbStructureFieldNameParam =
				"kbStructureFieldName" + kbStructureFieldIndex;
			String kbStructureFieldLabelParam =
				"kbStructureFieldLabel" + kbStructureFieldIndex;
			String kbStructureFieldTypeParam =
				"kbStructureFieldType" + kbStructureFieldIndex;

			String kbStructureFieldId = ParamUtil.getString(
				portletRequest, kbStructureFieldIdParam);
			String kbStructureFieldName = ParamUtil.getString(
				portletRequest, kbStructureFieldNameParam);
			String kbStructureFieldLabel = ParamUtil.getString(
				portletRequest, kbStructureFieldLabelParam);
			String kbStructureFieldType = ParamUtil.getString(
				portletRequest, kbStructureFieldTypeParam);

			KBStructureField kbStructureField = new KBStructureFieldImpl();

			kbStructureField.setKbStructureFieldId(kbStructureFieldId);
			kbStructureField.setName(kbStructureFieldName);
			kbStructureField.setLabel(kbStructureFieldLabel);
			kbStructureField.setType(kbStructureFieldType);

			List<KBStructureOption> kbStructureOptions =
				new ArrayList<KBStructureOption>();

			String kbStructureOptionsIndexesParam =
				"kbStructureOptionsIndexes" + kbStructureFieldIndex;

			long[] kbStructureOptionsIndexes = StringUtil.split(
				ParamUtil.getString(
					portletRequest, kbStructureOptionsIndexesParam), 0L);

			for (long kbStructureOptionIndex : kbStructureOptionsIndexes) {
				String kbStructureOptionIdParam =
					"kbStructureField" + kbStructureFieldIndex +
						"kbStructureOptionId" + kbStructureOptionIndex;
				String kbStructureOptionLabelParam =
					"kbStructureField" + kbStructureFieldIndex +
						"kbStructureOptionLabel" + kbStructureOptionIndex;
				String kbStructureOptionValueParam =
					"kbStructureField" + kbStructureFieldIndex +
						"kbStructureOptionValue" + kbStructureOptionIndex;

				String kbStructureOptionId = ParamUtil.getString(
					portletRequest, kbStructureOptionIdParam);
				String kbStructureOptionLabel = ParamUtil.getString(
					portletRequest, kbStructureOptionLabelParam);
				String kbStructureOptionValue = ParamUtil.getString(
					portletRequest, kbStructureOptionValueParam);

				KBStructureOption kbStructureOption =
					new KBStructureOptionImpl();

				kbStructureOption.setKbStructureOptionId(kbStructureOptionId);
				kbStructureOption.setLabel(kbStructureOptionLabel);
				kbStructureOption.setValue(kbStructureOptionValue);

				kbStructureOptions.add(kbStructureOption);
			}

			kbStructureField.setKbStructureOptions(kbStructureOptions);

			kbStructureFields.add(kbStructureField);
		}

		return kbStructureFields;
	}

	public static String unescapeContent(String content) throws Exception {
		if (!Validator.isXml(content)) {
			return StringPool.BLANK;
		}

		Document document = SAXReaderUtil.read(content);

		Element rootElement = document.getRootElement();

		for (Element kbStructureContentElement : rootElement.elements()) {
			for (Element kbStructureFieldElement :
					kbStructureContentElement.elements()) {

				String kbStructureFieldName = StringEscapeUtils.unescapeXml(
					kbStructureFieldElement.attributeValue("name"));
				String kbStructureFieldLabel = StringEscapeUtils.unescapeXml(
					kbStructureFieldElement.attributeValue("label"));
				String kbStructureFieldType = StringEscapeUtils.unescapeXml(
					kbStructureFieldElement.attributeValue("type"));

				kbStructureFieldElement.addAttribute(
					"name", kbStructureFieldName);
				kbStructureFieldElement.addAttribute(
					"label", kbStructureFieldLabel);
				kbStructureFieldElement.addAttribute(
					"type", kbStructureFieldType);

				for (Element kbStructureOptionElement :
						kbStructureFieldElement.elements()) {

					String kbStructureOptionLabel =
						StringEscapeUtils.unescapeXml(
							kbStructureOptionElement.attributeValue("label"));
					String kbStructureOptionValue =
						StringEscapeUtils.unescapeXml(
							kbStructureOptionElement.attributeValue("value"));

					kbStructureOptionElement.addAttribute(
						"label", kbStructureOptionLabel);
					kbStructureOptionElement.addAttribute(
						"value", kbStructureOptionValue);
				}
			}
		}

		return document.formattedString();
	}

	public static String updateKBStructureFields(
			String localizedLanguageId,
			List<KBStructureField> kbStructureFields, String content)
		throws PortalException, SystemException {

		Document document = null;

		try {
			document = SAXReaderUtil.read(content);
		}
		catch (DocumentException de) {
			throw new PortalException(de);
		}

		Element rootElement = document.getRootElement();

		Element oldKBStructureContentElement =
			(Element)rootElement.selectSingleNode(
				"kb-structure-content[@language-id='" +
					localizedLanguageId + "']");

		if (oldKBStructureContentElement != null) {
			oldKBStructureContentElement.detach();
		}

		Element newKBStructureContentElement = rootElement.addElement(
			"kb-structure-content");

		newKBStructureContentElement.addAttribute(
			"language-id", localizedLanguageId);

		for (KBStructureField kbStructureField : kbStructureFields) {
			String kbStructureFieldId =
				kbStructureField.getKbStructureFieldId();
			String kbStructureFieldName = StringEscapeUtils.escapeXml(
				kbStructureField.getName());
			String kbStructureFieldLabel = StringEscapeUtils.escapeXml(
				kbStructureField.getLabel());
			String kbStructureFieldType = StringEscapeUtils.escapeXml(
				kbStructureField.getType());

			if (Validator.isNull(kbStructureFieldId)) {
				kbStructureFieldId = String.valueOf(
					CounterLocalServiceUtil.increment());
			}

			Element kbStructureFieldElement =
				newKBStructureContentElement.addElement("kb-structure-field");

			kbStructureFieldElement.addAttribute(
				"kb-structure-field-id", kbStructureFieldId);
			kbStructureFieldElement.addAttribute("name", kbStructureFieldName);
			kbStructureFieldElement.addAttribute(
				"label", kbStructureFieldLabel);
			kbStructureFieldElement.addAttribute("type", kbStructureFieldType);

			List<KBStructureOption> kbStructureOptions =
				kbStructureField.getKbStructureOptions();

			for (KBStructureOption kbStructureOption : kbStructureOptions) {
				String kbStructureOptionId =
					kbStructureOption.getKbStructureOptionId();
				String kbStructureOptionLabel = StringEscapeUtils.escapeXml(
					kbStructureOption.getLabel());
				String kbStructureOptionValue = StringEscapeUtils.escapeXml(
					kbStructureOption.getValue());

				if (Validator.isNull(kbStructureOptionId)) {
					kbStructureOptionId = String.valueOf(
						CounterLocalServiceUtil.increment());
				}

				Element kbStructureOptionElement =
					kbStructureFieldElement.addElement("kb-structure-option");

				kbStructureOptionElement.addAttribute(
					"kb-structure-option-id", kbStructureOptionId);
				kbStructureOptionElement.addAttribute(
					"label", kbStructureOptionLabel);
				kbStructureOptionElement.addAttribute(
					"value", kbStructureOptionValue);
			}
		}

		Map<String, Element> elements = new TreeMap<String, Element>();

		for (Element element : rootElement.elements()) {
			String languageId = element.attributeValue("language-id");

			elements.put(languageId, (Element)element.detach());
		}

		String defaultLanguageId = rootElement.attributeValue(
			"default-language-id");

		Element defaultKBStructureContentElement = elements.remove(
			defaultLanguageId);

		rootElement.add(defaultKBStructureContentElement);

		if (!localizedLanguageId.equals(defaultLanguageId)) {
			for (Map.Entry<String, Element> entry : elements.entrySet()) {
				rootElement.add(entry.getValue());
			}
		}
		else {
			updateLocalizations(
				rootElement, defaultKBStructureContentElement, elements);
		}

		try {
			return document.formattedString();
		}
		catch (IOException ioe) {
			return document.asXML();
		}
	}

	protected static Element updateLocalizations(
		Element rootElement, Element defaultKBStructureContentElement,
		Map<String, Element> elements) {

		for (Map.Entry<String, Element> entry : elements.entrySet()) {
			Element oldKBStructureContentElement = entry.getValue();

			Element newKBStructureContentElement =
				oldKBStructureContentElement.createCopy();

			for (Element newKBStructureFieldElement :
					newKBStructureContentElement.elements()) {

				newKBStructureFieldElement.detach();
			}

			for (Element defaultKBStructureFieldElement :
					defaultKBStructureContentElement.elements()) {

				String defaultKBStructureFieldId =
					defaultKBStructureFieldElement.attributeValue(
						"kb-structure-field-id");

				Element oldKBStructureFieldElement =
					(Element)oldKBStructureContentElement.selectSingleNode(
						"kb-structure-field[@kb-structure-field-id='" +
							defaultKBStructureFieldId + "']");

				if (oldKBStructureFieldElement == null) {
					newKBStructureContentElement.add(
						defaultKBStructureFieldElement.createCopy());

					continue;
				}

				Element newKBStructureFieldElement =
					oldKBStructureFieldElement.createCopy();

				for (Element newKBStructureOptionElement :
						newKBStructureFieldElement.elements()) {

					newKBStructureOptionElement.detach();
				}

				for (Element defaultKBStructureOptionElement :
						defaultKBStructureFieldElement.elements()) {

					String defaultKBStructureOptionId =
						defaultKBStructureOptionElement.attributeValue(
							"kb-structure-option-id");

					Element oldKBStructureOptionElement =
						(Element)oldKBStructureFieldElement.selectSingleNode(
							"kb-structure-option[@kb-structure-option-id='" +
								defaultKBStructureOptionId + "']");

					if (oldKBStructureOptionElement == null) {
						newKBStructureFieldElement.add(
							defaultKBStructureOptionElement.createCopy());
					}
					else {
						newKBStructureFieldElement.add(
							oldKBStructureOptionElement.createCopy());
					}
				}

				newKBStructureContentElement.add(newKBStructureFieldElement);
			}

			rootElement.add(newKBStructureContentElement);
		}

		return rootElement;
	}

}