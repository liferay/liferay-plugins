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

package com.liferay.portal.iweb.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.liferay.portal.iweb.model.Community;
import com.liferay.portal.iweb.model.Semantics;
import com.liferay.portal.service.GroupServiceUtil;

public class CommunityServiceTest extends BaseCommunityServiceTest {
	
	
	/*This test case does the following:
	 * 1. Update a community with semantics.
	 * 2. Get the semantics applied for each community.
	 * 3. Remove the semantics associated with the community
	 *  and check if it is removed.
	 * When a community doesn't have a semantics associated,
	 *  the community entity is removed as well.
	 * A few warnings are thrown when the groups
	 *  are deleted as part of tearDown().
	 */

	public void testCommunityService() throws Exception {
		Set<String> semanticsset = new TreeSet();
		semanticsset.add(ontologyURI1);
		updateCommunityWithSemantics(group1.getPrimaryKey(),semanticsset);
		Map<Long,Set<Semantics>> commSemantics = 
			CommunityLocalServiceUtil.getAppliedSemantics(
					group1.getPrimaryKey());

		Set<Map.Entry<Long, Set<Semantics>>> entries = commSemantics.entrySet();
		for(Map.Entry<Long, Set<Semantics>> entry: entries){
			long id = entry.getKey();
			Set<Semantics> appliedSemantics = entry.getValue();
			assertEquals(false,appliedSemantics.isEmpty());
		}
		
		updateCommunityWithSemantics(group1.getGroupId(),null);
	}
	
	/*This test case does the following:
	 * 1. Update 3 communities with semantics.
	 * 2. Get the semantics applied for each community.
	 * 3. Getting the related communites for each community.
	 * 4. Remove the semantics associated with each community.
	 * When a community doesn't have a semantics,
	 *  the community entity is removed as well.
	 * A few warnings are thrown when the groups
	 *  are deleted as part of tearDown().
	 */

	public void testCommunityServiceforMulitpleCommunities() throws Exception{
		Map<Long,Set<String>> semanticsmap = new HashMap();
		Set<String> semanticsset1 = new TreeSet();
		semanticsset1.add(ontologyURI1);
		semanticsset1.add(ontologyURI2);
		semanticsmap.put(group1.getPrimaryKey(),semanticsset1);
		Set<String> semanticsset2 = new TreeSet();
		semanticsset2.add(ontologyURI1);
		semanticsmap.put(group2.getPrimaryKey(),semanticsset2);
		Set<String> semanticsset3 = new TreeSet();
		semanticsset3.add(ontologyURI2);
		semanticsmap.put(group3.getPrimaryKey(),semanticsset3);
		updateCommunityWithSemantics(semanticsmap);
		Set<Long> communityIds = new TreeSet();
		communityIds.add(group1.getPrimaryKey());
		communityIds.add(group2.getPrimaryKey());
		communityIds.add(group3.getPrimaryKey());
		Map<Long,Set<Semantics>> commSemantics = 
			CommunityLocalServiceUtil.getAppliedSemantics(communityIds);
		Set<Map.Entry<Long, Set<Semantics>>> entries = 
			commSemantics.entrySet();
		for(Map.Entry<Long, Set<Semantics>> entry: entries){
			long id = entry.getKey();
			Set<Semantics> appliedSemantics = entry.getValue();
			assertEquals(false,appliedSemantics.isEmpty());
		}
		Set<Community> relatedCommunities = 
			CommunityLocalServiceUtil.getRelatedCommunitiesByAppliedSemantics(
				new Long(group1.getPrimaryKey()));

		assertEquals(false,relatedCommunities.isEmpty());
		relatedCommunities = 
			CommunityLocalServiceUtil.getRelatedCommunitiesByAppliedSemantics(
				new Long(group2.getPrimaryKey()));

		assertEquals(false,relatedCommunities.isEmpty());
		relatedCommunities = 
			CommunityLocalServiceUtil.getRelatedCommunitiesByAppliedSemantics(
					new Long(group3.getPrimaryKey()));

		assertEquals(false,relatedCommunities.isEmpty());
		semanticsmap.put(group1.getPrimaryKey(),null);
		semanticsmap.put(group2.getPrimaryKey(),null);
		semanticsmap.put(group3.getPrimaryKey(), null);
		updateCommunityWithSemantics(semanticsmap);
		Set<Community> comm = CommunityLocalServiceUtil.getAllCommunities();
		assertNull(comm);
	}
	
}
