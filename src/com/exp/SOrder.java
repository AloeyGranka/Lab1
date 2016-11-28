/**
 * @Title: SOrder.java 
 * @Package com.exp 
 * @Description: 存储simplify命令，进行赋值和简化
 * @author Shuqq 
 * @date 2016年11月11日 下午8:16:31
 * 
 */
package com.exp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * @ClassName: SOrder 
 * @Description: simplify命令实体类
 * @author Shuqq 
 *  
 */
public class SOrder {
	
	//变量-对应值的集合
	public Map<String, String> orderMap = new HashMap<String, String>();

	public Map<String, String> getOrderMap() {
		return orderMap;
	}
	
	public void setOrderMap(Map<String, String> orderMap) {
		this.orderMap = orderMap;
	}
	
	//赋值
	public String assignExp(String exp) {
		Map<String, String> map = this.orderMap;
		
		StringBuffer sb = new StringBuffer(exp);
	
		Matcher match = Pattern.compile("[a-zA-Z]+").matcher(sb);
		int sta = 0;
		int en = 0;
		
		//用变量对应的值去替换该变量
		while (match.find()) {
			sta = match.start();
			en = match.end();
			String st = new String(match.group());
			if (map.containsKey(st)) {
				sb.replace(sta, en, map.get(st));
				match.reset(sb);
			}
		}
		System.out.println("赋值后的表达式：" + sb.toString());
		
		Matcher match2 = Pattern.compile("\\d{1,}\\^\\d{1,}").matcher(sb);
		sta = 0;
		en = 0;
		int medium = 0;
		
		//计算赋值之后的幂运算
		
			while (match2.find()) {
				sta = match2.start();
				en = match2.end();
				medium = sb.indexOf("^", sta);
					
				int num = Integer.parseInt(sb.substring(medium + 1, en));
				int expo  = Integer.parseInt(sb.substring(sta, medium)); //存储幂运算结果
				double result = Math.pow(expo, num);
				int finalResult = (int) result;  //因为不涉及小数运算，所以这边强制转换即可			
					
				sb.replace(sta, en, String.valueOf(finalResult));
				System.out.println("赋值后处理了幂运算的表达式：" + sb.toString());
				match2.reset();      // 重新设置到最开始..如果这里没有这一步,下面的匹配将受到影响!
			}
			
		Expression expForMul = new Expression();
		Pretreatment pre = new Pretreatment();
		boolean buildExpForMul = pre.buildExpression(sb.toString(), expForMul);
		String mulResult = new String();
		
		if (buildExpForMul) {
			mulResult = this.mulExp(expForMul);
		} else {
			System.out.println("Pretreatment类的buildSOrder方法出错");
		}
		
		return mulResult;
	}
	
	//乘除运算
	public String mulExp(Expression expression) {
		List<String> mulExpList = expression.getSubExpList();
		
		StringBuffer mulExpResult = new StringBuffer();
		System.out.println("子表达式个数：" + String.valueOf(mulExpList.size()));
		for (int i = 0; i < mulExpList.size(); i++) {
			String sbString = mulExpList.get(i);
			System.out.println("每个子表达式：" + sbString);
			
			//根据Pretreatment类中的buildExpresion方法，
			//Expression实体类中存储的子表达式是带有负号的（如果有负号的话）
			boolean flag = false;
			String newSbString = new String();
			if (sbString.charAt(0) == '-') {
				newSbString = sbString.substring(1);
				flag = true;
			} else {
				newSbString = sbString;
			}
			
			StringBuffer sb = new StringBuffer();  //存储计算了数字乘积后的子表达式
			int perResult = 1;  //存储每个子表达式中的数字乘积
		
			String [] atomExp = newSbString.split("\\*");
			
			for (int j = 0; j < atomExp.length; j++) {
				if (atomExp[j].matches("\\d{1,}")) {
					perResult *= Integer.parseInt(atomExp[j]);
				} else {
					if (j != 0) {
						sb.append("*");
					}
					sb.append(atomExp[j]);
				}
			}
			sb.insert(0, perResult);
			if (flag) {
				sb.insert(0, "-");
			} 
			
			if (i != 0 && !flag) {
				mulExpResult.append("+");
			}
			mulExpResult.append(sb);
					
		}
		
		Expression expForAdd = new Expression();
		Pretreatment pre = new Pretreatment();
		boolean buildExpForAdd = pre.buildExpression(mulExpResult.toString(), expForAdd);
		String addResult = new String();
		
		if (buildExpForAdd) {
			addResult = this.addExp(expForAdd);
		} else {
			System.out.println("Pretreatment类的buildSOrder方法出错");
		}
		
		return addResult;
	}
	
	//加减运算
	public String addExp(Expression expression) {
		List<String> addExpList = expression.getSubExpList();
		StringBuffer addExpResult = new StringBuffer();
		int digitResult = 0;
		
		for (int i = 0; i < addExpList.size(); i++) {
			String sbString = addExpList.get(i);
			
			if (sbString.matches("(\\d{1,})|(-\\d{1,})")) {
				digitResult += Integer.parseInt(sbString);
			} else {
				if (sbString.charAt(0) == '-') {
					addExpResult.append(sbString);
				} else {
					addExpResult.append("+" + sbString);
				}
				
			}
		}
		
		//保证减号不出现在表达式第一个字符
		if (digitResult > 0) {
			addExpResult.insert(0, digitResult);
		} else if (digitResult < 0) {
			//删掉多余的+号
			if (addExpResult.charAt(0) == '+') {
				addExpResult.deleteCharAt(0);
			}
			addExpResult.append(digitResult);
		} else {
			//删掉多余的+号
			if (addExpResult.charAt(0) == '+') {
				addExpResult.deleteCharAt(0);
			}
		}
	
		return addExpResult.toString();
	}

}
