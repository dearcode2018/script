/**
 * 描述: 
 * ScriptTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.script;

// 静态导入
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.constant.Constant;
import com.hua.constant.ScriptConstant;
import com.hua.test.BaseTest;
import com.hua.util.ClassPathUtil;
import com.hua.util.ProjectUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * ScriptTest
 */
public final class ScriptTest extends BaseTest {

	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCallJs() {
		try {
			
			// javax.script.ScriptEngineManager 脚本引擎管理器
			final ScriptEngineManager sem = new ScriptEngineManager();
			// 通过名称获取脚本引擎
			final ScriptEngine se = sem.getEngineByName(ScriptConstant.JAVASCRIPT);
			
			// 脚本字符串 (直接解析脚本字符串来执行)
			String script = "println('content print');";
			// 执行 js 语句
			se.eval(script);
			
			// js 函数 (有参有返回值)
			String function = "function sayHello(name) {println('hello ' + name); return 'my name is ' + name;}";
			// 执行函数
			se.eval(function);
			
			// 强转为 可调用引擎 
			final Invocable invocableEngine = (Invocable) se;
			// 函数名，函数的参数
			String retValue = (String) invocableEngine.invokeFunction("sayHello", "zhengqianye");
			
			log.info("testCallJs =====> retValue = " + retValue);
			
			/**
			 注意调用的js文件，有些语句是不支持的，原因尚未查明，
			 比如在js的函数中直接写 alert() 是无法识别的函数，而print()或println()是可以识别的.
			 原因可能是jvm无法实现弹出对话框类似的功能.
			 
			 */
			
			// 加载/调用 js 脚本 (读取js脚本文件，进而调用脚本文件中的属性或方法)
			final Reader reader  = new InputStreamReader(ClassPathUtil.getInputStream("/conf/script/javascript.js"));
			//log.info("testCallJs =====> " + ClassPathUtil.getClassSubpath("/conf/script/javascript.js"));
			se.eval(reader);
			se.eval("welcome('zhang')");
			
			retValue = (String) invocableEngine.invokeFunction("welcome", "zhang");
			log.info("testCallJs =====> retValue = " + retValue);
			
			// 绑定 - 就是一个Set的子类型
			Bindings binds = null;
			
			// 脚本上下文 (脚本运行环境变量)
			ScriptContext context = null;
			
			
		} catch (Exception e) {
			log.error("testCallJs =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCallShell() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCallShell =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCallBat() {
		try {
			String command = "ping 127.0.0.1";
			command = "/bin/conf/script/win.bat";
			//command = "start d:\\English.txt";
			final Runtime rt = Runtime.getRuntime();
			Process ps = rt.exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream(), Constant.CHART_SET_GB2312));
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
			String result = sb.toString();
			
			System.out.println(result);
			
		} catch (Exception e) {
			log.error("testCallBat =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJavaCallBat() {
		try {
			String command = "ping 127.0.0.1";
			//command = "start d:\\English.txt";
			//command = "/bin/conf/script/win.bat";
			String path = ProjectUtil.getAbsolutePath("/doc/JavaCallBat.bat", true);
			System.out.println("path = " + path);
			command = "cmd /c start " + path;
			System.out.println("command = " + command);
			//command = "cmd /c start D:\\workspace\\java\\script\\doc\\JavaCallBat.bat";
			final Runtime rt = Runtime.getRuntime();
			rt.exec(command);
			
		} catch (Exception e) {
			log.error("testJavaCallBat =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCall() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCall =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTemp() {
		try {
			
			
		} catch (Exception e) {
			log.error("testTemp=====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCommon() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCommon =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSimple() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSimple =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void test() {
		try {
			
			
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 普通测试方法
	 ,@Test注解 不带参数
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testNormal() {
		System.out.println("testNormal()");
	}
	
	/**
	 * 
	 * 描述: 期望发生异常-测试方法
	 ,@Test注解 异常
	 * @author qye.zheng
	 * 
	 */
	@Test(expected=NullPointerException.class)
	@SuppressWarnings("all")
	public void testException() {
		String str = null;
		System.out.println(str.length());
	}
	
	/**
	 * 
	 * 描述: 超时测试方法
	 ,@Test注解 指定运行时间 (单位 : 毫秒)
	 * @author qye.zheng
	 * 
	 */
	@Test(timeout=3000)
	public void testTimeOut() {
		System.out.println("testTimeOut()");
	}
	
	/**
	 * 
	 * 描述: 测试忽略的方法
	 * @author qye.zheng
	 * 
	 */
	@Ignore("暂时忽略的方法")
	@Test
	public void ignoreMethod() {
		System.out.println("ignoreMethod()");
	}
	
	/**
	 * 
	 * 描述: [所有测试]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@BeforeClass
	public static void beforeClass() {
		System.out.println("beforeClass()");
	}
	
	/**
	 * 
	 * 描述: [所有测试]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println("afterClass()");
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@Before
	public void beforeMethod() {
		System.out.println("beforeMethod()");
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@After
	public void afterMethod() {
		System.out.println("afterMethod()");
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@Ignore("解决ide静态导入消除问题 ")
	private void noUse() {
		String expected = null;
		String actual = null;
		Object[] expecteds = null;
		Object[] actuals = null;
		String message = null;
		
		assertEquals(expected, actual);
		assertEquals(message, expected, actual);
		assertNotEquals(expected, actual);
		assertNotEquals(message, expected, actual);
		
		assertArrayEquals(expecteds, actuals);
		assertArrayEquals(message, expecteds, actuals);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(message, true);
		assertTrue(message, true);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(message, expecteds, actuals);
		assertNotSame(message, expecteds, actuals);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(message, actuals);
		assertNotNull(message, actuals);
		
		assertThat(null, null);
		assertThat(null, null, null);
		
		fail();
		fail("Not yet implemented");
		
	}

}
