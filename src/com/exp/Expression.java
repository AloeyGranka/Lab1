/**
 * @Title: Expression.java 
 * @Package com.exp 
 * @Description: 存储表达式
 * @author Shuqq 
 * @date 2016年11月11日 下午7:00:00
 * 
 */
package com.exp;

import java.util.ArrayList;
import java.util.List;

/** 
 * @ClassName: Expression 
 * @Description: 表达式实体类
 * @author Shuqq 
 *  
 */
public class Expression {
	
	//用+和-split之后得到的子表达式，含有减号的也要将减号作为表达式的一部分
	public List<String> subExpList = new ArrayList<String>(); 
	public List<String> varList = new ArrayList<String>(); //存储表达式中的变量
	public String newExpression; //存储经过去空格,加乘号处理后的表达式

	/**
	 * @return the newExpression
	 */
	public String getNewExpression() {
		return newExpression;
	}

	/**
	 * @param newExpression the newExpression to set
	 */
	public void setNewExpression(String newExpression) {
		this.newExpression = newExpression;
	}

	/**
	 * @return the varList
	 */
	public List<String> getVarList() {
		return varList;
	}

	/**
	 * @param varList the varList to set
	 */
	public void setVarList(List<String> varList) {
		this.varList = varList;
	}

	/**
	 * @return the subExpList
	 */
	public List<String> getSubExpList() {
		return subExpList;
	}

	/**
	 * @param subExpList the subExpList to set
	 */
	public void setSubExpList(List<String> subExpList) {
		this.subExpList = subExpList;
	}
	
	
}
