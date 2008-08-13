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

package com.liferay.knowledgebase.service;

/**
 * <a href="KBArticleResourceLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBArticleResourceLocalServiceUtil {
	public static com.liferay.knowledgebase.model.KBArticleResource addKBArticleResource(
		com.liferay.knowledgebase.model.KBArticleResource kbArticleResource)
		throws com.liferay.portal.SystemException {
		return _service.addKBArticleResource(kbArticleResource);
	}

	public static void deleteKBArticleResource(long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_service.deleteKBArticleResource(resourcePrimKey);
	}

	public static void deleteKBArticleResource(
		com.liferay.knowledgebase.model.KBArticleResource kbArticleResource)
		throws com.liferay.portal.SystemException {
		_service.deleteKBArticleResource(kbArticleResource);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return _service.dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return _service.dynamicQuery(dynamicQuery, start, end);
	}

	public static com.liferay.knowledgebase.model.KBArticleResource getKBArticleResource(
		long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _service.getKBArticleResource(resourcePrimKey);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticleResource> getKBArticleResources(
		int start, int end) throws com.liferay.portal.SystemException {
		return _service.getKBArticleResources(start, end);
	}

	public static int getKBArticleResourcesCount()
		throws com.liferay.portal.SystemException {
		return _service.getKBArticleResourcesCount();
	}

	public static com.liferay.knowledgebase.model.KBArticleResource updateKBArticleResource(
		com.liferay.knowledgebase.model.KBArticleResource kbArticleResource)
		throws com.liferay.portal.SystemException {
		return _service.updateKBArticleResource(kbArticleResource);
	}

	public static void deleteArticleResource(long groupId,
		java.lang.String title)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_service.deleteArticleResource(groupId, title);
	}

	public static com.liferay.knowledgebase.model.KBArticleResource getArticleResource(
		long articleResourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _service.getArticleResource(articleResourcePrimKey);
	}

	public static long getArticleResourcePrimKey(long groupId,
		java.lang.String title) throws com.liferay.portal.SystemException {
		return _service.getArticleResourcePrimKey(groupId, title);
	}

	public static KBArticleResourceLocalService getService() {
		return _service;
	}

	public void setService(KBArticleResourceLocalService service) {
		_service = service;
	}

	private static KBArticleResourceLocalService _service;
}