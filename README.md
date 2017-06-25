# ProjectManagementSystem-DAO-
DAO and model for web application ProjectManagementSystem. Used metamodel.

The object model describing the problem is given:
The company is engaged in software development. Projects are carried out for customers. The project has a start and end date. The project consists of phases (Sprint). The next phase can begin only after the completion of the previous one. Phases consist of tasks. Tasks have a predictable execution time in hours (Estimate). Problems can have dependencies: a certain task can begin only after the tasks affecting it have been completed. A task can consist of other tasks (subtasks). The task may require the qualifications that this or that employee has. The manager assigns tasks to employees, specifying the date and time of execution. One employee can work in parallel on several tasks. Several employees can work together on the same task. Employees confirm the receipt of the task, fix its execution (with the actual execution time) or submit a request to increase the time required if not in time.
To store objects use:
1) Collections Framework.
2) Using file I / O in the following formats:
A. In the form of a text file (one line - one entry JSON)
B. In a binary file using serialization
3) Using a database (JDBC Oracle)
4) XML file
