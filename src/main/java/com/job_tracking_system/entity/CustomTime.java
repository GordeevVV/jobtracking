package com.job_tracking_system.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomTime{
    long hours;
    long minutes;
    long seconds;

    @Override
    public String toString() {
        return  hours +" : " + minutes + " : " + seconds;
    }
}
