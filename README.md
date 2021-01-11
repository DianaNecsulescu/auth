# auth
Spring boot authentication microservice

The present project represents a spring boot authentication microservice. Its role is to permit user to login and logout and see their
details such as name and age. The project usesthe H2 in memory database which means that all the created users will be lost at a restart. 
I choose this option in order to be easier to set-up and run the application without needing to have another preinstalled database. 
The operations that are supported are the following:
    - create a new user
    - login
    - see user details (a user must first login into its account to see its details)
    -logout
The operations could be executed via HTTP REST. To simplify this process I enabled swagger. Use this link to easily test the endpoints:
http://localhost:8080/swagger-ui.html

The user information are saved in the user table and the user details in the details table. There is a one to one mapping relationship
between the two table such that each user has a user detail.
The passwords are encyprted before saving them in the database.
