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

package com.liferay.iweb.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="InterestGroupSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class InterestGroupSoap implements Serializable {
	public static InterestGroupSoap toSoapModel(InterestGroup model) {
		InterestGroupSoap soapModel = new InterestGroupSoap();

		soapModel.setCid(model.getCid());
		soapModel.setType(model.getType());

		return soapModel;
	}

	public static InterestGroupSoap[] toSoapModels(List<InterestGroup> models) {
		List<InterestGroupSoap> soapModels = new ArrayList<InterestGroupSoap>(models.size());

		for (InterestGroup model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new InterestGroupSoap[soapModels.size()]);
	}

	public InterestGroupSoap() {
	}

	public long getPrimaryKey() {
		return _cid;
	}

	public void setPrimaryKey(long pk) {
		setCid(pk);
	}

	public long getCid() {
		return _cid;
	}

	public void setCid(long cid) {
		_cid = cid;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	private long _cid;
	private int _type;
}