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

package com.liferay.knowledgebase.service.impl;

import com.liferay.documentlibrary.DuplicateDirectoryException;
import com.liferay.documentlibrary.DuplicateFileException;
import com.liferay.documentlibrary.NoSuchDirectoryException;
import com.liferay.documentlibrary.NoSuchFileException;
import com.liferay.knowledgebase.ArticleTitleException;
import com.liferay.knowledgebase.ArticleVersionException;
import com.liferay.knowledgebase.KnowledgeBaseKeys;
import com.liferay.knowledgebase.NoSuchArticleException;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.impl.KBArticleImpl;
import com.liferay.knowledgebase.portlet.KnowledgeBaseFriendlyURLMapper;
import com.liferay.knowledgebase.service.base.KBArticleLocalServiceBaseImpl;
import com.liferay.knowledgebase.util.Indexer;
import com.liferay.knowledgebase.util.comparator.ArticleModifiedDateComparator;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.NotificationThreadLocal;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.util.MathUtil;

import java.rmi.RemoteException;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.mail.internet.InternetAddress;

import javax.portlet.PortletPreferences;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="KBArticleLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBArticleLocalServiceImpl extends KBArticleLocalServiceBaseImpl {

	public KBArticle addArticle(
			long userId, long groupId, String title, String content,
			String description, boolean minorEdit, boolean template,
			long parentResourcePrimKey, String[] tagsEntries,
			PortletPreferences prefs, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		String uuid = null;
		double version = KBArticleImpl.DEFAULT_VERSION;
		boolean head = true;

		return addArticle(
			uuid, userId, groupId, title, version, content, description,
			minorEdit, head, template, parentResourcePrimKey, tagsEntries,
			prefs, themeDisplay);
	}

	public KBArticle addArticle(
			String uuid, long userId, long groupId, String title,
			double version, String content, String description,
			boolean minorEdit, boolean head, boolean template,
			long parentResourcePrimKey, String[] tagsEntries,
			PortletPreferences prefs, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		// Article

		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		validate(title);

		int count = kbArticlePersistence.countByG_T(groupId, title);

		if (count > 0) {
			throw new ArticleTitleException(
				"The title " + title + " is already being used");
		}

		long articleId = counterLocalService.increment();

		long resourcePrimKey =
			kbArticleResourceLocalService.getArticleResourcePrimKey(
				groupId, title);

		KBArticle article = kbArticlePersistence.create(articleId);

		article.setUuid(uuid);
		article.setResourcePrimKey(resourcePrimKey);
		article.setCompanyId(user.getCompanyId());
		article.setUserId(user.getUserId());
		article.setUserName(user.getFullName());
		article.setModifiedDate(now);
		article.setGroupId(groupId);
		article.setTitle(title);
		article.setVersion(version);
		article.setMinorEdit(minorEdit);
		article.setContent(content);
		article.setDescription(description);
		article.setHead(head);
		article.setTemplate(template);
		article.setParentResourcePrimKey(parentResourcePrimKey);

		kbArticlePersistence.update(article, false);

		// Resources

		addArticleResources(article.getGroupId(), article, true, true);

		// Subscriptions

		if (!minorEdit && NotificationThreadLocal.isNotificationEnabled()) {
			try {
				notifySubscribers(article, prefs, themeDisplay, false);
			} catch (Exception e) {
				throw new SystemException(e);
			}
		}

		// Tags

		updateTagsAsset(userId, article, tagsEntries);

		// Lucene

		try {
			Indexer.addArticle(
				article.getCompanyId(), groupId, userId, resourcePrimKey, title,
				content, description, tagsEntries);
		}
		catch (SearchException se) {
			_log.error("Indexing " + articleId, se);
		}

		return article;
	}

	public void addArticleAttachments(
			long resourcePrimKey, List<ObjectValuePair<String, byte[]>> files)
		throws PortalException, SystemException {

		if (files.size() == 0) {
			return;
		}

		KBArticle article = getArticle(resourcePrimKey);

		long companyId = article.getCompanyId();
		String portletId = CompanyConstants.SYSTEM_STRING;
		long repositoryId = CompanyConstants.SYSTEM;
		String dirName = article.getAttachmentsDir();

		try {
			try {
				dlService.addDirectory(companyId, repositoryId, dirName);
			}
			catch (DuplicateDirectoryException dde) {
			}

			for (int i = 0; i < files.size(); i++) {
				ObjectValuePair<String, byte[]> ovp = files.get(i);

				String fileName = ovp.getKey();
				byte[] bytes = ovp.getValue();

				if (Validator.isNull(fileName)) {
					continue;
				}

				try {
					dlService.addFile(
						companyId, portletId, article.getGroupId(),
						repositoryId, dirName + "/" + fileName,
						StringPool.BLANK, new String[0], bytes);
				}
				catch (DuplicateFileException dfe) {
				}
			}
		}
		catch (RemoteException re) {
			throw new SystemException(re);
		}
	}

	public void addArticleResources(
			long groupId, KBArticle article, boolean addCommunityPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addResources(
			article.getCompanyId(), groupId,	article.getUserId(),
			KBArticle.class.getName(), article.getResourcePrimKey(), false,
			addCommunityPermissions, addGuestPermissions);
	}

	public void addArticleResources(
			long groupId, KBArticle article, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addModelResources(
			article.getCompanyId(), groupId, article.getUserId(),
			KBArticle.class.getName(), article.getResourcePrimKey(),
			communityPermissions, guestPermissions);
	}

	public void deleteArticle(long resourcePrimKey)
		throws PortalException, SystemException {

		List<KBArticle> articles = kbArticlePersistence.findByR_H(
			resourcePrimKey, true, 0, 1);

		if (articles.size() > 0) {
			KBArticle article = articles.iterator().next();

			deleteArticle(article);
		}
	}

	public void deleteArticle(KBArticle article)
		throws PortalException, SystemException {

		// Children

		List<KBArticle> children =
			kbArticlePersistence.findByParentResourcePrimKey(
				article.getResourcePrimKey());

		for (KBArticle curArticle : children) {
			deleteArticle(curArticle);
		}

		// Lucene

		try {
			Indexer.deleteArticle(
				article.getCompanyId(), article.getResourcePrimKey());
		}
		catch (SearchException se) {
			_log.error("Deleting index " + article.getPrimaryKey(), se);
		}

		// Attachments

		long companyId = article.getCompanyId();
		String portletId = CompanyConstants.SYSTEM_STRING;
		long repositoryId = CompanyConstants.SYSTEM;
		String dirName = article.getAttachmentsDir();

		try {
			dlService.deleteDirectory(
				companyId, portletId, repositoryId, dirName);
		}
		catch (NoSuchDirectoryException nsde) {
		}
		catch (RemoteException re) {
			throw new SystemException(re);
		}

		// Tags

		tagsAssetLocalService.deleteAsset(
			KBArticle.class.getName(), article.getResourcePrimKey());

		// Subscriptions

		subscriptionLocalService.deleteSubscriptions(
			article.getCompanyId(), KBArticle.class.getName(),
			article.getArticleId());

		// KBFeedback

		kbFeedbackEntryLocalService.deleteArticleFeedbackEntries(
			article.getResourcePrimKey());

		// Message boards

		mbMessageLocalService.deleteDiscussionMessages(
			KBArticle.class.getName(), article.getResourcePrimKey());

		// Resources

		resourceLocalService.deleteResource(
			article.getCompanyId(), KBArticle.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, article.getResourcePrimKey());

		// Resource

		kbArticleResourceLocalService.deleteArticleResource(
			article.getGroupId(), article.getTitle());

		// All versions

		kbArticlePersistence.removeByResourcePrimKey(
			article.getResourcePrimKey());

	}

	public void deleteArticleAttachment(
			long resourcePrimKey, String fileName)
		throws PortalException, SystemException {

		if (Validator.isNull(fileName)) {
			return;
		}

		KBArticle article = getArticle(resourcePrimKey);

		long companyId = article.getCompanyId();
		String portletId = CompanyConstants.SYSTEM_STRING;
		long repositoryId = CompanyConstants.SYSTEM;

		try {
			dlService.deleteFile(companyId, portletId, repositoryId, fileName);
		}
		catch (NoSuchFileException nsfe) {
		}
		catch (RemoteException re) {
			throw new SystemException(re);
		}
	}

	public void deleteArticles(long groupId, boolean template)
		throws PortalException, SystemException {

		Iterator<KBArticle> itr = kbArticlePersistence.findByG_H_T(
			groupId, true, template).iterator();

		while (itr.hasNext()) {
			KBArticle article = itr.next();

			deleteArticle(article);
		}
	}

	public List<KBArticle> getChildren(
			long parentResourcePrimKey, boolean head)
		throws SystemException {

		return kbArticlePersistence.findByP_H(parentResourcePrimKey, head);
	}

	public KBArticle getArticle(long resourcePrimKey)
		throws PortalException, SystemException {

		List<KBArticle> articles = kbArticlePersistence.findByR_H(
			resourcePrimKey, true, 0, 1);

		if (articles.size() > 0) {
			return articles.get(0);
		}
		else {
			throw new NoSuchArticleException();
		}
	}

	public KBArticle getArticle(long resourcePrimKey, double version)
		throws PortalException, SystemException {

		KBArticle article = null;

		if (version == 0) {
			article = getArticle(resourcePrimKey);
		}
		else {
			article = kbArticlePersistence.findByR_V(resourcePrimKey, version);
		}

		return article;
	}

	public KBArticle getArticle(long groupId, String title)
		throws PortalException, SystemException {

		List<KBArticle> articles = kbArticlePersistence.findByG_T_H(
			groupId, title, true, 0, 1);

		if (articles.size() > 0) {
			return articles.get(0);
		}
		else {
			throw new NoSuchArticleException();
		}
	}

	public KBArticle getArticle(long groupId, String title, double version)
		throws PortalException, SystemException {

		KBArticle article = null;

		if (version == 0) {
			article = getArticle(groupId, title);
		}
		else {
			article = kbArticlePersistence.findByG_T_V(groupId, title, version);
		}

		return article;
	}

	public List<KBArticle> getArticles(long resourcePrimKey, int start, int end)
		throws SystemException {

		return kbArticlePersistence.findByResourcePrimKey(
			resourcePrimKey, start, end,
			new ArticleModifiedDateComparator(false));
	}

	public List<KBArticle> getArticles(
			long resourcePrimKey, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		return kbArticlePersistence.findByResourcePrimKey(
			resourcePrimKey, start, end, obc);
	}

	public List<KBArticle> getArticles(
			long groupId, boolean head, boolean template, int start, int end)
		throws SystemException {

		return kbArticlePersistence.findByG_H_T(
			groupId, head, template, start, end,
			new ArticleModifiedDateComparator(false));
	}

	public List<KBArticle> getArticles(
			long groupId, String title, boolean head, int start, int end)
		throws SystemException {

		return kbArticlePersistence.findByG_T_H(
			groupId, title, head, start, end,
			new ArticleModifiedDateComparator(false));
	}

	public int getArticlesCount(long resourcePrimKey) throws SystemException {

		return kbArticlePersistence.countByResourcePrimKey(resourcePrimKey);
	}

	public int getArticlesCount(long groupId, boolean head, boolean template)
		throws SystemException {

		return kbArticlePersistence.countByG_H_T(groupId, head, template);
	}

	public int getArticlesCount(long groupId, String title, boolean head)
		throws SystemException {

		return kbArticlePersistence.countByG_T_H(groupId, title, head);
	}

	public void reIndex(String[] ids) throws SystemException {
		if (SearchEngineUtil.isIndexReadOnly()) {
			return;
		}

		long companyId = GetterUtil.getLong(ids[0]);

		try {
			Iterator<KBArticle> articlesItr =
				kbArticlePersistence.findByCompanyId(companyId).iterator();

			while (articlesItr.hasNext()) {
				KBArticle article = articlesItr.next();

				long groupId = article.getGroupId();
				long userId = article.getUserId();
				long resourcePrimKey = article.getResourcePrimKey();
				String title = article.getTitle();
				String content = article.getContent();
				String description = article.getDescription();

				String[] tagsEntries = tagsEntryLocalService.getEntryNames(
					KBArticle.class.getName(), article.getResourcePrimKey());

				try {
					Document doc = Indexer.getArticleDocument(
						companyId, groupId, userId, resourcePrimKey, title,
						content, description, tagsEntries);

					SearchEngineUtil.addDocument(companyId, doc);
				}
				catch (Exception e1) {
					_log.error("Reindexing " + article.getPrimaryKey(), e1);
				}
			}
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e2) {
			throw new SystemException(e2);
		}
	}

	public KBArticle revertArticle(
			long userId, long resourcePrimKey, double version,
			PortletPreferences prefs, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		KBArticle oldArticle = getArticle(resourcePrimKey, version);

		return updateArticle(
			userId, resourcePrimKey, 0, oldArticle.getTitle(),
			oldArticle.getContent(), oldArticle.getDescription(), false,
			oldArticle.isTemplate(), oldArticle.getParentResourcePrimKey(),
			null, prefs, themeDisplay);
	}

	public Hits search(
			long companyId, long groupId, String keywords, int start, int end)
		throws SystemException {

		try {
			BooleanQuery contextQuery = BooleanQueryFactoryUtil.create();

			contextQuery.addRequiredTerm(Field.PORTLET_ID, Indexer.PORTLET_ID);

			if (groupId > 0) {
				contextQuery.addRequiredTerm(Field.GROUP_ID, groupId);
			}

			BooleanQuery searchQuery = BooleanQueryFactoryUtil.create();

			if (Validator.isNotNull(keywords)) {
				searchQuery.addTerm(Field.TITLE, keywords);
				searchQuery.addTerm(Field.CONTENT, keywords);
				searchQuery.addTerm(Field.DESCRIPTION, keywords);
				searchQuery.addTerm(Field.TAGS_ENTRIES, keywords);
			}

			BooleanQuery fullQuery = BooleanQueryFactoryUtil.create();

			fullQuery.add(contextQuery, BooleanClauseOccur.MUST);

			if (searchQuery.clauses().size() > 0) {
				fullQuery.add(searchQuery, BooleanClauseOccur.MUST);
			}

			return SearchEngineUtil.search(
				companyId, fullQuery, start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public void subscribe(long userId, long groupId)
		throws PortalException, SystemException {

		subscriptionLocalService.addSubscription(
			userId, KBArticle.class.getName(), groupId);
	}

	public void subscribeArticle(long userId, long resourcePrimKey)
		throws PortalException, SystemException {

		subscriptionLocalService.addSubscription(
			userId, KBArticle.class.getName(), resourcePrimKey);
	}

	public void unsubscribe(long userId, long groupId)
		throws PortalException, SystemException {

		subscriptionLocalService.deleteSubscription(
			userId, KBArticle.class.getName(), groupId);
	}

	public void unsubscribeArticle(long userId, long resourcePrimKey)
		throws PortalException, SystemException {

		subscriptionLocalService.deleteSubscription(
			userId, KBArticle.class.getName(), resourcePrimKey);
	}

	public KBArticle updateArticle(
			long userId, long resourcePrimKey, double version,
			String title, String content, String description, boolean minorEdit,
			boolean template, long parentResourcePrimKey, String[] tagsEntries,
			PortletPreferences prefs, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		// Article

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		KBArticle article = getArticle(resourcePrimKey);

		long groupId = article.getGroupId();

		double oldVersion = article.getVersion();

		if ((version > 0) && (version != oldVersion)) {
			throw new ArticleVersionException();
		}

		article.setHead(false);
		article.setModifiedDate(now);

		kbArticlePersistence.update(article, false);

		double newVersion = MathUtil.format(oldVersion + 0.1, 1, 1);

		long articleId = counterLocalService.increment();

		article = kbArticlePersistence.create(articleId);

		article.setResourcePrimKey(resourcePrimKey);
		article.setCompanyId(user.getCompanyId());
		article.setUserId(user.getUserId());
		article.setUserName(user.getFullName());
		article.setModifiedDate(now);
		article.setGroupId(groupId);
		article.setTitle(title);
		article.setVersion(newVersion);
		article.setMinorEdit(minorEdit);
		article.setContent(content);
		article.setDescription(description);
		article.setHead(true);
		article.setTemplate(template);

		article.setParentResourcePrimKey(parentResourcePrimKey);

		kbArticlePersistence.update(article, false);

		// Subscriptions

		if (!minorEdit && NotificationThreadLocal.isNotificationEnabled()) {
			try {
				notifySubscribers(article, prefs, themeDisplay, true);
			} catch (Exception e) {
				throw new SystemException(e);
			}
		}

		// Tags

		updateTagsAsset(userId, article, tagsEntries);

		// Lucene

		try {
			Indexer.updateArticle(
				article.getCompanyId(), article.getGroupId(), userId,
				resourcePrimKey, article.getTitle(), content, description,
				tagsEntries);
		}
		catch (SearchException se) {
			_log.error("Indexing " + article.getPrimaryKey(), se);
		}

		return article;
	}

	public void updateTagsAsset(
			long userId, KBArticle article, String[] tagsEntries)
		throws PortalException, SystemException {

		String className = KBArticle.class.getName();

		if (article.isTemplate()) {
			className += ".template";
		}

		tagsAssetLocalService.updateAsset(
			userId, article.getGroupId(), className,
			article.getResourcePrimKey(), tagsEntries, null, null, null, null,
			ContentTypes.TEXT_HTML, article.getTitle(), null, null, null, 0, 0,
			null, false);
	}

	public void validateTitle(String title) throws PortalException {
		if (title.equals("all_articles") || title.equals("recent_changes")) {

			throw new ArticleTitleException(title + " is reserved");
		}
	}

	protected boolean isUsedTitle(long groupId, String title)
		throws SystemException {

		if (getArticlesCount(groupId, title, true) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	protected void notifySubscribers(
			KBArticle article, PortletPreferences prefs,
			ThemeDisplay themeDisplay, boolean update)
		throws Exception {

		if (prefs == null) {
			long ownerId = article.getGroupId();
			int ownerType = PortletKeys.PREFS_OWNER_TYPE_GROUP;
			long plid = PortletKeys.PREFS_PLID_SHARED;
			String portletId = KnowledgeBaseKeys.PORTLET_ID;
			String defaultPreferences = null;

			prefs = portletPreferencesLocalService.getPreferences(
				article.getCompanyId(), ownerId, ownerType, plid, portletId,
				defaultPreferences);
		}

		Company company = companyPersistence.findByPrimaryKey(
			article.getCompanyId());

		Group group = groupPersistence.findByPrimaryKey(article.getGroupId());

		User user = userPersistence.findByPrimaryKey(article.getUserId());

		String articleURL = StringPool.BLANK;

		if (themeDisplay != null) {
			String portalURL = PortalUtil.getPortalURL(themeDisplay);
			String layoutURL = PortalUtil.getLayoutURL(themeDisplay);

			articleURL =
				portalURL + layoutURL + StringPool.SLASH + StringPool.DASH +
					StringPool.SLASH +  KnowledgeBaseFriendlyURLMapper.MAPPING +
						StringPool.SLASH + article.getTitle();
		}

		String portletName = PortalUtil.getPortletTitle(
			PortletKeys.WIKI, user);

		String fromName = PrefsPropsUtil.getString(
			company.getCompanyId(), "admin.email.from.name");
		String fromAddress = PrefsPropsUtil.getString(
			company.getCompanyId(), "admin.email.from.address");

		String toName = user.getFullName();
		String toAddress = user.getEmailAddress();

		String subjectPrefix = null;
		String body = null;
		String signature = null;

		ClassLoader classLoader = getClass().getClassLoader();

		if (update) {
			subjectPrefix= StringUtil.read(
				classLoader,
				"com/liferay/knowledgebase/dependencies/" +
					"email_article_updated_subject_prefix.tmpl");
			body = StringUtil.read(
				classLoader,
				"com/liferay/knowledgebase/dependencies/" +
					"email_article_updated_body.tmpl");
			signature = StringUtil.read(
				classLoader,
				"com/liferay/knowledgebase/dependencies/" +
					"email_article_updated_signature.tmpl");
		}
		else {
			subjectPrefix= StringUtil.read(
				classLoader,
				"com/liferay/knowledgebase/dependencies/" +
					"email_article_added_subject_prefix.tmpl");
			body = StringUtil.read(
				classLoader,
				"com/liferay/knowledgebase/dependencies/" +
					"email_article_added_body.tmpl");
			signature = StringUtil.read(
				classLoader,
				"com/liferay/knowledgebase/dependencies/" +
					"email_article_added_signature.tmpl");
		}

		if (Validator.isNotNull(signature)) {
			body +=  "\n--\n" + signature;
		}

		subjectPrefix = StringUtil.replace(
			subjectPrefix,
			new String[] {
				"[$COMPANY_ID$]",
				"[$COMPANY_MX$]",
				"[$COMPANY_NAME$]",
				"[$COMMUNITY_NAME$]",
				"[$FROM_ADDRESS$]",
				"[$FROM_NAME$]",
				"[$ARTICLE_CONTENT$]",
				"[$ARTICLE_DESCRIPTION$]",
				"[$ARTICLE_ID$]",
				"[$ARTICLE_TITLE$]",
				"[$ARTICLE_USER_ADDRESS$]",
				"[$ARTICLE_USER_NAME$]",
				"[$PORTAL_URL$]",
				"[$PORTLET_NAME$]"
			},
			new String[] {
				String.valueOf(company.getCompanyId()),
				company.getMx(),
				company.getName(),
				group.getName(),
				fromAddress,
				fromName,
				article.getContent(),
				article.getDescription(),
				String.valueOf(article.getArticleId()),
				article.getTitle(),
				user.getEmailAddress(),
				user.getFullName(),
				company.getVirtualHost(),
				portletName
			});

		body = StringUtil.replace(
			body,
			new String[] {
				"[$COMPANY_ID$]",
				"[$COMPANY_MX$]",
				"[$COMPANY_NAME$]",
				"[$COMMUNITY_NAME$]",
				"[$FROM_ADDRESS$]",
				"[$FROM_NAME$]",
				"[$ARTICLE_CONTENT$]",
				"[$ARTICLE_DESCRIPTION$]",
				"[$ARTICLE_ID$]",
				"[$ARTICLE_TITLE$]",
				"[$ARTICLE_URL$]",
				"[$ARTICLE_USER_ADDRESS$]",
				"[$ARTICLE_USER_NAME$]",
				"[$PORTAL_URL$]",
				"[$PORTLET_NAME$]"
			},
			new String[] {
				String.valueOf(company.getCompanyId()),
				company.getMx(),
				company.getName(),
				group.getName(),
				fromAddress,
				fromName,
				article.getContent(),
				article.getDescription(),
				String.valueOf(article.getArticleId()),
				article.getTitle(),
				articleURL,
				user.getEmailAddress(),
				user.getFullName(),
				company.getVirtualHost(),
				portletName
			});

		String subject = article.getTitle();

		if (subject.indexOf(subjectPrefix) == -1) {
			subject = subjectPrefix + subject;
		}

		InternetAddress from = new InternetAddress(fromAddress, fromName);

		InternetAddress to = new InternetAddress(toAddress, toName);

		MailMessage mailMessage = new MailMessage(
			from, to, subject, body, true);

		MailServiceUtil.sendEmail(mailMessage);
	}

	protected void validate(String title) throws PortalException {

		if (Validator.isNull(title)) {
			throw new ArticleTitleException();
		}

		validateTitle(title);
	}

	private static Log _log = LogFactory.getLog(
		KBArticleLocalServiceImpl.class);

}