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

<%@ include file="/html/taglib/init.jsp" %>

<%@ page import="com.liferay.portal.kernel.repository.model.FileEntry" %>
<%@ page import="com.liferay.portal.kernel.repository.model.FileEntryWrapper" %>
<%@ page import="com.liferay.portal.kernel.search.Summary" %>
<%@ page import="com.liferay.portal.kernel.util.Tuple" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portlet.expando.model.ExpandoBridge" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%
List<Tuple> fileEntryTuples2 = (List<Tuple>)request.getAttribute("liferay-ui:app-view-search-entry:fileEntryTuples");

if (fileEntryTuples2 != null) {
	List<Tuple> dlFileNameTuples = new ArrayList<Tuple>(fileEntryTuples2.size());

	for (Tuple fileEntryTuple : fileEntryTuples2) {
		FileEntry fileEntry = (FileEntry) fileEntryTuple.getObject(0);
		Summary summary = (Summary)fileEntryTuple.getObject(1);

		fileEntry = new DLFileNameWrapperFileEntryImpl(fileEntry);

		Tuple dlFileNameTuple = new Tuple(fileEntry, summary);

		dlFileNameTuples.add(dlFileNameTuple);
	}

	request.setAttribute("liferay-ui:app-view-search-entry:fileEntryTuples", dlFileNameTuples);
}
%>

<%@ include file="/html/taglib/ui/app_view_search_entry/page.jsp" %>

<%!
public class DLFileNameWrapperFileEntryImpl extends FileEntryWrapper {

	public DLFileNameWrapperFileEntryImpl(FileEntry fileEntry) {
		super(fileEntry);
	}

	@Override
	public String getTitle() {
		ExpandoBridge expandoBridge = getExpandoBridge();

		String displayTitle = (String)expandoBridge.getAttribute("DLDisplayName");

		if (Validator.isNull(displayTitle)) {
			return super.getTitle();
		}

		return displayTitle;
	}

}
%>