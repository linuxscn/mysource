package com.atlassian.jira.event.config;

import com.atlassian.jira.issue.issuetype.IssueType;

/**
 * Event indicating an issue type has been updated
 *
 * @note As at 2012-01-27, it is not possible to change whether an issue type is a sub-task or not.
 *
 * @since v5.1
 */
public class IssueTypeUpdatedEvent extends AbstractIssueTypeEvent
{
    public IssueTypeUpdatedEvent(IssueType issueType, String issueStyleType)
    {
        super(issueType, issueStyleType);
    }
}
