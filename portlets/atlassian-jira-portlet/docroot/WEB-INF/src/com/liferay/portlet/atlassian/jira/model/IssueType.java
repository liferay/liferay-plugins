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