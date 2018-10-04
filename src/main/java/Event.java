public class Event {
    private String name;
    private String outputText = "";
    private String startDateTime = "";
    private String endDateTime = "";
    private String location = "";

    Event(String name) {
        this.name = name;
    }

    void setStartDateTime(int year0000, int month00, int day00, int hour00, int minute00) {
        startDateTime = String.format("%04d", year0000)
                +String.format("%02d", month00)
                +String.format("%02d", day00)
                +"T"
                +String.format("%02d", hour00)
                +String.format("%02d", minute00)
                +"00"+"Z";
    }

    void setEndDateTime(int year0000, int month00, int day00, int hour00, int minute00) {
        endDateTime = String.format("%04d", year0000)
                +String.format("%02d", month00)
                +String.format("%02d", day00)
                +"T"
                +String.format("%02d", hour00)
                +String.format("%02d", minute00)
                +"00"+"Z";
    }

    void setLocation(String location) {
        this.location = location;
    }

    String out() {
        outputText += "BEGIN:VEVENT\n";
        outputText += "DTSTART:"+startDateTime+"\n";
        outputText += "DTEND:"+endDateTime+"\n";
        outputText += "SUMMARY;ENCODING=QUOTED-PRINTABLE:"+name+"\n";
        outputText += "LOCATION;ENCODING=QUOTED-PRINTABLE:"+location+"\n";
        outputText += "END:VEVENT\n";
        return outputText;
    }
}
