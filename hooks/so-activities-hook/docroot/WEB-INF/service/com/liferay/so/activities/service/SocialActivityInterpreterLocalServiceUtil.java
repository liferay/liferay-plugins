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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the social activity interpreter local service. This utility wraps {@link com.liferay.so.activities.service.impl.SocialActivityInterpreterLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivityInterpreterLocalService
 * @see com.liferay.so.activities.service.base.SocialActivityInterpreterLocalServiceBaseImpl
 * @see com.liferay.so.activities.service.impl.SocialActivityInterpreterLocalServiceImpl
 * @generated
 */
public class SocialActivityInterpreterLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.so.activities.service.impl.SocialActivityInterpreterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Adds the activity interpreter to the list of available interpreters.
	*
	* @param activityInterpreter the activity interpreter
	*/
	public static void addActivityInterpreter(
		com.liferay.so.activities.model.SocialActivityInterpreter activityInterpreter) {
		getService().addActivityInterpreter(activityInterpreter);
	}

	/**
	* Removes the activity interpreter from the list of available interpreters.
	*
	* @param activityInterpreter the activity interpreter
	*/
	public static void deleteActivityInterpreter(
		com.liferay.so.activities.model.SocialActivityInterpreter activityInterpreter) {
		getService().deleteActivityInterpreter(activityInterpreter);
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
	public static com.liferay.portlet.social.model.SocialActivityFeedEntry interpret(
		java.lang.String selector,
		com.liferay.so.activities.model.SocialActivity activity,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().interpret(selector, activity, serviceContext);
	}

	public static com.liferay.portlet.social.model.SocialActivityFeedEntry interpret(
		java.lang.String selector,
		com.liferay.so.activities.model.SocialActivitySet activitySet,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService().interpret(selector, activitySet, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static SocialActivityInterpreterLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SocialActivityInterpreterLocalService.class.getName());

			if (invokableLocalService instanceof SocialActivityInterpreterLocalService) {
				_service = (SocialActivityInterpreterLocalService)invokableLocalService;
			}
			else {
				_service = new SocialActivityInterpreterLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SocialActivityInterpreterLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(SocialActivityInterpreterLocalService service) {
	}

	private static SocialActivityInterpreterLocalService _service;
}