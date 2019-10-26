package edu.westga.cs.schoolgrades.model;

public class SimpleGrade implements Grade
{
    private double value;
    
    public SimpleGrade(final double value) {
        this.setValue(value);
    }
    
    @Override
    public double getValue() {
        return this.value;
    }
    
    public void setValue(final double value) {
        if (value < 0.0) {
            throw new IllegalArgumentException("value should not be < 0");
        }
        this.value = value;
    }
}

