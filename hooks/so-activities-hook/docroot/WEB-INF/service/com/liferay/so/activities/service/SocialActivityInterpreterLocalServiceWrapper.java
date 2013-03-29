/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.so.activities.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link SocialActivityInterpreterLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SocialActivityInterpreterLocalService
 * @generated
 */
public class SocialActivityInterpreterLocalServiceWrapper
	implements SocialActivityInterpreterLocalService,
		ServiceWrapper<SocialActivityInterpreterLocalService> {
	public SocialActivityInterpreterLocalServiceWrapper(
		SocialActivityInterpreterLocalService socialActivityInterpreterLocalService) {
		_socialActivityInterpreterLocalService = socialActivityInterpreterLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _socialActivityInterpreterLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_socialActivityInterpreterLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _socialActivityInterpreterLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	* Adds the activity interpreter to the list of available interpreters.
	*
	* @param activityInterpreter the activity interpreter
	*/
	public void addActivityInterpreter(
		com.liferay.so.activities.model.SocialActivityInterpreter activityInterpreter) {
		_socialActivityInterpreterLocalService.addActivityInterpreter(activityInterpreter);
	}

	/**
	* Removes the activity interpreter from the list of available interpreters.
	*
	* @param activityInterpreter the activity interpreter
	*/
	public void deleteActivityInterpreter(
		com.liferay.so.activities.model.SocialActivityInterpreter activityInterpreter) {
		_socialActivityInterpreterLocalService.deleteActivityInterpreter(activityInterpreter);
	}

	/**
	* Creates a human readable activity feed entry for the activity using an
	* available compatible activity interpreter.
	*
	* <p>
	* This method finds the appropriate interpreter for the activity by going
	* through the available interpreters and asking them if they can handle the
	* asset type of the activity.
	* </p>
	*
	* @param activity the activity to be translated to human readable form
	* @return the activity feed that is a human readable form of the activity
	record or <code>null</code> if a compatible interpreter is not
	found
	*/
	public com.liferay.portlet.social.model.SocialActivityFeedEntry interpret(
		java.lang.String selector,
		com.liferay.so.activities.model.SocialActivity activity,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _socialActivityInterpreterLocalService.interpret(selector,
			activity, serviceContext);
	}

	public com.liferay.portlet.social.model.SocialActivityFeedEntry interpret(
		java.lang.String selector,
		com.liferay.so.activities.model.SocialActivitySet activitySet,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _socialActivityInterpreterLocalService.interpret(selector,
			activitySet, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SocialActivityInterpreterLocalService getWrappedSocialActivityInterpreterLocalService() {
		return _socialActivityInterpreterLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSocialActivityInterpreterLocalService(
		SocialActivityInterpreterLocalService socialActivityInterpreterLocalService) {
		_socialActivityInterpreterLocalService = socialActivityInterpreterLocalService;
	}

	public SocialActivityInterpreterLocalService getWrappedService() {
		return _socialActivityInterpreterLocalService;
	}

	public void setWrappedService(
		SocialActivityInterpreterLocalService socialActivityInterpreterLocalService) {
		_socialActivityInterpreterLocalService = socialActivityInterpreterLocalService;
	}

	private SocialActivityInterpreterLocalService _socialActivityInterpreterLocalService;
}