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

package com.liferay.portal.workflow.edoras.model.impl;

import com.liferay.portal.workflow.edoras.model.WorkflowInstance;
import com.liferay.portal.workflow.edoras.service.persistence.WorkflowInstanceUtil;

import java.util.List;

import org.edorasframework.process.api.ex.ProcessException;

/**
 * <a href="WorkflowInstanceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 * @author Brian Wing Shun Chan
 */
public class WorkflowInstanceImpl
	extends WorkflowInstanceModelImpl implements WorkflowInstance {

	public WorkflowInstanceImpl() {
	}

	public List<WorkflowInstance> getChildren() {
		try {
			return WorkflowInstanceUtil.findByParentWorkflowInstanceId(
				getPrimaryKey());
		}
		catch (Exception e) {
			throw new ProcessException(e.getMessage(), e);
		}
	}

	public WorkflowInstance getParent() {
		if (getParentWorkflowInstanceId() == 0) {
			return null;
		}

		try {
			return WorkflowInstanceUtil.findByPrimaryKey(
				getParentWorkflowInstanceId());
		}
		catch (Exception e) {
			throw new ProcessException(e.getMessage(), e);
		}
	}

}