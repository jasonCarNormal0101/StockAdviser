package Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import entity.CollectionTable;
import entity.Filter;
import entity.FilterConditions;


public class CollectionTableTest {

	@Test
	public void CollectionTableSaveTest() {
		CollectionTable t = new CollectionTable();
		FilterConditions f = new FilterConditions();
		Filter f1 = new Filter("涨跌幅",">",10);
		f.addFilter(f1);
		t.saveCollection("testCollection", f);
		FilterConditions g = t.getFilterConditions("testCollection");
		assertEquals(10, g.getFilters().get(0).get_Value(),0);
		assertEquals(">", g.getFilters().get(0).get_sign());
		assertEquals("涨跌幅", g.getFilters().get(0).get_name());
	}
	
	@Test
	public void CollectionTableGetTest() {
		CollectionTable t = new CollectionTable();
		assertEquals(null,t.getFilterConditions(" "));
	}
	
	@Test
	public void CollectionTableGetNameTest() {
		CollectionTable t = new CollectionTable();
		FilterConditions f = new FilterConditions();
		Filter f1 = new Filter("涨跌幅",">",10);
		f.addFilter(f1);
		t.saveCollection("testCollection", f);
		String[] s = t.getNameArray();
		t.removeCollection("testCollection");
		boolean check=false;
		for(int i=0;i<s.length;i++)
		{
			System.out.println(s[i]);
			if(s[i].equals("testCollection"))
				check=true;
		}
		assertEquals(true,check);
	}
	
	@Test
	public void CollectionTableRemoveTest() {
		CollectionTable t = new CollectionTable();
		assertEquals(null,t.getFilterConditions(" "));
		t.removeCollection("testCollection");
		assertEquals(0,t.getFilterConditions("testCollection").getFilters().size());
		
		FilterConditions f = new FilterConditions();
		Filter f1 = new Filter("涨跌幅",">",10);
		f.addFilter(f1);
		t.saveCollection("testCollection", f);
		assertEquals(1, t.getFilterConditions("testCollection").getFilters().size());
	}
	

}
