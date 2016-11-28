/**
 * @Title: Control.java 
 * @Package com.exp 
 * @Description: 控制
 * @author Shuqq 
 * @date 2016年11月11日 下午7:36:40
 * 
 */
package com.exp;

import java.util.List;

/** 
 * @ClassName: InOut 
 * @Description: 控制类
 * @author Shuqq 
 *  
 */
public class Control {
	public String str; //输入的表达式和命令
	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
	
	public String transfer(Expression expression, Pretreatment pre){

		String result = new String();
			
		if (this.str.equals("end")) {
			result = "00" + "Exit";
			
		} else {
			pre.setStr(this.str);
			int judgeResult = pre.judge();
				
			switch(judgeResult) {
				case 0:
					result = "Input out of range";
					break;
					
				case 1:
					boolean buildExp = pre.buildExpression(this.str, expression);
					if (!buildExp) {
						result = "00" + "Pretreatment类的buildExpression方法出错";
					} else {
						result = expression.getNewExpression();
					}
					break;
						
				case 2:
					String newExp1 = expression.getNewExpression();
					if (newExp1 == null || newExp1.length() == 0) {
						result = "未输入表达式";
						break;
					}
					result = newExp1;
					break;
						
				case 3: 
					String newExp2 = expression.getNewExpression();
					if (newExp2 == null || newExp2.length() == 0) {
						result = "未输入表达式";
						break;
					}
						
					SOrder sorder = new SOrder();
					boolean buildSOrd = pre.buildSOrder(this.str, sorder);
					
					if (buildSOrd) {
						String assignResult = sorder.assignExp(newExp2);
						result = assignResult;
					} else {
						result = "00" + "Pretreatment类的buildSOrder方法出错";
					}
					break;
						
					case 4:
						String newExp3 = expression.getNewExpression();
						List<String> varList = expression.getVarList();
						
						if (newExp3 == null || newExp3.length() == 0) {
							result = "未输入表达式";
							break;
						}
						
						DOrder dorder = new DOrder();
						boolean buildDOrd = pre.buildDOrder(this.str, dorder);
						if (buildDOrd) {
							boolean islegal = dorder.haveVar(varList);
							
							if (islegal) {
								String derResult = dorder.expoToMul(expression);
								
								//简化求导结果
								Expression simpDerResult = new Expression();
								boolean buildSimpRe = pre.buildExpression(derResult, simpDerResult);
								
								if (buildSimpRe) {
									SOrder sor = new SOrder();
									String finalResult = sor.mulExp(simpDerResult);
									result = finalResult;
								}
								
							} else {
								result = "求导变量非法";
							}
							
						} else {
							result = "00" + "Pretreatment类的buildDOrder方法出错";
						}
						break;

					default:
						result = "00";
						break;
				}
			}
		
			return result;
			
		}
	
}
