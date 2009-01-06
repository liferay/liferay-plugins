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

package com.liferay.ruon.model;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

/**
 * <a href="PresenceClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PresenceClp extends BaseModelImpl implements Presence {
	public PresenceClp() {
	}

	public long getPrimaryKey() {
		return _presenceId;
	}

	public void setPrimaryKey(long pk) {
		setPresenceId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_presenceId);
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

	public long getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(long modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getNetworkId() {
		return _networkId;
	}

	public void setNetworkId(long networkId) {
		_networkId = networkId;
	}

	public boolean getOnline() {
		return _online;
	}

	public boolean isOnline() {
		return _online;
	}

	public void setOnline(boolean online) {
		_online = online;
	}

	public Presence toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			Presence model = new PresenceClp();

			model.setEscapedModel(true);

			model.setPresenceId(getPresenceId());
			model.setUserId(getUserId());
			model.setModifiedDate(getModifiedDate());
			model.setNetworkId(getNetworkId());
			model.setOnline(getOnline());

			model = (Presence)Proxy.newProxyInstance(Presence.class.getClassLoader(),
					new Class[] { Presence.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		PresenceClp clone = new PresenceClp();

		clone.setPresenceId(getPresenceId());
		clone.setUserId(getUserId());
		clone.setModifiedDate(getModifiedDate());
		clone.setNetworkId(getNetworkId());
		clone.setOnline(getOnline());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		PresenceClp presence = (PresenceClp)obj;

		long pk = presence.getPrimaryKey();

		if (getPrimaryKey() < pk) {
			return -1;
		}
		else if (getPrimaryKey() > pk) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		PresenceClp presence = null;

		try {
			presence = (PresenceClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = presence.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	private long _presenceId;
	private long _userId;
	private long _modifiedDate;
	private long _networkId;
	private boolean _online;
}