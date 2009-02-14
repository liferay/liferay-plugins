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

package com.liferay.workflow.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;

import javax.portlet.RenderRequest;

/**
 * <a href="TaskDisplayTerms.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class TaskDisplayTerms extends DisplayTerms {

	public static final String INSTANCE_ID = "instanceId";

	public static final String TASK_NAME = "taskName";

	public static final String DEFINITION_NAME = "definitionName";

	public static final String ASSIGNED_TO = "assignedTo";

	public static final String CREATE_DATE_GT = "createDateGT";

	public static final String CREATE_DATE_LT = "createDateLT";

	public static final String START_DATE_GT = "startDateGT";

	public static final String START_DATE_LT = "startDateLT";

	public static final String END_DATE_GT = "endDateGT";

	public static final String END_DATE_LT = "endDateLT";

	public static final String HIDE_ENDED_TASKS = "hideEndedTasks";

	public TaskDisplayTerms(RenderRequest renderRequest) {
		super(renderRequest);

		instanceId = ParamUtil.getLong(renderRequest, INSTANCE_ID);
		taskName = ParamUtil.getString(renderRequest, TASK_NAME);
		definitionName = ParamUtil.getString(renderRequest, DEFINITION_NAME);
		assignedTo = ParamUtil.getString(renderRequest, ASSIGNED_TO);
		hideEndedTasks = ParamUtil.getBoolean(renderRequest, HIDE_ENDED_TASKS);
	}

	public long getInstanceId() {
		return instanceId;
	}

	public String getInstanceIdString() {
		if (instanceId != 0) {
			return String.valueOf(instanceId);
		}
		else {
			return StringPool.BLANK;
		}
	}

	public String getTaskName() {
		return taskName;
	}

	public String getDefinitionName() {
		return definitionName;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public String getCreateDateGT() {
		return createDateGT;
	}

	public String getCreateDateLT() {
		return createDateLT;
	}

	public String getStartDateGT() {
		return startDateGT;
	}

	public String getStartDateLT() {
		return startDateLT;
	}

	public String getEndDateGT() {
		return endDateGT;
	}

	public String getEndDateLT() {
		return endDateLT;
	}

	public boolean isHideEndedTasks() {
		return hideEndedTasks;
	}

	protected long instanceId;
	protected String taskName;
	protected String definitionName;
	protected String assignedTo;
	protected String createDateGT;
	protected String createDateLT;
	protected String startDateGT;
	protected String startDateLT;
	protected String endDateGT;
	protected String endDateLT;
	protected boolean hideEndedTasks;

}