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

import com.liferay.iweb.NoSuchPostEntryException;
import com.liferay.iweb.model.PostEntry;
import com.liferay.portal.service.persistence.BasePersistenceTestCase;

/**
 * <a href="PostEntryPersistenceTest.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PostEntryPersistenceTest extends BasePersistenceTestCase {
	protected void setUp() throws Exception {
		super.setUp();

		_persistence = (PostEntryPersistence)com.liferay.portal.kernel.bean.PortalBeanLocatorUtil.locate(_TX_IMPL);
	}

	public void testCreate() throws Exception {
		long pk = nextLong();

		PostEntry post = _persistence.create(pk);

		assertNotNull(post);

		assertEquals(post.getPrimaryKey(), pk);
	}

	public void testRemove() throws Exception {
		PostEntry newPost = addPost();

		_persistence.remove(newPost);

		PostEntry existingPost = _persistence.fetchByPrimaryKey(
			newPost.getPrimaryKey());

		assertNull(existingPost);
	}

	public void testUpdateNew() throws Exception {
		addPost();
	}

	public void testUpdateExisting() throws Exception {
		long pk = nextLong();

		PostEntry newPost = _persistence.create(pk);

		newPost.setPid(nextLong());
		newPost.setType(randomString());
		newPost.setInterestGroupId(nextLong());

		_persistence.update(newPost, false);

		PostEntry existingPost = _persistence.findByPrimaryKey(newPost.getPrimaryKey());

		assertEquals(existingPost.getUid(), newPost.getUid());
		assertEquals(existingPost.getPid(), newPost.getPid());
		assertEquals(existingPost.getType(), newPost.getType());
		assertEquals(existingPost.getInterestGroupId(), newPost.getInterestGroupId());
	}

	public void testFindByPrimaryKeyExisting() throws Exception {
		PostEntry newPost = addPost();

		PostEntry existingPost = _persistence.findByPrimaryKey(
			newPost.getPrimaryKey());

		assertEquals(existingPost, newPost);
	}

	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			fail("Missing entity did not throw NoSuchPostException");
		}
		catch (NoSuchPostEntryException nsee) {
		}
	}

	public void testFetchByPrimaryKeyExisting() throws Exception {
		PostEntry newPost = addPost();

		PostEntry existingPost = _persistence.fetchByPrimaryKey(
			newPost.getPrimaryKey());

		assertEquals(existingPost, newPost);
	}

	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = nextLong();

		PostEntry missingPost = _persistence.fetchByPrimaryKey(pk);

		assertNull(missingPost);
	}

	protected PostEntry addPost() throws Exception {
		long pk = nextLong();

		PostEntry post = _persistence.create(pk);

		post.setPid(nextLong());
		post.setType(randomString());
		post.setInterestGroupId(nextLong());

		_persistence.update(post, false);

		return post;
	}

	private static final String _TX_IMPL = PostEntryPersistence.class.getName()+
		".transaction";
	private PostEntryPersistence _persistence;

}