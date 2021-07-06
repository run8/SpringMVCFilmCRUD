## Spring MVC Film C.R.U.D.

### Overview
This full stack web application allows a user to to interact with data in a film database. A user can choose from a menu of options to view specific film information based on a keyword search or film ID search. A user can also add new films to the data base and edit or delete information in the database. The user views information presented dynamically based on user input.

### Technologies Used
* Back end
- Framework: Spring
- IDE: Eclipse
- Solution Stack: MAMP
- Web Server: Apache Tomcat v8.5
- Build Automation & Dependency Manager: Gradle
- Database Service: MySQL
* Front End
- Bootstrap
- CSS
- XML
* Design Patterns
- MVC (Model View Controller)
- DAO (Database Access Object)

### Lessons Learned
- DAO: How to isolate entities that use data, access data, and persist data.
- How to make a database atomic (changes apply across all affected tables).
- Further learned Bootstrap and JSP to improve user interface (Dropdown menus,)
- MySQL UPDATE, INSERT, DELETE and associated logic and syntax.
- How to use Spring and Spring beans.
- How to apply the MVC design pattern.

### How To Run

Upon landing on the site. The user is presented with options to search the database by film ID#, keyword, or to add a new film.

* If searching by ID #, the application will not accept an empty search field or any input other than a whole number. If a film with the provided ID # does not exist in the database, the user is informed that no results were found. Otherwise, the user will be shown the one film that matches the searched ID #. At this point, the user can click on the film ID # in the results table to view all of the information pertaining to the film or they may perform another search.

* If searching by keyword, the application will accept any input in the search box. Clicking search with an empty search box will return a results table showing all of the films in the database. Otherwise, the keyword search will query the database looking for any films that contain the searched term in their title or description. If no matches exist, the user is informed of this. If matches do exit, the user is displayed a table with all of the matching results. From here, the user may click on a films ID# to view all details about that film or they may perform a new search.

* Following a search, if results are found, the user has the option of clicking on the film ID # of a result. Clicking on the ID # will bring the user to a page that displays all of the information pertaining to the film. From this detailed view, the user is also provided an option to Delete a film or update a film.

* Adding a film - If a user chooses to add a film, they are displayed a form that allows them to enter all of the information pertaining to a film. This includes title, description, release year, rental rate, rental duration, replacement cost, film length, rating, language, and category. After filling out the fields and selecting submit, the user will be informed that the submit was successful and they will be sent to the detailed view of the newly added film.

* Deleting a film - When a user selects delete film, a modal is displayed asking the user to confirm that they would like to delete the film. The user is only allowed to delete films that they have added to the database. If the user attempts to delete a film that they did not add, they will be displayed a message saying the delete failed and they will be returned to the detailed view of the film. If the deletion occurs on a film the user added, the user will be informed that the deletion was successful and they will be redirected to the main landing page view.

* Editing a film - When the user selects edit film, they are displayed the same form as if they were to add a film. However, the information is pre-filled with the exiting details of the film. The user may choose to keep any of the original fields or they may change any of the fields. After hitting submit, the user is informed that the update was successful and they are redirected to the films detailed view which now shows the updated information.