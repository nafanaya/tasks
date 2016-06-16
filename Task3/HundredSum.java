import java.math.BigInteger;

public class HundredSum {
	/* find factorial of  number*/ 
	public static BigInteger factorial(int n)
	{
		BigInteger fact  = BigInteger.ONE; 
		//i use BigInteger, because 100!  it's a very big number
		for(int i = 2; i<=n; i++)
		{
			fact = BigInteger.valueOf(i).multiply(fact);
		}
		return fact; 
	}
	/* find sum of the digits*/
	public static int sumD(BigInteger b)
	{
		int sum = 0; 
		BigInteger numberAll = b; // for the integer part of number

		int numberRest = 0; // for the remainder of number
		
		while(!numberAll.equals(BigInteger.ZERO)) 
		{
			// find the remainder of number
			numberRest = numberAll.remainder(BigInteger.TEN).intValue(); 
			
			// find the integer part of number
			numberAll = numberAll.divide(BigInteger.TEN);
			
			// count the sum of digits
			sum += numberRest;
		}
		return sum;
	}
	
	public static void main(String [] args)
	{
		//print the answer to the console
		System.out.println(sumD(factorial(100)));
	}
}
