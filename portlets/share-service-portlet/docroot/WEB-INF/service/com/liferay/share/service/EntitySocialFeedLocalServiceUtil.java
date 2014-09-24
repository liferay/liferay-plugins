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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for EntitySocialFeed. This utility wraps
 * {@link com.liferay.share.service.impl.EntitySocialFeedLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see EntitySocialFeedLocalService
 * @see com.liferay.share.service.base.EntitySocialFeedLocalServiceBaseImpl
 * @see com.liferay.share.service.impl.EntitySocialFeedLocalServiceImpl
 * @generated
 */
@ProviderType
public class EntitySocialFeedLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.share.service.impl.EntitySocialFeedLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the entity social feed to the database. Also notifies the appropriate model listeners.
	*
	* @param entitySocialFeed the entity social feed
	* @return the entity social feed that was added
	*/
	public static com.liferay.share.model.EntitySocialFeed addEntitySocialFeed(
		com.liferay.share.model.EntitySocialFeed entitySocialFeed) {
		return getService().addEntitySocialFeed(entitySocialFeed);
	}

	/**
	* Creates a new entity social feed with the primary key. Does not add the entity social feed to the database.
	*
	* @param entitySocialFeedPK the primary key for the new entity social feed
	* @return the new entity social feed
	*/
	public static com.liferay.share.model.EntitySocialFeed createEntitySocialFeed(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK) {
		return getService().createEntitySocialFeed(entitySocialFeedPK);
	}

	/**
	* Deletes the entity social feed from the database. Also notifies the appropriate model listeners.
	*
	* @param entitySocialFeed the entity social feed
	* @return the entity social feed that was removed
	*/
	public static com.liferay.share.model.EntitySocialFeed deleteEntitySocialFeed(
		com.liferay.share.model.EntitySocialFeed entitySocialFeed) {
		return getService().deleteEntitySocialFeed(entitySocialFeed);
	}

	/**
	* Deletes the entity social feed with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entitySocialFeedPK the primary key of the entity social feed
	* @return the entity social feed that was removed
	* @throws PortalException if a entity social feed with the primary key could not be found
	*/
	public static com.liferay.share.model.EntitySocialFeed deleteEntitySocialFeed(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteEntitySocialFeed(entitySocialFeedPK);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.share.model.impl.EntitySocialFeedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.share.model.impl.EntitySocialFeedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.share.model.EntitySocialFeed fetchEntitySocialFeed(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK) {
		return getService().fetchEntitySocialFeed(entitySocialFeedPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Returns the entity social feed with the primary key.
	*
	* @param entitySocialFeedPK the primary key of the entity social feed
	* @return the entity social feed
	* @throws PortalException if a entity social feed with the primary key could not be found
	*/
	public static com.liferay.share.model.EntitySocialFeed getEntitySocialFeed(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEntitySocialFeed(entitySocialFeedPK);
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
	public static java.util.List<com.liferay.share.model.EntitySocialFeed> getEntitySocialFeeds(
		int start, int end) {
		return getService().getEntitySocialFeeds(start, end);
	}

	/**
	* Returns the number of entity social feeds.
	*
	* @return the number of entity social feeds
	*/
	public static int getEntitySocialFeedsCount() {
		return getService().getEntitySocialFeedsCount();
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
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	/**
	* Updates the entity social feed in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param entitySocialFeed the entity social feed
	* @return the entity social feed that was updated
	*/
	public static com.liferay.share.model.EntitySocialFeed updateEntitySocialFeed(
		com.liferay.share.model.EntitySocialFeed entitySocialFeed) {
		return getService().updateEntitySocialFeed(entitySocialFeed);
	}

	public static void clearService() {
		_service = null;
	}

	public static EntitySocialFeedLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					EntitySocialFeedLocalService.class.getName());

			if (invokableLocalService instanceof EntitySocialFeedLocalService) {
				_service = (EntitySocialFeedLocalService)invokableLocalService;
			}
			else {
				_service = new EntitySocialFeedLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(EntitySocialFeedLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(EntitySocialFeedLocalService service) {
	}

	private static EntitySocialFeedLocalService _service;
}