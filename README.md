# Book Library Management System

## Overview
The **Book Library Management System** is a comprehensive backend application designed to efficiently manage books, authors, and rentals. It provides a range of features such as *CRUD operations* for books and authors, rental management, and overdue rental tracking. Built using Java and Spring Boot framework, it offers a robust and scalable solution for library management needs.
* **Point to Know:-**
    * Mater Branch has Backend infomation. 
    * Main Branch has [UI](https://github.com/sunnysagar/e-Library-system/tree/main) information

## Main Features
* **CRUD Operations**: Enable the creation, retrieval, updating, and deletion of books and authors, providing full control over the library's inventory.
* **Rental Management**: Facilitates the renting and returning of books, ensuring availability and tracking rental periods.
* **Overdue Rental Tracking**: Automatically monitors rental periods and alerts users about overdue rentals, enhancing accountability and efficiency.

## Technologies Used
* **Java:** The primary programming language for backend logic, providing flexibility, and performance.
* **Spring Boot:** A powerful framework for building Java-based applications, offering features such as dependency injection, MVC architecture, and RESTful API development.
* **MySQL:** The relational database management system used for storing book, author, and rental data, ensuring data integrity and reliability.
* **IntelliJ IDEA:** The integrated development environment (IDE) for Java development, providing tools for coding, debugging, and testing.

## Setup Instructions
**Requirements**
* Ensure you have latest version IntelliJ IDEA installed on your system.
   * [Download link](https://www.jetbrains.com/idea/download/)
* Make sure you have JDK 17 installed and configured.
* Clone the repository containing the project files.
    * *clone command:* git clone <repository_url> (HTTPS_url)

**Open Project in IntelliJ IDEA:**
* Launch IntelliJ IDEA.
* Select "Open" from the main menu.
* Navigate to the location where you cloned the project repository.
* Select the project folder and click "Open" to load the project into IntelliJ IDEA.

**Configure Project SDK:**
* *The IDE will automatically configure the project SDK.*
* *However, if it doesn't, Do it manually*:
  * Go to "File" > "Project Structure" > "Project" in IntelliJ IDEA.
  * Set the Project SDK to JDK 17.
  * Click "Apply" and then "OK" to save the changes.
    
**Setup Database**
* Open the project directory
* Navigate to the src/main/java/resources/application.properties directory.
* Double-click on the file to open it in the editor.
* Change the database name otherwise create the same database in your database tool like Workbench.
    * My SQL Worknbench download [Link](https://dev.mysql.com/downloads/workbench/)
* Change the url, username, password as per need.

## Build and Run the Application
* Once the project is loaded, IntelliJ IDEA will automatically detect the Maven project structure.
* Navigate to the main class for e.g. Application.java
   * **Steps:**
      * Open the project directory
      * Navigate to the src/main/java directory.
      * Look for the main class file for e.g Application.java
      * Double-click on the main class file to open it in the editor.
* Right-click on the main class file and select **Run** to build and run the application.

## Testing Endpoints
* Once the application is running, you can test the RESTful APIs using a tool like Postman.
  * Postman download [Link](https://www.postman.com/downloads/)
  * *Sample request for testing endpoints:* `http://localhost:port_no/`
    * **Few Samples:**
      * Get Book by ID : `http://localhost:8009/books/bookId`
      * Create Book by POST Method: `http://localhost:8009/books`
      * Rent Book by POST Method: `http://localhost:8009/rentals/bookId/renterName`
  * For a detail overview of all endpoints and their functionalities, please refer to the [API_Documentation.md](https://github.com/sunnysagar/Book-Library-System/blob/master/src/main/resources/API_Documentation.md) file.
## Design Decision and Assumptions
* **IDE Choice**
  * IntelliJ IDEA is chosen due to its compatibility with Java, and ease of use and also offers excellent support for Java development, debugging tools, and many more featues.
* **Database Selection**
  * MySQL Workbench is selected as the database management system for its reliability, performance, and also provides a user-friendly interface.
* **Testing with Postman**
  * It was utilized for testing RESTful APIs due to its intuitive interface, powerful features, and also provide sending requests, inspecting responses, and debugging of API endpoints.
* **Assumption while developing the application**
  * Each book has a unique identifier (ID).
  * ISBN numbers are unique for each book.
  * Publication year cannot be greater then current year.
  * Rental durations follow a specific format or policy.
  * Users are required to provide valid inputs when interacting with the system.
