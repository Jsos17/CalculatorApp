# Software architecture

## Application logic

The classes ExpressionEvaluator and InputParser handle almost all the functional logic of the program. In other words, they are responsible for checking the input for correctness (InputParser) and transforming it into numerical form (ExpressionEvaluator). The CalculatorService class handles the actual numerical computations. 

The ExpressionMemory class stores recently used expressions during the execution of the program, while ExpressionDao offers database functionality, and thus long-term storage for expressions.

MathDatabase provides a database connection and Expression class provides a Java encapsulation for the expressions stored in the SQL Expression table.

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

### Sequence diagrams

CalculatorAppUi calls ExpressionEvaluator which first performs three checks on the input using the InputParser, and then starts calling its own methods tokenizeExpression, shuntingYardWithFunctions and postFixEvaluator. During these method calls other methods from InputParser are called and also short helper methods from inside the ExpressionEvaluator class.

Eventually ExpressionEvaluator returns the value 4.0 and then CalculatorAppUi calls ExpressionMemory to add **the expression not the result** into memory and to then return the updated ArrayList which will then be updated to actually show in the gui. And finally the result field is updated with the result of the evaluation.

The following diagram shows this in greater detail:

![Expression evaluation in great detail](https://github.com/Jsos17/CalculatorApp/blob/master/dokumentointi/Detailed_Expr_Eval_Sequence.png)

## Omissions from original requirements

The modulo operation was removed, since using it with double precision makes no sense.

## Weaknesses remaining in the program

Automated testing for the expression evaluation algorithms would insure that the implementations actually work as intended in all scenarios. Now test cases were created manually and for more complex ones, the result produced by wolfram-alpha for the same expression was the comparison basis.

Some of the InputParser's methods need refactoring, in order to simplify the code and to diminish the times the entire String is iterated over. Currently it is about 3n in the InputParser alone (although still O(n), but could be more efficient and clear).

The CalculatorAppUi would benefit greatly from refactoring.
