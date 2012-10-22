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

package com.liferay.akismet.service.impl;

import com.liferay.akismet.model.AkismetData;
import com.liferay.akismet.service.base.AkismetDataLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.Date;

/**
 * @author Amos Fong
 */
public class AkismetDataLocalServiceImpl
	extends AkismetDataLocalServiceBaseImpl {

	public void deleteAkismetData(Date modifiedDate) throws SystemException {
		akismetDataPersistence.removeByLtModifiedDate(modifiedDate);
	}

	public void deleteMBMessageAkismetData(long mbMessageId)
		throws PortalException, SystemException {

		akismetDataPersistence.removeByMBMessageId(mbMessageId);
	}

	public AkismetData fetchMBMessageAkismetData(long mbMessageId)
		throws SystemException {

		return akismetDataPersistence.fetchByMBMessageId(mbMessageId);
	}

	public AkismetData updateAkismetData(
			long mbMessageId, String type, String permalink, String referrer,
			String userAgent, String userIP, String userURL)
		throws SystemException {

		AkismetData akismetData = akismetDataPersistence.fetchByMBMessageId(
			mbMessageId);

		if (akismetData == null) {
			long akismetDataId = counterLocalService.increment();

			akismetData = akismetDataPersistence.create(akismetDataId);
		}

		akismetData.setModifiedDate(new Date());
		akismetData.setMbMessageId(mbMessageId);
		akismetData.setType(type);
		akismetData.setPermalink(permalink);
		akismetData.setReferrer(referrer);
		akismetData.setUserAgent(userAgent);
		akismetData.setUserIP(userIP);
		akismetData.setUserURL(userURL);

		akismetDataPersistence.update(akismetData);

		return akismetData;
	}

}