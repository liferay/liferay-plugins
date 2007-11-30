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

import java.beans.PropertyEditorSupport;

/**
 * @author Michael C. Han
 * @version $Revision$
 */
public class IssueTypePropertyEditor extends PropertyEditorSupport {
    /**
	 * Convert the given text value
	 */
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(text);
	}

    public Object getValue() {
        return _type;
    }

    /**
	 * Convert the given value to a Collection of the target _type.
	 */
	public void setValue(Object value) {
        if (value == null) {
            return;
        }
        if (value instanceof IssueType) {
            _type = (IssueType)value;
        }
        else if (value instanceof String) {
            _type = IssueType.convert((String)value);
        }
        else {
            throw new IllegalArgumentException("Invalid issue type property editor text:" +
                value);
        }
	}

	/**
	 * This implementation returns <code>null</code> to indicate that
	 * there is no appropriate text representation.
	 */
	public String getAsText() {
        if (_type == null) {
            return null;
        }
		return _type.getText();
	}
    private IssueType _type;
}
