# Code of exercice 5 : Cyclomatic Complexity

This project implements a Java application that **computes the Cyclomatic Complexity of all methods** in a given Java project. It utilizes the JavaParser to analyze Java source code, producing a comprehensive report of the complexity metrics.

To generate the cyclomatic complexity report (cc_report.csv), you must **provide the path to the source code directory as an argument** when running the Java application. The program will analyze the Java source files in the specified directory and generate a CSV report listing all methods and their corresponding cyclomatic complexity values.

Once the cc_report.csv file is generated, you can visualize the distribution of CC values using the following Python script.


```python
import pandas as pd
import matplotlib.pyplot as plt

def generate_cc_histogram(csv_file):
    df = pd.read_csv(csv_file, sep=',', dtype=str, on_bad_lines='skip')

    df['CC'] = pd.to_numeric(df['CC'])
    cc_values = df['CC'].dropna()

    plt.figure(figsize=(10, 6))
    
    plt.hist(cc_values, bins=range(1, 16), edgecolor='black', color='pink', alpha=0.7)  
    
    plt.title('Histogram of Cyclomatic Complexity Values', fontsize=16)
    plt.xlabel('Cyclomatic Complexity', fontsize=14)
    plt.ylabel('Frequency', fontsize=14)
    plt.xlim(1, 15)
    plt.grid(True)
    plt.show()

csv_file = '/content/drive/MyDrive/ColabNotebooks/cc_report.csv'  
generate_cc_histogram(csv_file)
```

We used the **Apache Commons Collection**s project as the input source code to analyze. After running the Java Parser application, we generated the cc_report.csv file.

Using the Python script above, **we visualized the distribution of Cyclomatic Complexity values across different methods in the project**. The resulting histogram provides insights into the complexity structure of the code, helping to identify potential areas of high complexity.

![histogram](https://github.com/user-attachments/assets/5c2f93f1-5596-4c32-8fb7-eede06eda45e)

It shows that **most methods have low cyclomatic complexity (1-2)**, indicating **simple code**. The **frequency decreases quickly as complexity increases**, with a few methods reaching high complexity values. This suggests that **the project is generally well-structured**, with only a few potentially complex methods that may need attention.
