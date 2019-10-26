package edu.westga.cs.schoolgrades.model;

import java.util.List;

public interface GradeCalculationStrategy
{
    double calculate(final List<Grade> p0);
}
