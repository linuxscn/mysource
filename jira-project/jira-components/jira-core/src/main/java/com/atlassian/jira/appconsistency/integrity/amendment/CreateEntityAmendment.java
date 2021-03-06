/*
 * Copyright (c) 2002-2004
 * All rights reserved.
 */

package com.atlassian.jira.appconsistency.integrity.amendment;

import com.atlassian.jira.appconsistency.integrity.check.AbstractAmendment;
import org.ofbiz.core.entity.GenericValue;

public class CreateEntityAmendment extends AbstractAmendment
{
    private GenericValue entity;

    public CreateEntityAmendment(int type, String message, GenericValue entity)
    {
        super(type, "", message);
        this.entity = entity;
    }

    public String getMessage()
    {
        return super.getMessage() + ": " + entity.getEntityName() + " (ID:" + entity.get("id").toString() + ")";
    }

    public GenericValue getEntity()
    {
        return entity;
    }
}
