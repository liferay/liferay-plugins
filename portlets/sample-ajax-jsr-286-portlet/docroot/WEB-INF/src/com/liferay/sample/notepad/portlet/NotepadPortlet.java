/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
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

package com.liferay.sample.notepad.portlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.sample.model.NotepadEntry;
import com.liferay.sample.service.NotepadEntryLocalServiceUtil;
import com.liferay.util.bridges.jsp.JSPPortlet;

/**
 * <a href="JSPPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 *
 */
public class NotepadPortlet extends JSPPortlet {

	public void serveResource(ResourceRequest req, ResourceResponse res)
		throws IOException, PortletException {

		try {
			String cmd = ParamUtil.getString(req, Constants.CMD);

			String jsonObjString = StringPool.BLANK;

			if (cmd.equals(Constants.ADD)) {
				jsonObjString = addNotepadEntry(req);
			}
			else if (cmd.equals(Constants.DELETE)) {
				jsonObjString = deleteNotepadEntry(req);
			}

			if (Validator.isNotNull(cmd)) {
				String contentType = "application/json";

				res.setContentType(contentType);
				res.getWriter().write(jsonObjString);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	protected String addNotepadEntry(ResourceRequest req) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)req.getAttribute(
			WebKeys.THEME_DISPLAY);

		long categoryId = ParamUtil.getLong(req, "categoryid");
		String notes = ParamUtil.getString(req, "notes");

		String createDate = StringPool.BLANK;
		String categoryImage = StringPool.BLANK;

		DateFormat dateDisplayDate =
			new SimpleDateFormat("MMM d", themeDisplay.getLocale());
		DateFormat dateDisplayTime =
			new SimpleDateFormat("h:mm a", themeDisplay.getLocale());
		DateFormat dateFormatDate =
			DateFormat.getDateInstance(
				DateFormat.MEDIUM, themeDisplay.getLocale());

		Date now = new Date();
		Date today = dateFormatDate.parse(dateFormatDate.format(now));

		NotepadEntry notepadEntry =
			NotepadEntryLocalServiceUtil.addNotepadEntry(categoryId, notes);

		if (categoryId == 1) {
			categoryImage = "important.png";
		}
		else if (categoryId == 2) {
			categoryImage = "work.png";
		}
		else if (categoryId == 3) {
			categoryImage = "social.png";
		}
		else if (categoryId == 4) {
			categoryImage = "other.png";
		}
		else {
			categoryImage = "spacer.png";
		}

		if (notepadEntry.getCreateDate().before(today)) {
			createDate = dateDisplayDate.format(notepadEntry.getCreateDate());
		}
		else {
			createDate = dateDisplayTime.format(notepadEntry.getCreateDate());
		}

		JSONObject jsonObj = new JSONObject();

		jsonObj.put("createDate", createDate);
		jsonObj.put("notes", notepadEntry.getNotes());
		jsonObj.put("notepadEntryId", notepadEntry.getNotepadEntryId());
		jsonObj.put("categoryImage", categoryImage);

		return jsonObj.toString();
	}

	protected String deleteNotepadEntry(ResourceRequest req) throws Exception {
		long notepadEntryId = ParamUtil.getLong(req, "notepadEntryId");

		try {
			NotepadEntryLocalServiceUtil.deleteNotepadEntry(notepadEntryId);
		}
		catch (Exception e) {
			_log.error(notepadEntryId + " does not exist.");
		}

		JSONObject jsonObj = new JSONObject();

		jsonObj.put("notepadEntryId", notepadEntryId);

		return jsonObj.toString();
	}

	private static Log _log = LogFactory.getLog(NotepadPortlet.class);

}