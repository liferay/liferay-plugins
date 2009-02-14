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

package com.liferay.workflow.model;

import java.io.Serializable;

import java.util.Date;

/**
 * <a href="WorkflowInstance.java.html"><b><i>View Source</i></b></a>
 *
 * @author Charles May
 *
 */
public class WorkflowInstance implements Serializable {

	public WorkflowInstance() {
	}

	public long getInstanceId() {
		return _instanceId;
	}

	public void setInstanceId(long instanceId) {
		_instanceId = instanceId;
	}

	public WorkflowDefinition getDefinition() {
		return _definition;
	}

	public void setDefinition(WorkflowDefinition definition) {
		_definition = definition;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public WorkflowToken getToken() {
		return _token;
	}

	public void setToken(WorkflowToken token) {
		_token = token;
	}

	public boolean isEnded() {
		return _ended;
	}

	public void setEnded(boolean ended) {
		_ended = ended;
	}

	private long _instanceId;
	private WorkflowDefinition _definition;
	private Date _startDate;
	private Date _endDate;
	private WorkflowToken _token;
	private boolean _ended;

}