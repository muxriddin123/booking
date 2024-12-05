package uz.app.authapp.entity;

import lombok.Data;

@Data
public class EventBooking {
    private Integer id;
    private Integer eventId;
    private String day;
    private String time;
}
