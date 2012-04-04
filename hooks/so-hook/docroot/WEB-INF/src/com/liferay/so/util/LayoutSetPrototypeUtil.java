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
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import java.util.List;
/*
 * @author Eudaldo Alonso
 */
public class LayoutSetPrototypeUtil {

	public static LayoutSetPrototype fetchLayoutSetPrototype(
			User user, boolean privateLayoutSetPrototype)
		throws PortalException, SystemException {

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

			ExpandoBridge expandoBridge = layoutSetPrototype.getExpandoBridge();

			String layoutSetPrototypeKey = (String)expandoBridge.getAttribute(
				SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY);

			if (privateLayoutSetPrototype &&
				layoutSetPrototypeKey.equals(
					SocialOfficeConstants.
						LAYOUT_SET_PROTOTYPE_KEY_USER_PRIVATE)) {

				return layoutSetPrototype;
			}
			else if (layoutSetPrototypeKey.equals(
						SocialOfficeConstants.
							LAYOUT_SET_PROTOTYPE_KEY_USER_PUBLIC)) {

				return layoutSetPrototype;
			}
		}

		return null;
	}

}