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

package com.liferay.wol.service.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;

import com.liferay.wol.service.ClpSerializer;
import com.liferay.wol.service.JIRAActionLocalServiceUtil;
import com.liferay.wol.service.JIRAChangeGroupLocalServiceUtil;
import com.liferay.wol.service.JIRAChangeItemLocalServiceUtil;
import com.liferay.wol.service.JIRAIssueLocalServiceUtil;
import com.liferay.wol.service.MeetupsEntryLocalServiceUtil;
import com.liferay.wol.service.MeetupsRegistrationLocalServiceUtil;
import com.liferay.wol.service.SVNRepositoryLocalServiceUtil;
import com.liferay.wol.service.SVNRevisionLocalServiceUtil;
import com.liferay.wol.service.WallEntryLocalServiceUtil;

/**
 * <a href="ClpMessageListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ClpMessageListener implements MessageListener {
	public static final String SERVLET_CONTEXT_NAME = ClpSerializer.SERVLET_CONTEXT_NAME;

	public void receive(Message message) {
		try {
			doReceive(message);
		}
		catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}

	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");
		String servletContextName = message.getString("servletContextName");

		if (command.equals("undeploy") &&
				servletContextName.equals(SERVLET_CONTEXT_NAME)) {
			JIRAActionLocalServiceUtil.clearService();

			JIRAChangeGroupLocalServiceUtil.clearService();

			JIRAChangeItemLocalServiceUtil.clearService();

			JIRAIssueLocalServiceUtil.clearService();

			MeetupsEntryLocalServiceUtil.clearService();

			MeetupsRegistrationLocalServiceUtil.clearService();

			SVNRepositoryLocalServiceUtil.clearService();

			SVNRevisionLocalServiceUtil.clearService();

			WallEntryLocalServiceUtil.clearService();
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ClpMessageListener.class);
}