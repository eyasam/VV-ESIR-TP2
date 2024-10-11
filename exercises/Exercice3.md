# Extending PMD

Use XPath to define a new rule for PMD to prevent complex code. The rule should detect the use of three or more nested `if` statements in Java programs so it can detect patterns like the following:

```Java
if (...) {
    ...
    if (...) {
        ...
        if (...) {
            ....
        }
    }

}
```
Notice that the nested `if`s may not be direct children of the outer `if`s. They may be written, for example, inside a `for` loop or any other statement.
Write below the XML definition of your rule.

You can find more information on extending PMD in the following link: https://pmd.github.io/latest/pmd_userdocs_extending_writing_rules_intro.html, as well as help for using `pmd-designer` [here](./designer-help.md).

Use your rule with different projects and describe you findings below. See the [instructions](../sujet.md) for suggestions on the projects to use.

## Answer

After installing a compatible version of JavaFX and executing the command 
```pmd.bat designer```, we developed a custom rule for PMD aimed at detecting complex **nested if statements (3 times at least)** in Java code using XPath. 

The rule is defined as follows, containing the following XPath expression ```//IfStatement[.//IfStatement[.//IfStatement]]```:

```xml
<?xml version="1.0"?>
<ruleset name="Custom Rules"
    xmlns="http://pmd.sourceforge.net/ruleset/2.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://pmd.sourceforge.net/ruleset/2.0.0 https://pmd.sourceforge.io/ruleset_2_0_0.xsd">

    <description>
        My custom rules
    </description>

    <rule name="NestedIf" 
          language="java"
          message="Avoid complex nested if statements" 
          class="net.sourceforge.pmd.lang.rule.xpath.XPathRule">
        <description></description>
        <priority>3</priority>
        <properties>
            <property name="xpath">
                <value>
<![CDATA[
//IfStatement[.//IfStatement[.//IfStatement]]
                ]]>
                </value>
            </property>
        </properties>
    </rule>
</ruleset>
```

To apply this rule to the chosen Apache Commons Collections project, we used the following command:
```bash
pmd check -f text -R "..\pmd_rule.xml" -d "..\commons-collections-master" -r "..\ResultEx3.txt"
```
In this command, the rule is defined in the file named **pmd_rule.xml**, and the results of the PMD static analysis are stored in a file named **ResultEx3.txt**.

### Analysis Results :
Upon running the analysis, we encountered the following result:
``` txt
..\commons-collections-master\src\main\java\org\apache\commons\collections4\bidimap\TreeBidiMap.java:1158:	NestedIf :	Avoid complex nested if statements
```
The relevant code can be found [here TreeBidiMap.java](https://github.com/apache/commons-collections/blob/master/src/main/java/org/apache/commons/collections4/bidimap/TreeBidiMap.java) at line 1158.

The following code capture demonstrates the implications of the three nested if statements in the code:

<img width="514" alt="img" src="https://github.com/user-attachments/assets/3a6f6581-42a8-4c18-9e39-2085a7d8652b">
