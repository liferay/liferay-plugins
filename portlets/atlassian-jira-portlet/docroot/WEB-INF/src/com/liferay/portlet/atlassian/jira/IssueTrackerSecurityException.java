package com.liferay.portlet.atlassian.jira;

public class IssueTrackerSecurityException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public IssueTrackerSecurityException(String mesg) {
        super(mesg);
    }

    public IssueTrackerSecurityException(String mesg, Throwable t) {
        super(mesg, t);
    }

}
