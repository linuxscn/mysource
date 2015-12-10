package com.atlassian.jira.issue.index.indexers.impl;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.search.constants.SystemSearchConstants;
import com.atlassian.jira.web.FieldVisibilityManager;
import org.apache.lucene.document.Document;

public class ResolutionDateIndexer extends BaseFieldIndexer
{
    public ResolutionDateIndexer(final FieldVisibilityManager fieldVisibilityManager)
    {
        super(fieldVisibilityManager);
    }

    public String getId()
    {
        return SystemSearchConstants.forResolutionDate().getFieldId();
    }

    public String getDocumentFieldId()
    {
        return SystemSearchConstants.forResolutionDate().getIndexField();
    }

    public void addIndex(Document doc, Issue issue)
    {
        indexDateField(doc, getDocumentFieldId(), issue.getResolutionDate(), issue);
    }
}