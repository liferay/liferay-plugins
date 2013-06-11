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

package com.liferay.wsrp.service.persistence;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.ManifestSummary;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.StagedModelDataHandler;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerRegistryUtil;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.model.SystemEventConstants;
import com.liferay.portal.service.persistence.SystemEventActionableDynamicQuery;
import com.liferay.portal.util.PortalUtil;

import com.liferay.wsrp.model.WSRPConsumer;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class WSRPConsumerExportActionableDynamicQuery
	extends WSRPConsumerActionableDynamicQuery {
	public WSRPConsumerExportActionableDynamicQuery(
		PortletDataContext portletDataContext) throws SystemException {
		_portletDataContext = portletDataContext;
	}

	@Override
	public long performCount() throws PortalException, SystemException {
		ManifestSummary manifestSummary = _portletDataContext.getManifestSummary();

		long modelAdditionCount = super.performCount();

		manifestSummary.addModelAdditionCount(getManifestSummaryKey(),
			modelAdditionCount);

		long modelDeletionCount = getModelDeletionCount();

		manifestSummary.addModelDeletionCount(getManifestSummaryKey(),
			modelDeletionCount);

		return modelAdditionCount;
	}

	@Override
	protected void addCriteria(DynamicQuery dynamicQuery) {
		_portletDataContext.addDateRangeCriteria(dynamicQuery, "modifiedDate");
	}

	protected long getModelDeletionCount()
		throws PortalException, SystemException {
		ActionableDynamicQuery actionableDynamicQuery = new SystemEventActionableDynamicQuery() {
				@Override
				protected void addCriteria(DynamicQuery dynamicQuery) {
					Property classNameIdProperty = PropertyFactoryUtil.forName(
							"classNameId");

					dynamicQuery.add(classNameIdProperty.eq(
							PortalUtil.getClassNameId(
								WSRPConsumer.class.getName())));

					Property typeProperty = PropertyFactoryUtil.forName("type");

					dynamicQuery.add(typeProperty.eq(
							SystemEventConstants.TYPE_DELETE));

					_addCreateDateProperty(dynamicQuery);
				}

				@Override
				protected void performAction(Object object) {
				}

				private void _addCreateDateProperty(DynamicQuery dynamicQuery) {
					if (!_portletDataContext.hasDateRange()) {
						return;
					}

					Property createDateProperty = PropertyFactoryUtil.forName(
							"createDate");

					Date startDate = _portletDataContext.getStartDate();

					dynamicQuery.add(createDateProperty.ge(startDate));

					Date endDate = _portletDataContext.getEndDate();

					dynamicQuery.add(createDateProperty.le(endDate));
				}
			};

		actionableDynamicQuery.setGroupId(_portletDataContext.getScopeGroupId());

		return actionableDynamicQuery.performCount();
	}

	protected String getManifestSummaryKey() {
		StagedModelDataHandler<?> stagedModelDataHandler = StagedModelDataHandlerRegistryUtil.getStagedModelDataHandler(WSRPConsumer.class.getName());

		return stagedModelDataHandler.getManifestSummaryKey(null);
	}

	@Override
	@SuppressWarnings("unused")
	protected void performAction(Object object)
		throws PortalException, SystemException {
		WSRPConsumer stagedModel = (WSRPConsumer)object;

		StagedModelDataHandlerUtil.exportStagedModel(_portletDataContext,
			stagedModel);
	}

	private PortletDataContext _portletDataContext;
}