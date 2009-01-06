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

package com.liferay.iweb.service;

/**
 * <a href="SemanticsElementLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SemanticsElementLocalServiceUtil {
	public static com.liferay.iweb.model.SemanticsElement addSemanticsElement(
		com.liferay.iweb.model.SemanticsElement semanticsElement)
		throws com.liferay.portal.SystemException {
		SemanticsElementLocalService semanticsElementLocalService = SemanticsElementLocalServiceFactory.getService();

		return semanticsElementLocalService.addSemanticsElement(semanticsElement);
	}

	public static void deleteSemanticsElement(java.lang.String elementURI)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		SemanticsElementLocalService semanticsElementLocalService = SemanticsElementLocalServiceFactory.getService();

		semanticsElementLocalService.deleteSemanticsElement(elementURI);
	}

	public static void deleteSemanticsElement(
		com.liferay.iweb.model.SemanticsElement semanticsElement)
		throws com.liferay.portal.SystemException {
		SemanticsElementLocalService semanticsElementLocalService = SemanticsElementLocalServiceFactory.getService();

		semanticsElementLocalService.deleteSemanticsElement(semanticsElement);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		SemanticsElementLocalService semanticsElementLocalService = SemanticsElementLocalServiceFactory.getService();

		return semanticsElementLocalService.dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		SemanticsElementLocalService semanticsElementLocalService = SemanticsElementLocalServiceFactory.getService();

		return semanticsElementLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	public static com.liferay.iweb.model.SemanticsElement getSemanticsElement(
		java.lang.String elementURI)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		SemanticsElementLocalService semanticsElementLocalService = SemanticsElementLocalServiceFactory.getService();

		return semanticsElementLocalService.getSemanticsElement(elementURI);
	}

	public static java.util.List<com.liferay.iweb.model.SemanticsElement> getSemanticsElements(
		int start, int end) throws com.liferay.portal.SystemException {
		SemanticsElementLocalService semanticsElementLocalService = SemanticsElementLocalServiceFactory.getService();

		return semanticsElementLocalService.getSemanticsElements(start, end);
	}

	public static int getSemanticsElementsCount()
		throws com.liferay.portal.SystemException {
		SemanticsElementLocalService semanticsElementLocalService = SemanticsElementLocalServiceFactory.getService();

		return semanticsElementLocalService.getSemanticsElementsCount();
	}

	public static com.liferay.iweb.model.SemanticsElement updateSemanticsElement(
		com.liferay.iweb.model.SemanticsElement semanticsElement)
		throws com.liferay.portal.SystemException {
		SemanticsElementLocalService semanticsElementLocalService = SemanticsElementLocalServiceFactory.getService();

		return semanticsElementLocalService.updateSemanticsElement(semanticsElement);
	}
}