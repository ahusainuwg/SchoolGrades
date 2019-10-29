package edu.westga.cs.schoolgrades.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompositeGrade implements Grade
{
    private final GradeCalculationStrategy strategy;
    private final List<Grade> childGrades;
    
    public CompositeGrade(final GradeCalculationStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("strategy must not be null");
        }
        this.strategy = strategy;
        this.childGrades = new ArrayList<Grade>();
    }
    
    public void add(final Grade grade) {
        if (grade == null) {
            throw new IllegalArgumentException("grade can not be null");
        }
        if (this.childGrades.contains(grade)) {
            throw new IllegalArgumentException("can not add the same grade twice");
        }
        this.childGrades.add(grade);
    }
    
    public List<Grade> getGrades() {
        return Collections.unmodifiableList((List<? extends Grade>)this.childGrades);
    }
    
    @Override
    public double getValue() {
        return this.strategy.calculate(this.childGrades);
    }
    
    public void addAll(final List<? extends Grade> grades) {
        if (grades == null) {
            throw new IllegalArgumentException("grades can not be null");
        }
        for (final Grade grade : grades) {
            this.add(grade);
        }
    }
}
