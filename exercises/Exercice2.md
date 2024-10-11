
# Using PMD


Pick a Java project from Github (see the [instructions](../sujet.md) for suggestions). Run PMD on its source code using any ruleset (see the [pmd install instruction](./pmd-help.md)). Describe below an issue found by PMD that you think should be solved (true positive) and include below the changes you would add to the source code. Describe below an issue found by PMD that is not worth solving (false positive). Explain why you would not solve this issue.


## Answer

We chose the **Apache Commons Collections** project and used PMD to analyze its source code. 
To perform the analysis, we ran the following PMD command:

``` bash
pmd check -f text -R rulesets/java/quickstart.xml -d "...\commons-collections-master" -r "....\ResultEx2.txt"
```

This command generated a report (`ResultEx2.txt`) containing the issues identified by PMD, using the predefined ruleset from rulesets/java/quickstart.xml. 

### True Positive :

- **Violation**: SimplifyBooleanReturns, **Location**: SplitMapUtils.java:162
- PMD detected an unnecessarily complex **if-else block** used to return a boolean value, which can be simplified.

```java
if (condition) {
    return true;
} else {
    return false;
}
```

- Suggested Change:
  
```
return condition;
```

-> Simplifying the boolean return logic removes redundancy, making the code easier to read and maintain while keeping the method's functionality unchanged, following clean code practices.

### False Positive :

- **Violation** : UnusedPrivateMethod, **Location** : SparseBloomFilter.java:62
- PMD identified **an unused private method** in the code.
  
-> The unused method could be part of an upcoming feature or refactoring plan. Removing it prematurely would create extra work if it needs to be reintroduced later. Since it does not negatively impact the code or its performance, there is no immediate need to remove it.











