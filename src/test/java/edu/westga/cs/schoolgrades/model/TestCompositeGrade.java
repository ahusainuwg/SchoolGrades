package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class TestCompositeGrade {
	GradeCalculationStrategy strategy = new SumOfGradesStrategy();
	CompositeGrade compositeGrade = new CompositeGrade(strategy);

	@Test()
	public void shouldAddGrade() {
		compositeGrade.add(new SimpleGrade(40));
		assertEquals(40,compositeGrade.getValue());

	}
	
	@Test()
	public void shouldAddAllGrades() {
		
		compositeGrade.addAll(populateGradeList());
		assertEquals(4,compositeGrade.getGrades().size());
		List<Grade> childGrades = compositeGrade.getGrades();
		assertEquals(40,childGrades.get(0).getValue());
		assertEquals(40,childGrades.get(1).getValue());
		assertEquals(20,childGrades.get(2).getValue());
		assertEquals(20,childGrades.get(3).getValue());
	}
	@Test()
	public void shouldNotAllowNullGrades() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> compositeGrade.addAll(null));

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

}
