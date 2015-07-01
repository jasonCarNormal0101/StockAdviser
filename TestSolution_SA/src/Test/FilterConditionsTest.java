package Test;
import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;

import entity.Filter;
import entity.FilterConditions;


public class FilterConditionsTest {

	@Test
	public void FilterConditionsAddAndGetTest() {
		FilterConditions f = new FilterConditions();
		Filter f1 = new Filter("涨跌幅",">",10);
		f.addFilter(f1);
		assertEquals(f1, f.getFilters().get(0));
	}
	@Test
	public void FilterConditionsFindTest() {
		FilterConditions f = new FilterConditions();
		Filter f1 = new Filter("涨跌幅",">",10);
		assertEquals("priceChangeRatio", f1.getRealName());
		f.addFilter(f1);
		assertEquals(f1, f.findFilter("涨跌幅", ">"));
		assertEquals(null, f.findFilter("涨跌幅", "<"));
	}
	
	@Test
	public void FilterConditionsRemoveTest() {
		FilterConditions f = FilterConditionsCreate();
		f.remove(f.getFilters().get(0));
		assertEquals(4, f.getFilters().size());
		f.remove(f.getFilters().get(0));
		assertEquals(3, f.getFilters().size());
		f.remove(f.getFilters().get(0));
		assertEquals(2, f.getFilters().size());
		f.remove(f.getFilters().get(0));
		assertEquals(1, f.getFilters().size());
		f.remove(f.getFilters().get(0));
		assertEquals(0, f.getFilters().size());
	}
	
	@Test
	public void FilterConditionsAddExistTest() {
		FilterConditions f = FilterConditionsCreate();
		Filter f1 = new Filter("涨跌幅",">",20);
		f.addFilter(f1);
		assertEquals(20, f.findFilter("涨跌幅",">").get_Value(),0);
	}
	
	@Test
	public void FilterConditionsToStringArrayTest() throws UnsupportedEncodingException {
		FilterConditions f = FilterConditionsCreate();
		assertEquals(URLEncoder.encode("涨跌幅", "utf-8")+">10.0", f.toStringArray().get(0));
		assertEquals(URLEncoder.encode("现价", "utf-8")+">10.0", f.toStringArray().get(1));
		assertEquals(URLEncoder.encode("市盈率", "utf-8")+">10.0", f.toStringArray().get(2));
		assertEquals(URLEncoder.encode("预测市盈率", "utf-8")+">10.0", f.toStringArray().get(3));
		assertEquals(URLEncoder.encode("市净率", "utf-8")+">10.0", f.toStringArray().get(4));
	}
	
	
	private FilterConditions FilterConditionsCreate() {
		FilterConditions f = new FilterConditions();
		Filter f1 = new Filter("涨跌幅",">",10);
		Filter f2 = new Filter("现价",">",10);
		Filter f3 = new Filter("市盈率",">",10);
		Filter f4 = new Filter("预测市盈率",">",10);
		Filter f5 = new Filter("市净率",">",10);
		f.addFilter(f1);
		f.addFilter(f2);
		f.addFilter(f3);
		f.addFilter(f4);
		f.addFilter(f5);
		return f;
	}

}
