import java.util.Scanner;
import java.util.Set;

public class Main {
    private static SectionType selectedSectionType;
    private static int nextOption;

    public static void main(String[] args) {

        System.out.println("Welcome to UBC WebScraper!");

        promptCourseNameInput();
        printNumSections();
        promptSectionTypeInput();
        printSeatSummary();

        //functionality for allowing users to choose different options such as picking different course or section
        endPrompt();
        while(nextOption != 0){
            if(nextOption == 1) {
                promptSectionTypeInput();
                printSeatSummary();
            }

            if(nextOption == 2){
                promptCourseNameInput();
                printNumSections();
                promptSectionTypeInput();
                printSeatSummary();
            }

            endPrompt();
        }

    }

    /**
     * allows users to choose whether to end the application, pick a different course, or pick a different section type for selected course
     */
    // TODO better handling for inputs using regex
    private static void endPrompt(){
        System.out.println("To pick another section type for "+ CourseManager.getInstance().getSelectedCourse().getCourseName()+", type 1.");
        System.out.println("To pick another course, type 2.");
        System.out.println("To end the app, type 0.");
        Scanner scanner = new Scanner(System.in);

        nextOption = scanner.nextInt();

    }


    private static void printSeatSummary(){
        CourseManager.getInstance().printSeatSummaryForSelectedCourse(selectedSectionType);
    }

    private static void promptSectionTypeInput(){
        System.out.println("Pick a section type:");
        System.out.println("A - Lecture");
        System.out.println("B - Lab");
        System.out.println("C - Tutorial");
        System.out.println("D - Waiting List");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.next().toLowerCase();
        while(!(input.matches("[a-d]"))){
            System.out.println("Please enter A, B, C or D");
            input = scanner.next();
        }

        switch(input){
            case "a":
                selectedSectionType = SectionType.LECTURE;
                break;
            case "b":
                selectedSectionType = SectionType.LAB;
                break;
            case "c":
                selectedSectionType = SectionType.TUTORIAL;
                break;
            case "d":
                selectedSectionType = SectionType.WAITLIST;
                break;
        }
    }

    private static void printNumSections(){
        Course selected = CourseManager.getInstance().getSelectedCourse();
        System.out.println("Found a total of "+ selected.getNumSections()+" sections for "+ selected.getCourseName() +".");
    }


    private static void promptCourseNameInput(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a course name. Example: MATH100");
        String courseName = scanner.next();

        while(!isValid(courseName)){
            System.out.println("Please enter a valid course name. Example: CPSC310");
            System.out.println("Enter a course name:");
            courseName = scanner.next();
        }

        System.out.println("Retrieving sections...");
        CourseManager cManager = CourseManager.getInstance();
        //check CourseManager to see if the course already exists and select the course if it does
        // otherwise create a new course
        if(cManager.containsCourseWithName(courseName)){
            cManager.selectCourseWithName(courseName);
        }
        else {
            Course inputCourse = new Course(courseName);
            cManager.addCourse(inputCourse);
            cManager.selectCourseWithName(courseName);
        }
    }

    private static boolean isValid(String courseName){
        return courseName.matches("[A-Z]{4}[0-9]{3}");
    }


}