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
 * <a href="PresenceUserSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PresenceUserSoap implements Serializable {
	public static PresenceUserSoap toSoapModel(PresenceUser model) {
		PresenceUserSoap soapModel = new PresenceUserSoap();

		soapModel.setPresenceUserId(model.getPresenceUserId());
		soapModel.setPresenceStatus(model.getPresenceStatus());

		return soapModel;
	}

	public static PresenceUserSoap[] toSoapModels(List<PresenceUser> models) {
		List<PresenceUserSoap> soapModels = new ArrayList<PresenceUserSoap>(models.size());

		for (PresenceUser model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PresenceUserSoap[soapModels.size()]);
	}

	public PresenceUserSoap() {
	}

	public long getPrimaryKey() {
		return _presenceUserId;
	}

	public void setPrimaryKey(long pk) {
		setPresenceUserId(pk);
	}

	public long getPresenceUserId() {
		return _presenceUserId;
	}

	public void setPresenceUserId(long presenceUserId) {
		_presenceUserId = presenceUserId;
	}

	public long getPresenceStatus() {
		return _presenceStatus;
	}

	public void setPresenceStatus(long presenceStatus) {
		_presenceStatus = presenceStatus;
	}

	private long _presenceUserId;
	private long _presenceStatus;
}