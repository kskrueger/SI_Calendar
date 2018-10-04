import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteCalendars {
    private String outputText = "";
    private String name;

    WriteCalendars(String name) {
        this.name = name;
    }

    void write(ArrayList<Event> eventList) {
        outputText += "BEGIN:VCALENDAR\n";
        for (Event event : eventList) {
            outputText += event.out();
        }
        outputText += "END:VCALENDAR\n";
        try {
            FileWriter fileWriter = new FileWriter(name+".ics");
            fileWriter.write(outputText);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
