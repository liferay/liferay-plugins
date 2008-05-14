<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<table id="account-container">
<tr>
	<td id="selection">
		<select id="account-selection">
		</select>
	</td>

	<td id="status-div">
		<span id="status" />
	</td>

	<td id="search">
		<input id="search-text">
		<input id="search-button" type="button" value="Search" />
	</td>
</tr>
</table>

<table id="email-container">
<tr>
	<td id="email-left-column">
		<div id="compose-mail">
			<liferay-ui:message key="compose-mail" />
		</div>

		<div id="folders">
			<div class="folder" folderName="INBOX">
				<a href="javascript: ;"><liferay-ui:message key="inbox" /></a>
			</div>
		</div>
	</td>
	<td id="email-right-column">
		<div id="folder" folderName="">

			<div class="folder-controls">
				<div class="actions">
					<div>
						<input type="button" value="Delete" class="delete" />
						<select class="select">
							<option value="none"><liferay-ui:message key="more-actions" /></option>
							<option value="read">- <liferay-ui:message key="mark-as-read" /></option>
							<option value="unread">- <liferay-ui:message key="mark-as-unread" /></option>
						</select>
						<a class="refresh" href="javascript: ;"><liferay-ui:message key="refresh" /></a>
					</div>

					<div>
						<liferay-ui:message key="select" />: 
						<a href="javascript: ;" class="select-all"><liferay-ui:message key="all" /></a>, 
						<a href="javascript: ;" class="select-none"><liferay-ui:message key="none" /></a>, 
						<a href="javascript: ;" class="select-read"><liferay-ui:message key="read" /></a>, 
						<a href="javascript: ;" class="select-unread"><liferay-ui:message key="unread" /></a>
					</div>
				</div>

				<div class="navigation">
					<a href="javascript: ;" class="newest">« <liferay-ui:message key="newest" /></a>
					<a href="javascript: ;" class="newer">&lt; <liferay-ui:message key="newer" /></a>
					<span class="status">? - ? of ???</span>
					<a href="javascript: ;" class="older"><liferay-ui:message key="older" /> &gt;</a>
					<a href="javascript: ;" class="oldest"><liferay-ui:message key="oldest" /> »</a>
				</div>
			</div>

			<table id="message-list">
			</table>

			<div class="folder-controls">
				<div class="actions">
					<div>
						<liferay-ui:message key="select" />: 
						<a href="javascript: ;" class="select-all"><liferay-ui:message key="all" /></a>, 
						<a href="javascript: ;" class="select-none"><liferay-ui:message key="none" /></a>, 
						<a href="javascript: ;" class="select-read"><liferay-ui:message key="read" /></a>, 
						<a href="javascript: ;" class="select-unread"><liferay-ui:message key="unread" /></a>
					</div>

					<div>
						<input type="button" value="Delete" class="delete" />
						<select class="select-actions">
							<option value="none"><liferay-ui:message key="more-actions" /></option>
							<option value="read">- <liferay-ui:message key="mark-as-read" /></option>
							<option value="unread">- <liferay-ui:message key="mark-as-unread" /></option>
						</select>
						<a class="refresh" href="javascript: ;"><liferay-ui:message key="refresh" /></a>
					</div>
				</div>

				<div class="navigation">
					<a href="javascript: ;" class="newest">« <liferay-ui:message key="newest" /></a>
					<a href="javascript: ;" class="newer">&lt; <liferay-ui:message key="newer" /></a>
					<span class="status">? - ? of ???</span>
					<a href="javascript: ;" class="older"><liferay-ui:message key="older" /> &gt;</a>
					<a href="javascript: ;" class="oldest"><liferay-ui:message key="oldest" /> »</a>
				</div>
			</div>
		</div>

		<div id="message">
			<div class="message-controls">
				<div class="actions">
					<span class="back">« <liferay-ui:message key="back-to" /> <span class="folder-name">Inbox</span></span>
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
							<td id="read-from"></td>
						</tr>
						<tr>
							<td class="label">
								<liferay-ui:message key="reply-to" />
							</td>
							<td id="read-reply-to"></td>
						</tr>
						<tr>
							<td class="label">
								<liferay-ui:message key="to" />
							</td>
							<td id="read-to"></td>
						</tr>
						<tr>
							<td class="label">
								<liferay-ui:message key="cc" />
							</td>
							<td id="read-cc"></td>
						</tr>
						<tr>
							<td class="label">
								<liferay-ui:message key="date" />
							</td>
							<td id="read-date"></td>
						</tr>
						<tr>
							<td class="label">
								<liferay-ui:message key="subject" />
							</td>
							<td id="read-subject"></td>
						</tr>
						<tr>
							<td class="label">
								<liferay-ui:message key="mailed-by" />
							</td>
							<td id="read-mailed-by"></td>
						</tr>
					</table>
				</div>

				<div id="read-body">
				</div>
			</div>

			<div id="message-options">
				<div id="reply">
					<a href="#message-options"><liferay-ui:message key="reply" /></a>
				</div>

				<div id="reply-all">
					<a href="#message-options"><liferay-ui:message key="reply-to-all" /></a>
				</div>

				<div id="forward">
					<a href="#message-options"><liferay-ui:message key="forward" /></a>
				</div>
			</div>

			<div id="message-send">
				<div class="options">
					<input type="button" value="Send" class="send">
					<input type="button" value="Save" class="save">
					<input type="button" value="Discard" class="discard">
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

					<textarea id="send-body"></textarea>
				</div>

				<div class="options">
					<input type="button" value="Send" class="send">
					<input type="button" value="Save" class="save">
					<input type="button" value="Discard" class="discard">
				</div>
			</div>

			<div class="message-controls">
				<div class="actions">
					<span class="back">« <liferay-ui:message key="back-to" /> <span class="folder-name">Inbox</span></span>
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

<%!
public static final String EDITOR_WYSIWYG_IMPL_KEY = "editor.wysiwyg.portal-web.docroot.html.portlet.blogs.edit_entry.jsp";
%>