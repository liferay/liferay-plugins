/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.User;
import com.liferay.portal.service.LayoutSetPrototypeServiceUtil;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import java.util.List;

/*
 * @author Eudaldo Alonso
 */
public class LayoutSetPrototypeUtil {

	public static LayoutSetPrototype[] getLayoutSetPrototypes(User user)
		throws PortalException, SystemException {

		LayoutSetPrototype[] layoutSetPrototypes = new LayoutSetPrototype[2];

		List<ExpandoValue> expandoValues =
			ExpandoValueLocalServiceUtil.getColumnValues(
				user.getCompanyId(), LayoutSetPrototype.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME,
				SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (ExpandoValue expandoValue : expandoValues) {
			LayoutSetPrototype layoutSetPrototype =
				LayoutSetPrototypeServiceUtil.getLayoutSetPrototype(
					expandoValue.getClassPK());

			String layoutSetPrototypeKey =
				(String)layoutSetPrototype.getExpandoBridge().getAttribute(
					SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY);

			boolean layoutSetPrototypeKeyUserPublic =
				layoutSetPrototypeKey.equals(
					SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_USER_PUBLIC);

			if (layoutSetPrototypeKeyUserPublic) {
				layoutSetPrototypes[0] = layoutSetPrototype;
			}

			boolean layoutSetPrototypeKeyUserPrivate =
				layoutSetPrototypeKey.equals(
					SocialOfficeConstants.
						LAYOUT_SET_PROTOTYPE_KEY_USER_PRIVATE);

			if (layoutSetPrototypeKeyUserPrivate) {
				layoutSetPrototypes[1] = layoutSetPrototype;
			}
		}

		return layoutSetPrototypes;
	}

}