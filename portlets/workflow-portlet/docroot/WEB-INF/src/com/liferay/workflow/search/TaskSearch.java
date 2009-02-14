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

package com.liferay.workflow.search;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.workflow.model.WorkflowTask;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;

/**
 * <a href="TaskSearch.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class TaskSearch extends SearchContainer<WorkflowTask> {

	static List<String> headerNames = new ArrayList<String>();

	static {
		headerNames.add("task-id");
		headerNames.add("task-name");
		headerNames.add("instance-id");
		headerNames.add("definition-name");
		headerNames.add("assigned-to");
		headerNames.add("create-date");
		headerNames.add("start-date");
		headerNames.add("end-date");
	}

	public static final String EMPTY_RESULTS_MESSAGE = "no-tasks-were-found";

	public TaskSearch(RenderRequest renderRequest, PortletURL iteratorURL) {
		super(
			renderRequest, new TaskDisplayTerms(renderRequest),
			new TaskSearchTerms(renderRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		TaskDisplayTerms displayTerms = (TaskDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			TaskDisplayTerms.INSTANCE_ID,
			String.valueOf(displayTerms.getInstanceId()));
		iteratorURL.setParameter(
			TaskDisplayTerms.TASK_NAME, displayTerms.getTaskName());
		iteratorURL.setParameter(
			TaskDisplayTerms.DEFINITION_NAME, displayTerms.getDefinitionName());
		iteratorURL.setParameter(
			TaskDisplayTerms.ASSIGNED_TO, displayTerms.getAssignedTo());
		iteratorURL.setParameter(
			TaskDisplayTerms.HIDE_ENDED_TASKS,
			String.valueOf(displayTerms.isHideEndedTasks()));
	}

}