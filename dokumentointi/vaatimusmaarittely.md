# Software requirement specification for CalculatorApp (Vaatimusmäärittely)

## The purpose of the application

The application provides all the basic arithmetic operations (+, -, *, /, ^) and also trigonometric functions, square-root (other roots can be inputted as a fractional power), logarithm, absolute value and percentage. Negative numberr are represented by the help of the neg() function, since the "-" sign is interpreted as a subtraction operator. 

The application provides means to save functions into a database. Hence the user does not need to write the functions all over again every time he/she has closed the application. Additionally, the user can also save a new edited version of a function or  delete functions.

The user can search expressions based on a keyword, and all matches will be shown to the user.

## Users

The application does not have any login feature.

## User interface outline

The user has all the buttons available at all times, and inputted functions also appear automatically in the list that represents the memory of the calculator.

The count of expressions in the database is shown to the user at all times and is updated automatically. 

Additionally, the user can retrieve all expressions saved in a database. After retrieval, the expressions are permanently in view. 

If the user saves an expression or saves all expressions in memory, then the database list is updated automatically. The same applies to deletion of an expression. Thus, the user needs to click retrieve all expressions button only once.

## Features of the final version

* All the basic arithmetic operations and selected functions with a button for each.

* Graphical user-interface

* If the expression cannot be evaluated, then the user is notified

* Functions can be created, edited, saved and deleted by the user

* These functions can contain other functions

* When a user copies an existing expression and saves it again, a new expression is saved and the copied expression stays intact in the database. However, the user can easily delete the old expression, if it is not needed.

* Database search functionality

* Count of expressions in the database is shown to the user

* The ability to modify the size of memory

* When trying to close the application, the user is asked for confirmation of this and is informed that all unsaved expressions will be lost once the application closes.

## Possibilities for expansion

* Adding a graphics calculator feature

* Better than double precision accuracy, using integer values when possible

* Adding e, pi and maybe other constants

* The ability to toggle between fractional and decimal representation of numbers and results

* If a login-feature exists, the ability for users to make public some of their functions for other users to use

* Customization of visible mathematical operations by the user
