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

import controller.GetSelectedShares;
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
		filterList.addFilter(new Filter("�ǵ��", ">", 3));
		filterList.addFilter(new Filter("�ǵ��", "<", 5));
		filterList.addFilter(new Filter("�ּ�", "<", 30));
		filterList.addFilter(new Filter("�ּ�", ">", 10));
		filterList.addFilter(new Filter("��ӯ��", ">", 0));
		filterList.addFilter(new Filter("��ӯ��", "<", 10));
		filterList.addFilter(new Filter("Ԥ����ӯ��", ">", 0));
		filterList.addFilter(new Filter("Ԥ����ӯ��", "<", 10));
		filterList.addFilter(new Filter("�о���", ">", 0));
		filterList.addFilter(new Filter("�о���", "<", 10));
	}

	public void InitToTwelveFilter() {
		InitToTenFilter();
		filterList.addFilter(new Filter("�ܹɱ�", ">", 0));
		filterList.addFilter(new Filter("�ܹɱ�", "<", 10));
	}
//	private final static CyclicBarrier swtBarrier2 = new CyclicBarrier(2);
	/*
	 * �����ղذ�ť
	 */
	@Test
	public void collectFilterTest() throws InterruptedException, BrokenBarrierException {
		InitToNullFilter();
		
		bot.button("�ղ�").click();
		bot.shell("�����ղ�").activate();
		bot.activeShell().close();
		// ����Ƿ�����������˵�
		getInstance().saveCollectionAndRefresh("���ղ�", filterList);
		String[] item=bot.comboBox(0).items();
		System.out.println("item="+item[0]);
		int index=-1;
		for (int i = 0; i < item.length; i++) {
			if (item[i].equals("���ղ�")) {
				index=i;
				break;
			}
		}
		assertNotEquals(index, -1);
//		//ѡ����������Ŀ�������
//		bot.comboBoxInGroup(" ").setSelection(index);
//		assertEquals("���ղ�",bot.comboBoxInGroup(" ").getText());
//		InitToTenFilter();
//		bot.button("�ղ�");
//		InitToTwelveFilter();
//		bot.button("�ղ�");

	}
   @Test
   public void deleteFilterTest(){
	   
   }
	private void assertOneFilterUi(Filter filter, int index) {
		assertEquals(filter.get_name(), bot.labelInGroup("������", 3 * index)
				.getId());
		assertEquals(filter.get_sign() + filter.get_Value(),
				bot.labelInGroup("������", 3 * index + 1).getText());
		assertEquals("ɾ��", bot.labelInGroup("������", 3 * index + 2).getText());
	}

	private void assertFilterBlock(FilterConditions _filterList) {
		ArrayList<Filter> filterArray = _filterList.getFilters();
		for (int i = 0; i < filterArray.size(); i++) {
			assertOneFilterUi(filterArray.get(i), i);
		}

	}

	/*
	 * ���Ը���FilterBlock��ui
	 */
	@Test
	public void updateFilterComponentTest() {
		// Null��ʱ��
		InitToNullFilter();
		getInstance().getFilterBlock().updateFilterComponent(filterList);
		assertFilterBlock(filterList);
		// �ٽ�ת��ΪScrollPane��ʱ��
		InitToTenFilter();
		getInstance().getFilterBlock().updateFilterComponent(filterList);
		// Ӧ��ת��ΪScrollPane��ʱ��
		InitToTwelveFilter();
		getInstance().getFilterBlock().updateFilterComponent(filterList);

		// SWTBotButton addButton=bot.button("+");
		// addButton.click();
		// SWTBotShell shell=bot.shell("�ǵ��");
		// shell.activate();
		// // System.out.println(bot.activeShell().getText());
		// // bot.label("�ǵ��:");
		// // SWTBotButton goButton=bot.button("GO");
		// // goButton.click();
		// bot.
		// SWTBotButton confirmButton=bot.button("ȷ��");
		// confirmButton.click();
		// bot.textWithTooltip("��ʽΪ:�ȽϷ����ֵ") .setText("<6");
		//
		// SWTBotShell mainframe=bot.shell("ѡ����");
		// mainframe.activate();

	}
}
