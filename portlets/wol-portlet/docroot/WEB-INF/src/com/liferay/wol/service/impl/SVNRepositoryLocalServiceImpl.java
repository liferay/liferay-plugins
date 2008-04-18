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

package com.liferay.wol.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.wol.model.SVNRepository;
import com.liferay.wol.service.base.SVNRepositoryLocalServiceBaseImpl;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
 * <a href="SVNRepositoryLocalServiceImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SVNRepositoryLocalServiceImpl
	extends SVNRepositoryLocalServiceBaseImpl {

	public SVNRepository getSVNRepository(String url)
		throws PortalException, SystemException {

		return svnRepositoryPersistence.findByUrl(url);
	}

	public void updateSVNRepository(String url)
		throws PortalException, SystemException {

		SVNRepository svnRepository = svnRepositoryPersistence.fetchByUrl(url);

		if (svnRepository == null) {
			long svnRepositoryId = CounterLocalServiceUtil.increment();

			svnRepository = svnRepositoryPersistence.create(svnRepositoryId);

			svnRepository.setUrl(url);

			svnRepositoryPersistence.update(svnRepository, false);
		}

		org.tmatesoft.svn.core.io.SVNRepository repository = null;

		try {
			DAVRepositoryFactory.setup();

			repository = SVNRepositoryFactory.create(
				SVNURL.parseURIEncoded(url));

			ISVNAuthenticationManager authenticationManager =
				SVNWCUtil.createDefaultAuthenticationManager();

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

	private static Log _log =
		LogFactory.getLog(SVNRepositoryLocalServiceImpl.class);

}