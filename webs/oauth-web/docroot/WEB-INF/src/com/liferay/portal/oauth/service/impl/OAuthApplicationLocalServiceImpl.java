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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.oauth.model.OAuthApplication;
import com.liferay.portal.oauth.service.base.OAuthApplicationLocalServiceBaseImpl;

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
	 * Add info about new application that should use OAuth feature. Method will generate new
	 * consumer key and secret that will be used by this application to do authorized access
	 * to portal resources.
	 *  
	 */
	public OAuthApplication addOAuthApplication(
			int accessLevel, String callbackURL, String description,
			String name, long ownerId, String website)
		throws SystemException {
		OAuthApplication oaa = createOAuthApplication(
			CounterLocalServiceUtil.increment());

		oaa.setConsumerKey(PortalUUIDUtil.generate());
		oaa.setAccessLevel(accessLevel);
		oaa.setCallBackURL(callbackURL);
		oaa.setName(name);
		oaa.setDescription(description);
		oaa.setOwnerId(ownerId);
		oaa.setWebsite(website);
		
		String secretSeed = oaa.getConsumerKey()
				.concat(Long.toString(System.nanoTime()));
		
		oaa.setConsumerSecret(DigesterUtil.digestHex(secretSeed));
		
		return updateOAuthApplication(oaa, true);
	}
	
	public int countByName(String name)
			throws SystemException {
			
			return oAuthApplicationFinder.countByName(name);
	}
	
	public int countByNameAndOwner(String name, long ownerId)
			throws SystemException {
			
			return oAuthApplicationFinder.countByN_O(name, ownerId);
	}

	public OAuthApplication getOAuthApplicationByConsumerKey(String consumerKey)
		throws SystemException {

		return oAuthApplicationPersistence.fetchByConsumerKey(consumerKey);
	}
	
	public List<OAuthApplication> findByName(String name, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {
			
			return oAuthApplicationFinder.findByName(
					name, start, end, orderByComparator);
		}
	
	public List<OAuthApplication> findByNameAndOwner(String name, long ownerId,
			int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
			
			return oAuthApplicationFinder.findByN_O(
					name, ownerId, start, end, orderByComparator);
		}

}