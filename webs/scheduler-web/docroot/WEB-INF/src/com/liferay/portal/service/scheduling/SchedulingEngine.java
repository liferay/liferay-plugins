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

package com.liferay.portal.service.scheduling;

import java.util.Collection;
import java.util.Date;

import com.liferay.portal.kernel.job.SchedulingRequest;

/**
 * <a href="SchedulingEngine.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 * 
 */
public interface SchedulingEngine {
	
	public static final String CRON_TEXT = "cronText";
	
	public static final String DESTINATION_NAME = "destinationName";

	public static final String MESSAGE_BODY = "messageBody";
    
	public Collection<SchedulingRequest> retrieveScheduledJobs(String groupName) 
		throws SchedulingException;

	public void schedule(
			String jobName, String groupName, String cronText, 
    		String destinationName, String messageBody, Date startDate, 
    		Date endDate)
    	throws SchedulingException;

	public void shutdown() throws SchedulingException;

	public void start() throws SchedulingException;

	public void unschedule(String jobName, String groupName)
    	throws SchedulingException;
}