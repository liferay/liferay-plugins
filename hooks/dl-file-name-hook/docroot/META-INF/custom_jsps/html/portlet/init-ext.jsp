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

<%@ page import="com.liferay.portal.kernel.cache.Lifecycle" %><%@
page import="com.liferay.portal.kernel.cache.ThreadLocalCacheManager" %><%@
page import="com.liferay.portal.kernel.cache.ThreadLocalCache" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.repository.model.FileEntry" %><%@
page import="com.liferay.portal.kernel.repository.model.FileVersion" %><%@
page import="com.liferay.portlet.expando.model.ExpandoBridge" %><%@
page import="com.liferay.portal.kernel.repository.model.FileEntryWrapper" %><%@
page import="com.liferay.portal.kernel.repository.model.FileVersionWrapper" %><%@
page import="com.liferay.portal.kernel.exception.PortalException" %><%@
page import="com.liferay.portal.kernel.exception.SystemException" %><%@
page import="com.liferay.portlet.messageboards.model.MBMessageWrapper" %><%@
page import="com.liferay.portlet.messageboards.model.MBMessage" %><%@
page import="com.liferay.portlet.wiki.model.WikiPage" %><%@
page import="com.liferay.portlet.wiki.model.WikiPageWrapper" %><%@
page import="com.liferay.portlet.wiki.model.WikiNode" %><%@
page import="com.liferay.portlet.wiki.model.WikiNodeWrapper" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%!
public class DLFileNameWrapperFileEntryImpl extends FileEntryWrapper {

	public DLFileNameWrapperFileEntryImpl(FileEntry fileEntry) {
		super(fileEntry);
	}

	@Override
	public FileVersion getLatestFileVersion() throws PortalException, SystemException {
		return new DLFileNameWrapperFileVersionImpl(super.getLatestFileVersion());
	}

	@Override
	public FileVersion getFileVersion() throws PortalException, SystemException {
		return new DLFileNameWrapperFileVersionImpl(super.getFileVersion());
	}

	@Override
	public String getTitle() {
		ExpandoBridge expandoBridge = getExpandoBridge();

		String displayTitle = (String) expandoBridge.getAttribute("DLDisplayName");

		if (Validator.isNull(displayTitle)) {
			return super.getTitle();
		}

		return displayTitle;
	}

}

public class DLFileNameWrapperFileVersionImpl extends FileVersionWrapper {

