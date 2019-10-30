package edu.westga.cs.schoolgrades.model;

import java.util.List;

public class SumOfGradesStrategy implements GradeCalculationStrategy
{
    @Override
    public double calculate(final List<Grade> grades) {
        if (grades == null) {
            throw new IllegalArgumentException("grades should not be null");
        }
        double sum = 0.0;
        for (final Grade grade : grades) {
            sum += grade.getValue();
        }
        return sum;
    }
}
