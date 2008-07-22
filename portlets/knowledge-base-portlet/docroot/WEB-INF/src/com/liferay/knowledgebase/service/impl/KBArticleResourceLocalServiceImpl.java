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

package com.liferay.knowledgebase.service.impl;

import com.liferay.knowledgebase.model.KBArticleResource;
import com.liferay.knowledgebase.service.base.KBArticleResourceLocalServiceBaseImpl;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

/**
 * <a href="KBArticleResourceLocalServiceImpl.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBArticleResourceLocalServiceImpl
	extends KBArticleResourceLocalServiceBaseImpl {

	public void deleteArticleResource(long groupId, String title)
		throws PortalException, SystemException {

		kbArticleResourcePersistence.removeByG_T(groupId, title);
	}

	public KBArticleResource getArticleResource(long articleResourcePrimKey)
		throws PortalException, SystemException {

		return kbArticleResourcePersistence.findByPrimaryKey(
			articleResourcePrimKey);
	}

	public long getArticleResourcePrimKey(long groupId, String title)
		throws SystemException {

		KBArticleResource articleResource =
			kbArticleResourcePersistence.fetchByG_T(groupId, title);

		if (articleResource == null) {
			long articleResourcePrimKey = counterLocalService.increment();

			articleResource = kbArticleResourcePersistence.create(
				articleResourcePrimKey);

			articleResource.setGroupId(groupId);
			articleResource.setTitle(title);

			kbArticleResourcePersistence.update(articleResource, false);
		}

		return articleResource.getResourcePrimKey();
	}

}