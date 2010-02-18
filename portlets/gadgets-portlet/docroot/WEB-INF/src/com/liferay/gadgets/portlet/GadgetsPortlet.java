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

package com.liferay.gadgets.portlet;

import com.liferay.gadgets.model.GadgetsEntry;
import com.liferay.gadgets.service.GadgetsEntryLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.util.servlet.PortletResponseUtil;

import java.io.IOException;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * <a href="GadgetsPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 */
public class GadgetsPortlet extends GenericPortlet {

	public static final String PORTLET_NAME_PREFIX = "GADGETS_";

	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			doRender(renderRequest, renderResponse);
		}
		catch (IOException ioe) {
			throw ioe;
		}
		catch (PortletException pe) {
			throw pe;
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	protected void doRender(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		GadgetsEntry gadgetsEntry = getGadgetsEntry();

		renderResponse.setContentType(ContentTypes.TEXT_HTML_UTF8);

		PortletResponseUtil.write(renderResponse, gadgetsEntry.getXml());
	}

	protected GadgetsEntry getGadgetsEntry() throws Exception {
		String portletName = getPortletConfig().getPortletName();

		int pos = portletName.indexOf(
			StringPool.UNDERLINE, PORTLET_NAME_PREFIX.length());

		long gadgetsEntryId = GetterUtil.getLong(
			portletName.substring(pos + 1));

		GadgetsEntry gadgetsEntry =
			GadgetsEntryLocalServiceUtil.getGadgetsEntry(gadgetsEntryId);

		return gadgetsEntry;
	}

}