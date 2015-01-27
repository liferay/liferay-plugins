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

package com.liferay.asset.entry.set.hook.events;

import com.liferay.asset.entry.set.handler.AssetEntrySetHandler;
import com.liferay.asset.entry.set.util.AssetEntrySetManagerUtil;
import com.liferay.asset.entry.set.util.PortletPropsKeys;
import com.liferay.asset.entry.set.util.PortletPropsValues;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Calvin Keum
 */
public class StartupAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		try {
			doRun();
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doRun() throws Exception {
		initAssetEntrySetPortletIds();
		initAssetEntrySetHandlers();
	}

	protected void initAssetEntrySetHandlers() throws PortalException {
		String[] portletIds = PortletPropsValues.ASSET_ENTRY_SET_PORTLET_IDS;

		for (String portletId : portletIds) {
			String assetEntrySetHandlerClassName = PortletProps.get(
				PortletPropsKeys.ASSET_ENTRY_SET_HANDLER,
				new Filter(portletId));

			try {
				AssetEntrySetHandler assetEntrySetHandler =
					(AssetEntrySetHandler)
						InstanceFactory.newInstance(
							assetEntrySetHandlerClassName, String.class,
							portletId);

				AssetEntrySetManagerUtil.deleteAssetEntrySetHandler(
					assetEntrySetHandler);

				AssetEntrySetManagerUtil.addAssetEntrySetHandler(
					assetEntrySetHandler);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to add asset entry set handler " +
							assetEntrySetHandlerClassName);
				}
			}
		}
	}

	protected void initAssetEntrySetPortletIds() throws PortalException {
		String[] types = PortletPropsValues.ASSET_ENTRY_SET_TYPES;

		for (String type : types) {
			String assetEntrySetPortletId = PortletProps.get(
				PortletPropsKeys.ASSET_ENTRY_SET_PORTLET_ID, new Filter(type));

			try {
				AssetEntrySetManagerUtil.deleteAssetEntrySetPortletId(type);

				AssetEntrySetManagerUtil.addAssetEntrySetPortletId(
					type, assetEntrySetPortletId);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to add asset entry set portlet ID " +
							assetEntrySetPortletId);
				}
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(StartupAction.class);

}