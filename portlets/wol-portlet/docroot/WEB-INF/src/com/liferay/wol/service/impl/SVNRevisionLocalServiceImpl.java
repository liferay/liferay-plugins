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
import com.liferay.wol.model.SVNRevision;
import com.liferay.wol.service.base.SVNRevisionLocalServiceBaseImpl;

import java.util.Date;

/**
 * <a href="SVNRevisionLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SVNRevisionLocalServiceImpl
	extends SVNRevisionLocalServiceBaseImpl {

	public SVNRevision addSvnRevision(
			long svnRepositoryId, long revisionNumber, Date date, String author,
			String comments)
		throws PortalException, SystemException {

		long svnRevisionId = CounterLocalServiceUtil.increment();

		SVNRevision svnRevision = svnRevisionPersistence.create(svnRevisionId);

		svnRevision.setSvnRepositoryId(svnRepositoryId);
		svnRevision.setRevisionNumber(revisionNumber);
		svnRevision.setDate(date);
		svnRevision.setAuthor(author);
		svnRevision.setComments(comments);

		svnRevisionPersistence.update(svnRevision, false);

		return svnRevision;
	}

}