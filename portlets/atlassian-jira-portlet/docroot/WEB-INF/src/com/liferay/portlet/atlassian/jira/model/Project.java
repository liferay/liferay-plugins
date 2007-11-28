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

public class Project implements Serializable {

    public static Project convert(String value) {
        int index = value.indexOf(",");
        if (index == -1) {
            throw new IllegalArgumentException("Invalid project text: " +
                value);
        }
        String key = value.substring(0, index);
        int index2 = value.indexOf(",", index + 1);
        if (index2 == -1) {
            throw new IllegalArgumentException("Invalid project text: " +
                value);
        }
        String name = value.substring(index + 1, index2);
        String id = value.substring(index2 + 1, value.length());

        return new Project(key, name, id);
    }


    public Project(String key, String name, String id) {
        _key = key;
        _name = name;
        _id = id;
    }

    public String getText() {
        return _key + "," + _name + "," + _id;
    }

    public String getKey() {
        return _key;
    }

    public void setKey(String key) {
        _key = key;
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

        Project project = (Project) o;

        if (_id != null ? !_id.equals(project._id) : project._id != null) {
            return false;
        }
        if (_key != null ? !_key.equals(project._key) : project._key != null) {
            return false;
        }
        if (_name != null ? !_name.equals(
            project._name) : project._name != null) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        int result;
        result = (_key != null ? _key.hashCode() : 0);
        result = 31 * result + (_name != null ? _name.hashCode() : 0);
        result = 31 * result + (_id != null ? _id.hashCode() : 0);
        return result;
    }

    private String _key;
    private String _name;
    private String _id;
}
