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

package com.liferay.iweb.service;

import java.util.Set;
import java.util.TreeSet;

import com.liferay.iweb.model.PostEntry;
import com.liferay.iweb.model.SemanticsElement;
import com.liferay.portal.service.BaseServiceTestCase;

/**
 * <a href="PostEntryServiceTest.java.html"><b><i>View Source</i></b></a>
 *
 * @author Prakash Radhakrishnan
 *
 */
public class PostEntryServiceTest extends BaseServiceTestCase {

	private long postid1 = 10168;
	private long postid2 = 10171;
	private long postid3 = 10174;
	String semanticselemuri1 = 
		"http://www.co-ode.org/ontologies/pizza/2005/10/18/pizza.owl" +
		"#VegetarianTopping";

	String semanticselemuri2 = 
		"http://www.co-ode.org/ontologies/pizza/2005/10/18/pizza.owl" +
		"#CheeseTopping";

	String semanticselemuri3 = 
		"http://www.co-ode.org/ontologies/pizza/2005/10/18/pizza.owl" +
		"#FruitTopping";

	public void testUpdatePostEntryWithSemanticElements1() throws Exception {
		Set<String> semelements = new TreeSet();
		semelements.add(semanticselemuri1);
		PostEntryLocalServiceUtil.updatePostEntriesWithSemanticsElements(
			postid1, "blog",semelements);
		
		semelements.remove(semanticselemuri1);
		semelements.add(semanticselemuri2);
		PostEntryLocalServiceUtil.updatePostEntriesWithSemanticsElements(
			postid2, "blog",semelements);

		semelements.remove(semanticselemuri2);
		semelements.add(semanticselemuri3);
		PostEntryLocalServiceUtil.updatePostEntriesWithSemanticsElements(
			postid3, "blog",semelements);

	}

	public void getAppliedSemanticsElements() throws Exception {
		Set<SemanticsElement> semset = 
			PostEntryLocalServiceUtil.getAppliedSemanticsElements(
				postid1, "blog");

		for(SemanticsElement semelement: semset){
			if(semanticselemuri1.equals(semelement.getElementURI())){
				assertEquals(semanticselemuri1, semelement.getElementURI());
			}
		}
	}
	
	public void testRelatedPostEntries() throws Exception {
		Set<PostEntry> posts = PostEntryLocalServiceUtil
			.getRelatedPostEntriesByAppliedSemanticsElements(
				postid1, "blog", null,null, true);

		assertNotNull(posts);
	}

}
