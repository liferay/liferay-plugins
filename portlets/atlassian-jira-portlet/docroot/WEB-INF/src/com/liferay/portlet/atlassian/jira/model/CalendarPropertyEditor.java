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
