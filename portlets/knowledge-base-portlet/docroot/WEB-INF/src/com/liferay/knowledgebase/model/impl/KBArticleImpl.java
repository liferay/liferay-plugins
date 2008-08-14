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

package com.liferay.knowledgebase.model.impl;

import com.liferay.documentlibrary.NoSuchDirectoryException;
import com.liferay.documentlibrary.service.DLServiceUtil;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="KBArticleImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 * @author Peter Shin
 *
 */
public class KBArticleImpl extends KBArticleModelImpl implements KBArticle {

	public static final double DEFAULT_VERSION = 1.0;

	public static final long DEFAULT_PARENT = 0;

	public KBArticleImpl() {
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public Group getGroup() {
		Group group = null;

		try {
			group = GroupLocalServiceUtil.getGroup(getGroupId());
		}
		catch (Exception e) {
			_log.error(e);
		}

		return group;
	}

	public KBArticle getParentArticle(long userId) {
		if (!hasParent()) {
			return null;
		}

		KBArticle article = null;

		try {
			article = KBArticleLocalServiceUtil.getArticle(
				userId, getParentResourcePrimKey(), true, false);
		}
		catch (Exception e) {
			_log.error(e);
		}

		return article;
	}

	public List<KBArticle> getParentArticles(long userId) {
		List<KBArticle> parentArticles = new ArrayList<KBArticle>();

		KBArticle parentArticle = getParentArticle(userId);

		if (parentArticle != null) {
			parentArticles.addAll(parentArticle.getParentArticles(userId));
			parentArticles.add(parentArticle);
		}

		return parentArticles;
	}

	public List<KBArticle> getChildArticles(long userId) {
		List<KBArticle> articles = null;

		try {
			long parentResourcePrimKey = getResourcePrimKey();

			articles = KBArticleLocalServiceUtil.getChildArticles(
				userId, parentResourcePrimKey, true, false);
		}
		catch (Exception e) {
			articles = Collections.EMPTY_LIST;
		}

		return articles;
	}

	public String getAttachmentsDir() {
		if (_attachmentDirs == null) {
			_attachmentDirs = "knowledgebase/" + getResourcePrimKey();
		}

		return _attachmentDirs;
	}

	public void setAttachmentsDir(String attachmentsDir) {
		_attachmentDirs = attachmentsDir;
	}

	public String[] getAttachmentsFiles()
		throws PortalException, SystemException {

		String[] fileNames = new String[0];

		try {
			fileNames = DLServiceUtil.getFileNames(
				getCompanyId(), CompanyConstants.SYSTEM, getAttachmentsDir());
		}
		catch (NoSuchDirectoryException nsde) {
		}
		catch (RemoteException re) {
			_log.error(re);
		}

		return fileNames;
	}

	public boolean hasParent() {
		if (getParentResourcePrimKey() > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	private static Log _log = LogFactory.getLog(KBArticleImpl.class);

	private String _userUuid;
	private String _attachmentDirs;

}