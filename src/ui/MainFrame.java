package ui;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Collection;

import javax.security.auth.Refreshable;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.commands.ToggleState;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import Connect.GetNewUri;

import com.sun.xml.internal.ws.wsdl.writer.document.OpenAtts;

import entity.CollectionTable;
import entity.Filter;
import entity.FilterConditions;

import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.DragDetectEvent;
import org.eclipse.swt.events.DragDetectListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.omg.CORBA.PUBLIC_MEMBER;

import util.MouseListenerAdapter;
import util.RefreshMethod;
import util.RefreshTask;

public class MainFrame {
	private Display display;
	protected Shell shell;
	private FilterBlock filterBlock;
	private Composite composite;
	public FilterConditions filterConditions = new FilterConditions();
	private CollectionBlock collectionBlock;

	/**
	 * @wbp.parser.entryPoint
	 */

	public MainFrame() {
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
	}

	public void open() {
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		// TODO Auto-generated method stub
		shell = new Shell(display, SWT.CLOSE | SWT.MIN);
		shell.setSize(566, 470);
		shell.setText("选股器");

		composite = new Composite(shell, SWT.NONE);
		composite.setFont(SWTResourceManager.getFont("Microsoft Yi Baiti", 9,
				SWT.BOLD));
		composite.setBounds(0, 0, 560, 433);

		Composite composite_1 = new Composite(composite, SWT.BORDER);
		composite_1.setBounds(0, 0, 175, 433);

		Label label = new Label(composite_1, SWT.NONE);
		label.setBounds(10, 10, 61, 17);
		label.setText("条件类型");

		Label lb_prizelimit = new Label(composite_1, SWT.NONE);
		lb_prizelimit.setBounds(35, 49, 61, 17);
		lb_prizelimit.setText("涨跌幅");

		Label lb_prize = new Label(composite_1, SWT.NONE);
		lb_prize.setText("现价");
		lb_prize.setBounds(35, 82, 61, 17);

		Label lb_pe = new Label(composite_1, SWT.NONE);
		lb_pe.setText("市盈率");
		lb_pe.setBounds(35, 117, 61, 17);

		Label lb_forePE = new Label(composite_1, SWT.NONE);
		lb_forePE.setText("预测市盈率");
		lb_forePE.setBounds(35, 153, 61, 17);

		Label lb_pb = new Label(composite_1, SWT.NONE);
		lb_pb.setText("市净率");
		lb_pb.setBounds(35, 189, 61, 17);

		Label lb_totalquity = new Label(composite_1, SWT.NONE);
		lb_totalquity.setText("总股本");
		lb_totalquity.setBounds(35, 225, 61, 17);

		AddButton btn_addPL = new AddButton(composite_1,
				lb_prizelimit.getText(), filterConditions);
		btn_addPL.setBounds(99, 49, 21, 17);

		AddButton btn_addPrize = new AddButton(composite_1, lb_prize.getText(),
				filterConditions);
		btn_addPrize.setBounds(99, 82, 21, 17);

		AddButton btn_addPE = new AddButton(composite_1, lb_pe.getText(),
				filterConditions);
		btn_addPE.setBounds(99, 117, 21, 17);

		AddButton btn_addFPE = new AddButton(composite_1, lb_forePE.getText(),
				filterConditions);
		btn_addFPE.setBounds(99, 153, 21, 17);

		AddButton btn_addPB = new AddButton(composite_1, lb_pb.getText(),
				filterConditions);
		btn_addPB.setBounds(99, 189, 21, 17);

		AddButton btn_addTQ = new AddButton(composite_1,
				lb_totalquity.getText(), filterConditions);
		btn_addTQ.setBounds(99, 225, 21, 17);

		Button btnGo = new Button(composite_1, SWT.NONE);
		btnGo.setBounds(35, 297, 80, 27);
		btnGo.setText("GO");
		btnGo.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				// GetNewUri getNewUri = new GetNewUri(new
				// String[]{"pe<30","pe>20"});
				//
				// System.out.println("test:"+getNewUri.getNewUri());
				// TableDemo tableDemo=new TableDemo();
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						TableDemo.createAndShowGUI(filterConditions);
					}
				});
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		// Label label_1 = new Label(composite, SWT.NONE);
		// label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI Light",
		// 12, SWT.BOLD));
		// label_1.setBounds(191, 10, 87, 27);
		// label_1.setText("过滤器");
		ScrolledComposite scrolledComposite=new ScrolledComposite(composite, SWT.V_SCROLL|SWT.BORDER);
		scrolledComposite.setBounds(216, 65,300, 294);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		collectionBlock = new CollectionBlock(composite, SWT.NONE);
		collectionBlock.setBounds(370, 20, 150, 40);
		filterBlock = new FilterBlock(scrolledComposite);
		filterBlock.setBounds(0, 0,300, 294);
		scrolledComposite.setContent(filterBlock);

		Button button = new Button(composite, SWT.NONE);
		button.setBounds(438, 366, 80, 27);
		button.setText("收藏");
		button.addSelectionListener(new CollectFilterListener());

		
		
	}

	private class CollectionBlock extends Composite implements RefreshMethod {
		Combo combo;
		String[] _items;

		public CollectionBlock(Composite parent, int style) {
			super(parent, style);
			// TODO Auto-generated constructor stub
			Group comboGroup = new Group(this, SWT.None);
			comboGroup.setText(" ");
			comboGroup.setBounds(346, 11, 175, 42);

			combo = new Combo(this, SWT.NONE);
			combo.setBounds(25, 10, 124, 25);
			combo.setText("此处选择已收藏");
			refresh();
			combo.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
				
					String selection=combo.getItem(combo.getSelectionIndex());
					System.out.println(selection);
					filterConditions=new CollectionTable().getFilterConditions(selection);
					new RefreshTask().addRefreshTask(display, filterBlock);
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub

				}
			});

		}

		public void setItems(String[] items) {
			_items = items;
		}

		@Override
		public void refresh() {
			// TODO Auto-generated method stub
			_items = new CollectionTable().getNameArray();
			for (int i = 0; i < _items.length; i++) {
				System.out.println(_items[i]);
			}
			combo.setItems(_items);
		}

	}

	public FilterBlock getFilterBlock() {
		return filterBlock;
	}

	public class FilterBlock extends Composite implements RefreshMethod {
		private ArrayList<FilterComponent> rdList;
		public Group group;

		public FilterBlock(Composite parent) {
			super(parent, SWT.V_SCROLL);
			setLayout(new  FillLayout());
			// this.rdList = new ArrayList<FilterComponent>();
			//FIXME  test Init
			InitToTwelveFilter();
			createBlock();
			rdList=new ArrayList<FilterComponent>();
		}


		public void updateFilterComponent(FilterConditions ls) {
			RefreshTask.addRefreshTask(display, this);
		}

		private void checkNDepose() {
			if (filterBlock != null && group != null) {
				filterBlock.dispose();
				group.dispose();
			}
		}

		@Override
		public void refresh() {
			// checkNDepose();
			for (int i = 0; i < rdList.size(); i++) {
				rdList.get(i).dispose();
			}
			for (int i = 0; i < filterConditions.getFilters().size(); ++i) {
				FilterComponent fc = new FilterComponent(group,SWT.V_SCROLL,
						filterConditions.getFilters().get(i));
				fc.setBounds(10, 30 + 30 * i, 340, 30);

				rdList.add(fc);
			}
		}

		private void createBlock() {
			// TODO Auto-generated method stub
//			style = filterConditions.getFilters().size() < 10 ? SWT.BORDER
//					:SWT.BORDER| SWT.V_SCROLL;
			// group.setText("");
		
			group = new Group(this, SWT.NONE);
			group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
			group.setLayout(new GridLayout(1, false));
			group.setText("过滤器");
			group.setFont(SWTResourceManager.getFont(
					"Microsoft YaHei UI Light", 17, SWT.BOLD));		
			group.setBounds(0, 0, 300, 300);
			// System.out.println(group.toString());
			// System.out.println(filterBlock.getBounds().toString());

		}

	}
	public void InitToTwelveFilter() {
		filterConditions.addFilter(new Filter("涨跌幅", ">", 3));
		filterConditions.addFilter(new Filter("涨跌幅", "<", 5));
		filterConditions.addFilter(new Filter("现价", "<", 30));
		filterConditions.addFilter(new Filter("现价", ">", 10));
		filterConditions.addFilter(new Filter("市盈率", ">", 0));
		filterConditions.addFilter(new Filter("市盈率", "<", 10));
		filterConditions.addFilter(new Filter("预测市盈率", ">", 0));
		filterConditions.addFilter(new Filter("预测市盈率", "<", 10));
		filterConditions.addFilter(new Filter("市净率", ">", 0));
		filterConditions.addFilter(new Filter("市净率", "<", 10));
		filterConditions.addFilter(new Filter("总股本", ">", 0));
		filterConditions.addFilter(new Filter("总股本", "<", 10));
	}
	private class AddButton {
		public Button button;

		public AddButton(Composite parent, String addWhat,
				FilterConditions filterlist) {
			button = new Button(parent, SWT.NONE);
			button.setText("+");
			button.addSelectionListener(new AddFilterListener(shell, addWhat,
					filterlist));
		}

		public void setBounds(int x, int y, int width, int height) {
			button.setBounds(x, y, width, height);
		}

		private class AddFilterListener extends MouseListenerAdapter {

			Shell _parent;
			String _addWhat;
			FilterConditions _filterList;

			public AddFilterListener(Shell parent, String addWhat,
					FilterConditions filterlist) {
				_parent = parent;
				_addWhat = addWhat;
				_filterList = filterlist;
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				AddFilterDlg dlg = new AddFilterDlg(_parent, _addWhat,
						_filterList, filterBlock);
				dlg.open();
				RefreshTask.addRefreshTask(shell.getDisplay(), filterBlock);
			}

		}

		// private class RefreshFilterBlock implements RefreshMethod{
		// public void refresh(){
		// composite_2.updateFilterComponent(filterConditions);
		// }
		// }
	}

	private class CollectFilterListener extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			NameCollectionDlg dlg = new NameCollectionDlg(shell);
			dlg.open();
			String text = dlg.getValue();
			if (text != null) {
			 saveCollectionAndRefresh(text, filterConditions);
			}
		}

	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		Display homeDisplay = Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(homeDisplay),
				new Runnable() {
					public void run() {
						try {
							MainFrame window = new MainFrame();
							window.open();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
	}

	public Shell getShell() {
		// TODO Auto-generated method stub
		return shell;
	}

	public void saveCollectionAndRefresh(String text,
			FilterConditions filterList) {
		// TODO Auto-generated method stub
		CollectionTable collections = new CollectionTable();
		collections.saveCollection(text, filterConditions);
		RefreshTask.addRefreshTask(display, collectionBlock);
	}
}
