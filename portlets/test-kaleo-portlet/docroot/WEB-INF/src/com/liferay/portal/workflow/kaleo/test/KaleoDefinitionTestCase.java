/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.test;

import com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowException;

import java.io.InputStream;

/**
 * @author Marcellus Tavares
 */
public class KaleoDefinitionTestCase extends TestCase {

	public void testValidateCategorySpecificDefinition() throws Exception {
		InputStream inputStream = getResource(
			"/META-INF/definitions/category-specific-definition.xml");

		assertValid(inputStream);
	}

	public void testValidateIncomingTransitionInitialStateDefinition()
		throws Exception {

		InputStream inputStream = getResource(
			"/META-INF/definitions/incoming-initial-state.xml");

		String error = assertInvalid(inputStream);

		assertEquals(
			"An incoming transition was found for initial state start", error);
	}

	public void testValidateIncomingTransitionsJoinNodeDefinition()
		throws Exception {

		InputStream inputStream = getResource(
			"/META-INF/definitions/incoming-transitions-join-1.xml");

		String error = assertInvalid(inputStream);

		assertEquals(
			"Incorrect number of incoming transitions for join join", error);

		inputStream = getResource(
			"/META-INF/definitions/incoming-transitions-join-2.xml");

		error = assertInvalid(inputStream);

		assertEquals(
			"Incorrect number of incoming transitions for join join1", error);

		inputStream = getResource(
			"/META-INF/definitions/incoming-transitions-join-3.xml");

		error = assertInvalid(inputStream);

		assertEquals(
			"Incorrect number of incoming transitions for join join", error);

		inputStream = getResource(
			"/META-INF/definitions/incoming-transitions-join-4.xml");

		error = assertInvalid(inputStream);

		assertEquals(
			"Incorrect number of incoming transitions for join join", error);
	}

	public void testValidateLegalMarketingDefinition() throws Exception {
		InputStream inputStream = getResource(
			"/META-INF/definitions/legal-marketing-definition.xml");

		assertValid(inputStream);
	}

	public void testValidateLessThanTwoOutgoingConditionNodeDefinition()
		throws Exception {

		InputStream inputStream = getResource(
			"/META-INF/definitions/less-than-two-outgoing-condition.xml");

		String error = assertInvalid(inputStream);

		assertEquals(
			"Less than 2 outgoing transitions found for condition condition",
			error);
	}

	public void testValidateLessThanTwoOutgoingForkNodeDefinition()
		throws Exception {

		InputStream inputStream = getResource(
			"/META-INF/definitions/less-than-two-outgoing-fork.xml");

		String error = assertInvalid(inputStream);

		assertEquals(
			"Less than 2 outgoing transitions found for fork fork", error);
	}

	public void testValidateMatchingForkAndJoins() throws Exception {
		InputStream inputStream = getResource(
			"/META-INF/definitions/matching-fork-and-join-1.xml");

		String error = assertInvalid(inputStream);

		assertEquals("Fork fork2 and join join2 are not paired", error);

		inputStream = getResource(
			"/META-INF/definitions/matching-fork-and-join-2.xml");

		error = assertInvalid(inputStream);

		assertEquals("Fork fork2 and join join2 are not paired", error);

		inputStream = getResource(
			"/META-INF/definitions/matching-fork-and-join-3.xml");

		error = assertInvalid(inputStream);

		assertEquals("Fork fork3 and join join6 are not paired", error);
	}

	public void testValidateMultipleInitialStatesDefinedDefinition()
		throws Exception {

		InputStream inputStream = getResource(
			"/META-INF/definitions/multiple-initial-states.xml");

		String error = assertInvalid(inputStream);

		assertEquals("Multiple initial states start1 and start2", error);
	}

	public void testValidateNoAssignmentsTaskNodeDefinition() throws Exception {
		InputStream inputStream = getResource(
			"/META-INF/definitions/no-assignments-task.xml");

		String error = assertInvalid(inputStream);

		assertEquals("No assignments for task task", error);
	}

	public void testValidateNoIncomingTransitionConditionNodeDefinition()
		throws Exception {

		InputStream inputStream = getResource(
			"/META-INF/definitions/no-incoming-condition.xml");

		String error = assertInvalid(inputStream);

		assertEquals(
			"No incoming transition found for condition condition", error);
	}

