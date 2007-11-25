package com.liferay.portlet.atlassian.jira.model;

import org.springframework.beans.propertyeditors.CustomCollectionEditor;

/**
 * @author Michael C. Han
 * @version $Revision$
 */
public class VersionsPropertyEditor extends CustomCollectionEditor {
    public VersionsPropertyEditor(final Class collectionType) {
        super(collectionType);
    }

    public VersionsPropertyEditor(final Class collectionType,
                                  final boolean nullAsEmptyCollection) {
        super(collectionType, nullAsEmptyCollection);
    }

    protected Object convertElement(final Object element) {
        if (element == null) {
            return null;
        }
        else if (element instanceof Version) {
            return element;
        }
        if (element instanceof String) {
            return Version.convert((String) element);
        }
        throw new IllegalArgumentException("Invalid versions element type: " +
            element);
    }
}
