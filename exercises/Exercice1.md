# TCC *vs* LCC

Explain under which circumstances *Tight Class Cohesion* (TCC) and *Loose Class Cohesion* (LCC) metrics produce the same value for a given Java class. Build an example of such as class and include the code below or find one example in an open-source project from Github and include the link to the class below. Could LCC be lower than TCC for any given class? Explain.

A refresher on TCC and LCC is available in the [course notes](https://oscarlvp.github.io/vandv-classes/#cohesion-graph).

## Answer

- **Tight Class Cohesion** : [TCC measures how well the methods of a class are related to each other](https://dcm.dev/docs/metrics/class/tight-class-cohesion/#:~:text=Measures%20how%20well%20the%20methods%20of%20a%20class%20are%20related%20to%20each%20other.). It focuses strictly on **direct connections** between methods.

- **Loose Class Cohesion** : LCC includes both **direct and indirect connections** between methods.

### Example of a Class Where TCC and LCC Produce the Same Value

```java

public class TestStatistics {
    private int a;   
    private int b; 
    private int c;

    public TestStatistics (int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int calculateSum() {
        return a + b;
    }

    public int calculateProduct() {
        return b * c; 
    }

    public int calculateDifference() {
        return a - c;
    }

    public int calculateTotal() {
        return a + b + c; 
    }
}

```
**Connections between methods**

- calculateSum and calculateProduct → Connected (by b)
- calculateSum and calculateDifference → Connected (by a)
- calculateSum and calculateTotal → Connected (by a and b)
- calculateProduct and calculateDifference → Connected (by c)
- calculateProduct and calculateTotal → Connected (by b and c)
- calculateDifference and calculateTotal → Connected (by a and c)

The figure below shows the graph that results from the class TestStatistics.

![Connections between methods](https://github.com/user-attachments/assets/59504cb4-423f-48e1-a3b9-1ca8a01c3e84)

**TCC :**

- **Nshared**: The number of method pairs that share at least one instance variable.
- **Npairs**: The total number of method pairs in the class.
- **Nconnected**: The number of method pairs that are either directly or indirectly connected.
  
  $$
  TCC = \frac{N_{\text{shared}}}{N_{\text{pairs}}} = \frac{6}{6} = 1
  $$

**LCC :** (all nodes are connected)

  $$
  LCC =\frac{N_{\text{connected}}}{N_{\text{pairs}}} = \frac{6}{6} = 1 
  $$

### Could LCC be lower than TCC for any given class ?

**LCC can never be lower than TCC** because LCC accounts for both **direct and indirect connections** between methods, while TCC considers **only direct connections**.

- If there are **no indirect connections**, TCC = LCC.
- However, if **indirect connections exist**, LCC ≥ TCC.

Thus, LCC is always at least equal to TCC.





