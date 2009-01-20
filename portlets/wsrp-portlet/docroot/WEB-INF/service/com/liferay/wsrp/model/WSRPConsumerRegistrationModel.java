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
 * <a href="WSRPConsumerRegistrationModel.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface WSRPConsumerRegistrationModel extends BaseModel {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getConsumerRegistrationId();

	public void setConsumerRegistrationId(long consumerRegistrationId);

	public String getConsumerName();

	public void setConsumerName(String consumerName);

	public boolean getStatus();

	public boolean isStatus();

	public void setStatus(boolean status);

	public String getRegistrationHandle();

	public void setRegistrationHandle(String registrationHandle);

	public String getRegistrationData();

	public void setRegistrationData(String registrationData);

	public String getLifetimeTerminationTime();

	public void setLifetimeTerminationTime(String lifetimeTerminationTime);

	public String getProducerKey();

	public void setProducerKey(String producerKey);

	public WSRPConsumerRegistration toEscapedModel();
}