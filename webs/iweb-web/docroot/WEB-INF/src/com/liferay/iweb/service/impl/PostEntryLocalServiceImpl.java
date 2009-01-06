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
 * Copyright 2008 Sun Microsystems Inc. All rights reserved.
 */

package com.liferay.iweb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.liferay.iweb.IWebException;
import com.liferay.iweb.model.PostEntry;
import com.liferay.iweb.model.SemanticsElement;
import com.liferay.iweb.model.impl.PostEntryImpl;
import com.liferay.iweb.service.SemanticsFileLocalServiceUtil;
import com.liferay.iweb.service.base.PostEntryLocalServiceBaseImpl;
import com.liferay.portal.SystemException;

/**
 * <a href="PostEntryLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Prakash Radhakrishnan
 *
 */
public class PostEntryLocalServiceImpl extends PostEntryLocalServiceBaseImpl {

	/*
	 * This method takes a map with each key-value pair representing
	 * the postEntryId and postEntryType and returns a map with key-value pairs
	 * representing the postEntryId and the associated SemanticsElements.
	 */

	public Map<Long, Set<SemanticsElement>> getAppliedSemantics(
			Map<Long, String> postEntryId_Type)
		throws IWebException {

		Map<Long, Set<SemanticsElement>> postEntrySemantics = new HashMap();
		Set<Map.Entry<Long, String>> entries = postEntryId_Type.entrySet();
		for (Map.Entry<Long, String> entry : entries) {
			long postEntryId = entry.getKey();
			String postEntryType = entry.getValue();
			postEntrySemantics.put(
				postEntryId, getAppliedSemanticsElements(
					postEntryId,postEntryType));

		}
		return postEntrySemantics;
	}

	/*
	 * This method takes the postEntryId and postEntryType and returns the
	 * semantics elements associated with that postEntry.
	 */

	public Set<SemanticsElement> getAppliedSemanticsElements(long postEntryId,
			String postEntryType)
		throws IWebException {

		try {
			PostEntry postEntry = postEntryPersistence.fetchById_Type(
				postEntryId, postEntryType);

			long pk = postEntry.getPrimaryKey();
			Set<SemanticsElement> semanticsElementSet = new TreeSet(
				postEntryPersistence.getSemanticsElements(pk));

			return semanticsElementSet;
		}
		catch (SystemException ex) {
			throw new IWebException(ex);
		}
	}

	/*
	 * This method gets the related postEntries for a given given postEntry
	 * identified by the postEntryId and postEntryType.
	 * The reason parameter specifies whether the reasoner has to be invoked to
	 * get the related postEntries.
	 * The requiredTypes and interestGroupIds set are for filtering the related
	 * postEntrytEntries obtained. For example if only the related postEntries
	 * of type "blog" are to be obtained then the requiredTypes will have only 
	 * "blog".
	 * Similarly if related postEntries belonging to a particular interestGroup
	 * or a set of communities are be obtained then the interestGroupIds set is
	 * populated with the required interestGroup Ids.
	 */

	public Set<PostEntry> getRelatedPostEntriesByAppliedSemanticsElements(
			Long postEntryId, String postEntryType, Set<String> requiredTypes,
				Set<Long> interestGroupIds,	boolean reason)
		throws IWebException {

		try {
			Set<PostEntry> poststoremove = new TreeSet();
			Set<PostEntry> postEntries = new TreeSet();
			PostEntry postEntry = postEntryPersistence.fetchById_Type(
				postEntryId, postEntryType);

			long uuid = postEntry.getPrimaryKey();
			Set<SemanticsElement> semanticsElements = new TreeSet(
				postEntryPersistence.getSemanticsElements(uuid));

			semanticsElements.addAll(
				SemanticsFileLocalServiceUtil.getRelatedSemanticsElements(
					semanticsElements, reason));

			for (SemanticsElement element : semanticsElements) {
				postEntries.addAll(
					semanticsElementPersistence.getPostEntries(
						element.getElementURI()));

			}
			for (PostEntry postentry : postEntries) {
				if (postentry.getPid() == postEntryId) {
					poststoremove.add(postentry);
					break;
				}
			}

			if (requiredTypes != null && !requiredTypes.isEmpty()) {
				for (PostEntry postentry : postEntries) {
					String type = postentry.getType();
					if (!requiredTypes.contains(type)) {
						poststoremove.add(postentry);
					}
				}

			}
			if (interestGroupIds != null && !interestGroupIds.isEmpty()) {
				for (PostEntry postentry : postEntries) {
					long interestGroupId = postentry.getInterestGroupId();
					if (!interestGroupIds.contains(interestGroupId)) {
						poststoremove.add(postentry);
					}
				}
			}
			if (!poststoremove.isEmpty()) {
				postEntries.removeAll(poststoremove);
			}
			return postEntries;
		}
		catch (SystemException ex) {
			throw new IWebException(ex);
		}
	}

	/*
	 * This method updates a postEntry identified by the postEntryId and type
	 * with the semantics elements specified in the semanticsElementSet.
	 */

	public void updatePostEntriesWithSemanticsElements(
			long postEntryId, String type,Set<String> semanticsElementSet)
		throws IWebException {

		try {
			long uid = 0;
			if (postEntryPersistence.fetchById_Type(postEntryId, type) == null){
				PostEntry postEntry = new PostEntryImpl();
				uid = counterLocalService.increment();
				postEntry.setUid(uid);
				postEntry.setNew(true);
				postEntry.setPid(postEntryId);
				postEntry.setType(type);
				postEntryPersistence.update(postEntry, false);
			}
			if (semanticsElementSet != null) {
				for (String semElementURI : semanticsElementSet) {
					if (semanticsElementPersistence.fetchByPrimaryKey(
						semElementURI) == null) {

						SemanticsElement element =
							semanticsElementPersistence.create(
								semElementURI);

						semanticsElementPersistence.update(element, false);
						if (semElementURI.indexOf("#") != -1) {
							element.setSemanticsURI(
								semElementURI.substring(
									0,semElementURI.indexOf("#")));

						}
					}
				}
			}
			if (semanticsElementSet != null && !semanticsElementSet.isEmpty()) {
				postEntryPersistence.setSemanticsElements(
					uid, semanticsElementSet.toArray(new String[0]));

			}
			else {
				Set<SemanticsElement> semElements = new TreeSet(
					postEntryPersistence.getSemanticsElements(postEntryId));

				postEntryPersistence.clearSemanticsElements(uid);
				postEntryPersistence.removeById_Type(postEntryId, type);
				for (SemanticsElement semElement : semElements) {
					List postsbysem = 
						semanticsElementPersistence.getPostEntries(
							semElement.getElementURI());

					if (postsbysem.isEmpty() || postsbysem == null) {
						semanticsElementPersistence.remove(
							semElement.getElementURI());

					}
				}
			}
		}
		catch (Exception ex) {
			throw new IWebException(ex);
		}
	}

}