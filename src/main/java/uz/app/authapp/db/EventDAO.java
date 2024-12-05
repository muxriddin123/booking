package uz.app.authapp.db;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import uz.app.authapp.entity.Event;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EventDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    public void addEvent(Event event) {
        jdbcTemplate.update(
                "insert into event(name, description, start_date, end_date, start_time, end_time, capacity/* bu data bser bilan bir xil  bolishi kerak*/) values( :name, :description, :startDate, :endDate, :startTime, :endTime, :capacity/* bu past bn bir xil bosin*/)",
                Map.of(
                        "name",event.getName(),
                        "description",event.getDescription(),
                        "startDate",event.getStartDate(),
                        "endDate",event.getEndDate(),
                        "startTime",event.getStartTime(),
                        "endTime",event.getEndTime(),
                        "capacity",event.getCapacity()
                )
        );
    }

    public List<Event> getEvents() {
        List<Event> events= jdbcTemplate.query("select * from event", (rs,row)->{
           Event temp = new Event();
           temp.setId(rs.getInt("id"));
           temp.setName(rs.getString("name"));// yashil bilan yazilganla db bilan bir xil bosin
           temp.setDescription(rs.getString("description"));
           temp.setStartDate(rs.getString("start_date"));
           temp.setEndDate(rs.getString("end_date"));
           temp.setStartTime(rs.getLong("start_time"));
           temp.setEndTime(rs.getLong("end_time"));
           temp.setCapacity(rs.getInt("capacity"));
           return temp;
        });

        return events;
    }

    public Optional<Event> getEvent(Long id) {
        Event event = jdbcTemplate.queryForObject("select * from event where id = :id",
                Map.of("id", id),
                (rs, r) -> {
                    Event temp = new Event();
                    temp.setId(rs.getInt("id"));
                    temp.setName(rs.getString("name"));
                    temp.setDescription(rs.getString("description"));
                    temp.setStartDate(rs.getString("start_date"));
                    temp.setEndDate(rs.getString("end_date"));
                    temp.setStartTime(rs.getLong("start_time"));
                    temp.setEndTime(rs.getLong("end_time"));
                    temp.setCapacity(rs.getInt("capacity"));
                    return temp;
                }
        );
        return Optional.ofNullable(event);
    }
}
