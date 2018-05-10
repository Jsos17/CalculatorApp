# Software requirement specification for CalculatorApp (Vaatimusmäärittely)

## The purpose of the application

The application provides all the basic arithmetic operations (+, -, *, /, ^) and also trigonometric functions, square-root (other roots can be inputted as a fractional power), logarithm, absolute value and percentage. Negative numbers are represented by the neg() functions, since the "-" sign is interpreted as a subtraction operator. 

The application provides means to save functions into a database, hence the user does not need to write the functions all over again every time he/she has closed the application. Additionally the user can also save an new edited version of a function or  delete functions.

## Users

The application does not have any login feature.

## User interface outline

The user has all the buttons available at all times, and inputted functions also appear automatically in the list that represents the memory of the calculator.

Additionally, the user can retrieve all expressions saved in a database. After retrieval the expressions are permanently in view. 

If the user saves an expression or saves all expressions in memory, then the database list is updated automatically. The same applies to deletion of an expression. Thus, the user needs to click retrieve all expressions button only once.

## Planned features of the basic version

* All the basic arithmetic operations with a button for each.

* Graphical user-interface

* Error handling for bad inputs, e.g. divide by zero etc, and appropriate messages for the user

* Functions can be created, edited, saved and deleted by the user

* These functions can contain other functions

## Possibilities for expansion

* Adding a graphics calculator feature

* The ability to toggle between fractional and decimal representation of numbers and results

* If a login-feature exists, the ability for users to make public some of their functions for other users to use

* Customization of visible mathematical operations by the user
