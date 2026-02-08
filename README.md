# Gaussian Elimination

A Java implementation of Gaussian elimination with partial pivoting for solving systems of linear equations. Transforms an augmented matrix to row echelon form, then applies back substitution to find the solution.

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)

## How It Works

The solver takes an augmented matrix representing a system of linear equations and applies two steps:

1. **Row Echelon Form** — Forward elimination with partial pivoting (selects the largest absolute value as pivot to improve numerical stability)
2. **Back Substitution** — Reduces the matrix to reduced row echelon form, yielding the solution

The implementation handles singular matrix detection and validates matrix dimensions.

## Usage

Pass the augmented matrix as a space-separated string of comma-separated rows:

```bash
javac -d out linear/EquationSolver.java linear/algebra/GaussianElimination.java
java -cp out linear.EquationSolver "2,1,-1,8 -3,-1,2,-11 -2,1,2,-3"
```

Output:
```
Input matrix:
+2.0x+1.0y-1.0z=8.0
-3.0x-1.0y+2.0z=-11.0
-2.0x+1.0y+2.0z=-3.0

Matrix in row echelon form:
+1.0x+0.33y-0.67z=2.67
+0.0x+1.0y+0.4z=2.6
+0.0x+0.0y+1.0z=-1.0

Matrix after back substitution:
+1.0x+0.0y+0.0z=2.0
+0.0x+1.0y+0.0z=3.0
+0.0x+0.0y+1.0z=-1.0
```

Requires Java 8+.

## Testing

The `EquationSolver-Tests/` directory contains JUnit 5 tests covering matrix operations, structure validation, and edge cases.

## Author

**Isroilbek Jamolov** — [GitHub](https://github.com/Jamolov-Isroilbek)
