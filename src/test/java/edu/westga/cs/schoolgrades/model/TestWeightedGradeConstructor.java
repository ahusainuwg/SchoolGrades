package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;

public class TestWeightedGradeConstructor {
	
	@Test
	public void shouldNotAllowNullGrade() {
		assertThrows(IllegalArgumentException.class, () ->{ 
			new WeightedGrade(null, 10.0);
		});
	}
	
	@Test
	public void shouldNotAllowNegativeWeight() {
		assertThrows(IllegalArgumentException.class, () ->{ 
			new WeightedGrade(null, -10.0);
		});
	}
	
	@Test
	public void shouldNotAllowWeightGreaterThanOne() {
		System.out.println("running test with mock");
		assertThrows(IllegalArgumentException.class, () ->{ 
			new WeightedGrade(mock(Grade.class), 10.0);
		});
	}
	
	@Test
	public void shouldAllowCorrectWeight() {
		WeightedGrade weightedGrade = new WeightedGrade(mock(Grade.class), 1.0);
		assertEquals(0, weightedGrade.getValue(), 1.0);
	
	}
}
