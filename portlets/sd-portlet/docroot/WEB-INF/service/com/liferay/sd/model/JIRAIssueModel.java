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

package com.liferay.sd.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;

/**
 * <a href="JIRAIssueModel.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface JIRAIssueModel extends BaseModel<JIRAIssue> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getJiraIssueId();

	public void setJiraIssueId(long jiraIssueId);

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public Date getModifiedDate();

	public void setModifiedDate(Date modifiedDate);

	public long getProjectId();

	public void setProjectId(long projectId);

	public String getKey();

	public void setKey(String key);

	public String getSummary();

	public void setSummary(String summary);

	public String getDescription();

	public void setDescription(String description);

	public String getReporterJiraUserId();

	public void setReporterJiraUserId(String reporterJiraUserId);

	public String getAssigneeJiraUserId();

	public void setAssigneeJiraUserId(String assigneeJiraUserId);

	public String getResolution();

	public void setResolution(String resolution);

	public String getStatus();

	public void setStatus(String status);

	public JIRAIssue toEscapedModel();
}