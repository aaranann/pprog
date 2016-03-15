import java.util.Arrays;
import java.util.Scanner;

public class UberDieMittel 
{
	public static void main(String[] args) 
	{
		

	}
 public static int [] maxsubarray(int [] a)
 {
	/* if (a.length%2==1)
	 {
		 int [] l_a= new int [a.length/2];
		 int [] r_a= new int [a.length/2+1];
	
	 }
	 else 
	 {
		 int [] l_a= new int [a.length/2];
		 int [] r_a= new int [a.length/2];
	 }
	 */
	 
	 int b = 0;
	 if (a.length%2==1)	  b=a.length/2+1;
	 else b=a.length/2;
	 
	 int [] l_a= new int [b];
	 int [] r_a= new int [a.length/2];
	 
	 return maxsubarray(l_a);
	 //maxsubarray(r_a);

	
	
 }
}
