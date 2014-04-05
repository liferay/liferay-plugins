<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%!
private static boolean _isPendingApproval(WikiPage wikiPage) {
	if ((wikiPage == null) || !Validator.equals(wikiPage.getSummary(), _AKISMET_CONSTANTS_WIKI_PAGE_PENDING_APPROVAL)) {
		return false;
	}

	return true;
}

private static boolean _isSpam(WikiPage wikiPage) {
	if ((wikiPage == null) || !Validator.equals(wikiPage.getSummary(), _AKISMET_CONSTANTS_WIKI_PAGE_MARKED_AS_SPAM)) {
		return false;
	}

	return true;
}

private static final String _AKISMET_CONSTANTS_WIKI_PAGE_MARKED_AS_SPAM = "Marked as Spam";

private static final String _AKISMET_CONSTANTS_WIKI_PAGE_PENDING_APPROVAL = "Pending Approval";
%>