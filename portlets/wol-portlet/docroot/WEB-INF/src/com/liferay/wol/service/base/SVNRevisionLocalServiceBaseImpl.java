/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.wol.service.base;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.DynamicQueryInitializer;

import com.liferay.wol.model.SVNRevision;
import com.liferay.wol.service.JIRAIssueLocalService;
import com.liferay.wol.service.JIRAIssueLocalServiceFactory;
import com.liferay.wol.service.SVNRepositoryLocalService;
import com.liferay.wol.service.SVNRepositoryLocalServiceFactory;
import com.liferay.wol.service.SVNRevisionLocalService;
import com.liferay.wol.service.persistence.JIRAIssuePersistence;
import com.liferay.wol.service.persistence.JIRAIssueUtil;
import com.liferay.wol.service.persistence.SVNRepositoryPersistence;
import com.liferay.wol.service.persistence.SVNRepositoryUtil;
import com.liferay.wol.service.persistence.SVNRevisionPersistence;
import com.liferay.wol.service.persistence.SVNRevisionUtil;

import org.springframework.beans.factory.InitializingBean;

import java.util.List;

/**
 * <a href="SVNRevisionLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class SVNRevisionLocalServiceBaseImpl
	implements SVNRevisionLocalService, InitializingBean {
	public SVNRevision addSVNRevision(SVNRevision svnRevision)
		throws SystemException {
		svnRevision.setNew(true);

		return svnRevisionPersistence.update(svnRevision, false);
	}

	public void deleteSVNRevision(long svnRevisionId)
		throws PortalException, SystemException {
		svnRevisionPersistence.remove(svnRevisionId);
	}

	public void deleteSVNRevision(SVNRevision svnRevision)
		throws PortalException, SystemException {
		svnRevisionPersistence.remove(svnRevision);
	}

	public List<SVNRevision> dynamicQuery(
		DynamicQueryInitializer queryInitializer) throws SystemException {
		return svnRevisionPersistence.findWithDynamicQuery(queryInitializer);
	}

	public List<SVNRevision> dynamicQuery(
		DynamicQueryInitializer queryInitializer, int begin, int end)
		throws SystemException {
		return svnRevisionPersistence.findWithDynamicQuery(queryInitializer,
			begin, end);
	}

	public SVNRevision updateSVNRevision(SVNRevision svnRevision)
		throws SystemException {
		svnRevision.setNew(false);

		return svnRevisionPersistence.update(svnRevision, true);
	}

	public JIRAIssueLocalService getJIRAIssueLocalService() {
		return jiraIssueLocalService;
	}

	public void setJIRAIssueLocalService(
		JIRAIssueLocalService jiraIssueLocalService) {
		this.jiraIssueLocalService = jiraIssueLocalService;
	}

	public JIRAIssuePersistence getJIRAIssuePersistence() {
		return jiraIssuePersistence;
	}

	public void setJIRAIssuePersistence(
		JIRAIssuePersistence jiraIssuePersistence) {
		this.jiraIssuePersistence = jiraIssuePersistence;
	}

	public SVNRepositoryLocalService getSVNRepositoryLocalService() {
		return svnRepositoryLocalService;
	}

	public void setSVNRepositoryLocalService(
		SVNRepositoryLocalService svnRepositoryLocalService) {
		this.svnRepositoryLocalService = svnRepositoryLocalService;
	}

	public SVNRepositoryPersistence getSVNRepositoryPersistence() {
		return svnRepositoryPersistence;
	}

	public void setSVNRepositoryPersistence(
		SVNRepositoryPersistence svnRepositoryPersistence) {
		this.svnRepositoryPersistence = svnRepositoryPersistence;
	}

	public SVNRevisionPersistence getSVNRevisionPersistence() {
		return svnRevisionPersistence;
	}

	public void setSVNRevisionPersistence(
		SVNRevisionPersistence svnRevisionPersistence) {
		this.svnRevisionPersistence = svnRevisionPersistence;
	}

	public void afterPropertiesSet() {
		if (jiraIssueLocalService == null) {
			jiraIssueLocalService = JIRAIssueLocalServiceFactory.getImpl();
		}

		if (jiraIssuePersistence == null) {
			jiraIssuePersistence = JIRAIssueUtil.getPersistence();
		}

		if (svnRepositoryLocalService == null) {
			svnRepositoryLocalService = SVNRepositoryLocalServiceFactory.getImpl();
		}

		if (svnRepositoryPersistence == null) {
			svnRepositoryPersistence = SVNRepositoryUtil.getPersistence();
		}

		if (svnRevisionPersistence == null) {
			svnRevisionPersistence = SVNRevisionUtil.getPersistence();
		}
	}

	protected JIRAIssueLocalService jiraIssueLocalService;
	protected JIRAIssuePersistence jiraIssuePersistence;
	protected SVNRepositoryLocalService svnRepositoryLocalService;
	protected SVNRepositoryPersistence svnRepositoryPersistence;
	protected SVNRevisionPersistence svnRevisionPersistence;
}