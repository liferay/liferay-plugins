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

package com.liferay.opensocial.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for OAuthToken. This utility wraps
 * {@link com.liferay.opensocial.service.impl.OAuthTokenLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see OAuthTokenLocalService
 * @see com.liferay.opensocial.service.base.OAuthTokenLocalServiceBaseImpl
 * @see com.liferay.opensocial.service.impl.OAuthTokenLocalServiceImpl
 * @generated
 */
@ProviderType
public class OAuthTokenLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.opensocial.service.impl.OAuthTokenLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the o auth token to the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthToken the o auth token
	* @return the o auth token that was added
	*/
	public static com.liferay.opensocial.model.OAuthToken addOAuthToken(
		com.liferay.opensocial.model.OAuthToken oAuthToken) {
		return getService().addOAuthToken(oAuthToken);
	}

	public static com.liferay.opensocial.model.OAuthToken addOAuthToken(
		long userId, java.lang.String gadgetKey, java.lang.String serviceName,
		long moduleId, java.lang.String accessToken,
		java.lang.String tokenName, java.lang.String tokenSecret,
		java.lang.String sessionHandle, long expiration)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addOAuthToken(userId, gadgetKey, serviceName, moduleId,
			accessToken, tokenName, tokenSecret, sessionHandle, expiration);
	}

	/**
	* Creates a new o auth token with the primary key. Does not add the o auth token to the database.
	*
	* @param oAuthTokenId the primary key for the new o auth token
	* @return the new o auth token
	*/
	public static com.liferay.opensocial.model.OAuthToken createOAuthToken(
		long oAuthTokenId) {
		return getService().createOAuthToken(oAuthTokenId);
	}

	/**
	* Deletes the o auth token from the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthToken the o auth token
	* @return the o auth token that was removed
	*/
	public static com.liferay.opensocial.model.OAuthToken deleteOAuthToken(
		com.liferay.opensocial.model.OAuthToken oAuthToken) {
		return getService().deleteOAuthToken(oAuthToken);
	}

	/**
	* Deletes the o auth token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthTokenId the primary key of the o auth token
	* @return the o auth token that was removed
	* @throws PortalException if a o auth token with the primary key could not be found
	*/
	public static com.liferay.opensocial.model.OAuthToken deleteOAuthToken(
		long oAuthTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteOAuthToken(oAuthTokenId);
	}

	public static void deleteOAuthToken(long userId,
		java.lang.String gadgetKey, java.lang.String serviceName,
		long moduleId, java.lang.String tokenName)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.deleteOAuthToken(userId, gadgetKey, serviceName, moduleId,
			tokenName);
	}

	public static void deleteOAuthTokens(java.lang.String gadgetKey,
		java.lang.String serviceName) {
		getService().deleteOAuthTokens(gadgetKey, serviceName);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.OAuthTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.OAuthTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.opensocial.model.OAuthToken fetchOAuthToken(
		long oAuthTokenId) {
		return getService().fetchOAuthToken(oAuthTokenId);
	}

	public static com.liferay.opensocial.model.OAuthToken fetchOAuthToken(
		long userId, java.lang.String gadgetKey, java.lang.String serviceName,
		long moduleId, java.lang.String tokenName) {
		return getService()
				   .fetchOAuthToken(userId, gadgetKey, serviceName, moduleId,
			tokenName);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the o auth token with the primary key.
	*
	* @param oAuthTokenId the primary key of the o auth token
	* @return the o auth token
	* @throws PortalException if a o auth token with the primary key could not be found
	*/
	public static com.liferay.opensocial.model.OAuthToken getOAuthToken(
		long oAuthTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOAuthToken(oAuthTokenId);
	}

	public static com.liferay.opensocial.model.OAuthToken getOAuthToken(
		long userId, java.lang.String gadgetKey, java.lang.String serviceName,
		long moduleId, java.lang.String tokenName)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getOAuthToken(userId, gadgetKey, serviceName, moduleId,
			tokenName);
	}

	public static java.util.List<com.liferay.opensocial.model.OAuthToken> getOAuthTokens(
		java.lang.String gadgetKey, java.lang.String serviceName) {
		return getService().getOAuthTokens(gadgetKey, serviceName);
	}

	/**
	* Returns a range of all the o auth tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.OAuthTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of o auth tokens
	* @param end the upper bound of the range of o auth tokens (not inclusive)
	* @return the range of o auth tokens
	*/
	public static java.util.List<com.liferay.opensocial.model.OAuthToken> getOAuthTokens(
		int start, int end) {
		return getService().getOAuthTokens(start, end);
	}

	/**
	* Returns the number of o auth tokens.
	*
	* @return the number of o auth tokens
	*/
	public static int getOAuthTokensCount() {
		return getService().getOAuthTokensCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Updates the o auth token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param oAuthToken the o auth token
	* @return the o auth token that was updated
	*/
	public static com.liferay.opensocial.model.OAuthToken updateOAuthToken(
		com.liferay.opensocial.model.OAuthToken oAuthToken) {
		return getService().updateOAuthToken(oAuthToken);
	}

	public static void clearService() {
		_service = null;
	}

	public static OAuthTokenLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					OAuthTokenLocalService.class.getName());

			if (invokableLocalService instanceof OAuthTokenLocalService) {
				_service = (OAuthTokenLocalService)invokableLocalService;
			}
			else {
				_service = new OAuthTokenLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(OAuthTokenLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(OAuthTokenLocalService service) {
	}

	private static OAuthTokenLocalService _service;
}