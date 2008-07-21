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

import com.liferay.iweb.service.CommunityLocalService;
import com.liferay.iweb.service.CommunityLocalServiceFactory;
import com.liferay.iweb.service.CrossContainerLocalService;
import com.liferay.iweb.service.CrossContainerLocalServiceFactory;
import com.liferay.iweb.service.IWebCallBackLocalService;
import com.liferay.iweb.service.IWebLocalService;
import com.liferay.iweb.service.IWebLocalServiceFactory;
import com.liferay.iweb.service.IWebService;
import com.liferay.iweb.service.IWebServiceFactory;
import com.liferay.iweb.service.PostLocalService;
import com.liferay.iweb.service.PostLocalServiceFactory;
import com.liferay.iweb.service.SemanticsElementLocalService;
import com.liferay.iweb.service.SemanticsElementLocalServiceFactory;
import com.liferay.iweb.service.SemanticsLocalService;
import com.liferay.iweb.service.SemanticsLocalServiceFactory;
import com.liferay.iweb.service.persistence.CommunityPersistence;
import com.liferay.iweb.service.persistence.CommunityUtil;
import com.liferay.iweb.service.persistence.PostPersistence;
import com.liferay.iweb.service.persistence.PostUtil;
import com.liferay.iweb.service.persistence.SemanticsElementPersistence;
import com.liferay.iweb.service.persistence.SemanticsElementUtil;
import com.liferay.iweb.service.persistence.SemanticsPersistence;
import com.liferay.iweb.service.persistence.SemanticsUtil;

import com.liferay.portal.service.GroupLocalService;
import com.liferay.portal.service.GroupLocalServiceFactory;
import com.liferay.portal.service.GroupService;
import com.liferay.portal.service.GroupServiceFactory;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserLocalServiceFactory;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.UserServiceFactory;
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.GroupUtil;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.UserUtil;

import com.liferay.portlet.blogs.service.BlogsEntryLocalService;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceFactory;
import com.liferay.portlet.blogs.service.BlogsEntryService;
import com.liferay.portlet.blogs.service.BlogsEntryServiceFactory;
import com.liferay.portlet.blogs.service.persistence.BlogsEntryPersistence;
import com.liferay.portlet.blogs.service.persistence.BlogsEntryUtil;
import com.liferay.portlet.wiki.service.WikiNodeLocalService;
import com.liferay.portlet.wiki.service.WikiNodeLocalServiceFactory;
import com.liferay.portlet.wiki.service.WikiNodeService;
import com.liferay.portlet.wiki.service.WikiNodeServiceFactory;
import com.liferay.portlet.wiki.service.WikiPageLocalService;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceFactory;
import com.liferay.portlet.wiki.service.WikiPageService;
import com.liferay.portlet.wiki.service.WikiPageServiceFactory;
import com.liferay.portlet.wiki.service.persistence.WikiNodePersistence;
import com.liferay.portlet.wiki.service.persistence.WikiNodeUtil;
import com.liferay.portlet.wiki.service.persistence.WikiPagePersistence;
import com.liferay.portlet.wiki.service.persistence.WikiPageUtil;

