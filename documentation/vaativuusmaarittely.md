# Software requirement specification for CalculatorApp

## The purpose of the application

The application provides all the basic arithmetic operations (+, -, *, /, mod) and also trigonometric functions, exponentiation, square-root, logarithmic, factorial and so on. 

Additionally it serves as a graphics calculator, providing the ability to visualize simple functions and graphically examine their behaviour. 

The application should also provide some means to save functions into a database, hence the user does not need to write the functions all over again every time he/she has closed the application. Additionally the user can also edit and delete his/her personally made functions.

The CalculatorApp should also provide some basic template-functions so that the user can examine the behaviour of the program before making his/her own functions.

## Users

At first the application does not provide any login feature, so users are not specified. Later on, the application can be expanded into allowing login, so that the users can save their own functions and only the user that created a function can modify it.

## User-Interface outline

The application should have the basic arithmetic operations visible to the user at all times, while the application is running, and an additional window should open when the user wishes to use the graphical calculation feature.

The ability of the user to modify what mathematical operations he/she has in his disposal might be a feature.

## Planned features for the basic version

* All the basic arithmetic operations with a button for each and/or the ability to type inputs

* Graphical user-interface

* Error handling for bad inputs, e.g. divide by zero etc, and appropriate messages for the user

* A separate graphics calculator window

* At least simple functions can be created, edited, saved and deleted by the user

* These simple functions can then be examined graphically through the graphics calculator feature

* Thus function creation should have detailed instructions and error handling


## Possibilities for expansion

* Visualizing the behaviour of a fucntion when it approaches its limit, e.g. lim (1 + 1/n)^n -> e when n -> infinity

* The ability to toggle between fractional and decimal representation of numbers and results

* If a login-feature exists, the ability for users to make public some of their functions for other users to use

