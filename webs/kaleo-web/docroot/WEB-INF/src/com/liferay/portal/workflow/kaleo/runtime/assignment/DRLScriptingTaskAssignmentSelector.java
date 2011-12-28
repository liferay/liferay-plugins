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

package com.liferay.portal.workflow.kaleo.runtime.assignment;

import com.liferay.portal.kernel.bi.rules.Fact;
import com.liferay.portal.kernel.bi.rules.Query;
import com.liferay.portal.kernel.bi.rules.RulesEngineUtil;
import com.liferay.portal.kernel.bi.rules.RulesResourceRetriever;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.resource.StringResourceRetriever;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.util.RulesContextBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class DRLScriptingTaskAssignmentSelector
	extends BaseTaskAssignmentSelector {

	public Collection<KaleoTaskAssignment> calculateTaskAssignments(
			KaleoTaskAssignment kaleoTaskAssignment,
			ExecutionContext executionContext)
		throws PortalException, SystemException {

		List<Fact<?>> facts = RulesContextBuilder.buildRulesContext(
			executionContext);

		String assigneeScript = kaleoTaskAssignment.getAssigneeScript();

		RulesResourceRetriever rulesResourceRetriever =
			new RulesResourceRetriever(
				new StringResourceRetriever(assigneeScript));

		Query query = Query.createStandardQuery();

		Map<String, ?> results = RulesEngineUtil.execute(
			rulesResourceRetriever, facts, query,
			PortalClassLoaderUtil.getClassLoader());

		return getKaleoTaskAssignments(results);
	}

}