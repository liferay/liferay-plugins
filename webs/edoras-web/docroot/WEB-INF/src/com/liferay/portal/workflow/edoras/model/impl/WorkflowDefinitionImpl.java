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

import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.workflow.edoras.model.WorkflowDefinition;

import org.edorasframework.process.api.service.MutableProcessModelDefinition;

/**
 * <a href="WorkflowDefinitionImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 * @author Brian Wing Shun Chan
 */
public class WorkflowDefinitionImpl
	extends WorkflowDefinitionModelImpl
	implements WorkflowDefinition, MutableProcessModelDefinition,
	WorkflowEntity {

	public WorkflowDefinitionImpl() {
	}

	public String getGraphicalProcessModelAsXML() {
		return getGraphicalXml();
	}

	public String getModelDesignerVersion() {
		return getDesignerVersion();
	}

	public String getProcessModelAsXML() {
		return getModelXml();
	}

	public String getProcessModelId() {
		return getName();
	}

	public int getProcessModelVersion() {
		return getVersion();
	}

	public Long getTenantId() {
		long tenantId = getCompanyId();

		if (tenantId == CompanyConstants.SYSTEM) {
			return null;
		}
		else {
			return tenantId;
		}
	}

	public void setGraphicalProcessModelAsXML(String xmlModel) {
		setGraphicalXml(xmlModel);
	}

	public void setModelDesignerVersion(String designerVersion) {
		setDesignerVersion(designerVersion);
	}

	public void setProcessModelAsXML(String xmlModel) {
		setModelXml(xmlModel);
	}

	public void setProcessModelId(String modelId) {
		setName(modelId);
	}

	public void setProcessModelVersion(int modelVersion) {
		setVersion(modelVersion);
	}

	public void setTenantId(Long tenantId) {
		if (tenantId == null) {
			setCompanyId(CompanyConstants.SYSTEM);
		}
		else {
			setCompanyId(tenantId.longValue());
		}
	}

}