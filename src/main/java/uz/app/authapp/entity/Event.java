package uz.app.authapp.entity;

import lombok.Data;

@Data
public class Event {
    private Integer id;
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private Long startTime;
    private Long endTime;
    private Integer capacity;
}
