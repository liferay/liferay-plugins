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

package com.liferay.wsrp.admin.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Layout;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.jsp.JSPPortlet;
import com.liferay.wsrp.consumer.NoSuchProducerException;
import com.liferay.wsrp.consumer.model.Producer;
import com.liferay.wsrp.consumer.service.ProducerServiceUtil;

/**
 * <a href="AdminPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 *
 */
public class AdminPortlet extends JSPPortlet {

	public void processAction(ActionRequest req, ActionResponse res)
		throws PortletException {

		String cmd = ParamUtil.getString(req, Constants.CMD);

		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				updateProducer(req);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteProducer(req);
			}
		}
		catch (Exception e) {
			if (e instanceof NoSuchProducerException ||
				e instanceof PrincipalException) {

				SessionErrors.add(req, e.getClass().getName());
			}
			else {
				throw new PortletException(e);
			}
		}
		
		String redirect = ParamUtil.getString(req, "redirect");

		try {
			res.sendRedirect(redirect);
		}
		catch (IOException e) {
			throw new PortletException(e);
		}
	}

	public void doView(RenderRequest req, RenderResponse res)
		throws PortletException {

		String cmd = ParamUtil.getString(req, Constants.CMD);
		
		try {
			if (cmd.equals(Constants.ADD)) {
				include("/admin/edit_producer.jsp", req, res);
			}	
			else if (cmd.equals(Constants.UPDATE)) {
				long producerId = ParamUtil.getLong(req, "producerId");
	
				Producer producer = ProducerServiceUtil.getProducer(producerId);
				
				req.setAttribute("producer", producer);
				
				include("/admin/edit_producer.jsp", req, res);
			}
			else {
				include("/admin/view.jsp", req, res);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	protected void deleteProducer(ActionRequest req) throws Exception {
		long producerId = ParamUtil.getLong(req, "producerId");

		ProducerServiceUtil.deleteProducer(producerId);
	}

	protected void updateProducer(ActionRequest req) throws Exception {
		ThemeDisplay themeDisplay = 
			(ThemeDisplay)req.getAttribute(WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();
		
		String name = ParamUtil.getString(req, "name");
		String wsdlURL = ParamUtil.getString(req, "wsdlURL");

		long producerId = ParamUtil.getLong(req, "producerId");
		
		if (producerId <= 0) {
			ProducerServiceUtil.addProducer(layout.getPlid(), name, wsdlURL);	
		}
		else {
			ProducerServiceUtil.updateProducer(producerId, name, wsdlURL);	
		}
	}

}
