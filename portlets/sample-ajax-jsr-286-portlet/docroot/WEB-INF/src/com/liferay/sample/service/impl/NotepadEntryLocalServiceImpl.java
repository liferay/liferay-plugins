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

package com.liferay.sample.service.impl;

import java.util.Date;
import java.util.List;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;
import com.liferay.sample.NoSuchNotepadEntryException;
import com.liferay.sample.model.NotepadEntry;
import com.liferay.sample.service.base.NotepadEntryLocalServiceBaseImpl;

/**
 * <a href="NotepadEntryLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 *
 */
public class NotepadEntryLocalServiceImpl
	extends NotepadEntryLocalServiceBaseImpl {

	public NotepadEntry addNotepadEntry(long categoryId, String notes)
		throws SystemException {

		// Notepad entry

		Date now = new Date();

		long wallEntryId = CounterLocalServiceUtil.increment();

		NotepadEntry notepadEntry = notepadEntryPersistence.create(wallEntryId);

		notepadEntry.setCategoryId(categoryId);
		notepadEntry.setCreateDate(now);
		notepadEntry.setNotes(notes);

		notepadEntryPersistence.update(notepadEntry, false);

		return notepadEntry;
	}

	public void deleteNotepadEntry(long notepadEntryId)
		throws NoSuchNotepadEntryException, SystemException {

		// Notepad entry

		notepadEntryPersistence.remove(notepadEntryId);
	}

	public List<NotepadEntry> getAllNotepadEntries()
		throws SystemException {

		// Notepad entry

		return notepadEntryPersistence.findAll();
	}

}