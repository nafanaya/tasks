import java.math.BigInteger;

public class RightBrackets {
	
	/* problem with the parentheses we can solve with Catalan's numbers
	 * i use non-recursively formula (2n)!/(n!(n+1)!)*/
	public static BigInteger numbersCatalan(int n)
	{
		return factorial(2*n).divide(factorial(n).multiply(factorial(n+1)));
	}
	
	/* find factorial of  number*/ 
	public static BigInteger factorial(int n)
	{
		if(n == 0) return BigInteger.ZERO;
		if(n == 1) return BigInteger.ONE;
		BigInteger fact = BigInteger.ONE;
		
		for(int i = 2; i<=n; i++)
		{
			fact = BigInteger.valueOf(i).multiply(fact);
		}
		
		return fact; 
	}
	
	public static void main(String[] args)
	{
		System.out.println(numbersCatalan(5));
	}
	

}
