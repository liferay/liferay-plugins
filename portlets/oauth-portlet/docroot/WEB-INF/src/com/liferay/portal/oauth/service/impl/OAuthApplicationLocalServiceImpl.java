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
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.oauth.model.OAuthApplication;
import com.liferay.portal.oauth.service.base.OAuthApplicationLocalServiceBaseImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.oauth.OAuthConstants;

import java.net.MalformedURLException;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the o auth application local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.portal.oauth.service.OAuthApplicationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Igor Beslic
 * @see com.liferay.portal.oauth.service.base.OAuthApplicationLocalServiceBaseImpl
 * @see com.liferay.portal.oauth.service.OAuthApplicationLocalServiceUtil
 */
public class OAuthApplicationLocalServiceImpl
	extends OAuthApplicationLocalServiceBaseImpl {
	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.portal.oauth.service.OAuthApplicationLocalServiceUtil} to access the o auth application local service.
	 */

	/**
	 * Add info about new application that should use OAuth feature. Method will
	 * generate new consumer key and secret that will be used by this
	 * application to do authorized access to portal resources.
	 */
	public OAuthApplication addApplication(
			long userId, String name, String description, String website,
			String callBackURL, int accessLevel, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Application

		User user = userPersistence.findByPrimaryKey(userId);

		validate(name, website, callBackURL);

		Date now = new Date();

		long applicationId = counterLocalService.increment();

		OAuthApplication application = oAuthApplicationPersistence.create(
			applicationId);

		application.setCompanyId(user.getCompanyId());
		application.setUserId(user.getUserId());
		application.setUserName(user.getFullName());
		application.setCreateDate(serviceContext.getCreateDate(now));
		application.setModifiedDate(serviceContext.getModifiedDate(now));
		application.setOwnerId(user.getUserId());
		application.setName(name);
		application.setDescription(description);
		application.setWebsite(website);
		application.setConsumerKey(PortalUUIDUtil.generate());

		String secretSeed = application.getConsumerKey().concat(
			String.valueOf(System.nanoTime()));

		application.setConsumerSecret(DigesterUtil.digestHex(secretSeed));
		application.setCallBackURL(callBackURL);
		application.setAccessLevel(accessLevel);

		oAuthApplicationPersistence.update(application, true);

		// Resources

		resourceLocalService.addModelResources(application, serviceContext);

		return application;
	}

	public OAuthApplication getApplication(String consumerKey)
		throws SystemException {

		return oAuthApplicationPersistence.fetchByConsumerKey(consumerKey);
	}

	public List<OAuthApplication> getApplications(long companyId)
		throws SystemException {

		return oAuthApplicationPersistence.findByCompanyId(companyId);
	}

	public List<OAuthApplication> getApplications(
			long companyId, String name, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return oAuthApplicationPersistence.findByC_N(
			companyId, name, start, end, orderByComparator);
	}

	public List<OAuthApplication> getApplicationsByCN(
			long companyId, String name)
		throws SystemException {

		return oAuthApplicationPersistence.findByC_N(companyId, name);
	}

	public List<OAuthApplication> getApplicationsByON(
			long ownerId, String name)
		throws SystemException {

		return oAuthApplicationPersistence.findByO_N(ownerId, name);
	}

	public List<OAuthApplication> getApplicationsByON(
			long ownerId, String name, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return oAuthApplicationPersistence.findByO_N(
			ownerId, name, start, end, orderByComparator);
	}

	public int getApplicationsByCNCount(long companyId, String name)
		throws SystemException {

		return oAuthApplicationPersistence.countByC_N(companyId, name);
	}

	public int getApplicationsByONCount(long ownerId, String name)
		throws SystemException {

		return oAuthApplicationPersistence.countByO_N(ownerId, name);
	}

	public int getApplicationsCount(long companyId) throws SystemException {
		return oAuthApplicationPersistence.countByCompanyId(companyId);
	}

	private void validate(String name, String website, String callBackURL)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new RequiredFieldException(
				"required-field", OAuthConstants.WEB_APP_NAME_ID);
		}

		if (Validator.isNull(callBackURL)) {
			throw new RequiredFieldException(
				"required-field", OAuthConstants.WEB_APP_CALLBACKURL_ID);
		}

		if (!Validator.isUrl(callBackURL)) {
			throw new PortalException(new MalformedURLException(callBackURL));
		}

		if (Validator.isNull(website)) {
			throw new RequiredFieldException(
				"required-field", OAuthConstants.WEB_APP_WEBSITE_ID);
		}

		if (!Validator.isUrl(website)) {
			throw new PortalException(new MalformedURLException(website));
		}
	}

}