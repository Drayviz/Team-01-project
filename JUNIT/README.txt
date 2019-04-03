To run the junit, 
=========
1. put the latest version of the file in a text environment along with the junit and two package 
==========
2.Change the directory of your console to the folder then enter the command
==========
3.javac -cp .;junit-4.12.jar;hamcrest-core-1.3.jar *.java

or javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar *.java (if youre on a unix system)
==========
4. after that, run

java -cp .;junit-4.12.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore FatTest

or java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore FatTest (if on unix system)
====