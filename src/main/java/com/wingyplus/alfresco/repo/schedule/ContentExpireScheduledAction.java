package com.wingyplus.alfresco.repo.schedule;

import java.text.ParseException;
import java.util.List;

import org.alfresco.repo.action.scheduled.AbstractScheduledAction;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.repository.NodeRef;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class ContentExpireScheduledAction extends AbstractScheduledAction
{
    /**
     * cron expression scheduler
     */
    private String cronExpression;
    private String jobGroup;
    private String jobName;
    private String triggerGroup;
    private String triggerName;
    private Scheduler scheduler;
    private Action action;

    public ContentExpireScheduledAction()
    {
        super();
    }

    /**
     * @return the cronExpression
     */
    public String getCronExpression()
    {
        return cronExpression;
    }

    /**
     * @param cronExpression
     *            the cronExpression to set
     */
    public void setCronExpression(String cronExpression)
    {
        this.cronExpression = cronExpression;
    }

    /**
     * @return the scheduler
     */
    public Scheduler getScheduler()
    {
        try
        {
            this.scheduler = StdSchedulerFactory.getDefaultScheduler();
        } catch (SchedulerException se)
        {
            se.printStackTrace();
        }

        return scheduler;
    }

    /**
     * @return the action
     */
    public Action getAction()
    {
        return action;
    }

    /**
     * @param action
     *            the action to set
     */
    public void setAction(Action action)
    {
        this.action = action;
    }

    @Override
    public String getJobGroup()
    {
        return this.jobGroup;
    }

    @Override
    public String getJobName()
    {
        return this.jobName;
    }

    @Override
    public String getTriggerGroup()
    {
        return this.triggerGroup;
    }

    @Override
    public String getTriggerName()
    {
        return this.triggerName;
    }

    @Override
    public void setJobGroup(String jobGroup)
    {
        this.jobGroup = jobGroup;
    }

    @Override
    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }

    @Override
    public void setTriggerGroup(String triggerGroup)
    {
        this.triggerGroup = triggerGroup;
    }

    @Override
    public void setTriggerName(String triggerName)
    {
        this.triggerName = triggerName;
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        register(this.getScheduler());
    }

    @Override
    public Action getAction(NodeRef nodeRef)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<NodeRef> getNodes()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Trigger getTrigger()
    {
        try
        {
            return new CronTrigger(this.getTriggerName(), this.getTriggerGroup(), this.getCronExpression());
        } catch (ParseException e)
        {
            return null;
        }
    }

}
