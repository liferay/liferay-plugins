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

import java.util.Map;
import java.util.Set;

import com.liferay.portal.iweb.IWebException;
import com.liferay.portal.iweb.model.Semantics;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.BaseServiceTestCase;
import com.liferay.portal.service.GroupServiceUtil;

public class BaseCommunityServiceTest extends BaseServiceTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		group1 = GroupServiceUtil.addGroup(
			"testgroup1", "testgroup1", 1,"/testgroup1", true);

		group2 = GroupServiceUtil.addGroup(
			"testgroup2", "testgroup2", 1,"/testgroup2", true);

		group3 = GroupServiceUtil.addGroup(
			"testgroup3", "testgroup3", 3,"/testgroup3", true);

		Semantics sem = SemanticsLocalServiceUtil.getSemantics(ontologyURI1);
		if (sem == null) {
			SemanticsLocalServiceUtil.createNewSemantics();
		}
		sem.setCreatedInCommunity(group1.getPrimaryKey());
		SemanticsLocalServiceUtil.updateSemantics(sem);
		sem = SemanticsLocalServiceUtil.getSemantics(ontologyURI2);
		sem.setCreatedInCommunity(group2.getPrimaryKey());
		SemanticsLocalServiceUtil.updateSemantics(sem);
		sem = SemanticsLocalServiceUtil.getSemantics(ontologyURI3);
		sem.setCreatedInCommunity(group3.getPrimaryKey());
		SemanticsLocalServiceUtil.updateSemantics(sem);
	}

	public void updateCommunityWithSemantics(
			long cid, Set<String> semanticsset) throws IWebException {

		CommunityLocalServiceUtil.updateCommunityWithSemantics(
			cid,semanticsset);
	}

	public void updateCommunityWithSemantics(
			Map<Long, Set<String>> communitySemanticsMap) throws IWebException {

			CommunityLocalServiceUtil.updateCommunityWithSemantics(
				communitySemanticsMap);
	}

	public void tearDown() throws Exception {
		GroupServiceUtil.deleteGroup(group1.getPrimaryKey());
		GroupServiceUtil.deleteGroup(group2.getPrimaryKey());
		GroupServiceUtil.deleteGroup(group3.getPrimaryKey());
		super.tearDown();
	}

	Group group1 = null;
	Group group2 = null;
	Group group3 = null;
	String ontologyURI1 = 
		"http://www.co-ode.org/ontologies/pizza/2005/10/18/pizza.owl";

	String ontologyURI2 = "http://purl.org/obo/owl/cell";
	String ontologyURI3 = "http://ccdb.ucsd.edu/SAO/1.2";


}