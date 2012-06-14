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

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.oauth.model.OAuthApplications_Users;
import com.liferay.portal.oauth.service.base.OAuthApplications_UsersLocalServiceBaseImpl;
import com.liferay.portal.service.ServiceContext;

/**
 * The implementation of the o auth applications_ users local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.portal.oauth.service.OAuthApplications_UsersLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.oauth.service.base.OAuthApplications_UsersLocalServiceBaseImpl
 * @see com.liferay.portal.oauth.service.OAuthApplications_UsersLocalServiceUtil
 */
public class OAuthApplications_UsersLocalServiceImpl
	extends OAuthApplications_UsersLocalServiceBaseImpl {
	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.portal.oauth.service.OAuthApplications_UsersLocalServiceUtil} to access the o auth applications_ users local service.
	 */
	
	/**
	 * Add new user's authorization for an existing application that is
	 * registered to use OAuth feature. All optional fields will be set to
	 * null or initial value (depending on data type). Method creates necessary
	 * resources used later by permissions algorithm.
	 * 
	 * @param authorized
	 * @param oAuthApplicationId
	 * @param userId
	 * @param accessSecret
	 * @param accessToken
	 * @param serviceContext
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	public OAuthApplications_Users addOAuthApplications_Users(
			boolean authorized, long oAuthApplicationId, long userId,
			String accessSecret, String accessToken,
			ServiceContext serviceContext)
		throws PortalException, SystemException{
		OAuthApplications_Users oAuthApplications_users =
				createOAuthApplications_Users(
						CounterLocalServiceUtil.increment());

		oAuthApplications_users.setAuthorized(authorized);
		oAuthApplications_users.setApplicationId(oAuthApplicationId);
		oAuthApplications_users.setUserId(userId);
		oAuthApplications_users.setAccessSecret(accessSecret);
		oAuthApplications_users.setAccessToken(accessToken);

		oAuthApplications_users = updateOAuthApplications_Users(
			oAuthApplications_users, true);
		
		resourceLocalService.addModelResources(serviceContext.getCompanyId(),
			serviceContext.getScopeGroupId(), serviceContext.getUserId(),
			OAuthApplications_Users.class.getName(),
			oAuthApplications_users.getPrimaryKey(),
			new String [0],new String [0]);

		return oAuthApplications_users;
	}
	
	public int countByApplicationId(long applicationId)
		throws SystemException {
		return oAuthApplications_UsersPersistence
				.countByApplicationId(applicationId);
	}
	
	public int countByUser(long userId) throws SystemException {
		return oAuthApplications_UsersPersistence.countByUserId(userId);
	}
	
	public int countByOwner(long ownerId, boolean authorized)
		throws SystemException {
		return oAuthApplications_UsersFinder.countByO_A(ownerId, authorized);
	}
	
	public OAuthApplications_Users deleteOAuthApplications_Users(
			long oAuthApplicationId, long userId,
			ServiceContext serviceContext)
		throws PortalException, SystemException{
		OAuthApplications_Users oAuthApplications_users =
			oAuthApplications_UsersPersistence
				.findByA_U(oAuthApplicationId, userId);
		
		deleteOAuthApplications_Users(oAuthApplications_users);
		
		resourceLocalService.deleteResource(serviceContext.getCompanyId(),
				OAuthApplications_Users.class.getName(),
				ResourceConstants.SCOPE_COMPANY,
				oAuthApplications_users.getPrimaryKey());
		
		return oAuthApplications_users;
	}
	
	public List<OAuthApplications_Users> findByApplicationId(
			long applicationId)
		throws SystemException {
			return oAuthApplications_UsersPersistence
					.findByApplicationId(applicationId);
	}
	
	public List<OAuthApplications_Users> findByUser(long userId)
		throws SystemException {
		return oAuthApplications_UsersPersistence.findByUserId(userId);
	}
	
	public List<OAuthApplications_Users> findByUser(
			long userId, int start, int end)
		throws SystemException {
		return oAuthApplications_UsersPersistence
				.findByUserId(userId, start,end);
	}
	
	public List<OAuthApplications_Users> findByUser(
			long userId, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {
		return oAuthApplications_UsersPersistence
				.findByUserId(userId, start,end,orderByComparator);
	}
	
	public List<OAuthApplications_Users> findByOwner(
			long ownerId, boolean authorized, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {
		return oAuthApplications_UsersFinder
				.findByO_A(
						ownerId, authorized, start, end, orderByComparator);
	}

	public OAuthApplications_Users updateOAuthApplications_Users(
			long oAuthApplicationId, long userId, boolean authorized)
		throws SystemException{

		OAuthApplications_Users oAuthApplications_users =
			oAuthApplications_UsersPersistence.fetchByA_U(
				oAuthApplicationId, userId);

		if (oAuthApplications_users == null) {
			oAuthApplications_users = createOAuthApplications_Users(
					CounterLocalServiceUtil.increment());
			oAuthApplications_users.setApplicationId(oAuthApplicationId);
			oAuthApplications_users.setUserId(userId);
		}

		oAuthApplications_users.setAuthorized(authorized);

		oAuthApplications_users = updateOAuthApplications_Users(
			oAuthApplications_users, true);

		return oAuthApplications_users;
	}

	public OAuthApplications_Users updateOAuthApplications_Users(
			long oAuthApplicationId, long userId, String accessToken,
			String accessSecret)
		throws SystemException{

		OAuthApplications_Users oAuthApplications_users =
			oAuthApplications_UsersPersistence.fetchByA_U(
				oAuthApplicationId, userId);

		if (oAuthApplications_users == null) {
			oAuthApplications_users = createOAuthApplications_Users(
					CounterLocalServiceUtil.increment());
			oAuthApplications_users.setApplicationId(oAuthApplicationId);
			oAuthApplications_users.setUserId(userId);
			oAuthApplications_users.setAccessToken(accessToken);
			oAuthApplications_users.setAccessSecret(accessSecret);
		}

		oAuthApplications_users.setAccessToken(accessToken);
		oAuthApplications_users.setAccessSecret(accessSecret);

		oAuthApplications_users = updateOAuthApplications_Users(
			oAuthApplications_users, true);

		return oAuthApplications_users;
	}
	
	/**
	 * Update user's authorization for an existing application that is
	 * registered to use OAuth feature. If entity doesn't exist new one (with
	 * resources for later permissions check) will be created. When updating
	 * existing authorization, method prevents overwriting non null
	 * accessSecret and accessToken fields.
	 * 
	 * @param authorized if set to false application access rights are revoked
	 * @param oAuthApplicationId
	 * @param userId
	 * @param accessSecret
	 * @param accessToken
	 * @param serviceContext
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	public OAuthApplications_Users updateOAuthApplications_Users(
			boolean authorized, long oAuthApplicationId, long userId,
			String accessSecret, String accessToken,
			ServiceContext serviceContext)
		throws PortalException, SystemException{

		OAuthApplications_Users oAuthApplications_users =
			oAuthApplications_UsersPersistence.fetchByA_U(
				oAuthApplicationId, userId);

		if (oAuthApplications_users == null) {
			oAuthApplications_users =
				addOAuthApplications_Users(
					authorized, oAuthApplicationId, userId, accessSecret,
					accessToken, serviceContext);
		}
		else {
			oAuthApplications_users.setAuthorized(authorized);
			
			/* update secret/token only if it is null
			 * (update is not supported by OAuth Specification
			 */
			if (null == oAuthApplications_users.getAccessSecret()) {
				oAuthApplications_users.setAccessSecret(accessSecret);
			}
			
			if (null == oAuthApplications_users.getAccessToken()) {
				oAuthApplications_users.setAccessToken(accessToken);	
			}
			
			oAuthApplications_users = updateOAuthApplications_Users(
					oAuthApplications_users, true);
		}

		return oAuthApplications_users;
	}

}