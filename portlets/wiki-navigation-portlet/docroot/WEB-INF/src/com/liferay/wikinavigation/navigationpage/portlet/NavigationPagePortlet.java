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

package com.liferay.wikinavigation.navigationpage.portlet;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageServiceUtil;
import com.liferay.util.bridges.jsp.JSPPortlet;
import com.liferay.wikinavigation.WebKeys;
import com.liferay.wikinavigation.util.EntryDisplay;

import java.io.IOException;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * <a href="NavigationPagePortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Thiago Moreira
 * @author Jorge Ferrer
 *
 */
public class NavigationPagePortlet extends JSPPortlet {

	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			PortletPreferences prefs = renderRequest.getPreferences();

			long nodeId = GetterUtil.getLong(
				prefs.getValue("node-id", StringPool.BLANK));
			String title = prefs.getValue("title", null);

			if ((nodeId > 0) && Validator.isNotNull(title)) {

				WikiPage wikiPage = WikiPageServiceUtil.getPage(nodeId, title);

				List<EntryDisplay>entries = _buildNavigationTree(
					wikiPage, renderResponse.createRenderURL());

				renderRequest.setAttribute(WebKeys.NAVIGATION_ENTRIES, entries);
				renderRequest.setAttribute(WebKeys.WIKI_PAGE, wikiPage);
			}
		}
		catch (Exception e) {
			SessionErrors.add(renderRequest, e.getClass().getName(), e);
		}

		super.doView(renderRequest, renderResponse);
	}

	private List<EntryDisplay> _buildNavigationTree(
			WikiPage page, PortletURL portletURL)
		throws Exception {

		String regex = "((==\\s((.)*)\\s==)*(\\Q[[\\E((.)*)\\Q]]\\E)*)*";

		Pattern pattern = Pattern.compile(regex);

		List<EntryDisplay> entries = new LinkedList<EntryDisplay>();

		Matcher matcher =  pattern.matcher(page.getContent());

		EntryDisplay parent = null;

		while (matcher.find()) {
			String title = matcher.group(3);

			if (title != null) {
				parent = new EntryDisplay();

				parent.setLabel(title);

				entries.add(parent);
			}

			if (parent == null) {
				parent = new EntryDisplay();

				parent.setLabel(StringPool.BLANK);

				entries.add(parent);
			}

			String temp = matcher.group(6);

			if (temp != null) {
				EntryDisplay child = new EntryDisplay();

				int index = temp.indexOf("|");

				if (index != -1) {
					String url = temp.substring(0, index);
					String label = temp.substring(index + 1, temp.length());

					if (!url.startsWith("http")) {
						portletURL.setParameter("title", url);
						portletURL.setParameter(
							"nodeId", String.valueOf(page.getNodeId()));

						url = portletURL.toString();
					}
					else {
						child.setExternalURL(true);
					}

					child.setLabel(label);
					child.setUrl(url);
				}
				else {
					String url = temp;

					if (!url.startsWith("http")) {
						portletURL.setParameter("title", url);
						portletURL.setParameter(
							"nodeId", String.valueOf(page.getNodeId()));

						url = portletURL.toString();
					}

					child.setLabel(temp);
					child.setUrl(url);
				}

				parent.addChild(child);
			}
		}

		return entries;
	}

}