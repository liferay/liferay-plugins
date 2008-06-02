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

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<script type="text/javascript">

	jQuery(
		function() {
			Liferay.Mail.init(
				{
					sendBodyEditor : window.<portlet:namespace />editor
				}
			);
		}
	);

	function <portlet:namespace />initEditor() {
		return '';
	}
</script>

<table id="account-container">
<tr>
	<td id="selection">
		<select id="account-selection">
		</select>
	</td>
	<td id="status-div">
		<span id="status" />
		<span id="debug" />
	</td>
	<td id="search">
		<input id="search-text">

		<input id="search-button" type="button" value="<liferay-ui:message key="search" />" />
	</td>
</tr>
</table>

<table id="email-container">
<tr>
	<td id="email-left-column">
		<div id="compose-mail">
			<liferay-ui:message key="compose-email" />
		</div>

		<div id="folders">
			<div class="folder" folderName="INBOX">
				<a href="javascript: ;">INBOX</a>
			</div>
		</div>
	</td>
	<td id="email-right-column">
		<div id="folder" folderName="">
			<table class="folder-controls">
			<tr>
				<td class="actions">
					<div>
						<input class="delete" type="button" value="<liferay-ui:message key="delete" />" />

						<select class="select-actions">
							<option value="none"><liferay-ui:message key="more-actions" /></option>

							<option value="read">- <liferay-ui:message key="mark-as-read" /></option>

							<option value="unread">- <liferay-ui:message key="mark-as-unread" /></option>
						</select>

						<a class="refresh" href="javascript: ;"><liferay-ui:message key="refresh" /></a>
					</div>

					<div>
						<liferay-ui:message key="select" />:

						<a class="select-all" href="javascript: ;"><liferay-ui:message key="all" /></a>,

						<a class="select-none" href="javascript: ;"><liferay-ui:message key="none" /></a>,

						<a class="select-read" href="javascript: ;"><liferay-ui:message key="read" /></a>,

						<a class="select-unread" href="javascript: ;"><liferay-ui:message key="unread" /></a>
					</div>
				</td>
				<td class="navigation">
					<a class="newest" href="javascript: ;">&laquo; <liferay-ui:message key="newest" /></a>

					<a class="newer" href="javascript: ;">&lt; <liferay-ui:message key="newer" /></a>

					<span class="status">? - ? of ???</span>

					<a class="older" href="javascript: ;"><liferay-ui:message key="older" /> &gt;</a>

					<a class="oldest" href="javascript: ;"><liferay-ui:message key="oldest" /> &raquo;</a>
				</td>
			</tr>
			</table>

			<table id="message-list">
			<tr>
				<td class="alert"><liferay-ui:message key="no-messages-found" /></td>
			</tr>
			</table>

			<table class="folder-controls">
			<tr>
				<td class="actions">
					<div>
						<liferay-ui:message key="select" />:

						<a class="select-all" href="javascript: ;"><liferay-ui:message key="all" /></a>,

						<a class="select-none" href="javascript: ;"><liferay-ui:message key="none" /></a>,

						<a class="select-read" href="javascript: ;"><liferay-ui:message key="read" /></a>,

						<a class="select-unread" href="javascript: ;"><liferay-ui:message key="unread" /></a>
					</div>

					<div>
						<input class="delete" type="button" value="<liferay-ui:message key="delete" />" />

						<select class="select-actions">
							<option value="none"><liferay-ui:message key="more-actions" /></option>

							<option value="read">- <liferay-ui:message key="mark-as-read" /></option>

							<option value="unread">- <liferay-ui:message key="mark-as-unread" /></option>
						</select>

						<a class="refresh" href="javascript: ;"><liferay-ui:message key="refresh" /></a>
					</div>
				</td>
				<td class="navigation">
					<a class="newest" href="javascript: ;">&laquo; <liferay-ui:message key="newest" /></a>

					<a class="newer" href="javascript: ;">&lt; <liferay-ui:message key="newer" /></a>

					<span class="status">? - ? of ???</span>

					<a class="older" href="javascript: ;"><liferay-ui:message key="older" /> &gt;</a>

					<a class="oldest" href="javascript: ;"><liferay-ui:message key="oldest" /> &raquo;</a>
				</td>
			</tr>
			</table>
		</div>

		<div id="message">
			<div class="message-controls">
				<div class="actions">
					<span class="back">&laquo; <%= LanguageUtil.format(pageContext, "back-to-x", "<span class=\"folder-name\">INBOX</span>") %></span>
				</div>

				<div class="navigation">
					<span class="newer">&lt; <liferay-ui:message key="newer" /></span>

					<span class="status">? of ???</span>

					<span class="older"><liferay-ui:message key="older" /> &gt;</span>
				</div>
			</div>

			<div id="message-read">
				<div class="details">
					<table>
						<tr>
							<td class="label">
								<liferay-ui:message key="from" />
							</td>
							<td><span id="read-from"></span></td>
						</tr>
						<tr>
							<td class="label">
								<liferay-ui:message key="reply-to" />
							</td>
							<td><span id="read-reply-to"></span></td>
						</tr>
						<tr>
							<td class="label">
								<liferay-ui:message key="to" />
							</td>
							<td><span id="read-to"></span></td>
						</tr>
						<tr>
							<td class="label">
								<liferay-ui:message key="cc" />
							</td>
							<td><span id="read-cc"></span></td>
						</tr>
						<tr>
							<td class="label">
								<liferay-ui:message key="date" />
							</td>
							<td><span id="read-date"></span></td>
						</tr>
						<tr>
							<td class="label">
								<liferay-ui:message key="subject" />
							</td>
							<td><span id="read-subject"></span></td>
						</tr>
						<tr style="display: none">
							<td class="label">
								<liferay-ui:message key="mailed-by" />
							</td>
							<td><span id="read-mailed-by"></span></td>
						</tr>
					</table>
				</div>

				<div id="read-body">
				</div>
			</div>

			<table id="message-options">
			<tr>
				<td id="reply">
					<a href="#message-options"><liferay-ui:message key="reply" /></a>
				</td>
				<td id="reply-all">
					<a href="#message-options"><liferay-ui:message key="reply-all" /></a>
				</td>
				<td id="forward">
					<a href="#message-options"><liferay-ui:message key="forward" /></a>
				</td>
			</tr>
			</table>

			<div id="message-send">
				<div class="options">
					<input class="send" type="button" value="<liferay-ui:message key="send" />">

					<input class="save" type="button" value="<liferay-ui:message key="save" />">

					<input class="discard" type="button" value="<liferay-ui:message key="discard" />">
				</div>

				<div class="details">
					<table>
					<tr>
						<td class="label">
							<liferay-ui:message key="from" />:
						</td>
						<td>
							<select id="send-from">
							</select>
						</td>
					</tr>
					<tr>
						<td class="label">
							<liferay-ui:message key="to" />:
						</td>
						<td>
							<textarea id="send-to"></textarea>
						</td>
					</tr>
					<tr>
						<td class="label">
							<liferay-ui:message key="cc" />:
						</td>
						<td>
							<input id="send-cc" type="text">
						</td>
					</tr>
					<tr>
						<td class="label">
							<liferay-ui:message key="bcc" />:
						</td>
						<td>
							<input id="send-bcc" type="text">
						</td>
					</tr>
					<tr>
						<td class="label">
							<liferay-ui:message key="subject" />:
						</td>
						<td>
							<input id="send-subject" type="text">
						</td>
					</tr>
					</table>

					<span id="send-body">
						<liferay-ui:input-editor height="500px" width="95%" />
					</span>
				</div>

				<div class="options">
					<input class="send" type="button" value="<liferay-ui:message key="send" />">

					<input class="save" type="button" value="<liferay-ui:message key="save" />">

					<input class="discard" type="button" value="<liferay-ui:message key="discard" />">
				</div>
			</div>

			<div class="message-controls">
				<div class="actions">
					<span class="back">&laquo; <%= LanguageUtil.format(pageContext, "back-to-x", "<span class=\"folder-name\">INBOX</span>") %></span>
				</div>

				<div class="navigation">
					<span class="newer">&lt; <liferay-ui:message key="newer" /></span>

					<span class="status">? of ???</span>

					<span class="older"><liferay-ui:message key="older" /> &gt;</span>
				</div>
			</div>

		</div>
	</td>
</tr>
</table>