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

package com.liferay.alfrescocontent.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.portlet.PortletProps;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.alfresco.webservice.accesscontrol.AccessControlServiceSoapBindingStub;
import org.alfresco.webservice.accesscontrol.AccessStatus;
import org.alfresco.webservice.accesscontrol.HasPermissionsResult;
import org.alfresco.webservice.content.Content;
import org.alfresco.webservice.content.ContentServiceSoapBindingStub;
import org.alfresco.webservice.repository.QueryResult;
import org.alfresco.webservice.repository.RepositoryServiceSoapBindingStub;
import org.alfresco.webservice.types.NamedValue;
import org.alfresco.webservice.types.Node;
import org.alfresco.webservice.types.Predicate;
import org.alfresco.webservice.types.Query;
import org.alfresco.webservice.types.Reference;
import org.alfresco.webservice.types.ResultSet;
import org.alfresco.webservice.types.ResultSetRow;
import org.alfresco.webservice.types.Store;
import org.alfresco.webservice.util.AuthenticationUtils;
import org.alfresco.webservice.util.Constants;
import org.alfresco.webservice.util.WebServiceFactory;

/**
 * <a href="AlfrescoContentUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 * @author Brian Wing Shun Chan
 * @author Jerry Niu
 *
 */
public class AlfrescoContentUtil {

	public static ResultSetRow[] getChildNodes(
			String userId, String password, String uuid)
		throws Exception {

		try {
			AuthenticationUtils.startSession(userId, password);

			RepositoryServiceSoapBindingStub repositoryService =
				WebServiceFactory.getRepositoryService();

			Reference reference = null;

			if (Validator.isNull(uuid)) {
				Predicate predicate = new Predicate(null, _spacesStore, null);

				Node[] nodes = repositoryService.get(predicate);

				reference = nodes[0].getReference();
			}
			else {
				reference = new Reference(_spacesStore, uuid, null);
			}

			QueryResult result = repositoryService.queryChildren(reference);

			ResultSetRow[] rows = result.getResultSet().getRows();

			return rows;
		}
		finally {
			AuthenticationUtils.endSession();
		}
	}

	public static String getContent(
			String userId, String password, String uuid, String path)
		throws Exception {

		try {
			if (Validator.isNotNull(path)) {
				uuid = null;
			}
			else if (Validator.isNotNull(uuid)) {
				path = null;
			}
			else {
				return null;
			}

			AuthenticationUtils.startSession(userId, password);

			ContentServiceSoapBindingStub contentService =
				WebServiceFactory.getContentService();

			Reference reference = new Reference(_spacesStore, uuid, path);

			Predicate predicate = new Predicate(
				new Reference[] {reference}, _spacesStore, null);

			Content[] contents = contentService.read(
				predicate, Constants.PROP_CONTENT);

			String ticket = AuthenticationUtils.getTicket();

			String content = HttpUtil.URLtoString(
				contents[0].getUrl() + "?ticket=" + ticket);

			return content;
		}
		finally {
			AuthenticationUtils.endSession();
		}
	}

	public static String getContent(
			String userId, String password, String uuid, String path,
			boolean maximizeLinks, RenderResponse res)
		throws Exception {

		String content = getContent(userId, password, uuid, path);

		return formatContent(content, maximizeLinks, res);
	}

	public static String getNamedValue(NamedValue[] namedValues, String name) {
		String value = null;

		for (int i = 0; i < namedValues.length; i++) {
			NamedValue namedValue = namedValues[i];

			if (namedValue.getName().endsWith(name)) {
				value = namedValue.getValue();
			}
		}

		return value;
	}

	public static Node getNode(String userId, String password, String uuid)
		throws Exception {

		try {
			if (Validator.isNull(uuid)) {
				return null;
			}

			AuthenticationUtils.startSession(userId, password);

			RepositoryServiceSoapBindingStub repositoryService =
				WebServiceFactory.getRepositoryService();

			Reference reference = new Reference(_spacesStore, uuid, null);

			Predicate predicate = new Predicate(
				new Reference[] {reference}, _spacesStore, null);

			Node[] nodes = repositoryService.get(predicate);

			return nodes[0];
		}
		finally {
			AuthenticationUtils.endSession();
		}
	}

