package controllerTest;

//import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.anyOf;
//import static org.hamcrest.Matchers.greaterThan;
//import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.CatchStocksTodb;
import controller.CatchTongHuaShunTodb;
import controller.SQLdb;

abstract public class SQLdbTest {
	
	private  static SQLdb SQLdb;

	public SQLdbTest() {
		// TODO Auto-generated constructor stub
	
	}
	
	public static void BeforeClass(CatchStocksTodb crawStocks) 
			throws Exception {
		
		SQLdb = new SQLdb(new CatchTongHuaShunTodb());
		SQLdb.execute();
//		SQLdb.createdbTable();
//		System.out.println(SQLdb.getCount());
//		if(SQLdb.getCount() == 0){
//			System.out.println("sssssssssssss");
//			SQLdb.update();
//		}
	}

	public static void AfterClass() throws Exception {
		SQLdb.shutdow();
	}

	//测试数据库链接状态
	public static void connetion() {
		try {
			assertEquals(false, SQLdb.getConnection().isClosed());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	//测试数据库中记录的总数应该大于2000
	public static void count(){
		int count = SQLdb.getCount();
		assertThat(count, greaterThan(2000));
	}
	*/
	
	//测试市盈率pe极值
	public static void extenOf_pe(){
		extenValue("pe");
	}
	//测试涨跌幅极值
	public static void extenOf_priceChangeRatio(){
		extenValue("priceChangeRatio");
	}
	//测试现价极值
	public static void extenOf_curPrice(){
		extenValue("curPrice");
	}
	//测试动态市盈率极值
	public static void extenOf_dynamicPE(){
		extenValue("dynamicPE");
	}
	//测试市净率极值
	public static void extenOf_pb(){
		extenValue("pb");
	}
	
	//测试最大值最小值查询
	public static void extenValue(String condition){
		ResultSet rs = SQLdb.queryExtre(condition);
		try {
			double min = rs.getDouble(1);
			double max = rs.getDouble(2);
			assertNotEquals(max,min);
			//assertThat(max).isGreaterThan(min);
			//assertThat(min, lessThan(max));
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//测试市盈率pe查询的结果
	public static void resultOf_pe(){
		try {
			result("pe", -10.0, 20.0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//测试涨跌幅查询的结果
	public static void resultOf_priceChangeRatio(){
		try {
			result("priceChangeRatio", -10.0, 20.0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//测试现价查询的结果
	public static void resultOf_curPrice(){
		extenValue("curPrice");
		try {
			result("curPrice", 10.0, 20.0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//测试动态市盈率查询的结果
	public static void resultOf_dynamicPE(){
		extenValue("dynamicPE");
		try {
			result("dynamicPE", -10.0, 20.0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//测试市净率查询的结果
	public static void resultOf_pb(){
		extenValue("pb");
		try {
			result("pb", -10.0, 20);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//测试单一条件查询的结果
	public static void result(String condition, double min, double max) 
			throws SQLException{
		String query = "(" + condition + ">" + min
				+ " and " + condition + "<" + max + ")";
		ResultSet rs = SQLdb.query(query);
		while(rs.next()){
			double d = rs.getDouble(condition);
//			assertThat(d, anyOf(lessThan(max), greaterThan(min)));
			//assertThat(d, greaterThan(min));
			assertNotEquals(d,min);
			assertNotEquals(d,max);
			//assertThat(d, lessThan(max));
		}
	}
	
	
	//测试关闭数据库
	public static void shutdown(){
		SQLdb.shutdow();
		try {
			assertEquals(true, SQLdb.getConnection().isClosed());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
