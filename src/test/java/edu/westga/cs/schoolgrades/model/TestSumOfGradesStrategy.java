package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TestSumOfGradesStrategy {
	
	GradeCalculationStrategy strategy = new SumOfGradesStrategy();
	
	@Test()
	public void shouldAddAllGrades() {
		Double sum = strategy.calculate(populateGradeList());
		assertEquals(120,sum);
	
	}
	private List<Grade> populateGradeList(){
		List<Grade> childGrades = new  ArrayList<Grade>();
		SimpleGrade grade1 = new SimpleGrade(40);
		SimpleGrade grade2 = new SimpleGrade(40);
		SimpleGrade grade3 = new SimpleGrade(20);
		SimpleGrade grade4 = new SimpleGrade(20);
		childGrades.add(0,grade1);
		childGrades.add(1,grade2);
		childGrades.add(2,grade3);
		childGrades.add(3,grade4);
		return childGrades;
	}
	
	@Test()
	public void shouldNotAllowNullGrades() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> strategy.calculate(null));

	}
	

}
