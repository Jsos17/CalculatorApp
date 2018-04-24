# User manual/Käyttöohje

Download file [CalculatorApp-1.0.jar]

## Configuration

In order to run, the program needs to have the configuration file [config.properties](https://github.com/Jsos17/otm-harjoitustyo/blob/master/CalculatorApp/config.properties) in the same directory as the jar file. It defines the name of the SQLite database that the program uses to save expressions for long term. 

The file *config.properties* must be created and the proper line below has to be added to the file before running the program or exceptions may occur and the program might not run.

The format of the file is the following:

    mathDatabase=math.db

## Running the program

The program can be run with the command

    java -jar CalculatorApp-1.0.jar

## Using the CalculatorApp

The user can input any expression by using the provided buttons, and if the expression can be parsed (i.e. brackets, operators, dots etc are placed correctly) provides the result as a double value. If the input cannot be parsed, then the user is notified and asked to correct the expression.

Maximum size for exression is currently set at 1000 characters, which cannot be exceeded.

The app also shows recently used expressions, and the user can change how many, if any, expressions are kept in memory. Note if new memory size is set smaller than current list size of recently used expressions, then expressions will be removed until the list size matches the given memory limit.

The user can also clear the memory list which removes all recently used expressions from the list.

