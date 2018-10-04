import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class AddSI {
    public static void main(String[] args) {
        System.out.println(parseScan());

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println("Day "+day);

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.getDayOfWeek().toString().toLowerCase());
        System.out.println(now.getDayOfMonth());
        now = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println(now.getDayOfMonth()+ " " + now.getMonthValue());

        System.out.print("Input a day of the week: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(input);
    }

    public static void main1(String[] args) {
        Calendar now = Calendar.getInstance();
        int weekday = now.get(Calendar.DAY_OF_WEEK);
        if (weekday != Calendar.MONDAY)
        {
            // calculate how much to add
            // the 2 is the difference between Saturday and Monday
            int days = (Calendar.SATURDAY - weekday + 2) % 7;
            now.add(Calendar.DAY_OF_YEAR, days);
        }
        System.out.println(now.getFirstDayOfWeek());
    }

    private static String parseScan() {
        String fullText = scanSite("https://apps-dso.sws.iastate.edu/si/course.php?id=1291","#content > div:nth-child(2)");

        String startWord = "Weekly SI Sessions";
        String endWord = "About SI for this Course";

        int startIndex = fullText.indexOf(startWord) + startWord.length();
        int endIndex = fullText.indexOf(endWord);

        return fullText.substring(startIndex,endIndex);
    }

    private static String scanSite(String url, String path) {
        Document site;
        try {
            site = Jsoup.connect(url).get();
            return site.select(path).first().text();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("DOES NOT EXIST!");
            return "";
        }

        System.out.println("ERROR");
        return "";
    }

    public static void write() {
        Event testCal = new Event("testCal");
        Event event1 = new Event("event1");
        ArrayList<Event> events = new ArrayList<>();
        WriteCalendars calendar = new WriteCalendars("myCal");

        testCal.setStartDateTime(2018, 9, 30, 6+12, 10);
        testCal.setEndDateTime(2018, 9, 30, 7+12, 10);
        testCal.setLocation("Gilman 2205");
        events.add(testCal);

        event1.setStartDateTime(2018, 9, 30, 7+12, 10);
        event1.setEndDateTime(2018, 9, 30, 8+12, 10);
        event1.setLocation("Gilman 2205");
        events.add(event1);

        calendar.write(events);
    }

}
