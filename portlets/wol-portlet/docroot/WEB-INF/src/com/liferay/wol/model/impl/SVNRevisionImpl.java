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

package com.liferay.wol.model.impl;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.wol.model.JIRAIssue;
import com.liferay.wol.model.SVNRepository;
import com.liferay.wol.model.SVNRevision;
import com.liferay.wol.service.JIRAIssueLocalServiceUtil;
import com.liferay.wol.service.SVNRepositoryLocalServiceUtil;
import com.liferay.wol.svn.util.SVNConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="SVNRevisionImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SVNRevisionImpl
	extends SVNRevisionModelImpl implements SVNRevision {

	public SVNRevisionImpl() {
	}

	public SVNRepository getSVNRepository() {
		SVNRepository svnRepository = null;

		try {
			svnRepository = SVNRepositoryLocalServiceUtil.getSVNRepository(
				getSvnRepositoryId());
		}
		catch (Exception e) {
			svnRepository = new SVNRepositoryImpl();

			_log.error(e);
		}

		return svnRepository;
	}

	public String getWebRevisionNumberURL() {
		return SVNConstants.WEB_REVISION_NUMBER_URL + getRevisionNumber();
	}

	public Object[] getJIRAIssueAndComments() {
		JIRAIssue jiraIssue = null;
		String comments = getComments();

		if (comments.startsWith(_LEP_PREFIX_1) ||
			comments.startsWith(_LEP_PREFIX_2)) {

			comments = StringUtil.replace(
				comments, StringPool.NEW_LINE, StringPool.SPACE);

			int pos = comments.indexOf(StringPool.SPACE);

			if (pos == -1) {
				pos = comments.length();
			}

			String key = null;

			if (comments.startsWith("LEP-")) {
				key = comments.substring(_LEP_PREFIX_1.length(), pos);
			}
			else {
				key = comments.substring(_LEP_PREFIX_2.length(), pos);
			}

			comments = comments.substring(pos).trim();

			if (Validator.isNumber(key)) {
				try {
					jiraIssue = JIRAIssueLocalServiceUtil.getJIRAIssue(
						"LEP-" + key);
				}
				catch (Exception e) {
				}
			}

			if (jiraIssue != null) {
				return new Object[] {jiraIssue, comments};
			}
		}

		return null;
	}

	private static final String _LEP_PREFIX_1 = "LEP-";

	private static final String _LEP_PREFIX_2 =
		"http://support.liferay.com/browse/LEP-";

	private static Log _log = LogFactory.getLog(SVNRevisionImpl.class);

}