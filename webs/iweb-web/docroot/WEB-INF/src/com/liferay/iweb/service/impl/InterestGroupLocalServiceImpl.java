/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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
 * Copyright 2009 Sun Microsystems Inc. All rights reserved.
 */

package com.liferay.iweb.service.impl;

import com.liferay.iweb.IWebException;
import com.liferay.iweb.NoSuchInterestGroupException;
import com.liferay.iweb.model.InterestGroup;
import com.liferay.iweb.model.SemanticsFile;
import com.liferay.iweb.service.CallBackLocalServiceUtil;
import com.liferay.iweb.service.SemanticsFileLocalServiceUtil;
import com.liferay.iweb.service.base.InterestGroupLocalServiceBaseImpl;
import com.liferay.portal.SystemException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * <a href="InterestGroupLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Prakash Radhakrishnan
 *
 */
public class InterestGroupLocalServiceImpl
	extends InterestGroupLocalServiceBaseImpl {

	/**
	 * Returns all the interestGroups available.
	 */

	public Set<InterestGroup> getAllCommunities() throws IWebException {
		Set<InterestGroup> igset = new TreeSet();
		try {
			igset.addAll(interestGroupPersistence.findAll());
			return igset;
		}
		catch (SystemException e) {
			if (e.getCause() instanceof
				com.liferay.portal.NoSuchResourceException){

				return igset;
			}
			else {
				throw new IWebException(e);
			}
		}
	}

	/**
	 * For the given set of interestGroupId, this method returns the associated
	 * semantics for the InterestGroup identified by the interestGroupId.
	 */

	public Map<Long, Set<SemanticsFile>> getAppliedSemanticsFiles(
			long interestGroupId)
		throws IWebException {

		Set<Long> interestGroupIds = new TreeSet();
		interestGroupIds.add(interestGroupId);
		return getAppliedSemanticsFiles(interestGroupIds);
	}

	/**
	 * For the given set of interestGroupIds, this method returns the associated
	 * semantics for each InterestGroup identified by the interestGroupId.
	 */

	public Map<Long, Set<SemanticsFile>> getAppliedSemanticsFiles(
			Set<Long> interestGroupIds)
		throws IWebException {

		Map<Long, Set<SemanticsFile>> interestGroupSemantics = new HashMap();
		for (Long interestGroupId : interestGroupIds) {
			try {
				Set<SemanticsFile> semanticsSet = new TreeSet(
					interestGroupPersistence.getSemanticsFiles(
						interestGroupId));

				interestGroupSemantics.put(interestGroupId, semanticsSet);
			}
			catch (SystemException ex) {
				throw new IWebException(ex);
			}
		}
		return interestGroupSemantics;
	}

	/**
	 * For a given interestGroupid, this method gets the related InterestGroups
	 * based on the applied semantics.
	 */

	public Set<InterestGroup> getRelatedInterestGroupsByAppliedSemantics(
			long interestGroupId)
		throws IWebException {

		try {
			Set<SemanticsFile> semanticsSet = new TreeSet(
				interestGroupPersistence.getSemanticsFiles(interestGroupId));

			Set<InterestGroup> interestGroups = new TreeSet();
			for (SemanticsFile sem : semanticsSet) {
				interestGroups.addAll(
					semanticsFilePersistence.getInterestGroups(
						sem.getSemanticsURI()));

			}
			for (InterestGroup ig : interestGroups) {
				if (ig.getCid() == interestGroupId) {
					interestGroups.remove(ig);
					break;
				}
			}
			return CallBackLocalServiceUtil.getInterestGroupDetails(
				interestGroups);

		}
		catch (SystemException ex) {
			throw new IWebException(ex);
		}
	}

	/**
	 * Takes a interestGroup id and a set of semanticsElement URIs and persists
	 * in the mapping table.
	 *
	 */

	public void updateInterestGroupWithSemantics(
			long interestGroupId, Set<String> semanticsSet)
		throws IWebException {

		try {
			if (interestGroupPersistence.fetchByPrimaryKey(
				interestGroupId) == null) {

				if (semanticsSet != null && !semanticsSet.isEmpty()) {
					InterestGroup ig = interestGroupPersistence.create(
						interestGroupId);

					ig.setType(
						CallBackLocalServiceUtil.getInterestGroupType(
							interestGroupId));

					interestGroupPersistence.update(ig, false);
				}
			}
			if (semanticsSet != null && !semanticsSet.isEmpty()) {
				interestGroupPersistence.setSemanticsFiles(
					interestGroupId, semanticsSet.toArray(new String[0]));

			}
			else {
				List<SemanticsFile> semanticslist =
					interestGroupPersistence.getSemanticsFiles(interestGroupId);

				interestGroupPersistence.clearSemanticsFiles(interestGroupId);
				interestGroupPersistence.remove(interestGroupId);
				for (SemanticsFile sem : semanticslist) {
					List<InterestGroup> interestGrouplist =
						semanticsFilePersistence.getInterestGroups(
							sem.getPrimaryKey());

					if (interestGrouplist.isEmpty()) {
						SemanticsFileLocalServiceUtil.removeCachedSemanticsFile(
							sem.getPrimaryKey());

					}
				}
			}
		}
		catch (NoSuchInterestGroupException ex) {
			throw new IWebException(ex);
		}
		catch (SystemException ex) {
			throw new IWebException(ex);
		}
	}

	/**
	 * Takes a map where each entry in the map has the interestGroup id as the
	 * key and a set of semanticsElement URIs as the value and persists in the
	 * mapping table.
	 */

	public void updateInterestGroupWithSemantics(
			Map<Long, Set<String>> interestGroupSemanticsMap)
		throws IWebException {

		Set<Map.Entry<Long, Set<String>>> entries =
			interestGroupSemanticsMap.entrySet();

		for (Map.Entry<Long, Set<String>> entry : entries) {
			updateInterestGroupWithSemantics(entry.getKey(), entry.getValue());
		}
	}

}