package com.liferay.portlet.atlassian.jira.model;

import java.beans.PropertyEditorSupport;

/**
 * @author Michael C. Han
 * @version $Revision$
 */
public class ProjectPropertyEditor extends PropertyEditorSupport {
    /**
	 * Convert the given text value
	 */
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(text);
	}

    public Object getValue() {
        return _project;
    }

    /**
	 * Convert the given value to a Collection of the target type.
	 */
	public void setValue(Object value) {
        if (value == null) {
            return;
        }
        if (value instanceof Project) {
            _project = (Project)value;
        }
        else if (value instanceof String) {
            _project = Project.convert((String)value);
        }
        else {
            throw new IllegalArgumentException("Invalid project text:" +
                value);
        }
	}

	/**
	 * This implementation returns <code>null</code> to indicate that
	 * there is no appropriate text representation.
	 */
	public String getAsText() {
        if (_project == null) {
            return null;
        }
		return _project.getText();
	}

    private Project _project;
}

