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

package com.liferay.knowledgebase.service.impl;

import com.liferay.knowledgebase.KBTemplateContentException;
import com.liferay.knowledgebase.KBTemplateTitleException;
import com.liferay.knowledgebase.NoSuchTemplateException;
import com.liferay.knowledgebase.admin.social.AdminActivityKeys;
import com.liferay.knowledgebase.model.KBTemplate;
import com.liferay.knowledgebase.service.base.KBTemplateLocalServiceBaseImpl;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.portal.kernel.dao.orm.Conjunction;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.SystemEventConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class KBTemplateLocalServiceImpl extends KBTemplateLocalServiceBaseImpl {

	@Override
	public KBTemplate addKBTemplate(
			long userId, String title, String content,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// KB template

		User user = userPersistence.findByPrimaryKey(userId);
		long groupId = serviceContext.getScopeGroupId();
		Date now = new Date();

		validate(title, content);

		long kbTemplateId = counterLocalService.increment();

		KBTemplate kbTemplate = kbTemplatePersistence.create(kbTemplateId);

		kbTemplate.setUuid(serviceContext.getUuid());
		kbTemplate.setGroupId(groupId);
		kbTemplate.setCompanyId(user.getCompanyId());
		kbTemplate.setUserId(user.getUserId());
		kbTemplate.setUserName(user.getFullName());
		kbTemplate.setCreateDate(serviceContext.getCreateDate(now));
		kbTemplate.setModifiedDate(serviceContext.getModifiedDate(now));
		kbTemplate.setTitle(title);
		kbTemplate.setContent(content);

		kbTemplatePersistence.update(kbTemplate);

		// Resources

		resourceLocalService.addModelResources(kbTemplate, serviceContext);

		// Social

		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

		extraDataJSONObject.put("title", kbTemplate.getTitle());

		socialActivityLocalService.addActivity(
			userId, groupId, KBTemplate.class.getName(), kbTemplateId,
			AdminActivityKeys.ADD_KB_TEMPLATE, extraDataJSONObject.toString(),
			0);

		return kbTemplate;
	}

	@Override
	public void deleteGroupKBTemplates(long groupId)
		throws PortalException, SystemException {

		List<KBTemplate> kbTemplates = kbTemplatePersistence.findByGroupId(
			groupId);

		for (KBTemplate kbTemplate : kbTemplates) {
			kbTemplateLocalService.deleteKBTemplate(kbTemplate);
		}
	}

	@Override
	@SystemEvent(
		action = SystemEventConstants.ACTION_SKIP,
		type = SystemEventConstants.TYPE_DELETE)
	public KBTemplate deleteKBTemplate(KBTemplate kbTemplate)
		throws PortalException, SystemException {

		// KB template

		kbTemplatePersistence.remove(kbTemplate);

		// Resources

		resourceLocalService.deleteResource(
			kbTemplate.getCompanyId(), KBTemplate.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, kbTemplate.getKbTemplateId());

		// KB Comments

		kbCommentLocalService.deleteKBComments(
			KBTemplate.class.getName(), kbTemplate.getKbTemplateId());

		// Social

		socialActivityLocalService.deleteActivities(
			KBTemplate.class.getName(), kbTemplate.getKbTemplateId());

		return kbTemplate;
	}

	@Override
	public KBTemplate deleteKBTemplate(long kbTemplateId)
		throws PortalException, SystemException {

		KBTemplate kbTemplate = kbTemplatePersistence.findByPrimaryKey(
			kbTemplateId);

		return kbTemplateLocalService.deleteKBTemplate(kbTemplate);
	}

	@Override
	public void deleteKBTemplates(long[] kbTemplateIds)
		throws PortalException, SystemException {

		for (long kbTemplateId : kbTemplateIds) {
			KBTemplate kbTemplate = null;

			try {
				kbTemplate = kbTemplatePersistence.findByPrimaryKey(
					kbTemplateId);
			}
			catch (NoSuchTemplateException nste) {
				continue;
			}

			kbTemplateLocalService.deleteKBTemplate(kbTemplate);
		}
	}

	@Override
	public List<KBTemplate> getGroupKBTemplates(
			long groupId, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return kbTemplatePersistence.findByGroupId(
			groupId, start, end, orderByComparator);
	}

	@Override
	public int getGroupKBTemplatesCount(long groupId) throws SystemException {
		return kbTemplatePersistence.countByGroupId(groupId);
	}

	@Override
	public List<KBTemplate> search(
			long groupId, String title, String content, Date startDate,
			Date endDate, boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			groupId, title, content, startDate, endDate, andOperator);

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	@Override
	public KBTemplate updateKBTemplate(
			long kbTemplateId, String title, String content,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// KB template

		validate(title, content);

		KBTemplate kbTemplate = kbTemplatePersistence.findByPrimaryKey(
			kbTemplateId);

		kbTemplate.setModifiedDate(serviceContext.getModifiedDate(null));
		kbTemplate.setTitle(title);
		kbTemplate.setContent(content);

		kbTemplatePersistence.update(kbTemplate);

		// Resources

		if ((serviceContext.getGroupPermissions() != null) ||
			(serviceContext.getGuestPermissions() != null)) {

			updateKBTemplateResources(
				kbTemplate, serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}

		// Social

		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

		extraDataJSONObject.put("title", kbTemplate.getTitle());

		socialActivityLocalService.addActivity(
			kbTemplate.getUserId(), kbTemplate.getGroupId(),
			KBTemplate.class.getName(), kbTemplateId,
			AdminActivityKeys.UPDATE_KB_TEMPLATE,
			extraDataJSONObject.toString(), 0);

		return kbTemplate;
	}

	@Override
	public void updateKBTemplateResources(
			KBTemplate kbTemplate, String[] groupPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.updateResources(
			kbTemplate.getCompanyId(), kbTemplate.getGroupId(),
			KBTemplate.class.getName(), kbTemplate.getKbTemplateId(),
			groupPermissions, guestPermissions);
	}

	protected DynamicQuery buildDynamicQuery(
		long groupId, String title, String content, Date startDate,
		Date endDate, boolean andOperator) {

		Junction junction = null;

		if (andOperator) {
			junction = RestrictionsFactoryUtil.conjunction();
		}
		else {
			junction = RestrictionsFactoryUtil.disjunction();
		}

		Map<String, String> terms = new HashMap<String, String>();

		if (Validator.isNotNull(title)) {
			terms.put("title", title);
		}

		if (Validator.isNotNull(content)) {
			terms.put("content", content);
		}

		for (Map.Entry<String, String> entry : terms.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			Disjunction disjunction = RestrictionsFactoryUtil.disjunction();

			for (String keyword : KnowledgeBaseUtil.splitKeywords(value)) {
				Criterion criterion = RestrictionsFactoryUtil.ilike(
					key, StringUtil.quote(keyword, StringPool.PERCENT));

				disjunction.add(criterion);
			}

			junction.add(disjunction);
		}

		if ((endDate != null) && (startDate != null)) {
			Disjunction disjunction = RestrictionsFactoryUtil.disjunction();

			String[] propertyNames = {"createDate", "modifiedDate"};

			for (String propertyName : propertyNames) {
				Property property = PropertyFactoryUtil.forName(propertyName);

				Conjunction conjunction = RestrictionsFactoryUtil.conjunction();

				conjunction.add(property.gt(startDate));
				conjunction.add(property.lt(endDate));

				disjunction.add(conjunction);
			}

			junction.add(disjunction);
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KBTemplate.class, getClassLoader());

		if (groupId > 0) {
			Property property = PropertyFactoryUtil.forName("groupId");

			dynamicQuery.add(property.eq(groupId));
		}

		return dynamicQuery.add(junction);
	}

	protected void validate(String title, String content)
		throws PortalException {

		if (Validator.isNull(title)) {
			throw new KBTemplateTitleException();
		}

		if (Validator.isNull(content)) {
			throw new KBTemplateContentException();
		}
	}

}