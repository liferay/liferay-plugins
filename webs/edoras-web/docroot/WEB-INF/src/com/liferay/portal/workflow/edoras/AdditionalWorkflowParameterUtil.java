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

import com.liferay.portal.workflow.edoras.dao.model.WorkflowEntityBridgeUtil;

import java.util.Map;

import org.edorasframework.process.api.setup.Default;


/**
 * <a href="AdditionalWorkflowParameterConstants.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 */
public class AdditionalWorkflowParameterUtil {
	public static final String WF_SETUP_ID = "setupId";
	
	public static final String WF_TENANT_ID = "tenantId";
	
	public static Class<?> getDeclaredSetupId(
		
		Map<String, Object> additionalParameters) {
		if (additionalParameters == null) {
			return Default.class;
		}
		
		String setupIdName = (String) additionalParameters.get(WF_SETUP_ID);
		if (setupIdName == null) {
			return Default.class;
		}

		return WorkflowEntityBridgeUtil.getSetupClassForName(setupIdName);
	}
	
	public static Long getDeclaredTenantId(
		Map<String, Object> additionalParameters) {

		if (additionalParameters == null) {
			return null;
		}

		String tenantId = (String) additionalParameters.get(WF_TENANT_ID);
		if (tenantId == null) {
			return null;
		}

		return Long.valueOf(tenantId);
	}
}
