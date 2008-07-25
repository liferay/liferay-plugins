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

package com.liferay.ruon.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="PresenceStatusSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PresenceStatusSoap implements Serializable {
	public static PresenceStatusSoap toSoapModel(PresenceStatus model) {
		PresenceStatusSoap soapModel = new PresenceStatusSoap();

		soapModel.setPresenceStatusId(model.getPresenceStatusId());
		soapModel.setPresenceStatusMessage(model.getPresenceStatusMessage());

		return soapModel;
	}

	public static PresenceStatusSoap[] toSoapModels(List<PresenceStatus> models) {
		List<PresenceStatusSoap> soapModels = new ArrayList<PresenceStatusSoap>(models.size());

		for (PresenceStatus model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PresenceStatusSoap[soapModels.size()]);
	}

	public PresenceStatusSoap() {
	}

	public long getPrimaryKey() {
		return _presenceStatusId;
	}

	public void setPrimaryKey(long pk) {
		setPresenceStatusId(pk);
	}

	public long getPresenceStatusId() {
		return _presenceStatusId;
	}

	public void setPresenceStatusId(long presenceStatusId) {
		_presenceStatusId = presenceStatusId;
	}

	public String getPresenceStatusMessage() {
		return _presenceStatusMessage;
	}

	public void setPresenceStatusMessage(String presenceStatusMessage) {
		_presenceStatusMessage = presenceStatusMessage;
	}

	private long _presenceStatusId;
	private String _presenceStatusMessage;
}