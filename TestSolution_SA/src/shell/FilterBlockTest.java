package shell;

import static org.junit.Assert.assertNotEquals;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import ui.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import Connect.GetSelectedShares;
import entity.CollectionTable;
import entity.Filter;
import entity.FilterConditions;

@RunWith(BlockJUnit4ClassRunner.class)
public class FilterBlockTest extends AbsMainFrame {
	FilterConditions filterList;

	@Before
	public void setUp() throws Exception {

		filterList = getInstance().filterConditions;
		// filterList.getFilters().clear();
	};

	public void InitToNullFilter() {
		filterList.getFilters().clear();
	}

	public void InitToTenFilter() {
		InitToNullFilter();
		filterList.addFilter(new Filter("涨跌幅", ">", 3));
		filterList.addFilter(new Filter("涨跌幅", "<", 5));
		filterList.addFilter(new Filter("现价", "<", 30));
		filterList.addFilter(new Filter("现价", ">", 10));
		filterList.addFilter(new Filter("市盈率", ">", 0));
		filterList.addFilter(new Filter("市盈率", "<", 10));
		filterList.addFilter(new Filter("预测市盈率", ">", 0));
		filterList.addFilter(new Filter("预测市盈率", "<", 10));
		filterList.addFilter(new Filter("市净率", ">", 0));
		filterList.addFilter(new Filter("市净率", "<", 10));
	}

	public void InitToTwelveFilter() {
		InitToTenFilter();
		filterList.addFilter(new Filter("总股本", ">", 0));
		filterList.addFilter(new Filter("总股本", "<", 10));
	}
//	private final static CyclicBarrier swtBarrier2 = new CyclicBarrier(2);
	/*
	 * 测试收藏按钮
	 */
	@Test
	public void collectFilterTest() throws InterruptedException, BrokenBarrierException {
		InitToNullFilter();
		
		bot.button("收藏").click();
		bot.shell("命名收藏").activate();
		bot.activeShell().close();
		// 检测是否更新了下拉菜单
		getInstance().saveCollectionAndRefresh("空收藏", filterList);
		String[] item=bot.comboBox(0).items();
		System.out.println("item="+item[0]);
		int index=-1;
		for (int i = 0; i < item.length; i++) {
			if (item[i].equals("空收藏")) {
				index=i;
				break;
			}
		}
		assertNotEquals(index, -1);
//		//选择新增的条目并检查点击
//		bot.comboBoxInGroup(" ").setSelection(index);
//		assertEquals("空收藏",bot.comboBoxInGroup(" ").getText());
//		InitToTenFilter();
//		bot.button("收藏");
//		InitToTwelveFilter();
//		bot.button("收藏");

	}
   @Test
   public void deleteFilterTest(){
	   
   }
	private void assertOneFilterUi(Filter filter, int index) {
		assertEquals(filter.get_name(), bot.labelInGroup("过滤器", 3 * index)
				.getId());
		assertEquals(filter.get_sign() + filter.get_Value(),
				bot.labelInGroup("过滤器", 3 * index + 1).getText());
		assertEquals("删除", bot.labelInGroup("过滤器", 3 * index + 2).getText());
	}

	private void assertFilterBlock(FilterConditions _filterList) {
		ArrayList<Filter> filterArray = _filterList.getFilters();
		for (int i = 0; i < filterArray.size(); i++) {
			assertOneFilterUi(filterArray.get(i), i);
		}

	}

	/*
	 * 测试更新FilterBlock的ui
	 */
	@Test
	public void updateFilterComponentTest() {
		// Null的时候
		InitToNullFilter();
		getInstance().getFilterBlock().updateFilterComponent(filterList);
		assertFilterBlock(filterList);
		// 临界转换为ScrollPane的时候
		InitToTenFilter();
		getInstance().getFilterBlock().updateFilterComponent(filterList);
		// 应该转换为ScrollPane的时候
		InitToTwelveFilter();
		getInstance().getFilterBlock().updateFilterComponent(filterList);

		// SWTBotButton addButton=bot.button("+");
		// addButton.click();
		// SWTBotShell shell=bot.shell("涨跌幅");
		// shell.activate();
		// // System.out.println(bot.activeShell().getText());
		// // bot.label("涨跌幅:");
		// // SWTBotButton goButton=bot.button("GO");
		// // goButton.click();
		// bot.
		// SWTBotButton confirmButton=bot.button("确定");
		// confirmButton.click();
		// bot.textWithTooltip("格式为:比较符加数值") .setText("<6");
		//
		// SWTBotShell mainframe=bot.shell("选股器");
		// mainframe.activate();

	}
}
