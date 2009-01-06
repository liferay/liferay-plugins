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

package com.liferay.iweb.service.persistence;

import com.liferay.iweb.NoSuchSemanticsElementException;
import com.liferay.iweb.model.SemanticsElement;
import com.liferay.portal.service.persistence.BasePersistenceTestCase;

/**
 * <a href="SemanticsElementPersistenceTest.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SemanticsElementPersistenceTest extends BasePersistenceTestCase {
	protected void setUp() throws Exception {
		super.setUp();

		_persistence = (SemanticsElementPersistence)com.liferay.portal.kernel.bean.PortalBeanLocatorUtil.locate(_TX_IMPL);
	}

	public void testCreate() throws Exception {
		String pk = randomString();

		SemanticsElement semanticsElement = _persistence.create(pk);

		assertNotNull(semanticsElement);

		assertEquals(semanticsElement.getPrimaryKey(), pk);
	}

	public void testRemove() throws Exception {
		SemanticsElement newSemanticsElement = addSemanticsElement();

		_persistence.remove(newSemanticsElement);

		SemanticsElement existingSemanticsElement = _persistence.fetchByPrimaryKey(newSemanticsElement.getPrimaryKey());

		assertNull(existingSemanticsElement);
	}

	public void testUpdateNew() throws Exception {
		addSemanticsElement();
	}

	public void testUpdateExisting() throws Exception {
		String pk = randomString();

		SemanticsElement newSemanticsElement = _persistence.create(pk);

		newSemanticsElement.setSemanticsURI(randomString());

		_persistence.update(newSemanticsElement, false);

		SemanticsElement existingSemanticsElement = _persistence.findByPrimaryKey(newSemanticsElement.getPrimaryKey());

		assertEquals(existingSemanticsElement.getElementURI(),
			newSemanticsElement.getElementURI());
		assertEquals(existingSemanticsElement.getSemanticsURI(),
			newSemanticsElement.getSemanticsURI());
	}

	public void testFindByPrimaryKeyExisting() throws Exception {
		SemanticsElement newSemanticsElement = addSemanticsElement();

		SemanticsElement existingSemanticsElement = _persistence.findByPrimaryKey(newSemanticsElement.getPrimaryKey());

		assertEquals(existingSemanticsElement, newSemanticsElement);
	}

	public void testFindByPrimaryKeyMissing() throws Exception {
		String pk = randomString();

		try {
			_persistence.findByPrimaryKey(pk);

			fail("Missing entity did not throw NoSuchSemanticsElementException");
		}
		catch (NoSuchSemanticsElementException nsee) {
		}
	}

	public void testFetchByPrimaryKeyExisting() throws Exception {
		SemanticsElement newSemanticsElement = addSemanticsElement();

		SemanticsElement existingSemanticsElement = _persistence.fetchByPrimaryKey(newSemanticsElement.getPrimaryKey());

		assertEquals(existingSemanticsElement, newSemanticsElement);
	}

	public void testFetchByPrimaryKeyMissing() throws Exception {
		String pk = randomString();

		SemanticsElement missingSemanticsElement = _persistence.fetchByPrimaryKey(pk);

		assertNull(missingSemanticsElement);
	}

	protected SemanticsElement addSemanticsElement() throws Exception {
		String pk = randomString();

		SemanticsElement semanticsElement = _persistence.create(pk);

		semanticsElement.setSemanticsURI(randomString());

		_persistence.update(semanticsElement, false);

		return semanticsElement;
	}

	private static final String _TX_IMPL = SemanticsElementPersistence.class.getName() +
		".transaction";
	private SemanticsElementPersistence _persistence;
}