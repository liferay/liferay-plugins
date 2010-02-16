/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

/**
 * <a href="EntryClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class EntryClp extends BaseModelImpl<Entry> implements Entry {
	public EntryClp() {
	}

	public long getPrimaryKey() {
		return _entryId;
	}

	public void setPrimaryKey(long pk) {
		setEntryId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_entryId);
	}

	public long getEntryId() {
		return _entryId;
	}

	public void setEntryId(long entryId) {
		_entryId = entryId;
	}

	public long getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(long createDate) {
		_createDate = createDate;
	}

	public long getFromUserId() {
		return _fromUserId;
	}

	public void setFromUserId(long fromUserId) {
		_fromUserId = fromUserId;
	}

	public String getFromUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getFromUserId(), "uuid", _fromUserUuid);
	}

	public void setFromUserUuid(String fromUserUuid) {
		_fromUserUuid = fromUserUuid;
	}

	public long getToUserId() {
		return _toUserId;
	}

	public void setToUserId(long toUserId) {
		_toUserId = toUserId;
	}

	public String getToUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getToUserId(), "uuid", _toUserUuid);
	}

	public void setToUserUuid(String toUserUuid) {
		_toUserUuid = toUserUuid;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public Entry toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			Entry model = new EntryClp();

			model.setEscapedModel(true);

			model.setEntryId(getEntryId());
			model.setCreateDate(getCreateDate());
			model.setFromUserId(getFromUserId());
			model.setToUserId(getToUserId());
			model.setContent(HtmlUtil.escape(getContent()));

			model = (Entry)Proxy.newProxyInstance(Entry.class.getClassLoader(),
					new Class[] { Entry.class }, new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		EntryClp clone = new EntryClp();

		clone.setEntryId(getEntryId());
		clone.setCreateDate(getCreateDate());
		clone.setFromUserId(getFromUserId());
		clone.setToUserId(getToUserId());
		clone.setContent(getContent());

		return clone;
	}

	public int compareTo(Entry entry) {
		int value = 0;

		if (getCreateDate() < entry.getCreateDate()) {
			value = -1;
		}
		else if (getCreateDate() > entry.getCreateDate()) {
			value = 1;
		}
		else {
			value = 0;
		}

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		EntryClp entry = null;

		try {
			entry = (EntryClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = entry.getPrimaryKey();

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
		StringBundler sb = new StringBundler(11);

		sb.append("{entryId=");
		sb.append(getEntryId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", fromUserId=");
		sb.append(getFromUserId());
		sb.append(", toUserId=");
		sb.append(getToUserId());
		sb.append(", content=");
		sb.append(getContent());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.chat.model.Entry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>entryId</column-name><column-value><![CDATA[");
		sb.append(getEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fromUserId</column-name><column-value><![CDATA[");
		sb.append(getFromUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>toUserId</column-name><column-value><![CDATA[");
		sb.append(getToUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>content</column-name><column-value><![CDATA[");
		sb.append(getContent());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _entryId;
	private long _createDate;
	private long _fromUserId;
	private String _fromUserUuid;
	private long _toUserId;
	private String _toUserUuid;
	private String _content;
}