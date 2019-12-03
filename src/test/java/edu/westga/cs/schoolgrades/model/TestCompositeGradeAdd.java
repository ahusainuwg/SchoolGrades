package edu.westga.cs.schoolgrades.model;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCompositeGradeAdd {

	private CompositeGrade composite;
	private Grade grade0;
	private Grade grade1;
	private Grade grade2;
	
	@BeforeEach
	public void setup() {
		composite = new CompositeGrade(mock(GradeCalculationStrategy.class));
		grade0 = mock(Grade.class);
		grade1 = mock(Grade.class);
		grade2 = mock(Grade.class);
		when(grade0.getValue()).thenReturn(10.00);
		when(grade1.getValue()).thenReturn(20.00);
		when(grade2.getValue()).thenReturn(30.00);
	}
	
	@Test
	public void canAddOneGrade() {
		composite.add(grade0);
		List<Grade> grades = composite.getGrades();
		assertEquals(1, grades.size());
		assertEquals(grade0, grades.get(0));
	}

	@Test
	public void canAddManyGrades() {
		composite.add(grade0);
		composite.add(grade1);
		composite.add(grade2);
		List<Grade> grades = composite.getGrades();
		assertEquals(3, grades.size());
		assertEquals(grade0, grades.get(0));
		assertEquals(grade1, grades.get(1));
		assertEquals(grade2, grades.get(2));
	}
	
	@Test
	public void shouldNotAddSameGradeTwice() {
		composite.add(grade0);
		
		assertThrows(IllegalArgumentException.class, () ->{ 
			composite.add(grade0);
		});
	}
	
	@Test
	public void shouldNotAddNullGrade() {
		assertThrows(IllegalArgumentException.class, () ->{ 
			composite.add(null);
		});
	}
}
