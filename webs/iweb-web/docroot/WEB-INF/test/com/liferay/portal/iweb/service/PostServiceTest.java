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

import java.util.Set;
import java.util.TreeSet;

import com.liferay.portal.iweb.model.Post;
import com.liferay.portal.iweb.model.SemanticsElement;
import com.liferay.portal.service.BaseServiceTestCase;

public class PostServiceTest extends BaseServiceTestCase {

	public void testUpdatePostWithSemanticElements1() throws Exception {
		Set<String> semelements = new TreeSet();
		semelements.add(_semanticselemuri1);
		PostLocalServiceUtil.updatePostsWithSemanticsElements(
			_postid1, "blog",semelements);
		
		semelements.remove(_semanticselemuri1);
		semelements.add(_semanticselemuri2);
		PostLocalServiceUtil.updatePostsWithSemanticsElements(
			_postid2, "blog",semelements);

		semelements.remove(_semanticselemuri2);
		semelements.add(_semanticselemuri3);
		PostLocalServiceUtil.updatePostsWithSemanticsElements(
			_postid3, "blog",semelements);

	}

	public void getAppliedSemanticsElements() throws Exception {
		Set<SemanticsElement> semset = 
			PostLocalServiceUtil.getAppliedSemanticsElements(_postid1, "blog");

		for(SemanticsElement semelement: semset){
			if(_semanticselemuri1.equals(semelement.getElementURI())){
				assertEquals(_semanticselemuri1, semelement.getElementURI());
			}
		}
	}
	
	public void testRelatedPosts() throws Exception {
		Set<Post> posts = 
			PostLocalServiceUtil.getRelatedPostsByAppliedSemanticsElements(
				_postid1, "blog", null,null, true);

		assertNotNull(posts);
	}

	private long _postid1 = 10168;
	private long _postid2 = 10171;
	private long _postid3 = 10174;
	private String _semanticselemuri1 = 
		"http://www.co-ode.org/ontologies/pizza/2005/10/18/pizza.owl" +
		"#VegetarianTopping";

	private String _semanticselemuri2 = 
		"http://www.co-ode.org/ontologies/pizza/2005/10/18/pizza.owl" +
		"#CheeseTopping";

	private String _semanticselemuri3 = 
		"http://www.co-ode.org/ontologies/pizza/2005/10/18/pizza.owl" +
		"#FruitTopping";

}
