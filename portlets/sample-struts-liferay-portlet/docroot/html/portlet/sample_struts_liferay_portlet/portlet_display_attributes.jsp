<%
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
%>

<%@ include file="/html/portlet/sample_struts_liferay_portlet/init.jsp" %>

<b>Portlet Display Attributes:</b>

<br><br>

portletDisplay.getId()=<%= portletDisplay.getId() %><br>
portletDisplay.getTitle()=<%= portletDisplay.getTitle() %><br>
portletDisplay.isAccess()=<%= portletDisplay.isAccess() %><br>
portletDisplay.isActive()=<%= portletDisplay.isActive() %><br>
portletDisplay.isStateMax()=<%= portletDisplay.isStateMax() %><br>
portletDisplay.isStateMin()=<%= portletDisplay.isStateMin() %><br>
portletDisplay.isStatePopUp()=<%= portletDisplay.isStatePopUp() %><br>
portletDisplay.isModeAbout()=<%= portletDisplay.isModeAbout() %><br>
portletDisplay.isModeConfig()=<%= portletDisplay.isModeConfig() %><br>
portletDisplay.isModeEdit()=<%= portletDisplay.isModeEdit() %><br>
portletDisplay.isModeEditDefaults()=<%= portletDisplay.isModeEditDefaults() %><br>
portletDisplay.isModeHelp()=<%= portletDisplay.isModeHelp() %><br>
portletDisplay.isModePreview()=<%= portletDisplay.isModePreview() %><br>
portletDisplay.isModePrint()=<%= portletDisplay.isModePrint() %><br>
portletDisplay.isShowEditIcon()=<%= portletDisplay.isShowEditIcon() %><br>
portletDisplay.isShowHelpIcon()=<%= portletDisplay.isShowHelpIcon() %><br>
portletDisplay.isShowMoveIcon()=<%= portletDisplay.isShowMoveIcon() %><br>
portletDisplay.isShowMinIcon()=<%= portletDisplay.isShowMinIcon() %><br>
portletDisplay.isShowMaxIcon()=<%= portletDisplay.isShowMaxIcon() %><br>
portletDisplay.isShowCloseIcon()=<%= portletDisplay.isShowCloseIcon() %><br>
portletDisplay.getURLEdit()=<%= portletDisplay.getURLEdit() %><br>
portletDisplay.getURLHelp()=<%= portletDisplay.getURLHelp() %><br>
portletDisplay.getURLMax()=<%= portletDisplay.getURLMax() %><br>
portletDisplay.isRestoreCurrentView()=<%= portletDisplay.isRestoreCurrentView() %><br>