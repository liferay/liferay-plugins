/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.samplewicket.view;

import org.apache.wicket.feedback.FeedbackMessage;

/**
 * <a href="FeedbackPanel.java.html"><b><i>View Source</i></b></a>
 *
 * @author Thiago Moreira
 */
public class FeedbackPanel extends
		org.apache.wicket.markup.html.panel.FeedbackPanel {

	public FeedbackPanel(final String id) {
		super(id);
	}

	protected String getCSSClass(FeedbackMessage message) {
		if (message.getLevel() == FeedbackMessage.INFO) {
			return "portlet-msg-success";
		} else if (message.getLevel() == FeedbackMessage.ERROR) {
			return "portlet-msg-error";
		} else {
			return "portlet-msg-none";
		}
	}

}