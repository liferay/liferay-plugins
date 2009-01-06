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
 * <a href="InstanceDisplayTerms.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class InstanceDisplayTerms extends DisplayTerms {

	public static final String DEFINITION_ID = "definitionId";

	public static final String INSTANCE_ID = "instanceId";

	public static final String DEFINITION_NAME = "definitionName";

	public static final String DEFINITION_VERSION = "definitionVersion";

	public static final String START_DATE_GT = "startDateGT";

	public static final String START_DATE_LT = "startDateLT";

	public static final String END_DATE_GT = "endDateGT";

	public static final String END_DATE_LT = "endDateLT";

	public static final String HIDE_ENDED_TASKS = "hideEndedTasks";

	public InstanceDisplayTerms(RenderRequest renderRequest) {
		super(renderRequest);

		definitionId = ParamUtil.getLong(renderRequest, DEFINITION_ID);
		instanceId = ParamUtil.getLong(renderRequest, INSTANCE_ID);
		definitionName = ParamUtil.getString(renderRequest, DEFINITION_NAME);
		definitionVersion = ParamUtil.getString(
			renderRequest, DEFINITION_VERSION);
		hideEndedTasks = ParamUtil.getBoolean(renderRequest, HIDE_ENDED_TASKS);
	}

	public long getDefinitionId() {
		return definitionId;
	}

	public String getDefinitionIdString() {
		if (definitionId != 0) {
			return String.valueOf(definitionId);
		}
		else {
			return StringPool.BLANK;
		}
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

	public String getDefinitionName() {
		return definitionName;
	}

	public String getDefinitionVersion() {
		return definitionVersion;
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

	protected long definitionId;
	protected long instanceId;
	protected String definitionName;
	protected String definitionVersion;
	protected String startDateGT;
	protected String startDateLT;
	protected String endDateGT;
	protected String endDateLT;
	protected boolean hideEndedTasks;

}