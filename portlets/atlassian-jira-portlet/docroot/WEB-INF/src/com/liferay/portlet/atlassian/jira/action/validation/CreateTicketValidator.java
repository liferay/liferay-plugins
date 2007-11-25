package com.liferay.portlet.atlassian.jira.action.validation;

import com.liferay.portlet.atlassian.jira.model.Issue;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CreateTicketValidator implements Validator {
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
    }

}
