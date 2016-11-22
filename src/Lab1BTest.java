import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @Title: Lab1Test.java 
 * @Package  
 * @Description: Lab1 expression方法的黑盒测试
 * @author Shuqq 
 * @date 2016年11月7日 下午7:37:55
 * 
 */

/** 
 * @ClassName: Lab1Test 
 * @Description: Lab1 expression方法的黑盒测试
 * @author Shuqq 
 *  
 */
public class Lab1Test {
	
	private Lab1 exp;
	
	/** 
	 * @Title: setUp 
	 * @Description: 为测试做准备，完成初始化
	 * @param @throws java.lang.Exception   
	 * @throws 
	 */
	@Before
	public void setUp() throws Exception {
		exp = new Lab1();
	}

	/** 
	 * @Title: tearDown 
	 * @Description: 清理测试现场
	 * @param @throws java.lang.Exception   
	 * @throws 
	 */
	@After
	public void tearDown() throws Exception {
		exp = null;
	}

	/**
	 * Test method for {@link Lab1#expression(java.lang.String)}.
	 */
	
	
	@Test
	public void testExpression1() {
		assertEquals("x+6*zoo^3-3+20x*y-x-3x+x", exp.expression("x+6*zoo^3 - 3+	20x*y-x-3x+x"));
		assertEquals("x+6*2^3-3+20*x*y-x-3*x+x", exp.expression("!simplify zoo=2"));
	}
	@Test
	public void testExpression2() {
		assertEquals("x+6*zoo^3-3+20x*y-x-3x+x", exp.expression("x+6*zoo^3 - 3+	20x*y-x-3x+x"));
		assertEquals("x+6*zoo^3-3+20x*y-x-3x+x", exp.expression("!simplify"));
	}
	@Test
	public void testExpression3() {
		assertEquals("x+6*zoo^3-3+20x*y-x-3x+x", exp.expression("x+6*zoo^3 - 3+	20x*y-x-3x+x"));
		assertEquals("x+20*x*y-x-3*x+x", exp.expression("!d/d x"));
	}
	
	@Test
	public void testExpression4() {
		assertEquals("6*zoo^3", exp.expression("6*zoo^3"));
		assertEquals("6*zoo^3", exp.expression("!d/d zoo"));
	}
	@Test
	public void testExpression5() {
		assertEquals("2x-6*zoo^3-3-2x*y-2x", exp.expression("2x-6*zoo^3-3-2x*y-2x"));
		assertEquals("2*x-2*x*y-2*x", exp.expression("!d/d x"));
	}
	@Test
	public void testExpression6() {
		assertEquals("6*zoo^3-3+2x*y", exp.expression("6*zoo^3-3+2x*y"));
		assertEquals("2*x*y", exp.expression("!d/d x"));
	}
	@Test
	public void testExpression7() {
		assertEquals("Input out of range", exp.expression("x"));
	}
	@Test
	public void testExpression8() {
		assertEquals("Input out of range", exp.expression("6/x-u"));
	}
	@Test
	public void testExpression9() {
		assertEquals("Input out of range", exp.expression("-6*zoo^3-3+2x*y"));
	}
	@Test
	public void testExpression10() {
		assertEquals("Input out of range", exp.expression("3^zoo-3+2x*y"));
	}
	@Test
	public void testExpression11() {
		assertEquals("1+a", exp.expression("1+	a"));
		assertEquals("Input out of range", exp.expression("simplify"));
	}
	@Test
	public void testExpression12() {
		assertEquals("1+a", exp.expression("1+	a"));
		assertEquals("Input out of range", exp.expression("!d/ d a"));
	}
	@Test
	public void testExpression13() {
		assertEquals("6*zoo^3+x+zoo", exp.expression("6*zoo^3+x+zoo"));
		assertEquals("o not found", exp.expression("!d/d o"));
	}
	@Test
	public void testExpression14() {
		assertEquals("6*zoo^3", exp.expression("6*zoo^3"));
		assertEquals("a not found", exp.expression("!d/d a"));
	}
	
	@Test
	public void testExpression15() {
		assertEquals("6*zoo^3", exp.expression("6*zoo^3"));
		assertEquals("o not found", exp.expression("!d/d o"));
	}

	@Test
	public void testExpression16() {
		assertEquals("1+x+y", exp.expression("1+x+y"));
		assertEquals("Input out of range", exp.expression("!d/d x y"));
	}
	@Test
	public void testExpression17() {
		assertEquals("1-zoo", exp.expression("1-zoo"));
		assertEquals("Input out of range", exp.expression("!Simplif zoo=2"));
	}
	@Test
	public void testExpression18() {
		assertEquals("1+zoo+x", exp.expression("1+zoo+x"));
		assertEquals("Input out of range", exp.expression("!simplify zoo=2x=2"));
	}
	
	@Test
	public void testExpression19() {
		assertEquals("Input out of range", exp.expression("!simplify zoo=2"));
	}
	@Test
	public void testExpression20() {
		assertEquals("Input out of range", exp.expression("!simplify"));
	}
	@Test
	public void testExpression21() {
		assertEquals("Input out of range", exp.expression("!d/d x"));
	}
		
}
