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

package com.liferay.chat.model;

import com.liferay.portal.SystemException;
import com.liferay.portal.model.BaseModel;

/**
 * <a href="StatusModel.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface StatusModel extends BaseModel<Status> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getStatusId();

	public void setStatusId(long statusId);

	public long getUserId();

	public void setUserId(long userId);

	public String getUserUuid() throws SystemException;

	public void setUserUuid(String userUuid);

	public long getModifiedDate();

	public void setModifiedDate(long modifiedDate);

	public boolean getOnline();

	public boolean isOnline();

	public void setOnline(boolean online);

	public boolean getAwake();

	public boolean isAwake();

	public void setAwake(boolean awake);

	public String getActivePanelId();

	public void setActivePanelId(String activePanelId);

	public String getMessage();

	public void setMessage(String message);

	public boolean getPlaySound();

	public boolean isPlaySound();

	public void setPlaySound(boolean playSound);

	public Status toEscapedModel();
}