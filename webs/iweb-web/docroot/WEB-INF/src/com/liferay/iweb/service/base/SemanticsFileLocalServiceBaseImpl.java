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

package com.liferay.iweb.service.base;

import com.liferay.iweb.model.SemanticsFile;
import com.liferay.iweb.service.CallBackLocalService;
import com.liferay.iweb.service.CallBackLocalServiceFactory;
import com.liferay.iweb.service.CrossContainerLocalService;
import com.liferay.iweb.service.CrossContainerLocalServiceFactory;
import com.liferay.iweb.service.IWebLocalService;
import com.liferay.iweb.service.IWebLocalServiceFactory;
import com.liferay.iweb.service.InterestGroupLocalService;
import com.liferay.iweb.service.InterestGroupLocalServiceFactory;
import com.liferay.iweb.service.PostEntryLocalService;
import com.liferay.iweb.service.PostEntryLocalServiceFactory;
import com.liferay.iweb.service.SemanticsElementLocalService;
import com.liferay.iweb.service.SemanticsElementLocalServiceFactory;
import com.liferay.iweb.service.SemanticsFileLocalService;
import com.liferay.iweb.service.persistence.InterestGroupPersistence;
import com.liferay.iweb.service.persistence.InterestGroupUtil;
import com.liferay.iweb.service.persistence.PostEntryPersistence;
import com.liferay.iweb.service.persistence.PostEntryUtil;
import com.liferay.iweb.service.persistence.SemanticsElementPersistence;
import com.liferay.iweb.service.persistence.SemanticsElementUtil;
import com.liferay.iweb.service.persistence.SemanticsFilePersistence;
import com.liferay.iweb.service.persistence.SemanticsFileUtil;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bean.InitializingBean;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import java.util.List;

/**
 * <a href="SemanticsFileLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class SemanticsFileLocalServiceBaseImpl
	implements SemanticsFileLocalService, InitializingBean {
	public SemanticsFile addSemanticsFile(SemanticsFile semanticsFile)
		throws SystemException {
		semanticsFile.setNew(true);

		return semanticsFilePersistence.update(semanticsFile, false);
	}

	public void deleteSemanticsFile(String semanticsURI)
		throws PortalException, SystemException {
		semanticsFilePersistence.remove(semanticsURI);
	}

	public void deleteSemanticsFile(SemanticsFile semanticsFile)
		throws SystemException {
		semanticsFilePersistence.remove(semanticsFile);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return semanticsFilePersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return semanticsFilePersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public SemanticsFile getSemanticsFile(String semanticsURI)
		throws PortalException, SystemException {
		return semanticsFilePersistence.findByPrimaryKey(semanticsURI);
	}

	public List<SemanticsFile> getSemanticsFiles(int start, int end)
		throws SystemException {
		return semanticsFilePersistence.findAll(start, end);
	}

	public int getSemanticsFilesCount() throws SystemException {
		return semanticsFilePersistence.countAll();
	}

	public SemanticsFile updateSemanticsFile(SemanticsFile semanticsFile)
		throws SystemException {
		semanticsFile.setNew(false);

		return semanticsFilePersistence.update(semanticsFile, true);
	}

	public CallBackLocalService getCallBackLocalService() {
		return callBackLocalService;
	}

	public void setCallBackLocalService(
		CallBackLocalService callBackLocalService) {
		this.callBackLocalService = callBackLocalService;
	}

	public CrossContainerLocalService getCrossContainerLocalService() {
		return crossContainerLocalService;
	}

	public void setCrossContainerLocalService(
		CrossContainerLocalService crossContainerLocalService) {
		this.crossContainerLocalService = crossContainerLocalService;
	}

	public InterestGroupLocalService getInterestGroupLocalService() {
		return interestGroupLocalService;
	}

	public void setInterestGroupLocalService(
		InterestGroupLocalService interestGroupLocalService) {
		this.interestGroupLocalService = interestGroupLocalService;
	}

	public InterestGroupPersistence getInterestGroupPersistence() {
		return interestGroupPersistence;
	}

	public void setInterestGroupPersistence(
		InterestGroupPersistence interestGroupPersistence) {
		this.interestGroupPersistence = interestGroupPersistence;
	}

	public IWebLocalService getIWebLocalService() {
		return iWebLocalService;
	}

	public void setIWebLocalService(IWebLocalService iWebLocalService) {
		this.iWebLocalService = iWebLocalService;
	}

	public PostEntryLocalService getPostEntryLocalService() {
		return postEntryLocalService;
	}

	public void setPostEntryLocalService(
		PostEntryLocalService postEntryLocalService) {
		this.postEntryLocalService = postEntryLocalService;
	}

	public PostEntryPersistence getPostEntryPersistence() {
		return postEntryPersistence;
	}

	public void setPostEntryPersistence(
		PostEntryPersistence postEntryPersistence) {
		this.postEntryPersistence = postEntryPersistence;
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

	public SemanticsFilePersistence getSemanticsFilePersistence() {
		return semanticsFilePersistence;
	}

	public void setSemanticsFilePersistence(
		SemanticsFilePersistence semanticsFilePersistence) {
		this.semanticsFilePersistence = semanticsFilePersistence;
	}

	public void afterPropertiesSet() {
		if (callBackLocalService == null) {
			callBackLocalService = CallBackLocalServiceFactory.getImpl();
		}

		if (crossContainerLocalService == null) {
			crossContainerLocalService = CrossContainerLocalServiceFactory.getImpl();
		}

		if (interestGroupLocalService == null) {
			interestGroupLocalService = InterestGroupLocalServiceFactory.getImpl();
		}

		if (interestGroupPersistence == null) {
			interestGroupPersistence = InterestGroupUtil.getPersistence();
		}

		if (iWebLocalService == null) {
			iWebLocalService = IWebLocalServiceFactory.getImpl();
		}

		if (postEntryLocalService == null) {
			postEntryLocalService = PostEntryLocalServiceFactory.getImpl();
		}

		if (postEntryPersistence == null) {
			postEntryPersistence = PostEntryUtil.getPersistence();
		}

		if (semanticsElementLocalService == null) {
			semanticsElementLocalService = SemanticsElementLocalServiceFactory.getImpl();
		}

		if (semanticsElementPersistence == null) {
			semanticsElementPersistence = SemanticsElementUtil.getPersistence();
		}

		if (semanticsFilePersistence == null) {
			semanticsFilePersistence = SemanticsFileUtil.getPersistence();
		}
	}

	protected CallBackLocalService callBackLocalService;
	protected CrossContainerLocalService crossContainerLocalService;
	protected InterestGroupLocalService interestGroupLocalService;
	protected InterestGroupPersistence interestGroupPersistence;
	protected IWebLocalService iWebLocalService;
	protected PostEntryLocalService postEntryLocalService;
	protected PostEntryPersistence postEntryPersistence;
	protected SemanticsElementLocalService semanticsElementLocalService;
	protected SemanticsElementPersistence semanticsElementPersistence;
	protected SemanticsFilePersistence semanticsFilePersistence;
}