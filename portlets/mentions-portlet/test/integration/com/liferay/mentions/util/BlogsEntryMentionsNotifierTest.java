/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.mentions.util;

import org.jboss.arquillian.junit.Arquillian;

import org.junit.runner.RunWith;

/**
 * @author Sergio González
 */
@RunWith(Arquillian.class)
public class BlogsEntryMentionsNotifierTest
	extends BaseMentionsNotifierTestCase {

	public BlogsEntryMentionsNotifierTest() {
		super(
			"Send an email to " +
				"<a href=\"mailto:ana@sergio.com\">ana@sergio.com</a>.",
			"Hey <a href=\"http://localhost:8080\">@sergio</a> " +
				"<a href=\"http://localhost:8080\">@ana</a> " +
				"can you check this out?",
			"Hey <a href=\"http://localhost:8080\">@ana</a>," +
				"<a href=\"http://localhost:8080\">@sergio</a> " +
				"can you check this out?",
			"Hey <a href=\"http://localhost:8080\">@fakeuser</a> " +
				"you are not real.",
			"Hey <a href=\"http://localhost:8080\">@sergio</a> " +
				"can you check this out?",
			"Hey <a href=\"http://localhost:8080\">@sergio</a>, " +
				"can you check this out?",
			"Hello <a href=\"http://localhost:8080\">@sergio</a>. " +
				"How are you?");
	}

}