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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * Provides the local service interface for OAuthConsumer. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see OAuthConsumerLocalServiceUtil
 * @see com.liferay.opensocial.service.base.OAuthConsumerLocalServiceBaseImpl
 * @see com.liferay.opensocial.service.impl.OAuthConsumerLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface OAuthConsumerLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OAuthConsumerLocalServiceUtil} to access the o auth consumer local service. Add custom service methods to {@link com.liferay.opensocial.service.impl.OAuthConsumerLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public com.liferay.opensocial.model.OAuthConsumer addOAuthConsumer(
		long companyId, java.lang.String gadgetKey,
		java.lang.String serviceName, java.lang.String consumerKey,
		java.lang.String consumerSecret, java.lang.String keyType);

	/**
	* Adds the o auth consumer to the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthConsumer the o auth consumer
	* @return the o auth consumer that was added
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public com.liferay.opensocial.model.OAuthConsumer addOAuthConsumer(
		com.liferay.opensocial.model.OAuthConsumer oAuthConsumer);

	/**
	* Creates a new o auth consumer with the primary key. Does not add the o auth consumer to the database.
	*
	* @param oAuthConsumerId the primary key for the new o auth consumer
	* @return the new o auth consumer
	*/
	public com.liferay.opensocial.model.OAuthConsumer createOAuthConsumer(
		long oAuthConsumerId);

	/**
	* Deletes the o auth consumer from the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthConsumer the o auth consumer
	* @return the o auth consumer that was removed
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	public com.liferay.opensocial.model.OAuthConsumer deleteOAuthConsumer(
		com.liferay.opensocial.model.OAuthConsumer oAuthConsumer);

	/**
	* Deletes the o auth consumer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthConsumerId the primary key of the o auth consumer
	* @return the o auth consumer that was removed
	* @throws PortalException if a o auth consumer with the primary key could not be found
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	public com.liferay.opensocial.model.OAuthConsumer deleteOAuthConsumer(
		long oAuthConsumerId) throws PortalException;

	public void deleteOAuthConsumers(java.lang.String gadgetKey);

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws PortalException;

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.OAuthConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.OAuthConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.opensocial.model.OAuthConsumer fetchOAuthConsumer(
		java.lang.String gadgetKey, java.lang.String serviceName);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.opensocial.model.OAuthConsumer fetchOAuthConsumer(
		long oAuthConsumerId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery();

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.opensocial.model.OAuthConsumer getOAuthConsumer(
		java.lang.String gadgetKey, java.lang.String serviceName)
		throws PortalException;

	/**
	* Returns the o auth consumer with the primary key.
	*
	* @param oAuthConsumerId the primary key of the o auth consumer
	* @return the o auth consumer
	* @throws PortalException if a o auth consumer with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.opensocial.model.OAuthConsumer getOAuthConsumer(
		long oAuthConsumerId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.opensocial.model.OAuthConsumer> getOAuthConsumers(
		java.lang.String gadgetKey);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.opensocial.model.OAuthConsumer> getOAuthConsumers(
		java.lang.String gadgetKey, int start, int end);

	/**
	* Returns a range of all the o auth consumers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.OAuthConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of o auth consumers
	* @param end the upper bound of the range of o auth consumers (not inclusive)
	* @return the range of o auth consumers
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.opensocial.model.OAuthConsumer> getOAuthConsumers(
		int start, int end);

	/**
	* Returns the number of o auth consumers.
	*
	* @return the number of o auth consumers
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOAuthConsumersCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOAuthConsumersCount(java.lang.String gadgetKey);

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj) throws PortalException;

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	/**
	* Updates the o auth consumer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param oAuthConsumer the o auth consumer
	* @return the o auth consumer that was updated
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public com.liferay.opensocial.model.OAuthConsumer updateOAuthConsumer(
		com.liferay.opensocial.model.OAuthConsumer oAuthConsumer);

	public com.liferay.opensocial.model.OAuthConsumer updateOAuthConsumer(
		long oAuthConsumerId, java.lang.String consumerKey,
		java.lang.String consumerSecret, java.lang.String keyType,
		java.lang.String keyName, java.lang.String callbackURL)
		throws PortalException;
}