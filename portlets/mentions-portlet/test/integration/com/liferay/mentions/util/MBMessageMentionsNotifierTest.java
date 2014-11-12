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
public class MBMessageMentionsNotifierTest
	extends BaseMentionsNotifierTestCase {

	public MBMessageMentionsNotifierTest() {
		super(
			"Send an email to [url=mailto:ana@sergio.com]ana@sergio.com[/url].",
			"Hey [url=http://localhost:8080]@sergio[/url] " +
				"[url=http://localhost:8080]@ana[/url] can you check this out?",
			"Hey [url=http://localhost:8080]@ana[/url], " +
				"[url=http://localhost:8080]@sergio[/url] " +
				"can you check this out?",
			"Hey [url=http://localhost:8080]@fakeuser[/url] you are not real.",
			"Hey [url=http://localhost:8080]@sergio[/url] " +
				"can you check this out?",
			"Hey [url=http://localhost:8080]@sergio[/url], " +
				"can you check this out?",
			"Hello [url=http://localhost:8080]@sergio[/url]. How are you?");
	}

}