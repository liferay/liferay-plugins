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

package com.liferay.chat.model.impl;

import com.liferay.chat.model.Entry;
import com.liferay.chat.model.EntrySoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.portlet.service.PropsUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="EntryModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class EntryModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "Chat_Entry";
	public static final Object[][] TABLE_COLUMNS = {
			{ "entryId", new Integer(Types.BIGINT) },
			

			{ "createDate", new Integer(Types.BIGINT) },
			

			{ "fromUserId", new Integer(Types.BIGINT) },
			

			{ "toUserId", new Integer(Types.BIGINT) },
			

			{ "content", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table Chat_Entry (entryId LONG not null primary key,createDate LONG,fromUserId LONG,toUserId LONG,content STRING null)";
	public static final String TABLE_SQL_DROP = "drop table Chat_Entry";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.chat.model.Entry"),
			true);

	public static Entry toModel(EntrySoap soapModel) {
		Entry model = new EntryImpl();

		model.setEntryId(soapModel.getEntryId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setFromUserId(soapModel.getFromUserId());
		model.setToUserId(soapModel.getToUserId());
		model.setContent(soapModel.getContent());

		return model;
	}

	public static List<Entry> toModels(EntrySoap[] soapModels) {
		List<Entry> models = new ArrayList<Entry>(soapModels.length);

		for (EntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(PropsUtil.get(
				"lock.expiration.time.com.liferay.chat.model.Entry"));

	public EntryModelImpl() {
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
		if (entryId != _entryId) {
			_entryId = entryId;
		}
	}

	public long getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(long createDate) {
		if (createDate != _createDate) {
			_createDate = createDate;
		}
	}

	public long getFromUserId() {
		return _fromUserId;
	}

	public void setFromUserId(long fromUserId) {
		if (fromUserId != _fromUserId) {
			_fromUserId = fromUserId;
		}
	}

	public long getToUserId() {
		return _toUserId;
	}

	public void setToUserId(long toUserId) {
		if (toUserId != _toUserId) {
			_toUserId = toUserId;
		}
	}

	public String getContent() {
		return GetterUtil.getString(_content);
	}

	public void setContent(String content) {
		if (((content == null) && (_content != null)) ||
				((content != null) && (_content == null)) ||
				((content != null) && (_content != null) &&
				!content.equals(_content))) {
			_content = content;
		}
	}

	public Entry toEscapedModel() {
		if (isEscapedModel()) {
			return (Entry)this;
		}
		else {
			Entry model = new EntryImpl();

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
		EntryImpl clone = new EntryImpl();

		clone.setEntryId(getEntryId());
		clone.setCreateDate(getCreateDate());
		clone.setFromUserId(getFromUserId());
		clone.setToUserId(getToUserId());
		clone.setContent(getContent());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		EntryImpl entry = (EntryImpl)obj;

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

		EntryImpl entry = null;

		try {
			entry = (EntryImpl)obj;
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

	private long _entryId;
	private long _createDate;
	private long _fromUserId;
	private long _toUserId;
	private String _content;
}