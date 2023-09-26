package setup;
public enum DaysOfWeek {

    MONDAY("mandag"),
    TUESDAY("tirsdag"),
    WEDNESDAY("onsdag"),
    THURSDAY("torsdag"),
    FRIDAY("fredag"),
    SATURDAY("lørdag"),
    SUNDAY("søndag"),
    TODAY("i dag"),
    EVENING("aften"),
    TONIGHT("i nat");

    DaysOfWeek(String day) {
        this.day = day;
    }

    private final String day;

    public String getDay() {
        return day;
    }

}
