
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShortWay {
	
	static String [] cityArr;// array for list of the cities
	static int[][] costCity;// array for cost between  two cities
	
	int t; //the number of tests
	static 	int n; //the number  of city NAME
	int p; //the number of neighbors of city NAME  
	int r;//the number of paths to find 
	
	Scanner scanner = new Scanner(System.in);
	
	/*read  and check data from console*/
	public void fillData() 
	{
		Pattern pattern = Pattern.compile("[a-z]+");// pattern for check name of the city
		
		System.out.println("Enter the number of cities");
		n = scanner.nextInt();
	
		if((n<=10000)&& (n>0)) // check input number
		{
			cityArr = new String[n+1];//initialization of array with size n
			costCity = new int[n+1][n+1]; //initialization of array with size n
			
			for(int j = 1; j<=n; j++)//cycle for input data about the city
			{
				System.out.println("Enter the name of city");
				String name = "";
				name = scanner.next();
				
				Matcher matcher = pattern.matcher(name);//for interpreting pattern on name  
				
				if((name.length() <=10) &&(name.length() >0) && (matcher.matches()))//check input name
				{
					cityArr[j] = name;// write name in the array of cities
				
					System.out.println("Enter the number of the neighbors");
					p = scanner.nextInt();
				
					if((p<n) && (p>0))// check input number
					{
						for(int k = 0; k<=p-1; k++)
						{
							System.out.println("Enter the index of a city connected to NAME and the cost");
							int indN = scanner.nextInt(); // id of the neighbor
							if((indN<n+1) && (indN>0)) // check input number
							{
								int costN = scanner.nextInt(); // cost of the cities
								if((costN<200000) && (costN>0)) // check input number
								{
									costCity[j][indN]= costN;// fill the matrix cost between two cities
								}
								else
								{
									System.out.println("Inadmissible number");
								}
							}	
							else
							{
								System.out.println("Inadmissible number");
								break;
							}
						}
					}
					else
					{
						System.out.println("Inadmissible number");
					}
				}
				else
				{
					System.out.println("Inadmissible name");
					break;
				}
			}
		}
		else
		{
			System.out.println("Inadmissible number");
		}
	
	}
	
	/*launch the tests, call all need methods, find short path between two cities*/
	public  void testData() 
	{                       
		System.out.println("Enter the number of tests");
		t = scanner.nextInt();
		
		if((t<=10) && (t>0))// check input number
		{
			for(int i = 0; i<=t-1; i++)
			{
				fillData();// call the method that read input data from keyboard
				
				costCity= fillMatrix(costCity);// call the method that fill matrix with the rules for use Floyd-Warshall's algorithm
				costCity = algorithmFlW(costCity);//call the method that use Floyd-Warshall's algorithm
				
//------------Enter need pairs and find short paths-----------------------------------------------
				System.out.println("Enter the number of paths to find ");
				r = scanner.nextInt(); 
				if((r<=100) && (r>0)) // check input number
				{
					ArrayList<int[]> pairsCity = new ArrayList<int[]>();//for index pairs of city
						
					for(int k = 0; k<=r-1; k++)
					{
						System.out.println("Enter the names of cities (from NAME1 to NAME2) ");
						String cityS = scanner.next(); // for city source
						String cityE = scanner.next(); // for city destination
							
						int [] tempInd = new int[2];// for index one pair of cities
							
						for(int j = 1; j<=n; j++)
						{
							if(cityArr[j].equals(cityS))// find the index of city - source
							{
								tempInd[0] = j;//index of city - source
							}
							if(cityArr[j].equals(cityE))// find the index of city - destination
							{
								tempInd[1] = j;//index of city - destination
							}
						}
						
						pairsCity.add(tempInd);// add pair of cities to the list
					}
					
					System.out.println("---------------OUTPUT----------------------");
					int [][] shortCost = algorithmFlW(costCity); 
					for (int [] listPair: pairsCity)
					{
						System.out.println(shortCost[listPair[0]][listPair[1]]);
					}
				}
				else
				{
					System.out.println("Inadmissible number");
				}
				
				System.out.println();
			}
		}
		else 
		{
			System.out.println("Inadmissible number");
		}
	}
/*fill matrix with rules that 1) cost from city to himself == 0  
 * 2) if edge isn't between two cities cost == very big number */
	public int [][] fillMatrix(int [][] d)  
	{										 
		for(int i = 1; i <=n; i++)
		{
			for(int j=1; j<=n; j++)
			{
				
				if(i==j)
				{
					d[i][j] =0;//1)
				}
				else if(d[i][j] ==0)
				{
					d[i][j] =200001;//2)
				}
			}
		}
		return d;
		
	}

	/*use Floyd-Warshall's algorithm for find short path*/
	public int [][] algorithmFlW(int [][] gr)
	{
		for (int k=1; k<=n; k++)
			for (int i=1; i<=n; i++)
				for (int j=1; j<=n; j++)
			    	if(gr[i][j] > gr[i][k] + gr[k][j])  
			    		gr[i][j] = gr[i][k] + gr[k][j];
		return gr;
	}
	
	public static void main(String [] args)
	{
		new ShortWay().testData();
	}
		
}
