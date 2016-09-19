import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Class for representing courses
public class Course {
    private Set<Section> sections;
    private String courseName;

    public Course(String courseName){
        this.courseName = courseName;
        sections = new HashSet<>();
        makeSections();
    }

    public String getCourseName(){
        return courseName;
    }

    public int getNumSections(){ return sections.size();}

    public Set<Section> getSectionsForCourse(){
        return sections;
    }

    private void makeSections(){
        WebScraper webScraper = WebScraper.getInstance();
        List<String> sectionURLs = webScraper.getSectionURLsForCourse(this);
        for(String next : sectionURLs){
            sections.add(new Section(next));
        }
    }


    public void printSeatSummary(SectionType selectedSectionType){
        int count = 0;

        for(Section next : sections ){
            if(next.getSectionType().equals(selectedSectionType)){
                next.printSectionName();
                next.printSeatSummary();
                System.out.print("\n");
                count++;
            }
        }

        if(count == 0)
            System.out.println("No "+ selectedSectionType.toString().toLowerCase() + " sections were found.");
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (sections != null ? !sections.equals(course.sections) : course.sections != null) return false;
        return courseName.equals(course.courseName);

    }

    @Override
    public int hashCode() {
        int result = sections != null ? sections.hashCode() : 0;
        result = 31 * result + courseName.hashCode();
        return result;
    }
}
