/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.util;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ClassResolverUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.comparator.LayoutPriorityComparator;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import java.util.List;

/**
 * @author Eudaldo Alonso
 * @author Jonathan Lee
 */
public class LayoutSetPrototypeUtil {

	public static LayoutSetPrototype fetchLayoutSetPrototype(
			long companyId, String layoutSetPrototypeKey)
		throws PortalException {

		List<ExpandoValue> expandoValues =
			ExpandoValueLocalServiceUtil.getColumnValues(
				companyId, LayoutSetPrototype.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME,
				SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (ExpandoValue expandoValue : expandoValues) {
			if (!layoutSetPrototypeKey.equals(expandoValue.getString())) {
				continue;
			}

			return LayoutSetPrototypeLocalServiceUtil.fetchLayoutSetPrototype(
				expandoValue.getClassPK());
		}

		return null;
	}

	public static void removeLayoutSetPrototype(
			Group group, boolean privateLayout, String layoutSetPrototypeKey)
		throws PortalException {

		LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
			group.getGroupId(), privateLayout);

		UnicodeProperties settingsProperties =
			layoutSet.getSettingsProperties();

		settingsProperties.remove("last-merge-time");

		layoutSet.setSettingsProperties(settingsProperties);

		layoutSet.setLayoutSetPrototypeUuid(StringPool.BLANK);
		layoutSet.setLayoutSetPrototypeLinkEnabled(false);

		LayoutSetLocalServiceUtil.updateLayoutSet(layoutSet);

		LayoutSetLocalServiceUtil.updateLookAndFeel(
			group.getGroupId(), null, null, StringPool.BLANK, false);

		LayoutSetPrototype layoutSetPrototype = fetchLayoutSetPrototype(
			group.getCompanyId(), layoutSetPrototypeKey);

		Group layoutSetPrototypeGroup = layoutSetPrototype.getGroup();

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			layoutSetPrototypeGroup.getGroupId(), true);

		String[] layoutUuids = new String[layouts.size()];

		for (int i = 0; i < layouts.size(); i++) {
			Layout layout = layouts.get(i);

			layoutUuids[i] = layout.getUuid();
		}

		layouts = LayoutLocalServiceUtil.getLayouts(
			group.getGroupId(), privateLayout);

		layouts = ListUtil.sort(layouts, new LayoutPriorityComparator(false));

		for (Layout layout : layouts) {
			if (ArrayUtil.contains(
					layoutUuids, layout.getSourcePrototypeLayoutUuid())) {

				LayoutLocalServiceUtil.deleteLayout(
					layout.getGroupId(), privateLayout, layout.getLayoutId(),
					new ServiceContext());
			}
		}
	}

	public static void updateLayoutSetPrototype(
			Group group, boolean privateLayout, String layoutSetPrototypeKey)
		throws Exception {

		LayoutSetPrototype layoutSetPrototype = fetchLayoutSetPrototype(
			group.getCompanyId(), layoutSetPrototypeKey);

		if (layoutSetPrototype != null) {
			LayoutSetLocalServiceUtil.updateLayoutSetPrototypeLinkEnabled(
				group.getGroupId(), privateLayout, true,
				layoutSetPrototype.getUuid());

			LayoutSet layoutSet = group.getPublicLayoutSet();

			if (privateLayout) {
				layoutSet = group.getPrivateLayoutSet();
			}

			PortalClassInvoker.invoke(
				true, _mergeLayoutSetPrototypeLayoutsMethodKey, group,
				layoutSet);

			LayoutLocalServiceUtil.updatePriorities(
				group.getGroupId(), privateLayout);
		}
	}

	private static MethodKey _mergeLayoutSetPrototypeLayoutsMethodKey =
		new MethodKey(
			ClassResolverUtil.resolveByPortalClassLoader(
				"com.liferay.sites.kernel.util.SitesUtil"),
			"mergeLayoutSetPrototypeLayouts", Group.class, LayoutSet.class);

}