	public DLFileNameWrapperFileVersionImpl(FileVersion fileVersion) {
		super(fileVersion);
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

public class DLFileNameWrapperMBMessageImpl extends MBMessageWrapper {

	public DLFileNameWrapperMBMessageImpl(MBMessage mbMessage) {
		super(mbMessage);
	}

	@Override
	public List<FileEntry> getAttachmentsFileEntries(int start, int end) throws PortalException, SystemException {
		return _wrapFileEntries(super.getAttachmentsFileEntries(start, end));
	}

	@Override
	public List<FileEntry> getDeletedAttachmentsFileEntries(int start, int end) throws PortalException, SystemException {
		return _wrapFileEntries(super.getDeletedAttachmentsFileEntries(start, end));
	}

	private List<FileEntry> _wrapFileEntries(List<FileEntry> fileEntries) {
		List<FileEntry> dlFileNameAttachments = new ArrayList<FileEntry>(fileEntries.size());

		for (FileEntry fileEntry : fileEntries) {
			dlFileNameAttachments.add(new DLFileNameWrapperFileEntryImpl(fileEntry));
		}

		return dlFileNameAttachments;
	}

}

public class DLFileNameWrapperWikiNodeImpl extends WikiNodeWrapper {

	public DLFileNameWrapperWikiNodeImpl(WikiNode wikiNode) {
		super(wikiNode);
	}

	@Override
	public List<FileEntry> getDeletedAttachmentsFiles() throws SystemException {
		return _wrapFileEntries(super.getDeletedAttachmentsFiles());
	}

	private List<FileEntry> _wrapFileEntries(List<FileEntry> fileEntries) {
		List<FileEntry> dlFileNameAttachments = new ArrayList<FileEntry>(fileEntries.size());

		for (FileEntry fileEntry : fileEntries) {
			dlFileNameAttachments.add(new DLFileNameWrapperFileEntryImpl(fileEntry));
		}

		return dlFileNameAttachments;
	}
}

public class DLFileNameWrapperWikiPageImpl extends WikiPageWrapper {

	public DLFileNameWrapperWikiPageImpl(WikiPage page) {
		super(page);
	}

	@Override
	public List<FileEntry> getAttachmentsFileEntries(int start, int end) throws SystemException {
		return _wrapFileEntries(super.getAttachmentsFileEntries(start, end));
	}

	@Override
	public List<FileEntry> getDeletedAttachmentsFileEntries(int start, int end) throws SystemException {
		return _wrapFileEntries(super.getDeletedAttachmentsFileEntries(start, end));
	}

	private List<FileEntry> _wrapFileEntries(List<FileEntry> fileEntries) {
		List<FileEntry> dlFileNameAttachments = new ArrayList<FileEntry>(fileEntries.size());

		for (FileEntry fileEntry : fileEntries) {
			dlFileNameAttachments.add(new DLFileNameWrapperFileEntryImpl(fileEntry));
		}

		return dlFileNameAttachments;
	}

}

private void _wrapRequestFileEntry(HttpServletRequest request, String key) {
	FileEntry fileEntry = (FileEntry)request.getAttribute(key);

	fileEntry = _wrap(fileEntry);

	request.setAttribute(key, fileEntry);
}

private void _wrapRequestFileVersion(HttpServletRequest request, String key) {
	FileVersion fileVersion = (FileVersion)request.getAttribute(key);

	fileVersion = _wrap(fileVersion);

	request.setAttribute(key, fileVersion);
}

private void _wrapRequestMBMessage(HttpServletRequest request, String key) {
	MBMessage message = (MBMessage)request.getAttribute(key);

	message = _wrap(message);

	request.setAttribute(key, message);
}

private void _wrapRequestWikiNode(HttpServletRequest request, String key) {
	WikiNode node = (WikiNode)request.getAttribute(key);

	node = _wrap(node);

	request.setAttribute(key, node);
}

private void _wrapRequestWikiPage(HttpServletRequest request, String key) {
	WikiPage page = (WikiPage)request.getAttribute(key);

	page = _wrap(page);

	request.setAttribute(key, page);
}

private FileEntry _wrap(FileEntry fileEntry) {
	if (fileEntry == null) {
		return fileEntry;
	}

	return new DLFileNameWrapperFileEntryImpl(fileEntry);
}

private FileVersion _wrap(FileVersion fileVersion) {
	if (fileVersion == null) {
		return fileVersion;
	}

	return new DLFileNameWrapperFileVersionImpl(fileVersion);
}

private MBMessage _wrap(MBMessage message) {
	if (message == null) {
		return message;
	}

	return new DLFileNameWrapperMBMessageImpl(message);
}

private WikiNode _wrap(WikiNode node) {
	if (node == null) {
		return node;
	}

	return new DLFileNameWrapperWikiNodeImpl(node);
}

private WikiPage _wrap(WikiPage page) {
	if (page == null) {
		return page;
	}

	return new DLFileNameWrapperWikiPageImpl(page);
}

private static boolean _putThreadLocalEnabled(boolean enabled, String key) {
	ThreadLocalCache<Boolean> threadLocalCache = ThreadLocalCacheManager.getThreadLocalCache(Lifecycle.REQUEST, key);

	Boolean dlFileNameHookEnabled = threadLocalCache.get("isEnabled");

	threadLocalCache.put("isEnabled", enabled);

	if (dlFileNameHookEnabled == null) {
		return false;
	}

	return dlFileNameHookEnabled;
}
%>