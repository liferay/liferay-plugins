package com.liferay.portlet.atlassian.jira.model;

import org.springframework.beans.propertyeditors.CustomCollectionEditor;

/**
 * @author Michael C. Han
 * @version $Revision$
 */
public class ComponentsPropertyEditor extends CustomCollectionEditor {
    public ComponentsPropertyEditor(final Class collectionType) {
        super(collectionType);
    }

    public ComponentsPropertyEditor(final Class collectionType,
                                    final boolean nullAsEmptyCollection) {
        super(collectionType, nullAsEmptyCollection);
    }

    protected Object convertElement(final Object element) {
        if (element == null) {
            return null;
        }
        else if (element instanceof Component) {
            return element;
        }
        if (element instanceof String) {
            return Component.convert((String)element);
        }
        throw new IllegalArgumentException("Invalid components element type: " +
            element);
    }
}