	public static ResultSetRow[] getParentNodes(
			String userId, String password, String uuid)
		throws Exception {

		try {
			AuthenticationUtils.startSession(userId, password);

			RepositoryServiceSoapBindingStub repositoryService =
				WebServiceFactory.getRepositoryService();

			Reference reference = null;

			if (Validator.isNull(uuid)) {
				reference = new Reference(_spacesStore, null, null);
			}
			else {
				reference = new Reference(_spacesStore, uuid, null);
			}

			QueryResult result = repositoryService.queryParents(reference);

			ResultSetRow[] rows = result.getResultSet().getRows();

			return rows;
		}
		finally {
			AuthenticationUtils.endSession();
		}
	}

	public static ResultSetRow[] getSearchResults(
			String userId, String password, String keywords)
		throws Exception {

		try {
			AuthenticationUtils.startSession(userId, password);

			RepositoryServiceSoapBindingStub repositoryService =
				WebServiceFactory.getRepositoryService();

			Query query = new Query(
				Constants.QUERY_LANG_LUCENE, "TEXT:'" + keywords + "'");

			QueryResult queryResult = repositoryService.query(
				_spacesStore, query, false);

			ResultSet resultSet = queryResult.getResultSet();

			return resultSet.getRows();
		}
		finally {
			AuthenticationUtils.endSession();
		}
	}

	public static String formatContent(
		String content, boolean maximizeLinks, RenderResponse renderResponse) {

		if (content == null) {
			return null;
		}

		Matcher matcher = _proxyUrlPattern.matcher(content);

		StringBuffer sb = new StringBuffer();

		try {
			while (matcher.find()) {
				String uuid = matcher.group(1);

				PortletURL portletURL = renderResponse.createRenderURL();

				portletURL.setParameter("uuid", uuid);

				if (maximizeLinks) {
					portletURL.setWindowState(WindowState.MAXIMIZED);
				}

				matcher.appendReplacement(
					sb, "\"" + portletURL.toString() + "\"");
			}
		}
		catch (Exception e) {
			_log.error(e);
		}

		matcher.appendTail(sb);

		content = sb.toString();

		matcher = _resourceUrlPattern.matcher(content);

		sb = new StringBuffer();

		try {
			while (matcher.find()) {
				String imagePath = matcher.group(1);

				matcher.appendReplacement(
					sb,
					"\"" + getEndpointAddress() + "/alfresco" + imagePath +
						"?guest=true" + "\"");
			}
		}
		catch (Exception e) {
			_log.error(e);
		}

		matcher.appendTail(sb);

		content = sb.toString();

		return content;
	}

	public static boolean hasPermission(
		String login, String password, String uuid, String action) {

		try {
			AccessControlServiceSoapBindingStub accessControlService =
				WebServiceFactory.getAccessControlService();

			AuthenticationUtils.startSession(login, password);

			Reference reference = new Reference(_spacesStore, uuid, null);

			Predicate predicate = new Predicate(
				new Reference[] {reference}, _spacesStore, null);

			HasPermissionsResult[] results =
				accessControlService.hasPermissions(
					predicate, new String[] {action});

			if ((results.length == 1) &&
				(results[0].getPermission().equals(action)) &&
				(results[0].getAccessStatus().equals(AccessStatus.acepted))) {

				return true;
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Could not start session for " + login + " " + password +
						" " + uuid + " " + action);
			}
		}
		finally {
			AuthenticationUtils.endSession();
		}

		return false;
	}

	public static String getEndpointAddress() {
		String endPoint = PortletProps.get("content.server.url");

		if (_log.isDebugEnabled()) {
			_log.debug("Using endpoint " + endPoint);
		}

		return endPoint;
	}

	private static Log _log = LogFactoryUtil.getLog(AlfrescoContentUtil.class);

	private static Pattern _proxyUrlPattern = Pattern.compile(
		"\"workspace://SpacesStore/([\\w\\-]*)\"");
	private static Pattern _resourceUrlPattern = Pattern.compile(
		"\"(?:\\.\\.)?(?:/\\.\\.)*" +
			"(/download/direct/workspace/SpacesStore/[\\w\\-/\\.]*)\"");
 	private static Store _spacesStore = new Store(
		Constants.WORKSPACE_STORE, "SpacesStore");

}