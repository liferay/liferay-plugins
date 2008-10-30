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
 * <a href="PresenceSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PresenceSoap implements Serializable {
	public static PresenceSoap toSoapModel(Presence model) {
		PresenceSoap soapModel = new PresenceSoap();

		soapModel.setPresenceId(model.getPresenceId());
		soapModel.setUserId(model.getUserId());
		soapModel.setStatusId(model.getStatusId());

		return soapModel;
	}

	public static PresenceSoap[] toSoapModels(List<Presence> models) {
		List<PresenceSoap> soapModels = new ArrayList<PresenceSoap>(models.size());

		for (Presence model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PresenceSoap[soapModels.size()]);
	}

	public PresenceSoap() {
	}

	public long getPrimaryKey() {
		return _presenceId;
	}

	public void setPrimaryKey(long pk) {
		setPresenceId(pk);
	}

	public long getPresenceId() {
		return _presenceId;
	}

	public void setPresenceId(long presenceId) {
		_presenceId = presenceId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public int getStatusId() {
		return _statusId;
	}

	public void setStatusId(int statusId) {
		_statusId = statusId;
	}

	private long _presenceId;
	private long _userId;
	private int _statusId;
}