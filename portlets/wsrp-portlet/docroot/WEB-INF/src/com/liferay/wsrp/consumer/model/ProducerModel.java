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

package com.liferay.wsrp.consumer.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;

/**
 * <a href="ProducerModel.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface ProducerModel extends BaseModel {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public String getUuid();

	public void setUuid(String uuid);

	public long getProducerId();

	public void setProducerId(long producerId);

	public long getGroupId();

	public void setGroupId(long groupId);

	public long getCompanyId();

	public void setCompanyId(long companyId);

	public long getUserId();

	public void setUserId(long userId);

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public Date getModifiedDate();

	public void setModifiedDate(Date modifiedDate);

	public String getName();

	public void setName(String name);

	public String getWsdlURL();

	public void setWsdlURL(String wsdlURL);

	public String getMarkupEndpoint();

	public void setMarkupEndpoint(String markupEndpoint);

	public String getPortletManagementEndpoint();

	public void setPortletManagementEndpoint(String portletManagementEndpoint);

	public String getRegistrationEndpoint();

	public void setRegistrationEndpoint(String registrationEndpoint);

	public String getServiceDescriptionEndpoint();

	public void setServiceDescriptionEndpoint(String serviceDescriptionEndpoint);

	public String getRegistrationContext();

	public void setRegistrationContext(String registrationContext);

	public String getServiceDescription();

	public void setServiceDescription(String serviceDescription);

	public Producer toEscapedModel();
}