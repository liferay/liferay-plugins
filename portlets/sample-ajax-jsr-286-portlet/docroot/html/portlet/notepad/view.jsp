<%
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
%>

<%@ include file="/html/portlet/init.jsp" %>

<div class="notepad-entry" id="<portlet:namespace />notepad-entry">
	<div class="portlet-msg-info" id="<portlet:namespace />notepad-status">Add a new note!</div>

	<div class="notepad-header">

		<%
		Object[][] labelValuesArray = new Object[][] {
			new Object[] {
				"Important", notepadImagePath + "important.png", "notepad-important"
			},
			new Object[] {
				"Work", notepadImagePath + "work.png", "notepad-work"
			},
			new Object[] {
				"Social", notepadImagePath + "social.png", "notepad-social"
			},
			new Object[] {
				"Other", notepadImagePath + "other.png", "notepad-other"
			},
			new Object[] {
				"None", notepadImagePath + "none.png", "notepad-none current"
			}
		};

		for (int i = 0; i < labelValuesArray.length; i++) {
			Object[] labelValues = labelValuesArray[i];
		%>

			<span class="<%= labelValues[2] %>" categoryid="<%= i+1 %>">
				<img src="<%= labelValues[1] %>" /> <%= labelValues[0] %>
			</span>

		<%
		}
		%>

	</div>

	<div class="notepad-text-field">
		<liferay-ui:input-field model="<%= NotepadEntry.class %>" bean="<%= null %>" field="notes" />
	</div>

	<input class="notepad-entry-button" id="<portlet:namespace />notepad-entry-button" type="button" value="<liferay-ui:message key="post" />" onclick="javascript: Liferay.Notepad.addNotepadEntry();" />
</div>

<a action="collapse" id="<portlet:namespace />notepad-entry-link" href="javascript: Liferay.Notepad.notepadLink();">Compact view »</a>

<div class="notes-container separator"><!-- --></div>

<fieldset class="notes-container block-labels">
	<legend id="<portlet:namespace />notes-legend">Notepad</legend>

	<table class="lfr-table notes-table">
	<tr id="notes-spacer">
		<td class="notes-category"><!-- --></td>
		<td class="notes"><!-- --></td>
	</tr>

	<%
	List<NotepadEntry> results = NotepadEntryLocalServiceUtil.getAllNotepadEntries();

	for (int i = 0; i < results.size(); i++) {
		NotepadEntry notepadEntry = results.get(i);

		long categoryId = notepadEntry.getCategoryId();
		String createDate = StringPool.BLANK;
		String closeImagePath = notepadImagePath + "close.png";
		StringBuilder categoryImagePath = new StringBuilder(notepadImagePath);

		if (categoryId == 1) {
			categoryImagePath.append("important.png");
		}
		else if (categoryId == 2) {
			categoryImagePath.append("work.png");
		}
		else if (categoryId == 3) {
			categoryImagePath.append("social.png");
		}
		else if (categoryId == 4) {
			categoryImagePath.append("other.png");
		}
		else {
			categoryImagePath.append("spacer.png");
		}

		if (notepadEntry.getCreateDate().before(today)) {
			createDate = dateDisplayDate.format(notepadEntry.getCreateDate());
		}
		else {
			createDate = dateDisplayTime.format(notepadEntry.getCreateDate());
		}
	%>

		<tr class="notes-holder" id="<portlet:namespace /><%= notepadEntry.getNotepadEntryId() %>">
			<td class="notes-category" valign="top">
				<img src="<%= categoryImagePath.toString() %>" />
			</td>
			<td class="notes">
				<span class="notes-controller">
					<span class="notes-date" valign="top"><%= createDate %></span>

					<span class="notes-delete" valign="top">
						<a href="javascript: Liferay.Notepad.deleteNotepadEntry(<%= notepadEntry.getNotepadEntryId() %>);"><img src="<%= closeImagePath %>" /></a>
					</span>
				</span>

				<%= notepadEntry.getNotes() %>
			</td>
		</tr>

	<%
	}
	%>

	</table>
</fieldset>

<portlet:resourceURL var="notepadURL" escapeXml="false">
	<param name="p_l_id" value="<%= themeDisplay.getPlid() %>" />
</portlet:resourceURL>

<script type="text/javascript">
	jQuery(function() {
		Liferay.Notepad.init({
			addConstant: '<%= Constants.ADD %>',
			deleteConstant: '<%= Constants.DELETE %>',
			imagePath: '<%= notepadImagePath %>',
			infoMessage: 'Add a new note!',
			namespace: '<portlet:namespace />',
			notepadShowAllText: 'Full view »',
			notepadShowLessText: 'Compact view »',
			successText: '<liferay-ui:message key="your-request-processed-successfully" />',
			url: '<%= notepadURL %>'
		});
	});
</script>