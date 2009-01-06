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

package com.liferay.kb.knowledgebase.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="KBArticleResourceLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBArticleResourceLocalServiceUtil {
	public static com.liferay.kb.knowledgebase.model.KBArticleResource addKBArticleResource(
		com.liferay.kb.knowledgebase.model.KBArticleResource kbArticleResource)
		throws com.liferay.portal.SystemException {
		return getService().addKBArticleResource(kbArticleResource);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticleResource createKBArticleResource(
		long resourcePrimKey) {
		return getService().createKBArticleResource(resourcePrimKey);
	}

	public static void deleteKBArticleResource(long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteKBArticleResource(resourcePrimKey);
	}

	public static void deleteKBArticleResource(
		com.liferay.kb.knowledgebase.model.KBArticleResource kbArticleResource)
		throws com.liferay.portal.SystemException {
		getService().deleteKBArticleResource(kbArticleResource);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticleResource getKBArticleResource(
		long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getKBArticleResource(resourcePrimKey);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticleResource> getKBArticleResources(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getKBArticleResources(start, end);
	}

	public static int getKBArticleResourcesCount()
		throws com.liferay.portal.SystemException {
		return getService().getKBArticleResourcesCount();
	}

	public static com.liferay.kb.knowledgebase.model.KBArticleResource updateKBArticleResource(
		com.liferay.kb.knowledgebase.model.KBArticleResource kbArticleResource)
		throws com.liferay.portal.SystemException {
		return getService().updateKBArticleResource(kbArticleResource);
	}

	public static void deleteArticleResource(long groupId,
		java.lang.String title)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteArticleResource(groupId, title);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticleResource getArticleResource(
		long articleResourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getArticleResource(articleResourcePrimKey);
	}

	public static long getArticleResourcePrimKey(long groupId,
		java.lang.String title) throws com.liferay.portal.SystemException {
		return getService().getArticleResourcePrimKey(groupId, title);
	}

	public static KBArticleResourceLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate("knowledge-base-portlet",
					KBArticleResourceLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate("knowledge-base-portlet",
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new KBArticleResourceLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(KBArticleResourceLocalService service) {
		_service = service;
	}

	private static KBArticleResourceLocalService _service;
}