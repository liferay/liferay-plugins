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

package com.liferay.knowledgebase.service.base;

import com.liferay.counter.service.CounterLocalService;
import com.liferay.counter.service.CounterService;

import com.liferay.documentlibrary.service.DLLocalService;
import com.liferay.documentlibrary.service.DLService;

import com.liferay.knowledgebase.service.KBArticleLocalService;
import com.liferay.knowledgebase.service.KBArticleResourceLocalService;
import com.liferay.knowledgebase.service.KBArticleService;
import com.liferay.knowledgebase.service.KBFeedbackEntryLocalService;
import com.liferay.knowledgebase.service.KBFeedbackStatsLocalService;
import com.liferay.knowledgebase.service.persistence.KBArticlePersistence;
import com.liferay.knowledgebase.service.persistence.KBArticleResourcePersistence;
import com.liferay.knowledgebase.service.persistence.KBFeedbackEntryPersistence;
import com.liferay.knowledgebase.service.persistence.KBFeedbackStatsPersistence;

import com.liferay.portal.kernel.bean.InitializingBean;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.service.CompanyLocalService;
import com.liferay.portal.service.CompanyService;
import com.liferay.portal.service.GroupLocalService;
import com.liferay.portal.service.GroupService;
import com.liferay.portal.service.PortletPreferencesLocalService;
import com.liferay.portal.service.PortletPreferencesService;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.SubscriptionLocalService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.base.PrincipalBean;
import com.liferay.portal.service.persistence.CompanyPersistence;
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.PortletPreferencesPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.SubscriptionPersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.portlet.messageboards.service.MBMessageLocalService;
import com.liferay.portlet.messageboards.service.MBMessageService;
import com.liferay.portlet.messageboards.service.persistence.MBMessagePersistence;
import com.liferay.portlet.tags.service.TagsAssetLocalService;
import com.liferay.portlet.tags.service.TagsAssetService;
import com.liferay.portlet.tags.service.TagsEntryLocalService;
import com.liferay.portlet.tags.service.TagsEntryService;
import com.liferay.portlet.tags.service.persistence.TagsAssetPersistence;
import com.liferay.portlet.tags.service.persistence.TagsEntryPersistence;

import com.liferay.util.bean.PortletBeanLocatorUtil;

