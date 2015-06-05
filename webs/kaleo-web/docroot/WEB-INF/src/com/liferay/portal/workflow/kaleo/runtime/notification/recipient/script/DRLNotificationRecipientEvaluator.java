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

package com.liferay.portal.workflow.kaleo.runtime.notification.recipient.script;

import com.liferay.portal.kernel.bi.rules.Fact;
import com.liferay.portal.kernel.bi.rules.Query;
import com.liferay.portal.kernel.bi.rules.RulesEngineUtil;
import com.liferay.portal.kernel.bi.rules.RulesResourceRetriever;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.resource.StringResourceRetriever;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.util.ClassLoaderUtil;
import com.liferay.portal.workflow.kaleo.runtime.util.RulesContextBuilder;

import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class DRLNotificationRecipientEvaluator
	implements NotificationRecipientEvaluator {

	@Override
	public Map<String, ?> evaluate(
			KaleoNotificationRecipient kaleoNotificationRecipient,
			ExecutionContext executionContext)
		throws PortalException {

		RulesResourceRetriever rulesResourceRetriever =
			new RulesResourceRetriever(
				new StringResourceRetriever(
					kaleoNotificationRecipient.getRecipientScript()));
		List<Fact<?>> facts = RulesContextBuilder.buildRulesContext(
			executionContext);
		Query query = Query.createStandardQuery();

		String[] recipientScriptRequiredContexts = StringUtil.split(
			kaleoNotificationRecipient.getRecipientScriptRequiredContexts());

		ClassLoader[] classLoaders = ClassLoaderUtil.getClassLoaders(
			recipientScriptRequiredContexts);

		return RulesEngineUtil.execute(
			rulesResourceRetriever, facts, query, classLoaders);
	}

}