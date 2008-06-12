/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.mail.portlet;

import com.liferay.mail.util.MailBoxManager;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.jsp.JSPPortlet;
import com.liferay.util.mail.JavaMailUtil;
import com.liferay.util.servlet.PortletResponseUtil;
import com.liferay.util.servlet.ServletResponseUtil;

import java.io.IOException;
import java.io.InputStream;

import javax.mail.MessagingException;
import javax.mail.Part;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <a href="MailPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 *
 */
public class MailPortlet extends JSPPortlet {

	public void processAction(ActionRequest req, ActionResponse res)
		throws IOException, PortletException {

		try {
			int accountId = ParamUtil.getInteger(req, "accountId");
			String folderName = ParamUtil.getString(req, "folderName");
			int messageUid = ParamUtil.getInteger(req, "messageUid");
			String fileName = ParamUtil.getString(req, "fileName");
			String contentPath = ParamUtil.getString(req, "contentPath");

			ThemeDisplay themeDisplay = (ThemeDisplay)req.getAttribute(
				WebKeys.THEME_DISPLAY);

			MailBoxManager mailBoxManager = new MailBoxManager(
				themeDisplay.getUser(), accountId);

			Part messagePart = mailBoxManager.getAttachment(
				folderName, messageUid, contentPath);

			byte[] content = JavaMailUtil.getBytes(messagePart);
			String contentType = messagePart.getContentType();

			HttpServletResponse httpRes = PortalUtil.getHttpServletResponse(
				res);

			req.setAttribute("com.liferay.portal.servlet.filters.strip.StripFilterSKIP_FILTER", Boolean.TRUE);

			ServletResponseUtil.sendFile(httpRes, fileName, content, contentType);

		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void serveResource(ResourceRequest req, ResourceResponse res)
		throws IOException, PortletException {

		String jspPage = req.getParameter("jspPage");

		if (jspPage.equals("/attachment.jsp")) {
			HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(req);

			int accountId = ParamUtil.getInteger(req, "accountId");
			String folderName = ParamUtil.getString(req, "folderName");
			int messageUid = ParamUtil.getInteger(req, "messageUid");
			String fileName = ParamUtil.getString(req, "fileName");
			String contentPath = ParamUtil.getString(req, "contentPath");

			try {
				MailBoxManager mailBoxManager = new MailBoxManager(
					PortalUtil.getUser(httpReq), accountId);

				Part messagePart = mailBoxManager.getAttachment(
					folderName, messageUid, contentPath);

				InputStream is = messagePart.getInputStream();
				String contentType = MimeTypesUtil.getContentType(fileName);

				PortletResponseUtil.sendFile(res, fileName, is, contentType);
			}
			catch (MessagingException me) {
			}
			catch (PortalException pe) {
			}
			catch (SystemException se) {
			}
		}
		else {
			super.serveResource(req, res);
		}
	}

}