	public void testValidateNoIncomingTransitionForkNodeDefinition()
		throws Exception {

		InputStream inputStream = getResource(
			"/META-INF/definitions/no-incoming-fork.xml");

		String error = assertInvalid(inputStream);

		assertEquals("No incoming transition found for fork fork", error);
	}

	public void testValidateNoIncomingTransitionStateNodeDefinition()
		throws Exception {

		InputStream inputStream = getResource(
			"/META-INF/definitions/no-incoming-state.xml");

		String error = assertInvalid(inputStream);

		assertEquals("No incoming transition found for state state", error);
	}

	public void testValidateNoIncomingTransitionTaskNodeDefinition()
		throws Exception {

		InputStream inputStream = getResource(
			"/META-INF/definitions/no-incoming-task.xml");

		String error = assertInvalid(inputStream);

		assertEquals("No incoming transition found for task task", error);
	}

	public void testValidateNoInitialStateDefinedDefinition() throws Exception {
		InputStream inputStream = getResource(
			"/META-INF/definitions/no-initial-state.xml");

		String error = assertInvalid(inputStream);

		assertEquals("No initial state defined", error);
	}

	public void testValidateNoOutgoingTransitionInitialStateDefinition()
		throws Exception {

		InputStream inputStream = getResource(
			"/META-INF/definitions/no-outgoing-initial-state.xml");

		String error = assertInvalid(inputStream);

		assertEquals(
			"No outgoing transition found for initial state start", error);
	}

	public void testValidateNoOutgoingTransitionJoinNodeDefinition()
		throws Exception {

		InputStream inputStream = getResource(
			"/META-INF/definitions/no-outgoing-join.xml");

		String error = assertInvalid(inputStream);

		assertEquals("No outgoing transition found for join join", error);
	}

	public void testValidateNoOutgoingTransitionStartNodeDefinition()
		throws Exception {

		InputStream inputStream = getResource(
			"/META-INF/definitions/no-outgoing-start-node.xml");

		String error = assertInvalid(inputStream);

		assertEquals(
			"No outgoing transition found for initial state start", error);
	}

	public void testValidateNoOutgoingTransitionTaskNodeDefinition()
		throws Exception {

		InputStream inputStream = getResource(
			"/META-INF/definitions/no-outgoing-task.xml");

		String error = assertInvalid(inputStream);

		assertEquals("No outgoing transition found for task task", error);
	}

	public void testValidateNoTerminalStatesDefinition() throws Exception {
		InputStream inputStream = getResource(
			"/META-INF/definitions/no-terminal-states.xml");

		String error = assertInvalid(inputStream);

		assertEquals("No terminal states defined", error);
	}

	public void testValidateSingleApproverDefinition() throws Exception {
		InputStream inputStream = getResource(
			"/META-INF/definitions/single-approver-definition.xml");

		assertValid(inputStream);
	}

	public void testValidateSingleApproverScriptedAssignmentDefinition()
		throws Exception {

		InputStream inputStream = getResource(
			"/META-INF/definitions/" +
			"single-approver-definition-scripted-assignment.xml");

		assertValid(inputStream);
	}

	public void testValidateTransitions() throws Exception {
		InputStream inputStream = getResource(
			"/META-INF/definitions/invalid-transition.xml");

		String error = assertInvalid(inputStream);

		assertEquals("Unable to find target node for transition end", error);
	}

	public void testValidateUnbalancedForkAndJoinNodes() throws Exception {
		InputStream inputStream = getResource(
			"/META-INF/definitions/unbalanced-fork-and-join.xml");

		String error = assertInvalid(inputStream);

		assertEquals("There are unbalanced fork and join nodes", error);
	}

	public void testValidateValidDefinition() throws Exception {
		InputStream inputStream = getResource(
			"/META-INF/definitions/valid-definition.xml");

		assertValid(inputStream);
	}

	protected String assertInvalid(InputStream inputStream) {
		try {
			WorkflowDefinitionManagerUtil.validateWorkflowDefinition(
				inputStream);

			fail();
		}
		catch (WorkflowException we) {
			Throwable throwable = we.getCause();

			return throwable.getMessage();
		}

		return null;
	}

	protected void assertValid(InputStream inputStream) {
		try {
			WorkflowDefinitionManagerUtil.validateWorkflowDefinition(
				inputStream);
		}
		catch (WorkflowException we) {
			fail(we.getMessage());
		}
	}

	protected InputStream getResource(String name) {
		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

		return classLoader.getResourceAsStream(name);
	}

}