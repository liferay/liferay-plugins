package com.liferay.portlet.atlassian.jira.util;

public class StringUtil {

    public static final String EMPTY_STRING = "";

    public static boolean isEmpty(String value) {
        return (value == null) || value.equals(EMPTY_STRING);
    }

}
