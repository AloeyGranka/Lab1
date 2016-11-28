/**
 * @Title: ControlTest.java 
 * @Package com.exp 
 * @Description: 回归测试 
 * @author Shuqq 
 * @date 2016年11月22日 下午10:02:08
 * 
 */
package com.exp;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** 
 * @ClassName: ControlTest 
 * @Description: 回归测试表达式+命令处理和求导功能
 * @author Shuqq 
 *  
 */
public class ControlTest {
	private Control con;
	private Expression exp = new Expression();
	private Pretreatment pre = new Pretreatment();
	
	/** 
	 * @Title: setUp 
	 * @Description: 为测试做准备，完成初始化
	 * @param @throws java.lang.Exception   
	 * @throws 
	 */
	@Before
	public void setUp() throws Exception {
		con = new Control();
	}

	/** 
	 * @Title: tearDown 
	 * @Description:  清理测试现场
	 * @param @throws java.lang.Exception   
	 * @throws 
	 */
	@After
	public void tearDown() throws Exception {
		con = null;
	}

	/**
	 * Test method for {@link com.exp.Control#transfer(com.exp.Expression, com.exp.Pretreatment)}.
	 */
	@Test
	public void testTransfer1() {
		con.setStr("6*zoo^2");
		con.transfer(exp, pre);
		con.setStr("!d/d zoo");
		assertEquals("12*zoo", con.transfer(exp, pre));
	}
	
	@Test
	public void testTransfer2() {
		con.setStr("3*x^2*a");
		con.transfer(exp, pre);
		con.setStr("!d/d x");
		assertEquals("6*a*x", con.transfer(exp, pre));
	}
	
	@Test
	public void testTransfer3() {
		con.setStr("a*a*a");
		con.transfer(exp, pre);
		con.setStr("!d/d a");
		assertEquals("3*a^2", con.transfer(exp, pre));
	}
	
	@Test
	public void testTransfer4() {
		con.setStr("a*2*a*a*b");
		con.transfer(exp, pre);
		con.setStr("!d/d a");
		assertEquals("6*b*a^2", con.transfer(exp, pre));
	}
	
	@Test
	public void testTransfer5() {
		con.setStr("x+6*zoo^3 - 3+	20x*y-x-3x+x");
		con.transfer(exp, pre);
		con.setStr("!simplify");
		assertEquals("x+6*zoo^3-3+20*x*y-x-3*x+x", con.transfer(exp, pre));
	}
	
	@Test
	public void testTransfer6() {
		con.setStr("x+6*zoo^3 - 3+	20x*y-x-3x+x");
		con.transfer(exp, pre);
		con.setStr("!simplify x=2");
		assertEquals("6*zoo^3+40*y-7", con.transfer(exp, pre));
	}
	
	@Test
	public void testTransfer7() {
		con.setStr("x+6*zoo^3 - 3+	20x*y-x-3x+x");
		con.transfer(exp, pre);
		con.setStr("!d/d x");
		assertEquals("20*y-2", con.transfer(exp, pre));
	}
	
	@Test
	public void testTransfer8() {
		con.setStr("2x-6*zoo^3-3-2x*y-2x");
		con.transfer(exp, pre);
		con.setStr("!d/d x");
		assertEquals("-2*y", con.transfer(exp, pre));
	}
	
	@Test
	public void testTransfer9() {
		con.setStr("x");
		con.transfer(exp, pre);
		assertEquals("Input out of range", con.transfer(exp, pre));
	}
	
	@Test
	public void testTransfer10() {
		con.setStr("6/x-u");
		con.transfer(exp, pre);
		assertEquals("Input out of range", con.transfer(exp, pre));
	}
	
	@Test
	public void testTransfer11() {
		con.setStr("-6*zoo^3-3+2x*y");
		con.transfer(exp, pre);
		assertEquals("Input out of range", con.transfer(exp, pre));
	}
	
	@Test
	public void testTransfer12() {
		con.setStr("3^zoo-3+2x*y");
		con.transfer(exp, pre);
		assertEquals("Input out of range", con.transfer(exp, pre));
	}
	
	@Test
	public void testTransfer13() {
		con.setStr("1+	a");
		con.transfer(exp, pre);
		con.setStr("simplify");
		assertEquals("Input out of range", con.transfer(exp, pre));
	}
	
	@Test
	public void testTransfer14() {
		con.setStr("1+	a");
		con.transfer(exp, pre);
		con.setStr("!d/ d a");
		assertEquals("Input out of range", con.transfer(exp, pre));
	}
	
	@Test
	public void testTransfer15() {
		con.setStr("6*zoo^3+x+zoo");
		con.transfer(exp, pre);
		con.setStr("!d/d o");
		assertEquals("求导变量非法", con.transfer(exp, pre));
	}
	
	@Test
	public void testTransfer16() {
		con.setStr("1+	a");
		con.transfer(exp, pre);
		con.setStr("!d/ d a");
		assertEquals("Input out of range", con.transfer(exp, pre));
	}
	
	@Test
	public void testTransfer17() {
		con.setStr("1+x+y");
		con.transfer(exp, pre);
		con.setStr("!d/d x y");
		assertEquals("Input out of range", con.transfer(exp, pre));
	}
	
	@Test
	public void testTransfer18() {
		con.setStr("!d/d x");
		con.transfer(exp, pre);
		assertEquals("未输入表达式", con.transfer(exp, pre));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
