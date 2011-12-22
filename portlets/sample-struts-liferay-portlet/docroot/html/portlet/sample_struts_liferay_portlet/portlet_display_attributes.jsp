<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/html/portlet/sample_struts_liferay_portlet/init.jsp" %>

<strong>Portlet Display Attributes:</strong>

<br /><br />

portletDisplay.getId()=<%= portletDisplay.getId() %><br />
portletDisplay.getTitle()=<%= portletDisplay.getTitle() %><br />
portletDisplay.isAccess()=<%= portletDisplay.isAccess() %><br />
portletDisplay.isActive()=<%= portletDisplay.isActive() %><br />
portletDisplay.isStateMax()=<%= portletDisplay.isStateMax() %><br />
portletDisplay.isStateMin()=<%= portletDisplay.isStateMin() %><br />
portletDisplay.isStatePopUp()=<%= portletDisplay.isStatePopUp() %><br />
portletDisplay.isModeAbout()=<%= portletDisplay.isModeAbout() %><br />
portletDisplay.isModeConfig()=<%= portletDisplay.isModeConfig() %><br />
portletDisplay.isModeEdit()=<%= portletDisplay.isModeEdit() %><br />
portletDisplay.isModeEditDefaults()=<%= portletDisplay.isModeEditDefaults() %><br />
portletDisplay.isModeHelp()=<%= portletDisplay.isModeHelp() %><br />
portletDisplay.isModePreview()=<%= portletDisplay.isModePreview() %><br />
portletDisplay.isModePrint()=<%= portletDisplay.isModePrint() %><br />
portletDisplay.isShowEditIcon()=<%= portletDisplay.isShowEditIcon() %><br />
portletDisplay.isShowHelpIcon()=<%= portletDisplay.isShowHelpIcon() %><br />
portletDisplay.isShowMoveIcon()=<%= portletDisplay.isShowMoveIcon() %><br />
portletDisplay.isShowMinIcon()=<%= portletDisplay.isShowMinIcon() %><br />
portletDisplay.isShowMaxIcon()=<%= portletDisplay.isShowMaxIcon() %><br />
portletDisplay.isShowCloseIcon()=<%= portletDisplay.isShowCloseIcon() %><br />
portletDisplay.getURLEdit()=<%= portletDisplay.getURLEdit() %><br />
portletDisplay.getURLHelp()=<%= portletDisplay.getURLHelp() %><br />
portletDisplay.getURLMax()=<%= portletDisplay.getURLMax() %><br />
portletDisplay.isRestoreCurrentView()=<%= portletDisplay.isRestoreCurrentView() %><br />