package com.job_tracking_system.entity;

import java.util.Map;

public class AuthorizeMessage extends Message{
    Map<String, String> condition;

    public Map<String, String> getCondition() {
        return condition;
    }

    public void setCondition(Map<String, String> condition) {
        this.condition = condition;
    }
}
