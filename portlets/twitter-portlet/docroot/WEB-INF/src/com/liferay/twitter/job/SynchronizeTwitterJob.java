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

package com.liferay.twitter.job;

import com.liferay.portal.kernel.job.IntervalJob;
import com.liferay.portal.kernel.job.JobExecutionContext;
import com.liferay.portal.kernel.job.JobExecutionException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.twitter.service.FeedLocalServiceUtil;
import com.liferay.util.portlet.PortletProps;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="SynchronizeTwitterJob.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SynchronizeTwitterJob implements IntervalJob {

	public static final long INTERVAL = GetterUtil.getLong(PortletProps.get(
		"twitter.synchronization.interval")) * Time.MINUTE;

	public void execute(JobExecutionContext context)
		throws JobExecutionException {

		try {
			FeedLocalServiceUtil.updateFeeds();
		}
		catch (Exception e) {
			_log.error(e.getMessage());
		}
	}

	public long getInterval() {
		return INTERVAL;
	}

	private static Log _log = LogFactory.getLog(SynchronizeTwitterJob.class);

}