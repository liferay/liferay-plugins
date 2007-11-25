package com.liferay.portlet.atlassian.jira;

public class SystemException extends Exception {

    public SystemException(String mesg) {
        super(mesg);
    }

    public SystemException(String mesg, Throwable t) {
        super(mesg, t);
    }

}
