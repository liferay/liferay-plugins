/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.vldap.server.handler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.vldap.server.handler.util.Directory;
import com.liferay.vldap.server.handler.util.LdapHandlerContext;
import com.liferay.vldap.util.PortletPropsValues;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.time.StopWatch;
import org.apache.directory.shared.ldap.entry.Entry;
import org.apache.directory.shared.ldap.filter.SearchScope;
import org.apache.directory.shared.ldap.message.ResultCodeEnum;
import org.apache.directory.shared.ldap.message.SearchResponseEntryImpl;
import org.apache.directory.shared.ldap.message.internal.InternalRequest;
import org.apache.directory.shared.ldap.message.internal.InternalResponse;
import org.apache.directory.shared.ldap.message.internal.InternalSearchRequest;
import org.apache.directory.shared.ldap.message.internal.InternalSearchResponseEntry;
import org.apache.mina.core.session.IoSession;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class SearchLdapHandler extends BaseLdapHandler {

	public List<InternalResponse> messageReceived(
		InternalRequest internalRequest, IoSession ioSession,
		LdapHandlerContext ldapHandlerContext) {

		InternalSearchRequest internalSearchRequest =
			(InternalSearchRequest)internalRequest;

		List<InternalResponse> internalResponses =
			new ArrayList<InternalResponse>();

		try {
			Directory directory = ldapHandlerContext.getDirectory();

			directory = directory.findBase(internalSearchRequest.getBase());

			if (directory != null) {
				SearchScope searchScope = internalSearchRequest.getScope();

				StopWatch stopWatch = new StopWatch();

				stopWatch.start();

				if (searchScope.equals(SearchScope.OBJECT)) {
					addObjectEntry(
						internalSearchRequest, internalResponses,
						ldapHandlerContext, directory, stopWatch);
				}
				else if (searchScope.equals(SearchScope.ONELEVEL)) {
					for (Directory curDirectory : directory.getDirectories()) {
						addObjectEntry(
							internalSearchRequest, internalResponses,
							ldapHandlerContext, curDirectory, stopWatch);
					}
				}
				else if (searchScope.equals(SearchScope.SUBTREE)) {
					addSubtreeEntries(
						internalSearchRequest, internalResponses,
						ldapHandlerContext, directory, stopWatch);
				}
			}

			internalResponses.add(getInternalResponse(internalRequest));
		}
		catch (SearchSizeLimitException ssle) {
			internalResponses.add(
				getInternalResponse(
					internalRequest, ResultCodeEnum.SIZE_LIMIT_EXCEEDED));
		}
		catch (SearchTimeLimitException ssle) {
			internalResponses.add(
				getInternalResponse(
					internalRequest, ResultCodeEnum.TIME_LIMIT_EXCEEDED));
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return internalResponses;
	}

	protected void addObjectEntry(
			InternalSearchRequest internalSearchRequest,
			List<InternalResponse> internalResponses,
			LdapHandlerContext ldapHandlerContext, Directory directory,
			StopWatch stopWatch)
		throws Exception {

		InternalSearchResponseEntry internalSearchResponseEntry =
			new SearchResponseEntryImpl(
				internalSearchRequest.getMessageId());

		Entry entry = directory.toEntry(
			ldapHandlerContext.getSchemaManager(),
			internalSearchRequest.getAttributes());

		internalSearchResponseEntry.setEntry(entry);

		if (internalResponses.size() > getSizeLimit(internalSearchRequest)) {
			throw new SearchSizeLimitException();
		}

		if ((stopWatch.getTime() / Time.SECOND) >
				getTimeLimit(internalSearchRequest)) {

			throw new SearchTimeLimitException();
		}

		internalResponses.add(internalSearchResponseEntry);
	}

	protected void addSubtreeEntries(
			InternalSearchRequest internalSearchRequest,
			List<InternalResponse> internalResponses,
			LdapHandlerContext ldapHandlerContext, Directory directory,
			StopWatch stopWatch)
		throws Exception {

		addObjectEntry(
			internalSearchRequest, internalResponses, ldapHandlerContext,
			directory, stopWatch);

		for (Directory curDirectory : directory.getDirectories()) {
			addSubtreeEntries(
				internalSearchRequest, internalResponses, ldapHandlerContext,
				curDirectory, stopWatch);
		}
	}

	protected long getSizeLimit(InternalSearchRequest internalSearchRequest) {
		long sizeLimit = internalSearchRequest.getSizeLimit();

		if ((sizeLimit == 0) ||
			(sizeLimit > PortletPropsValues.SEARCH_MAX_SIZE)) {

			sizeLimit = PortletPropsValues.SEARCH_MAX_SIZE;
		}

		return sizeLimit;
	}

	protected int getTimeLimit(InternalSearchRequest internalSearchRequest) {
		int timeLimit = internalSearchRequest.getTimeLimit();

		if ((timeLimit == 0) ||
			(timeLimit > PortletPropsValues.SEARCH_MAX_TIME)) {

			timeLimit = PortletPropsValues.SEARCH_MAX_TIME;
		}

		return timeLimit;
	}

	private static Log _log = LogFactoryUtil.getLog(SearchLdapHandler.class);

}