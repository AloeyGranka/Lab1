/**
 * @Title: DOrder.java 
 * @Package com.exp 
 * @Description: 存储求导命令，进行求导
 * @author Shuqq 
 * @date 2016年11月11日 下午8:16:21
 * 
 */
package com.exp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * @ClassName: DOrder 
 * @Description: 求导命令实体类
 * @author Shuqq 
 *  
 */
public class DOrder {
	public String dVar;      //要进行求导的变量

	public String getdVar() {
		return dVar;
	}

	public void setdVar(String dVar) {
		this.dVar = dVar;
	}
	
	//判断求导变量是否在表达式中出现
	public boolean haveVar(List<String> list) {
		String var = this.dVar;
		if (list.contains(var)) {
			return true;
		}
		return false;
	}
	
	public String expoToMul(Expression expression) {
		List<String> subExpList = expression.getSubExpList();
		String var = this.dVar;
		StringBuffer mulRe = new StringBuffer(); 
		
		for (int i = 0; i < subExpList.size(); i++) {
			String temp = new String(subExpList.get(i));
			StringBuffer tempBuf = new StringBuffer(temp);
			
			Matcher match = Pattern.compile("[a-zA-Z]+\\^\\d+").matcher(tempBuf);
	
			while (match.find()) {
				int sta = match.start();
				int en = match.end();
				String group = new String(match.group());
		
				int index = group.indexOf("^");
				String st = group.substring(0, index);
				
				if (st.equals(var)) {
					int num = Integer.parseInt(group.substring(index+1));
					int fl = 0;
					
					if ((en != tempBuf.length() && sta == 0) || 
							(en != tempBuf.length() && tempBuf.charAt(en) == '*')) {
						tempBuf.delete(sta, en + 1);
					} else if (sta != 0 && en == tempBuf.length() && tempBuf.charAt(sta - 1) == '-') {
						fl = 1;
						tempBuf.delete(sta - 1, en);
					} else if ((sta != 0 && en == tempBuf.length() && tempBuf.charAt(sta - 1) != '-') ||
							(sta != 0 && tempBuf.charAt(sta - 1) == '*')) {
						tempBuf.delete(sta - 1, en);
					} else if (sta == 0 && en == tempBuf.length()) {
						fl = 2;
						tempBuf.delete(sta, en);  //删除整个tempBuf
					}
					
					for (int j = 0; j < num; j++) {
						tempBuf.append("*" + st);
					}
					
					//删除多余的乘号
					if (fl == 2) {
						tempBuf.deleteCharAt(0);
					} else if (fl == 1) {
						tempBuf.deleteCharAt(1);  
					}
					match.reset();
				}	
			}
			if (tempBuf.charAt(0) != '-') {
				mulRe.append("+");
			}
			mulRe.append(tempBuf);
		}
		
		//mulRe是经过幂运算转变量连乘之后的字符串
		if (mulRe.charAt(0) == '+') {
			mulRe.deleteCharAt(0);
		}
	
		String finalResult = this.getVarExp(mulRe.toString());
		
		return finalResult;
	}
	
	//获取含有var的那些子表达式
	public String getVarExp(String exp) {
		Expression expression = new Expression();
		Pretreatment pre = new Pretreatment();
		boolean buildExp = pre.buildExpression(exp, expression);
		String derResult = new String();
		
		if (buildExp) {
			List<String> subExpList = new ArrayList<String>();
			subExpList = expression.getSubExpList();
			List<String> varExpList = new ArrayList<String>();       //含有var的那些子表达式集合
			
			for (int i = 0; i < subExpList.size(); i++) {
				String [] atomExp = subExpList.get(i).split("\\*");
				
				for (int j = 0; j < atomExp.length; j++) {
					if (atomExp[j].equals(this.dVar) || atomExp[j].equals("-" + this.dVar)) {
						varExpList.add(subExpList.get(i));
						break;
					}
				}
			}
			
			derResult = this.derivation(varExpList);
			
		} else {
			System.out.println("Pretreatment类的buildExpression方法出错");
		}
		
		return derResult;
	}
	

	//求导
	public String derivation(List<String> list) {
		StringBuffer finalResult = new StringBuffer();
		
		for (int i = 0; i < list.size(); i++) {
			String temp = new String(list.get(i));
			System.out.println("temp: "+temp);
			int count = 0;
			StringBuffer tempResult = new StringBuffer();
			int tempNum = 1;
			
			String [] subTemp = temp.split("\\*");
			for (int j = 0; j < subTemp.length; j++) {
				if (subTemp[j].equals(this.dVar)) {
					count++;
				} else if (subTemp[j].equals("-" + this.dVar)) {
					count++;
					tempNum = -1;
				} else if (subTemp[j].matches("(\\d+)|(-\\d+)")){
					tempNum *= Integer.parseInt(subTemp[j]);
				} else {
					tempResult.append(subTemp[j] + "*");
				}
			}
			
			System.out.println("??: " + tempResult.toString());
			
			if (tempResult.toString().equals("") || tempResult == null) {
				tempResult.append(tempNum * count);
			} else {
				if (tempNum < 0) {
					tempResult.deleteCharAt(tempResult.length() - 1);
					tempResult.insert(0, "*");
					tempResult.insert(0, tempNum * count);
				} else {
					tempResult.append(tempNum * count);
				}
			}
			
			if (count == 2) {
				tempResult.append(this.dVar);
			} else if (count > 2) {
				tempResult.append("*" + this.dVar + "^" + String.valueOf(count - 1));
			}

			if (tempNum > 0) {
				finalResult.append("+");
			} 
			finalResult.append(tempResult);
		}
		
		if (finalResult.charAt(0) == '+') {
			finalResult.deleteCharAt(0);
		}
		
		System.out.println("lalla: " + finalResult.toString());
		return finalResult.toString();
	}

	
}
