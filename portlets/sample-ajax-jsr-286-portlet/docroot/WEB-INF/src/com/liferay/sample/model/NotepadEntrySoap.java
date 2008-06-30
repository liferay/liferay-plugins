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

package com.liferay.sample.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="NotepadEntrySoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NotepadEntrySoap implements Serializable {
	public static NotepadEntrySoap toSoapModel(NotepadEntry model) {
		NotepadEntrySoap soapModel = new NotepadEntrySoap();

		soapModel.setNotepadEntryId(model.getNotepadEntryId());
		soapModel.setCategoryId(model.getCategoryId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setNotes(model.getNotes());

		return soapModel;
	}

	public static NotepadEntrySoap[] toSoapModels(List<NotepadEntry> models) {
		List<NotepadEntrySoap> soapModels = new ArrayList<NotepadEntrySoap>(models.size());

		for (NotepadEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NotepadEntrySoap[soapModels.size()]);
	}

	public NotepadEntrySoap() {
	}

	public long getPrimaryKey() {
		return _notepadEntryId;
	}

	public void setPrimaryKey(long pk) {
		setNotepadEntryId(pk);
	}

	public long getNotepadEntryId() {
		return _notepadEntryId;
	}

	public void setNotepadEntryId(long notepadEntryId) {
		_notepadEntryId = notepadEntryId;
	}

	public long getCategoryId() {
		return _categoryId;
	}

	public void setCategoryId(long categoryId) {
		_categoryId = categoryId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public String getNotes() {
		return _notes;
	}

	public void setNotes(String notes) {
		_notes = notes;
	}

	private long _notepadEntryId;
	private long _categoryId;
	private Date _createDate;
	private String _notes;
}