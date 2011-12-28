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

package com.liferay.socialcoding.model.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.socialcoding.model.JIRAIssue;
import com.liferay.socialcoding.model.SVNRepository;
import com.liferay.socialcoding.service.JIRAIssueLocalServiceUtil;
import com.liferay.socialcoding.service.SVNRepositoryLocalServiceUtil;
import com.liferay.socialcoding.svn.util.SVNConstants;

import java.text.MessageFormat;

/**
 * @author Brian Wing Shun Chan
 */
public class SVNRevisionImpl
	extends SVNRevisionBaseImpl {

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
		SVNRepository svnRepository = getSVNRepository();

		return MessageFormat.format(
			SVNConstants.WEB_REVISION_NUMBER_URL,
			new Object[] {
				svnRepository.getName(), String.valueOf(getRevisionNumber())
			});
	}

	public Object[] getJIRAIssueAndComments() {
		JIRAIssue jiraIssue = null;
		String comments = getComments();

		if (

			// LEP

			comments.startsWith(_LEP_PREFIX_1) ||
			comments.startsWith(_LEP_PREFIX_2) ||
			comments.startsWith(_LEP_PREFIX_3) ||

			// LPE

			comments.startsWith(_LPE_PREFIX_1) ||
			comments.startsWith(_LPE_PREFIX_2) ||
			comments.startsWith(_LPE_PREFIX_3) ||

			// LPS

			comments.startsWith(_LPS_PREFIX_1) ||
			comments.startsWith(_LPS_PREFIX_2) ||
			comments.startsWith(_LPS_PREFIX_3)) {

			comments = StringUtil.replace(
				comments, StringPool.NEW_LINE, StringPool.SPACE);

			int pos = comments.indexOf(StringPool.SPACE);

			if (pos == -1) {
				pos = comments.length();
			}

			String keyPrefix = null;
			String keyId = null;

			// LPE

			if (comments.startsWith(_LEP_PREFIX_1)) {
				keyPrefix = "LEP";
				keyId = comments.substring(_LEP_PREFIX_1.length(), pos);
			}
			else if (comments.startsWith(_LEP_PREFIX_2)) {
				keyPrefix = "LEP";
				keyId = comments.substring(_LEP_PREFIX_2.length(), pos);
			}
			else if (comments.startsWith(_LEP_PREFIX_3)) {
				keyPrefix = "LEP";
				keyId = comments.substring(_LEP_PREFIX_3.length(), pos);
			}

			// LPE

			if (comments.startsWith(_LPE_PREFIX_1)) {
				keyPrefix = "LPE";
				keyId = comments.substring(_LPE_PREFIX_1.length(), pos);
			}
			else if (comments.startsWith(_LPE_PREFIX_2)) {
				keyPrefix = "LPE";
				keyId = comments.substring(_LPE_PREFIX_2.length(), pos);
			}
			else if (comments.startsWith(_LPE_PREFIX_3)) {
				keyPrefix = "LPE";
				keyId = comments.substring(_LPE_PREFIX_3.length(), pos);
			}

			// LPS

			if (comments.startsWith(_LPS_PREFIX_1)) {
				keyPrefix = "LPS";
				keyId = comments.substring(_LPS_PREFIX_1.length(), pos);
			}
			else if (comments.startsWith(_LPS_PREFIX_2)) {
				keyPrefix = "LPS";
				keyId = comments.substring(_LPS_PREFIX_2.length(), pos);
			}
			else if (comments.startsWith(_LPS_PREFIX_3)) {
				keyPrefix = "LPS";
				keyId = comments.substring(_LPS_PREFIX_3.length(), pos);
			}

			comments = comments.substring(pos).trim();

			if (Validator.isNumber(keyId)) {
				try {
					jiraIssue = JIRAIssueLocalServiceUtil.getJIRAIssue(
						keyPrefix + "-" + keyId);
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

	// LEP

	private static final String _LEP_PREFIX_1 = "LEP-";

	private static final String _LEP_PREFIX_2 =
		"http://issues.liferay.com/browse/LEP-";

	private static final String _LEP_PREFIX_3 =
		"http://support.liferay.com/browse/LEP-";

	// LPE

	private static final String _LPE_PREFIX_1 = "LPE-";

	private static final String _LPE_PREFIX_2 =
		"http://issues.liferay.com/browse/LPE-";

	private static final String _LPE_PREFIX_3 =
		"http://support.liferay.com/browse/LPE-";

	// LPS

	private static final String _LPS_PREFIX_1 = "LPS-";

	private static final String _LPS_PREFIX_2 =
		"http://issues.liferay.com/browse/LPS-";

	private static final String _LPS_PREFIX_3 =
		"http://support.liferay.com/browse/LPS-";

	private static Log _log = LogFactoryUtil.getLog(SVNRevisionImpl.class);

}