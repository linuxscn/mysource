package com.atlassian.jira.plugin.jql.function;

import com.atlassian.core.util.Clock;
import com.atlassian.jira.jql.operand.QueryLiteral;
import com.atlassian.jira.jql.query.QueryCreationContext;
import com.atlassian.jira.timezone.TimeZoneManager;
import com.atlassian.query.clause.TerminalClause;
import com.atlassian.query.operand.FunctionOperand;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Function that produces the end of the month as the value.
 *
 * @since v4.3
 */
public class StartOfMonthFunction extends AbstractDateFunction
{
    public static final String FUNCTION_START_OF_MONTH = "startOfMonth";
    
    StartOfMonthFunction(final Clock clock, final TimeZoneManager timeZoneManager)
    {
        super(clock, timeZoneManager);
    }

    public StartOfMonthFunction(final TimeZoneManager timeZoneManager)
    {
        super(timeZoneManager);
    }
    
    public List<QueryLiteral> getValues(final QueryCreationContext queryCreationContext, final FunctionOperand operand, final TerminalClause terminalClause)
    {
        boolean hasAdjustment = operand.getArgs().size() == 1;
        int unit = 0;
        int incrementValue = 0;
        if (hasAdjustment)
        {
            final String duration = operand.getArgs().get(0);
            unit = getDurationUnit(duration);
            if (unit == -1)
            {
            	unit = Calendar.MONTH;
            }
            incrementValue = getDurationAmount(duration);
        }

        Calendar cal = new GregorianCalendar();
        cal.setTimeZone(timeZoneManager.getLoggedInUserTimeZone());
        cal.setTime(clock.getCurrentDate());
        //Force the calendar to re-calculate date fields.
        cal.getTime();

        // For year and month increments we want the actual real start of the previous/next month
        if (hasAdjustment)
        {
            if (unit == Calendar.YEAR || unit == Calendar.MONTH)
            {
            	cal.add(unit, incrementValue);
            }
        }
    	
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));

        // For increments < month we want the to adjust from the start of the month
        if (hasAdjustment)
        {
            if (!(unit == Calendar.YEAR || unit == Calendar.MONTH))
            {
            	cal.add(unit, incrementValue);
            }
        }
        
        return Collections.singletonList(new QueryLiteral(operand, cal.getTimeInMillis()));
    }
}
