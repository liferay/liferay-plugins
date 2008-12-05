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

package com.liferay.kb.knowledgebase.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.documentlibrary.DuplicateDirectoryException;
import com.liferay.documentlibrary.DuplicateFileException;
import com.liferay.documentlibrary.NoSuchDirectoryException;
import com.liferay.documentlibrary.NoSuchFileException;
import com.liferay.documentlibrary.service.DLServiceUtil;
import com.liferay.kb.knowledgebase.ArticleTitleException;
import com.liferay.kb.knowledgebase.ArticleVersionException;
import com.liferay.kb.knowledgebase.KnowledgeBaseKeys;
import com.liferay.kb.knowledgebase.NoSuchArticleException;
import com.liferay.kb.knowledgebase.model.KBArticle;
import com.liferay.kb.knowledgebase.model.impl.KBArticleImpl;
import com.liferay.kb.knowledgebase.portlet.KnowledgeBaseFriendlyURLMapper;
import com.liferay.kb.knowledgebase.service.base.KBArticleLocalServiceBaseImpl;
import com.liferay.kb.knowledgebase.util.Indexer;
import com.liferay.kb.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.kb.knowledgebase.util.comparator.ArticleModifiedDateComparator;
import com.liferay.kb.util.DocBookEntityResolver;
import com.liferay.kb.util.PortletPropsKeys;
import com.liferay.kb.util.Section;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.ByteArrayMaker;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.NotificationThreadLocal;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.tags.service.TagsAssetLocalServiceUtil;
import com.liferay.portlet.tags.service.TagsEntryLocalServiceUtil;
import com.liferay.util.MathUtil;

import java.io.File;
import java.io.Reader;
import java.io.StringReader;

import java.net.URL;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.mail.internet.InternetAddress;

import javax.portlet.PortletPreferences;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * <a href="KBArticleLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 * @author Peter Shin
 *
 */
public class KBArticleLocalServiceImpl extends KBArticleLocalServiceBaseImpl {

	public KBArticle addArticle(
			long userId, long groupId, String title, String content,
			String description, boolean minorEdit, boolean template,
			boolean draft, long parentResourcePrimKey, String[] tagsEntries,
			String[] categoriesEntries, PortletPreferences prefs,
			ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		String uuid = null;
		String htmlTitle = null;
		double version = KBArticleImpl.DEFAULT_VERSION;
		boolean head = true;

		return addArticle(
			uuid, userId, groupId, title, htmlTitle, version, content,
			description, minorEdit, head, template, draft,
			parentResourcePrimKey, tagsEntries, categoriesEntries, prefs,
			themeDisplay);
	}

	public KBArticle addArticle(
			long userId, long groupId, String title, String htmlTitle,
			String content, String description, boolean minorEdit,
			boolean template, boolean draft, long parentResourcePrimKey,
			String[] tagsEntries, String[] categoriesEntries,
			PortletPreferences prefs, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		String uuid = null;
		double version = KBArticleImpl.DEFAULT_VERSION;
		boolean head = true;

		return addArticle(
			uuid, userId, groupId, title, htmlTitle, version, content,
			description, minorEdit, head, template, draft,
			parentResourcePrimKey, tagsEntries, categoriesEntries, prefs,
			themeDisplay);
	}

