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

package com.liferay.portal.iweb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.liferay.portal.SystemException;
import com.liferay.portal.iweb.IWebException;
import com.liferay.portal.iweb.model.Post;
import com.liferay.portal.iweb.model.SemanticsElement;
import com.liferay.portal.iweb.model.impl.PostImpl;
import com.liferay.portal.iweb.service.base.PostLocalServiceBaseImpl;

/**
 * <a href="PostLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 * 
 * @author Brian Wing Shun Chan
 * @author Prakash Radhakrishnan
 * 
 */
public class PostLocalServiceImpl extends PostLocalServiceBaseImpl {

	/*
	 * This method takes a map with each key-value pair representing  
	 * the postId and postType and returns a map with key-value pairs
	 * representing the postId and the associated SemanticElements.
	 */

	public Map<Long, Set<SemanticsElement>> getAppliedSemantics(
		Map<Long, String> postId_Type) throws IWebException {
		Map<Long, Set<SemanticsElement>> postSemantics = new HashMap();
		Set<Map.Entry<Long, String>> entries = postId_Type.entrySet();
		for (Map.Entry<Long, String> entry : entries) {
			long postId = entry.getKey();
			String postType = entry.getValue();
			postSemantics.put(
				postId, getAppliedSemanticsElements(postId,postType));

		}
		return postSemantics;
	}

	/*
	 * This method takes the postId and postType and returns the semantic
	 * elements associated with that post.
	 */
	
	public Set<SemanticsElement> getAppliedSemanticsElements(long postId,
		String postType) throws IWebException {
		try {
			Post post = postPersistence.fetchById_Type(postId, postType);
			long pk = post.getPrimaryKey();
			Set<SemanticsElement> semanticsElementSet = new TreeSet(
				postPersistence.getSemanticsElements(pk));

			return semanticsElementSet;
		}
		catch (SystemException ex) {
			throw new IWebException(ex);
		}
	}

	/*
	 * This method gets the related posts for a given given post identified
	 * by the postId and postType.
	 * The reason parameter specifies whether the reasoner has to be invoked
	 * to get the related posts.
	 * The requiredTypes and communityIds set are for filtering the related
	 * posts obtained. For example if only the related posts of type "blog"
	 * are to be obtained then the requiredTypes will have only "blog".
	 * Similarly if related posts belonging to a particular community or a 
	 * set of communities are be obtained then the communityIds set is
	 * populated with the required community Ids. 
	 */
	
	public Set<Post> getRelatedPostsByAppliedSemanticsElements(Long postId,
		String postType, Set<String> requiredTypes, Set<Long> communityIds,
		boolean reason) throws IWebException {

		try {
			Set<Post> poststoremove = new TreeSet();
			Set<Post> posts = new TreeSet();
			Post post = postPersistence.fetchById_Type(postId, postType);
			long uuid = post.getPrimaryKey();
			Set<SemanticsElement> semanticsElements = new TreeSet(
				postPersistence.getSemanticsElements(uuid));
			semanticsElements.addAll(
				semanticsLocalService.getRelatedSemanticsElements(
					semanticsElements, reason));

			for (SemanticsElement element : semanticsElements) {
				posts.addAll(
					semanticsElementPersistence.getPosts(
						element.getElementURI()));

			}
			for (Post postentry : posts) {
				if (postentry.getPid() == postId) {
					poststoremove.add(postentry);
					break;
				}
			}

			if (requiredTypes != null && !requiredTypes.isEmpty()) {
				for (Post postentry : posts) {
					String type = postentry.getType();
					if (!requiredTypes.contains(type)) {
						poststoremove.add(postentry);
					}
				}

			}
			if (communityIds != null && !communityIds.isEmpty()) {
				for (Post postentry : posts) {
					long communityId = postentry.getCommunityid();
					if (!communityIds.contains(communityId)) {
						poststoremove.add(postentry);
					}
				}
			}
			if (!poststoremove.isEmpty()) {
				posts.removeAll(poststoremove);
			}
			return posts;
		}
		catch (SystemException ex) {
			throw new IWebException(ex);
		}
	}

	/*
	 * This method updates a post identified by the postId and type with
	 * the semantics elements specified in the semanticsElementSet.
	 */

	public void updatePostsWithSemanticsElements(
		long postId, String type,Set<String> semanticsElementSet)
			throws IWebException {

		try {
			long uid = 0;
			if (postPersistence.fetchById_Type(postId, type) == null) {
				Post post = new PostImpl();
				uid = counterLocalService.increment();
				post.setUid(uid);
				post.setNew(true);
				post.setPid(postId);
				post.setType(type);
				postPersistence.update(post, false);
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
				postPersistence.setSemanticsElements(
					uid, semanticsElementSet.toArray(new String[0]));

			}
			else {
				Set<SemanticsElement> semElements = new TreeSet(
					postPersistence.getSemanticsElements(postId));

				postPersistence.clearSemanticsElements(uid);
				postPersistence.removeById_Type(postId, type);
				for (SemanticsElement semElement : semElements) {
					List postsbysem = semanticsElementPersistence.getPosts(
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