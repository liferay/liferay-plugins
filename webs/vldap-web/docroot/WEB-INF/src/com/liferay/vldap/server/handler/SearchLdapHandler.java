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
import com.liferay.vldap.server.directory.Attribute;
import com.liferay.vldap.server.directory.Directory;
import com.liferay.vldap.server.handler.util.LdapHandlerContext;
import com.liferay.vldap.util.PortletPropsValues;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.time.StopWatch;
import org.apache.directory.shared.ldap.entry.Entry;
import org.apache.directory.shared.ldap.filter.AndNode;
import org.apache.directory.shared.ldap.filter.BranchNode;
import org.apache.directory.shared.ldap.filter.EqualityNode;
import org.apache.directory.shared.ldap.filter.ExprNode;
import org.apache.directory.shared.ldap.filter.GreaterEqNode;
import org.apache.directory.shared.ldap.filter.LessEqNode;
import org.apache.directory.shared.ldap.filter.NotNode;
import org.apache.directory.shared.ldap.filter.OrNode;
import org.apache.directory.shared.ldap.filter.PresenceNode;
import org.apache.directory.shared.ldap.filter.SearchScope;
import org.apache.directory.shared.ldap.filter.SubstringNode;
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
				addEntries(
					internalSearchRequest, internalResponses,
					ldapHandlerContext, directory);
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

	protected void addEntries(
			InternalSearchRequest internalSearchRequest,
			List<InternalResponse> internalResponses,
			LdapHandlerContext ldapHandlerContext, Directory directory)
		throws Exception {

		SearchScope searchScope = internalSearchRequest.getScope();

		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		if (searchScope.equals(SearchScope.OBJECT)) {
			addObjectEntry(
				internalSearchRequest, internalResponses, ldapHandlerContext,
				directory, stopWatch);
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
				internalSearchRequest, internalResponses, ldapHandlerContext,
				directory, stopWatch);
		}
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

		ExprNode exprNode = internalSearchRequest.getFilter();

		if (isMatch(exprNode, directory)) {
			internalResponses.add(internalSearchResponseEntry);
		}
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

	protected boolean isMatch(ExprNode exprNode, Directory directory) {
		if (exprNode.isLeaf()) {
			if (exprNode instanceof EqualityNode<?>) {
				EqualityNode<?> equalityNode = (EqualityNode<?>)exprNode;

				String attributeId = equalityNode.getAttribute();
				String value = equalityNode.getValue().getString();

				Attribute attribute = directory.getAttribute(
					attributeId, value);

				if (attribute != null) {
					return true;
				}
				else {
					return false;
				}
			}
			else if (exprNode instanceof GreaterEqNode<?>) {
			}
			else if (exprNode instanceof LessEqNode<?>) {
			}
			else if (exprNode instanceof PresenceNode) {
				PresenceNode specificFilter = (PresenceNode)exprNode;

				String attributeId = specificFilter.getAttribute();

				Attribute attribute = directory.getAttribute(attributeId);

				if (attribute != null) {
					return true;
				}
				else {
					return false;
				}
			}
			else if (exprNode instanceof SubstringNode) {
			}
			else {
				_log.error("Unsupported expression " + exprNode);
			}
		}
		else {
			BranchNode branchNode = (BranchNode)exprNode;

			if (exprNode instanceof AndNode) {
				for (ExprNode childBranchNode : branchNode.getChildren()) {
					if (!isMatch(childBranchNode, directory)) {
						return false;
					}
				}

				return true;
			}
			else if (exprNode instanceof NotNode) {
				for (ExprNode childBranchNode : branchNode.getChildren()) {
					if (!isMatch(childBranchNode, directory)) {
						return true;
					}
					else {
						return false;
					}
				}

				return false;
			}
			else if (exprNode instanceof OrNode) {
				for (ExprNode childBranchNode : branchNode.getChildren()) {
					if (isMatch(childBranchNode, directory)) {
						return true;
					}
				}

				return false;
			}
		}

		return true;
	}

	private static Log _log = LogFactoryUtil.getLog(SearchLdapHandler.class);

}