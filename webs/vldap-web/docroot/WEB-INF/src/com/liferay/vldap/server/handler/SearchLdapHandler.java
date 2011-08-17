/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
import com.liferay.vldap.server.directory.DirectoryTree;
import com.liferay.vldap.server.directory.SearchBase;
import com.liferay.vldap.server.directory.ldap.Directory;
import com.liferay.vldap.server.handler.util.LdapHandlerContext;
import com.liferay.vldap.util.PortletPropsValues;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.time.StopWatch;
import org.apache.directory.shared.ldap.model.entry.Entry;
import org.apache.directory.shared.ldap.model.filter.AndNode;
import org.apache.directory.shared.ldap.model.filter.BranchNode;
import org.apache.directory.shared.ldap.model.filter.EqualityNode;
import org.apache.directory.shared.ldap.model.filter.ExprNode;
import org.apache.directory.shared.ldap.model.filter.GreaterEqNode;
import org.apache.directory.shared.ldap.model.filter.LeafNode;
import org.apache.directory.shared.ldap.model.filter.LessEqNode;
import org.apache.directory.shared.ldap.model.filter.NotNode;
import org.apache.directory.shared.ldap.model.filter.OrNode;
import org.apache.directory.shared.ldap.model.filter.PresenceNode;
import org.apache.directory.shared.ldap.model.filter.SubstringNode;
import org.apache.directory.shared.ldap.model.message.Request;
import org.apache.directory.shared.ldap.model.message.Response;
import org.apache.directory.shared.ldap.model.message.ResultCodeEnum;
import org.apache.directory.shared.ldap.model.message.SearchRequest;
import org.apache.directory.shared.ldap.model.message.SearchResultEntry;
import org.apache.directory.shared.ldap.model.message.SearchResultEntryImpl;
import org.apache.directory.shared.ldap.model.message.SearchScope;
import org.apache.mina.core.session.IoSession;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class SearchLdapHandler extends BaseLdapHandler {

	public List<Response> messageReceived(
		Request request, IoSession ioSession,
		LdapHandlerContext ldapHandlerContext) {

		SearchRequest searchRequest = (SearchRequest)request;

		List<Response> responses = new ArrayList<Response>();

		try {
			addObjectEntries(searchRequest, responses, ldapHandlerContext);

			responses.add(getResponse(searchRequest));
		}
		catch (SearchSizeLimitException ssle) {
			responses.add(
				getResponse(
					searchRequest, ResultCodeEnum.SIZE_LIMIT_EXCEEDED));
		}
		catch (SearchTimeLimitException ssle) {
			responses.add(
				getResponse(
					searchRequest, ResultCodeEnum.TIME_LIMIT_EXCEEDED));
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return responses;
	}

	protected void addObjectEntries(
			SearchRequest searchRequest, List<Response> responses,
			LdapHandlerContext ldapHandlerContext)
		throws Exception {

		DirectoryTree directoryTree = new DirectoryTree();

		SearchBase searchBase = directoryTree.getSearchBase(
			searchRequest.getBase(), getSizeLimit(searchRequest));

		if (searchBase == null) {
			return;
		}

		Directory directory = searchBase.getDirectory();

		if (directory == null) {
			return;
		}

		long sizeLimit = getSizeLimit(searchRequest);

		searchBase.setSizeLimit(sizeLimit);

		SearchScope searchScope = searchRequest.getScope();

		if (searchScope.equals(SearchScope.OBJECT) ||
			searchScope.equals(SearchScope.SUBTREE)) {

			if (isMatch(directory, searchRequest.getFilter())) {
				StopWatch stopWatch = new StopWatch();

				stopWatch.start();

				addObjectEntry(
					searchRequest, responses, ldapHandlerContext, directory,
					stopWatch);

				searchBase.setSizeLimit(searchBase.getSizeLimit() - 1);
			}
		}

		if (searchScope.equals(SearchScope.ONELEVEL) ||
			searchScope.equals(SearchScope.SUBTREE)) {

			StopWatch stopWatch = new StopWatch();

			stopWatch.start();

			List<Directory> subdirectories = directoryTree.getDirectories(
				searchBase, searchRequest.getFilter(), searchScope);

			for (Directory subdirectory : subdirectories) {
				addObjectEntry(
					searchRequest, responses, ldapHandlerContext, subdirectory,
					stopWatch);
			}
		}
	}

	protected void addObjectEntry(
			SearchRequest searchRequest, List<Response> responses,
			LdapHandlerContext ldapHandlerContext, Directory directory,
			StopWatch stopWatch)
		throws Exception {

		SearchResultEntry searchResponseEntry =
			new SearchResultEntryImpl(searchRequest.getMessageId());

		Entry entry = directory.toEntry(searchRequest.getAttributes());

		searchResponseEntry.setEntry(entry);

		if (responses.size() >= getSizeLimit(searchRequest)) {
			throw new SearchSizeLimitException();
		}

		if ((stopWatch.getTime() / Time.SECOND) >
				getTimeLimit(searchRequest)) {

			throw new SearchTimeLimitException();
		}

		// These are commented out because we probably don't need to filter
		// the nodes that we got back from the search, because the search
		// is now efficient enough to return only nodes that would have matched
		// the filter. However, we could still filter them here also just to
		// make sure. I haven't tried every query, so in some cases we may have
		// nodes that need to be filtered here.

		//ExprNode filter = searchRequest.getFilter();

		//if (isMatch(filter, directory)) {
			responses.add(searchResponseEntry);
		//}
	}

	protected long getSizeLimit(SearchRequest searchRequest) {
		long sizeLimit = searchRequest.getSizeLimit();

		if ((sizeLimit == 0) ||
			(sizeLimit > PortletPropsValues.SEARCH_MAX_SIZE)) {

			sizeLimit = PortletPropsValues.SEARCH_MAX_SIZE;
		}

		return sizeLimit;
	}

	protected int getTimeLimit(SearchRequest searchRequest) {
		int timeLimit = searchRequest.getTimeLimit();

		if ((timeLimit == 0) ||
			(timeLimit > PortletPropsValues.SEARCH_MAX_TIME)) {

			timeLimit = PortletPropsValues.SEARCH_MAX_TIME;
		}

		return timeLimit;
	}

	protected boolean isMatch(Directory directory, ExprNode exprNode) {
		if (exprNode.isLeaf()) {
			LeafNode leafNode = (LeafNode)exprNode;

			return isMatchLeafNode(directory, leafNode);
		}
		else {
			BranchNode branchNode = (BranchNode)exprNode;

			return isMatchBranchNode(directory, branchNode);
		}
	}

	protected boolean isMatchBranchNode(
		Directory directory, BranchNode branchNode) {

		if (branchNode instanceof AndNode) {
			for (ExprNode exprNode : branchNode.getChildren()) {
				if (!isMatch(directory, exprNode)) {
					return false;
				}
			}

			return true;
		}
		else if (branchNode instanceof NotNode) {
			for (ExprNode exprNode : branchNode.getChildren()) {
				if (!isMatch(directory, exprNode)) {
					return true;
				}
				else {
					return false;
				}
			}

			return false;
		}
		else if (branchNode instanceof OrNode) {
			for (ExprNode exprNode : branchNode.getChildren()) {
				if (isMatch(directory, exprNode)) {
					return true;
				}
			}

			return false;
		}

		if (_log.isWarnEnabled()) {
			_log.warn("Unsupported expression " + branchNode);
		}

		return true;
	}

	protected boolean isMatchLeafNode(Directory directory, LeafNode leafNode) {
		if (leafNode instanceof EqualityNode<?>) {
			EqualityNode<?> equalityNode = (EqualityNode<?>)leafNode;

			String attributeId = equalityNode.getAttribute();
			String value = equalityNode.getValue().getString();

			if (directory.hasAttribute(attributeId, value)) {
				return true;
			}
			else {
				return false;
			}
		}
		else if (leafNode instanceof GreaterEqNode<?>) {
		}
		else if (leafNode instanceof LessEqNode<?>) {
		}
		else if (leafNode instanceof PresenceNode) {
			PresenceNode presenceNode = (PresenceNode)leafNode;

			String attributeId = presenceNode.getAttribute();

			if (directory.hasAttribute(attributeId)) {
				return true;
			}
			else {
				return false;
			}
		}
		else if (leafNode instanceof SubstringNode) {
		}

		if (_log.isWarnEnabled()) {
			_log.warn("Unsupported expression " + leafNode);
		}

		return true;
	}

	private static Log _log = LogFactoryUtil.getLog(SearchLdapHandler.class);

}