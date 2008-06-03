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

package com.liferay.chat.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="EntrySoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class EntrySoap implements Serializable {
	public static EntrySoap toSoapModel(Entry model) {
		EntrySoap soapModel = new EntrySoap();

		soapModel.setEntryId(model.getEntryId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setContent(model.getContent());
		soapModel.setReceiverUserId(model.getReceiverUserId());

		return soapModel;
	}

	public static EntrySoap[] toSoapModels(List<Entry> models) {
		List<EntrySoap> soapModels = new ArrayList<EntrySoap>(models.size());

		for (Entry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EntrySoap[soapModels.size()]);
	}

	public EntrySoap() {
	}

	public long getPrimaryKey() {
		return _entryId;
	}

	public void setPrimaryKey(long pk) {
		setEntryId(pk);
	}

	public long getEntryId() {
		return _entryId;
	}

	public void setEntryId(long entryId) {
		_entryId = entryId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(long createDate) {
		_createDate = createDate;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public long getReceiverUserId() {
		return _receiverUserId;
	}

	public void setReceiverUserId(long receiverUserId) {
		_receiverUserId = receiverUserId;
	}

	private long _entryId;
	private long _userId;
	private long _createDate;
	private String _content;
	private long _receiverUserId;
}