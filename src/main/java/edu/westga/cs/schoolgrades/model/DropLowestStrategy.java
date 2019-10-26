package edu.westga.cs.schoolgrades.model;

import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class DropLowestStrategy implements GradeCalculationStrategy
{
    private GradeCalculationStrategy childStrategy;
    
    public DropLowestStrategy(final GradeCalculationStrategy childStrategy) {
        if (childStrategy == null) {
            throw new IllegalArgumentException("childStrategy can not be null");
        }
        this.childStrategy = childStrategy;
    }
    
    @Override
    public double calculate(final List<Grade> grades) {
        if (grades == null) {
            throw new IllegalArgumentException("grades may not be null");
        }
        if (grades.size() < 2) {
            return this.childStrategy.calculate(grades);
        }
        final List<Grade> withLowestRemoved = this.dropLowestFrom(grades);
        return this.childStrategy.calculate(withLowestRemoved);
    }
    
    private List<Grade> dropLowestFrom(final List<Grade> grades) {
        Grade lowest = grades.get(0);
        for (final Grade grade : grades) {
            if (lowest.getValue() > grade.getValue()) {
                lowest = grade;
            }
        }
        final List<Grade> result = new ArrayList<Grade>(grades);
        result.remove(lowest);
        return result;
    }
}
