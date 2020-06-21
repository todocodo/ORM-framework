package question1;

import java.util.Scanner;
import java.util.StringTokenizer;
/***
 * A programme for calculating derivative of a function
 * 
 * @author Todor Ranchev
 *
 */
public class Derivatives {

	private static StringBuffer buffer = new StringBuffer();
	
	private static char xCount = 0;
	private static int intCountBeforePower = 0;
	private static int intCountAfterPower = 0;
	private static char powerCount = 0;
	private static char sign = 0;	
	
	/***
	 * This method is to separate every char in a function 
	 * 
	 * @param the function that we write from the console
	 */
	public static void derivative(String arr) {
		StringTokenizer st = new StringTokenizer(arr);
		
		while(st.hasMoreTokens()) {
			 String input = st.nextToken();
			 //I used this if we have more that one digit respectively before and after the power
			 boolean secondDigit = false;
			 boolean secondDigitPower = false;

			 for (int i = 0; i < input.length(); i++) {
			
					char element = input.charAt(i);
					
					if(element >= '0' && element < '9' && powerCount == 0) {
						if(secondDigit) {
							String num = intCountBeforePower + String.valueOf(element);
							intCountBeforePower = Integer.parseInt(num);
						} else {
							intCountBeforePower = Character.getNumericValue(element);
							secondDigit = true;
						}
					} else if (element == 'x') {
						xCount = element;
					} else if (element == '^') {
						powerCount = element;
					} else if (element >= '0' && element < '9' && powerCount != 0) {
						if(secondDigitPower) {
							String num = intCountAfterPower + String.valueOf(element);
							intCountAfterPower = Integer.parseInt(num);
						} else {
							intCountAfterPower = Character.getNumericValue(element);
							secondDigitPower = true;
						}
					} else if (element == '+' || element == '-') {
						sign = element;
					}

				}
				//Because if it is 1 in front of the "x" we don't write it 
				if(intCountBeforePower == 0) {
					intCountBeforePower = 1;
				}
				
			//Here I call the second method which will determine by which rules will find the derivative of the function	
			derivativeRules();
			
			xCount = 0;
			intCountBeforePower = 0;
			intCountAfterPower = 0;
			powerCount = 0;
			sign = 0;
			secondDigit = false;
			secondDigitPower = false;
		}
		
		System.out.println(buffer.toString());
	}
	
	/***
	 * This method will determine the rule by which to find the derivative of a function
	 * We can add here all other rules
	 * 
	 */
	public static void derivativeRules() {
	
		//A constant - we need to remove the last sign that remain if we have a constant
		if(intCountBeforePower > 0 && xCount == 0 && powerCount == 0 && intCountAfterPower == 0 && sign == 0) {
			buffer.deleteCharAt(buffer.length() - 1);
			buffer.deleteCharAt(buffer.length() - 1);
			buffer.append("");
		}
		
		//A power rule
		if(intCountBeforePower >= 0 && xCount == 'x' && powerCount == '^' && intCountAfterPower > 0) {

			buffer.append((intCountBeforePower * intCountAfterPower) + String.valueOf(xCount) + String.valueOf(powerCount) + (intCountAfterPower - 1));
		}
		
		//A constant and a "x" - we just leave the constant 
		if(intCountBeforePower >= 0 && xCount == 'x' && intCountAfterPower == 0) {

			buffer.append(intCountBeforePower);
		}
		
		//A if the token is a sign
		if(sign > 0) {
			buffer.append(" " + String.valueOf(sign) + " ");
		}
	}
	
	/***
	 * The main method were we execute in the console
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Write a function like (x^16 + 12x^3 - 8x + 9)");
		String function = in.nextLine();
		
		System.out.println("The derivative of this function is:");
		derivative(function);
	}
}