/**
 * <a href="IWebCallBackLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class IWebCallBackLocalServiceBaseImpl
	implements IWebCallBackLocalService {
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

	public SemanticsLocalService getSemanticsLocalService() {
		return semanticsLocalService;
	}

	public void setSemanticsLocalService(
		SemanticsLocalService semanticsLocalService) {
		this.semanticsLocalService = semanticsLocalService;
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

	public BlogsEntryLocalService getBlogsEntryLocalService() {
		return blogsEntryLocalService;
	}

	public void setBlogsEntryLocalService(
		BlogsEntryLocalService blogsEntryLocalService) {
		this.blogsEntryLocalService = blogsEntryLocalService;
	}

	public BlogsEntryService getBlogsEntryService() {
		return blogsEntryService;
	}

	public void setBlogsEntryService(BlogsEntryService blogsEntryService) {
		this.blogsEntryService = blogsEntryService;
	}

	public BlogsEntryPersistence getBlogsEntryPersistence() {
		return blogsEntryPersistence;
	}

	public void setBlogsEntryPersistence(
		BlogsEntryPersistence blogsEntryPersistence) {
		this.blogsEntryPersistence = blogsEntryPersistence;
	}

	public WikiNodeLocalService getWikiNodeLocalService() {
		return wikiNodeLocalService;
	}

	public void setWikiNodeLocalService(
		WikiNodeLocalService wikiNodeLocalService) {
		this.wikiNodeLocalService = wikiNodeLocalService;
	}

	public WikiNodeService getWikiNodeService() {
		return wikiNodeService;
	}

	public void setWikiNodeService(WikiNodeService wikiNodeService) {
		this.wikiNodeService = wikiNodeService;
	}

	public WikiNodePersistence getWikiNodePersistence() {
		return wikiNodePersistence;
	}

	public void setWikiNodePersistence(WikiNodePersistence wikiNodePersistence) {
		this.wikiNodePersistence = wikiNodePersistence;
	}

	public WikiPageLocalService getWikiPageLocalService() {
		return wikiPageLocalService;
	}

	public void setWikiPageLocalService(
		WikiPageLocalService wikiPageLocalService) {
		this.wikiPageLocalService = wikiPageLocalService;
	}

	public WikiPageService getWikiPageService() {
		return wikiPageService;
	}

	public void setWikiPageService(WikiPageService wikiPageService) {
		this.wikiPageService = wikiPageService;
	}

	public WikiPagePersistence getWikiPagePersistence() {
		return wikiPagePersistence;
	}

	public void setWikiPagePersistence(WikiPagePersistence wikiPagePersistence) {
		this.wikiPagePersistence = wikiPagePersistence;
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

	protected void init() {
		if (communityLocalService == null) {
			communityLocalService = CommunityLocalServiceFactory.getImpl();
		}

		if (communityPersistence == null) {
			communityPersistence = CommunityUtil.getPersistence();
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

		if (semanticsLocalService == null) {
			semanticsLocalService = SemanticsLocalServiceFactory.getImpl();
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

		if (blogsEntryLocalService == null) {
			blogsEntryLocalService = BlogsEntryLocalServiceFactory.getImpl();
		}

		if (blogsEntryService == null) {
			blogsEntryService = BlogsEntryServiceFactory.getImpl();
		}

		if (blogsEntryPersistence == null) {
			blogsEntryPersistence = BlogsEntryUtil.getPersistence();
		}

		if (wikiNodeLocalService == null) {
			wikiNodeLocalService = WikiNodeLocalServiceFactory.getImpl();
		}

		if (wikiNodeService == null) {
			wikiNodeService = WikiNodeServiceFactory.getImpl();
		}

		if (wikiNodePersistence == null) {
			wikiNodePersistence = WikiNodeUtil.getPersistence();
		}

		if (wikiPageLocalService == null) {
			wikiPageLocalService = WikiPageLocalServiceFactory.getImpl();
		}

		if (wikiPageService == null) {
			wikiPageService = WikiPageServiceFactory.getImpl();
		}

		if (wikiPagePersistence == null) {
			wikiPagePersistence = WikiPageUtil.getPersistence();
		}

		if (groupLocalService == null) {
			groupLocalService = GroupLocalServiceFactory.getImpl();
		}

		if (groupService == null) {
			groupService = GroupServiceFactory.getImpl();
		}

		if (groupPersistence == null) {
			groupPersistence = GroupUtil.getPersistence();
		}

		if (userLocalService == null) {
			userLocalService = UserLocalServiceFactory.getImpl();
		}

		if (userService == null) {
			userService = UserServiceFactory.getImpl();
		}

		if (userPersistence == null) {
			userPersistence = UserUtil.getPersistence();
		}
	}

	protected CommunityLocalService communityLocalService;
	protected CommunityPersistence communityPersistence;
	protected IWebLocalService iWebLocalService;
	protected IWebService iWebService;
	protected PostLocalService postLocalService;
	protected PostPersistence postPersistence;
	protected SemanticsLocalService semanticsLocalService;
	protected SemanticsPersistence semanticsPersistence;
	protected SemanticsElementLocalService semanticsElementLocalService;
	protected SemanticsElementPersistence semanticsElementPersistence;
	protected CrossContainerLocalService crossContainerLocalService;
	protected BlogsEntryLocalService blogsEntryLocalService;
	protected BlogsEntryService blogsEntryService;
	protected BlogsEntryPersistence blogsEntryPersistence;
	protected WikiNodeLocalService wikiNodeLocalService;
	protected WikiNodeService wikiNodeService;
	protected WikiNodePersistence wikiNodePersistence;
	protected WikiPageLocalService wikiPageLocalService;
	protected WikiPageService wikiPageService;
	protected WikiPagePersistence wikiPagePersistence;
	protected GroupLocalService groupLocalService;
	protected GroupService groupService;
	protected GroupPersistence groupPersistence;
	protected UserLocalService userLocalService;
	protected UserService userService;
	protected UserPersistence userPersistence;
}