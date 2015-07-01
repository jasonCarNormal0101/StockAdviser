package Test;
import static org.junit.Assert.*;

import org.junit.Test;

import entity.Filter;


public class FilterEntityTest {

	@Test
	public void FilterCreateTest() {
		Filter f1 = new Filter("涨跌幅",">",10);
		assertEquals("pcRadio", f1.getRealName());
		Filter f2 = new Filter("现价",">",10);
		assertEquals("curPrice", f2.getRealName());
		Filter f3 = new Filter("市盈率",">",10);
		assertEquals("pe", f3.getRealName());
		Filter f4 = new Filter("预测市盈率",">",10);
		assertEquals("dynamicPE", f4.getRealName());
		Filter f5 = new Filter("市净率",">",10);
		assertEquals("pb", f5.getRealName());
	}
	
	@Test
	public void FilterNameTest() {
		Filter f1 = new Filter("涨跌幅",">",10);
		assertEquals("涨跌幅", f1.get_name());
	}
	
	@Test
	public void FilterSignTest() {
		Filter f1 = new Filter("涨跌幅",">",10);
		assertEquals(">", f1.get_sign());
	}
	
	@Test
	public void FilterValueTest() {
		Filter f1 = new Filter("涨跌幅",">",10);
		assertEquals(10, f1.get_Value(),0);
	}
}
