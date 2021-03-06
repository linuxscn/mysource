package com.atlassian.jira.webtests.ztests.timetracking.modern;

import com.atlassian.jira.functest.framework.suite.Category;
import com.atlassian.jira.functest.framework.suite.WebTest;

/**
 * Uses the tests defined in {@link AbstractTestCreateWorklogAsField} to test the "log work" system field on the Create
 * Issue screen.
 *
 * @since v4.2
 */
@WebTest ({ Category.FUNC_TEST, Category.TIME_TRACKING, Category.WORKLOGS })
public class TestCreateWorklogOnCreateIssue extends AbstractTestCreateWorklogAsField
{
    private LogWorkRunner logWorkRunner;

    protected LogWorkRunner getLogWorkRunner()
    {
        if (logWorkRunner == null)
        {
            logWorkRunner = new CreateIssueLogWorkRunner();
        }
        return logWorkRunner;
    }

    class CreateIssueLogWorkRunner implements LogWorkRunner
    {
        public void gotoLogWorkScreen1()
        {
            navigation.issue().goToCreateIssueForm("homosapien", "Bug");
            tester.setFormElement("summary", "My Test Issue");
        }

        public boolean isCommentFieldShown()
        {
            return false;
        }

        public void gotoLogWorkScreen2()
        {
            throw new UnsupportedOperationException("Cannot log work twice whilst creating an issue");
        }

        public void gotoLogWorkScreenWithOriginalEstimate(final String originalEstimate)
        {
            // preset the original estimate
            gotoLogWorkScreen1();
            tester.setFormElement("timetracking", originalEstimate);
        }

        public void gotoLogWorkResult()
        {
            // assuming that the issue which gets created is HSP-2
            navigation.issue().viewIssue(HSP_2);
        }

        public boolean isLoggingWorkTwiceSupported()
        {
            return false;
        }

        public void addCommentForWork(final String comment)
        {
            tester.setFormElement("comment", comment);            
        }
    }


    @Override
    public void testWorklogNoPermToCreate()
    {
        super.testWorklogNoPermToCreate();
    }

    @Override
    public void testWorklogTimeTrackingDisabled()
    {
        super.testWorklogTimeTrackingDisabled();
    }

    @Override
    public void testLogWorkLeaveEstimateNoteCorrect()
    {
        super.testLogWorkLeaveEstimateNoteCorrect();
    }

    @Override
    public void testMandatoryFields()
    {
        super.testMandatoryFields();
    }

    @Override
    public void testInvalidFormattedDurationFields()
    {
        super.testInvalidFormattedDurationFields();
    }

    @Override
    public void testBadFractionDuration()
    {
        super.testBadFractionDuration();
    }

    @Override
    public void testGoodFractionDuration() throws Exception
    {
        super.testGoodFractionDuration();
    }

    @Override
    public void testInvalidTimeSpentZero()
    {
        super.testInvalidTimeSpentZero();
    }

    @Override
    public void testInvalidStartDateField()
    {
        super.testInvalidStartDateField();
    }

    @Override
    public void testAutoAdjustEstimate()
    {
        super.testAutoAdjustEstimate();
    }

    @Override
    public void testNewEstimate()
    {
        super.testNewEstimate();
    }

    @Override
    public void testLeaveExistingEstimate()
    {
        super.testLeaveExistingEstimate();
    }

    @Override
    public void testManuallyAdjustEstimate()
    {
        super.testManuallyAdjustEstimate();
    }

    @Override
    public void testLogWorkVisibleToAll()
    {
        super.testLogWorkVisibleToAll();
    }

    @Override
    public void testLogWorkVisibleToAllDaysTimeFormat()
    {
        super.testLogWorkVisibleToAllDaysTimeFormat();
    }

    @Override
    public void testLogWorkVisibleToAllHoursTimeFormat()
    {
        super.testLogWorkVisibleToAllHoursTimeFormat();
    }

    @Override
    public void testLogWorkVisibleToRole()
    {
        super.testLogWorkVisibleToRole();
    }

    @Override
    public void testLogWorkVisibleToRoleDaysTimeFormat()
    {
        super.testLogWorkVisibleToRoleDaysTimeFormat();
    }

    @Override
    public void testLogWorkVisibleToRoleHoursTimeFormat()
    {
        super.testLogWorkVisibleToRoleHoursTimeFormat();
    }

    @Override
    public void testLogWorkVisibleToGroup()
    {
        super.testLogWorkVisibleToGroup();
    }

    @Override
    public void testLogWorkVisibleToGroupDaysTimeFormat()
    {
        super.testLogWorkVisibleToGroupDaysTimeFormat();
    }

    @Override
    public void testLogWorkVisibleToGroupHoursTimeFormat()
    {
        super.testLogWorkVisibleToGroupHoursTimeFormat();
    }

    @Override
    public void testLogWorkCommentsNotCopiedWhenCopyingDisabled()
    {
        super.testLogWorkCommentsNotCopiedWhenCopyingDisabled();
    }

    @Override
    public void testLogWorkCommentsCopiedWhenCopyingEnabled()
    {
        super.testLogWorkCommentsCopiedWhenCopyingEnabled();
    }

    @Override
    public void testLogWorkDateIsStartDate()
    {
        super.testLogWorkDateIsStartDate();
    }

    @Override
    public void testChangeHistory() throws Exception
    {
        super.testChangeHistory();
    }
}
