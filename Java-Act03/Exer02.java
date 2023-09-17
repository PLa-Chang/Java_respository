import java.util.Scanner;
public class Exer02{
	public static void main(String[] args){
		/*
			交换两个数位置
			1. 输入两个数字
		*/
		Scanner sc = new Scanner(System.in);
		
		System.out.println("请输入一个数字：");
		int num = sc.nextInt();
		System.out.println("请输入另一个数字：");
		int num1 = sc.nextInt();
		
		System.out.printf("输入的两个数字分别是：%d , %d \r\n", num, num1);
		
		// 交换位置
        // 第一种方法：异或法
		// num = num ^ num1;
		// num1 = num ^ num1;
		// num = num ^ num1;
		
        //第二种方法：加法方法
		// num = num + num1;
		// num1 = num - num1;
		// num = num - num1;
		
        
		int temp = num;
		num = num1;
		num1 = temp;
		
		System.out.printf("交换位置后两个数字分别是：%d , %d", num, num1);
	}
}
