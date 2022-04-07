package com.sgs.student;

public class Temp {
	
	public static int factorial(int n)
	{
		if(n==1)
			return 0;
		else
			return n*factorial(n-1);
	}

}
