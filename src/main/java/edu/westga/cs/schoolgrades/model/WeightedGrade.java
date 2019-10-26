package edu.westga.cs.schoolgrades.model;

public class WeightedGrade implements Grade
{
    private final Grade grade;
    private final double weight;
    
    public WeightedGrade(final Grade decoratedGrade, final double weight) {
        if (decoratedGrade == null) {
            throw new IllegalArgumentException("grade should not be null");
        }
        if (weight < 0.0 || weight > 1.0) {
            throw new IllegalArgumentException("weight must be between 0.0 and 1.0");
        }
        this.grade = decoratedGrade;
        this.weight = weight;
    }
    
    @Override
    public double getValue() {
        return this.grade.getValue() * this.weight;
    }
}
