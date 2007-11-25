package com.liferay.portlet.atlassian.jira.model;

import java.io.Serializable;

/**
 * @author Mahong Wei
 * @version $Revision$
 */
public class Component implements Serializable {

    public static Component convert(String value) {
        int index = value.indexOf(",");
        if (index == -1) {
            throw new IllegalArgumentException("Invalid issue type text: " +
                value);
        }
        return new Component(value.substring(0, index),
                             value.substring(index + 1, value.length()));
    }
    
    public Component(String name, String id) {
        _name = name;
        _id = id;
    }

    public String getText() {
        return _name + "," + _id;
    }


    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = id;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Component component = (Component) o;

        if (_id != null ? !_id.equals(component._id) : component._id != null) {
            return false;
        }
        if (_name != null ? !_name.equals(
            component._name) : component._name != null) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        int result;
        result = (_name != null ? _name.hashCode() : 0);
        result = 31 * result + (_id != null ? _id.hashCode() : 0);
        return result;
    }

    private String _name;
    private String _id;
}
