/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.pagesitetemplates.hook.upgrade;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutPrototype;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutPrototypeLocalServiceUtil;
import com.liferay.portal.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * <a href="UpgradeProcess_1_0_0.java.html"><b><i>View Source</i></b></a>
 *
 * @author Sergio González
 */
public class UpgradeProcess_1_0_0 extends UpgradeProcess {

	protected void doUpgrade() throws Exception {
		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		for (int i = 0; i < companies.size(); i++) {
			long companyId = companies.get(i).getCompanyId();

			try {
				doRunPageBlog(companyId);
				doRunPageContent(companyId);
				doRunPageWiki(companyId);
				doRunSitePublic(companyId);;
				doRunSitePrivate(companyId);
			}
			catch (Exception e) {
				throw new ActionException(e);
			}
		}
	}

	protected void doRunPageBlog(long companyId) throws Exception {
		String description = "Blog Liferay's template with the following " +
				"portlets: Blogs, Tags Cloud, Recent Bloggers.";

		Map<Locale, String> titleMap = new HashMap<Locale, String>();
		String portletIdd = StringPool.BLANK;
		long defaultUserId = UserLocalServiceUtil.getDefaultUserId(companyId);

		titleMap.put(Locale.US, "Blog");

		LayoutPrototype layoutPrototype =
				LayoutPrototypeLocalServiceUtil.addLayoutPrototype(
						defaultUserId, companyId, titleMap, description, true);

		Layout layout = layoutPrototype.getLayout();

		LayoutTypePortlet layoutTypePortlet =
				(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(0, "2_columns_iii", false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.BLOGS, "column-1", -1, false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.TAGS_CLOUD, "column-2", -1, false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.RECENT_BLOGGERS, "column-2", -1, false);

		updateLayout(layout);
	}

	protected void doRunPageContent(long companyId) throws Exception {
		String description = "Content Liferay's template with the following " +
				"portlet: Asset Publisher.";

		Map<Locale, String> titleMap = new HashMap<Locale, String>();
		String portletIdd = StringPool.BLANK;
		long defaultUserId = UserLocalServiceUtil.getDefaultUserId(companyId);

		titleMap.put(Locale.US, "Content");

		LayoutPrototype layoutPrototype =
				LayoutPrototypeLocalServiceUtil.addLayoutPrototype(
						defaultUserId, companyId, titleMap, description, true);

		Layout layout = layoutPrototype.getLayout();

		LayoutTypePortlet layoutTypePortlet =
				(LayoutTypePortlet)layout.getLayoutType();
		layoutTypePortlet.setLayoutTemplateId(0, "1_column", false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, "101", "column-1", -1, false);

		updateLayout(layout);
	}

	protected void doRunPageWiki(long companyId) throws Exception {
		String description = "Wiki Liferay's template with the following " +
				"portlets: Wiki, Categories Navigation, Tags Navigation.";

		Map<Locale, String> titleMap = new HashMap<Locale, String>();
		String portletIdd = StringPool.BLANK;
		long defaultUserId = UserLocalServiceUtil.getDefaultUserId(companyId);

		titleMap.put(Locale.US, "Wiki");

		LayoutPrototype layoutPrototype =
				LayoutPrototypeLocalServiceUtil.addLayoutPrototype(
						defaultUserId, companyId, titleMap, description, true);

		Layout layout = layoutPrototype.getLayout();

		LayoutTypePortlet layoutTypePortlet =
				(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(0, "2_columns_iii", false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.WIKI, "column-1", -1, false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.TAGS_CATEGORIES_NAVIGATION, "column-2", -1,
				false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.TAGS_ENTRIES_NAVIGATION, "column-2", -1,
				false);

		updateLayout(layout);
	}

	protected void doRunSitePrivate(long companyId) throws Exception {
		String description = "Private Site Liferay's template containing the " +
				"following pages: home, tools, documents and images.";

		Map<Locale, String> titleMap = new HashMap<Locale, String>();
		String portletIdd = StringPool.BLANK;
		long defaultUserId = UserLocalServiceUtil.getDefaultUserId(companyId);
		ServiceContext serviceContext = new ServiceContext();

		titleMap.put(Locale.US, "Private Site Liferay's template");

		LayoutSetPrototype layoutSetPrototype =
				LayoutSetPrototypeLocalServiceUtil.addLayoutSetPrototype(
						defaultUserId, companyId, titleMap, description,
						true);

		LayoutLocalServiceUtil.deleteLayouts(
				layoutSetPrototype.getGroup().getGroupId(), true);

		Layout layout = LayoutLocalServiceUtil.addLayout(
				defaultUserId, layoutSetPrototype.getGroup().getGroupId(), true,
				0, "Home", null, null, "portlet", false, "/home",
				serviceContext);

		LayoutTypePortlet layoutTypePortlet =
				(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(0, "2_columns_iii", false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.ANNOUNCEMENTS, "column-1", -1,
				false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.CALENDAR, "column-1", -1, false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.ACTIVITIES, "column-1", -1, false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.DIRECTORY, "column-2", -1, false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.COMMUNITIES, "column-2", -1, false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.INVITATION, "column-2", -1, false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.RSS, "column-2", -1, false);

		updateLayout(layout);

		layout = LayoutLocalServiceUtil.addLayout(
				defaultUserId, layoutSetPrototype.getGroup().getGroupId(), true,
				0, "Tools", null, null,	"portlet", false, "/tools",
				serviceContext);

		layoutTypePortlet =	(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(0, "2_columns_i", false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.TRANSLATOR, "column-1", -1, false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.CURRENCY_CONVERTER, "column-1", -1, false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.DICTIONARY, "column-2", -1, false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.SMS_TEXT_MESSENGER, "column-2", -1, false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.UNIT_CONVERTER, "column-2", -1, false);

		updateLayout(layout);

		layout = LayoutLocalServiceUtil.addLayout(
				defaultUserId, layoutSetPrototype.getGroup().getGroupId(), true,
				0, "Documents", null, null,	"portlet", false, "/documents",
				serviceContext);

		layoutTypePortlet =	(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(0, "2_columns_iii", false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.DOCUMENT_LIBRARY_DISPLAY, "column-1", -1,
				false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.RECENT_DOCUMENTS, "column-2", -1, false);

		updateLayout(layout);

		layout = LayoutLocalServiceUtil.addLayout(defaultUserId,
				layoutSetPrototype.getGroup().getGroupId(), true, 0, "Images",
				null, null, "portlet", false, "/images", serviceContext);

		layoutTypePortlet =	(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(0, "1_column", false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.IMAGE_GALLERY, "column-1", -1, false);

		updateLayout(layout);
	}

	protected void doRunSitePublic(long companyId) throws Exception {
		String description = "Public Site Liferay's template containing the " +
				"following pages: home, blog, wiki and forum.";

		Map<Locale, String> titleMap = new HashMap<Locale, String>();
		String portletIdd = StringPool.BLANK;
		long defaultUserId = UserLocalServiceUtil.getDefaultUserId(companyId);
		ServiceContext serviceContext = new ServiceContext();

		titleMap.put(Locale.US, "Public Site Liferay's template");

		LayoutSetPrototype layoutSetPrototype =
				LayoutSetPrototypeLocalServiceUtil.addLayoutSetPrototype(
						defaultUserId, companyId, titleMap, description,
						true);

		LayoutLocalServiceUtil.deleteLayouts(
				layoutSetPrototype.getGroup().getGroupId(), true);

		Layout layout = LayoutLocalServiceUtil.addLayout(
				defaultUserId, layoutSetPrototype.getGroup().getGroupId(), true,
				0, "Home", null, null, "portlet", false, "/home",
				serviceContext);

		LayoutTypePortlet layoutTypePortlet =
				(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(0, "1_2_columns_ii", false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.JOURNAL_CONTENT_SEARCH, "column-1", -1,
				false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.JOURNAL_CONTENT, "column-1", -1, false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.ASSET_PUBLISHER, "column-2", -1, false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.POLLS_DISPLAY, "column-3", -1, false);

		updateLayout(layout);

		layout = LayoutLocalServiceUtil.addLayout(
				defaultUserId, layoutSetPrototype.getGroup().getGroupId(), true,
				0, "Blog", null, null,	"portlet", false, "/blog",
				serviceContext);

		layoutTypePortlet =	(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(0, "2_columns_iii", false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.BLOGS, "column-1", -1, false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.TAGS_CLOUD, "column-2", -1, false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.RECENT_BLOGGERS, "column-2", -1, false);

		updateLayout(layout);

		layout = LayoutLocalServiceUtil.addLayout(
				defaultUserId, layoutSetPrototype.getGroup().getGroupId(), true,
				0, "Wiki", null, null,	"portlet", false, "/wiki",
				serviceContext);

		layoutTypePortlet =	(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(0, "2_columns_iii", false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.WIKI, "column-1", -1, false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.TAGS_CATEGORIES_NAVIGATION, "column-2", -1,
				false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.TAGS_ENTRIES_NAVIGATION, "column-2", -1,
				false);

		updateLayout(layout);

		layout = LayoutLocalServiceUtil.addLayout(defaultUserId,
				layoutSetPrototype.getGroup().getGroupId(), true, 0, "Forum",
				null, null, "portlet", false, "/forum", serviceContext);

		layoutTypePortlet =	(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(0, "1_column", false);

		portletIdd = layoutTypePortlet.addPortletId(
				0, PortletKeys.MESSAGE_BOARDS, "column-1", -1, false);

		updateLayout(layout);
	}

	protected void updateLayout(Layout layout) throws Exception {
		LayoutLocalServiceUtil.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getTypeSettings());
	}

}