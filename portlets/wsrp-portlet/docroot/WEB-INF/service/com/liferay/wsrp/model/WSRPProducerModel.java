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

package com.liferay.wsrp.model;

import com.liferay.portal.model.BaseModel;

/**
 * <a href="WSRPProducerModel.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface WSRPProducerModel extends BaseModel {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getProducerId();

	public void setProducerId(long producerId);

	public String getPortalId();

	public void setPortalId(String portalId);

	public boolean getStatus();

	public boolean isStatus();

	public void setStatus(boolean status);

	public String getNamespace();

	public void setNamespace(String namespace);

	public String getInstanceName();

	public void setInstanceName(String instanceName);

	public boolean getRequiresRegistration();

	public boolean isRequiresRegistration();

	public void setRequiresRegistration(boolean requiresRegistration);

	public boolean getSupportsInbandRegistration();

	public boolean isSupportsInbandRegistration();

	public void setSupportsInbandRegistration(
		boolean supportsInbandRegistration);

	public String getVersion();

	public void setVersion(String version);

	public String getOfferedPortlets();

	public void setOfferedPortlets(String offeredPortlets);

	public String getProducerProfileMap();

	public void setProducerProfileMap(String producerProfileMap);

	public String getRegistrationProperties();

	public void setRegistrationProperties(String registrationProperties);

	public String getRegistrationValidatorClass();

	public void setRegistrationValidatorClass(String registrationValidatorClass);

	public WSRPProducer toEscapedModel();
}