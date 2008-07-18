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

package com.liferay.portal.iweb.service.base;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.iweb.model.Semantics;
import com.liferay.portal.iweb.service.CommunityLocalService;
import com.liferay.portal.iweb.service.CommunityLocalServiceFactory;
import com.liferay.portal.iweb.service.CrossContainerLocalService;
import com.liferay.portal.iweb.service.CrossContainerLocalServiceFactory;
import com.liferay.portal.iweb.service.IWebCallBackLocalService;
import com.liferay.portal.iweb.service.IWebCallBackLocalServiceFactory;
import com.liferay.portal.iweb.service.IWebLocalService;
import com.liferay.portal.iweb.service.IWebLocalServiceFactory;
import com.liferay.portal.iweb.service.IWebService;
import com.liferay.portal.iweb.service.IWebServiceFactory;
import com.liferay.portal.iweb.service.PostLocalService;
import com.liferay.portal.iweb.service.PostLocalServiceFactory;
import com.liferay.portal.iweb.service.SemanticsElementLocalService;
import com.liferay.portal.iweb.service.SemanticsElementLocalServiceFactory;
import com.liferay.portal.iweb.service.SemanticsLocalService;
import com.liferay.portal.iweb.service.persistence.CommunityPersistence;
import com.liferay.portal.iweb.service.persistence.CommunityUtil;
import com.liferay.portal.iweb.service.persistence.PostPersistence;
import com.liferay.portal.iweb.service.persistence.PostUtil;
import com.liferay.portal.iweb.service.persistence.SemanticsElementPersistence;
import com.liferay.portal.iweb.service.persistence.SemanticsElementUtil;
import com.liferay.portal.iweb.service.persistence.SemanticsPersistence;
import com.liferay.portal.iweb.service.persistence.SemanticsUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import java.util.List;

/**
 * <a href="SemanticsLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class SemanticsLocalServiceBaseImpl
	implements SemanticsLocalService {
	public Semantics addSemantics(Semantics semantics)
		throws SystemException {
		semantics.setNew(true);

		return semanticsPersistence.update(semantics, false);
	}

	public void deleteSemantics(String semanticsURI)
		throws PortalException, SystemException {
		semanticsPersistence.remove(semanticsURI);
	}

	public void deleteSemantics(Semantics semantics) throws SystemException {
		semanticsPersistence.remove(semantics);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return semanticsPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return semanticsPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	public Semantics getSemantics(String semanticsURI)
		throws PortalException, SystemException {
		return semanticsPersistence.findByPrimaryKey(semanticsURI);
	}

	public Semantics updateSemantics(Semantics semantics)
		throws SystemException {
		semantics.setNew(false);

		return semanticsPersistence.update(semantics, true);
	}

	public CommunityLocalService getCommunityLocalService() {
		return communityLocalService;
	}

	public void setCommunityLocalService(
		CommunityLocalService communityLocalService) {
		this.communityLocalService = communityLocalService;
	}

	public CommunityPersistence getCommunityPersistence() {
		return communityPersistence;
	}

	public void setCommunityPersistence(
		CommunityPersistence communityPersistence) {
		this.communityPersistence = communityPersistence;
	}

	public IWebCallBackLocalService getIWebCallBackLocalService() {
		return iWebCallBackLocalService;
	}

	public void setIWebCallBackLocalService(
		IWebCallBackLocalService iWebCallBackLocalService) {
		this.iWebCallBackLocalService = iWebCallBackLocalService;
	}

	public IWebLocalService getIWebLocalService() {
		return iWebLocalService;
	}

	public void setIWebLocalService(IWebLocalService iWebLocalService) {
		this.iWebLocalService = iWebLocalService;
	}

	public IWebService getIWebService() {
		return iWebService;
	}

	public void setIWebService(IWebService iWebService) {
		this.iWebService = iWebService;
	}

	public PostLocalService getPostLocalService() {
		return postLocalService;
	}

	public void setPostLocalService(PostLocalService postLocalService) {
		this.postLocalService = postLocalService;
	}

	public PostPersistence getPostPersistence() {
		return postPersistence;
	}

	public void setPostPersistence(PostPersistence postPersistence) {
		this.postPersistence = postPersistence;
	}

	public SemanticsPersistence getSemanticsPersistence() {
		return semanticsPersistence;
	}

	public void setSemanticsPersistence(
		SemanticsPersistence semanticsPersistence) {
		this.semanticsPersistence = semanticsPersistence;
	}

	public SemanticsElementLocalService getSemanticsElementLocalService() {
		return semanticsElementLocalService;
	}

	public void setSemanticsElementLocalService(
		SemanticsElementLocalService semanticsElementLocalService) {
		this.semanticsElementLocalService = semanticsElementLocalService;
	}

	public SemanticsElementPersistence getSemanticsElementPersistence() {
		return semanticsElementPersistence;
	}

	public void setSemanticsElementPersistence(
		SemanticsElementPersistence semanticsElementPersistence) {
		this.semanticsElementPersistence = semanticsElementPersistence;
	}

	public CrossContainerLocalService getCrossContainerLocalService() {
		return crossContainerLocalService;
	}

	public void setCrossContainerLocalService(
		CrossContainerLocalService crossContainerLocalService) {
		this.crossContainerLocalService = crossContainerLocalService;
	}

	protected void init() {
		if (communityLocalService == null) {
			communityLocalService = CommunityLocalServiceFactory.getImpl();
		}

		if (communityPersistence == null) {
			communityPersistence = CommunityUtil.getPersistence();
		}

		if (iWebCallBackLocalService == null) {
			iWebCallBackLocalService = IWebCallBackLocalServiceFactory.getImpl();
		}

		if (iWebLocalService == null) {
			iWebLocalService = IWebLocalServiceFactory.getImpl();
		}

		if (iWebService == null) {
			iWebService = IWebServiceFactory.getImpl();
		}

		if (postLocalService == null) {
			postLocalService = PostLocalServiceFactory.getImpl();
		}

		if (postPersistence == null) {
			postPersistence = PostUtil.getPersistence();
		}

		if (semanticsPersistence == null) {
			semanticsPersistence = SemanticsUtil.getPersistence();
		}

		if (semanticsElementLocalService == null) {
			semanticsElementLocalService = SemanticsElementLocalServiceFactory.getImpl();
		}

		if (semanticsElementPersistence == null) {
			semanticsElementPersistence = SemanticsElementUtil.getPersistence();
		}

		if (crossContainerLocalService == null) {
			crossContainerLocalService = CrossContainerLocalServiceFactory.getImpl();
		}
	}

	protected CommunityLocalService communityLocalService;
	protected CommunityPersistence communityPersistence;
	protected IWebCallBackLocalService iWebCallBackLocalService;
	protected IWebLocalService iWebLocalService;
	protected IWebService iWebService;
	protected PostLocalService postLocalService;
	protected PostPersistence postPersistence;
	protected SemanticsPersistence semanticsPersistence;
	protected SemanticsElementLocalService semanticsElementLocalService;
	protected SemanticsElementPersistence semanticsElementPersistence;
	protected CrossContainerLocalService crossContainerLocalService;
}