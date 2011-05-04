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

package com.liferay.knowledgebase.service.impl;

import com.liferay.knowledgebase.DuplicateKBStructureFieldLabelException;
import com.liferay.knowledgebase.DuplicateKBStructureFieldNameException;
import com.liferay.knowledgebase.DuplicateKBStructureOptionLabelException;
import com.liferay.knowledgebase.DuplicateKBStructureOptionValueException;
import com.liferay.knowledgebase.KBStructureFieldLabelException;
import com.liferay.knowledgebase.KBStructureFieldNameException;
import com.liferay.knowledgebase.KBStructureOptionLabelException;
import com.liferay.knowledgebase.KBStructureOptionValueException;
import com.liferay.knowledgebase.KBStructureTitleException;
import com.liferay.knowledgebase.NoSuchStructureException;
import com.liferay.knowledgebase.admin.social.AdminActivityKeys;
import com.liferay.knowledgebase.admin.util.KBStructureContentUtil;
import com.liferay.knowledgebase.model.KBStructure;
import com.liferay.knowledgebase.model.KBStructureField;
import com.liferay.knowledgebase.model.KBStructureOption;
import com.liferay.knowledgebase.service.base.KBStructureLocalServiceBaseImpl;
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
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * @author Brian Wing Shun Chan
 */
