package com.wingyplus.alfresco.repo.schedule;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.quartz.Trigger;

public class ContentExpireScheduledActionTest
{
    // private ApplicationContext applicationContext;
    // private NodeService nodeService;
    private ContentExpireScheduledAction contentExpireScheduledAction;

    @Before
    public void setUp()
    {
        contentExpireScheduledAction = new ContentExpireScheduledAction();
    }

    @After
    public void tearDown()
    {
        contentExpireScheduledAction = null;
    }

    @Test
    public void testGetTrigger()
    {
        contentExpireScheduledAction.setTriggerName("myTriggerName");
        contentExpireScheduledAction.setTriggerGroup("myTriggerGroup");
        contentExpireScheduledAction.setCronExpression("0 0 12 * * ?");

        Trigger trigger = contentExpireScheduledAction.getTrigger();

        DateTime currentDateTime = new DateTime();
        Date nextFireDate = trigger.getFireTimeAfter(currentDateTime.toDate());
        Date expectedDate = new DateTime(currentDateTime.getYear(), currentDateTime.getMonthOfYear(), currentDateTime.getDayOfMonth(), 12, 0, 0, 0).toDate();

        assertThat(trigger.getName(), equalTo("myTriggerName"));
        assertThat(trigger.getGroup(), equalTo("myTriggerGroup"));
        assertThat(nextFireDate, equalTo(expectedDate));
    }
}
