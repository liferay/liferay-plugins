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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.socialcoding.model.SVNRepository;
import com.liferay.socialcoding.service.base.SVNRepositoryLocalServiceBaseImpl;
import com.liferay.socialcoding.svn.util.SVNConstants;

import java.util.Collection;
import java.util.Iterator;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class SVNRepositoryLocalServiceImpl
	extends SVNRepositoryLocalServiceBaseImpl {

	@Override
	public SVNRepository getSVNRepository(long svnRepositoryId)
		throws PortalException, SystemException {

		return svnRepositoryPersistence.findByPrimaryKey(svnRepositoryId);
	}

	public SVNRepository getSVNRepository(String url)
		throws PortalException, SystemException {

		return svnRepositoryPersistence.findByUrl(url);
	}

	public void updateSVNRepository(String url)
		throws PortalException, SystemException {

		SVNRepository svnRepository = svnRepositoryPersistence.fetchByUrl(url);

		if (svnRepository == null) {
			long svnRepositoryId = counterLocalService.increment();

			svnRepository = svnRepositoryPersistence.create(svnRepositoryId);

			svnRepository.setUrl(url);

			svnRepositoryPersistence.update(svnRepository, false);
		}

		org.tmatesoft.svn.core.io.SVNRepository repository = null;

		try {
			SVNRepositoryFactoryImpl.setup();

			repository = SVNRepositoryFactory.create(
				SVNURL.parseURIEncoded(url));

			ISVNAuthenticationManager authenticationManager =
				SVNWCUtil.createDefaultAuthenticationManager(
					SVNConstants.SVN_AUTH_USERNAME,
					SVNConstants.SVN_AUTH_PASSWORD);

			repository.setAuthenticationManager(authenticationManager);

			if (svnRepository.getRevisionNumber() ==
					repository.getLatestRevision()) {

				return;
			}

			long startRevision = svnRepository.getRevisionNumber() + 1;
			long endRevision = -1;

			Collection<SVNLogEntry> svnLogEntries = repository.log(
				null, null, startRevision, endRevision, false, true);

			Iterator<SVNLogEntry> itr = svnLogEntries.iterator();

			while (itr.hasNext()) {
				SVNLogEntry svnLogEntry = itr.next();

				svnRevisionLocalService.addSVNRevision(
					svnLogEntry.getAuthor(), svnLogEntry.getDate(),
					svnRepository.getSvnRepositoryId(),
					svnLogEntry.getRevision(), svnLogEntry.getMessage());

				if (!itr.hasNext()) {
					svnRepository.setRevisionNumber(svnLogEntry.getRevision());

					svnRepositoryPersistence.update(svnRepository, false);
				}
			}
		}
		catch (SVNException svne) {
			_log.error(svne.getMessage());
		}
		finally {
			repository.closeSession();
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		SVNRepositoryLocalServiceImpl.class);

}