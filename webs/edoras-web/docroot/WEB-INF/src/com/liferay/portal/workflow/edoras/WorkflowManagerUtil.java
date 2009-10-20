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

package com.liferay.portal.workflow.edoras;

import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.model.CompanyConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="WorkflowManagerUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 */
public class WorkflowManagerUtil {

	public static long getCompanyId(Long tenantId) {
		if (tenantId == null) {
			return CompanyConstants.SYSTEM;
		}

		return tenantId.longValue();
	}

	public static Long getTenantId(long companyId) {
		if (companyId == CompanyConstants.SYSTEM) {
			return null;
		}

		return Long.valueOf(companyId);
	}

	public static List<WorkflowDefinition> wrapWorkflowDefinitions(
		List<com.liferay.portal.workflow.edoras.model.WorkflowDefinition>
		workflowDefinitions) {

		List<WorkflowDefinition> wrappedDefinitions =
			new ArrayList<WorkflowDefinition>(workflowDefinitions.size());

		for (com.liferay.portal.workflow.edoras.model.WorkflowDefinition
				workflowDefinition : workflowDefinitions) {

			wrappedDefinitions.add(
				new WorkflowDefinitionImpl(workflowDefinition));
		}

		return wrappedDefinitions;
	}

}