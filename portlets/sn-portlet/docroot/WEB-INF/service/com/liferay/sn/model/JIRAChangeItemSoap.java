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

package com.liferay.sn.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="JIRAChangeItemSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAChangeItemSoap implements Serializable {
	public static JIRAChangeItemSoap toSoapModel(JIRAChangeItem model) {
		JIRAChangeItemSoap soapModel = new JIRAChangeItemSoap();

		soapModel.setJiraChangeItemId(model.getJiraChangeItemId());
		soapModel.setJiraChangeGroupId(model.getJiraChangeGroupId());
		soapModel.setField(model.getField());
		soapModel.setOldValue(model.getOldValue());
		soapModel.setOldString(model.getOldString());
		soapModel.setNewValue(model.getNewValue());
		soapModel.setNewString(model.getNewString());

		return soapModel;
	}

	public static JIRAChangeItemSoap[] toSoapModels(List<JIRAChangeItem> models) {
		List<JIRAChangeItemSoap> soapModels = new ArrayList<JIRAChangeItemSoap>(models.size());

		for (JIRAChangeItem model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new JIRAChangeItemSoap[soapModels.size()]);
	}

	public JIRAChangeItemSoap() {
	}

	public long getPrimaryKey() {
		return _jiraChangeItemId;
	}

	public void setPrimaryKey(long pk) {
		setJiraChangeItemId(pk);
	}

	public long getJiraChangeItemId() {
		return _jiraChangeItemId;
	}

	public void setJiraChangeItemId(long jiraChangeItemId) {
		_jiraChangeItemId = jiraChangeItemId;
	}

	public long getJiraChangeGroupId() {
		return _jiraChangeGroupId;
	}

	public void setJiraChangeGroupId(long jiraChangeGroupId) {
		_jiraChangeGroupId = jiraChangeGroupId;
	}

	public String getField() {
		return _field;
	}

	public void setField(String field) {
		_field = field;
	}

	public String getOldValue() {
		return _oldValue;
	}

	public void setOldValue(String oldValue) {
		_oldValue = oldValue;
	}

	public String getOldString() {
		return _oldString;
	}

	public void setOldString(String oldString) {
		_oldString = oldString;
	}

	public String getNewValue() {
		return _newValue;
	}

	public void setNewValue(String newValue) {
		_newValue = newValue;
	}

	public String getNewString() {
		return _newString;
	}

	public void setNewString(String newString) {
		_newString = newString;
	}

	private long _jiraChangeItemId;
	private long _jiraChangeGroupId;
	private String _field;
	private String _oldValue;
	private String _oldString;
	private String _newValue;
	private String _newString;
}