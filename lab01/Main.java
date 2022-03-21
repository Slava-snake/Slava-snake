package ua.khpi.oop.lab01;

public final class Main {

	public static int getLastDigit(int n) {
		return n%10;
	}
	
	public static boolean IsNumberEven(int n) {
		return (n%2)==0;
	}
	
	public static void countEvenOddDigits(int number) {
		int counterEven=0;
		int counterOdd=0;
		int n=number;
		do {
			if (IsNumberEven(getLastDigit(n)))
				counterEven++;
			else
				counterOdd++;
			n/=10;
		} while (n!=0);
		System.out.println("В числе "+number+" цифр четных : "+counterEven+
						   " и нечетных : "+counterOdd);
	}
		
	public static void countBinary1(int number) {
		int count=0;
		int n=number;
		do {
			if (n%2==1)
				count++;
			n/=2;
		} while (n!=0);
		System.out.println("В числе "+number+" битовых 1 : "+count);
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int zalikova=0x0B;
		int phone=988615819;
		int last2=0b10011;
		int last4=013273;
		int n1=(11-1)%26 + 1;	//11
		char a='K';
		countEvenOddDigits(zalikova);
		countEvenOddDigits(phone);
		countEvenOddDigits(last2);
		countEvenOddDigits(last4);
		countEvenOddDigits(n1);
		countEvenOddDigits(a);
		countBinary1(zalikova);
		countBinary1(phone);
		countBinary1(last2);
		countBinary1(last4);
		countBinary1(n1);
		countBinary1(a);
	}

}
