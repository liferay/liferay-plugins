/*
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

package com.liferay.util.bridges.simplemvc;

import junit.framework.TestCase;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * <a href="ActionCacheTest.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class ActionCacheTest extends TestCase {

	@Test
	public void testGetAction()
	throws Exception {
		Action action = _cache.getAction("testMe");
		assertTrue(action.getClass().getName().equals("com.liferay.util.bridges.simplemvc.TestMeAction"));
	}

	@Test
	public void testGetActionChain()
	throws Exception {
		List<Action> actions = _cache.getActionChain("testMe,testMe2,testMe");
		assertEquals(3, actions.size());
		assertTrue(actions.get(0).getClass().getName().equals("com.liferay.util.bridges.simplemvc.TestMeAction"));
		assertTrue(actions.get(1).getClass().getName().equals("com.liferay.util.bridges.simplemvc.TestMe2Action"));
		assertTrue(actions.get(2).getClass().getName().equals("com.liferay.util.bridges.simplemvc.TestMeAction"));

		//test to make sure cached objects...
		List<Action> actions2 = _cache.getActionChain("testMe,testMe2,testMe");
		assertTrue(actions2 == actions);
	}

	@Before
	public void setUp() throws Exception {
		_cache = new ActionCache(getClass().getPackage().getName());
	}

	private ActionCache _cache;
}
