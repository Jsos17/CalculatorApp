# Ohjelmistotekniikan menetelmät project work: CalculatorApp

[![Build Status](https://travis-ci.com/Jsos17/CalculatorApp.svg?branch=master)](https://travis-ci.com/Jsos17/CalculatorApp)   [![codecov](https://codecov.io/gh/Jsos17/CalculatorApp/branch/master/graph/badge.svg)](https://codecov.io/gh/Jsos17/CalculatorApp)



Here is the **README-file** for the project work **CalculatorApp**. This project is part of the course *Ohjelmistotekniikan menetelmät (Software engineering methods (unofficial translation))*

The CalculatorApp is, as the name suggests, a calculator. See [user manual](https://github.com/Jsos17/CalculatorApp/blob/master/dokumentointi/kayttoohje.md) for what operations are supported. The CalculatorApp uses double precision. Therefore, it is intended mainly to be a demonstration of certain programming skills, and is not suited for calculations requiring absolute precision.

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

Test coverage report can be run with the command:

    mvn jacoco:report

And it can be examined by opening the file *target/site/jacoco/index.html* in the chromium-browser for example.

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
