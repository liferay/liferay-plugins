package com.liferay.portal.service.persistence;

import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class CompanyActionableDynamicQuery
	extends BaseActionableDynamicQuery {

	public CompanyActionableDynamicQuery() throws SystemException {
		setBaseLocalService(CompanyLocalServiceUtil.getService());
		setClass(Company.class);

			setClassLoader(PortalClassLoaderUtil.getClassLoader());

		setPrimaryKeyPropertyName("companyId");
	}

}