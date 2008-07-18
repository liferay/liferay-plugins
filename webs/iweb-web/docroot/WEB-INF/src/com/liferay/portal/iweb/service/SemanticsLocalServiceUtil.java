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

package com.liferay.portal.iweb.service;

/**
 * <a href="SemanticsLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SemanticsLocalServiceUtil {
	public static com.liferay.portal.iweb.model.Semantics addSemantics(
		com.liferay.portal.iweb.model.Semantics semantics)
		throws com.liferay.portal.SystemException {
		SemanticsLocalService semanticsLocalService = SemanticsLocalServiceFactory.getService();

		return semanticsLocalService.addSemantics(semantics);
	}

	public static void deleteSemantics(java.lang.String semanticsURI)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		SemanticsLocalService semanticsLocalService = SemanticsLocalServiceFactory.getService();

		semanticsLocalService.deleteSemantics(semanticsURI);
	}

	public static void deleteSemantics(
		com.liferay.portal.iweb.model.Semantics semantics)
		throws com.liferay.portal.SystemException {
		SemanticsLocalService semanticsLocalService = SemanticsLocalServiceFactory.getService();

		semanticsLocalService.deleteSemantics(semantics);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		SemanticsLocalService semanticsLocalService = SemanticsLocalServiceFactory.getService();

		return semanticsLocalService.dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		SemanticsLocalService semanticsLocalService = SemanticsLocalServiceFactory.getService();

		return semanticsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	public static com.liferay.portal.iweb.model.Semantics getSemantics(
		java.lang.String semanticsURI)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		SemanticsLocalService semanticsLocalService = SemanticsLocalServiceFactory.getService();

		return semanticsLocalService.getSemantics(semanticsURI);
	}

	public static com.liferay.portal.iweb.model.Semantics updateSemantics(
		com.liferay.portal.iweb.model.Semantics semantics)
		throws com.liferay.portal.SystemException {
		SemanticsLocalService semanticsLocalService = SemanticsLocalServiceFactory.getService();

		return semanticsLocalService.updateSemantics(semantics);
	}

	public static void createNewSemantics()
		throws com.liferay.portal.iweb.IWebException {
		SemanticsLocalService semanticsLocalService = SemanticsLocalServiceFactory.getService();

		semanticsLocalService.createNewSemantics();
	}

	public static void deleteSemantics()
		throws com.liferay.portal.iweb.IWebException {
		SemanticsLocalService semanticsLocalService = SemanticsLocalServiceFactory.getService();

		semanticsLocalService.deleteSemantics();
	}

	public static java.util.Set<com.liferay.portal.iweb.model.Semantics> getAllSemanticElements(
		java.util.Set<com.liferay.portal.iweb.model.Semantics> semantics)
		throws com.liferay.portal.iweb.IWebException {
		SemanticsLocalService semanticsLocalService = SemanticsLocalServiceFactory.getService();

		return semanticsLocalService.getAllSemanticElements(semantics);
	}

	public static java.util.Set<com.liferay.portal.iweb.model.Semantics> getAvailablePublicSemantics()
		throws com.liferay.portal.iweb.IWebException {
		SemanticsLocalService semanticsLocalService = SemanticsLocalServiceFactory.getService();

		return semanticsLocalService.getAvailablePublicSemantics();
	}

	public static java.util.Set<com.liferay.portal.iweb.model.SemanticsElement> getRelatedSemanticsElements(
		java.util.Set<com.liferay.portal.iweb.model.SemanticsElement> elements,
		boolean reason) throws com.liferay.portal.iweb.IWebException {
		SemanticsLocalService semanticsLocalService = SemanticsLocalServiceFactory.getService();

		return semanticsLocalService.getRelatedSemanticsElements(elements,
			reason);
	}

	public static java.util.Set<com.liferay.portal.iweb.model.Semantics> listAllSemantics()
		throws com.liferay.portal.iweb.IWebException {
		SemanticsLocalService semanticsLocalService = SemanticsLocalServiceFactory.getService();

		return semanticsLocalService.listAllSemantics();
	}

	public static void loadSemanticReasoner()
		throws com.liferay.portal.iweb.IWebException {
		SemanticsLocalService semanticsLocalService = SemanticsLocalServiceFactory.getService();

		semanticsLocalService.loadSemanticReasoner();
	}

	public static com.liferay.portal.iweb.model.Semantics loadSemantics(
		java.lang.String uri, java.lang.String name)
		throws com.liferay.portal.iweb.IWebException {
		SemanticsLocalService semanticsLocalService = SemanticsLocalServiceFactory.getService();

		return semanticsLocalService.loadSemantics(uri, name);
	}

	public static void removeCachedSemantics(java.lang.String semanticsURI)
		throws com.liferay.portal.iweb.IWebException {
		SemanticsLocalService semanticsLocalService = SemanticsLocalServiceFactory.getService();

		semanticsLocalService.removeCachedSemantics(semanticsURI);
	}

	public static void updateSemantics(java.lang.String uri,
		java.lang.String name) throws com.liferay.portal.iweb.IWebException {
		SemanticsLocalService semanticsLocalService = SemanticsLocalServiceFactory.getService();

		semanticsLocalService.updateSemantics(uri, name);
	}

	public static boolean validateSemantics(
		com.liferay.portal.iweb.model.Semantics semantics)
		throws com.liferay.portal.iweb.IWebException {
		SemanticsLocalService semanticsLocalService = SemanticsLocalServiceFactory.getService();

		return semanticsLocalService.validateSemantics(semantics);
	}
}