/**
 * <a href="KBArticleServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public abstract class KBArticleServiceBaseImpl extends PrincipalBean
	implements KBArticleService, InitializingBean {
	public KBArticleLocalService getKBArticleLocalService() {
		return kbArticleLocalService;
	}

	public void setKBArticleLocalService(
		KBArticleLocalService kbArticleLocalService) {
		this.kbArticleLocalService = kbArticleLocalService;
	}

	public KBArticlePersistence getKBArticlePersistence() {
		return kbArticlePersistence;
	}

	public void setKBArticlePersistence(
		KBArticlePersistence kbArticlePersistence) {
		this.kbArticlePersistence = kbArticlePersistence;
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

	public DLLocalService getDLLocalService() {
		return dlLocalService;
	}

	public void setDLLocalService(DLLocalService dlLocalService) {
		this.dlLocalService = dlLocalService;
	}

	public DLService getDLService() {
		return dlService;
	}

	public void setDLService(DLService dlService) {
		this.dlService = dlService;
	}

	public CompanyLocalService getCompanyLocalService() {
		return companyLocalService;
	}

	public void setCompanyLocalService(CompanyLocalService companyLocalService) {
		this.companyLocalService = companyLocalService;
	}

	public CompanyService getCompanyService() {
		return companyService;
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public CompanyPersistence getCompanyPersistence() {
		return companyPersistence;
	}

	public void setCompanyPersistence(CompanyPersistence companyPersistence) {
		this.companyPersistence = companyPersistence;
	}

	public GroupLocalService getGroupLocalService() {
		return groupLocalService;
	}

	public void setGroupLocalService(GroupLocalService groupLocalService) {
		this.groupLocalService = groupLocalService;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public GroupPersistence getGroupPersistence() {
		return groupPersistence;
	}

	public void setGroupPersistence(GroupPersistence groupPersistence) {
		this.groupPersistence = groupPersistence;
	}

	public PortletPreferencesLocalService getPortletPreferencesLocalService() {
		return portletPreferencesLocalService;
	}

	public void setPortletPreferencesLocalService(
		PortletPreferencesLocalService portletPreferencesLocalService) {
		this.portletPreferencesLocalService = portletPreferencesLocalService;
	}

	public PortletPreferencesService getPortletPreferencesService() {
		return portletPreferencesService;
	}

	public void setPortletPreferencesService(
		PortletPreferencesService portletPreferencesService) {
		this.portletPreferencesService = portletPreferencesService;
	}

	public PortletPreferencesPersistence getPortletPreferencesPersistence() {
		return portletPreferencesPersistence;
	}

	public void setPortletPreferencesPersistence(
		PortletPreferencesPersistence portletPreferencesPersistence) {
		this.portletPreferencesPersistence = portletPreferencesPersistence;
	}

	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	public ResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	public SubscriptionLocalService getSubscriptionLocalService() {
		return subscriptionLocalService;
	}

	public void setSubscriptionLocalService(
		SubscriptionLocalService subscriptionLocalService) {
		this.subscriptionLocalService = subscriptionLocalService;
	}

	public SubscriptionPersistence getSubscriptionPersistence() {
		return subscriptionPersistence;
	}

	public void setSubscriptionPersistence(
		SubscriptionPersistence subscriptionPersistence) {
		this.subscriptionPersistence = subscriptionPersistence;
	}

	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public MBMessageLocalService getMBMessageLocalService() {
		return mbMessageLocalService;
	}

	public void setMBMessageLocalService(
		MBMessageLocalService mbMessageLocalService) {
		this.mbMessageLocalService = mbMessageLocalService;
	}

	public MBMessageService getMBMessageService() {
		return mbMessageService;
	}

	public void setMBMessageService(MBMessageService mbMessageService) {
		this.mbMessageService = mbMessageService;
	}

	public MBMessagePersistence getMBMessagePersistence() {
		return mbMessagePersistence;
	}

	public void setMBMessagePersistence(
		MBMessagePersistence mbMessagePersistence) {
		this.mbMessagePersistence = mbMessagePersistence;
	}

	public TagsAssetLocalService getTagsAssetLocalService() {
		return tagsAssetLocalService;
	}

	public void setTagsAssetLocalService(
		TagsAssetLocalService tagsAssetLocalService) {
		this.tagsAssetLocalService = tagsAssetLocalService;
	}

	public TagsAssetService getTagsAssetService() {
		return tagsAssetService;
	}

	public void setTagsAssetService(TagsAssetService tagsAssetService) {
		this.tagsAssetService = tagsAssetService;
	}

	public TagsAssetPersistence getTagsAssetPersistence() {
		return tagsAssetPersistence;
	}

	public void setTagsAssetPersistence(
		TagsAssetPersistence tagsAssetPersistence) {
		this.tagsAssetPersistence = tagsAssetPersistence;
	}

	public TagsEntryLocalService getTagsEntryLocalService() {
		return tagsEntryLocalService;
	}

	public void setTagsEntryLocalService(
		TagsEntryLocalService tagsEntryLocalService) {
		this.tagsEntryLocalService = tagsEntryLocalService;
	}

	public TagsEntryService getTagsEntryService() {
		return tagsEntryService;
	}

	public void setTagsEntryService(TagsEntryService tagsEntryService) {
		this.tagsEntryService = tagsEntryService;
	}

	public TagsEntryPersistence getTagsEntryPersistence() {
		return tagsEntryPersistence;
	}

	public void setTagsEntryPersistence(
		TagsEntryPersistence tagsEntryPersistence) {
		this.tagsEntryPersistence = tagsEntryPersistence;
	}

	public void afterPropertiesSet() {
		if (kbArticleLocalService == null) {
			kbArticleLocalService = (KBArticleLocalService)PortletBeanLocatorUtil.locate(KBArticleLocalService.class.getName() +
					".impl");
		}

		if (kbArticlePersistence == null) {
			kbArticlePersistence = (KBArticlePersistence)PortletBeanLocatorUtil.locate(KBArticlePersistence.class.getName() +
					".impl");
		}

		if (kbArticleResourceLocalService == null) {
			kbArticleResourceLocalService = (KBArticleResourceLocalService)PortletBeanLocatorUtil.locate(KBArticleResourceLocalService.class.getName() +
					".impl");
		}

		if (kbArticleResourcePersistence == null) {
			kbArticleResourcePersistence = (KBArticleResourcePersistence)PortletBeanLocatorUtil.locate(KBArticleResourcePersistence.class.getName() +
					".impl");
		}

		if (kbFeedbackEntryLocalService == null) {
			kbFeedbackEntryLocalService = (KBFeedbackEntryLocalService)PortletBeanLocatorUtil.locate(KBFeedbackEntryLocalService.class.getName() +
					".impl");
		}

		if (kbFeedbackEntryPersistence == null) {
			kbFeedbackEntryPersistence = (KBFeedbackEntryPersistence)PortletBeanLocatorUtil.locate(KBFeedbackEntryPersistence.class.getName() +
					".impl");
		}

		if (kbFeedbackStatsLocalService == null) {
			kbFeedbackStatsLocalService = (KBFeedbackStatsLocalService)PortletBeanLocatorUtil.locate(KBFeedbackStatsLocalService.class.getName() +
					".impl");
		}

		if (kbFeedbackStatsPersistence == null) {
			kbFeedbackStatsPersistence = (KBFeedbackStatsPersistence)PortletBeanLocatorUtil.locate(KBFeedbackStatsPersistence.class.getName() +
					".impl");
		}

		if (counterLocalService == null) {
			counterLocalService = (CounterLocalService)PortalBeanLocatorUtil.locate(CounterLocalService.class.getName() +
					".impl");
		}

		if (counterService == null) {
			counterService = (CounterService)PortalBeanLocatorUtil.locate(CounterService.class.getName() +
					".impl");
		}

		if (dlLocalService == null) {
			dlLocalService = (DLLocalService)PortalBeanLocatorUtil.locate(DLLocalService.class.getName() +
					".impl");
		}

		if (dlService == null) {
			dlService = (DLService)PortalBeanLocatorUtil.locate(DLService.class.getName() +
					".impl");
		}

		if (companyLocalService == null) {
			companyLocalService = (CompanyLocalService)PortalBeanLocatorUtil.locate(CompanyLocalService.class.getName() +
					".impl");
		}

		if (companyService == null) {
			companyService = (CompanyService)PortalBeanLocatorUtil.locate(CompanyService.class.getName() +
					".impl");
		}

		if (companyPersistence == null) {
			companyPersistence = (CompanyPersistence)PortalBeanLocatorUtil.locate(CompanyPersistence.class.getName() +
					".impl");
		}

		if (groupLocalService == null) {
			groupLocalService = (GroupLocalService)PortalBeanLocatorUtil.locate(GroupLocalService.class.getName() +
					".impl");
		}

		if (groupService == null) {
			groupService = (GroupService)PortalBeanLocatorUtil.locate(GroupService.class.getName() +
					".impl");
		}

		if (groupPersistence == null) {
			groupPersistence = (GroupPersistence)PortalBeanLocatorUtil.locate(GroupPersistence.class.getName() +
					".impl");
		}

		if (portletPreferencesLocalService == null) {
			portletPreferencesLocalService = (PortletPreferencesLocalService)PortalBeanLocatorUtil.locate(PortletPreferencesLocalService.class.getName() +
					".impl");
		}

		if (portletPreferencesService == null) {
			portletPreferencesService = (PortletPreferencesService)PortalBeanLocatorUtil.locate(PortletPreferencesService.class.getName() +
					".impl");
		}

		if (portletPreferencesPersistence == null) {
			portletPreferencesPersistence = (PortletPreferencesPersistence)PortalBeanLocatorUtil.locate(PortletPreferencesPersistence.class.getName() +
					".impl");
		}

		if (resourceLocalService == null) {
			resourceLocalService = (ResourceLocalService)PortalBeanLocatorUtil.locate(ResourceLocalService.class.getName() +
					".impl");
		}

		if (resourceService == null) {
			resourceService = (ResourceService)PortalBeanLocatorUtil.locate(ResourceService.class.getName() +
					".impl");
		}

		if (resourcePersistence == null) {
			resourcePersistence = (ResourcePersistence)PortalBeanLocatorUtil.locate(ResourcePersistence.class.getName() +
					".impl");
		}

		if (subscriptionLocalService == null) {
			subscriptionLocalService = (SubscriptionLocalService)PortalBeanLocatorUtil.locate(SubscriptionLocalService.class.getName() +
					".impl");
		}

		if (subscriptionPersistence == null) {
			subscriptionPersistence = (SubscriptionPersistence)PortalBeanLocatorUtil.locate(SubscriptionPersistence.class.getName() +
					".impl");
		}

		if (userLocalService == null) {
			userLocalService = (UserLocalService)PortalBeanLocatorUtil.locate(UserLocalService.class.getName() +
					".impl");
		}

		if (userService == null) {
			userService = (UserService)PortalBeanLocatorUtil.locate(UserService.class.getName() +
					".impl");
		}

		if (userPersistence == null) {
			userPersistence = (UserPersistence)PortalBeanLocatorUtil.locate(UserPersistence.class.getName() +
					".impl");
		}

		if (mbMessageLocalService == null) {
			mbMessageLocalService = (MBMessageLocalService)PortalBeanLocatorUtil.locate(MBMessageLocalService.class.getName() +
					".impl");
		}

		if (mbMessageService == null) {
			mbMessageService = (MBMessageService)PortalBeanLocatorUtil.locate(MBMessageService.class.getName() +
					".impl");
		}

		if (mbMessagePersistence == null) {
			mbMessagePersistence = (MBMessagePersistence)PortalBeanLocatorUtil.locate(MBMessagePersistence.class.getName() +
					".impl");
		}

		if (tagsAssetLocalService == null) {
			tagsAssetLocalService = (TagsAssetLocalService)PortalBeanLocatorUtil.locate(TagsAssetLocalService.class.getName() +
					".impl");
		}

		if (tagsAssetService == null) {
			tagsAssetService = (TagsAssetService)PortalBeanLocatorUtil.locate(TagsAssetService.class.getName() +
					".impl");
		}

		if (tagsAssetPersistence == null) {
			tagsAssetPersistence = (TagsAssetPersistence)PortalBeanLocatorUtil.locate(TagsAssetPersistence.class.getName() +
					".impl");
		}

		if (tagsEntryLocalService == null) {
			tagsEntryLocalService = (TagsEntryLocalService)PortalBeanLocatorUtil.locate(TagsEntryLocalService.class.getName() +
					".impl");
		}

		if (tagsEntryService == null) {
			tagsEntryService = (TagsEntryService)PortalBeanLocatorUtil.locate(TagsEntryService.class.getName() +
					".impl");
		}

		if (tagsEntryPersistence == null) {
			tagsEntryPersistence = (TagsEntryPersistence)PortalBeanLocatorUtil.locate(TagsEntryPersistence.class.getName() +
					".impl");
		}
	}

	protected KBArticleLocalService kbArticleLocalService;
	protected KBArticlePersistence kbArticlePersistence;
	protected KBArticleResourceLocalService kbArticleResourceLocalService;
	protected KBArticleResourcePersistence kbArticleResourcePersistence;
	protected KBFeedbackEntryLocalService kbFeedbackEntryLocalService;
	protected KBFeedbackEntryPersistence kbFeedbackEntryPersistence;
	protected KBFeedbackStatsLocalService kbFeedbackStatsLocalService;
	protected KBFeedbackStatsPersistence kbFeedbackStatsPersistence;
	protected CounterLocalService counterLocalService;
	protected CounterService counterService;
	protected DLLocalService dlLocalService;
	protected DLService dlService;
	protected CompanyLocalService companyLocalService;
	protected CompanyService companyService;
	protected CompanyPersistence companyPersistence;
	protected GroupLocalService groupLocalService;
	protected GroupService groupService;
	protected GroupPersistence groupPersistence;
	protected PortletPreferencesLocalService portletPreferencesLocalService;
	protected PortletPreferencesService portletPreferencesService;
	protected PortletPreferencesPersistence portletPreferencesPersistence;
	protected ResourceLocalService resourceLocalService;
	protected ResourceService resourceService;
	protected ResourcePersistence resourcePersistence;
	protected SubscriptionLocalService subscriptionLocalService;
	protected SubscriptionPersistence subscriptionPersistence;
	protected UserLocalService userLocalService;
	protected UserService userService;
	protected UserPersistence userPersistence;
	protected MBMessageLocalService mbMessageLocalService;
	protected MBMessageService mbMessageService;
	protected MBMessagePersistence mbMessagePersistence;
	protected TagsAssetLocalService tagsAssetLocalService;
	protected TagsAssetService tagsAssetService;
	protected TagsAssetPersistence tagsAssetPersistence;
	protected TagsEntryLocalService tagsEntryLocalService;
	protected TagsEntryService tagsEntryService;
	protected TagsEntryPersistence tagsEntryPersistence;
}