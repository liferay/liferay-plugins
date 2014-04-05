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

package com.liferay.opensocial.service.impl;

import com.liferay.opensocial.model.OAuthConsumer;
import com.liferay.opensocial.model.OAuthConsumerConstants;
import com.liferay.opensocial.service.base.OAuthConsumerLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;

/**
 * @author Dennis Ju
 */
public class OAuthConsumerLocalServiceImpl
	extends OAuthConsumerLocalServiceBaseImpl {

	public OAuthConsumer addOAuthConsumer(
			long companyId, String gadgetKey, String serviceName,
			String consumerKey, String consumerSecret, String keyType)
		throws SystemException {

		if (keyType.equals(OAuthConsumerConstants.KEY_TYPE_RSA_PRIVATE)) {
			consumerSecret = StringPool.BLANK;
		}

		Date now = new Date();

		long oAuthConsumerId = counterLocalService.increment();

		OAuthConsumer oAuthConsumer = oAuthConsumerPersistence.create(
			oAuthConsumerId);

		oAuthConsumer.setCompanyId(companyId);
		oAuthConsumer.setCreateDate(now);
		oAuthConsumer.setModifiedDate(now);
		oAuthConsumer.setGadgetKey(gadgetKey);
		oAuthConsumer.setServiceName(serviceName);
		oAuthConsumer.setConsumerKey(consumerKey);
		oAuthConsumer.setConsumerSecret(consumerSecret);
		oAuthConsumer.setKeyType(keyType);

		oAuthConsumerPersistence.update(oAuthConsumer);

		return oAuthConsumer;
	}

	@Override
	public OAuthConsumer deleteOAuthConsumer(long oAuthConsumerId)
		throws PortalException, SystemException {

		OAuthConsumer oAuthConsumer = oAuthConsumerPersistence.findByPrimaryKey(
			oAuthConsumerId);

		return deleteOAuthConsumer(oAuthConsumer);
	}

	@Override
	public OAuthConsumer deleteOAuthConsumer(OAuthConsumer oAuthConsumer)
		throws SystemException {

		// OAuth consumer

		oAuthConsumerPersistence.remove(oAuthConsumer);

		// OAuth tokens

		oAuthTokenLocalService.deleteOAuthTokens(
			oAuthConsumer.getGadgetKey(), oAuthConsumer.getServiceName());

		return oAuthConsumer;
	}

	public void deleteOAuthConsumers(String gadgetKey) throws SystemException {
		List<OAuthConsumer> oAuthConsumers =
			oAuthConsumerPersistence.findByGadgetKey(gadgetKey);

		for (OAuthConsumer oAuthConsumer : oAuthConsumers) {
			deleteOAuthConsumer(oAuthConsumer);
		}
	}

	public OAuthConsumer fetchOAuthConsumer(
			String gadgetKey, String serviceName)
		throws SystemException {

		return oAuthConsumerPersistence.fetchByG_S(gadgetKey, serviceName);
	}

	public OAuthConsumer getOAuthConsumer(String gadgetKey, String serviceName)
		throws PortalException, SystemException {

		return oAuthConsumerPersistence.findByG_S(gadgetKey, serviceName);
	}

	public List<OAuthConsumer> getOAuthConsumers(String gadgetKey)
		throws SystemException {

		return oAuthConsumerPersistence.findByGadgetKey(gadgetKey);
	}

	public List<OAuthConsumer> getOAuthConsumers(
			String gadgetKey, int start, int end)
		throws SystemException {

		return oAuthConsumerPersistence.findByGadgetKey(gadgetKey, start, end);
	}

	public int getOAuthConsumersCount(String gadgetKey) throws SystemException {
		return oAuthConsumerPersistence.countByGadgetKey(gadgetKey);
	}

	public OAuthConsumer updateOAuthConsumer(
			long oAuthConsumerId, String consumerKey, String consumerSecret,
			String keyType, String keyName, String callbackURL)
		throws PortalException, SystemException {

		if (keyType.equals(OAuthConsumerConstants.KEY_TYPE_RSA_PRIVATE)) {
			consumerSecret = StringPool.BLANK;
		}

		OAuthConsumer oAuthConsumer = oAuthConsumerPersistence.findByPrimaryKey(
			oAuthConsumerId);

		oAuthConsumer.setConsumerKey(consumerKey);
		oAuthConsumer.setConsumerSecret(consumerSecret);
		oAuthConsumer.setKeyType(keyType);

		oAuthConsumerPersistence.update(oAuthConsumer);

		return oAuthConsumer;
	}

}