import java.util.Scanner;

public class Exer01{

	public static void main(String[] args){
		/*
			1. 输入一个整数
			2. 获取每一位数字
			3. 验证是否是 四叶玫瑰数
		*/
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入一个 4 位整数：");
		int num = sc.nextInt();
		
		
		// 获取每一位数字 4321
		int q = num / 1000;
		int b = num / 100 % 10;
		int s = num / 10 % 10;
		int g = num % 10;
		
		int result = q * q * q * q + b * b * b * b + s * s * s * s + g * g * g * g;
		System.out.println(result == num);
	}

}