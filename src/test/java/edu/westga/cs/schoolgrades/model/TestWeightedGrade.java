package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class TestWeightedGrade {
	
	@Test()
	public void shouldAddWeightedGrade() {
		WeightedGrade weightedGrade = new  WeightedGrade(new SimpleGrade(40), 1);
		assertEquals(40,weightedGrade.getValue());
	}
	
	@Test()
	public void shouldNotAllowNullGrade() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> new  WeightedGrade(null, 10));

	}
	@Test()
	public void shouldNotAllowIncorrectWeight() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> new  WeightedGrade(new SimpleGrade(40), 10));

	}

}
