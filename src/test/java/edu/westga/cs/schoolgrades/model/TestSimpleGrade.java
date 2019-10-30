package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class TestSimpleGrade {
	
	SimpleGrade simpleGrade = new SimpleGrade(40);
	
	@BeforeEach
	public void setUp() {
		simpleGrade = new SimpleGrade(40);

	}

	@Test()
	public void shouldSetValue() {
		simpleGrade.setValue(40);
		assertEquals(40,simpleGrade.getValue());

	}
	@Test()
	public void shouldNotAllowNegativeValues() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> simpleGrade.setValue(-4));

	}

}
