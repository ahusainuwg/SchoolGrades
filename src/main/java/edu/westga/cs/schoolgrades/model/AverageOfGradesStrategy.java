package edu.westga.cs.schoolgrades.model;

import java.util.List;

public class AverageOfGradesStrategy extends SumOfGradesStrategy{
	 @Override
	    public double calculate(final List<Grade> grades) {
	        if (grades == null) {
	            throw new IllegalArgumentException("grades list can not be null");
	        }
	        if (grades.isEmpty()) {
	            return 0.0;
	        }
	        final double sum = super.calculate(grades);
	        return sum / grades.size();
	    }

}
