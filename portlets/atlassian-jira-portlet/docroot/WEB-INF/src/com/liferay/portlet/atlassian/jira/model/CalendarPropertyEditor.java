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

import com.liferay.portlet.atlassian.jira.util.StringUtil;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Michael C. Han
 * @version $Revision$
 */
public class CalendarPropertyEditor extends PropertyEditorSupport {

    public CalendarPropertyEditor(String format) {
        _formatText = format;
        _format = new SimpleDateFormat(_formatText);
    }
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
        if (value instanceof Calendar) {
            _type = (Calendar) value;
        }
        else if (value instanceof String) {
            final String text = (String) value;
            if (StringUtil.isEmpty(text)) {
                return;
            }
            try {
                Date date = _format.parse(text);
                _type = Calendar.getInstance();
                _type.setTime(date);
            }
            catch (ParseException e) {
                throw new IllegalArgumentException("Text does not match form:" +
                    _formatText);
            }
        }
        else {
            throw new IllegalArgumentException("Invalid issue type text:" +
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
        return _format.format(_type.getTime());
    }

    private Calendar _type;
    private DateFormat _format;
    private String _formatText;
}
