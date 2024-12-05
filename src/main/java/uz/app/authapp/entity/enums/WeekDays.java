package uz.app.authapp.entity.enums;

public enum WeekDays {
    MONDAY("MONDAY"),
    TUESDAY("TUESDAY"),
    WEDNESDAY("WEDNESDAY"),
    THURSDAY("THURSDAY"),
    FRIDAY("FRIDAY"),
    SATURDAY("SATURDAY"),
    SUNDAY("SUNDAY");
    private String weekDay;

    WeekDays(String weekDay) {
        this.weekDay = weekDay;
    }
}
