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

package com.liferay.sample.model.impl;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;

import com.liferay.portlet.service.BaseModelImpl;
import com.liferay.portlet.service.PropsUtil;

import com.liferay.sample.model.NotepadEntry;
import com.liferay.sample.model.NotepadEntrySoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="NotepadEntryModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NotepadEntryModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "Sample_NotepadEntry";
	public static final Object[][] TABLE_COLUMNS = {
			{ "notepadEntryId", new Integer(Types.BIGINT) },
			

			{ "categoryId", new Integer(Types.BIGINT) },
			

			{ "createDate", new Integer(Types.TIMESTAMP) },
			

			{ "notes", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table Sample_NotepadEntry (notepadEntryId LONG not null primary key,categoryId LONG,createDate DATE null,notes STRING null)";
	public static final String TABLE_SQL_DROP = "drop table Sample_NotepadEntry";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.sample.model.NotepadEntry"),
			true);

	public static NotepadEntry toModel(NotepadEntrySoap soapModel) {
		NotepadEntry model = new NotepadEntryImpl();

		model.setNotepadEntryId(soapModel.getNotepadEntryId());
		model.setCategoryId(soapModel.getCategoryId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setNotes(soapModel.getNotes());

		return model;
	}

	public static List<NotepadEntry> toModels(NotepadEntrySoap[] soapModels) {
		List<NotepadEntry> models = new ArrayList<NotepadEntry>(soapModels.length);

		for (NotepadEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(PropsUtil.get(
				"lock.expiration.time.com.liferay.sample.model.NotepadEntry"));

	public NotepadEntryModelImpl() {
	}

	public long getPrimaryKey() {
		return _notepadEntryId;
	}

	public void setPrimaryKey(long pk) {
		setNotepadEntryId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_notepadEntryId);
	}

	public long getNotepadEntryId() {
		return _notepadEntryId;
	}

	public void setNotepadEntryId(long notepadEntryId) {
		if (notepadEntryId != _notepadEntryId) {
			_notepadEntryId = notepadEntryId;
		}
	}

	public long getCategoryId() {
		return _categoryId;
	}

	public void setCategoryId(long categoryId) {
		if (categoryId != _categoryId) {
			_categoryId = categoryId;
		}
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		if (((createDate == null) && (_createDate != null)) ||
				((createDate != null) && (_createDate == null)) ||
				((createDate != null) && (_createDate != null) &&
				!createDate.equals(_createDate))) {
			_createDate = createDate;
		}
	}

	public String getNotes() {
		return GetterUtil.getString(_notes);
	}

	public void setNotes(String notes) {
		if (((notes == null) && (_notes != null)) ||
				((notes != null) && (_notes == null)) ||
				((notes != null) && (_notes != null) && !notes.equals(_notes))) {
			_notes = notes;
		}
	}

	public NotepadEntry toEscapedModel() {
		if (isEscapedModel()) {
			return (NotepadEntry)this;
		}
		else {
			NotepadEntry model = new NotepadEntryImpl();

			model.setEscapedModel(true);

			model.setNotepadEntryId(getNotepadEntryId());
			model.setCategoryId(getCategoryId());
			model.setCreateDate(getCreateDate());
			model.setNotes(HtmlUtil.escape(getNotes()));

			model = (NotepadEntry)Proxy.newProxyInstance(NotepadEntry.class.getClassLoader(),
					new Class[] { NotepadEntry.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		NotepadEntryImpl clone = new NotepadEntryImpl();

		clone.setNotepadEntryId(getNotepadEntryId());
		clone.setCategoryId(getCategoryId());
		clone.setCreateDate(getCreateDate());
		clone.setNotes(getNotes());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		NotepadEntryImpl notepadEntry = (NotepadEntryImpl)obj;

		int value = 0;

		value = DateUtil.compareTo(getCreateDate(), notepadEntry.getCreateDate());

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

		NotepadEntryImpl notepadEntry = null;

		try {
			notepadEntry = (NotepadEntryImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = notepadEntry.getPrimaryKey();

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

	private long _notepadEntryId;
	private long _categoryId;
	private Date _createDate;
	private String _notes;
}