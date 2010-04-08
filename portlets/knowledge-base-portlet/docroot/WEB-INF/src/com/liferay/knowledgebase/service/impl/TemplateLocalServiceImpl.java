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

package com.liferay.knowledgebase.service.impl;

import com.liferay.knowledgebase.TemplateContentException;
import com.liferay.knowledgebase.TemplateTitleException;
import com.liferay.knowledgebase.model.Template;
import com.liferay.knowledgebase.service.base.TemplateLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.util.Date;
import java.util.List;

/**
 * <a href="TemplateLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class TemplateLocalServiceImpl extends TemplateLocalServiceBaseImpl {

	public Template addTemplate(
			String uuid, long userId, String title, String content,
			String description, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Template

		User user = userPersistence.findByPrimaryKey(userId);
		long groupId = serviceContext.getScopeGroupId();
		Date now = new Date();

		validate(title, content);

		long templateId = counterLocalService.increment();

		Template template = templatePersistence.create(templateId);

		template.setUuid(uuid);
		template.setGroupId(groupId);
		template.setCompanyId(user.getCompanyId());
		template.setUserId(user.getUserId());
		template.setUserName(user.getFullName());
		template.setCreateDate(now);
		template.setModifiedDate(now);
		template.setTitle(title);
		template.setContent(content);
		template.setDescription(description);

		templatePersistence.update(template, false);

		return template;
	}

	public void deleteTemplate(long templateId)
		throws PortalException, SystemException {

		Template template = templatePersistence.findByPrimaryKey(templateId);

		deleteTemplate(template);
	}

	public void deleteTemplate(Template template) throws SystemException {

		// Template

		templatePersistence.remove(template);
	}

	public void deleteTemplates(long groupId)
		throws PortalException, SystemException {

		List<Template> templates = templatePersistence.findByGroupId(groupId);

		for (Template template : templates) {
			deleteTemplate(template);
		}
	}

	public List<Template> getGroupTemplates(
			long groupId, int start, int end, OrderByComparator obc)
		throws SystemException {

		return templatePersistence.findByGroupId(groupId, start, end, obc);
	}

	public int getGroupTemplatesCount(long groupId) throws SystemException {
		return templatePersistence.countByGroupId(groupId);
	}

	public Template getTemplate(long templateId)
		throws PortalException, SystemException {

		return templatePersistence.findByPrimaryKey(templateId);
	}

	public Template updateTemplate(
			long templateId, String title, String content, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Template

		validate(title, content);

		Template template = templatePersistence.findByPrimaryKey(templateId);

		template.setModifiedDate(new Date());
		template.setTitle(title);
		template.setContent(content);
		template.setDescription(description);

		templatePersistence.update(template, false);

		return template;
	}

	protected void validate(String title, String content)
		throws PortalException {

		if (Validator.isNull(title)) {
			throw new TemplateTitleException();
		}

		if (Validator.isNull(content)) {
			throw new TemplateContentException();
		}
	}

}