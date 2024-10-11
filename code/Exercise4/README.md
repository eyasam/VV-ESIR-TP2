# Code of exercice 4 : NO getter!

This project is a Java code analyzer that identifies private fields in public classes that do not have associated getter methods. 

Project Structure :
- `PrivateCheck.java` : Collects information about private fields without getters in public classes.
- `ReportGenerator.java`: Generates a CSV report based on the data provided by PrivateCheck.
- `Main.java`
- `PublicElementsPrinter.java`
  
! You must provide **the path to the source code directory** as an **argument** when running the program. 

The program will analyze the Java source files and **generate a report.csv file** listing all private fields without getters.
