<%--
/**
 * Copyright (c) 2008-2010 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%
childrenMessagesTaggable = false;
includeFormTag = true;
%>

<%!
public String getCategoryBreadcrumbs(long categoryId, PageContext pageContext, RenderResponse renderResponse) throws Exception {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setWindowState(WindowState.NORMAL);

	portletURL.setParameter("struts_action", "/message_boards/view");
	portletURL.setParameter("mbCategoryId", String.valueOf(MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID));

	String head = "<span><a href=\"" + portletURL.toString() + "\">" + LanguageUtil.get(pageContext, "forums") + "</a></span> &raquo; ";

	if (categoryId == MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {
		return head;
	}

	String breadcrumb = StringPool.BLANK;

	MBCategory category = MBCategoryLocalServiceUtil.getCategory(categoryId);

	while (true) {
		portletURL.setParameter("mbCategoryId", String.valueOf(category.getCategoryId()));

		breadcrumb = "<span><a href=\"" + portletURL.toString() + "\">" + category.getName() + "</a></span> &raquo; " + breadcrumb;

		if (category.isRoot()) {
			break;
		}

		category = MBCategoryLocalServiceUtil.getCategory(category.getParentCategoryId());
	};

	breadcrumb = head + breadcrumb;

	return breadcrumb;
}
%>