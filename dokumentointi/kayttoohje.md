# User manual/Käyttöohje

Download file [CalculatorApp-v1.0.jar](https://github.com/Jsos17/CalculatorApp/releases/tag/viikko5)

Download file [CalculatorApp-v2.0.jar](https://github.com/Jsos17/CalculatorApp/releases/tag/v2.0)

**Download file [CalculatorApp-v3.1.jar](https://github.com/Jsos17/CalculatorApp/releases/tag/v3.1)**

## Configuration

It is advisable that the user adds configuration file [config.properties](https://github.com/Jsos17/CalculatorApp/blob/master/CalculatorApp/config.properties) in the same directory as the jar file. It defines the name of the SQL database that the program uses to save expressions for long term. 

If this file is not added, then a new file called mathAlternative.db is created. 

**If the jar is not executed from the command line the actual database might be stored in a different place than where the jar is. In other words, it is possible that left-clicking the jar creates a new mathAlternative.db database somewhere else (probably in the root directory) and running the command *java -jar CalculatorApp-v3.1.jar* creates another in the same directory where the jar is.** Thus left-clicking the jar file is not dependable way of ensuring that the right database is accessed, and thus using the command line is advised.

The format of the [config.properties](https://github.com/Jsos17/CalculatorApp/blob/master/CalculatorApp/config.properties) file is the following:

    mathDatabase=math.db

The input "math.db" can be substituted with whatever name the user desires, and then the program will access that database only.

## Running the program

It is strongly advised to either create the config.properties file or to only run the jar through command line, for reasons explained above.

The program can be run with the command

    java -jar CalculatorApp-v3.1.jar

## Using the CalculatorApp

The user can input any expression by using the provided buttons, and if the expression can be parsed (i.e. brackets, operators, dots etc are placed correctly) provides the result as a double value. If the input cannot be parsed, then the user is notified and asked to correct the expression.

The CalculatorApp uses double precision and is intended mainly to be a demonstration of certain programming skills. Thus it is not suited for scientific calculations requiring extreme precision. 

Currently the operations +, -, *, /, ^, ., Ans, Delete, Clear, =, (, ) and the functions sin(), cos(), tan(), log(), ln(), sqrt(), abs(), neg() and %() are supported, where the neg() function represents the negative sign of a number.

So for example a percentage calculation is carried out like: %(42) = 0.42. 

Thus every sign that has double brackets () following it, is a function, and what needs to be calculated is to be put inside the brackets. The user must remember to close all brackets. 

The **sign "-" is a subtraction operator, and does not function as the sign of a number**. Instead the **neg() function** should be used for this purpose: for example neg(7) = -7.0 and neg(7)^3 = -343.0.
 
Maximum size for expression is currently set at 1000 characters, which cannot be exceeded.

If the user wishes to correct an expression then the delete button can be used to delete characters.

The Clear button clears the *input, expression, result and instructions* fields. 

### Memory and database functionality

**Set memory limit using the slider** a slider can be used to set the desired amount of expressions stored in memory. As the word memory implies, once the program closes all expressions in memory are lost. In order to save expressions for future use, the database functionality should be used. See below.

**Set memory limit** once this button is clicked the value set by the slider comes in effect. Note if new memory size is set smaller than current list size of recently used expressions, then expressions will be removed until the list size matches the given memory limit.

**Clear memory** removes everything from memory.

**Retrieve all saved expressions** retrieves all expressions that exist in the database.

**Copy the selected database expression to input** copies the selected expression to input field.

**Delete the selected expression** deletes the selected expression. This action is irreversible.

**Save all expressions in memory** inserts all the expressions that are in memory into the database.

**Save the selected expression in memory** inserts the selected expression into the Expression table. If the expression already existed in the database, a duplicate is inserted anyway.

**Copy the selected memory expression to input** copies the selected expression in memory to input field.

**Search for an expression** allows the user to search expressions matching the provided keyword. Here the keyboard is enabled, and desired keyword can simply be typed and the search is executed by pressing the button.

**Copy the selected match to input** copies the selected match to input.
