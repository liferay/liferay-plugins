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

package com.liferay.iweb.service.persistence;

import com.liferay.iweb.NoSuchSemanticsFileException;
import com.liferay.iweb.model.SemanticsFile;
import com.liferay.portal.service.persistence.BasePersistenceTestCase;

/**
 * <a href="SemanticsFilePersistenceTest.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SemanticsFilePersistenceTest extends BasePersistenceTestCase {
	protected void setUp() throws Exception {
		super.setUp();

		_persistence = (SemanticsFilePersistence)com.liferay.portal.kernel.bean.PortalBeanLocatorUtil.locate(_TX_IMPL);
	}

	public void testCreate() throws Exception {
		String pk = randomString();

		SemanticsFile semantics = _persistence.create(pk);

		assertNotNull(semantics);

		assertEquals(semantics.getPrimaryKey(), pk);
	}

	public void testRemove() throws Exception {
		SemanticsFile newSemantics = addSemantics();

		_persistence.remove(newSemantics);

		SemanticsFile existingSemantics = _persistence.fetchByPrimaryKey(newSemantics.getPrimaryKey());

		assertNull(existingSemantics);
	}

	public void testUpdateNew() throws Exception {
		addSemantics();
	}

	public void testUpdateExisting() throws Exception {
		String pk = randomString();

		SemanticsFile newSemantics = _persistence.create(pk);

		newSemantics.setSemanticsName(randomString());
		newSemantics.setCreatedInInterestGroup(nextLong());

		_persistence.update(newSemantics, false);

		SemanticsFile existingSemantics = _persistence.findByPrimaryKey(newSemantics.getPrimaryKey());

		assertEquals(existingSemantics.getSemanticsName(),
			newSemantics.getSemanticsName());
		assertEquals(existingSemantics.getSemanticsURI(),
			newSemantics.getSemanticsURI());
		assertEquals(existingSemantics.getCreatedInInterestGroup(),
			newSemantics.getCreatedInInterestGroup());
	}

	public void testFindByPrimaryKeyExisting() throws Exception {
		SemanticsFile newSemantics = addSemantics();

		SemanticsFile existingSemantics = _persistence.findByPrimaryKey(newSemantics.getPrimaryKey());

		assertEquals(existingSemantics, newSemantics);
	}

	public void testFindByPrimaryKeyMissing() throws Exception {
		String pk = randomString();

		try {
			_persistence.findByPrimaryKey(pk);

			fail("Missing entity did not throw NoSuchSemanticsFileException");
		}
		catch (NoSuchSemanticsFileException nsee) {
		}
	}

	public void testFetchByPrimaryKeyExisting() throws Exception {
		SemanticsFile newSemantics = addSemantics();

		SemanticsFile existingSemantics = _persistence.fetchByPrimaryKey(newSemantics.getPrimaryKey());

		assertEquals(existingSemantics, newSemantics);
	}

	public void testFetchByPrimaryKeyMissing() throws Exception {
		String pk = randomString();

		SemanticsFile missingSemantics = _persistence.fetchByPrimaryKey(pk);

		assertNull(missingSemantics);
	}

	protected SemanticsFile addSemantics() throws Exception {
		String pk = randomString();

		SemanticsFile semantics = _persistence.create(pk);

		semantics.setSemanticsName(randomString());
		semantics.setCreatedInInterestGroup(nextLong());

		_persistence.update(semantics, false);

		return semantics;
	}

	private static final String _TX_IMPL = SemanticsFilePersistence.class.getName() +
		".transaction";
	private SemanticsFilePersistence _persistence;
}