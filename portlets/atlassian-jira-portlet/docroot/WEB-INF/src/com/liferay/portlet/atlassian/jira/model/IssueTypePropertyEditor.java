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
		return _type.getText();
	}
    private IssueType _type;
}
