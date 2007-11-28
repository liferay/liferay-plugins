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