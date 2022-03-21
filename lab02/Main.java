package ua.khpi.oop.lab02;
import java.util.Random;

//(11-1)%10+1 = 1

public class Main {

	public static int N=10;
	
	public static int NOD(int a, int b) {
		int c;
		do {
			if (a<b) {
				c=a;
				a=b;
				b=c;
			}
			a=a%b;
		} while (a!=0);
		return b;
	}
	
	public static void z1() {
		int n1;
		int n2;
		Random rand=new Random();
		System.out.println("+-------+-------+-------+");
		System.out.println("|число 1|число 2|  НОД  |");
		System.out.println("+-------+-------+-------+");
		for (int i=0; i<N; i++) {
			n1=rand.nextInt(100)+1;
			n2=rand.nextInt(100)+1;
			System.out.println("| "+n1+"\t| "+n2+"\t| "+NOD(n1,n2)+"\t|");
		}
		System.out.println("+-------+-------+-------+");
	}	

	public static int digitSum(int a) {
		int s=0;
		while (a!=0) {
			s+=a%10;
			a/=10;
		}
		return s;
	}
	
	public static void z2() {
		int n1;
		Random rand=new Random();
		System.out.println("+-------+---------------+");
		System.out.println("| число |  сумма цифр   |");
		System.out.println("+-------+---------------+");
		for (int i=0; i<N; i++) {
			n1=rand.nextInt(100000)+1;
			System.out.println("| "+n1+"\t| "+digitSum(n1)+"\t\t|");
		}
		System.out.println("+-------+---------------+");
	}	

	public static int digitMax(int a) {
		int m=0;
		int last;
		while (a!=0) {
			last=a%10;
			if (m<last) m=last;
			a/=10;
		}
		return m;
	}
	
	public static void z3() {
		int n1;
		Random rand=new Random();
		System.out.println("+-------+---------------+");
		System.out.println("| число | максим. цифра |");
		System.out.println("+-------+---------------+");
		for (int i=0; i<N; i++) {
			n1=rand.nextInt(100000)+1;
			System.out.println("| "+n1+"\t| "+digitMax(n1)+"\t\t|");
		}
		System.out.println("+-------+---------------+");
	}	

	public static int digitMin(int a) {
		int m=9;
		int last;
		do {
			last=a%10;
			if (m>last) m=last;
			a/=10;
		} while (a!=0);
		return m;
	}
	
	public static int digitPos(int a, int b) {
		int i=1; 
		while (a!=0) {
			if (a%10==b) System.out.print(" "+i);
			a/=10;
			i++;
		}
		return 0;
	}
	
	public static void z4() {
		int n1;
		int min;
		Random rand=new Random();
		System.out.println("+-------+-------------------------------+");
		System.out.println("| число |  позиции наименьшей цифры     |");
		System.out.println("+-------+-------------------------------+");
		for (int i=0; i<N; i++) {
			n1=rand.nextInt(100000)+1;
			min=digitMin(n1);
			System.out.print("| "+n1+"\t| ["+min+"] ->");
			digitPos(n1,min);
			System.out.println("\t\t\t|");
		}
		System.out.println("+-------+-------------------------------+");
	}	

	public static boolean prost(int a) {
		if (a%2==0) return false;
		int d=3;
		int mid=a/2;
		while (d<mid) {
			if (a%d==0) return false;
			d+=2;
		}
		return true;
	}
	
	public static void z5() {
		int n1;
		Random rand=new Random();
		System.out.println("+-------+---------------+");
		System.out.println("| число | простое число |");
		System.out.println("+-------+---------------+");
		for (int i=0; i<N; i++) {
			n1=rand.nextInt(10000)+1;
			System.out.println("| "+n1+"\t| "+(prost(n1)?"простое":"нет\t")+"\t|");
		}
		System.out.println("+-------+---------------+");
	}	
	
	public static int sum3(int a) {
		int sum=0;
		for (int i=0; i<3; i++) {
			sum+=a%10;
			a/=10;
		}
		return sum;
	}
		
	public static boolean happyNum(int a) {
		int sum3low=0;
		int sum3high=0;
		sum3low=sum3(a);
		sum3high=sum3(a/1000);
		return sum3low==sum3high;
	}

	public static void z6() {
		int n1;
		Random rand=new Random();
		System.out.println("+-------+-----------------------+");
		System.out.println("| число |    счастливый номер   |");
		System.out.println("+-------+-----------------------+");
		for (int i=0; i<N; i++) {
			n1=0;
			while (n1<=99999) n1=rand.nextInt(999999)+1;
			System.out.println("|"+n1+"\t| "+(happyNum(n1)?"ДА":"нет")+"\t\t\t|");
		}
		System.out.println("+-------+-----------------------+");
	}	
	
