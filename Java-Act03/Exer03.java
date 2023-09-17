import java.util.Scanner;
public class Exer03{
	public static void main(String[] args){
		/*
			输入三条边，判断是三角形吗？
			1. 输入三条边长
			2. 判断是否能组成三角形
				任意两条边相加大于第三边
		*/
		Scanner sc = new Scanner(System.in);
		
		System.out.println("请输入三角形的三条边，以空格分隔：");
		int firstSide = sc.nextInt();			
		int secondSide = sc.nextInt();	
		int thirdSide = sc.nextInt();
		
		boolean result = firstSide + secondSide > thirdSide 
		&& firstSide + thirdSide > secondSide 
		&& secondSide + thirdSide >  firstSide;
		
		System.out.println(result);
				
	}
}
