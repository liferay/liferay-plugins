/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.compat.portal.kernel.search;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.AttachedModel;
import com.liferay.portal.model.AuditedModel;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.GroupedModel;
import com.liferay.portal.model.ResourcedModel;
import com.liferay.portal.model.WorkflowedModel;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;
import com.liferay.portlet.expando.util.ExpandoBridgeIndexerUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Hugo Huijser
 * @author Ryan Park
 * @author Raymond Augé
 */
public abstract class BaseIndexer
	extends com.liferay.portal.kernel.search.BaseIndexer {

	public BaseIndexer() {
		_documentImpl = new DocumentImpl();
	}

	public String getSortField(String orderByCol) {
		String sortField = doGetSortField(orderByCol);

		if (_documentImpl.isDocumentSortableTextField(sortField)) {
			return DocumentImpl.getSortableFieldName(sortField);
		}

		return sortField;
	}

	@Override
	protected Document getBaseModelDocument(
			String portletId, BaseModel<?> baseModel)
		throws SystemException {

		Document document = newDocument();

		String className = baseModel.getModelClassName();

		long classPK = 0;
		long resourcePrimKey = 0;

		if (baseModel instanceof ResourcedModel) {
			ResourcedModel resourcedModel = (ResourcedModel)baseModel;

			classPK = resourcedModel.getResourcePrimKey();
			resourcePrimKey = resourcedModel.getResourcePrimKey();
		}
		else {
			classPK = (Long)baseModel.getPrimaryKeyObj();
		}

		document.addUID(portletId, classPK);

		List<AssetCategory> assetCategories =
			AssetCategoryLocalServiceUtil.getCategories(className, classPK);

		long[] assetCategoryIds = StringUtil.split(
			ListUtil.toString(
				assetCategories, AssetCategory.CATEGORY_ID_ACCESSOR),
			0L);

		document.addKeyword(Field.ASSET_CATEGORY_IDS, assetCategoryIds);

		addSearchAssetCategoryTitles(
			document, Field.ASSET_CATEGORY_TITLES, assetCategories);

		String[] assetTagNames = AssetTagLocalServiceUtil.getTagNames(
			className, classPK);

		document.addText(Field.ASSET_TAG_NAMES, assetTagNames);

		document.addKeyword(Field.ENTRY_CLASS_NAME, className);
		document.addKeyword(Field.ENTRY_CLASS_PK, classPK);
		document.addKeyword(Field.PORTLET_ID, portletId);

		if (resourcePrimKey > 0) {
			document.addKeyword(Field.ROOT_ENTRY_CLASS_PK, resourcePrimKey);
		}

		if (baseModel instanceof AttachedModel) {
			AttachedModel attachedModel = (AttachedModel)baseModel;

			document.addKeyword(
				Field.CLASS_NAME_ID, attachedModel.getClassNameId());
			document.addKeyword(Field.CLASS_PK, attachedModel.getClassPK());
		}

		if (baseModel instanceof AuditedModel) {
			AuditedModel auditedModel = (AuditedModel)baseModel;

			document.addKeyword(Field.COMPANY_ID, auditedModel.getCompanyId());
			document.addDate(Field.CREATE_DATE, auditedModel.getCreateDate());
			document.addDate(
				Field.MODIFIED_DATE, auditedModel.getModifiedDate());
			document.addKeyword(Field.USER_ID, auditedModel.getUserId());

			String userName = PortalUtil.getUserName(
				auditedModel.getUserId(), auditedModel.getUserName());

			document.addKeyword(Field.USER_NAME, userName, true);
		}

		if (baseModel instanceof GroupedModel) {
			GroupedModel groupedModel = (GroupedModel)baseModel;

			document.addKeyword(
				Field.GROUP_ID, getParentGroupId(groupedModel.getGroupId()));
			document.addKeyword(
				Field.SCOPE_GROUP_ID, groupedModel.getGroupId());
		}

		if (baseModel instanceof WorkflowedModel) {
			WorkflowedModel workflowedModel = (WorkflowedModel)baseModel;

			document.addKeyword(Field.STATUS, workflowedModel.getStatus());
		}

		ExpandoBridgeIndexerUtil.addAttributes(
			document, baseModel.getExpandoBridge());

		return document;
	}

	protected Document newDocument() {
		return (Document)_documentImpl.clone();
	}

	protected void setSortableTextFields(String[] sortableTextFields) {
		_documentImpl.setSortableTextFields(sortableTextFields);
	}

	private DocumentImpl _documentImpl;

}