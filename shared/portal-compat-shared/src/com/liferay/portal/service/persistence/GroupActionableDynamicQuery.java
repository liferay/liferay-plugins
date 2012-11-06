package com.liferay.portal.service.persistence;

import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class GroupActionableDynamicQuery
	extends BaseActionableDynamicQuery {

	public GroupActionableDynamicQuery() throws SystemException {
		setBaseLocalService(GroupLocalServiceUtil.getService());
		setClass(Group.class);

			setClassLoader(PortalClassLoaderUtil.getClassLoader());

		setPrimaryKeyPropertyName("groupId");
	}

}