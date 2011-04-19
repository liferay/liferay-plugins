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
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.velocity.VelocityContext;
import com.liferay.portal.kernel.velocity.VelocityEngineUtil;
import com.liferay.portal.kernel.velocity.VelocityVariablesUtil;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Peter Shin
 */
public class KBVelocityTemplateParserImpl implements KBTemplateParser {

	public String transform(HttpServletRequest request) throws Exception {
		KBArticle kbArticle = (KBArticle)request.getAttribute(
			WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

		KBTemplate kbTemplate = kbArticle.getKBTemplate();

		String velocityTemplateId = getVelocityTemplateId(
			kbTemplate.getGroupId(), kbTemplate.getKbTemplateId());

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		VelocityEngineUtil.mergeTemplate(
			velocityTemplateId, kbTemplate.getContent(),
			getVelocityContext(request), unsyncStringWriter);

		return unsyncStringWriter.toString();
	}

	public String transform(
			String id, String content, Map<String, Object> variables,
			HttpServletRequest request)
		throws Exception {

		if (Validator.isNull(id) || Validator.isNull(content)) {
			return StringPool.BLANK;
		}

		VelocityContext velocityContext = getVelocityContext(request);

		if (variables != null) {
			for (Map.Entry<String, Object> entry : variables.entrySet()) {
				velocityContext.put(entry.getKey(), entry.getValue());
			}
		}

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		VelocityEngineUtil.mergeTemplate(
			id, content, velocityContext, unsyncStringWriter);

		return unsyncStringWriter.toString();
	}

	protected VelocityContext getVelocityContext(HttpServletRequest request)
		throws Exception {

		// Standard tools

		VelocityContext velocityContext =
			VelocityEngineUtil.getWrappedStandardToolsContext();

		// Variables

		VelocityVariablesUtil.insertVariables(velocityContext, request);

		// KB Article

		KBArticle kbArticle = (KBArticle)request.getAttribute(
			WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

		if (kbArticle != null) {
			velocityContext.put("kbArticle", kbArticle);
		}
		else {
			velocityContext.put("kbArticle", new KBArticleImpl());
		}

		// Restricted variables

		String[] restrictedVariables =
			PortletPropsValues.ADMIN_KB_TEMPLATE_RESTRICTED_VARIABLES;

		for (String restrictedVariable : restrictedVariables) {
			velocityContext.put(restrictedVariable, StringPool.BLANK);
		}

		return velocityContext;
	}

	protected String getVelocityTemplateId(long groupId, long kbTemplateId) {
		return String.valueOf(groupId).concat(StringPool.PERIOD).concat(
			String.valueOf(kbTemplateId));
	}

}