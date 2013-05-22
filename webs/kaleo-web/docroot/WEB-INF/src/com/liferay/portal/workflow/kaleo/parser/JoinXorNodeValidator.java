package com.liferay.portal.workflow.kaleo.parser;

import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.definition.JoinXor;

/**
 * @author Michael C. Han
 */
public class JoinXorNodeValidator extends BaseNodeValidator<JoinXor> {

	@Override
	protected void doValidate(Definition definition, JoinXor joinXor)
		throws WorkflowException {

		if (joinXor.getIncomingTransitionsCount() == 0) {
			throw new WorkflowException(
				"No incoming transition found for join-xor " +
					joinXor.getName());
		}

		if (joinXor.getOutgoingTransitionsCount() == 0) {
			throw new WorkflowException(
				"No outgoing transition found for join-xor " +
					joinXor.getName());
		}
	}

}