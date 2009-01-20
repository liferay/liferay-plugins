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

package com.liferay.samplestrutsliferay.struts.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.util.PortalUtil;

import java.io.File;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <a href="UploadAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class UploadAction extends PortletAction {

	public void processAction(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(
			actionRequest);

		File file = uploadRequest.getFile("file_location");

		if (_log.isInfoEnabled()) {
			_log.info(file);
		}

		setForward(
			actionRequest,
			"/sample_struts_liferay_portlet/upload_success?actionURL=true" +
				"&file_name=" + HttpUtil.encodeURL(file.getName()));
	}

	public ActionForward render(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		return mapping.findForward(
			"portlet.sample_struts_liferay_portlet.upload");
	}

	private static Log _log = LogFactoryUtil.getLog(UploadAction.class);

}