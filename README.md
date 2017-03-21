# UBCrawl
Retrieve all the information you need about UBC courses, sections, and waitlists!
Uses jsoup 1.9.2 API to crawl the web

<h4>Features</h4>
<ul>
    <li>Uses jsoup to parse the source code of websites of UBC domain</li>
    <li>Gets input from user through the console</li>
    <li>Retrieves information such as number of people registered in certain sections for a given course (e.g. waitlists)</li>
</ul>

# Images
When the application is ran, the user is first prompted an input for a course name and a course section type. The application connects to the UBC course webpage corresponding to the given course and parses the source code for information. 

Below is an example of using the webscraper to get information about the waitlists for the course CPSC310:

![](https://cloud.githubusercontent.com/assets/21695878/18620699/78c57ede-7dcc-11e6-9ae1-1a695b6cc119.JPG)

Once the information is displayed, the user can choose to either end the application, or choose a different course or a section type.

If the section information is unavailable, it displays "Seat Summary Available". This usually means that the course is part of the standard timetable so there is no seat summary to be displayed.

![](https://cloud.githubusercontent.com/assets/21695878/18620700/7b76c598-7dcc-11e6-8168-4ed7be8154a0.JPG)

# License
MIT © jsondoo
