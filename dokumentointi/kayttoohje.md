# User manual/Käyttöohje

Download file [CalculatorApp-v1.0.jar](https://github.com/Jsos17/CalculatorApp/releases)

## Configuration

In order to run, the program needs to have the configuration file [config.properties](https://github.com/Jsos17/otm-harjoitustyo/blob/master/CalculatorApp/config.properties) in the same directory as the jar file. It defines the name of the SQLite database that the program uses to save expressions for long term. 

The file *config.properties* must be created and the proper line below has to be added to the file before running the program or exceptions may occur and the program might not run.

The format of the file is the following:

    mathDatabase=math.db

## Running the program

The program can be run with the command

    java -jar CalculatorApp-v1.0.jar

## Using the CalculatorApp

The user can input any expression by using the provided buttons, and if the expression can be parsed (i.e. brackets, operators, dots etc are placed correctly) provides the result as a double value. If the input cannot be parsed, then the user is notified and asked to correct the expression.

Currently the operations (+, -, *, /, ^, ., Ans, Delete, =) are supported. Additionally, the app has an integrated database and memory. See *Memory and database functionality* below. 

Maximum size for expression is currently set at 1000 characters, which cannot be exceeded.

**Memory and database functionality**

*Set memory limit using the slider* a slider can be used to set the desired amount of expressions stored in memory. As the word memory implies, once the program closes all expressions in memory are lost. In order to save expressions for future use, the database functionality should be used. See below.

*Set memory limit* once this button is clicked the value set by the slider comes in effect. Note if new memory size is set smaller than current list size of recently used expressions, then expressions will be removed until the list size matches the given memory limit.

*Clear memory* removes everything from memory.

*Retrieve all saved expressions* retrieves all expressions that exist in the SQLite Expression table.

*Copy selected database expression* copies the selected expression to input field.

*Delete the selected expression* deletes the selected expression. This action is irreversible.

*Save all expressions in memory* inserts all the expressions that are in memory into the SQLite Expression table.

*Save the selected expression* inserts the selected expression into SQLite Expression table. If the expression already existed in the database, a duplicate is inserted anyway.

*Copy the selected memory expression* copies the selected expression in memory to input field.
