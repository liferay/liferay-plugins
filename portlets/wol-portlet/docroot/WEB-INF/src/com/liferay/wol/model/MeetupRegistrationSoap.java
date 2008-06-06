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

package com.liferay.wol.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="MeetupRegistrationSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MeetupRegistrationSoap implements Serializable {
	public static MeetupRegistrationSoap toSoapModel(MeetupRegistration model) {
		MeetupRegistrationSoap soapModel = new MeetupRegistrationSoap();

		soapModel.setMeetupRegistrationId(model.getMeetupRegistrationId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setMeetupEntryId(model.getMeetupEntryId());
		soapModel.setStatus(model.getStatus());
		soapModel.setComments(model.getComments());

		return soapModel;
	}

	public static MeetupRegistrationSoap[] toSoapModels(
		List<MeetupRegistration> models) {
		List<MeetupRegistrationSoap> soapModels = new ArrayList<MeetupRegistrationSoap>(models.size());

		for (MeetupRegistration model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MeetupRegistrationSoap[soapModels.size()]);
	}

	public MeetupRegistrationSoap() {
	}

	public long getPrimaryKey() {
		return _meetupRegistrationId;
	}

	public void setPrimaryKey(long pk) {
		setMeetupRegistrationId(pk);
	}

	public long getMeetupRegistrationId() {
		return _meetupRegistrationId;
	}

	public void setMeetupRegistrationId(long meetupRegistrationId) {
		_meetupRegistrationId = meetupRegistrationId;
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

	public long getMeetupEntryId() {
		return _meetupEntryId;
	}

	public void setMeetupEntryId(long meetupEntryId) {
		_meetupEntryId = meetupEntryId;
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

	private long _meetupRegistrationId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _meetupEntryId;
	private int _status;
	private String _comments;
}