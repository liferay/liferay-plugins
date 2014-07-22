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

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.test.GroupTestUtil;
import com.liferay.portal.util.test.TestPropsValues;
import com.liferay.portal.util.test.UserTestUtil;

import org.jboss.arquillian.junit.Arquillian;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Sergio González
 */
@RunWith(Arquillian.class)
public class BlogsEntryMentionsNotifierTest {

	@Before
	public void setUp() throws Exception {
		_group1 = GroupTestUtil.addGroup();

		_user1 = UserTestUtil.addUser("sergio", _group1.getGroupId());
		_user2 = UserTestUtil.addUser("ana", _group1.getGroupId());
	}

	@After
	public void tearDown() throws Exception {
		GroupLocalServiceUtil.deleteGroup(_group1);

		UserLocalServiceUtil.deleteUser(_user1);
		UserLocalServiceUtil.deleteUser(_user2);
	}

	@Test
	public void testEmail() throws Exception {
		MentionsNotifier mentionsNotifier = new MentionsNotifier();

		String[] mentionedUsersScreenNames =
			mentionsNotifier.getMentionedUsersScreenNames(
				TestPropsValues.getUserId(),
				"Send an email to " +
					"<a href=\"mailto:ana@sergio.com\">ana@sergio.com</a>.");

		Assert.assertEquals(0, mentionedUsersScreenNames.length);
	}

	@Test
	public void testInexistentUser() throws Exception {
		MentionsNotifier mentionsNotifier = new MentionsNotifier();

		String[] mentionedUsersScreenNames =
			mentionsNotifier.getMentionedUsersScreenNames(
				TestPropsValues.getUserId(),
				"hey <a href=\"http://localhost:8080\">@fakeuser</a> " +
					"you are a ghost.");

		Assert.assertEquals(0, mentionedUsersScreenNames.length);
	}

	@Test
	public void testMultipleUsers() throws Exception {
		MentionsNotifier mentionsNotifier = new MentionsNotifier();

		String[] mentionedUsersScreenNames =
			mentionsNotifier.getMentionedUsersScreenNames(
				TestPropsValues.getUserId(),
				"hey <a href=\"http://localhost:8080\">@sergio</a> " +
					"<a href=\"http://localhost:8080\">@ana</a> " +
						"can you check this out?");

		Assert.assertEquals(2, mentionedUsersScreenNames.length);
		Assert.assertTrue(ArrayUtil.contains(mentionedUsersScreenNames, "ana"));
		Assert.assertTrue(
			ArrayUtil.contains(mentionedUsersScreenNames, "sergio"));
	}

	@Test
	public void testMultipleUsersSeparatedByComma() throws Exception {
		MentionsNotifier mentionsNotifier = new MentionsNotifier();

		String[] mentionedUsersScreenNames =
			mentionsNotifier.getMentionedUsersScreenNames(
				TestPropsValues.getUserId(),
				"hey <a href=\"http://localhost:8080\">@ana</a>," +
					"<a href=\"http://localhost:8080\">@sergio</a> " +
						"can you check this out?");

		Assert.assertEquals(2, mentionedUsersScreenNames.length);
		Assert.assertTrue(ArrayUtil.contains(mentionedUsersScreenNames, "ana"));
		Assert.assertTrue(
			ArrayUtil.contains(mentionedUsersScreenNames, "sergio"));
	}

	@Test
	public void testSingleUser() throws Exception {
		MentionsNotifier mentionsNotifier = new MentionsNotifier();

		String[] mentionedUsersScreenNames =
			mentionsNotifier.getMentionedUsersScreenNames(
				TestPropsValues.getUserId(),
				"hey <a href=\"http://localhost:8080\">@sergio</a> " +
					"can you check this out?");

		Assert.assertEquals(1, mentionedUsersScreenNames.length);
		Assert.assertEquals("sergio", mentionedUsersScreenNames[0]);
	}

	@Test
	public void testSingleUserWithComma() throws Exception {
		MentionsNotifier mentionsNotifier = new MentionsNotifier();

		String[] mentionedUsersScreenNames =
			mentionsNotifier.getMentionedUsersScreenNames(
				TestPropsValues.getUserId(),
				"hey <a href=\"http://localhost:8080\">@sergio</a>, " +
					"can you check this out?");

		Assert.assertEquals(1, mentionedUsersScreenNames.length);
		Assert.assertEquals("sergio", mentionedUsersScreenNames[0]);
	}

	@Test
	public void testSingleUserWithPeriod() throws Exception {
		MentionsNotifier mentionsNotifier = new MentionsNotifier();

		String[] mentionedUsersScreenNames =
			mentionsNotifier.getMentionedUsersScreenNames(
				TestPropsValues.getUserId(),
				"Hello <a href=\"http://localhost:8080\">@sergio</a>. " +
					"How are you?");

		Assert.assertEquals(1, mentionedUsersScreenNames.length);
		Assert.assertEquals("sergio", mentionedUsersScreenNames[0]);
	}

	private Group _group1;
	private User _user1;
	private User _user2;

}