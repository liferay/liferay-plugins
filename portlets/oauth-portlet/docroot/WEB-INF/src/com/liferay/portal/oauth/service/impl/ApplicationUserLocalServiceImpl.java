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

import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.oauth.model.ApplicationUser;
import com.liferay.portal.oauth.service.base.ApplicationUserLocalServiceBaseImpl;
import com.liferay.portal.service.ServiceContext;

/**
 * The implementation of the application user local service. <p> All custom
 * service methods should be put in this class. Whenever methods are added,
 * rerun ServiceBuilder to copy their definitions into the
 * {@link com.liferay.portal.oauth.service.ApplicationUserLocalService}
 * interface. <p> This is a local service. Methods of this service will not have
 * security checks based on the propagated JAAS credentials because this service
 * can only be accessed from within the same VM. </p>
 * 
 * @author Igor Beslic
 * @author Raymond Augé
 * @see com.liferay.portal.oauth.service.base.ApplicationUserLocalServiceBaseImpl
 * @see com.liferay.portal.oauth.service.ApplicationUserLocalServiceUtil
 */
public class ApplicationUserLocalServiceImpl
	extends ApplicationUserLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use
	 * {@link com.liferay.portal.oauth.service.ApplicationUserLocalServiceUtil}
	 * to access the application user local service.
	 */

	/**
	 * Add new user's authorization for an existing application that is
	 * registered to use OAuth feature. All optional fields will be set to null
	 * or initial value (depending on data type). Method creates necessary
	 * resources used later by permissions algorithm.
	 * 
	 * @param authorized
	 * @param applicationId
	 * @param userId
	 * @param accessSecret
	 * @param accessToken
	 * @param serviceContext
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	public ApplicationUser addApplicationUser(
		long userId, long applicationId, String accessToken,
		String accessSecret, boolean authorized, ServiceContext serviceContext)
		throws PortalException, SystemException {

		long oaauId = counterLocalService.increment();

		ApplicationUser applicationUser =
			applicationUserPersistence.create(oaauId);

		applicationUser.setUserId(userId);
		applicationUser.setApplicationId(applicationId);
		applicationUser.setAccessToken(accessToken);
		applicationUser.setAccessSecret(accessSecret);
		applicationUser.setAuthorized(authorized);

		applicationUser =
			applicationUserPersistence.update(applicationUser, false);

		resourceLocalService.addResources(
			serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
			serviceContext.getUserId(), ApplicationUser.class.getName(),
			applicationUser.getPrimaryKey(), false, false, false);

		return applicationUser;
	}

	public ApplicationUser deleteApplicationUser(
		long userId, long applicationId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		ApplicationUser applicationUser =
			applicationUserPersistence.findByU_AP(userId, applicationId);

		return deleteApplicationUser(applicationUser, serviceContext);
	}

	public ApplicationUser deleteApplicationUser(
		ApplicationUser applicationUser, ServiceContext serviceContext)
		throws PortalException, SystemException {

		resourceLocalService.deleteResource(
			serviceContext.getCompanyId(), ApplicationUser.class.getName(),
			ResourceConstants.SCOPE_COMPANY, applicationUser.getPrimaryKey());

		applicationUser = applicationUserPersistence.remove(applicationUser);

		return applicationUser;
	}
	
	/**
	 * Return ApplicationUser with accessToken given by parameter.
	 * @param accessToken
	 * @return
	 * @throws SystemException
	 */
	public ApplicationUser getApplicationUserByAccessToken(String accessToken)
		throws SystemException {
		
		if (null == accessToken) {
			throw new SystemException("AccessToken can not be null.");
		}
		
		/*
		 * accessToken value if defined it is unique across applicationUser
		 * entity. 
		 * */
		List<ApplicationUser> applicationUserLst =
					applicationUserPersistence.findByAccessToken(accessToken);
		
		if (applicationUserLst.size() > 1) {
			throw new SystemException(
				"No unique ApplicationUser.");	
		}
		else if (applicationUserLst.size() == 1) {
			return applicationUserLst.get(0);
		}
		
		throw new SystemException("No such ApplicationUser.");
	}

	public List<ApplicationUser> getApplicationUsers(long applicationId)
		throws SystemException {

		return applicationUserPersistence.findByApplicationId(applicationId);
	}

	public List<ApplicationUser> getApplicationUsers(
		long applicationId, int start, int end,
		OrderByComparator orderByComparator)
		throws SystemException {

		return applicationUserPersistence.findByApplicationId(
			applicationId, start, end, orderByComparator);
	}

	public List<ApplicationUser> getApplicationUsersByUserId(long userId)
		throws SystemException {

		return applicationUserPersistence.findByUserId(userId);
	}

	public List<ApplicationUser> getApplicationUsersByUserId(
		long userId, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {

		return applicationUserPersistence.findByUserId(
			userId, start, end, orderByComparator);
	}

	public int getApplicationUsersByUserIdCount(long userId)
		throws SystemException {

		return applicationUserPersistence.countByUserId(userId);
	}

	public int getApplicationUsersCount(long applicationId)
		throws SystemException {

		return applicationUserPersistence.countByApplicationId(applicationId);
	}
	
	public List<ApplicationUser> getAuthorizedApplicationUsersByOwnerId(
			long ownerId, boolean authorized, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {
		
		return applicationUserFinder.findByO_A(
			ownerId, authorized, start, end, orderByComparator);
	}
	
	public int getAuthorizedApplicationUsersByOwnerIdCount(
			long ownerId, boolean authorized)
		throws SystemException {
	
		return applicationUserFinder.countByO_A(ownerId, authorized);
	}

	public List<ApplicationUser> getAuthorizedApplicationUsersByUserId(
			long userId, boolean authorized)
		throws SystemException {

		return applicationUserPersistence.findByU_AU(userId, authorized);
	}

	public List<ApplicationUser> getAuthorizedApplicationUsersByUserId(
		long userId, boolean authorized, int start, int end,
		OrderByComparator orderByComparator)
		throws SystemException {

		return applicationUserPersistence.findByU_AU(
			userId, authorized, start, end, orderByComparator);
	}

	public int getAuthorizedApplicationUsersByUserIdCount(
		long userId, boolean authorized)
		throws SystemException {

		return applicationUserPersistence.countByU_AU(userId, authorized);
	}

	/**
	 * Update user's authorization for an existing application that is
	 * registered to use OAuth feature. If entity doesn't exist new one (with
	 * resources for later permissions check) will be created.
	 * 
	 * @param authorized
	 *            if set to false application access rights are revoked
	 * @param oAuthApplicationId
	 * @param userId
	 * @param accessSecret
	 * @param accessToken
	 * @param serviceContext
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	public ApplicationUser updateApplicationUser(
		long userId, long applicationId, String accessToken,
		String accessSecret, ServiceContext serviceContext)
		throws PortalException, SystemException {

		ApplicationUser applicationUser =
			applicationUserPersistence.fetchByU_AP(userId, applicationId);

		applicationUser.setAccessToken(accessToken);
		applicationUser.setAccessSecret(accessSecret);

		applicationUserPersistence.update(applicationUser, false);

		return applicationUser;
	}
	
	public ApplicationUser updateAuthorized(
		long userId, long applicationId, boolean authorized,
		ServiceContext serviceContext)
		throws PortalException, SystemException {

		ApplicationUser applicationUser =
			applicationUserPersistence.fetchByU_AP(userId, applicationId);

		applicationUser.setAuthorized(authorized);

		applicationUserPersistence.update(applicationUser, false);

		return applicationUser;
	}

}
