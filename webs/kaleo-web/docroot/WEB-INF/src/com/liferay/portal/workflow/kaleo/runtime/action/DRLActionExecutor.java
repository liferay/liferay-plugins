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

package com.liferay.portal.workflow.kaleo.runtime.action;

import com.liferay.portal.kernel.bi.rules.Fact;
import com.liferay.portal.kernel.bi.rules.RulesEngineUtil;
import com.liferay.portal.kernel.bi.rules.RulesResourceRetriever;
import com.liferay.portal.kernel.resource.StringResourceRetriever;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.util.RulesContextBuilder;

import java.util.List;

/**
 * @author Michael C. Han
 */
public class DRLActionExecutor implements ActionExecutor {

	public void execute(
			KaleoAction kaleoAction, ExecutionContext executionContext)
		throws ActionExecutorException {

		try {
			doExecute(kaleoAction, executionContext);
		}
		catch (Exception e) {
			throw new ActionExecutorException(e);
		}
	}

	protected void doExecute(
			KaleoAction kaleoAction, ExecutionContext executionContext)
		throws Exception {

		List<Fact<?>> facts = RulesContextBuilder.buildRulesContext(
			executionContext);

		RulesResourceRetriever rulesResourceRetriever =
			new RulesResourceRetriever(
				new StringResourceRetriever(kaleoAction.getScript()));

		RulesEngineUtil.execute(
			rulesResourceRetriever, facts,
			PortalClassLoaderUtil.getClassLoader());
	}

}