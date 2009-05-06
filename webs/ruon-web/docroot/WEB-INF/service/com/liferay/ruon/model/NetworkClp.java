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
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

/**
 * <a href="NetworkClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NetworkClp extends BaseModelImpl<Network> implements Network {
	public NetworkClp() {
	}

	public long getPrimaryKey() {
		return _networkId;
	}

	public void setPrimaryKey(long pk) {
		setNetworkId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_networkId);
	}

	public long getNetworkId() {
		return _networkId;
	}

	public void setNetworkId(long networkId) {
		_networkId = networkId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public long getTtl() {
		return _ttl;
	}

	public void setTtl(long ttl) {
		_ttl = ttl;
	}

	public Network toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			Network model = new NetworkClp();

			model.setEscapedModel(true);

			model.setNetworkId(getNetworkId());
			model.setName(HtmlUtil.escape(getName()));
			model.setTtl(getTtl());

			model = (Network)Proxy.newProxyInstance(Network.class.getClassLoader(),
					new Class[] { Network.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		NetworkClp clone = new NetworkClp();

		clone.setNetworkId(getNetworkId());
		clone.setName(getName());
		clone.setTtl(getTtl());

		return clone;
	}

	public int compareTo(Network network) {
		long pk = network.getPrimaryKey();

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

		NetworkClp network = null;

		try {
			network = (NetworkClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = network.getPrimaryKey();

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

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("{networkId=");
		sb.append(getNetworkId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", ttl=");
		sb.append(getTtl());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<model><model-name>");
		sb.append("com.liferay.ruon.model.Network");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>networkId</column-name><column-value><![CDATA[");
		sb.append(getNetworkId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ttl</column-name><column-value><![CDATA[");
		sb.append(getTtl());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _networkId;
	private String _name;
	private long _ttl;
}