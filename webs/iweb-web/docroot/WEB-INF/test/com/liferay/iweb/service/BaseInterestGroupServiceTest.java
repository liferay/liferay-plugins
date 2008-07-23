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

package com.liferay.iweb.service;

import java.util.Map;
import java.util.Set;

import com.liferay.iweb.IWebException;
import com.liferay.iweb.model.SemanticsFile;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.BaseServiceTestCase;
import com.liferay.portal.service.GroupServiceUtil;

/**
 * <a href="BaseInterestGroupServiceTest.java.html"><b><i>View Source</i></b></a>
 *
 * @author Prakash Radhakrishnan
 *
 */
public class BaseInterestGroupServiceTest extends BaseServiceTestCase {

	Group group1 = null;
	Group group2 = null;
	Group group3 = null;
	String ontologyURI1 = 
		"http://www.co-ode.org/ontologies/pizza/2005/10/18/pizza.owl";
	String ontologyURI2 = "http://purl.org/obo/owl/cell";
	String ontologyURI3 = "http://ccdb.ucsd.edu/SAO/1.2";

	protected void setUp() throws Exception {

		super.setUp();
		group1 = GroupServiceUtil.addGroup("testgroup1", "testgroup1", 1,
				"/testgroup1", true);

		group2 = GroupServiceUtil.addGroup("testgroup2", "testgroup2", 1,
				"/testgroup2", true);

		group3 = GroupServiceUtil.addGroup("testgroup3", "testgroup3", 3,
				"/testgroup3", true);

		SemanticsFile sem = SemanticsFileLocalServiceUtil.getSemanticsFile(
			ontologyURI1);

		if (sem == null) {
			SemanticsFileLocalServiceUtil.createNewSemanticsFile();
		}
		sem.setCreatedInInterestGroup(group1.getPrimaryKey());
		SemanticsFileLocalServiceUtil.updateSemanticsFile(sem);
		sem = SemanticsFileLocalServiceUtil.getSemanticsFile(ontologyURI2);
		sem.setCreatedInInterestGroup(group2.getPrimaryKey());
		SemanticsFileLocalServiceUtil.updateSemanticsFile(sem);
		sem = SemanticsFileLocalServiceUtil.getSemanticsFile(ontologyURI3);
		sem.setCreatedInInterestGroup(group3.getPrimaryKey());
		SemanticsFileLocalServiceUtil.updateSemanticsFile(sem);

	}

	public void updateInterestGroupWithSemantics(
			long cid, Set<String> semanticsset) {

		try {
			InterestGroupLocalServiceUtil.updateInterestGroupWithSemantics(
				cid, semanticsset);

		} catch (IWebException e) {
			e.printStackTrace();
		}

	}

	public void updateInterestGroupWithSemantics(
			Map<Long, Set<String>> communitySemanticsMap) {

		try {
			InterestGroupLocalServiceUtil.updateInterestGroupWithSemantics(
				communitySemanticsMap);
			
		} catch (IWebException e) {
			e.printStackTrace();
		}

	}

	public void tearDown() throws Exception {
		GroupServiceUtil.deleteGroup(group1.getPrimaryKey());
		GroupServiceUtil.deleteGroup(group2.getPrimaryKey());
		GroupServiceUtil.deleteGroup(group3.getPrimaryKey());
		super.tearDown();
	}

}