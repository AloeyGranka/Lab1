/**
 * @Title: Pretreatment.java 
 * @Package com.exp 
 * @Description: 判断合法性并进行预处理形成相应数据结构
 * @author Shuqq 
 * @date 2016年11月11日 下午7:48:43
 * 
 */
package com.exp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * @ClassName: Pretreatment 
 * @Description: 预处理实体类
 * @author Shuqq 
 *  
 */

public class Pretreatment {
	
	public String str;        //输入的表达式或者命令
	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public int judge() {
		
		String regex0 = "^\\d+[a-zA-Z]+$";
		String regex1 = "^(([a-zA-Z]+\\^\\d+|\\d+[a-zA-Z]+|\\d+|[a-zA-Z]+)(-|\\+|\\*|-\\s+|\\s+-|\\s+-\\s+|\\+\\s+|\\s+\\+|\\s+\\*|\\*\\s+|\\s+\\+\\s+|\\s+\\*\\s+))+(\\d+[a-zA-Z]+|\\d+|[a-zA-Z]+|[a-zA-Z]+\\^\\d+)$";
		String regex2 = "^!simplify$";
		String regex3 = "^(!simplify)(\\s\\w+=\\d+)+$";
		String regex4 = "^!d/d\\s\\w+$";
		
		if (this.str.matches(regex1) || this.str.matches(regex0)) {
			return 1;
		} else if (this.str.matches(regex2)) {
			return 2;
		}else if (this.str.matches(regex3)){
			return 3;
		} else if (this.str.matches(regex4)) {
			return 4;
		} else {
			return 0;
		}
		
	}
	
	public boolean buildExpression(String str, Expression expression) {
		String exp = str.replaceAll("\\s", "");
		StringBuffer sb = new StringBuffer(exp);
		
		int sta, medium = 0;
		int count = 0;
		
		//在数字和变量间加乘号
		Matcher match = Pattern.compile("(\\d+[a-zA-Z]+)").matcher(exp);
		while (match.find()) {
			sta = match.start();
			String st = new String(match.group());
			System.out.println("st:" + st);
			Matcher m3 = Pattern.compile("\\d+").matcher(st);
			
			if (m3.find()) {
				medium = m3.end();
			}
				
			sb.insert(sta+medium+count, '*');
			count++;
		}
		
		String newExp = sb.toString();   //加乘号之后得到的新的字符串
		
		List<String> subExpArray = new ArrayList<String>();
		sta = 0;
		count = 0;
		
		//遍历字符串，截取按照+/-分离的子表达式存入subExpArray，
		//若该子表达式前面的字符为-，也要作为子表达式的一部分存入
		for (int i = 0; i < newExp.length(); i++) {
			if (newExp.charAt(i) == '+') {
				subExpArray.add(count, newExp.substring(sta, i));
				sta = i + 1;
				count++;
			} else if (newExp.charAt(i) == '-') {
				subExpArray.add(count, newExp.substring(sta, i));
				sta = i;
				count++;
			}
		}
		//按照上面的遍历方法只能截取到最后一个算符前面的部分，所以还要加上剩下的部分
		subExpArray.add(count, newExp.substring(sta, newExp.length()));
		
		
		String [] subExp = newExp.split("\\+|-");  //+/-划分字符串得到子表达式数组
		List<String> vars = new ArrayList<String>();
	
		for (int j = 0; j < subExp.length ; j++) {
			String [] atomExp = subExp[j].split("\\*|\\^");  //这里考虑用\\*|\\^|-来split的话是不是将split对象换为subExpArray
		
			for (int k = 0; k < atomExp.length; k++) {
				if (atomExp[k].matches("[A-Za-z]+")) {
					if (vars.contains(atomExp[k])) {
						break;
					} else {
						vars.add(atomExp[k]);
					}
				}	
			}
		}
		
		expression.setNewExpression(newExp);
		expression.setSubExpList(subExpArray);
		expression.setVarList(vars);
		
		return true;
		
	}
	
	public boolean buildSOrder(String sord, SOrder sorder) {
		Map<String, String> map = new HashMap<String, String>();
		
		String [] subSOrder = sord.split(" ");
		int index;
		
		for (int i = 1; i < subSOrder.length; i++) {
			index = subSOrder[i].indexOf("=");
			String var = subSOrder[i].substring(0, index);
			String varNum = subSOrder[i].substring(index+1);
			map.put(var, varNum);
		}
		
		sorder.setOrderMap(map);
		
		return true;	
	}
	
	public boolean buildDOrder(String dord, DOrder dorder) {
		dorder.setdVar(dord.substring(5));
	
		return true;
	}
	
}
