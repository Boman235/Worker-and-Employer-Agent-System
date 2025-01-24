# Worker-and-Employer-Agent-System

Overview

The Worker and Employer Agency System is a Java-based application designed to facilitate the connection between freelancers and companies seeking to hire them. It provides an intuitive user interface built with JavaFX, allowing users to register, manage, and connect with each other effectively.

 Features
 Freelancer Registration: Register freelancers with details such as name, work type, age, GPA, department, email, and gender.
- Company Registration: Allow companies to post job openings with specific requirements (title, GPA, department, and number of positions).
- Connection Management: Admins can manage connections between freelancers and companies, ensuring suitable matches based on GPA and department.
- Feedback System: Freelancers and companies can give and view feedback.
- Notification System: Admins can send notifications regarding various actions within the system.

 Getting Started

1. Clone the repository: git clone <repository-url>

2. Compile the Java files:javac Agenecy.java
  
3. Run the main application: java Agenecy.AgencySystemDemo


OOP Principles Applied

- Encapsulation: User data is encapsulated within the User class.
- Interface Segregation: Separate interfaces for workers and advanced workers for flexibility.
- Implementation: Concrete classes implement defined interfaces for specific behaviors.

 Design Patterns

-Adapter Pattern the UserAdapter class adapts the User interface for different user roles, allowing for flexibility in user management.

- Strategy Pattern Different worker types (e.g., Freelancer, FullTimeWorker) implement the Worker interface, providing varied behaviors for job applications.


Technologies Used

- Java
- Javafx
- Object-Oriented Programming Principles

 Contact

For questions or feedback, please contact the author at: samueldebiso07@gmail.com.
