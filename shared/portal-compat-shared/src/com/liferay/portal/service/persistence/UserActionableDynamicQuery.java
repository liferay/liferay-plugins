package com.liferay.portal.service.persistence;

import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class UserActionableDynamicQuery
	extends BaseActionableDynamicQuery {

	public UserActionableDynamicQuery() throws SystemException {
		setBaseLocalService(UserLocalServiceUtil.getService());
		setClass(User.class);

			setClassLoader(PortalClassLoaderUtil.getClassLoader());

		setPrimaryKeyPropertyName("userId");
	}

}