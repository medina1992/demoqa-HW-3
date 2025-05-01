package data;

public record BirthDate(String day, String month, String year) {

    public String getFormatted() {
        return String.format("%s %s %s", day, month, year);
    }

    public String getFormattedForCheck() {
        return String.format("%s %s,%s", day, month, year);
    }
}

