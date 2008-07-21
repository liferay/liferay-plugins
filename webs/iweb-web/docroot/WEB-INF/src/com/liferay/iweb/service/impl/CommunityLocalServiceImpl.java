/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.sun.com/cddl/cddl.html and
 * legal/CDDLv1.0.txt. See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at legal/CDDLv1.0.txt.
 *
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2008 Sun Microsystems Inc. All rights reserved.
 */

package com.liferay.iweb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.liferay.iweb.IWebException;
import com.liferay.iweb.NoSuchCommunityException;
import com.liferay.iweb.model.Community;
import com.liferay.iweb.model.Semantics;
import com.liferay.iweb.service.IWebCallBackLocalServiceUtil;
import com.liferay.iweb.service.base.CommunityLocalServiceBaseImpl;
import com.liferay.portal.SystemException;

/**
 * <a href="CommunityLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Prakash Radhakrishnan
 *
 */
public class CommunityLocalServiceImpl extends CommunityLocalServiceBaseImpl {

	/*
	 * Returns all the communities available.
	 */

	public Set<Community> getAllCommunities() throws IWebException {
		Set<Community> commset = new TreeSet();
		try {
			commset.addAll(communityPersistence.findAll());
			return commset;
		}
		catch (SystemException e) {
			if (e.getCause() instanceof
				com.liferay.portal.NoSuchResourceException){

				return commset;
			}
			else {
				throw new IWebException(e);
			}
		}
	}

	/*
	 * For the given set of communityId, this method returns the associated
	 * semantics for the community identified by the communityId.
	 */

	public Map getAppliedSemantics(long communityId) throws IWebException {
		Set<Long> communityIds = new TreeSet();
		communityIds.add(communityId);
		return getAppliedSemantics(communityIds);
	}

	/*
	 * For the given set of communityIds, this method returns the associated
	 * semantics for each community identified by the communityId.
	 */

	public Map<Long, Set<Semantics>> getAppliedSemantics(Set<Long> communityIds)
		throws IWebException {

		Map<Long, Set<Semantics>> communitySemantics = new HashMap();
		for (Long communityId : communityIds) {
			try {
				Community community = communityPersistence.fetchByPrimaryKey(
					communityId);

				Set<Semantics> semanticsSet = new TreeSet(
					communityPersistence.getSemanticss(communityId));

				communitySemantics.put(communityId, semanticsSet);
			}
			catch (SystemException ex) {
				throw new IWebException(ex);
			}
		}
		return communitySemantics;
	}

	/*
	 * For a given communityid, this method gets the related communities based
	 * on the applied semantics.
	 */

	public Set<Community> getRelatedCommunitiesByAppliedSemantics(
		long communityId) throws IWebException {

		try {
			Set<Semantics> semanticsSet = new TreeSet(
				communityPersistence.getSemanticss(communityId));

			Set<Community> communities = new TreeSet();
			for (Semantics sem : semanticsSet) {
				communities.addAll(
					semanticsPersistence.getCommunities(sem.getSemanticsURI()));

			}
			for (Community com : communities) {
				if (com.getCid() == communityId) {
					communities.remove(com);
					break;
				}
			}
			return IWebCallBackLocalServiceUtil.getCommunityDetails(
				communities);

		}
		catch (SystemException ex) {
			throw new IWebException(ex);
		}
	}

	/*
	 * Takes a community id and a set of semanticsElement URIs and persists in
	 * the mapping table.
	 *
	 */

	public void updateCommunityWithSemantics(long communityId,
		Set<String> semanticsSet) throws IWebException {

		try {
			if (communityPersistence.fetchByPrimaryKey(communityId) == null) {
				if (semanticsSet != null && !semanticsSet.isEmpty()) {
					Community c = communityPersistence.create(communityId);
					c.setType(
						IWebCallBackLocalServiceUtil.getCommunityType(
							communityId));

					communityPersistence.update(c, false);
				}
			}
			if (semanticsSet != null && !semanticsSet.isEmpty()) {
				communityPersistence.setSemanticss(
					communityId, semanticsSet.toArray(new String[0]));

			}
			else {
				List<Semantics> semanticslist =
					communityPersistence.getSemanticss(communityId);

				communityPersistence.clearSemanticss(communityId);
				communityPersistence.remove(communityId);
				for (Semantics sem : semanticslist) {
					List<Community> communitylist =
						semanticsPersistence.getCommunities(
							sem.getPrimaryKey());

					if (communitylist.isEmpty()) {
						semanticsLocalService.removeCachedSemantics(
							sem.getPrimaryKey());

					}
				}
			}
		}
		catch (NoSuchCommunityException ex) {
			throw new IWebException(ex);
		}
		catch (SystemException ex) {
			throw new IWebException(ex);
		}
	}

	/*
	 * Takes a map where each entry in the map has the community id as the key
	 * and a set of semanticsElement URIs as the value and persists in the
	 * mapping table.
	 */

	public void updateCommunityWithSemantics(
		Map<Long, Set<String>> communitySemanticsMap) throws IWebException {

		Set<Map.Entry<Long, Set<String>>> entries =
			communitySemanticsMap.entrySet();

		for (Map.Entry<Long, Set<String>> entry : entries) {
			updateCommunityWithSemantics(entry.getKey(), entry.getValue());
		}
	}

}