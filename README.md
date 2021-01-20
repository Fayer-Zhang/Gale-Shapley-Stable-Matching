# Gale-Shapley-Stable-Matching

This Gale-Shapley algorithm is to solve the problem of matching coop employers to coop students.

### Key Words
Stack, Priority Queue

### Example
Given a list of employers:
```
0. Thales
1. Canada Post
2. Cisco
```

Given a list of students:
```
0. Olivia
1. Jackson
2. Sophia
```

The two list will always be of the same size.

Students and employers will enter their ranking. For example:
* Employer preferences
```
0. Thales:      0.Olivia, 1.Jackson, 2.Sophia
1. Canada Post: 2.Sophia, 1.Jackson, 0.Olivia
2. Cisco:       0.Olivia, 2.Sophia, 1.Jackson
```

* Student preferences
```
0. Olivia:   0.Thales, 1.Canada Post, 2.Cisco
1. Jackson:  0.Thales, 1.Canada Post, 2.Cisco
2. Sophia:   2.Cisco, 0.Thales, 1.Canada Post
```

A stable matching solution for this example is
```
Thales - Olivia
Canada Post - Jackson
Cisco - Sophia
```

### Input and Ouput
A text file will be given as the input. The first line is the number of students and employers, followed by the list of employers, the list of students and a matrix of ranking pairs (employer ranking, student ranking). Employers correspond to the rows of this matrix and student correspond to columns. For the example given on above, the input file will be as follows:
```
3
Thales
Canada Post
Cisco
Olivia
Jackson
Sophia
1,1 2,1 3,2
3,2 2,2 1,3
1,3 3,3 2,1
```

The program will produce a text file as the output:
``` 
Match 0: Thales - Olivia
Match 1: Canada Post - Jackson
Match 2: Cisco - Sophia
```

More details in Description.pdf
