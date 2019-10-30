package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TestDropLowestStrategy {
	GradeCalculationStrategy childStrategy = new SumOfGradesStrategy();
	DropLowestStrategy strategy = new DropLowestStrategy(childStrategy);
	
	@Test()
	public void shouldCalculateGradesForMoreThanOneGrade() {
		List<Grade> childGrades = new  ArrayList<Grade>();
		SimpleGrade grade4 = new SimpleGrade(10);
		childGrades.add(grade4);
		Double sum = strategy.calculate(childGrades);
		assertEquals(10,sum);
		
	}
	
	@Test()
	public void shouldCalculateForOneGrade() {
		
		Double sum = strategy.calculate(populateGradeList());
		assertEquals(100,sum);
		
	}
	@Test()
	public void shouldNotAllowNullGrades() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> strategy.calculate(null));

	}
	private List<Grade> populateGradeList(){
		List<Grade> childGrades = new  ArrayList<Grade>();
		SimpleGrade grade1 = new SimpleGrade(40);
		SimpleGrade grade2 = new SimpleGrade(40);
		SimpleGrade grade3 = new SimpleGrade(20);
		SimpleGrade grade4 = new SimpleGrade(10);
		childGrades.add(0,grade1);
		childGrades.add(1,grade2);
		childGrades.add(2,grade3);
		childGrades.add(3,grade4);
		return childGrades;
	}

}
