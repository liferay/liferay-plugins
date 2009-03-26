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

package com.liferay.wol.model;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * <a href="MeetupsRegistrationClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MeetupsRegistrationClp extends BaseModelImpl<MeetupsRegistration>
	implements MeetupsRegistration {
	public MeetupsRegistrationClp() {
	}

	public long getPrimaryKey() {
		return _meetupsRegistrationId;
	}

	public void setPrimaryKey(long pk) {
		setMeetupsRegistrationId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_meetupsRegistrationId);
	}

	public long getMeetupsRegistrationId() {
		return _meetupsRegistrationId;
	}

	public void setMeetupsRegistrationId(long meetupsRegistrationId) {
		_meetupsRegistrationId = meetupsRegistrationId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getMeetupsEntryId() {
		return _meetupsEntryId;
	}

	public void setMeetupsEntryId(long meetupsEntryId) {
		_meetupsEntryId = meetupsEntryId;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public String getComments() {
		return _comments;
	}

	public void setComments(String comments) {
		_comments = comments;
	}

	public MeetupsRegistration toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			MeetupsRegistration model = new MeetupsRegistrationClp();

			model.setEscapedModel(true);

			model.setMeetupsRegistrationId(getMeetupsRegistrationId());
			model.setCompanyId(getCompanyId());
			model.setUserId(getUserId());
			model.setUserName(HtmlUtil.escape(getUserName()));
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setMeetupsEntryId(getMeetupsEntryId());
			model.setStatus(getStatus());
			model.setComments(HtmlUtil.escape(getComments()));

			model = (MeetupsRegistration)Proxy.newProxyInstance(MeetupsRegistration.class.getClassLoader(),
					new Class[] { MeetupsRegistration.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		MeetupsRegistrationClp clone = new MeetupsRegistrationClp();

		clone.setMeetupsRegistrationId(getMeetupsRegistrationId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setMeetupsEntryId(getMeetupsEntryId());
		clone.setStatus(getStatus());
		clone.setComments(getComments());

		return clone;
	}

	public int compareTo(MeetupsRegistration meetupsRegistration) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(),
				meetupsRegistration.getModifiedDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		MeetupsRegistrationClp meetupsRegistration = null;

		try {
			meetupsRegistration = (MeetupsRegistrationClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = meetupsRegistration.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	private long _meetupsRegistrationId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _meetupsEntryId;
	private int _status;
	private String _comments;
}