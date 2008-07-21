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

import com.liferay.iweb.NoSuchCommunityException;
import com.liferay.iweb.model.Community;
import com.liferay.portal.service.persistence.BasePersistenceTestCase;

/**
 * <a href="CommunityPersistenceTest.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class CommunityPersistenceTest extends BasePersistenceTestCase {
	protected void setUp() throws Exception {
		super.setUp();

		_persistence = (CommunityPersistence)com.liferay.portal.kernel.bean.PortalBeanLocatorUtil.locate(_TX_IMPL);
	}

	public void testCreate() throws Exception {
		long pk = nextLong();

		Community community = _persistence.create(pk);

		assertNotNull(community);

		assertEquals(community.getPrimaryKey(), pk);
	}

	public void testRemove() throws Exception {
		Community newCommunity = addCommunity();

		_persistence.remove(newCommunity);

		Community existingCommunity = _persistence.fetchByPrimaryKey(newCommunity.getPrimaryKey());

		assertNull(existingCommunity);
	}

	public void testUpdateNew() throws Exception {
		addCommunity();
	}

	public void testUpdateExisting() throws Exception {
		long pk = nextLong();

		Community newCommunity = _persistence.create(pk);

		newCommunity.setType(nextInt());

		_persistence.update(newCommunity, false);

		Community existingCommunity = _persistence.findByPrimaryKey(newCommunity.getPrimaryKey());

		assertEquals(existingCommunity.getCid(), newCommunity.getCid());
		assertEquals(existingCommunity.getType(), newCommunity.getType());
	}

	public void testFindByPrimaryKeyExisting() throws Exception {
		Community newCommunity = addCommunity();

		Community existingCommunity = _persistence.findByPrimaryKey(newCommunity.getPrimaryKey());

		assertEquals(existingCommunity, newCommunity);
	}

	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			fail("Missing entity did not throw NoSuchCommunityException");
		}
		catch (NoSuchCommunityException nsee) {
		}
	}

	public void testFetchByPrimaryKeyExisting() throws Exception {
		Community newCommunity = addCommunity();

		Community existingCommunity = _persistence.fetchByPrimaryKey(newCommunity.getPrimaryKey());

		assertEquals(existingCommunity, newCommunity);
	}

	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = nextLong();

		Community missingCommunity = _persistence.fetchByPrimaryKey(pk);

		assertNull(missingCommunity);
	}

	protected Community addCommunity() throws Exception {
		long pk = nextLong();

		Community community = _persistence.create(pk);

		community.setType(nextInt());

		_persistence.update(community, false);

		return community;
	}

	private static final String _TX_IMPL = CommunityPersistence.class.getName() +
		".transaction";
	private CommunityPersistence _persistence;
}