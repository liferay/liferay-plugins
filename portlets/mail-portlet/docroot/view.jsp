<%
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
%>

<%@ include file="/init.jsp" %>

<c:choose>
	<c:when test="<%= themeDisplay.isSignedIn() %>">
		<script type="text/javascript">
			function <portlet:namespace />initEditor() {
				setTimeout("Liferay.Mail.setView('viewFolder')", 1000);

				return '';
			}

			AUI().ready(
				function() {
					Liferay.Mail.init(
						{
							sendBodyEditor: window.<portlet:namespace />editor,
							synchronizeInterval: <%= PortletProps.get("synchronize.interval.minutes") %>,
							messagesPerPage: <%= PortletProps.get("messages.per.page") %>,
							namespace: '<portlet:namespace />'
						}
					);
				}
			);
		</script>

		<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
			<div class="configuration-prompt" id="<portlet:namespace />configuration-prompt">
				<liferay-ui:message key="configure-email-accounts" />

				<br /><br />

				<liferay-ui:icon image="edit" message="setup-email-accounts" url="<%= portletDisplay.getURLEdit() %>" />
			</div>

			<table class="account-container" id="<portlet:namespace />account-container">
			<tr>
				<td class="selection" id="<portlet:namespace />selection">
					<select id="<portlet:namespace />account-selection"></select>

					<span class="configure-link">
						<a href="<%= portletDisplay.getURLEdit() %>"><liferay-ui:message key="configure-email-accounts" /></a>
					</span>
				</td>
				<td class="status-div" id="<portlet:namespace />status-div">
					<span class="status" id="<portlet:namespace />status" />
				</td>
				<td class="search" id="<portlet:namespace />search">
					<input id="<portlet:namespace />search-text">

					<input id="<portlet:namespace />search-button" type="button" value="<liferay-ui:message key="search" />" />
				</td>
			</tr>
			</table>

			<table class="email-container" id="<portlet:namespace />email-container">
			<tr>
				<td class="email-left-column" id="<portlet:namespace />email-left-column">
					<div class="compose-mail" id="<portlet:namespace />compose-mail">
						<liferay-ui:message key="compose-email" />
					</div>

					<div id="<portlet:namespace />folders">
						<div class="folder" folderName="INBOX">
							<a href="javascript:;">INBOX</a>
						</div>
					</div>
				</td>
				<td class="email-right-column" id="<portlet:namespace />email-right-column">
					<div class="folder-container" id="<portlet:namespace />folder" folderName="">
						<table class="folder-controls results-header">
						<tr>
							<td class="actions">
								<div>
									<input class="delete" type="button" value="<liferay-ui:message key="delete" />" />

									<select class="select-actions">
										<option value="none"><liferay-ui:message key="more-actions" /></option>

										<option value="seen,true">- <liferay-ui:message key="mark-as-read" /></option>

										<option value="seen,false">- <liferay-ui:message key="mark-as-unread" /></option>
									</select>
								</div>

								<div>
									<liferay-ui:message key="select" />:

									<a class="select-all" href="javascript:;"><liferay-ui:message key="all" /></a>,

									<a class="select-none" href="javascript:;"><liferay-ui:message key="none" /></a>,

									<a class="select-read" href="javascript:;"><liferay-ui:message key="read" /></a>,

									<a class="select-unread" href="javascript:;"><liferay-ui:message key="unread" /></a>
								</div>
							</td>
							<td class="navigation">
								<a class="newest" href="javascript:;">&laquo; <liferay-ui:message key="newest" /></a>

								<a class="newer" href="javascript:;">&lt; <liferay-ui:message key="newer" /></a>

								<span class="status">? - ? of ???</span>

								<a class="older" href="javascript:;"><liferay-ui:message key="older" /> &gt;</a>

								<!--<a class="oldest" href="javascript:;"><liferay-ui:message key="oldest" /> &raquo;</a>-->
							</td>
						</tr>
						</table>

						<table class="message-list" id="<portlet:namespace />message-list">
						<tr>
							<td class="alert"><liferay-ui:message key="no-messages-found" /></td>
						</tr>
						</table>

						<table class="folder-controls results-header">
						<tr>
							<td class="actions">
								<div>
									<liferay-ui:message key="select" />:

									<a class="select-all" href="javascript:;"><liferay-ui:message key="all" /></a>,

									<a class="select-none" href="javascript:;"><liferay-ui:message key="none" /></a>,

									<a class="select-read" href="javascript:;"><liferay-ui:message key="read" /></a>,

									<a class="select-unread" href="javascript:;"><liferay-ui:message key="unread" /></a>
								</div>

								<div>
									<input class="delete" type="button" value="<liferay-ui:message key="delete" />" />

									<select class="select-actions">
										<option value="none"><liferay-ui:message key="more-actions" /></option>

										<option value="seen,true">- <liferay-ui:message key="mark-as-read" /></option>

										<option value="seen,false">- <liferay-ui:message key="mark-as-unread" /></option>
									</select>
								</div>
							</td>
							<td class="navigation">
								<a class="newest" href="javascript:;">&laquo; <liferay-ui:message key="newest" /></a>

								<a class="newer" href="javascript:;">&lt; <liferay-ui:message key="newer" /></a>

								<span class="status">? - ? of ???</span>

								<a class="older" href="javascript:;"><liferay-ui:message key="older" /> &gt;</a>

								<!--<a class="oldest" href="javascript:;"><liferay-ui:message key="oldest" /> &raquo;</a>-->
							</td>
						</tr>
						</table>
					</div>

					<div class="message-container" id="<portlet:namespace />message">
						<div class="message-controls results-header">
							<div class="actions">
								<span class="back">&laquo; <%= LanguageUtil.format(pageContext, "back-to-x", "<span class=\"folder-name\">INBOX</span>") %></span>
							</div>

							<div class="navigation">
								<span class="newer">&lt; <liferay-ui:message key="newer" /></span>

								<span class="status">? of ???</span>

								<span class="older"><liferay-ui:message key="older" /> &gt;</span>
							</div>
						</div>

						<div class="message-read" id="<portlet:namespace />messageRead">
							<div class="details">
								<table>
									<tr>
										<td class="label">
											<liferay-ui:message key="from" />
										</td>
										<td><span id="<portlet:namespace />read-from"></span></td>
									</tr>
									<tr>
										<td class="label">
											<liferay-ui:message key="reply-to" />
										</td>
										<td><span id="<portlet:namespace />read-reply-to"></span></td>
									</tr>
									<tr>
										<td class="label">
											<liferay-ui:message key="to" />
										</td>
										<td><span id="<portlet:namespace />read-to"></span></td>
									</tr>
									<tr>
										<td class="label">
											<liferay-ui:message key="cc" />
										</td>
										<td><span id="<portlet:namespace />read-cc"></span></td>
									</tr>
									<tr>
										<td class="label">
											<liferay-ui:message key="date" />
										</td>
										<td><span id="<portlet:namespace />read-date"></span></td>
									</tr>
									<tr>
										<td class="label">
											<liferay-ui:message key="subject" />
										</td>
										<td><span id="<portlet:namespace />read-subject"></span></td>
									</tr>
									<tr style="display: none">
										<td class="label">
											<liferay-ui:message key="mailed-by" />
										</td>
										<td><span id="<portlet:namespace />read-mailed-by"></span></td>
									</tr>
								</table>
							</div>

							<div id="<portlet:namespace />read-body">
							</div>
						</div>

						<table class="message-options" id="<portlet:namespace />messageOptions" name="message-options">
						<tr>
							<td id="<portlet:namespace />reply" responseType="reply">
								<a href="#message-options"><liferay-ui:message key="reply" /></a>
							</td>
							<td id="<portlet:namespace />reply-all" responseType="reply-all">
								<a href="#message-options"><liferay-ui:message key="reply-all" /></a>
							</td>
							<td id="<portlet:namespace />forward" responseType="forward">
								<a href="#message-options"><liferay-ui:message key="forward" /></a>
							</td>
						</tr>
						</table>

						<form action="<portlet:actionURL name="saveOrSendMessage" />" enctype="multipart/form-data" id="<portlet:namespace />fm" method="post" name="<portlet:namespace />fm">
						<input id="<portlet:namespace />redirect" name="<portlet:namespace />redirect" type="hidden" value="<%= currentURL %>">
						<input id="<portlet:namespace />sendDraftMessageUid" name="<portlet:namespace />sendDraftMessageUid" type="hidden" value="">
						<input id="<portlet:namespace />sendEmailAddress" name="<portlet:namespace />sendEmailAddress" type="hidden" value="">
						<input id="<portlet:namespace />sendFolderName" name="<portlet:namespace />sendFolderName" type="hidden" value="">
						<input id="<portlet:namespace />sendMessageType" name="<portlet:namespace />sendMessageType" type="hidden" value="">
						<input id="<portlet:namespace />sendMessageUid" name="<portlet:namespace />sendMessageUid" type="hidden" value="">
						<input id="<portlet:namespace />sendMessage" name="<portlet:namespace />sendMessage" type="hidden" value="">

						<div class="message-send results-header" id="<portlet:namespace />messageSend">
							<div class="options">
								<input class="send" type="button" value="<liferay-ui:message key="send" />">

								<!--<input class="save" type="button" value="<liferay-ui:message key="save" />">-->

								<input class="discard" type="button" value="<liferay-ui:message key="discard" />">
							</div>

							<div class="details">
								<table>
								<tr>
									<td class="label">
										<liferay-ui:message key="from" />:
									</td>
									<td>
										<select id="<portlet:namespace />sendFrom" name="<portlet:namespace />sendFrom">
										</select>
									</td>
								</tr>
								<tr>
									<td class="label">
										<liferay-ui:message key="to" />:
									</td>
									<td>
										<textarea id="<portlet:namespace />sendTo" name="<portlet:namespace />sendTo"></textarea>
									</td>
								</tr>
								<tr>
									<td class="label">
										<liferay-ui:message key="cc" />:
									</td>
									<td>
										<input id="<portlet:namespace />sendCc" name="<portlet:namespace />sendCc" type="text">
									</td>
								</tr>
								<tr>
									<td class="label">
										<liferay-ui:message key="bcc" />:
									</td>
									<td>
										<input id="<portlet:namespace />sendBcc" name="<portlet:namespace />sendBcc" type="text">
									</td>
								</tr>
								<tr>
									<td class="label">
										<liferay-ui:message key="subject" />:
									</td>
									<td>
										<input id="<portlet:namespace />sendSubject" name="<portlet:namespace />sendSubject" type="text">
									</td>
								</tr>
								<tr>
									<td class="label">
										<liferay-ui:message key="attachments" />:
									</td>
									<td>
										<input id="<portlet:namespace />sendAttachments" name="<portlet:namespace />sendAttachments" size="50" type="file" />
									</td>
								</tr>
								</table>

								<span class="send-body-span">
									<liferay-ui:input-editor height="500px" width="95%" />

									<input id="<portlet:namespace />sendBody" name="<portlet:namespace />sendBody" type="hidden" />
								</span>
							</div>

							<div class="options">
								<input class="send" type="button" value="<liferay-ui:message key="send" />">

								<!--<input class="save" type="button" value="<liferay-ui:message key="save" />">-->

								<input class="discard" type="button" value="<liferay-ui:message key="discard" />">
							</div>
						</div>
						</form>

						<div class="message-controls results-header">
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
		</c:if>
		<c:if test="<%= !windowState.equals(WindowState.MAXIMIZED) %>">
			<div class="summary">
				<div class="has-accounts">
					<%= LanguageUtil.format(pageContext, "check-your-email-or-configure-email-accounts", new String[] {portletDisplay.getURLMax(), portletDisplay.getURLEdit()}) %>
				</div>

				<div class="has-no-accounts">
					<a href="<%= portletDisplay.getURLEdit() %>"><liferay-ui:message key="configure-email-accounts" /></a>
				</div>
			</div>
		</c:if>
	</c:when>
	<c:otherwise>
		<liferay-ui:message key="you-must-be-authenticated-to-use-this-portlet" />
	</c:otherwise>
</c:choose>