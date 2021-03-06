package com.atlassian.jira.issue.customfields.statistics;

import com.atlassian.annotations.Internal;
import com.atlassian.jira.issue.customfields.option.Option;

import java.util.Comparator;

/**
 * Compares two Select options by their values.
 *
 * @since v6.1
 */

@Internal
public class OptionValueComparator implements Comparator<Option>
{
    public int compare(Option o1, Option o2)
    {
        if (o1 == null && o2 == null)
        {
            return 0;
        }
        else if (o1 == null)
        {
            return 1;
        }
        else if (o2 == null)
        {
            return -1;
        }

        String value1 = o1.getValue();
        String value2 = o2.getValue();
        return value1.compareTo(value2);
    }
}
