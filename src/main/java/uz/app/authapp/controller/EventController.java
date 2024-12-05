package uz.app.authapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.app.authapp.db.EventDAO;
import uz.app.authapp.entity.Event;
import uz.app.authapp.entity.enums.Times;
import uz.app.authapp.entity.enums.WeekDays;
import uz.app.authapp.payload.EventDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventDAO eventDAO;

    @GetMapping
    public String event(Model model) {
        List<Event> events = eventDAO.getEvents();
        model.addAttribute("events", events);

        List<String> days = Arrays.stream(WeekDays.values()).map(weekDays -> weekDays.name()).toList();
        model.addAttribute("days", days);
        return "event";
    }

    @GetMapping("/{id}")
    public String event(@PathVariable(name = "id") Long id, Model model) {
        Event event = eventDAO.getEvent(id).orElseThrow(() -> new RuntimeException("event topilmadi"));
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setName(event.getName());
        eventDTO.setDescription(event.getDescription());


        List<String> days = new ArrayList<>();
        boolean valid = false;
        for (WeekDays value : WeekDays.values()) {
            if (value.name().equals(event.getStartDate())) {
                valid = true;
            }

            if (valid) {
                days.add(value.name());
            }
            if (value.name().equals(event.getEndDate())) {
                valid = false;
            }
        }
        eventDTO.setDays(days);

        List<String> times = new ArrayList<>();
        for (Times value : Times.values()) {
            int o = value.ordinal();
            if (o >= event.getStartTime()) {
                if (event.getEndTime() >= o)
                    times.add(value.getTime());
            }
        }
        eventDTO.setTimes(times);


        model.addAttribute("event", eventDTO);
        return "register";
    }

    @PostMapping
    public String addEvent(@ModelAttribute Event event, Model model) {
        eventDAO.addEvent(event);
        return "redirect:/event";
    }
}
