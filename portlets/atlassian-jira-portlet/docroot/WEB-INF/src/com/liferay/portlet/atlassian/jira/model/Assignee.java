package com.liferay.portlet.atlassian.jira.model;

import java.io.Serializable;

/**
 * @author Mahong Wei
 * @version $Revision$
 */
public class Assignee implements Serializable {
    public Assignee(String name, String email, String fullName) {
        _name = name;
        _email = email;
        _fullName = fullName;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
    }

    public String getFullName() {
        return _fullName;
    }

    public void setFullName(String fullName) {
        _fullName = fullName;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Assignee assignee = (Assignee) o;

        if (_email != null ? !_email.equals(assignee._email)
            : assignee._email != null) {
            return false;
        }
        if (_fullName != null ? !_fullName.equals(assignee._fullName)
            : assignee._fullName != null) {
            return false;
        }
        if (_name != null ? !_name.equals(assignee._name)
            : assignee._name != null) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        int result;
        result = (_name != null ? _name.hashCode() : 0);
        result = 31 * result + (_email != null ? _email.hashCode() : 0);
        result = 31 * result + (_fullName != null ? _fullName.hashCode() : 0);
        return result;
    }

    public String toString() {
        return "Assignee{" + "_name='" + _name + '\'' + ", _email='" + _email
            + '\'' + ", _fullName='" + _fullName + '\'' + '}';
    }

    private String _name;
    private String _email;
    private String _fullName;

}