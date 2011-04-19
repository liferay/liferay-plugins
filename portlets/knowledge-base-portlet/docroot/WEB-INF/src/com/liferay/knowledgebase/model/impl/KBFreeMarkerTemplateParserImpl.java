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

package com.liferay.knowledgebase.model.impl;

import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBTemplate;
import com.liferay.knowledgebase.model.KBTemplateParser;
import com.liferay.knowledgebase.util.PortletPropsValues;
import com.liferay.knowledgebase.util.WebKeys;
import com.liferay.portal.kernel.freemarker.FreeMarkerContext;
import com.liferay.portal.kernel.freemarker.FreeMarkerEngineUtil;
import com.liferay.portal.kernel.freemarker.FreeMarkerVariablesUtil;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Peter Shin
 */
public class KBFreeMarkerTemplateParserImpl implements KBTemplateParser {

	public String transform(HttpServletRequest request) throws Exception {
		KBArticle kbArticle = (KBArticle)request.getAttribute(
			WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

		KBTemplate kbTemplate = kbArticle.getKBTemplate();

		String freeMarkerTemplateId = getFreeMarkerTemplateId(
			kbTemplate.getGroupId(), kbTemplate.getKbTemplateId());

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		FreeMarkerEngineUtil.mergeTemplate(
			freeMarkerTemplateId, kbTemplate.getContent(),
			getFreeMarkerContext(request), unsyncStringWriter);

		return unsyncStringWriter.toString();
	}

	public String transform(
			String id, String content, Map<String, Object> variables,
			HttpServletRequest request)
		throws Exception {

		if (Validator.isNull(id) || Validator.isNull(content)) {
			return StringPool.BLANK;
		}

		FreeMarkerContext freeMarkerContext = getFreeMarkerContext(request);

		if (variables != null) {
			for (Map.Entry<String, Object> entry : variables.entrySet()) {
				freeMarkerContext.put(entry.getKey(), entry.getValue());
			}
		}

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		FreeMarkerEngineUtil.mergeTemplate(
			id, content, freeMarkerContext, unsyncStringWriter);

		return unsyncStringWriter.toString();
	}

	protected FreeMarkerContext getFreeMarkerContext(HttpServletRequest request)
		throws Exception {

		// Standard tools

		FreeMarkerContext freeMarkerContext =
			FreeMarkerEngineUtil.getWrappedStandardToolsContext();

		// Variables

		FreeMarkerVariablesUtil.insertVariables(freeMarkerContext, request);

		// KB Article

		KBArticle kbArticle = (KBArticle)request.getAttribute(
			WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

		if (kbArticle != null) {
			freeMarkerContext.put("kbArticle", kbArticle);
		}
		else {
			freeMarkerContext.put("kbArticle", new KBArticleImpl());
		}

		// Restricted variables

		String[] restrictedVariables =
			PortletPropsValues.ADMIN_KB_TEMPLATE_RESTRICTED_VARIABLES;

		for (String restrictedVariable : restrictedVariables) {
			freeMarkerContext.put(restrictedVariable, StringPool.BLANK);
		}

		return freeMarkerContext;
	}

	protected String getFreeMarkerTemplateId(long groupId, long kbTemplateId) {
		return String.valueOf(groupId).concat(StringPool.PERIOD).concat(
			String.valueOf(kbTemplateId));
	}

}