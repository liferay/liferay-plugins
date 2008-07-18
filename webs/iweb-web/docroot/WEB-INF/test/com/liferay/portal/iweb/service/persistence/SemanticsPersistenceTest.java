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

package com.liferay.portal.iweb.service.persistence;

import com.liferay.portal.iweb.NoSuchSemanticsException;
import com.liferay.portal.iweb.model.Semantics;
import com.liferay.portal.service.persistence.BasePersistenceTestCase;

/**
 * <a href="SemanticsPersistenceTest.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SemanticsPersistenceTest extends BasePersistenceTestCase {
	protected void setUp() throws Exception {
		super.setUp();

		_persistence = (SemanticsPersistence)com.liferay.portal.kernel.bean.PortalBeanLocatorUtil.locate(_TX_IMPL);
	}

	public void testCreate() throws Exception {
		String pk = randomString();

		Semantics semantics = _persistence.create(pk);

		assertNotNull(semantics);

		assertEquals(semantics.getPrimaryKey(), pk);
	}

	public void testRemove() throws Exception {
		Semantics newSemantics = addSemantics();

		_persistence.remove(newSemantics);

		Semantics existingSemantics = _persistence.fetchByPrimaryKey(newSemantics.getPrimaryKey());

		assertNull(existingSemantics);
	}

	public void testUpdateNew() throws Exception {
		addSemantics();
	}

	public void testUpdateExisting() throws Exception {
		String pk = randomString();

		Semantics newSemantics = _persistence.create(pk);

		newSemantics.setSemanticsName(randomString());
		newSemantics.setCreatedInCommunity(nextLong());

		_persistence.update(newSemantics, false);

		Semantics existingSemantics = _persistence.findByPrimaryKey(newSemantics.getPrimaryKey());

		assertEquals(existingSemantics.getSemanticsName(),
			newSemantics.getSemanticsName());
		assertEquals(existingSemantics.getSemanticsURI(),
			newSemantics.getSemanticsURI());
		assertEquals(existingSemantics.getCreatedInCommunity(),
			newSemantics.getCreatedInCommunity());
	}

	public void testFindByPrimaryKeyExisting() throws Exception {
		Semantics newSemantics = addSemantics();

		Semantics existingSemantics = _persistence.findByPrimaryKey(newSemantics.getPrimaryKey());

		assertEquals(existingSemantics, newSemantics);
	}

	public void testFindByPrimaryKeyMissing() throws Exception {
		String pk = randomString();

		try {
			_persistence.findByPrimaryKey(pk);

			fail("Missing entity did not throw NoSuchSemanticsException");
		}
		catch (NoSuchSemanticsException nsee) {
		}
	}

	public void testFetchByPrimaryKeyExisting() throws Exception {
		Semantics newSemantics = addSemantics();

		Semantics existingSemantics = _persistence.fetchByPrimaryKey(newSemantics.getPrimaryKey());

		assertEquals(existingSemantics, newSemantics);
	}

	public void testFetchByPrimaryKeyMissing() throws Exception {
		String pk = randomString();

		Semantics missingSemantics = _persistence.fetchByPrimaryKey(pk);

		assertNull(missingSemantics);
	}

	protected Semantics addSemantics() throws Exception {
		String pk = randomString();

		Semantics semantics = _persistence.create(pk);

		semantics.setSemanticsName(randomString());
		semantics.setCreatedInCommunity(nextLong());

		_persistence.update(semantics, false);

		return semantics;
	}

	private static final String _TX_IMPL = SemanticsPersistence.class.getName() +
		".transaction";
	private SemanticsPersistence _persistence;
}