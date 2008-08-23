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

package com.liferay.kb.knowledgebase.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;

/**
 * <a href="KBArticleModel.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public interface KBArticleModel extends BaseModel {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public String getUuid();

	public void setUuid(String uuid);

	public long getArticleId();

	public void setArticleId(long articleId);

	public long getGroupId();

	public void setGroupId(long groupId);

	public long getResourcePrimKey();

	public void setResourcePrimKey(long resourcePrimKey);

	public long getCompanyId();

	public void setCompanyId(long companyId);

	public long getUserId();

	public void setUserId(long userId);

	public String getUserName();

	public void setUserName(String userName);

	public Date getModifiedDate();

	public void setModifiedDate(Date modifiedDate);

	public String getTitle();

	public void setTitle(String title);

	public double getVersion();

	public void setVersion(double version);

	public boolean getMinorEdit();

	public boolean isMinorEdit();

	public void setMinorEdit(boolean minorEdit);

	public String getContent();

	public void setContent(String content);

	public String getDescription();

	public void setDescription(String description);

	public boolean getHead();

	public boolean isHead();

	public void setHead(boolean head);

	public boolean getTemplate();

	public boolean isTemplate();

	public void setTemplate(boolean template);

	public boolean getDraft();

	public boolean isDraft();

	public void setDraft(boolean draft);

	public KBArticle toEscapedModel();
}