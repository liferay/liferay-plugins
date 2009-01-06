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

import com.liferay.iweb.NoSuchInterestGroupException;
import com.liferay.iweb.model.InterestGroup;
import com.liferay.portal.service.persistence.BasePersistenceTestCase;

/**
 * <a href="InterestGroupPersistenceTest.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class InterestGroupPersistenceTest extends BasePersistenceTestCase {
	protected void setUp() throws Exception {
		super.setUp();

		_persistence = (InterestGroupPersistence)com.liferay.portal.kernel.bean.PortalBeanLocatorUtil.locate(_TX_IMPL);
	}

	public void testCreate() throws Exception {
		long pk = nextLong();

		InterestGroup community = _persistence.create(pk);

		assertNotNull(community);

		assertEquals(community.getPrimaryKey(), pk);
	}

	public void testRemove() throws Exception {
		InterestGroup newInterestGroup = addInterestGroup();

		_persistence.remove(newInterestGroup);

		InterestGroup existingInterestGroup = _persistence.fetchByPrimaryKey(
			newInterestGroup.getPrimaryKey());

		assertNull(existingInterestGroup);
	}

	public void testUpdateNew() throws Exception {
		addInterestGroup();
	}

	public void testUpdateExisting() throws Exception {
		long pk = nextLong();

		InterestGroup newInterestGroup = _persistence.create(pk);

		newInterestGroup.setType(nextInt());

		_persistence.update(newInterestGroup, false);

		InterestGroup existingInterestGroup = _persistence.findByPrimaryKey(
			newInterestGroup.getPrimaryKey());

		assertEquals(existingInterestGroup.getCid(), newInterestGroup.getCid());
		assertEquals(
			existingInterestGroup.getType(), newInterestGroup.getType());

	}

	public void testFindByPrimaryKeyExisting() throws Exception {
		InterestGroup newInterestGroup = addInterestGroup();

		InterestGroup existingInterestGroup = _persistence.findByPrimaryKey(
			newInterestGroup.getPrimaryKey());

		assertEquals(existingInterestGroup, newInterestGroup);
	}

	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			fail("Missing entity did not throw NoSuchInterestGroupException");
		}
		catch (NoSuchInterestGroupException nsee) {
		}
	}

	public void testFetchByPrimaryKeyExisting() throws Exception {
		InterestGroup newInterestGroup = addInterestGroup();

		InterestGroup existingInterestGroup = _persistence.fetchByPrimaryKey(
			newInterestGroup.getPrimaryKey());

		assertEquals(existingInterestGroup, newInterestGroup);
	}

	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = nextLong();

		InterestGroup missingInterestGroup = _persistence.fetchByPrimaryKey(pk);

		assertNull(missingInterestGroup);
	}

	protected InterestGroup addInterestGroup() throws Exception {
		long pk = nextLong();

		InterestGroup community = _persistence.create(pk);

		community.setType(nextInt());

		_persistence.update(community, false);

		return community;
	}

	private static final String _TX_IMPL = 
		InterestGroupPersistence.class.getName() + ".transaction";
	private InterestGroupPersistence _persistence;

}