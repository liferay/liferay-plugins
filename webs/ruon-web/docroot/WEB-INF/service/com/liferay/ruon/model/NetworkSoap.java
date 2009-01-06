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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="NetworkSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NetworkSoap implements Serializable {
	public static NetworkSoap toSoapModel(Network model) {
		NetworkSoap soapModel = new NetworkSoap();

		soapModel.setNetworkId(model.getNetworkId());
		soapModel.setName(model.getName());
		soapModel.setTtl(model.getTtl());

		return soapModel;
	}

	public static NetworkSoap[] toSoapModels(List<Network> models) {
		List<NetworkSoap> soapModels = new ArrayList<NetworkSoap>(models.size());

		for (Network model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NetworkSoap[soapModels.size()]);
	}

	public NetworkSoap() {
	}

	public long getPrimaryKey() {
		return _networkId;
	}

	public void setPrimaryKey(long pk) {
		setNetworkId(pk);
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

	private long _networkId;
	private String _name;
	private long _ttl;
}