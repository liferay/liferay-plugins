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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link OAuthTokenLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see OAuthTokenLocalService
 * @generated
 */
@ProviderType
public class OAuthTokenLocalServiceWrapper implements OAuthTokenLocalService,
	ServiceWrapper<OAuthTokenLocalService> {
	public OAuthTokenLocalServiceWrapper(
		OAuthTokenLocalService oAuthTokenLocalService) {
		_oAuthTokenLocalService = oAuthTokenLocalService;
	}

	/**
	* Adds the o auth token to the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthToken the o auth token
	* @return the o auth token that was added
	*/
	@Override
	public com.liferay.opensocial.model.OAuthToken addOAuthToken(
		com.liferay.opensocial.model.OAuthToken oAuthToken) {
		return _oAuthTokenLocalService.addOAuthToken(oAuthToken);
	}

	@Override
	public com.liferay.opensocial.model.OAuthToken addOAuthToken(long userId,
		java.lang.String gadgetKey, java.lang.String serviceName,
		long moduleId, java.lang.String accessToken,
		java.lang.String tokenName, java.lang.String tokenSecret,
		java.lang.String sessionHandle, long expiration)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _oAuthTokenLocalService.addOAuthToken(userId, gadgetKey,
			serviceName, moduleId, accessToken, tokenName, tokenSecret,
			sessionHandle, expiration);
	}

	/**
	* Creates a new o auth token with the primary key. Does not add the o auth token to the database.
	*
	* @param oAuthTokenId the primary key for the new o auth token
	* @return the new o auth token
	*/
	@Override
	public com.liferay.opensocial.model.OAuthToken createOAuthToken(
		long oAuthTokenId) {
		return _oAuthTokenLocalService.createOAuthToken(oAuthTokenId);
	}

	/**
	* Deletes the o auth token from the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthToken the o auth token
	* @return the o auth token that was removed
	*/
	@Override
	public com.liferay.opensocial.model.OAuthToken deleteOAuthToken(
		com.liferay.opensocial.model.OAuthToken oAuthToken) {
		return _oAuthTokenLocalService.deleteOAuthToken(oAuthToken);
	}

	/**
	* Deletes the o auth token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthTokenId the primary key of the o auth token
	* @return the o auth token that was removed
	* @throws PortalException if a o auth token with the primary key could not be found
	*/
	@Override
	public com.liferay.opensocial.model.OAuthToken deleteOAuthToken(
		long oAuthTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _oAuthTokenLocalService.deleteOAuthToken(oAuthTokenId);
	}

	@Override
	public void deleteOAuthToken(long userId, java.lang.String gadgetKey,
		java.lang.String serviceName, long moduleId, java.lang.String tokenName)
		throws com.liferay.portal.kernel.exception.PortalException {
		_oAuthTokenLocalService.deleteOAuthToken(userId, gadgetKey,
			serviceName, moduleId, tokenName);
	}

	@Override
	public void deleteOAuthTokens(java.lang.String gadgetKey,
		java.lang.String serviceName) {
		_oAuthTokenLocalService.deleteOAuthTokens(gadgetKey, serviceName);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _oAuthTokenLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _oAuthTokenLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _oAuthTokenLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _oAuthTokenLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _oAuthTokenLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _oAuthTokenLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _oAuthTokenLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.opensocial.model.OAuthToken fetchOAuthToken(
		long oAuthTokenId) {
		return _oAuthTokenLocalService.fetchOAuthToken(oAuthTokenId);
	}

	@Override
	public com.liferay.opensocial.model.OAuthToken fetchOAuthToken(
		long userId, java.lang.String gadgetKey, java.lang.String serviceName,
		long moduleId, java.lang.String tokenName) {
		return _oAuthTokenLocalService.fetchOAuthToken(userId, gadgetKey,
			serviceName, moduleId, tokenName);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _oAuthTokenLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _oAuthTokenLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the o auth token with the primary key.
	*
	* @param oAuthTokenId the primary key of the o auth token
	* @return the o auth token
	* @throws PortalException if a o auth token with the primary key could not be found
	*/
	@Override
	public com.liferay.opensocial.model.OAuthToken getOAuthToken(
		long oAuthTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _oAuthTokenLocalService.getOAuthToken(oAuthTokenId);
	}

	@Override
	public com.liferay.opensocial.model.OAuthToken getOAuthToken(long userId,
		java.lang.String gadgetKey, java.lang.String serviceName,
		long moduleId, java.lang.String tokenName)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _oAuthTokenLocalService.getOAuthToken(userId, gadgetKey,
			serviceName, moduleId, tokenName);
	}

	@Override
	public java.util.List<com.liferay.opensocial.model.OAuthToken> getOAuthTokens(
		java.lang.String gadgetKey, java.lang.String serviceName) {
		return _oAuthTokenLocalService.getOAuthTokens(gadgetKey, serviceName);
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
	@Override
	public java.util.List<com.liferay.opensocial.model.OAuthToken> getOAuthTokens(
		int start, int end) {
		return _oAuthTokenLocalService.getOAuthTokens(start, end);
	}

	/**
	* Returns the number of o auth tokens.
	*
	* @return the number of o auth tokens
	*/
	@Override
	public int getOAuthTokensCount() {
		return _oAuthTokenLocalService.getOAuthTokensCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _oAuthTokenLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _oAuthTokenLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _oAuthTokenLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Updates the o auth token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param oAuthToken the o auth token
	* @return the o auth token that was updated
	*/
	@Override
	public com.liferay.opensocial.model.OAuthToken updateOAuthToken(
		com.liferay.opensocial.model.OAuthToken oAuthToken) {
		return _oAuthTokenLocalService.updateOAuthToken(oAuthToken);
	}

	@Override
	public OAuthTokenLocalService getWrappedService() {
		return _oAuthTokenLocalService;
	}

	@Override
	public void setWrappedService(OAuthTokenLocalService oAuthTokenLocalService) {
		_oAuthTokenLocalService = oAuthTokenLocalService;
	}

	private OAuthTokenLocalService _oAuthTokenLocalService;
}