package controllerTest;


//import static org.hamcrest.Matchers.greaterThan;
//import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.CatchXueQiuTodb;
import controller.CatchStocksTodb;
import controller.CatchTongHuaShunTodb;

public class TongHuaShundbTest extends SQLdbTest {


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		CatchStocksTodb tonghuashun = new CatchTongHuaShunTodb();
		SQLdbTest.BeforeClass(tonghuashun);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		SQLdbTest.AfterClass();
	}

	@Test
	// 测试数据库链接状态
	public void testConnetion() {
		SQLdbTest.connetion();
	}

	/*
	@Test
	// 测试数据库中记录的总数应该大于2000
	public void testCount() {
		SQLdbTest.count();
	}
	*/

	@Test
	// 测试市盈率pe极值
	public void testExtenOf_pe() {
		SQLdbTest.extenOf_pe();
	}

	@Test
	// 测试涨跌幅极值
	public void testExtenOf_priceChangeRatio() {
		SQLdbTest.extenOf_priceChangeRatio();
	}

	@Test
	// 测试现价极值
	public void testExtenOf_curPrice() {
		SQLdbTest.extenOf_curPrice();
	}

	@Test
	// 测试动态市盈率极值
	public void testExtenOf_dynamicPE() {
		SQLdbTest.extenOf_dynamicPE();
	}

	@Test
	// 测试市净率极值
	public void testExtenOf_pb() {
		SQLdbTest.extenOf_pb();
	}

	@Test
	// 测试市盈率pe查询的结果
	public void testResultOf_pe() {
		SQLdbTest.resultOf_pe();
	}

	@Test
	// 测试涨跌幅查询的结果
	public void testResultOf_priceChangeRatio() {
		SQLdbTest.resultOf_priceChangeRatio();
	}

	@Test
	// 测试现价查询的结果
	public void testResultOf_curPrice() {
		SQLdbTest.resultOf_curPrice();
	}

	@Test
	// 测试动态市盈率查询的结果
	public void testResultOf_dynamicPE() {
		SQLdbTest.resultOf_dynamicPE();
	}

	@Test
	// 测试市净率查询的结果
	public void testResultOf_pb() {
		SQLdbTest.resultOf_pb();
	}


}
