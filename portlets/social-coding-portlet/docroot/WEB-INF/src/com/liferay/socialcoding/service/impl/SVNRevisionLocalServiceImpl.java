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

package com.liferay.socialcoding.service.impl;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.socialcoding.NoSuchSVNRevisionException;
import com.liferay.socialcoding.model.SVNRevision;
import com.liferay.socialcoding.service.base.SVNRevisionLocalServiceBaseImpl;
import com.liferay.socialcoding.svn.social.SVNActivityKeys;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class SVNRevisionLocalServiceImpl
	extends SVNRevisionLocalServiceBaseImpl {

	public SVNRevision addSVNRevision(
			String svnUserId, Date createDate, long svnRepositoryId,
			long revisionNumber, String comments)
		throws PortalException, SystemException {

		// SVN revision

		long svnRevisionId = counterLocalService.increment();

		SVNRevision svnRevision = svnRevisionPersistence.create(svnRevisionId);

		svnRevision.setSvnUserId(svnUserId);
		svnRevision.setCreateDate(createDate);
		svnRevision.setSvnRepositoryId(svnRepositoryId);
		svnRevision.setRevisionNumber(revisionNumber);
		svnRevision.setComments(comments);

		svnRevisionPersistence.update(svnRevision, false);

		// Social

		try {
			User user = userLocalService.getUserByScreenName(
				PortalUtil.getDefaultCompanyId(), svnUserId);

			long userId = user.getUserId();

			/*List<SocialActivity> socialActivities =
				SocialActivityLocalServiceUtil.getActivities(
					SVNRevision.class.getName(), 0, 1);

			if (socialActivities.size() > 0) {
				SocialActivity socialActivity = socialActivities.get(0);

				if (userId == socialActivity.getUserId()) {
					SocialActivityLocalServiceUtil.deleteActivity(
						socialActivity.getActivityId());
				}
			}*/

			SocialActivityLocalServiceUtil.addActivity(
				userId, 0, createDate, SVNRevision.class.getName(),
				svnRevisionId, SVNActivityKeys.ADD_REVISION, StringPool.BLANK,
				0);
		}
		catch (NoSuchUserException nsue) {
		}

		return svnRevision;
	}

	public SVNRevision getFirstSVNRevision(String svnUserId)
		throws PortalException, SystemException {

		int count = svnRevisionPersistence.countBySVNUserId(svnUserId);

		List<SVNRevision> svnRevisions = svnRevisionPersistence.findBySVNUserId(
			svnUserId, count - 1, count);

		if (svnRevisions.size() > 0) {
			return svnRevisions.get(0);
		}
		else {
			throw new NoSuchSVNRevisionException();
		}
	}

	public SVNRevision getLastSVNRevision(String svnUserId)
		throws PortalException, SystemException {

		List<SVNRevision> svnRevisions = svnRevisionPersistence.findBySVNUserId(
			svnUserId, 0, 1);

		if (svnRevisions.size() > 0) {
			return svnRevisions.get(0);
		}
		else {
			throw new NoSuchSVNRevisionException();
		}
	}

	@Override
	public SVNRevision getSVNRevision(long svnRevisionId)
		throws PortalException, SystemException {

		return svnRevisionPersistence.findByPrimaryKey(svnRevisionId);
	}

	public List<SVNRevision> getSVNRevisions(
			String svnUserId, int start, int end)
		throws SystemException {

		return svnRevisionPersistence.findBySVNUserId(svnUserId, start, end);
	}

	public List<SVNRevision> getSVNRevisions(
			long svnRepositoryId, int start, int end)
		throws SystemException {

		return svnRevisionPersistence.findBySVNRepositoryId(
			svnRepositoryId, start, end);
	}

	public List<SVNRevision> getSVNRevisions(
			String svnUserId, long svnRepositoryId, int start, int end)
		throws SystemException {

		return svnRevisionPersistence.findBySVNU_SVNR(
			svnUserId, svnRepositoryId, start, end);
	}

	public int getSVNRevisionsCount(String svnUserId) throws SystemException {
		return svnRevisionPersistence.countBySVNUserId(svnUserId);
	}

	public int getSVNRevisionsCount(long svnRepositoryId)
		throws SystemException {

		return svnRevisionPersistence.countBySVNRepositoryId(svnRepositoryId);
	}

	public int getSVNRevisionsCount(String svnUserId, long svnRepositoryId)
		throws SystemException {

		return svnRevisionPersistence.countBySVNU_SVNR(
			svnUserId, svnRepositoryId);
	}

}