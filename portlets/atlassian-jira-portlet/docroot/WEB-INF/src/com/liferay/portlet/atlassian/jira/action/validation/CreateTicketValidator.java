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

package com.liferay.portlet.atlassian.jira.action.validation;

import com.liferay.portlet.atlassian.jira.model.Issue;
import com.liferay.portlet.atlassian.jira.model.Component;
import com.liferay.portlet.atlassian.jira.model.Version;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Collection;

public class CreateTicketValidator implements Validator {
    private static final String _NEGATIVE_ONE = "-1";

    public boolean supports(Class clazz) {
        return clazz.isAssignableFrom(Issue.class);
    }

    public void validate(Object obj, Errors errors) {
        Issue issue = (Issue) obj;
        validateSummary(issue, errors);
    }

    public void validateSummary(Issue issue, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "summary", "SUMMARY_REQUIRED",
                                      "Summary is required.");

        Collection components = issue.getComponents();
        if ((components != null) && (components.size() == 1)) {
			Component component = (Component) components.iterator().next();
			if (component.getId().equals(_NEGATIVE_ONE)) {
				issue.setComponents(null);
			}
        }
        Collection versions = issue.getVersions();
        if ((versions != null) && (versions.size() == 1)) {
			Version version = (Version) versions.iterator().next();
			if (version.getVersionId().equals(_NEGATIVE_ONE)) {
				issue.setVersions(null);
			}
        }
        Collection fixVersions = issue.getFixedVersions();
        if ((fixVersions != null) && (fixVersions.size() == 1)) {
			Version version = (Version) fixVersions.iterator().next();

			if (version != null && version.getVersionId().equals(_NEGATIVE_ONE)) {
				issue.setFixedVersions(null);
			}
        }


    }

}
