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

package com.liferay.share.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EntitySocialFeedLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see EntitySocialFeedLocalService
 * @generated
 */
@ProviderType
public class EntitySocialFeedLocalServiceWrapper
	implements EntitySocialFeedLocalService,
		ServiceWrapper<EntitySocialFeedLocalService> {
	public EntitySocialFeedLocalServiceWrapper(
		EntitySocialFeedLocalService entitySocialFeedLocalService) {
		_entitySocialFeedLocalService = entitySocialFeedLocalService;
	}

	/**
	* Adds the entity social feed to the database. Also notifies the appropriate model listeners.
	*
	* @param entitySocialFeed the entity social feed
	* @return the entity social feed that was added
	*/
	@Override
	public com.liferay.share.model.EntitySocialFeed addEntitySocialFeed(
		com.liferay.share.model.EntitySocialFeed entitySocialFeed) {
		return _entitySocialFeedLocalService.addEntitySocialFeed(entitySocialFeed);
	}

	/**
	* Creates a new entity social feed with the primary key. Does not add the entity social feed to the database.
	*
	* @param entitySocialFeedPK the primary key for the new entity social feed
	* @return the new entity social feed
	*/
	@Override
	public com.liferay.share.model.EntitySocialFeed createEntitySocialFeed(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK) {
		return _entitySocialFeedLocalService.createEntitySocialFeed(entitySocialFeedPK);
	}

	/**
	* Deletes the entity social feed from the database. Also notifies the appropriate model listeners.
	*
	* @param entitySocialFeed the entity social feed
	* @return the entity social feed that was removed
	*/
	@Override
	public com.liferay.share.model.EntitySocialFeed deleteEntitySocialFeed(
		com.liferay.share.model.EntitySocialFeed entitySocialFeed) {
		return _entitySocialFeedLocalService.deleteEntitySocialFeed(entitySocialFeed);
	}

	/**
	* Deletes the entity social feed with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entitySocialFeedPK the primary key of the entity social feed
	* @return the entity social feed that was removed
	* @throws PortalException if a entity social feed with the primary key could not be found
	*/
	@Override
	public com.liferay.share.model.EntitySocialFeed deleteEntitySocialFeed(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entitySocialFeedLocalService.deleteEntitySocialFeed(entitySocialFeedPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entitySocialFeedLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _entitySocialFeedLocalService.dynamicQuery();
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
		return _entitySocialFeedLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.share.model.impl.EntitySocialFeedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _entitySocialFeedLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.share.model.impl.EntitySocialFeedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _entitySocialFeedLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _entitySocialFeedLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _entitySocialFeedLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.share.model.EntitySocialFeed fetchEntitySocialFeed(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK) {
		return _entitySocialFeedLocalService.fetchEntitySocialFeed(entitySocialFeedPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _entitySocialFeedLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _entitySocialFeedLocalService.getBeanIdentifier();
	}

	/**
	* Returns the entity social feed with the primary key.
	*
	* @param entitySocialFeedPK the primary key of the entity social feed
	* @return the entity social feed
	* @throws PortalException if a entity social feed with the primary key could not be found
	*/
	@Override
	public com.liferay.share.model.EntitySocialFeed getEntitySocialFeed(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entitySocialFeedLocalService.getEntitySocialFeed(entitySocialFeedPK);
	}

	/**
	* Returns a range of all the entity social feeds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.share.model.impl.EntitySocialFeedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entity social feeds
	* @param end the upper bound of the range of entity social feeds (not inclusive)
	* @return the range of entity social feeds
	*/
	@Override
	public java.util.List<com.liferay.share.model.EntitySocialFeed> getEntitySocialFeeds(
		int start, int end) {
		return _entitySocialFeedLocalService.getEntitySocialFeeds(start, end);
	}

	/**
	* Returns the number of entity social feeds.
	*
	* @return the number of entity social feeds
	*/
	@Override
	public int getEntitySocialFeedsCount() {
		return _entitySocialFeedLocalService.getEntitySocialFeedsCount();
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entitySocialFeedLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _entitySocialFeedLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_entitySocialFeedLocalService.setBeanIdentifier(beanIdentifier);
	}

	/**
	* Updates the entity social feed in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param entitySocialFeed the entity social feed
	* @return the entity social feed that was updated
	*/
	@Override
	public com.liferay.share.model.EntitySocialFeed updateEntitySocialFeed(
		com.liferay.share.model.EntitySocialFeed entitySocialFeed) {
		return _entitySocialFeedLocalService.updateEntitySocialFeed(entitySocialFeed);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public EntitySocialFeedLocalService getWrappedEntitySocialFeedLocalService() {
		return _entitySocialFeedLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedEntitySocialFeedLocalService(
		EntitySocialFeedLocalService entitySocialFeedLocalService) {
		_entitySocialFeedLocalService = entitySocialFeedLocalService;
	}

	@Override
	public EntitySocialFeedLocalService getWrappedService() {
		return _entitySocialFeedLocalService;
	}

	@Override
	public void setWrappedService(
		EntitySocialFeedLocalService entitySocialFeedLocalService) {
		_entitySocialFeedLocalService = entitySocialFeedLocalService;
	}

	private EntitySocialFeedLocalService _entitySocialFeedLocalService;
}