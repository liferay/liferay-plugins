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

package com.liferay.knowledgebase.service.messaging;

import com.liferay.knowledgebase.service.ArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.ArticleServiceUtil;
import com.liferay.knowledgebase.service.ClpSerializer;
import com.liferay.knowledgebase.service.CommentLocalServiceUtil;
import com.liferay.knowledgebase.service.TemplateLocalServiceUtil;
import com.liferay.knowledgebase.service.TemplateServiceUtil;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

/**
 * @author Brian Wing Shun Chan
 */
public class ClpMessageListener extends BaseMessageListener {
	public static final String SERVLET_CONTEXT_NAME = ClpSerializer.SERVLET_CONTEXT_NAME;

	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");
		String servletContextName = message.getString("servletContextName");

		if (command.equals("undeploy") &&
				servletContextName.equals(SERVLET_CONTEXT_NAME)) {
			ArticleLocalServiceUtil.clearService();

			ArticleServiceUtil.clearService();
			CommentLocalServiceUtil.clearService();

			TemplateLocalServiceUtil.clearService();

			TemplateServiceUtil.clearService();
		}
	}
}