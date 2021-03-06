package com.atlassian.jira.permission;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.bc.project.ProjectAction;
import com.atlassian.jira.issue.comparator.ProjectNameComparator;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.scheme.Scheme;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.security.PermissionManager;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

/**
 * @since v4.4
 */
public class DefaultProjectPermissionSchemeHelper implements ProjectPermissionSchemeHelper
{
    private final PermissionSchemeManager permissionSchemeManager;
    private final JiraAuthenticationContext authenticationContext;
    private final PermissionManager permissionManager;

    public DefaultProjectPermissionSchemeHelper(final PermissionSchemeManager permissionSchemeManager,
            final JiraAuthenticationContext authenticationContext, final PermissionManager permissionManager)
    {
        this.permissionSchemeManager = permissionSchemeManager;
        this.authenticationContext = authenticationContext;
        this.permissionManager = permissionManager;
    }

    public List<Project> getSharedProjects(final Scheme permissionScheme)
    {
        final Set<Project> sharedProjects = Sets.newTreeSet(ProjectNameComparator.COMPARATOR);
        final List<Project> projects = permissionSchemeManager.getProjects(permissionScheme);
        for (final Project project : projects)
        {
            if(hasEditPermission(authenticationContext.getLoggedInUser(), project))
            {
                sharedProjects.add(project);
            }
        }

        return Lists.newArrayList(sharedProjects);
    }

    boolean hasEditPermission(final User user, final Project project)
    {
        return ProjectAction.EDIT_PROJECT_CONFIG.hasPermission(permissionManager, user, project);
    }
}
