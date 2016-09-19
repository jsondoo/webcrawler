import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Class that contains the functionality for retrieve info from websites
 *  Uses the jsoup library for parsing HTML
 */
public class WebScraper {
    private static WebScraper instance;

    private WebScraper(){}

    public static WebScraper getInstance(){
        if(instance == null)
            instance = new WebScraper();

        return instance;
    }


    public List<String> getSectionURLsForCourse(Course course){
        List<String> sectionURLs = new ArrayList<String>();
        String courseName = course.getCourseName();
        // this creates the URL for the course
        // decided to use type string instead of URL because catching MalforedURLExceptions makes code look messy
        String courseURL = "https://courses.students.ubc.ca/cs/main?pname=subjarea&tname=subjareas&req=3&dept="+courseName.substring(0,4)+"&course="+courseName.substring(4);

        try {
            Document doc = Jsoup.connect(courseURL).get();
            // selects elements containing &section
            Elements links = doc.select("a[href*=&section]");
            for(Element next : links) {
                // gets absolute URL
                String link = next.attr("abs:href");
                sectionURLs.add(link);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sectionURLs;
    }



    public SectionType getSectionType(String url){
        String sectionType = null;
        try {
            Document doc = Jsoup.connect(url).get();
            String sectionName = doc.select("h4").first().text();

            sectionType = sectionName.substring(sectionName.indexOf("(")+1, sectionName.indexOf(")"));


        } catch (IOException e) {
            e.printStackTrace();
        }

        if(sectionType != null) {
            switch (sectionType) {
                case "Laboratory":
                    return SectionType.LAB;
                case "Lecture":
                    return SectionType.LECTURE;
                case "Waiting List":
                    return SectionType.WAITLIST;
                case "Tutorial":
                    return SectionType.TUTORIAL;
                default:
                    return SectionType.UNKNOWN;
            }
        }
        else
            return SectionType.UNKNOWN;

    }

    public void printSeatSummaryForSection(String url){
        try {
            Document doc = Jsoup.connect(url).get();
            String seatSummary = doc.select("table[class='table table-nonfluid']").last().text();

            // gets rid unnecessary info in the text
            String shortSummary = seatSummary.replace("Seat Summary ", "").replace("*","");

            // if seat summary is unavailable, prints "Unavailable Seat Summary"
            if(!shortSummary.contains("Total Seats Remaining:"))
                System.out.println("Seat Summary Unavailable");
            else
                System.out.println(shortSummary);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
