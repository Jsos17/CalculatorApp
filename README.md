# Software engineering project work: CalculatorApp

[![Build Status](https://travis-ci.com/Jsos17/CalculatorApp.svg?branch=master)](https://travis-ci.com/Jsos17/CalculatorApp)   [![codecov](https://codecov.io/gh/Jsos17/CalculatorApp/branch/master/graph/badge.svg)](https://codecov.io/gh/Jsos17/CalculatorApp) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/0f0b3e2add074934912c4ec280ce04de)](https://www.codacy.com/app/Jsos17/CalculatorApp?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Jsos17/CalculatorApp&amp;utm_campaign=Badge_Grade)

This programming project was part of the University of Helsinki course *Ohjelmistotekniikan menetelmät (Software engineering methods (unofficial translation))*. It was done between late March and early May of the year 2018. Since then, some modest refactoring has been implemented, but the bulk of the project was done during those March, April and May months of 2018. 

This was my first solo programming project, after I had learned to program with Java during the previous autumn. While I was working on this project, I was also taking the course Data Structures and Algorithms. Therefore, an overzealous obsession with algorithmic efficiency can undoubtedly be seen in the code, and also in the documentation. Consequently, very little thought was given to how easy and fun it would be to use the graphical user interface. If I were to do this project today, it would probably be the number one priority.

The CalculatorApp is, as the name suggests, a calculator. See [user manual](https://github.com/Jsos17/CalculatorApp/blob/master/dokumentointi/kayttoohje.md) for what operations are supported. The CalculatorApp uses double precision. Therefore, it is intended mainly to be a demonstration of certain programming skills, and is not suited for calculations requiring absolute precision.

The mathematical expressions are processed with my own implementation of Edsger Djikstra's [Shunting-Yard Algorithm](https://en.wikipedia.org/wiki/Shunting-yard_algorithm).

The user can input any expression by using the provided buttons, and if the expression can be parsed (i.e. brackets, operators, dots etc are placed correctly) provides the result as double value. If the input cannot be parsed, then the user is notified and is asked to correct the expression.

The app also shows recently used expressions, and the user can change how many, if any, expressions are kept in memory. 

The user can save expressions in a database and re-use them later.

## Documentation

[Käyttöohje/User manual](https://github.com/Jsos17/CalculatorApp/blob/master/dokumentointi/kayttoohje.md)

[Vaatimusmäärittely/Software requirement specification](https://github.com/Jsos17/CalculatorApp/blob/master/dokumentointi/vaatimusmaarittely.md)

[Testausdokumentti/Testing document](https://github.com/Jsos17/CalculatorApp/blob/master/dokumentointi/testaus.md)

[Arkkitehtuurikuvaus/Software architecture](https://github.com/Jsos17/CalculatorApp/blob/master/dokumentointi/arkkitehtuuri.md)

[Työaikakirjanpito/Work log](https://github.com/Jsos17/CalculatorApp/blob/master/dokumentointi/tyoaikakirjanpito.md)


## Releases

[Viikko 5](https://github.com/Jsos17/CalculatorApp/releases/tag/viikko5)

[Viikko 6](https://github.com/Jsos17/CalculatorApp/releases/tag/v2.0)

[loppupalautus](https://github.com/Jsos17/CalculatorApp/releases/tag/v3.1)

## Command line

### Testing

Tests can be run with the command:

    mvn test

### Generating an executable jar-file

    mvn package

Generates an executable jar-file, *CalculatorApp-1.0-SNAPSHOT.jar*, inside the target directory. 

### JavaDoc

JavaDoc is generated with the command

    mvn javadoc:javadoc

JavaDoc can be examined by opening the file *target/site/apidocs/index.html* with the chromium-browser for example. 

### Checkstyle

Style checks that are specified in the file [checkstyle.xml](https://github.com/Jsos17/CalculatorApp/blob/master/CalculatorApp/checkstyle.xml) can be run with the command:

    mvn jxr:jxr checkstyle:checkstyle

Errors, if any, can be examined by opening the file *target/site/checkstyle.html*.
