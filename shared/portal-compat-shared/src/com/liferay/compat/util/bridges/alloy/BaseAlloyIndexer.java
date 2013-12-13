/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.util.bridges.alloy;

import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
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
public abstract class BaseAlloyIndexer extends BaseIndexer {

	public AlloyServiceInvoker getAlloyServiceInvoker() {
		return alloyServiceInvoker;
	}

	@Override
	public String[] getClassNames() {
		return classNames;
	}

	@Override
	public String getPortletId() {
		return portletId;
	}

	@Override
	public void postProcessContextQuery(
			BooleanQuery contextQuery, SearchContext searchContext)
		throws Exception {

		int status = GetterUtil.getInteger(
			searchContext.getAttribute(Field.STATUS),
			WorkflowConstants.STATUS_ANY);

		if (status != WorkflowConstants.STATUS_ANY) {
			contextQuery.addRequiredTerm(Field.STATUS, status);
		}
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		BaseModel<?> baseModel = (BaseModel<?>)obj;

		Document document = new DocumentImpl();

		document.addUID(
			portletId, String.valueOf(baseModel.getPrimaryKeyObj()));

		AuditedModel auditedModel = (AuditedModel)obj;

		SearchEngineUtil.deleteDocument(
			getSearchEngineId(), auditedModel.getCompanyId(),
			document.get(Field.UID));
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		Document document = getDocument(obj);

		AuditedModel auditedModel = (AuditedModel)obj;

		SearchEngineUtil.updateDocument(
			getSearchEngineId(), auditedModel.getCompanyId(), document);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		Object model = alloyServiceInvoker.fetchModel(classPK);

		if (model != null) {
			doReindex(model);
		}
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexModels(companyId);
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		return portletId;
	}

	protected void reindexModels(long companyId) throws Exception {
		int count = alloyServiceInvoker.getModelsCount();

		int pages = count / Indexer.DEFAULT_INTERVAL;

		for (int i = 0; i <= pages; i++) {
			int start = (i * Indexer.DEFAULT_INTERVAL);
			int end = start + Indexer.DEFAULT_INTERVAL;

			reindexModels(companyId, start, end);
		}
	}

	protected void reindexModels(long companyId, int start, int end)
		throws Exception {

		List<Object> models = alloyServiceInvoker.getModels(start, end);

		if (models.isEmpty()) {
			return;
		}

		Collection<Document> documents = new ArrayList<Document>(models.size());

		for (Object model : models) {
			Document document = getDocument(model);

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
		if (this.classNames == null) {
			classNames = new String[] {className};
		}
	}

	protected void setPortletId(String portletId) {
		if (this.portletId == null) {
			this.portletId = portletId;
		}
	}

	protected AlloyServiceInvoker alloyServiceInvoker;
	protected String[] classNames;
	protected String portletId;

}