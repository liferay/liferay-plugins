/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.hook.listeners;

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.User;
import com.liferay.portlet.social.model.SocialRelation;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * @author Jonathan McCann
 */
public class SocialRelationModelListener
	extends BaseModelListener<SocialRelation> {

	@Override
	public void onAfterCreate(SocialRelation socialRelation)
		throws ModelListenerException {

		reindex(socialRelation);
	}

	@Override
	public void onAfterRemove(SocialRelation socialRelation)
		throws ModelListenerException {

		reindex(socialRelation);
	}

	protected void reindex(SocialRelation socialRelation) {
		SocialRelationReindexerCallable socialRelationReindexerCallable =
			_socialRelationReindexerCallable.get();

		if (socialRelationReindexerCallable == null) {
			socialRelationReindexerCallable =
				new SocialRelationReindexerCallable();

			TransactionCommitCallbackUtil.registerCallback(
				socialRelationReindexerCallable);

			_socialRelationReindexerCallable.set(
				socialRelationReindexerCallable);
		}

		socialRelationReindexerCallable.addUserId(socialRelation.getUserId1());
		socialRelationReindexerCallable.addUserId(socialRelation.getUserId2());
	}

	private static Log _log = LogFactoryUtil.getLog(
		SocialRelationModelListener.class);

	private AutoResetThreadLocal<SocialRelationReindexerCallable>
		_socialRelationReindexerCallable = new AutoResetThreadLocal<>(
			SocialRelationModelListener.class.getName());

	private class SocialRelationReindexerCallable implements Callable<Void> {

		public void addUserId(long userId) {
			_userIds.add(userId);
		}

		@Override
		public Void call() {
			Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				User.class);

			long[] userIds = ArrayUtil.toArray(_userIds.toArray(new Long[0]));

			try {
				indexer.reindex(userIds);
			}
			catch (SearchException se) {
				if (_log.isWarnEnabled()) {
					_log.warn(se, se);
				}
			}

			return null;
		}

		private Set<Long> _userIds = new HashSet<>();

	}

}