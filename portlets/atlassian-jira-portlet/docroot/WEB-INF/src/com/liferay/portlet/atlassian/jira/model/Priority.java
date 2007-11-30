/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.atlassian.jira.model;

import java.io.Serializable;

/**
 * @author Mahong Wei
 * @version $Revision$
 */
public class Priority implements Serializable {
    public static Priority convert(final String value) {
        int index = value.indexOf(",");
        if (index == -1) {
            throw new IllegalArgumentException("Invalid priority text: " +
                value);
        }
        return new Priority(value.substring(0, index),
                            value.substring(index + 1, value.length()));
    }

    public Priority(String priorityName, String priorityId) {
        _priorityName = priorityName;
        _priorityId = priorityId;
    }

    public String getPriorityName() {
        return _priorityName;
    }

    public String getPriorityId() {
        return _priorityId;
    }

    public void setPriorityId(final String priorityId) {
        _priorityId = priorityId;
    }

    public void setPriorityName(String priorityName) {
        _priorityName = priorityName;
    }

    public String getText() {
        return _priorityName + "," + _priorityId;
    }

    /*
      * (non-Javadoc)
      *
      * @see java.lang.Object#equals(java.lang.Object)
      */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Priority other = (Priority) obj;
        if (_priorityId == null) {
            if (other._priorityId != null) {
                return false;
            }
        }
        else if (!_priorityId.equals(other._priorityId)) {
            return false;
        }
        if (_priorityName == null) {
            if (other._priorityName != null) {
                return false;
            }
        }
        else if (!_priorityName.equals(other._priorityName)) {
            return false;
        }
        return true;
    }

    /*
      * (non-Javadoc)
      *
      * @see java.lang.Object#hashCode()
      */
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
            + ((_priorityId == null) ? 0 : _priorityId.hashCode());
        result = prime * result
            + ((_priorityName == null) ? 0 : _priorityName.hashCode());
        return result;
    }

    private String _priorityName;
    private String _priorityId;

}