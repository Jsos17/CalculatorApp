# Ohjelmistotekniikan menetelmät project work: CalculatorApp

Here is the **README-file** for the project work **CalculatorApp**. This project is part of the course *Ohjelmistotekniikan menetelmät (Software engineering methods (unofficial translation))*

## Documentation

[Vaatimusmäärittely/Software requirement specification](https://github.com/Jsos17/otm-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

[Työaikakirjanpito/Work log](https://github.com/Jsos17/otm-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)

[Arkkitehtuuri/Software architecture](https://github.com/Jsos17/otm-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

[Käyttöohje/User manual](https://github.com/Jsos17/otm-harjoitustyo/blob/master/dokumentointi/kayttoohje.md)

## Command line

**Testing**

Tests can be run with the command:

    mvn test

Test coverage report can be run with the command:

    mvn jacoco:report


**Checkstyle**

Style checks that are specified in the file [checkstyle.xml](https://github.com/Jsos17/otm-harjoitustyo/blob/master/CalculatorApp/checkstyle.xml) can be run with the command:

    mvn jxr:jxr checkstyle:checkstyle
