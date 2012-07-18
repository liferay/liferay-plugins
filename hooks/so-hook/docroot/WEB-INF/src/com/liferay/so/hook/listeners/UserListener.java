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

package com.liferay.so.hook.listeners;

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.NoSuchGroupException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.so.util.LayoutSetPrototypeUtil;
import com.liferay.so.util.SocialOfficeConstants;
import com.liferay.so.util.SocialOfficeUtil;

/**
 * @author Jonathan Lee
 * @author Eudaldo Alonso
 */
public class UserListener extends BaseModelListener<User> {

	@Override
	public void onAfterAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		try {
			User user = UserLocalServiceUtil.getUser((Long)classPK);

			boolean hasRole = UserLocalServiceUtil.hasRoleUser(
				user.getCompanyId(), "Social Office User", user.getUserId(),
				true);

			if (!hasRole) {
				return;
			}

			Group group = user.getGroup();

			ExpandoBridge expandoBridge = group.getExpandoBridge();

			boolean socialOfficeEnabled = GetterUtil.getBoolean(
				expandoBridge.getAttribute("socialOfficeEnabled"));

			if (socialOfficeEnabled) {
				return;
			}

			LayoutSetPrototypeUtil.updateLayoutSetPrototype(
				group, false,
				SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_USER_PUBLIC);
			LayoutSetPrototypeUtil.updateLayoutSetPrototype(
				group, true,
				SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_USER_PRIVATE);

			SocialOfficeUtil.enableSocialOffice(group);
		}
		catch (NoSuchGroupException nsge) {
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterRemoveAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		try {
			User user = UserLocalServiceUtil.getUser((Long)classPK);

			Group group = user.getGroup();

			ExpandoBridge expandoBridge = group.getExpandoBridge();

			boolean socialOfficeEnabled = GetterUtil.getBoolean(
				expandoBridge.getAttribute("socialOfficeEnabled"));

			if (!socialOfficeEnabled) {
				return;
			}

			LayoutSetPrototypeUtil.removeLayoutSetPrototype(
				group, false,
				SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_USER_PUBLIC);
			LayoutSetPrototypeUtil.removeLayoutSetPrototype(
				group, true,
				SocialOfficeConstants.LAYOUT_SET_PROTOTYPE_KEY_USER_PRIVATE);

			SocialOfficeUtil.disableSocialOffice(group);
		}
		catch (NoSuchGroupException nsge) {
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

}