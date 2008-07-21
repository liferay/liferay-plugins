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

package com.liferay.iweb.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="PostSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PostSoap implements Serializable {
	public static PostSoap toSoapModel(Post model) {
		PostSoap soapModel = new PostSoap();

		soapModel.setUid(model.getUid());
		soapModel.setPid(model.getPid());
		soapModel.setType(model.getType());
		soapModel.setCommunityid(model.getCommunityid());

		return soapModel;
	}

	public static PostSoap[] toSoapModels(List<Post> models) {
		List<PostSoap> soapModels = new ArrayList<PostSoap>(models.size());

		for (Post model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PostSoap[soapModels.size()]);
	}

	public PostSoap() {
	}

	public long getPrimaryKey() {
		return _uid;
	}

	public void setPrimaryKey(long pk) {
		setUid(pk);
	}

	public long getUid() {
		return _uid;
	}

	public void setUid(long uid) {
		_uid = uid;
	}

	public long getPid() {
		return _pid;
	}

	public void setPid(long pid) {
		_pid = pid;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public long getCommunityid() {
		return _communityid;
	}

	public void setCommunityid(long communityid) {
		_communityid = communityid;
	}

	private long _uid;
	private long _pid;
	private String _type;
	private long _communityid;
}