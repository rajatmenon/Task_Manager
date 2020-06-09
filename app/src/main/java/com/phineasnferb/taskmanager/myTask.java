package com.phineasnferb.taskmanager;

public class myTask {
    String tasktitle, taskdesc, taskdate, key;

    public myTask() {
    }

    public myTask(String tasktitle, String taskdesc, String taskdate, String key) {
        this.tasktitle = tasktitle;
        this.taskdesc = taskdesc;
        this.taskdate = taskdate;
        this.key = key;
    }

    public String getTasktitle() {
        return tasktitle;
    }

    public void setTasktitle(String tasktitle) {
        this.tasktitle = tasktitle;
    }

    public String getTaskdesc() {
        return taskdesc;
    }

    public void setTaskdesc(String taskdesc) {
        this.taskdesc = taskdesc;
    }

    public String getTaskdate() {
        return taskdate;
    }

    public void setTaskdate(String taskdate) {
        this.taskdate = taskdate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}