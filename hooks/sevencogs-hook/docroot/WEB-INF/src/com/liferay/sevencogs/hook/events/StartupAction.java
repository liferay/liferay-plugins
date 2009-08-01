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

package com.liferay.sevencogs.hook.events;

import com.liferay.documentlibrary.DuplicateFileException;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.OrganizationConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.PermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;
import com.liferay.portlet.imagegallery.model.IGFolder;
import com.liferay.portlet.imagegallery.model.IGImage;
import com.liferay.portlet.imagegallery.service.IGFolderLocalServiceUtil;
import com.liferay.portlet.imagegallery.service.IGImageLocalServiceUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalStructure;
import com.liferay.portlet.journal.model.JournalTemplate;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalStructureLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalTemplateLocalServiceUtil;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.social.model.SocialRequest;
import com.liferay.portlet.social.model.SocialRequestConstants;
import com.liferay.portlet.social.service.SocialRequestLocalServiceUtil;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiNodeLocalServiceUtil;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * <a href="StartupAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class StartupAction extends SimpleAction {

	public void run(String[] ids) throws ActionException {
		try {
			doRun(GetterUtil.getLong(ids[0]));
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected BlogsEntry addBlogsEntry(
			long userId, String title, String fileName,
			ServiceContext serviceContext)
		throws Exception {

		String content = getString("/blogs/" + fileName);

		return BlogsEntryLocalServiceUtil.addEntry(
			userId, title, content, 1, 1, 2008, 0,0, false, true, new String[0],
			serviceContext);
	}

	protected DLFileEntry addDLFileEntry(
			long userId, long folderId, String name, String title,
			String description)
		throws Exception {

		byte[] bytes = getBytes("/document_library/" + name);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddCommunityPermissions(true);
		serviceContext.setAddGuestPermissions(false);

		try {
			return DLFileEntryLocalServiceUtil.addFileEntry(
				userId, folderId, name, title, description, StringPool.BLANK,
				bytes, serviceContext);
		}
		catch (DuplicateFileException dfe) {
			return DLFileEntryLocalServiceUtil.updateFileEntry(
				userId, folderId, folderId, name, null, title, description,
				StringPool.BLANK, bytes, serviceContext);
		}
	}

	protected DLFolder addDLFolder(
			long userId, long groupId, String name, String description)
		throws Exception {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddCommunityPermissions(true);
		serviceContext.setAddGuestPermissions(false);

		return DLFolderLocalServiceUtil.addFolder(
			userId, groupId, 0, name, description, serviceContext);
	}

	protected IGImage addIGImage(
			long userId, long folderId, String name,
			ServiceContext serviceContext)
		throws Exception {

		InputStream is = getInputStream("/images/" + name);

		return IGImageLocalServiceUtil.addImage(
			userId, folderId, name, StringPool.BLANK, name, is, "image/png",
			serviceContext);
	}

	protected JournalArticle addJournalArticle(
			long userId, long groupId, String title, String fileName)
		throws Exception {

		return addJournalArticle(
			userId, groupId, title, fileName, StringPool.BLANK,
			StringPool.BLANK);
	}

	protected JournalArticle addJournalArticle(
			long userId, long groupId, String title, String fileName,
			String structureId, String templateId)
		throws Exception {

		String content = getString("/journal/articles/" + fileName);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddCommunityPermissions(false);
		serviceContext.setAddGuestPermissions(false);

		JournalArticle journalArticle =
			JournalArticleLocalServiceUtil.addArticle(
				userId, groupId, StringPool.BLANK, true, title,
				StringPool.BLANK, content, "general", structureId, templateId,
				1, 1, 2008, 0, 0, 0, 0, 0, 0, 0, true, 0, 0, 0, 0, 0, true,
				true, false, StringPool.BLANK, null, null, StringPool.BLANK,
				serviceContext);

		JournalArticleLocalServiceUtil.approveArticle(
			userId, groupId, journalArticle.getArticleId(),
			journalArticle.getVersion(), StringPool.BLANK, serviceContext);

		return journalArticle;
	}

	protected JournalStructure addJournalStructure(long userId, long groupId)
		throws Exception {

		String xsd = getString("/journal/structures/single_image.xml");

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddCommunityPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		return JournalStructureLocalServiceUtil.addStructure(
			userId, groupId, "SINGLE-IMAGE", false, StringPool.BLANK,
			"Single Image", "A single image, optional link", xsd,
			serviceContext);
	}

	protected JournalTemplate addJournalTemplate(long userId, long groupId)
		throws Exception {

		String xsl = getString("/journal/templates/single_image.xml");

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddCommunityPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		return JournalTemplateLocalServiceUtil.addTemplate (
			userId, groupId, "SINGLE-IMAGE", false, "SINGLE-IMAGE",
			"Single Image", "A single image, optional URL", xsl, true, "vm",
			true, false, StringPool.BLANK,	null, serviceContext) ;
	}

	protected Layout addLayout(
			Group group, String name, boolean privateLayout, String friendlyURL,
			String layouteTemplateId)
		throws Exception {

		ServiceContext serviceContext = new ServiceContext();

		Layout layout = LayoutLocalServiceUtil.addLayout(
			group.getCreatorUserId(), group.getGroupId(), privateLayout,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, name, StringPool.BLANK,
			StringPool.BLANK, LayoutConstants.TYPE_PORTLET, false, friendlyURL,
			serviceContext);

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(0, layouteTemplateId, false);

		return layout;
	}

	protected MBCategory addMBCategory(
			long userId, String name, String description,
			ServiceContext serviceContext)
		throws Exception {

		return MBCategoryLocalServiceUtil.addCategory(
			userId, 0, name, description, StringPool.BLANK, StringPool.BLANK,
			StringPool.BLANK, 0, false, StringPool.BLANK, StringPool.BLANK, 1,
			StringPool.BLANK, false, StringPool.BLANK, 0, false,
			StringPool.BLANK, StringPool.BLANK, false, serviceContext);
	}

	protected MBMessage addMBMessage(
			long userId, String userName, long categoryId, long threadId,
			long parentMessageId, String subject, String fileName,
			ServiceContext serviceContext)
		throws Exception {

		String body = getString("/message_boards/" + fileName);

		return MBMessageLocalServiceUtil.addMessage(
			userId, userName, categoryId, threadId, parentMessageId,
			subject, body, new ArrayList(), false, -1.0, serviceContext);
	}

	public String addPortletId(Layout layout, String portletId, String columnId)
		throws Exception {

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		portletId = layoutTypePortlet.addPortletId(
			0, portletId, columnId, -1, false);

		updateLayout(layout);

		return portletId;
	}

	protected void addSocialRequest(
			User user, User receiverUser, boolean confirm)
		throws Exception {

		SocialRequest socialRequest = SocialRequestLocalServiceUtil.addRequest(
			user.getUserId(), 0, User.class.getName(), user.getUserId(),
			_WOL_FRIENDS_REQUEST_KEYS_ADD_FRIEND, StringPool.BLANK,
			receiverUser.getUserId());

		if (confirm) {
			SocialRequestLocalServiceUtil.updateRequest(
				socialRequest.getRequestId(),
				SocialRequestConstants.STATUS_CONFIRM, new ThemeDisplay());
		}
	}

	protected User addUser(
			long companyId, String screenName, String firstName,
			String lastName, boolean male, String jobTitle, long[] roleIds)
		throws Exception {

		long creatorUserId = 0;
		boolean autoPassword = false;
		String password1 = screenName;
		String password2 = password1;
		boolean autoScreenName = false;
		String emailAddress = screenName + "@7cogs.com";
		String openId = StringPool.BLANK;
		Locale locale = Locale.US;
		String middleName = StringPool.BLANK;
		int prefixId = 0;
		int suffixId = 0;
		int birthdayMonth = Calendar.JANUARY;
		int birthdayDay = 1;
		int birthdayYear = 1970;

		Group guestGroup = GroupLocalServiceUtil.getGroup(
			companyId, GroupConstants.GUEST);

		long[] groupIds = new long[] {guestGroup.getGroupId()};

		Organization sevenCogsOrganization =
			OrganizationLocalServiceUtil.getOrganization(
				companyId, "7Cogs, Inc.");

		long[] organizationIds = new long[] {
			sevenCogsOrganization.getOrganizationId()
		};

		long[] userGroupIds = null;
		boolean sendEmail = false;
		ServiceContext serviceContext = null;

		User user = UserLocalServiceUtil.addUser(
			creatorUserId, companyId, autoPassword, password1, password2,
			autoScreenName, screenName, emailAddress, openId, locale, firstName,
			middleName, lastName, prefixId, suffixId, male, birthdayMonth,
			birthdayDay, birthdayYear, jobTitle, groupIds, organizationIds,
			roleIds, userGroupIds, sendEmail, serviceContext);

		byte[] portrait = getBytes("/users/" + screenName + "/portrait.jpg");

		UserLocalServiceUtil.updatePortrait(user.getUserId(), portrait);

		String[] questions = StringUtil.split(
			PropsUtil.get("users.reminder.queries.questions"));

		String question = questions[0];
		String answer = "1234";

		UserLocalServiceUtil.updateReminderQuery(
			user.getUserId(), question, answer);

		Group group = user.getGroup();

		// Profile layout

		Layout layout = addLayout(
			group, "Profile", false, "/profile", "2_columns_ii");

		String portletId = addPortletId(
			layout, PortletKeys.JOURNAL_CONTENT, "column-2");

		highlightPortlet(layout, portletId);

		JournalArticle journalArticle = addJournalArticle(
			user.getUserId(), group.getGroupId(),
			"Public Pages " + user.getScreenName(),
			"my_community_" + user.getScreenName() + ".xml");

		configureJournalContent(
			layout, portletId, journalArticle.getArticleId());

		addPortletId(layout, "1_WAR_wolportlet", "column-1");
		addPortletId(layout, PortletKeys.REQUESTS, "column-1");
		addPortletId(layout, "4_WAR_wolportlet", "column-1");
		addPortletId(layout, PortletKeys.ACTIVITIES, "column-2");
		addPortletId(layout, "5_WAR_wolportlet", "column-2");

		// Blog layout

		layout = addLayout(group, "Blog", false, "/blog", "2_columns_ii");

		addPortletId(layout, PortletKeys.RECENT_BLOGGERS, "column-1");
		addPortletId(layout, PortletKeys.BLOGS, "column-2");

		// Social layout

		layout = addLayout(group, "Social", true, "/social", "2_columns_ii");

		addPortletId(layout, "1_WAR_wolportlet", "column-1");
		addPortletId(layout, PortletKeys.REQUESTS, "column-1");

		portletId = addPortletId(
			layout, PortletKeys.JOURNAL_CONTENT, "column-2");

		highlightPortlet(layout, portletId);

		journalArticle = addJournalArticle(
			user.getUserId(), group.getGroupId(),
			"Public Pages " + user.getScreenName(),
			"private_pages_" + user.getScreenName() + ".xml");

		configureJournalContent(
			layout, portletId, journalArticle.getArticleId());

		addPortletId(layout, "6_WAR_wolportlet", "column-2");

		portletId = addPortletId(layout, PortletKeys.IFRAME, "column-1");

		Map iframePreferences = new HashMap();

		iframePreferences.put (
			"portlet-setup-show-borders", String.valueOf(Boolean.FALSE));

		if (screenName.equals("bruno") || screenName.equals("john")) {
			iframePreferences.put("src","http://iphone.facebook.com");
			iframePreferences.put("height-normal","460");
		}
		else if (screenName.equals("michelle")) {
			iframePreferences.put("src","https://twitterforiphone.com/login");
			iframePreferences.put("height-normal","400");
		}
		else if (screenName.equals("richard")) {
			iframePreferences.put("src","http://m.linkedin.com");
			iframePreferences.put("height-normal","350");
		}

		updatePortletSetup(layout, portletId, iframePreferences);

		// Workspace layout

		layout = addLayout(
			group, "Workspace", true, "/workspace", "2_columns_i");

		addPortletId(layout, PortletKeys.RECENT_DOCUMENTS, "column-1");
		addPortletId(layout, PortletKeys.DOCUMENT_LIBRARY, "column-1");
		addPortletId(layout, PortletKeys.IMAGE_GALLERY, "column-1");

		portletId = addPortletId(
			layout, PortletKeys.JOURNAL_CONTENT, "column-2");

		highlightPortlet(layout, portletId);

		journalArticle = addJournalArticle(
			user.getUserId(), group.getGroupId(), "My documents",
			"workspace_docs.xml");

		configureJournalContent(
			layout, portletId, journalArticle.getArticleId());

		addPortletId(layout, PortletKeys.CALENDAR, "column-2");

		// Email layout

		layout = addLayout(group, "Email", true, "/email", "1_column");

		addPortletId(layout, "1_WAR_mailportlet", "column-1");

		return user;
	}

	protected WikiPage addWikiPage(
			long userId, long nodeId, String title, String fileName,
			ServiceContext serviceContext)
		throws Exception {

		String content = getString("/wiki/" + fileName);

		return WikiPageLocalServiceUtil.addPage(
			userId, nodeId, title, content, "New", false, serviceContext);
	}

	protected void clearData(long companyId) throws Exception {

		// Users

		List<User> users = UserLocalServiceUtil.search(
			companyId, null, null, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			(OrderByComparator)null);

		for (User user : users) {
			if (user.getScreenName().equals("test")) {
				continue;
			}

			UserLocalServiceUtil.deleteUser(user.getUserId());
		}

		// Groups

		List<Group> groups = GroupLocalServiceUtil.search(
			companyId, null, null, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Group group : groups) {
			IGFolderLocalServiceUtil.deleteFolders(group.getGroupId());

			JournalArticleLocalServiceUtil.deleteArticles(group.getGroupId());
			JournalTemplateLocalServiceUtil.deleteTemplates(group.getGroupId());
			JournalStructureLocalServiceUtil.deleteStructures(
				group.getGroupId());

			if (!PortalUtil.isSystemGroup(group.getName())) {
				GroupLocalServiceUtil.deleteGroup(group.getGroupId());
			}
			else {
				LayoutLocalServiceUtil.deleteLayouts(group.getGroupId(), false);
				LayoutLocalServiceUtil.deleteLayouts(group.getGroupId(), true);
			}
		}

		// Organizations

		deleteOrganizations(
			companyId, OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID);

		// Roles

		List<Role> roles = RoleLocalServiceUtil.getRoles(companyId);

		for (Role role : roles) {
			if (!PortalUtil.isSystemRole(role.getName())) {
				RoleLocalServiceUtil.deleteRole(role.getRoleId());
			}
		}
	}

	protected void configureJournalContent(
			Layout layout, String portletId, String articleId)
		throws Exception {

		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, portletId);

		portletSetup.setValue("group-id", String.valueOf(layout.getGroupId()));
		portletSetup.setValue("article-id", articleId);

		portletSetup.store();
	}

	protected void configurePortletTitle(
			Layout layout, String portletId, String title)
		throws Exception {

		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, portletId);

		portletSetup.setValue("portlet-setup-title", title);

		portletSetup.store();
	}

	protected void deleteOrganizations(
			long companyId, long parentOrganizationId)
		throws Exception {

		List<Organization> organizations = OrganizationLocalServiceUtil.search(
			companyId, parentOrganizationId, null, null, null, null, null,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, (OrderByComparator)null);

		for (Organization organization : organizations) {
			deleteOrganizations(companyId, organization.getOrganizationId());
		}

		if (parentOrganizationId > 0) {
			OrganizationLocalServiceUtil.deleteOrganization(
				parentOrganizationId);
		}
	}

	protected void doRun(long companyId) throws Exception {
		if (isAlreadyRan(companyId)) {
			return;
		}

		long defaultUserId = UserLocalServiceUtil.getDefaultUserId(companyId);

		clearData(companyId);
		setupCommunities(companyId, defaultUserId);
		setupOrganizations(companyId, defaultUserId);
		setupRoles(companyId, defaultUserId);
		setupUsers(companyId);
	}

	protected byte[] getBytes(String path) throws Exception {
		return FileUtil.getBytes(getInputStream(path));
	}

	protected InputStream getInputStream(String path) throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();

		return classLoader.getResourceAsStream("/resources" +path);
	}

	protected String getString(String path) throws Exception {
		return new String(getBytes(path));
	}

	protected void highlightPortlet(Layout layout, String portletId)
		throws Exception {

		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, portletId);

		portletSetup.setValue(
			"portlet-setup-show-borders", String.valueOf(Boolean.FALSE));
		portletSetup.setValue(
			"portlet-setup-css", getString("/preferences/highlight.json"));

		portletSetup.store();
	}

	protected boolean isAlreadyRan(long companyId) throws Exception {
		boolean alreadyRan = false;

		try {

			// If Bruno exists, do not run again

			UserLocalServiceUtil.getUserByScreenName(companyId, "bruno");

			alreadyRan = true;
		}
		catch (NoSuchUserException nsue) {
		}

		return alreadyRan;
	}

	protected void removePortletBorder(Layout layout, String portletId)
		throws Exception {

		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, portletId);

		portletSetup.setValue(
			"portlet-setup-show-borders", String.valueOf(Boolean.FALSE));

		portletSetup.store();
	}

	protected void setRolePermissions(
			Role role, String name, String[] actionIds)
		throws Exception {

		long roleId = role.getRoleId();
		long companyId = role.getCompanyId();
		int scope =	ResourceConstants.SCOPE_COMPANY;
		String primKey = String.valueOf(companyId);

		PermissionLocalServiceUtil.setRolePermissions(
			roleId, companyId, name, scope, primKey, actionIds);
	}

	protected void setupCommunities(long companyId, long defaultUserId)
		throws Exception {

		// Guest community

		Group group = GroupLocalServiceUtil.getGroup(
			companyId, GroupConstants.GUEST);

		// Journal

		addJournalStructure(defaultUserId, group.getGroupId());
		addJournalTemplate(defaultUserId, group.getGroupId());

		// Image gallery

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddCommunityPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setScopeGroupId(group.getGroupId());

		IGFolder igFolder = IGFolderLocalServiceUtil.addFolder(
			defaultUserId, 0, "Web Content", "Images used for content",
			serviceContext);

		IGImage cellBgIGImage = addIGImage(
			defaultUserId, igFolder.getFolderId(), "cell_bg.png",
			serviceContext);

		IGImage portalMashupIGImage = addIGImage(
			defaultUserId, igFolder.getFolderId(), "portal_mashup.png",
			serviceContext);

		IGImage sevenCogsAdIGImage = addIGImage(
			defaultUserId, igFolder.getFolderId(), "sevencogs_ad.png",
			serviceContext);

		IGImage sharedWorkspacesIGImage = addIGImage(
			defaultUserId, igFolder.getFolderId(), "shared_workspaces.png",
			serviceContext);

		IGImage socialNetworkingIGImage = addIGImage(
			defaultUserId, igFolder.getFolderId(), "social_network.png",
			serviceContext);

		IGImage webPublishingIGImage = addIGImage(
			defaultUserId, igFolder.getFolderId(), "web_publishing.png",
			serviceContext);

		// Welcome layout

		Layout layout = addLayout(
			group, "Welcome", false, "/home", "2_columns_iii");

		// Welcome content portlet

		String portletId = addPortletId(
			layout, PortletKeys.JOURNAL_CONTENT, "column-1");

		removePortletBorder(layout, portletId);

		JournalArticle journalArticle = addJournalArticle(
			defaultUserId, group.getGroupId(), "Welcome", "welcome.xml");

		String content = StringUtil.replace(
			journalArticle.getContent(),
			new String[] {
				"[$CELL_BG_IG_IMAGE_UUID$]",
				"[$GROUP_ID$]",
				"[$PORTAL_MASHUPS_IG_IMAGE_UUID$]",
				"[$SHARED_WORKSPACES_IG_IMAGE_UUID$]",
				"[$SOCIAL_NETWORKING_IG_IMAGE_UUID$]",
				"[$WEB_PUBLISHING_IG_IMAGE_UUID$]"
			},
			new String[] {
				String.valueOf(cellBgIGImage.getUuid()),
				String.valueOf(group.getGroupId()),
				String.valueOf(portalMashupIGImage.getUuid()),
				String.valueOf(sharedWorkspacesIGImage.getUuid()),
				String.valueOf(socialNetworkingIGImage.getUuid()),
				String.valueOf(webPublishingIGImage.getUuid())
			});

		JournalArticleLocalServiceUtil.updateContent(
			group.getGroupId(), journalArticle.getArticleId(),
			journalArticle.getVersion(), content);

		configureJournalContent(
			layout, portletId, journalArticle.getArticleId());

		// 7Cogs Ad content portlet

		portletId = addPortletId(
			layout, PortletKeys.JOURNAL_CONTENT, "column-2");

		removePortletBorder(layout, portletId);

		journalArticle = addJournalArticle(
			defaultUserId, group.getGroupId(), "7 Cogs Ad", "7cogs_ad.xml");

		content = StringUtil.replace(
			journalArticle.getContent(),
			new String[] {
				"[$GROUP_ID$]",
				"[$SEVEN_COGS_IG_IMAGE_UUID$]"
			},
			new String[] {
				String.valueOf(group.getGroupId()),
				String.valueOf(sevenCogsAdIGImage.getUuid())
			});

		JournalArticleLocalServiceUtil.updateContent(
			group.getGroupId(), journalArticle.getArticleId(),
			journalArticle.getVersion(), content);

		configureJournalContent(
			layout, portletId, journalArticle.getArticleId());

		// Welcome Note content portlet

		portletId = addPortletId(
			layout, PortletKeys.JOURNAL_CONTENT, "column-2");

		removePortletBorder(layout, portletId);

		journalArticle = addJournalArticle(
			defaultUserId, group.getGroupId(), "Welcome Note",
			"welcome_note.xml");

		configureJournalContent(
			layout, portletId, journalArticle.getArticleId());

		// Welcome Login content portlet

		portletId = addPortletId(
			layout, PortletKeys.JOURNAL_CONTENT, "column-2");

		journalArticle = addJournalArticle(
			defaultUserId, group.getGroupId(), "Welcome Login",
			"welcome_login.xml");

		content = StringUtil.replace(
			journalArticle.getContent(), "[$COMPANY_ID$]",
			String.valueOf(companyId));

		JournalArticleLocalServiceUtil.updateContent(
			group.getGroupId(), journalArticle.getArticleId(),
			journalArticle.getVersion(), content);

		configureJournalContent(
			layout, portletId, journalArticle.getArticleId());

		configurePortletTitle(layout, portletId, "Current Users");

		// Login portlet

		addPortletId(layout, PortletKeys.LOGIN, "column-2");
	}

	protected void setupOrganizations(long companyId, long defaultUserId)
		throws Exception {

		// 7Cogs, Inc. organization

		long userId = defaultUserId;
		long parentOrganizationId =
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID;
		String name = "7Cogs, Inc.";
		String type = OrganizationConstants.TYPE_REGULAR_ORGANIZATION;
		boolean recursable = true;
		long regionId = 0;
		long countryId = 0;
		int statusId = GetterUtil.getInteger(PropsUtil.get(
			"sql.data.com.liferay.portal.model.ListType.organization.status"));
		String comments = null;

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddCommunityPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		Organization organization =
			OrganizationLocalServiceUtil.addOrganization(
				userId, parentOrganizationId, name, type, recursable, regionId,
				countryId, statusId, comments, serviceContext);

		// Group

		Group group = organization.getGroup();

		GroupLocalServiceUtil.updateFriendlyURL(group.getGroupId(), "/7cogs");

		// Layout set

		LayoutSetLocalServiceUtil.updateLogo(
			group.getGroupId(), false, true,
			getInputStream("/images/seven_cogs_log.png"));

		LayoutSetLocalServiceUtil.updateLookAndFeel(
			group.getGroupId(), false, "sevencogs_WAR_sevencogstheme", "01", "",
			false);

		// Journal

		addJournalStructure(defaultUserId, group.getGroupId());
		addJournalTemplate(defaultUserId, group.getGroupId());

		// Image gallery

		serviceContext.setScopeGroupId(group.getGroupId());

		IGFolder igFolder = IGFolderLocalServiceUtil.addFolder(
			defaultUserId, 0, "7Cogs Web Content", "Images used for content",
			serviceContext);

		IGImage addIconIGImage = addIGImage(
			defaultUserId, igFolder.getFolderId(), "add_icon.png",
			serviceContext);

		IGImage configurationIconIGImage = addIGImage(
			defaultUserId, igFolder.getFolderId(), "configuration_icon.png",
			serviceContext);

		IGImage editIconIGImage = addIGImage(
			defaultUserId, igFolder.getFolderId(), "edit_icon.png",
			serviceContext);

		IGImage eeAdIGImage = addIGImage(
			defaultUserId, igFolder.getFolderId(), "ee_ad.png", serviceContext);

		IGImage gartnerIGImage = addIGImage(
			defaultUserId, igFolder.getFolderId(), "gartner.png",
			serviceContext);

		IGImage introducingIGImage = addIGImage(
			defaultUserId, igFolder.getFolderId(), "introducing.png",
			serviceContext);

		IGImage liferayLogoIGImage = addIGImage(
			defaultUserId, igFolder.getFolderId(), "liferay_logo.png",
			serviceContext);

		IGImage lookIconIGImage = addIGImage(
			defaultUserId, igFolder.getFolderId(), "look_icon.png",
			serviceContext);

		IGImage productsIGImage = addIGImage(
			defaultUserId, igFolder.getFolderId(), "products.png",
			serviceContext);

		// Home layout

		Layout layout = addLayout(
			group, "Home", false, "/home", "1_2_columns_ii");

		// Banner content portlet

		String portletId = addPortletId(
			layout, PortletKeys.JOURNAL_CONTENT, "column-1");

		removePortletBorder(layout, portletId);

		JournalArticle journalArticle = addJournalArticle(
			defaultUserId, group.getGroupId(), "Banner",
			"front_page_banner.xml");

		String content = StringUtil.replace(
			journalArticle.getContent(),
			new String[] {
				"[$GROUP_ID$]",
				"[$INTRODUCING_IG_IMAGE_UUID$]"
			},
			new String[] {
				String.valueOf(group.getGroupId()),
				String.valueOf(introducingIGImage.getUuid())
			});

		JournalArticleLocalServiceUtil.updateContent(
			group.getGroupId(), journalArticle.getArticleId(),
			journalArticle.getVersion(), content);

		configureJournalContent(
			layout, portletId, journalArticle.getArticleId());

		// Front Page Intro content portlet

		portletId = addPortletId(
			layout, PortletKeys.JOURNAL_CONTENT, "column-2");

		removePortletBorder(layout, portletId);

		journalArticle = addJournalArticle(
			defaultUserId, group.getGroupId(), "Front Page Intro",
			"front_page_intro.xml");

		configureJournalContent(
			layout, portletId, journalArticle.getArticleId());

		// Fron Page - Edit and Create content portlet

		portletId = addPortletId(
			layout, PortletKeys.JOURNAL_CONTENT, "column-2");

		configurePortletTitle(layout, portletId, "Edit and Add Content");

		journalArticle = addJournalArticle(
			defaultUserId, group.getGroupId(), "Fron Page - Edit and Create",
			"front_page_edit_and_create.xml");

		content = StringUtil.replace(
			journalArticle.getContent(),
			new String[] {
				"[$ADD_ICON_IG_IMAGE_UUID$]",
				"[$CONFIGURATION_ICON_IG_IMAGE_UUID$]",
				"[$EDIT_ICON_IG_IMAGE_UUID$]",
				"[$GROUP_ID$]",
				"[$LOOK_ICON_IG_IMAGE_UUID$]"
			},
			new String[] {
				String.valueOf(addIconIGImage.getUuid()),
				String.valueOf(configurationIconIGImage.getUuid()),
				String.valueOf(editIconIGImage.getUuid()),
				String.valueOf(group.getGroupId()),
				String.valueOf(lookIconIGImage.getUuid())
			});

		JournalArticleLocalServiceUtil.updateContent(
			group.getGroupId(), journalArticle.getArticleId(),
			journalArticle.getVersion(), content);

		configureJournalContent(
			layout, portletId, journalArticle.getArticleId());

		// Front Page - EE Ad content portlet

		portletId = addPortletId(
			layout, PortletKeys.JOURNAL_CONTENT, "column-3");

		configurePortletTitle(layout, portletId, "Advertisement");

		journalArticle = addJournalArticle(
			defaultUserId, group.getGroupId(), "Front Page - EE Ad",
			"ee_ad.xml");

		content = StringUtil.replace(
			journalArticle.getContent(),
			new String[] {
				"[$GROUP_ID$]",
				"[$EE_AD_IG_IMAGE_UUID$]"
			},
			new String[] {
				String.valueOf(group.getGroupId()),
				String.valueOf(eeAdIGImage.getUuid())
			});

		JournalArticleLocalServiceUtil.updateContent(
			group.getGroupId(), journalArticle.getArticleId(),
			journalArticle.getVersion(), content);

		configureJournalContent(
			layout, portletId, journalArticle.getArticleId());

		// Front Page - Permissions content portlet

		portletId = addPortletId(
			layout, PortletKeys.JOURNAL_CONTENT, "column-3");

		highlightPortlet(layout, portletId);

		journalArticle = addJournalArticle(
			defaultUserId, group.getGroupId(), "Front Page - Permissions",
			"front_page_permissions.xml");

		configureJournalContent(
			layout, portletId, journalArticle.getArticleId());

		// Products layout

		layout = addLayout(
			group, "Products", false, "/products", "2_columns_iii");

		// Products Image content portlet

		portletId = addPortletId(
			layout, PortletKeys.JOURNAL_CONTENT, "column-1");

		removePortletBorder(layout, portletId);

		journalArticle = addJournalArticle(
			defaultUserId, group.getGroupId(), "Products Image",
			"products_banner.xml");

		content = StringUtil.replace(
			journalArticle.getContent(),
			new String[] {
				"[$GROUP_ID$]",
				"[$PRODUCTS_IG_IMAGE_UUID$]"
			},
			new String[] {
				String.valueOf(group.getGroupId()),
				String.valueOf(productsIGImage.getUuid())
			});

		JournalArticleLocalServiceUtil.updateContent(
			group.getGroupId(), journalArticle.getArticleId(),
			journalArticle.getVersion(), content);

		configureJournalContent(
			layout, portletId, journalArticle.getArticleId());

		// News content portlet

		portletId = addPortletId(
			layout, PortletKeys.JOURNAL_CONTENT, "column-2");

		configurePortletTitle(layout, portletId, "News");

		journalArticle = addJournalArticle(
			defaultUserId, group.getGroupId(), "Gartner", "gartner.xml");

		content = StringUtil.replace(
			journalArticle.getContent(),
			new String[] {
				"[$GROUP_ID$]", "[$GARTNER_IG_IMAGE_UUID$]"
			},
			new String[] {
				String.valueOf(group.getGroupId()),
				String.valueOf(gartnerIGImage.getUuid())
			});

		JournalArticleLocalServiceUtil.updateContent(
			group.getGroupId(), journalArticle.getArticleId(),
			journalArticle.getVersion(), content);

		configureJournalContent(
			layout, portletId, journalArticle.getArticleId());

		// Introducing Vix content portlet

		portletId = addPortletId(
			layout, PortletKeys.JOURNAL_CONTENT, "column-1");

		removePortletBorder(layout, portletId);

		journalArticle = addJournalArticle(
			defaultUserId, group.getGroupId(), "Introducing Vix",
			"introducing_vix.xml");

		configureJournalContent(
			layout, portletId, journalArticle.getArticleId());

		// Products - More Information content portlet

		portletId = addPortletId(
			layout, PortletKeys.JOURNAL_CONTENT, "column-1");

		highlightPortlet(layout, portletId);

		journalArticle = addJournalArticle(defaultUserId,
			group.getGroupId(), "Products - More Information",
			"products_more.xml");

		configureJournalContent(
			layout, portletId, journalArticle.getArticleId());

		// Blogs

		layout = addLayout(group, "Blogs", false, "/blogs", "2_columns_ii");

		addPortletId(layout, PortletKeys.RECENT_BLOGGERS, "column-1");
		addPortletId(layout, PortletKeys.BLOGS_AGGREGATOR, "column-2");

		// Wiki layout

		layout = addLayout(group, "Wiki", false, "/wiki", "1_column");

		addPortletId(layout, PortletKeys.WIKI, "column-1");

		// Wiki

		WikiNode wikiNode = WikiNodeLocalServiceUtil.addNode(
			defaultUserId, "Main", StringPool.BLANK, serviceContext);

		addWikiPage(
			defaultUserId, wikiNode.getNodeId(), "FrontPage",
			"FrontPage.xml", serviceContext);

		addWikiPage(
			defaultUserId, wikiNode.getNodeId(), "Vix-998",
			"Vix-998.xml", serviceContext);

		// Forums layout

		layout = addLayout(group, "Forums", false, "/forums", "1_column");

		addPortletId(layout, PortletKeys.MESSAGE_BOARDS, "column-1");

		// About Us layout

		layout = addLayout(
			group, "About Us", false, "/about_us", "2_columns_ii");

		portletId = addPortletId(
			layout, PortletKeys.JOURNAL_CONTENT, "column-1");

		highlightPortlet(layout, portletId);

		journalArticle = addJournalArticle(
			defaultUserId, group.getGroupId(), "About Us",
			"7cogs_about_us.xml");

		content = StringUtil.replace(
			journalArticle.getContent(),
			new String[] {
				"[$GROUP_ID$]",
				"[$LIFERAY_LOGO_IG_IMAGE_UUID$]"
			},
			new String[] {
				String.valueOf(group.getGroupId()),
				String.valueOf(liferayLogoIGImage.getUuid())
			});

		JournalArticleLocalServiceUtil.updateContent(
			group.getGroupId(), journalArticle.getArticleId(),
			journalArticle.getVersion(), content);

		configureJournalContent(
			layout, portletId, journalArticle.getArticleId());

		addPortletId(layout, "1_WAR_webformportlet", "column-2");

		portletId = addPortletId(layout, "1_WAR_googlemapsportlet", "column-1");

		Map<String, String> preferences = new HashMap<String, String>();

		preferences.put("map-address", "Los Angeles, USA");
		preferences.put("height", "300");

		updatePortletSetup(layout, portletId, preferences);

		// Home layout

		layout = addLayout(group, "Home", true, "/home", "2_columns_ii");

		portletId = addPortletId(
			layout, PortletKeys.JOURNAL_CONTENT, "column-1");

		highlightPortlet(layout, portletId);

		journalArticle = addJournalArticle(
			defaultUserId, group.getGroupId(), "Home",
			"7cogs_private_pages.xml");

		configureJournalContent(
			layout, portletId, journalArticle.getArticleId());

		portletId = addPortletId(layout, PortletKeys.ACTIVITIES, "column-2");

		configurePortletTitle(
			layout, portletId, "Last Activities in 7Cogs Organization");

		// Documents layout

		layout = addLayout(
			group, "Documents", true, "/documents", "2_columns_iii");

		addPortletId(layout, PortletKeys.DOCUMENT_LIBRARY, "column-1");
		addPortletId(layout, PortletKeys.IMAGE_GALLERY, "column-1");

		portletId = addPortletId(
			layout, PortletKeys.JOURNAL_CONTENT, "column-2");

		highlightPortlet(layout, portletId);

		journalArticle = addJournalArticle(
			defaultUserId, group.getGroupId(), "Shared Documents",
			"shared_docs.xml");

		configureJournalContent(
			layout, portletId, journalArticle.getArticleId());
	}

	protected void setupRoles(long companyId, long defaultUserId)
		throws Exception {

		Role publisherRole = RoleLocalServiceUtil.addRole(
			defaultUserId, companyId, "Publisher", null,
			"Publishers are responsible for approving content.",
			RoleConstants.TYPE_REGULAR);

		setRolePermissions(
			publisherRole, PortletKeys.JOURNAL,
			new String[] {ActionKeys.ACCESS_IN_CONTROL_PANEL});

		setRolePermissions(
			publisherRole, "com.liferay.portlet.journal",
			new String[] {
				ActionKeys.ADD_ARTICLE, ActionKeys.ADD_FEED,
				ActionKeys.ADD_STRUCTURE, ActionKeys.ADD_TEMPLATE,
				ActionKeys.APPROVE_ARTICLE, ActionKeys.EXPIRE
			});

		setRolePermissions(
			publisherRole, JournalArticle.class.getName(),
			new String[] {
				ActionKeys.ADD_DISCUSSION, ActionKeys.DELETE, ActionKeys.EXPIRE,
				ActionKeys.PERMISSIONS, ActionKeys.UPDATE, ActionKeys.VIEW
			});

		Role writerRole = RoleLocalServiceUtil.addRole(
			defaultUserId, companyId, "Writer", null,
			"Writers are responsible for creating content.",
			RoleConstants.TYPE_REGULAR);

		setRolePermissions(
			writerRole, PortletKeys.JOURNAL,
			new String[] {ActionKeys.ACCESS_IN_CONTROL_PANEL});

		setRolePermissions(
			writerRole, "com.liferay.portlet.journal",
			new String[] {
				ActionKeys.ADD_ARTICLE, ActionKeys.ADD_FEED,
				ActionKeys.ADD_STRUCTURE, ActionKeys.ADD_TEMPLATE,
				ActionKeys.MANAGE_STAGING
			});

		setRolePermissions(
			writerRole, JournalArticle.class.getName(),
			new String[] {ActionKeys.UPDATE, ActionKeys.VIEW});
	}

	protected void setupUsers(long companyId) throws Exception {

		// Roles

		Role adminRole = RoleLocalServiceUtil.getRole(
			companyId, RoleConstants.ADMINISTRATOR);

		Role powerUserRole = RoleLocalServiceUtil.getRole(
			companyId, RoleConstants.POWER_USER);

		Role publisherRole = RoleLocalServiceUtil.getRole(
			companyId, "Publisher");

		Role writerRole = RoleLocalServiceUtil.getRole(
			companyId, "Writer");

		// Users

		long[] roleIds = new long[] {
			adminRole.getRoleId(), powerUserRole.getRoleId()
		};

		User brunoUser = addUser(
			companyId, "bruno", "Bruno", "Admin", true, "Administrator",
			roleIds);

		roleIds = new long[] {powerUserRole.getRoleId()};

		User johnUser = addUser(
			companyId, "john", "John", "Regular", true, "Employee", roleIds);

		roleIds = new long[] {
			powerUserRole.getRoleId(), writerRole.getRoleId()
		};

		User michelleUser = addUser(
			companyId, "michelle", "Michelle", "Writer", false, "Writer",
			roleIds);

		roleIds = new long[] {
			powerUserRole.getRoleId(), publisherRole.getRoleId()
		};

		User richardUser = addUser(
			companyId, "richard", "Richard", "Editor", true, "Publisher",
			roleIds);

		// Blogs

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddCommunityPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setScopeGroupId(brunoUser.getGroup().getGroupId());

		addBlogsEntry(
			brunoUser.getUserId(), "New Control Panel!!", "controlpanel.xml",
			serviceContext);

		addBlogsEntry(
			brunoUser.getUserId(),
			"Configuration of the portal: portal.properties",
			"portalproperties.xml", serviceContext);

		serviceContext.setScopeGroupId(johnUser.getGroup().getGroupId());

		addBlogsEntry(
			johnUser.getUserId(), "Using the wiki", "wiki.xml", serviceContext);

		serviceContext.setScopeGroupId(michelleUser.getGroup().getGroupId());

		addBlogsEntry(
			michelleUser.getUserId(), "We have an amazing Chat!", "chat.xml",
			serviceContext);

		// Document library

		DLFolder dlFolder = addDLFolder(
			brunoUser.getUserId(), brunoUser.getGroup().getGroupId(),
			"Important Documents", "Documents related with the company");

		addDLFileEntry(
			brunoUser.getUserId(), dlFolder.getFolderId(), "Budget.xls",
			"Budget", "Budgets for the current year");

		addDLFolder(
			michelleUser.getUserId(), michelleUser.getGroup().getGroupId(),
			"My Documents", "Personal docs");

		dlFolder = addDLFolder(
			michelleUser.getUserId(), michelleUser.getGroup().getGroupId(),
			"Work Documents", "Works docs");

		addDLFileEntry(
			michelleUser.getUserId(), dlFolder.getFolderId(),
			"Notes from the last meeting.doc", "Notes from the last meeting",
			"Important notes");

		addDLFolder(
			richardUser.getUserId(), richardUser.getGroup().getGroupId(),
			"Documentation", StringPool.BLANK);

		dlFolder = addDLFolder(
			richardUser.getUserId(),richardUser.getGroup().getGroupId(),
			"Innovation", "New things");

		addDLFileEntry(
			richardUser.getUserId(), dlFolder.getFolderId(), "New Features.ppt",
			"New Features", "Features for the current year");

		// Message boards

		Organization sevenCogsOrganization =
			OrganizationLocalServiceUtil.getOrganization(
			companyId, "7Cogs, Inc.");

		serviceContext.setScopeGroupId(
			sevenCogsOrganization.getGroup().getGroupId());

		MBCategory mbCategory = addMBCategory(
			brunoUser.getUserId(), "Using the forum",
			"Some advice on using the forum", serviceContext);

		addMBMessage(
			brunoUser.getUserId(), brunoUser.getFullName(),
			mbCategory.getCategoryId(), 0, 0, "Nice Forums", "general.xml",
			serviceContext);

		mbCategory = addMBCategory(
			brunoUser.getUserId(), "General Questions",
			"Product questions and more!", serviceContext);

		MBMessage vix1Message = addMBMessage(
			brunoUser.getUserId(), brunoUser.getFullName(),
			mbCategory.getCategoryId(), 0, 0, "About the Vix-998", "vix1.xml",
			serviceContext);

		MBMessage vix2Message = addMBMessage(
			richardUser.getUserId(), richardUser.getFullName(),
			mbCategory.getCategoryId(), vix1Message.getThreadId(),
			vix1Message.getMessageId(), "RE: About the Vix-998", "vix2.xml",
			serviceContext);

		addMBMessage(
			michelleUser.getUserId(), michelleUser.getFullName(),
			mbCategory.getCategoryId(), vix1Message.getThreadId(),
			vix2Message.getMessageId(), "RE: About the Vix-998", "vix3.xml",
			serviceContext);

		// Social

		addSocialRequest(michelleUser, brunoUser, true);
		addSocialRequest(michelleUser, johnUser, true);
		addSocialRequest(michelleUser, richardUser, true);

		addSocialRequest(johnUser, brunoUser, false);
		addSocialRequest(johnUser, richardUser, false);
	}

	protected void updateLayout(Layout layout) throws Exception {
		LayoutLocalServiceUtil.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getTypeSettings());
	}

	protected PortletPreferences updatePortletSetup(
			Layout layout, String portletId, Map<String, String> preferences)
		throws Exception {

		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, portletId);

		Iterator<Map.Entry<String, String>> itr =
			preferences.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry<String, String> entry = itr.next();

			String key = entry.getKey();
			String value = entry.getValue();

			portletSetup.setValue(key, value);
		}

		portletSetup.store();

		return portletSetup;
	}

	private static final int _WOL_FRIENDS_REQUEST_KEYS_ADD_FRIEND = 1;

}