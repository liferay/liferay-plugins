/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.saml.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the s p session local service. This utility wraps {@link com.liferay.portal.saml.service.impl.SPSessionLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SPSessionLocalService
 * @see com.liferay.portal.saml.service.base.SPSessionLocalServiceBaseImpl
 * @see com.liferay.portal.saml.service.impl.SPSessionLocalServiceImpl
 * @generated
 */
public class SPSessionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.saml.service.impl.SPSessionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the s p session to the database. Also notifies the appropriate model listeners.
	*
	* @param spSession the s p session to add
	* @return the s p session that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.saml.model.SPSession addSPSession(
		com.liferay.portal.saml.model.SPSession spSession)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addSPSession(spSession);
	}

	/**
	* Creates a new s p session with the primary key. Does not add the s p session to the database.
	*
	* @param spSessionId the primary key for the new s p session
	* @return the new s p session
	*/
	public static com.liferay.portal.saml.model.SPSession createSPSession(
		long spSessionId) {
		return getService().createSPSession(spSessionId);
	}

	/**
	* Deletes the s p session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param spSessionId the primary key of the s p session to delete
	* @throws PortalException if a s p session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSPSession(long spSessionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteSPSession(spSessionId);
	}

	/**
	* Deletes the s p session from the database. Also notifies the appropriate model listeners.
	*
	* @param spSession the s p session to delete
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSPSession(
		com.liferay.portal.saml.model.SPSession spSession)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteSPSession(spSession);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the s p session with the primary key.
	*
	* @param spSessionId the primary key of the s p session to get
	* @return the s p session
	* @throws PortalException if a s p session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.saml.model.SPSession getSPSession(
		long spSessionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSPSession(spSessionId);
	}

	/**
	* Gets a range of all the s p sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of s p sessions to return
	* @param end the upper bound of the range of s p sessions to return (not inclusive)
	* @return the range of s p sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.saml.model.SPSession> getSPSessions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSPSessions(start, end);
	}

	/**
	* Gets the number of s p sessions.
	*
	* @return the number of s p sessions
	* @throws SystemException if a system exception occurred
	*/
	public static int getSPSessionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSPSessionsCount();
	}

	/**
	* Updates the s p session in the database. Also notifies the appropriate model listeners.
	*
	* @param spSession the s p session to update
	* @return the s p session that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.saml.model.SPSession updateSPSession(
		com.liferay.portal.saml.model.SPSession spSession)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSPSession(spSession);
	}

	/**
	* Updates the s p session in the database. Also notifies the appropriate model listeners.
	*
	* @param spSession the s p session to update
	* @param merge whether to merge the s p session with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the s p session that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.saml.model.SPSession updateSPSession(
		com.liferay.portal.saml.model.SPSession spSession, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSPSession(spSession, merge);
	}

	public static com.liferay.portal.saml.model.SPSession addSPSession(
		long ssoSessionId, java.lang.String issuer,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addSPSession(ssoSessionId, issuer, serviceContext);
	}

	public static java.util.List<com.liferay.portal.saml.model.SPSession> findBySSOSessionId(
		long ssoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().findBySSOSessionId(ssoSessionId);
	}

	public static com.liferay.portal.saml.model.SPSession findByS_I(
		long sessionId, java.lang.String issuer)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().findByS_I(sessionId, issuer);
	}

	public static void clearService() {
		_service = null;
	}

	public static SPSessionLocalService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					SPSessionLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					SPSessionLocalService.class.getName(), portletClassLoader);

			_service = new SPSessionLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(SPSessionLocalServiceUtil.class,
				"_service");
			MethodCache.remove(SPSessionLocalService.class);
		}

		return _service;
	}

	public void setService(SPSessionLocalService service) {
		MethodCache.remove(SPSessionLocalService.class);

		_service = service;

		ReferenceRegistry.registerReference(SPSessionLocalServiceUtil.class,
			"_service");
		MethodCache.remove(SPSessionLocalService.class);
	}

	private static SPSessionLocalService _service;
}