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

package com.liferay.kb.knowledgebase.service.base;

import com.liferay.counter.service.CounterLocalService;
import com.liferay.counter.service.CounterService;

import com.liferay.kb.knowledgebase.model.KBArticleResource;
import com.liferay.kb.knowledgebase.service.KBArticleLocalService;
import com.liferay.kb.knowledgebase.service.KBArticleResourceLocalService;
import com.liferay.kb.knowledgebase.service.KBArticleResourceLocalService;
import com.liferay.kb.knowledgebase.service.KBArticleService;
import com.liferay.kb.knowledgebase.service.KBFeedbackEntryLocalService;
import com.liferay.kb.knowledgebase.service.KBFeedbackStatsLocalService;
import com.liferay.kb.knowledgebase.service.persistence.KBArticleFinder;
import com.liferay.kb.knowledgebase.service.persistence.KBArticlePersistence;
import com.liferay.kb.knowledgebase.service.persistence.KBArticleResourcePersistence;
import com.liferay.kb.knowledgebase.service.persistence.KBFeedbackEntryPersistence;
import com.liferay.kb.knowledgebase.service.persistence.KBFeedbackStatsPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import java.util.List;

/**
 * <a href="KBArticleResourceLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public abstract class KBArticleResourceLocalServiceBaseImpl
	implements KBArticleResourceLocalService {
	public KBArticleResource addKBArticleResource(
		KBArticleResource kbArticleResource) throws SystemException {
		kbArticleResource.setNew(true);

		return kbArticleResourcePersistence.update(kbArticleResource, false);
	}

	public KBArticleResource createKBArticleResource(long resourcePrimKey) {
		return kbArticleResourcePersistence.create(resourcePrimKey);
	}

	public void deleteKBArticleResource(long resourcePrimKey)
		throws PortalException, SystemException {
		kbArticleResourcePersistence.remove(resourcePrimKey);
	}

	public void deleteKBArticleResource(KBArticleResource kbArticleResource)
		throws SystemException {
		kbArticleResourcePersistence.remove(kbArticleResource);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return kbArticleResourcePersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return kbArticleResourcePersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public KBArticleResource getKBArticleResource(long resourcePrimKey)
		throws PortalException, SystemException {
		return kbArticleResourcePersistence.findByPrimaryKey(resourcePrimKey);
	}

	public List<KBArticleResource> getKBArticleResources(int start, int end)
		throws SystemException {
		return kbArticleResourcePersistence.findAll(start, end);
	}

	public int getKBArticleResourcesCount() throws SystemException {
		return kbArticleResourcePersistence.countAll();
	}

	public KBArticleResource updateKBArticleResource(
		KBArticleResource kbArticleResource) throws SystemException {
		kbArticleResource.setNew(false);

		return kbArticleResourcePersistence.update(kbArticleResource, true);
	}

	public KBArticleLocalService getKBArticleLocalService() {
		return kbArticleLocalService;
	}

	public void setKBArticleLocalService(
		KBArticleLocalService kbArticleLocalService) {
		this.kbArticleLocalService = kbArticleLocalService;
	}

	public KBArticleService getKBArticleService() {
		return kbArticleService;
	}

	public void setKBArticleService(KBArticleService kbArticleService) {
		this.kbArticleService = kbArticleService;
	}

	public KBArticlePersistence getKBArticlePersistence() {
		return kbArticlePersistence;
	}

	public void setKBArticlePersistence(
		KBArticlePersistence kbArticlePersistence) {
		this.kbArticlePersistence = kbArticlePersistence;
	}

	public KBArticleFinder getKBArticleFinder() {
		return kbArticleFinder;
	}

	public void setKBArticleFinder(KBArticleFinder kbArticleFinder) {
		this.kbArticleFinder = kbArticleFinder;
	}

	public KBArticleResourceLocalService getKBArticleResourceLocalService() {
		return kbArticleResourceLocalService;
	}

	public void setKBArticleResourceLocalService(
		KBArticleResourceLocalService kbArticleResourceLocalService) {
		this.kbArticleResourceLocalService = kbArticleResourceLocalService;
	}

	public KBArticleResourcePersistence getKBArticleResourcePersistence() {
		return kbArticleResourcePersistence;
	}

	public void setKBArticleResourcePersistence(
		KBArticleResourcePersistence kbArticleResourcePersistence) {
		this.kbArticleResourcePersistence = kbArticleResourcePersistence;
	}

	public KBFeedbackEntryLocalService getKBFeedbackEntryLocalService() {
		return kbFeedbackEntryLocalService;
	}

	public void setKBFeedbackEntryLocalService(
		KBFeedbackEntryLocalService kbFeedbackEntryLocalService) {
		this.kbFeedbackEntryLocalService = kbFeedbackEntryLocalService;
	}

	public KBFeedbackEntryPersistence getKBFeedbackEntryPersistence() {
		return kbFeedbackEntryPersistence;
	}

	public void setKBFeedbackEntryPersistence(
		KBFeedbackEntryPersistence kbFeedbackEntryPersistence) {
		this.kbFeedbackEntryPersistence = kbFeedbackEntryPersistence;
	}

	public KBFeedbackStatsLocalService getKBFeedbackStatsLocalService() {
		return kbFeedbackStatsLocalService;
	}

	public void setKBFeedbackStatsLocalService(
		KBFeedbackStatsLocalService kbFeedbackStatsLocalService) {
		this.kbFeedbackStatsLocalService = kbFeedbackStatsLocalService;
	}

	public KBFeedbackStatsPersistence getKBFeedbackStatsPersistence() {
		return kbFeedbackStatsPersistence;
	}

	public void setKBFeedbackStatsPersistence(
		KBFeedbackStatsPersistence kbFeedbackStatsPersistence) {
		this.kbFeedbackStatsPersistence = kbFeedbackStatsPersistence;
	}

	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	public CounterService getCounterService() {
		return counterService;
	}

	public void setCounterService(CounterService counterService) {
		this.counterService = counterService;
	}

	protected KBArticleLocalService kbArticleLocalService;
	protected KBArticleService kbArticleService;
	protected KBArticlePersistence kbArticlePersistence;
	protected KBArticleFinder kbArticleFinder;
	protected KBArticleResourceLocalService kbArticleResourceLocalService;
	protected KBArticleResourcePersistence kbArticleResourcePersistence;
	protected KBFeedbackEntryLocalService kbFeedbackEntryLocalService;
	protected KBFeedbackEntryPersistence kbFeedbackEntryPersistence;
	protected KBFeedbackStatsLocalService kbFeedbackStatsLocalService;
	protected KBFeedbackStatsPersistence kbFeedbackStatsPersistence;
	protected CounterLocalService counterLocalService;
	protected CounterService counterService;
}