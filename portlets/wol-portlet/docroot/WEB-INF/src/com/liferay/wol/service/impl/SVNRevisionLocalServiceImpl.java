/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.wol.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.wol.NoSuchSVNRevisionException;
import com.liferay.wol.model.SVNRevision;
import com.liferay.wol.service.base.SVNRevisionLocalServiceBaseImpl;
import com.liferay.wol.svn.social.SVNActivityKeys;

import java.util.Date;
import java.util.List;

/**
 * <a href="SVNRevisionLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SVNRevisionLocalServiceImpl
	extends SVNRevisionLocalServiceBaseImpl {

	public SVNRevision addSVNRevision(
			String svnUserId, Date createDate, long svnRepositoryId,
			long revisionNumber, String comments)
		throws PortalException, SystemException {

		// SVN revision

		long svnRevisionId = CounterLocalServiceUtil.increment();

		SVNRevision svnRevision = svnRevisionPersistence.create(svnRevisionId);

		svnRevision.setSvnUserId(svnUserId);
		svnRevision.setCreateDate(createDate);
		svnRevision.setSvnRepositoryId(svnRepositoryId);
		svnRevision.setRevisionNumber(revisionNumber);
		svnRevision.setComments(comments);

		svnRevisionPersistence.update(svnRevision, false);

		// Social

		try {
			User user = UserLocalServiceUtil.getUserByScreenName(
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