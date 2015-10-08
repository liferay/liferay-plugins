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

package com.liferay.alloy.mvc;

import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.AuditedModel;
import com.liferay.portal.model.BaseModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class BaseAlloyIndexer extends BaseIndexer<BaseModel<?>> {

	public AlloyServiceInvoker getAlloyServiceInvoker() {
		return alloyServiceInvoker;
	}

	@Override
	public String getClassName() {
		return className;
	}

	@Override
	public void postProcessContextBooleanFilter(
			BooleanFilter booleanFilter, SearchContext searchContext)
		throws Exception {

		int status = GetterUtil.getInteger(
			searchContext.getAttribute(Field.STATUS),
			WorkflowConstants.STATUS_ANY);

		if (status != WorkflowConstants.STATUS_ANY) {
			booleanFilter.addRequiredTerm(Field.STATUS, status);
		}
	}

	@Override
	protected void doDelete(BaseModel<?> baseModel) throws Exception {
		Document document = new DocumentImpl();

		document.addUID(
			className, String.valueOf(baseModel.getPrimaryKeyObj()));

		AuditedModel auditedModel = (AuditedModel)baseModel;

		SearchEngineUtil.deleteDocument(
			getSearchEngineId(), auditedModel.getCompanyId(),
			document.get(Field.UID));
	}

	@Override
	protected void doReindex(BaseModel<?> baseModel) throws Exception {
		Document document = getDocument(baseModel);

		AuditedModel auditedModel = (AuditedModel)baseModel;

		SearchEngineUtil.updateDocument(
			getSearchEngineId(), auditedModel.getCompanyId(), document);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		BaseModel<?> baseModel = alloyServiceInvoker.fetchModel(classPK);

		if (baseModel != null) {
			doReindex(baseModel);
		}
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexModels(companyId);
	}

	@Override
	protected Document getBaseModelDocument(
		String portletId, BaseModel<?> baseModel) {

		Document document = super.getBaseModelDocument(portletId, baseModel);

		document.remove(Field.USER_ID);
		document.remove(Field.USER_NAME);

		return document;
	}

	protected void reindexModels(long companyId) throws Exception {
		int count = (int)alloyServiceInvoker.executeDynamicQueryCount(
			new Object[] {"companyId", companyId});

		int pages = count / Indexer.DEFAULT_INTERVAL;

		for (int i = 0; i <= pages; i++) {
			int start = (i * Indexer.DEFAULT_INTERVAL);
			int end = start + Indexer.DEFAULT_INTERVAL;

			reindexModels(companyId, start, end);
		}
	}

	protected void reindexModels(long companyId, int start, int end)
		throws Exception {

		List<BaseModel<?>> baseModels = alloyServiceInvoker.executeDynamicQuery(
			new Object[] {"companyId", companyId}, start, end);

		if (baseModels.isEmpty()) {
			return;
		}

		Collection<Document> documents = new ArrayList<>(baseModels.size());

		for (BaseModel<?> baseModel : baseModels) {
			Document document = getDocument(baseModel);

			documents.add(document);
		}

		SearchEngineUtil.updateDocuments(
			getSearchEngineId(), companyId, documents);
	}

	protected void setAlloyServiceInvoker(
		AlloyServiceInvoker alloyServiceInvoker) {

		this.alloyServiceInvoker = alloyServiceInvoker;
	}

	protected void setClassName(String className) {
		if (this.className == null) {
			this.className = className;
		}
	}

	protected AlloyServiceInvoker alloyServiceInvoker;
	protected String className;

}