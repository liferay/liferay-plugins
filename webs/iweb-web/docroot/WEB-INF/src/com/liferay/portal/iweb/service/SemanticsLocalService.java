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
 * <a href="SemanticsLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface SemanticsLocalService {
	public com.liferay.portal.iweb.model.Semantics addSemantics(
		com.liferay.portal.iweb.model.Semantics semantics)
		throws com.liferay.portal.SystemException;

	public void deleteSemantics(java.lang.String semanticsURI)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deleteSemantics(
		com.liferay.portal.iweb.model.Semantics semantics)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public com.liferay.portal.iweb.model.Semantics getSemantics(
		java.lang.String semanticsURI)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public com.liferay.portal.iweb.model.Semantics updateSemantics(
		com.liferay.portal.iweb.model.Semantics semantics)
		throws com.liferay.portal.SystemException;

	public void createNewSemantics()
		throws com.liferay.portal.iweb.IWebException;

	public void deleteSemantics() throws com.liferay.portal.iweb.IWebException;

	public java.util.Set<com.liferay.portal.iweb.model.Semantics> getAllSemanticElements(
		java.util.Set<com.liferay.portal.iweb.model.Semantics> semantics)
		throws com.liferay.portal.iweb.IWebException;

	public java.util.Set<com.liferay.portal.iweb.model.Semantics> getAvailablePublicSemantics()
		throws com.liferay.portal.iweb.IWebException;

	public java.util.Set<com.liferay.portal.iweb.model.SemanticsElement> getRelatedSemanticsElements(
		java.util.Set<com.liferay.portal.iweb.model.SemanticsElement> elements,
		boolean reason) throws com.liferay.portal.iweb.IWebException;

	public java.util.Set<com.liferay.portal.iweb.model.Semantics> listAllSemantics()
		throws com.liferay.portal.iweb.IWebException;

	public void loadSemanticReasoner()
		throws com.liferay.portal.iweb.IWebException;

	public com.liferay.portal.iweb.model.Semantics loadSemantics(
		java.lang.String uri, java.lang.String name)
		throws com.liferay.portal.iweb.IWebException;

	public void removeCachedSemantics(java.lang.String semanticsURI)
		throws com.liferay.portal.iweb.IWebException;

	public void updateSemantics(java.lang.String uri, java.lang.String name)
		throws com.liferay.portal.iweb.IWebException;

	public boolean validateSemantics(
		com.liferay.portal.iweb.model.Semantics semantics)
		throws com.liferay.portal.iweb.IWebException;
}