	public KBArticle addArticle(
			String uuid, long userId, long groupId, String title,
			String htmlTitle, double version, String content,
			String description, boolean minorEdit, boolean head,
			boolean template, boolean draft, long parentResourcePrimKey,
			String[] tagsEntries, String[] categoriesEntries,
			PortletPreferences prefs, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		// Article

		User user = UserLocalServiceUtil.getUser(userId);

		Date now = new Date();

		validate(title);

		int count = kbArticlePersistence.countByG_T(groupId, title);

		if (count > 0) {
			throw new ArticleTitleException(
				"The title " + title + " is already being used");
		}

		if (htmlTitle == null) {
			htmlTitle = title;
		}

		long articleId = CounterLocalServiceUtil.increment();

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
		article.setHtmlTitle(htmlTitle);
		article.setVersion(version);
		article.setMinorEdit(minorEdit);
		article.setContent(content);
		article.setDescription(description);
		article.setHead(head);
		article.setTemplate(template);
		article.setDraft(draft);
		article.setParentResourcePrimKey(parentResourcePrimKey);

		kbArticlePersistence.update(article, false);

		// Resources

		addArticleResources(article.getGroupId(), article, true, true);

		// Subscriptions

		if (!minorEdit && !draft
				&& NotificationThreadLocal.isNotificationEnabled()) {

			try {
				notifySubscribers(article, prefs, themeDisplay, false);
			}
			catch (Exception e) {
				throw new SystemException(e);
			}
		}

		// Tags

		updateTagsAsset(userId, article, tagsEntries, categoriesEntries);

		// Lucene

		if (!draft) {
			try {
				Indexer.addArticle(
					article.getCompanyId(), groupId, userId, resourcePrimKey,
					title, content, description, tagsEntries);
			}
			catch (SearchException se) {
				_log.error("Indexing " + articleId, se);
			}
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
			DLServiceUtil.addDirectory(companyId, repositoryId, dirName);
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
				DLServiceUtil.addFile(
					companyId, portletId, article.getGroupId(),
					repositoryId, dirName + "/" + fileName,
					0, StringPool.BLANK, new String[0], bytes);
			}
			catch (DuplicateFileException dfe) {
			}
		}
	}

	public void addArticleResources(
			long groupId, KBArticle article, boolean addCommunityPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		ResourceLocalServiceUtil.addResources(
			article.getCompanyId(), groupId, article.getUserId(),
			KBArticle.class.getName(), article.getResourcePrimKey(), false,
			addCommunityPermissions, addGuestPermissions);
	}

	public void addArticleResources(
			long groupId, KBArticle article, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		ResourceLocalServiceUtil.addModelResources(
			article.getCompanyId(), groupId, article.getUserId(),
			KBArticle.class.getName(), article.getResourcePrimKey(),
			communityPermissions, guestPermissions);
	}

	public void deleteArticle(long resourcePrimKey)
		throws PortalException, SystemException {

		// Children

		List<KBArticle> children =
			kbArticlePersistence.findByParentResourcePrimKey(resourcePrimKey);

		for (KBArticle curArticle : children) {
			deleteArticle(curArticle.getResourcePrimKey());
		}

		List<KBArticle> articles = kbArticlePersistence.findByR_H(
			resourcePrimKey, true, 0, 1);

		if (articles.size() > 0) {
			KBArticle article = articles.iterator().next();

			deleteArticle(article);
		}
	}

	public void deleteArticle(KBArticle article)
		throws PortalException, SystemException {

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
			DLServiceUtil.deleteDirectory(
				companyId, portletId, repositoryId, dirName);
		}
		catch (NoSuchDirectoryException nsde) {
		}

		// Tags

		TagsAssetLocalServiceUtil.deleteAsset(
			KBArticle.class.getName(), article.getResourcePrimKey());

		// Subscriptions

		SubscriptionLocalServiceUtil.deleteSubscriptions(
			article.getCompanyId(), KBArticle.class.getName(),
			article.getArticleId());

		// Feedback

		kbFeedbackEntryLocalService.deleteArticleFeedbackEntries(
			article.getResourcePrimKey());

		// Message boards

		MBMessageLocalServiceUtil.deleteDiscussionMessages(
			KBArticle.class.getName(), article.getResourcePrimKey());

		// Resources

		ResourceLocalServiceUtil.deleteResource(
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
			DLServiceUtil.deleteFile(companyId, portletId, repositoryId, fileName);
		}
		catch (NoSuchFileException nsfe) {
		}
	}

	public void deleteGroupArticles(long groupId, boolean template)
		throws PortalException, SystemException {

		Iterator<KBArticle> itr = kbArticlePersistence.findByG_H_T(
			groupId, true, template).iterator();

		while (itr.hasNext()) {
			KBArticle article = itr.next();

			deleteArticle(article);
		}
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

	public int getArticlesCount(long resourcePrimKey) throws SystemException {

		return kbArticlePersistence.countByResourcePrimKey(resourcePrimKey);
	}

	public List<KBArticle> getCompanyArticles(
			long companyId, boolean head, boolean template, boolean draft,
			int start, int end)
		throws SystemException {

		return kbArticlePersistence.findByC_H_T_D(
			companyId, head, template, draft, start, end,
			new ArticleModifiedDateComparator(false));
	}

	public List<KBArticle> getChildArticles(long parentResourcePrimKey)
		throws SystemException {

		return kbArticlePersistence.findByP_H_T_D(
			parentResourcePrimKey, true, false, false);
	}

	public List<KBArticle> getGroupArticles(
			long groupId, boolean head, boolean template, int start, int end)
		throws SystemException {

		return kbArticlePersistence.findByG_H_T(
			groupId, head, template, start, end,
			new ArticleModifiedDateComparator(false));
	}

	public List<KBArticle> getGroupArticles(
			long groupId, boolean head, boolean template, boolean draft)
		throws SystemException {

		return kbArticlePersistence.findByG_H_T_D(
			groupId, head, template, draft, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new ArticleModifiedDateComparator(false));
	}

	public List<KBArticle> getGroupArticles(
			long groupId, boolean head, boolean template, boolean draft,
			int start, int end)
		throws SystemException {

		return kbArticlePersistence.findByG_H_T_D(
			groupId, head, template, draft, start, end,
			new ArticleModifiedDateComparator(false));
	}

	public List<KBArticle> getGroupArticles(
			long groupId, String title, boolean head, int start, int end)
		throws SystemException {

		return kbArticlePersistence.findByG_T_H(
			groupId, title, head, start, end,
			new ArticleModifiedDateComparator(false));
	}

	public List<KBArticle> getGroupArticlesIncludingUserDrafts(
			long groupId, boolean template, long userId)
		throws SystemException {

		return getGroupArticlesIncludingUserDrafts(
			groupId, template, userId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	public List<KBArticle> getGroupArticlesIncludingUserDrafts(
			long groupId, boolean template, long userId, int start, int end)
		throws SystemException {

		long parentResourcePrimKey = KBArticleImpl.DEFAULT_PARENT;

		return kbArticleFinder.findByG_P_T_Or_G_P_T_U(
			groupId, parentResourcePrimKey, template, userId, start, end);
	}

	public List<KBArticle> getGroupsArticles(
			long[] groupIds, int start, int end)
		throws SystemException {

		return kbArticleFinder.findByGroupIds(
			groupIds, start, end, new ArticleModifiedDateComparator(false));
	}

	public int getGroupArticlesCount(long groupId, boolean head, boolean template)
		throws SystemException {

		return kbArticlePersistence.countByG_H_T(groupId, head, template);
	}

	public int getGroupArticlesCount(long groupId, String title, boolean head)
		throws SystemException {

		return kbArticlePersistence.countByG_T_H(groupId, title, head);
	}

	public int getGroupArticlesCount(
			long groupId, boolean head, boolean template, boolean draft)
		throws SystemException {

		return kbArticlePersistence.countByG_H_T_D(
			groupId, head, template, draft);
	}

	public int getGroupArticlesIncludingUserDraftsCount(
			long groupId, boolean template, long userId)
		throws SystemException {

		return kbArticleFinder.countByG_P_T_Or_G_P_T_U(groupId, template, userId);
	}

	public int getGroupsArticlesCount(long[] groupIds)
		throws SystemException {

		return kbArticleFinder.countByGroupIds(groupIds);
	}

	public List<KBArticle> getSubscribedArticles(long userId, long groupId)
		throws SystemException {

		return kbArticleFinder.findByS_U_G(userId, groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	public List<KBArticle> getSubscribedArticles(
			long userId, long groupId, int start, int end)
		throws SystemException {

		return kbArticleFinder.findByS_U_G(userId, groupId, start, end);
	}

	public int getSubscribedArticlesCount(long userId, long groupId)
		throws SystemException {

		return kbArticleFinder.countByS_U_G(userId, groupId);
	}

	public void importDocbook(
			long userId, long groupId, File file, PortletPreferences prefs,
			ThemeDisplay themeDisplay)
		throws Exception {

		ZipReader zipReader = new ZipReader(file);

		Map folderEntries  = zipReader.getFolderEntries();
		Set<Entry<String, byte[]>> entries  = zipReader.getEntries().entrySet();

		String indexPath = getIndexPath(folderEntries, entries);

		if (indexPath == null) {
			_log.debug("No index file found.");

			return;
		}

		// Liferay's SaxReader abstraction couldn't be used because it doesn't
		// support setEntityResolver method

		SAXReader reader = new SAXReader(false);

		String rootPath = new File(indexPath).getParent();

		reader.setEntityResolver(
			new DocBookEntityResolver(rootPath, zipReader));

		String indexContents = zipReader.getEntryAsString(indexPath);

		org.dom4j.Document doc = reader.read(new StringReader(indexContents));

		Element bookEl = doc.getRootElement();

		String htmlTitle = bookEl.element("bookinfo").elementText("title");

		String title = bookEl.attributeValue("id");

		KBArticle bookArticle = addArticle(
			userId, groupId, title, htmlTitle, StringPool.BLANK,
			StringPool.BLANK, true, false, false, KBArticleImpl.DEFAULT_PARENT,
			null, null, prefs, themeDisplay);

		List<Element> glossaries = bookEl.elements("glossary");

		for (Element glossaryEl : glossaries) {
			Section section = getSection(glossaryEl, true);

			title = validateTitle(groupId, section.getId());
			htmlTitle = section.getTitle();

			addArticle(
				userId, groupId, title, htmlTitle, section.getContent(),
				section.getSubTitle(), true, false, false,
				bookArticle.getResourcePrimKey(), null, null, prefs,
				themeDisplay);
		}

		List<Element> chaptersEl = bookEl.elements("chapter");

		for (Element chapterEl : chaptersEl) {
			Section section = getSection(chapterEl, false);

			title = validateTitle(groupId, section.getId());
			htmlTitle = section.getTitle();

			KBArticle chapterArticle = addArticle(
				userId, groupId, title, htmlTitle, section.getContent(),
				section.getDescription(), true, false, false,
				bookArticle.getResourcePrimKey(), null, null, prefs,
				themeDisplay);

			List<Element> sections = chapterEl.elements("section");

			for (Element sectionEl : sections) {
				section = getSection(sectionEl, false);

				title = validateTitle(groupId, section.getId());
				htmlTitle = section.getTitle();

				KBArticle sectionArticle = addArticle(
					userId, groupId, title, htmlTitle, section.getContent(),
					StringPool.BLANK, true, false, false,
					chapterArticle.getResourcePrimKey(), null, null, prefs,
					themeDisplay);

				List<Element> subSections = sectionEl.elements("section");

				for (Element subSectionEl : subSections) {
					section = getSection(subSectionEl, true);

					title = validateTitle(groupId, section.getId());
					htmlTitle = section.getTitle();

					addArticle(
						userId, groupId, title, htmlTitle, section.getContent(),
						section.getDescription(), true, false,  false,
						sectionArticle.getResourcePrimKey(), null, null, prefs,
						themeDisplay);
				}
			}
		}

	}

	public void reIndex(String[] ids) throws SystemException {
		if (SearchEngineUtil.isIndexReadOnly()) {
			return;
		}

		long companyId = GetterUtil.getLong(ids[0]);

		try {
			Iterator<KBArticle> articlesItr =
				kbArticlePersistence.findByC_D(companyId, false).iterator();

			while (articlesItr.hasNext()) {
				KBArticle article = articlesItr.next();

				long groupId = article.getGroupId();
				long userId = article.getUserId();
				long resourcePrimKey = article.getResourcePrimKey();
				String title = article.getTitle();
				String content = article.getContent();
				String description = article.getDescription();

				String[] tagsEntries = TagsEntryLocalServiceUtil.getEntryNames(
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

		KBArticle article = getArticle(resourcePrimKey);
		KBArticle oldArticle = getArticle(resourcePrimKey, version);

		return updateArticle(
			userId, resourcePrimKey, 0, oldArticle.getTitle(),
			oldArticle.getContent(), oldArticle.getDescription(),
			false, oldArticle.isTemplate(), article.getDraft(),
			oldArticle.getParentResourcePrimKey(), null, null, prefs,
			themeDisplay);
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

		SubscriptionLocalServiceUtil.addSubscription(
			userId, KBArticle.class.getName(), groupId);
	}

	public void subscribeArticle(long userId, long resourcePrimKey)
		throws PortalException, SystemException {

		SubscriptionLocalServiceUtil.addSubscription(
			userId, KBArticle.class.getName(), resourcePrimKey);
	}

	public void unsubscribe(long userId, long groupId)
		throws PortalException, SystemException {

		SubscriptionLocalServiceUtil.deleteSubscription(
			userId, KBArticle.class.getName(), groupId);
	}

	public void unsubscribeArticle(long userId, long resourcePrimKey)
		throws PortalException, SystemException {

		SubscriptionLocalServiceUtil.deleteSubscription(
			userId, KBArticle.class.getName(), resourcePrimKey);
	}

	public KBArticle updateArticle(
			long userId, long resourcePrimKey, double version,
			String title, String content, String description, boolean minorEdit,
			boolean template, boolean draft, long parentResourcePrimKey,
			String[] tagsEntries, String[] categoriesEntries,
			PortletPreferences prefs, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		String htmlTitle = null;

		return updateArticle(
			userId, resourcePrimKey, version, title, htmlTitle, content,
			description, minorEdit, template, draft, parentResourcePrimKey,
			tagsEntries, categoriesEntries, prefs, themeDisplay);
	}

	public KBArticle updateArticle(
			long userId, long resourcePrimKey, double version,
			String title, String htmlTitle, String content, String description,
			boolean minorEdit, boolean template, boolean draft,
			long parentResourcePrimKey, String[] tagsEntries,
			String[] categoriesEntries, PortletPreferences prefs,
			ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		// Article

		User user = UserLocalServiceUtil.getUser(userId);

		Date now = new Date();

		KBArticle article = getArticle(resourcePrimKey);

		long groupId = article.getGroupId();

		double oldVersion = article.getVersion();

		if ((version > 0) && (version != oldVersion)) {
			throw new ArticleVersionException();
		}

		if (htmlTitle == null) {
			htmlTitle = title;
		}

		article.setHead(false);
		article.setModifiedDate(now);

		kbArticlePersistence.update(article, false);

		double newVersion = MathUtil.format(oldVersion + 0.1, 1, 1);

		long articleId = CounterLocalServiceUtil.increment();

		article = kbArticlePersistence.create(articleId);

		article.setResourcePrimKey(resourcePrimKey);
		article.setCompanyId(user.getCompanyId());
		article.setUserId(user.getUserId());
		article.setUserName(user.getFullName());
		article.setModifiedDate(now);
		article.setGroupId(groupId);
		article.setTitle(title);
		article.setHtmlTitle(htmlTitle);
		article.setVersion(newVersion);
		article.setMinorEdit(minorEdit);
		article.setContent(content);
		article.setDescription(description);
		article.setHead(true);
		article.setTemplate(template);
		article.setDraft(draft);
		article.setParentResourcePrimKey(parentResourcePrimKey);

		kbArticlePersistence.update(article, false);

		// Subscriptions

		if (!minorEdit && !draft
				&& NotificationThreadLocal.isNotificationEnabled()) {

			try {
				notifySubscribers(article, prefs, themeDisplay, true);
			}
			catch (Exception e) {
				throw new SystemException(e);
			}
		}

		// Tags

		updateTagsAsset(userId, article, tagsEntries, categoriesEntries);

		// Lucene

		if (!draft) {
			try {
				Indexer.updateArticle(
					article.getCompanyId(), article.getGroupId(), userId,
					resourcePrimKey, article.getTitle(), content, description,
					tagsEntries);
			}
			catch (SearchException se) {
				_log.error("Indexing " + article.getPrimaryKey(), se);
			}
		}

		return article;
	}

	public void updateTagsAsset(
			long userId, KBArticle article, String[] tagsEntries,
			String[] categoriesEntries)
		throws PortalException, SystemException {

		String className = KBArticle.class.getName();

		if (article.isTemplate()) {
			className += ".template";
		}

		TagsAssetLocalServiceUtil.updateAsset(
			userId, article.getGroupId(), className,
			article.getResourcePrimKey(), categoriesEntries, tagsEntries, null,
			null, null, null, ContentTypes.TEXT_HTML, article.getTitle(), null,
			null, null, 0, 0, null, false);
	}

	public void validateTitle(String title) throws PortalException {
		if (title.equals("all_articles") || title.equals("recent_changes")) {

			throw new ArticleTitleException(title + " is reserved");
		}
	}

	protected String getIndexPath(
			Map folderEntries, Set<Entry<String, byte[]>> entries) {

		for (Entry<String, byte[]> entry : entries) {
			String indexPath = entry.getKey();

			if (!folderEntries.containsKey(indexPath)) {
				if (!indexPath.endsWith(".xml")) {
					if (_log.isDebugEnabled()) {
						_log.debug("Not a docbook: " + indexPath);
					}

					continue;
				}

				File parent = new File(indexPath).getParentFile();

				if ((parent != null) && (parent.getParent() == null)) {
					return indexPath;
				}
			}

		}

		return null;
	}

	protected Section getSection(Element el, boolean htmlContent) {
		String id = el.attributeValue("id");
		String title = el.elementText("title");
		String subTitle = el.elementText("subtitle");
		String description = el.elementText("para");
		String content = StringPool.BLANK;

		if (htmlContent) {
			try {
				content = transform(new StringReader(el.asXML()));
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return new Section(id, title, subTitle, description, content);
	}

	protected boolean isUsedTitle(long groupId, String title)
		throws SystemException {

		if (getGroupArticlesCount(groupId, title, true) > 0) {
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

			prefs = PortletPreferencesLocalServiceUtil.getPreferences(
				article.getCompanyId(), ownerId, ownerType, plid, portletId,
				defaultPreferences);
		}

		Company company = CompanyLocalServiceUtil.getCompany(
			article.getCompanyId());

		Group group = GroupLocalServiceUtil.getGroup(article.getGroupId());

		User user = UserLocalServiceUtil.getUser(article.getUserId());

		String groupName = KnowledgeBaseUtil.getGroupName(group);

		String articleURL = StringPool.BLANK;

		String portletName = "Knowledge Base";

		if (themeDisplay != null) {
			String portalURL = PortalUtil.getPortalURL(themeDisplay);
			String layoutURL = PortalUtil.getLayoutURL(themeDisplay);

			articleURL =
				portalURL + layoutURL + StringPool.SLASH + StringPool.DASH +
				StringPool.SLASH + KnowledgeBaseFriendlyURLMapper.MAPPING +
				StringPool.SLASH + article.getTitle();

			portletName =
				LanguageUtil.get(themeDisplay.getLocale(), "category.kb");
		}

		String fromName = PrefsPropsUtil.getString(
			company.getCompanyId(), PortletPropsKeys.ADMIN_EMAIL_FROM_NAME);
		String fromAddress = PrefsPropsUtil.getString(
			company.getCompanyId(), PortletPropsKeys.ADMIN_EMAIL_FROM_ADDRESS);

		String toName = user.getFullName();
		String toAddress = user.getEmailAddress();

		ClassLoader classLoader = getClass().getClassLoader();

		String subject = KnowledgeBaseUtil.getEmailSubject(
			company.getCompanyId(), classLoader, update);
		String body = KnowledgeBaseUtil.getEmailBody(
			company.getCompanyId(), classLoader, update);

		subject = StringUtil.replace(
			subject,
			new String[] {
				"[$ARTICLE_CONTENT$]",
				"[$ARTICLE_DESCRIPTION$]",
				"[$ARTICLE_TITLE$]",
				"[$ARTICLE_URL$]",
				"[$COMMUNITY_NAME$]",
				"[$COMPANY_NAME$]",
				"[$FROM_ADDRESS$]",
				"[$FROM_NAME$]",
				"[$PORTAL_URL$]",
				"[$PORTLET_NAME$]",
				"[$TO_NAME$]",
				"[$TO_ADDRESS$]"
			},
			new String[] {
				article.getContent(),
				article.getDescription(),
				article.getTitle(),
				articleURL,
				groupName,
				company.getName(),
				fromAddress,
				fromName,
				company.getVirtualHost(),
				portletName,
				user.getFullName(),
				user.getEmailAddress()
			});

		body = StringUtil.replace(
			body,
			new String[] {
				"[$ARTICLE_CONTENT$]",
				"[$ARTICLE_DESCRIPTION$]",
				"[$ARTICLE_TITLE$]",
				"[$ARTICLE_URL$]",
				"[$COMMUNITY_NAME$]",
				"[$COMPANY_NAME$]",
				"[$FROM_ADDRESS$]",
				"[$FROM_NAME$]",
				"[$PORTAL_URL$]",
				"[$PORTLET_NAME$]",
				"[$TO_NAME$]",
				"[$TO_ADDRESS$]"
			},
			new String[] {
				article.getContent(),
				article.getDescription(),
				article.getTitle(),
				articleURL,
				groupName,
				company.getName(),
				fromAddress,
				fromName,
				company.getVirtualHost(),
				portletName,
				user.getFullName(),
				user.getEmailAddress()
			});

		InternetAddress from = new InternetAddress(fromAddress, fromName);

		InternetAddress to = new InternetAddress(toAddress, toName);

		MailMessage mailMessage = new MailMessage(
			from, to, subject, body, true);

		MailServiceUtil.sendEmail(mailMessage);
	}

	protected String transform(Reader reader) throws Exception {
		StreamSource xmlSource = new StreamSource(reader);

		ByteArrayMaker bam = new ByteArrayMaker();

		getTransformer().transform(xmlSource, new StreamResult(bam));

		return bam.toString();
	}

	protected void validate(String title) throws PortalException {

		if (Validator.isNull(title)) {
			throw new ArticleTitleException();
		}

		validateTitle(title);
	}

	protected String validateTitle(long groupId, String title)
		throws SystemException {

		return validateTitle(groupId, title, 1);
	}

	protected String validateTitle(long groupId, String title, int suffix)
		throws SystemException {

		int count = kbArticlePersistence.countByG_T(groupId, title);

		if (count == 0) {
			return title;
		}

		if (Pattern.matches(".* \\(\\d+\\)", title)) {
			int pos = title.lastIndexOf(" (");

			title = title.substring(0, pos);
		}

		StringBuilder sb = new StringBuilder();

		sb.append(title);
		sb.append(StringPool.SPACE);
		sb.append(StringPool.OPEN_PARENTHESIS);
		sb.append(suffix);
		sb.append(StringPool.CLOSE_PARENTHESIS);

		title = sb.toString();

		return validateTitle(groupId, title, ++suffix);
	}

	protected Transformer getTransformer() throws Exception {
		if (_transformer == null) {
			TransformerFactory factory = TransformerFactory.newInstance();

			String xslPath = "../../dependencies/xsl/html/docbook.xsl";

			URL url = getClass().getResource(xslPath);

			String xslContent = StringUtil.read(
				getClass().getResourceAsStream(xslPath));

			Source source = new StreamSource(xslContent);
			source.setSystemId(url.getFile());
			_transformer = factory.newTransformer(source);
		}

		return _transformer;
	}

	private static Log _log = LogFactory.getLog(
		KBArticleLocalServiceImpl.class);

	private Transformer _transformer;

}