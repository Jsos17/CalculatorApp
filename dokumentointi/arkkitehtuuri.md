# Software architecture

## Application logic

**Package diagram**

![package diagram](https://github.com/Jsos17/CalculatorApp/blob/master/dokumentointi/calculatorapp_package_diagram.jpg)

**Class diagram**

![class diagram](https://github.com/Jsos17/CalculatorApp/blob/master/dokumentointi/calculatorapp_class_diagram.jpg)


## Long-term storage of expressions

### SQL database

The CalculatorApp provides an SQL database connection, and alongside the calculator functionality an user interface to save, retrieve and delete expressions.

The configuration file [config.properties](https://github.com/Jsos17/CalculatorApp/blob/master/CalculatorApp/config.properties) stores the name of the database that is in use.

The format of the saved expressions in the SQL database is the following:

     Expression (id integer PRIMARY KEY, symbols varchar(1000))

Thus the only input relevant to the user is the actual expression *symbols* that is stored in textual form.

Expressions are not modifiable in the database, instead simply a new expression is added. The user can easily delete duplicates (or near duplicates) by selecting them from the list and then pressing *Delete the selected expression from database*. 

During the execution of the program, the app automatically stores expressions in memory, so that the user does not need to remember to save every single expression instantly. The size of the memory can be configured by the user.

When a save, retrieval or delete operation is executed by pushing the appropriate button, a success/failure message is shown to the user for a duration of 10 seconds. The failure message is shown, if the underlying ExpressionDao class throws an SQLException, otherwise  a success message is shown.

The database list does not update automatically. Instead, after a save the user needs to retrieve all expressions from the database to see the newly saved expression there.

## Main functionality

**Sequence diagrams**

![Expression evaluation](https://github.com/Jsos17/CalculatorApp/blob/master/dokumentointi/Expression%20evaluation%20sequence.png)

## Weaknesses remaining in the program

Automated testing for the expression evaluation algorithms would insure that the implementations actually work as intended in all scenarios. Now test cases were created manually and for more complex ones, the result produced by wolfram-alpha for the same expression was the comparison basis.

Some of the InputParser's methods need refactoring, in order to simplify the code and to diminish the times the entire String is iterated over. Currently it is about 3n in the InputParser alone (although still O(n), but could be more efficient and clear).

The graphical user interface would benefit greatly from refactoring.
