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

import java.net.URI;
import java.util.Set;
import java.util.TreeSet;

import com.liferay.portal.iweb.model.Community;
import com.liferay.portal.iweb.model.Semantics;
import com.liferay.portal.iweb.model.SemanticsElement;
import com.liferay.portal.iweb.model.impl.SemanticsElementImpl;
import com.liferay.portal.iweb.model.impl.SemanticsImpl;

public class SemanticsServiceTest extends BaseCommunityServiceTest {

	public void testGetAllSemanticElements() throws Exception {
		Set<Semantics> semantics = new TreeSet();
		Semantics sem = new SemanticsImpl();
		sem.setSemanticsURI(ontologyURI1);
		semantics.add(sem);
		Semantics sem1 = new SemanticsImpl();
		sem1.setSemanticsURI(ontologyURI2);
		semantics.add(sem1);
		Set<Semantics> updatedsemantics = SemanticsLocalServiceUtil
				.getAllSemanticElements(semantics);
		for (Semantics s : updatedsemantics) {
			Set<SemanticsElement> elements = s.getSemanticselements();
			assertEquals(false,elements.isEmpty());
		}
	}

	public void testGetAvailablePublicCommunities() throws Exception {
		Set<Semantics> semset = SemanticsLocalServiceUtil
				.getAvailablePublicSemantics();

		for (Semantics sem : semset) {
			long commid = sem.getCreatedInCommunity();
			Community comm = CommunityLocalServiceUtil.getCommunity(commid);
			assertEquals(1,comm.getType());
		}
	}

	public void testGetSemanticElements() throws Exception {
		SemanticsElement element = new SemanticsElementImpl();
		element.setElementURI(ontologyURI1 + "#VegetarianTopping");
		Set<SemanticsElement> elemset = new TreeSet();
		elemset.add(element);
		Set<SemanticsElement> elements = 
			SemanticsLocalServiceUtil.getRelatedSemanticsElements(
				elemset, true);

		assertEquals(false,elements.isEmpty());
	}

}
