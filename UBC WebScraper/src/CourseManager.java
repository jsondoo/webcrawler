import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Keeps track of all the courses
 */
public class CourseManager implements Iterable<Course> {
    private Set<Course> courses;
    private static CourseManager instance;
    private Course selectedCourse;

    private CourseManager(){
        courses =  new HashSet<>();
    }

    public static CourseManager getInstance(){
        if(instance == null){
            instance = new CourseManager();
        }

        return instance;
    }

    public void printSeatSummaryForSelectedCourse(SectionType sectionType){
        selectedCourse.printSeatSummary(sectionType);
    }


    /**
     *
     * @param courseName name of course to search for in CourseManager
     * @return true if CourseManager already contains a course with courseName, false otherwise
     */
    public boolean containsCourseWithName(String courseName){
        for(Course next : courses){
            if(next.getCourseName().equals(courseName))
                return true;
        }

        return false;
    }

    /**
     *
     * @param courseName string used to search for course
     * @return a course in the CourseManager with the same name as given parameter
     */
    public Course getCourseWithName(String courseName){
        Course toReturn = null;
        for(Course next: courses) {
            if (next.getCourseName().equals(courseName)) {
                toReturn = next;
            }
        }
        return toReturn;
    }

    public void selectCourseWithName(String courseName){
        for(Course next: courses){
            if(next.getCourseName().equals(courseName)){
                selectedCourse = next;
            }
        }
    }

    public Course getSelectedCourse(){
        return selectedCourse;
    }

    public void addCourse(Course c){
        courses.add(c);
    }

    @Override
    public Iterator<Course> iterator(){
        return courses.iterator();
    }



}
