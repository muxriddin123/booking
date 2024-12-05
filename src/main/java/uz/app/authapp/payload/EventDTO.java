package uz.app.authapp.payload;

import lombok.Data;

import java.util.List;

@Data
public class EventDTO {
    private Integer id;
    private String name;
    private String description;
    private List<String> days;
    private List<String> times;
}
