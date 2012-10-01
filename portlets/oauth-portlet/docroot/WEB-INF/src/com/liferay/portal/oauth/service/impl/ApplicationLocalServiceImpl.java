/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.oauth.service.impl;

import com.liferay.portal.RequiredFieldException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.oauth.NoSuchApplicationException;
import com.liferay.portal.oauth.OAuthUtil;
import com.liferay.portal.oauth.model.Application;
import com.liferay.portal.oauth.service.base.ApplicationLocalServiceBaseImpl;
import com.liferay.portal.oauth.util.OAuthConstants;
import com.liferay.portal.service.ServiceContext;

import java.net.MalformedURLException;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the application local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.portal.oauth.service.ApplicationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Igor Beslic
 * @author Raymond Augé
 *
 * @see com.liferay.portal.oauth.service.base.ApplicationLocalServiceBaseImpl
 * @see com.liferay.portal.oauth.service.ApplicationLocalServiceUtil
 */
public class ApplicationLocalServiceImpl
	extends ApplicationLocalServiceBaseImpl {
	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.portal.oauth.service.ApplicationLocalServiceUtil} to access the application local service.
	 */

	/**
	 * Add info about new application that should use OAuth feature. Method will
	 * generate new consumer key and secret that will be used by this
	 * application to do authorized access to portal resources.
	 */
	public Application addApplication(
			long userId, String name, String description, String website,
			String callBackURL, int accessLevel, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Application

		User user = userPersistence.findByPrimaryKey(userId);

		validate(name, website, callBackURL);

		Date now = new Date();

		// Application

		long applicationId = counterLocalService.increment();

		Application application = applicationPersistence.create(applicationId);

		application.setCompanyId(user.getCompanyId());
		application.setUserId(user.getUserId());
		application.setUserName(user.getFullName());
		application.setCreateDate(serviceContext.getCreateDate(now));
		application.setModifiedDate(serviceContext.getModifiedDate(now));
		application.setName(name);
		application.setDescription(description);
		application.setWebsite(website);
		application.setCallBackURL(callBackURL);
		application.setAccessLevel(accessLevel);

		// This is to support potential import

		String consumerKey = serviceContext.getUuid();

		if (Validator.isNull(consumerKey)) {
			consumerKey = PortalUUIDUtil.generate();
		}

		String consumerSecret = OAuthUtil.randomizeToken(consumerKey);

		application.setConsumerKey(consumerKey);
		application.setConsumerSecret(consumerSecret);

		applicationPersistence.update(application, false);

		// Resources

		resourceLocalService.addResources(
			application.getCompanyId(), 0, userId, Application.class.getName(),
			application.getApplicationId(), false, false, false);

		return application;
	}

	public Application deleteApplication(
			Application application, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Applications

		applicationUserPersistence.removeByApplicationId(
			application.getApplicationId());

		// Resources

		resourceLocalService.deleteResource(
			application.getCompanyId(), Application.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, application.getApplicationId());

		// Application

			applicationPersistence.remove(application);

			return application;
	}

	/**
	 * Delete OAuth application designated by applicationId. Method will
	 * delete all application user's authorizations, application and
	 * corresponding resource entries.
	 *
	 * @param applicationId
	 * @param serviceContext
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	public Application deleteApplication(
			long applicationId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		Application application = applicationPersistence.findByPrimaryKey(
				applicationId);

		return deleteApplication(application, serviceContext);
	}

	public Application fetchApplication(String consumerKey)
		throws SystemException {

		return applicationPersistence.fetchByConsumerKey(consumerKey);
	}

	public Application getApplication(String consumerKey)
		throws PortalException, SystemException {

		try {
			return applicationPersistence.findByConsumerKey(consumerKey);
		}
		catch (NoSuchApplicationException e) {
			throw new PortalException(e);
		}
	}

	public List<Application> getApplications(long companyId)
		throws SystemException {

		return applicationPersistence.findByCompanyId(companyId);
	}

	public List<Application> getApplications(
			long companyId, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return applicationPersistence.findByCompanyId(
					companyId, start, end, orderByComparator);
	}

	public List<Application> getApplications(
			long companyId, String name, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {
		if (null == name) {
			name = "%";
		}

		return applicationPersistence.findByC_N(
			companyId, name, start, end, orderByComparator);
	}

	public List<Application> getApplicationsByCN(long companyId, String name)
		throws SystemException {

		return applicationPersistence.findByC_N(companyId, name);
	}

	public List<Application> getApplicationsByON(long userId, String name)
		throws SystemException {
		if (null == name) {
			name = "%";
		}

		return applicationPersistence.findByU_N(userId, name);
	}

	public List<Application> getApplicationsByON(
			long userId, String name, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {
		if (null == name) {
			name = "%";
		}

		return applicationPersistence.findByU_N(
			userId, name, start, end, orderByComparator);
	}

	public List<Application> getApplicationsByOwner(
			long userId, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return applicationPersistence.findByUserId(
			userId, start, end, orderByComparator);
	}

	public int getApplicationsByUserIdCount(long userId)
		throws SystemException {

		return applicationPersistence.countByUserId(userId);
	}

	public int getApplicationsCount(long companyId) throws SystemException {
		return applicationPersistence.countByCompanyId(companyId);
	}

	public int getApplicationsCountByCN(long companyId, String name)
		throws SystemException {
		if (null == name) {
			name = "%";
		}

		return applicationPersistence.countByC_N(companyId, name);
	}

	public int getApplicationsCountByON(long userId, String name)
		throws SystemException {
		if (null == name) {
			name = "%";
		}

		return applicationPersistence.countByU_N(userId, name);
	}

	/**
	 * Update existing application that should use OAuth feature. If changed
	 * method will update name, description, website, callbackURL and
	 * access level.
	 */
	public Application updateApplication(
			long userId, long applicationId, String name, String description,
			String website, String callBackURL, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Application

		User user = userPersistence.findByPrimaryKey(userId);

		validate(name, website, callBackURL);

		Date now = new Date();

		Application application = applicationPersistence.findByPrimaryKey(
				applicationId);

		application.setUserId(user.getUserId());
		application.setUserName(user.getFullName());
		application.setModifiedDate(serviceContext.getModifiedDate(now));
		application.setName(name);
		application.setDescription(description);
		application.setWebsite(website);
		application.setCallBackURL(callBackURL);

		applicationPersistence.update(application, true);

		return application;
	}

	private void validate(String name, String website, String callBackURL)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new RequiredFieldException(
				"required-field", OAuthConstants.NAME);
		}

		if (Validator.isNull(callBackURL)) {
			throw new RequiredFieldException(
				"required-field", OAuthConstants.CALLBACK_URL);
		}

		if (!Validator.isUrl(callBackURL)) {
			throw new PortalException(new MalformedURLException(callBackURL));
		}

		if (Validator.isNull(website)) {
			throw new RequiredFieldException(
				"required-field", OAuthConstants.WEBSITE);
		}

		if (!Validator.isUrl(website)) {
			throw new PortalException(new MalformedURLException(website));
		}
	}

}