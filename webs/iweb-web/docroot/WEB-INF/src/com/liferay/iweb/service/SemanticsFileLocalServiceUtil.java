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
 * <a href="SemanticsFileLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SemanticsFileLocalServiceUtil {
	public static com.liferay.iweb.model.SemanticsFile addSemanticsFile(
		com.liferay.iweb.model.SemanticsFile semanticsFile)
		throws com.liferay.portal.SystemException {
		SemanticsFileLocalService semanticsFileLocalService = SemanticsFileLocalServiceFactory.getService();

		return semanticsFileLocalService.addSemanticsFile(semanticsFile);
	}

	public static void deleteSemanticsFile(java.lang.String semanticsURI)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		SemanticsFileLocalService semanticsFileLocalService = SemanticsFileLocalServiceFactory.getService();

		semanticsFileLocalService.deleteSemanticsFile(semanticsURI);
	}

	public static void deleteSemanticsFile(
		com.liferay.iweb.model.SemanticsFile semanticsFile)
		throws com.liferay.portal.SystemException {
		SemanticsFileLocalService semanticsFileLocalService = SemanticsFileLocalServiceFactory.getService();

		semanticsFileLocalService.deleteSemanticsFile(semanticsFile);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		SemanticsFileLocalService semanticsFileLocalService = SemanticsFileLocalServiceFactory.getService();

		return semanticsFileLocalService.dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		SemanticsFileLocalService semanticsFileLocalService = SemanticsFileLocalServiceFactory.getService();

		return semanticsFileLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	public static com.liferay.iweb.model.SemanticsFile getSemanticsFile(
		java.lang.String semanticsURI)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		SemanticsFileLocalService semanticsFileLocalService = SemanticsFileLocalServiceFactory.getService();

		return semanticsFileLocalService.getSemanticsFile(semanticsURI);
	}

	public static java.util.List<com.liferay.iweb.model.SemanticsFile> getSemanticsFiles(
		int start, int end) throws com.liferay.portal.SystemException {
		SemanticsFileLocalService semanticsFileLocalService = SemanticsFileLocalServiceFactory.getService();

		return semanticsFileLocalService.getSemanticsFiles(start, end);
	}

	public static int getSemanticsFilesCount()
		throws com.liferay.portal.SystemException {
		SemanticsFileLocalService semanticsFileLocalService = SemanticsFileLocalServiceFactory.getService();

		return semanticsFileLocalService.getSemanticsFilesCount();
	}

	public static com.liferay.iweb.model.SemanticsFile updateSemanticsFile(
		com.liferay.iweb.model.SemanticsFile semanticsFile)
		throws com.liferay.portal.SystemException {
		SemanticsFileLocalService semanticsFileLocalService = SemanticsFileLocalServiceFactory.getService();

		return semanticsFileLocalService.updateSemanticsFile(semanticsFile);
	}

	public static void createNewSemanticsFile()
		throws com.liferay.iweb.IWebException {
		SemanticsFileLocalService semanticsFileLocalService = SemanticsFileLocalServiceFactory.getService();

		semanticsFileLocalService.createNewSemanticsFile();
	}

	public static void deleteSemanticsFile()
		throws com.liferay.iweb.IWebException {
		SemanticsFileLocalService semanticsFileLocalService = SemanticsFileLocalServiceFactory.getService();

		semanticsFileLocalService.deleteSemanticsFile();
	}

	public static java.util.Set<com.liferay.iweb.model.SemanticsFile> getAllSemanticElements(
		java.util.Set<com.liferay.iweb.model.SemanticsFile> semanticsFile)
		throws com.liferay.iweb.IWebException {
		SemanticsFileLocalService semanticsFileLocalService = SemanticsFileLocalServiceFactory.getService();

		return semanticsFileLocalService.getAllSemanticElements(semanticsFile);
	}

	public static java.util.Set<com.liferay.iweb.model.SemanticsFile> getAvailablePublicSemanticsFiles()
		throws com.liferay.iweb.IWebException {
		SemanticsFileLocalService semanticsFileLocalService = SemanticsFileLocalServiceFactory.getService();

		return semanticsFileLocalService.getAvailablePublicSemanticsFiles();
	}

	public static java.util.Set<com.liferay.iweb.model.SemanticsElement> getRelatedSemanticsElements(
		java.util.Set<com.liferay.iweb.model.SemanticsElement> elements,
		boolean reason) throws com.liferay.iweb.IWebException {
		SemanticsFileLocalService semanticsFileLocalService = SemanticsFileLocalServiceFactory.getService();

		return semanticsFileLocalService.getRelatedSemanticsElements(elements,
			reason);
	}

	public static java.util.Set<com.liferay.iweb.model.SemanticsFile> listAllSemantics()
		throws com.liferay.iweb.IWebException {
		SemanticsFileLocalService semanticsFileLocalService = SemanticsFileLocalServiceFactory.getService();

		return semanticsFileLocalService.listAllSemantics();
	}

	public static com.liferay.iweb.model.SemanticsFile loadSemanticsFile(
		java.lang.String uri, java.lang.String name)
		throws com.liferay.iweb.IWebException {
		SemanticsFileLocalService semanticsFileLocalService = SemanticsFileLocalServiceFactory.getService();

		return semanticsFileLocalService.loadSemanticsFile(uri, name);
	}

	public static void loadSemanticsReasoner()
		throws com.liferay.iweb.IWebException {
		SemanticsFileLocalService semanticsFileLocalService = SemanticsFileLocalServiceFactory.getService();

		semanticsFileLocalService.loadSemanticsReasoner();
	}

	public static void removeCachedSemanticsFile(java.lang.String semanticsURI)
		throws com.liferay.iweb.IWebException {
		SemanticsFileLocalService semanticsFileLocalService = SemanticsFileLocalServiceFactory.getService();

		semanticsFileLocalService.removeCachedSemanticsFile(semanticsURI);
	}

	public static void updateSemanticsFile(java.lang.String uri,
		java.lang.String name) throws com.liferay.iweb.IWebException {
		SemanticsFileLocalService semanticsFileLocalService = SemanticsFileLocalServiceFactory.getService();

		semanticsFileLocalService.updateSemanticsFile(uri, name);
	}

	public static boolean validateSemantics(
		com.liferay.iweb.model.SemanticsFile semanticsFile)
		throws com.liferay.iweb.IWebException {
		SemanticsFileLocalService semanticsFileLocalService = SemanticsFileLocalServiceFactory.getService();

		return semanticsFileLocalService.validateSemantics(semanticsFile);
	}
}