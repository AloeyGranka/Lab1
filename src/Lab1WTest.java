import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @Title: Lab1Test.java 
 * @Package  
 * @Description: Lab1 derivative方法的白盒测试
 * @author Shuqq 
 * @date 2016年11月9日 上午9:11:48
 * 
 */

/** 
 * @ClassName: Lab1Test 
 * @Description: Lab1 derivative方法的白盒测试
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
	 * Test method for {@link Lab1#derivative(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testDerivative1() {
		assertEquals("1", exp.derivative("x", "x"));
	}
	@Test
	public void testDerivative2() {
		assertEquals("2*zoo", exp.derivative("zoo^2", "zoo"));
	}
	@Test
	public void testDerivative3() {
		assertEquals("3*zoo^2", exp.derivative("zoo^3", "zoo"));
	}
	@Test
	public void testDerivative4() {
		assertEquals("6*2*zoo", exp.derivative("6*zoo^2", "zoo"));
	}
	@Test
	public void testDerivative5() {
		assertEquals("2*zoo*6", exp.derivative("zoo^2*6", "zoo"));
	}
	@Test
	public void testDerivative6() {
		assertEquals("3*2*x*a", exp.derivative("3*x^2*a", "x"));
	}
	@Test
	public void testDerivative7() {
		assertEquals("2", exp.derivative("a*2", "a"));
	}
	@Test
	public void testDerivative8() {
		assertEquals("3*a^2", exp.derivative("a*a*a", "a"));
	}
	@Test
	public void testDerivative9() {
		assertEquals("2*b*3*a^2", exp.derivative("a*2*a*a*b", "a"));
	}
	
	
	

}
