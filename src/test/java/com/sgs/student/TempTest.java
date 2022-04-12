package com.sgs.student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TempTest {
	
	
	@Test
	public void testFactorial()
	{
			assertEquals(Temp.factorial(5),120);
	}
	
	@Test
	public void testFactorial6()
	{
			assertEquals(Temp.factorial(6),720);
	}
	@Test
	public void testFactorial4()
	{
			assertEquals(Temp.factorial(4),24);
	}
	@Test
	public void testFactorial2()
	{
			assertEquals(Temp.factorial(2),2);
	}
	@Test
	public void testFactorial3()
	{
			assertEquals(Temp.factorial(3),6);
	}

}
