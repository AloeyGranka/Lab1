/**
 * @Title: InOut.java 
 * @Package com.exp 
 * @Description: 程序入口与出口
 * @author Shuqq 
 * @date 2016年11月11日 下午7:36:40
 * 
 */
package com.exp;

import java.util.Scanner;

/** 
 * @ClassName: InOut 
 * @Description: 输入输出的边界类
 * @author Shuqq 
 *  
 */
public class InOut {

	public static void main(String [] args) {
		
		Expression exp = new Expression();
		Pretreatment pre = new Pretreatment();
		boolean flag = true;
		
		while (flag) {
			Scanner scan = new Scanner(System.in);
			String str = scan.nextLine();
			Control con = new Control();
			con.setStr(str);
			
			String re = con.transfer(exp, pre);
			
			if (re.length() >= 2 && re.substring(0, 2).equals("00")) {
				flag = false;
				scan.close();
				System.out.println(re.substring(2));
			} else {
				System.out.println(re);
			}
		}
	}
	
	
}
