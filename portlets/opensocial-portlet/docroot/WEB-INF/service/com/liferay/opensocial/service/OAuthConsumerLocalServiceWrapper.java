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

package com.liferay.opensocial.service;

/**
 * <p>
 * This class is a wrapper for {@link OAuthConsumerLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OAuthConsumerLocalService
 * @generated
 */
public class OAuthConsumerLocalServiceWrapper
	implements OAuthConsumerLocalService {
	public OAuthConsumerLocalServiceWrapper(
		OAuthConsumerLocalService oAuthConsumerLocalService) {
		_oAuthConsumerLocalService = oAuthConsumerLocalService;
	}

	/**
	* Adds the o auth consumer to the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthConsumer the o auth consumer to add
	* @return the o auth consumer that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.OAuthConsumer addOAuthConsumer(
		com.liferay.opensocial.model.OAuthConsumer oAuthConsumer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthConsumerLocalService.addOAuthConsumer(oAuthConsumer);
	}

	/**
	* Creates a new o auth consumer with the primary key. Does not add the o auth consumer to the database.
	*
	* @param oAuthConsumerId the primary key for the new o auth consumer
	* @return the new o auth consumer
	*/
	public com.liferay.opensocial.model.OAuthConsumer createOAuthConsumer(
		long oAuthConsumerId) {
		return _oAuthConsumerLocalService.createOAuthConsumer(oAuthConsumerId);
	}

	/**
	* Deletes the o auth consumer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthConsumerId the primary key of the o auth consumer to delete
	* @throws PortalException if a o auth consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteOAuthConsumer(long oAuthConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_oAuthConsumerLocalService.deleteOAuthConsumer(oAuthConsumerId);
	}

	/**
	* Deletes the o auth consumer from the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthConsumer the o auth consumer to delete
	* @throws SystemException if a system exception occurred
	*/
	public void deleteOAuthConsumer(
		com.liferay.opensocial.model.OAuthConsumer oAuthConsumer)
		throws com.liferay.portal.kernel.exception.SystemException {
		_oAuthConsumerLocalService.deleteOAuthConsumer(oAuthConsumer);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthConsumerLocalService.dynamicQuery(dynamicQuery);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthConsumerLocalService.dynamicQuery(dynamicQuery, start, end);
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
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthConsumerLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthConsumerLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the o auth consumer with the primary key.
	*
	* @param oAuthConsumerId the primary key of the o auth consumer to get
	* @return the o auth consumer
	* @throws PortalException if a o auth consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.OAuthConsumer getOAuthConsumer(
		long oAuthConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _oAuthConsumerLocalService.getOAuthConsumer(oAuthConsumerId);
	}

	/**
	* Gets a range of all the o auth consumers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of o auth consumers to return
	* @param end the upper bound of the range of o auth consumers to return (not inclusive)
	* @return the range of o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.opensocial.model.OAuthConsumer> getOAuthConsumers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthConsumerLocalService.getOAuthConsumers(start, end);
	}

	/**
	* Gets the number of o auth consumers.
	*
	* @return the number of o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public int getOAuthConsumersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthConsumerLocalService.getOAuthConsumersCount();
	}

	/**
	* Updates the o auth consumer in the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthConsumer the o auth consumer to update
	* @return the o auth consumer that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.OAuthConsumer updateOAuthConsumer(
		com.liferay.opensocial.model.OAuthConsumer oAuthConsumer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthConsumerLocalService.updateOAuthConsumer(oAuthConsumer);
	}

	/**
	* Updates the o auth consumer in the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthConsumer the o auth consumer to update
	* @param merge whether to merge the o auth consumer with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the o auth consumer that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.OAuthConsumer updateOAuthConsumer(
		com.liferay.opensocial.model.OAuthConsumer oAuthConsumer, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthConsumerLocalService.updateOAuthConsumer(oAuthConsumer,
			merge);
	}

	public com.liferay.opensocial.model.OAuthConsumer addOAuthConsumer(
		long companyId, long gadgetId, java.lang.String serviceName,
		java.lang.String consumerKey, java.lang.String consumerSecret,
		java.lang.String keyType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthConsumerLocalService.addOAuthConsumer(companyId, gadgetId,
			serviceName, consumerKey, consumerSecret, keyType);
	}

	public void deleteOAuthConsumers(long gadgetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_oAuthConsumerLocalService.deleteOAuthConsumers(gadgetId);
	}

	public com.liferay.opensocial.model.OAuthConsumer getOAuthConsumer(
		long gadgetId, java.lang.String serviceName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _oAuthConsumerLocalService.getOAuthConsumer(gadgetId, serviceName);
	}

	public java.util.List<com.liferay.opensocial.model.OAuthConsumer> getOAuthConsumers(
		long gadgetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthConsumerLocalService.getOAuthConsumers(gadgetId);
	}

	public java.util.List<com.liferay.opensocial.model.OAuthConsumer> getOAuthConsumers(
		long gadgetId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthConsumerLocalService.getOAuthConsumers(gadgetId, start, end);
	}

	public int getOAuthConsumersCount(long gadgetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthConsumerLocalService.getOAuthConsumersCount(gadgetId);
	}

	public com.liferay.opensocial.model.OAuthConsumer updateOAuthConsumer(
		long oAuthConsumerId, java.lang.String consumerKey,
		java.lang.String consumerSecret, java.lang.String keyType,
		java.lang.String keyName, java.lang.String callbackURL)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _oAuthConsumerLocalService.updateOAuthConsumer(oAuthConsumerId,
			consumerKey, consumerSecret, keyType, keyName, callbackURL);
	}

	public OAuthConsumerLocalService getWrappedOAuthConsumerLocalService() {
		return _oAuthConsumerLocalService;
	}

	public void setWrappedOAuthConsumerLocalService(
		OAuthConsumerLocalService oAuthConsumerLocalService) {
		_oAuthConsumerLocalService = oAuthConsumerLocalService;
	}

	private OAuthConsumerLocalService _oAuthConsumerLocalService;
}