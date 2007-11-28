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
public class IssueType implements Serializable {
    public static IssueType convert(String value) {
        int index = value.indexOf(",");
        if (index == -1) {
            throw new IllegalArgumentException("Invalid issue type text: " +
                value);
        }
        return new IssueType(value.substring(0, index),
                             value.substring(index + 1, value.length()));
    }


    public IssueType(String issueTypeName, String issueTypeID) {
        _issueTypeName = issueTypeName;
        _issueTypeID = issueTypeID;
    }

    public String getIssueTypeName() {
        return _issueTypeName;
    }

    public void setIssueTypeName(String issueTypeName) {
        _issueTypeName = issueTypeName;
    }

    public String getText() {
        return _issueTypeName + "," + _issueTypeID;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IssueType issueType = (IssueType) o;

        return !(_issueTypeName != null ? !_issueTypeName
            .equals(issueType._issueTypeName)
            : issueType._issueTypeName != null);

    }

    public int hashCode() {
        return (_issueTypeName != null ? _issueTypeName.hashCode() : 0);
    }

    public String getIssueTypeID() {
        return _issueTypeID;
    }

    public void setIssueTypeID(String issueTypeID) {
        _issueTypeID = issueTypeID;
    }


    private String _issueTypeName;
    private String _issueTypeID;

}