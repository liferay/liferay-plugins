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

import com.liferay.ant.arquillian.WebArchiveBuilder;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.test.DeleteAfterTestRun;
import com.liferay.portal.test.rule.DeleteAfterTestRunRule;
import com.liferay.portal.util.test.GroupTestUtil;
import com.liferay.portal.util.test.TestPropsValues;
import com.liferay.portal.util.test.UserTestUtil;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

/**
 * @author Iván Zaera
 */
public abstract class BaseMentionsNotifierTestCase {

	@Deployment
	public static Archive<?> getWebArchive() {
		WebArchive webArchive = WebArchiveBuilder.build();

		webArchive.addClass(BaseMentionsNotifierTestCase.class);

		return webArchive;
	}

	public BaseMentionsNotifierTestCase(
		String testEmailMessage, String testMultipleUsersMessage,
		String testMultipleUsersSeparatedByCommaMessage,
		String testNonexistentUserMessage, String testSingleUserMessage,
		String testSingleUserWithCommaMessage,
		String testSingleUserWithPeriodMessage) {

		_testEmailMessage = testEmailMessage;
		_testMultipleUsersMessage = testMultipleUsersMessage;
		_testMultipleUsersSeparatedByCommaMessage =
			testMultipleUsersSeparatedByCommaMessage;
		_testNonexistentUserMessage = testNonexistentUserMessage;
		_testSingleUserMessage = testSingleUserMessage;
		_testSingleUserWithCommaMessage = testSingleUserWithCommaMessage;
		_testSingleUserWithPeriodMessage = testSingleUserWithPeriodMessage;
	}

	@Before
	public void setUp() throws Exception {
		_group1 = GroupTestUtil.addGroup();

		_user1 = UserTestUtil.addUser("sergio", _group1.getGroupId());
		_user2 = UserTestUtil.addUser("ana", _group1.getGroupId());
	}

	@Test
	public void testEmail() throws Exception {
		MentionsNotifier mentionsNotifier = new MentionsNotifier();

		String[] mentionedUsersScreenNames =
			mentionsNotifier.getMentionedUsersScreenNames(
				TestPropsValues.getUserId(), _testEmailMessage);

		Assert.assertEquals(0, mentionedUsersScreenNames.length);
	}

	@Test
	public void testMultipleUsers() throws Exception {
		MentionsNotifier mentionsNotifier = new MentionsNotifier();

		String[] mentionedUsersScreenNames =
			mentionsNotifier.getMentionedUsersScreenNames(
				TestPropsValues.getUserId(), _testMultipleUsersMessage);

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
				_testMultipleUsersSeparatedByCommaMessage);

		Assert.assertEquals(2, mentionedUsersScreenNames.length);
		Assert.assertTrue(ArrayUtil.contains(mentionedUsersScreenNames, "ana"));
		Assert.assertTrue(
			ArrayUtil.contains(mentionedUsersScreenNames, "sergio"));
	}

	@Test
	public void testNonexistentUser() throws Exception {
		MentionsNotifier mentionsNotifier = new MentionsNotifier();

		String[] mentionedUsersScreenNames =
			mentionsNotifier.getMentionedUsersScreenNames(
				TestPropsValues.getUserId(), _testNonexistentUserMessage);

		Assert.assertEquals(0, mentionedUsersScreenNames.length);
	}

	@Test
	public void testSingleUser() throws Exception {
		MentionsNotifier mentionsNotifier = new MentionsNotifier();

		String[] mentionedUsersScreenNames =
			mentionsNotifier.getMentionedUsersScreenNames(
				TestPropsValues.getUserId(), _testSingleUserMessage);

		Assert.assertEquals(1, mentionedUsersScreenNames.length);
		Assert.assertEquals("sergio", mentionedUsersScreenNames[0]);
	}

	@Test
	public void testSingleUserWithComma() throws Exception {
		MentionsNotifier mentionsNotifier = new MentionsNotifier();

		String[] mentionedUsersScreenNames =
			mentionsNotifier.getMentionedUsersScreenNames(
				TestPropsValues.getUserId(), _testSingleUserWithCommaMessage);

		Assert.assertEquals(1, mentionedUsersScreenNames.length);
		Assert.assertEquals("sergio", mentionedUsersScreenNames[0]);
	}

	@Test
	public void testSingleUserWithPeriod() throws Exception {
		MentionsNotifier mentionsNotifier = new MentionsNotifier();

		String[] mentionedUsersScreenNames =
			mentionsNotifier.getMentionedUsersScreenNames(
				TestPropsValues.getUserId(), _testSingleUserWithPeriodMessage);

		Assert.assertEquals(1, mentionedUsersScreenNames.length);
		Assert.assertEquals("sergio", mentionedUsersScreenNames[0]);
	}

	@Rule
	public TestRule deleteAfterTestRunRule = new DeleteAfterTestRunRule(this);

	@DeleteAfterTestRun
	private Group _group1;

	private String _testEmailMessage;
	private String _testMultipleUsersMessage;
	private String _testMultipleUsersSeparatedByCommaMessage;
	private String _testNonexistentUserMessage;
	private String _testSingleUserMessage;
	private String _testSingleUserWithCommaMessage;
	private String _testSingleUserWithPeriodMessage;

	@DeleteAfterTestRun
	private User _user1;

	@DeleteAfterTestRun
	private User _user2;

}