public class KBStructureLocalServiceImpl
	extends KBStructureLocalServiceBaseImpl {

	public KBStructure addKBStructure(
			long userId, String localizedLanguageId, String title,
			List<KBStructureField> kbStructureFields,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// KB structure

		User user = userPersistence.findByPrimaryKey(userId);
		long groupId = serviceContext.getScopeGroupId();
		Map<Locale, String> titleMap = new HashMap<Locale, String>();
		String content = StringPool.BLANK;
		Date now = new Date();

		validate(title, kbStructureFields);

		titleMap.put(LocaleUtil.fromLanguageId(localizedLanguageId), title);
		content = KBStructureContentUtil.addKBStructureFields(
			localizedLanguageId, kbStructureFields);

		long kbStructureId = counterLocalService.increment();

		KBStructure kbStructure = kbStructurePersistence.create(kbStructureId);

		kbStructure.setUuid(serviceContext.getUuid());
		kbStructure.setGroupId(groupId);
		kbStructure.setCompanyId(user.getCompanyId());
		kbStructure.setUserId(user.getUserId());
		kbStructure.setUserName(user.getFullName());
		kbStructure.setCreateDate(serviceContext.getCreateDate(now));
		kbStructure.setModifiedDate(serviceContext.getModifiedDate(now));
		kbStructure.setTitleMap(titleMap);
		kbStructure.setContent(content);

		kbStructurePersistence.update(kbStructure, false);

		// Resources

		if (serviceContext.getAddCommunityPermissions() ||
			serviceContext.getAddGuestPermissions()) {

			addKBStructureResources(
				kbStructure, serviceContext.getAddCommunityPermissions(),
				serviceContext.getAddGuestPermissions());
		}
		else {
			addKBStructureResources(
				kbStructure, serviceContext.getCommunityPermissions(),
				serviceContext.getGuestPermissions());
		}

		// Social

		socialActivityLocalService.addActivity(
			userId, groupId, KBStructure.class.getName(), kbStructureId,
			AdminActivityKeys.ADD_KB_STRUCTURE, StringPool.BLANK, 0);

		return kbStructure;
	}

	public void addKBStructureResources(
			KBStructure kbStructure, boolean addCommunityPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addResources(
			kbStructure.getCompanyId(), kbStructure.getGroupId(),
			kbStructure.getUserId(), KBStructure.class.getName(),
			kbStructure.getKbStructureId(), false, addCommunityPermissions,
			addGuestPermissions);
	}

	public void addKBStructureResources(
			KBStructure kbStructure, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addModelResources(
			kbStructure.getCompanyId(), kbStructure.getGroupId(),
			kbStructure.getUserId(), KBStructure.class.getName(),
			kbStructure.getKbStructureId(), communityPermissions,
			guestPermissions);
	}

	public void deleteGroupKBStructures(long groupId)
		throws PortalException, SystemException {

		List<KBStructure> kbStructures = kbStructurePersistence.findByGroupId(
			groupId);

		for (KBStructure kbStructure : kbStructures) {
			deleteKBStructure(kbStructure);
		}
	}

	public void deleteKBStructure(KBStructure kbStructure)
		throws PortalException, SystemException {

		// KB structure

		kbStructurePersistence.remove(kbStructure);

		// Resources

		resourceLocalService.deleteResource(
			kbStructure.getCompanyId(), KBStructure.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, kbStructure.getKbStructureId());

		// KB Comments

		kbCommentLocalService.deleteKBComments(
			KBStructure.class.getName(), kbStructure.getKbStructureId());

		// Social

		socialActivityLocalService.deleteActivities(
			KBStructure.class.getName(), kbStructure.getKbStructureId());
	}

	public void deleteKBStructure(long kbStructureId)
		throws PortalException, SystemException {

		KBStructure kbStructure = kbStructurePersistence.findByPrimaryKey(
			kbStructureId);

		deleteKBStructure(kbStructure);
	}

	public void deleteKBStructureLocalization(
			long kbStructureId, String localizedLanguageId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KBStructure kbStructure = kbStructurePersistence.findByPrimaryKey(
			kbStructureId);

		Map<Locale, String> titleMap = kbStructure.getTitleMap();
		String content = KBStructureContentUtil.deleteKBStructureFields(
			localizedLanguageId, kbStructure.getContent());

		titleMap.remove(LocaleUtil.fromLanguageId(localizedLanguageId));

		kbStructure.setModifiedDate(serviceContext.getModifiedDate(null));
		kbStructure.setTitleMap(titleMap);
		kbStructure.setContent(content);

		kbStructurePersistence.update(kbStructure, false);
	}

	public void deleteKBStructures(long[] kbStructureIds)
		throws PortalException, SystemException {

		for (long kbStructureId : kbStructureIds) {
			KBStructure kbStructure = null;

			try {
				kbStructure = kbStructurePersistence.findByPrimaryKey(
					kbStructureId);
			}
			catch (NoSuchStructureException nsse) {
				continue;
			}

			deleteKBStructure(kbStructure);
		}
	}

	public List<KBStructure> getGroupKBStructures(
			long groupId, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return kbStructurePersistence.findByGroupId(
			groupId, start, end, orderByComparator);
	}

	public int getGroupKBStructuresCount(long groupId) throws SystemException {
		return kbStructurePersistence.countByGroupId(groupId);
	}

	public List<KBStructure> search(
			long groupId, String title, String content, Date startDate,
			Date endDate, boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			groupId, title, content, startDate, endDate, andOperator);

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public KBStructure updateKBStructure(
			long kbStructureId, String localizedLanguageId, String title,
			List<KBStructureField> kbStructureFields,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// KB structure

		validate(title, kbStructureFields);

		KBStructure kbStructure = kbStructurePersistence.findByPrimaryKey(
			kbStructureId);

		Map<Locale, String> titleMap = kbStructure.getTitleMap();
		String content = KBStructureContentUtil.updateKBStructureFields(
			localizedLanguageId, kbStructureFields, kbStructure.getContent());

		titleMap.put(LocaleUtil.fromLanguageId(localizedLanguageId), title);

		kbStructure.setModifiedDate(serviceContext.getModifiedDate(null));
		kbStructure.setTitleMap(titleMap);
		kbStructure.setContent(content);

		kbStructurePersistence.update(kbStructure, false);

		// Resources

		if ((serviceContext.getCommunityPermissions() != null) ||
			(serviceContext.getGuestPermissions() != null)) {

			updateKBStructureResources(
				kbStructure, serviceContext.getCommunityPermissions(),
				serviceContext.getGuestPermissions());
		}

		// Social

		socialActivityLocalService.addActivity(
			kbStructure.getUserId(), kbStructure.getGroupId(),
			KBStructure.class.getName(), kbStructureId,
			AdminActivityKeys.UPDATE_KB_STRUCTURE, localizedLanguageId, 0);

		return kbStructure;
	}

	public void updateKBStructureResources(
			KBStructure kbStructure, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.updateResources(
			kbStructure.getCompanyId(), kbStructure.getGroupId(),
			KBStructure.class.getName(), kbStructure.getKbStructureId(),
			communityPermissions, guestPermissions);
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
			String escapedContent = StringEscapeUtils.escapeXml(content);

			terms.put("content", content + StringPool.SPACE + escapedContent);
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
			KBStructure.class, getClass().getClassLoader());

		if (groupId > 0) {
			Property property = PropertyFactoryUtil.forName("groupId");

			dynamicQuery.add(property.eq(groupId));
		}

		return dynamicQuery.add(junction);
	}

	protected void validate(
			String title, List<KBStructureField> kbStructureFields)
		throws PortalException {

		if (Validator.isNull(title)) {
			throw new KBStructureTitleException();
		}

		List<String> kbStructureFieldNames = new ArrayList<String>();
		List<String> kbStructureFieldLabels = new ArrayList<String>();

		for (KBStructureField kbStructureField : kbStructureFields) {
			if (Validator.isNull(kbStructureField.getName())) {
				throw new KBStructureFieldNameException();
			}

			if (kbStructureFieldNames.contains(kbStructureField.getName())) {
				throw new DuplicateKBStructureFieldNameException();
			}

			for (char c : kbStructureField.getName().toCharArray()) {
				if (!Validator.isChar(c) && !Validator.isDigit(c) &&
					(c != CharPool.DASH) && (c != CharPool.UNDERLINE)) {

					throw new KBStructureFieldNameException();
				}
			}

			kbStructureFieldNames.add(kbStructureField.getName());

			if (Validator.isNull(kbStructureField.getLabel())) {
				throw new KBStructureFieldLabelException();
			}

			if (kbStructureFieldLabels.contains(kbStructureField.getLabel())) {
				throw new DuplicateKBStructureFieldLabelException();
			}

			kbStructureFieldLabels.add(kbStructureField.getLabel());

			List<String> kbStructureOptionLabels = new ArrayList<String>();
			List<String> kbStructureOptionValues = new ArrayList<String>();

			for (KBStructureOption kbStructureOption :
					kbStructureField.getKbStructureOptions()) {

				if (Validator.isNull(kbStructureOption.getLabel())) {
					throw new KBStructureOptionLabelException();
				}

				if (kbStructureOptionLabels.contains(
						kbStructureOption.getLabel())) {

					throw new DuplicateKBStructureOptionLabelException();
				}

				kbStructureOptionLabels.add(kbStructureOption.getLabel());

				if (Validator.isNull(kbStructureOption.getValue())) {
					throw new KBStructureOptionValueException();
				}

				if (kbStructureOptionValues.contains(
						kbStructureOption.getValue())) {

					throw new DuplicateKBStructureOptionValueException();
				}

				kbStructureOptionValues.add(kbStructureOption.getValue());
			}
		}
	}

}