	public static boolean digit8_30_21(int a) {
		return (a/01000 == a%8)&&((a/0100)%8 == (a/010)%8);
	}
	
	public static void print8_4digit(int a) {
		int o;
		for (int i=0; i<4; i++) {
			o=(a/01000)%8;
			System.out.print(o);
			a=a*8;
		}
	}
	
	public static void z7() {
		int n1;
		Random rand=new Random();
		System.out.println("+---------------+-----------------------+");
		System.out.println("| 8-ричное число| разряды 0=3 и 2=1     |");
		System.out.println("+---------------+-----------------------+");
		for (int i=0; i<N; i++) {
			n1=rand.nextInt(010000);
			System.out.print("| ");
			print8_4digit(n1);
			System.out.println("\t\t| "+(digit8_30_21(n1)?"ДА":" ")+"\t\t\t|");
		}
		System.out.println("+---------------+-----------------------+");
	}	
	
	public static int last8(int a) {
		return a%8;
	}
	public static int digit8sum_even(int a) {
		int last;
		int sum=0;
		while (a!=0) {
			last=last8(a);
			if (last%2==0) sum+=last;
			a/=8;
		}
		return sum;
	}

	public static int digit8sum_odd(int a) {
		int last;
		int sum=0;
		while (a!=0) {
			last=last8(a);
			if (last%2==1) sum+=last;
			a/=8;
		}
		return sum;
	}

	public static void print8_8digit(int a) {
		int o;
		for (int i=0; i<8; i++) {
			o=(a/010000000)%8;
			System.out.print(o);
			a=(a%010000000)*8;
		}
	}
	
	public static void z8() {
		int n1;
		Random rand=new Random();
		System.out.println("+---------------+-----------------------+");
		System.out.println("| 8-ричное число|сумма чет и нечет цифр |");
		System.out.println("+---------------+-----------------------+");
		for (int i=0; i<N; i++) {
			n1=rand.nextInt(0100000000);
			System.out.print("| ");
			print8_8digit(n1);
			System.out.print("\t| чет="+digit8sum_even(n1));
			System.out.println(" нечет="+digit8sum_odd(n1)+"\t|");
		}
		System.out.println("+---------------+-----------------------+");
	}	
	
	public static int count16letter(long a) {
		int c=0;
		while (a!=0) {
			if (a%0x10>9) c++;
			a/=0x10;
		}
		return c;
	}
	
	public static void print16_9digit(long a) {
		int shift=32;
		long h;
		for (int i=0; i<9; i++) {
			h=(a>>shift)&0xF;
			switch ((int)h) {
				case 0xA: System.out.print("A"); break;
				case 0xB: System.out.print("B"); break; 
				case 0xC: System.out.print("C"); break;
				case 0xD: System.out.print("D"); break;
				case 0xE: System.out.print("E"); break;
				case 0xF: System.out.print("F"); break;
				default : System.out.print((int)h);  
			}	
			shift-=4;
		}
	}
	
	public static void z9() {
		long n1;
		Random rand=new Random();
		System.out.println("+---------------+---------------+-------+");
		System.out.println("|10значное число| 16ричный вид  | букв  |");
		System.out.println("+---------------+---------------+-------+");
		for (int i=0; i<N; i++) {
			n1=rand.nextLong(10000000000L);
			System.out.print("| "+n1+"\t| ");
			print16_9digit(n1);
			System.out.println("\t| "+count16letter(n1)+"\t|");
		}
		System.out.println("+---------------+-----------------------+");
	}	
	
	public static int count_comb01(int a) {
		int c=0;
		for (int i=0; i<31; i++) {
			if (a%4==0b01) c++;
			a>>=1;
		}
		return c;
	}

	public static int count_comb10(int a) {
		int c=0;
		for (int i=0; i<31; i++) {
			if (a%4==0b10) c++;
			a>>=1;
		}
		return c;
	}

	public static void print2_31digit(int a) {
		for (int i=30; i>=0; i--) {
			if ((a>>i)%2==0) System.out.print("0");
			else System.out.print("1");
		}
	}
	
	public static void z10() {
		int n1;
		Random rand=new Random();
		System.out.println("+---------------+-------------------------------+---------------+");
		System.out.println("|     число     |         двоичный вид          |  комбинации   |");
		System.out.println("+---------------+-------------------------------+---------------+");
		for (int i=0; i<N; i++) {
			n1=rand.nextInt(0x7FFFFFF);
			System.out.print("| "+n1+"\t|");
			print2_31digit(n1);
			System.out.println("|01="+count_comb01(n1)+"\t10="+count_comb10(n1)+"\t|");
		}
		System.out.println("+---------------+-------------------------------+---------------+");
	}	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		z1();
		z2();
		z3();
		z4();
		z5();
		z6();
		z7();//исправить
		z8();//исправить
		z9();
		z10();
	}
}
