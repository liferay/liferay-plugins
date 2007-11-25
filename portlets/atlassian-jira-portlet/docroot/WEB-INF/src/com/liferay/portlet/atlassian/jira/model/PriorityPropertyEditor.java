package com.liferay.portlet.atlassian.jira.model;

import java.beans.PropertyEditorSupport;

/**
 * @author Michael C. Han
 * @version $Revision$
 */
public class PriorityPropertyEditor extends PropertyEditorSupport {
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
        if (value instanceof Priority) {
            _type = (Priority)value;
        }
        else if (value instanceof String) {
            _type = Priority.convert((String)value);
        }
        else {
            throw new IllegalArgumentException("Invalid priority text:" +
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
    private Priority _type;
}
