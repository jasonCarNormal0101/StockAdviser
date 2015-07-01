package ui;

import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//import model.CollectionCondition;
//import model.Condition;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import entity.Filter;
import entity.FilterConditions;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;

import controller.CollectionSL;
import controller.CatchXueQiuTodb;
import controller.CatchTongHuaShunTodb;
import controller.SQLdb;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShowResultDlg extends Dialog {
	private Table stockListTable;
	private Shell parentShell;
	private Shell _shell;
	private SQLdb SQLdb;
	private Composite stockListComposite;
	private Composite optionComposite;
	private Composite topComposite;
	private FormData fd_optionComposite;
	private FilterConditions _filterConditions;
	private CLabel lblSelect;
	private CLabel lblCollectCondition;
	private CLabel lblResetCondition;
	private CLabel lblStartReseach;

	private Composite collectComposite;
	private Composite colHeadComposite;
	private Composite collectionComposite;
	private CLabel lblCollect;

	private CLabel lblResultCount;
	private CLabel lblNewLabel;
	private Label lblNewLabel_1;
	private Label btnClose;
	private Label btnMin;
	private Composite selectComposite;
	private Composite selectHeadComposite;
	private ArrayList<TableItem> conditionItem;
//	private ArrayList<SQLdb> SQLdbs;
	private ArrayList<String> sourceNames;
	private Table conditionTable;
	private ArrayList<TableEditor> minEditorItem;
	private ArrayList<TableEditor> maxEditortem;
//	private ArrayList<Condition> textItem;
	private CollectionSL collectCondCtrl;
//	private ArrayList<CollectCondition> collCompArray;
	private static final String[] HEADER = new String[] { "序号", "股票代码", "股票简称",
			"涨跌幅(%)", "现价(元)", "市盈率(pe)", "动态市盈率", "市净率" };
	private static final int[] HEADER_SIZE = new int[] { 55, 90, 75, 75, 70,
			100, 85, 75 };
	public static final String[] TABLE_COL_NAME = 
			new String[]{"priceChangeRatio", "curPrice", "pe", "dynamicPE", "pb"};
	private static final String[] CHOICE_CONDITION = new String[] { "涨跌幅(%)",
		"现价(元)", "市盈率(pe)", "动态市盈率", "市净率" };
	public ShowResultDlg(Shell parent, FilterConditions filterConditions,SQLdb SQLdb) {
		super(parent, SWT.NONE);
		parentShell = getParent();
		_filterConditions = filterConditions;
		
		this.SQLdb=SQLdb;
		
	

	}

	public void open() {
		// TODO Auto-generated method stub
		_shell = new Shell(parentShell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		final Display display = _shell.getDisplay();
		initSql();
		shellCenter();
		createComposite();
		ResultSet rs = reseach();
		try {
			showResult(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Your code goes here (widget creation, set result, etc).
		_shell.open();
		// Display display = parent.getDisplay();
		while (!_shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

	}
	//使shell居中显示
		public void shellCenter(){
			Rectangle bounds = Display.getDefault().getPrimaryMonitor().getBounds();
			Rectangle rect = _shell.getBounds();
			int x = bounds.x + (bounds.width - rect.width) / 2;
			int y = bounds.y + (bounds.height - rect.height) / 3;
			_shell.setLocation(x, y);
		}
	private void createComposite() {
		// TODO Auto-generated method stub
//		topComposite = new Composite(_shell, SWT.NONE);
//		topComposite.setBounds(0, 0, 605, 403);
//		topComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
//		topComposite.setLayout(new FormLayout());
//		stockListComposite = new Composite(topComposite, SWT.NONE);
//		FormData fd_stockListComposite = new FormData();
//		fd_stockListComposite.bottom = new FormAttachment(100);
//		fd_stockListComposite.right = new FormAttachment(100);
//		stockListComposite.setLayoutData(fd_stockListComposite);
//		stockListComposite.setLocation(0, 0);
//		stockListComposite.setSize(611, 432);
////		FormData fd_stockListComposite = new FormData();
////		fd_stockListComposite.top = new FormAttachment(0, 0);
////		fd_stockListComposite.bottom = new FormAttachment(100, 0);
////		fd_stockListComposite.right = new FormAttachment(0, 0);
////		fd_stockListComposite.left = new FormAttachment(0, 0);
////		stockListComposite.setLayoutData(fd_stockListComposite);
//		stockListComposite.setBackground(SWTResourceManager
//				.getColor(SWT.COLOR_WHITE));
//		stockListComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
//		stockListTable = new Table(stockListComposite, SWT.BORDER
//				| SWT.FULL_SELECTION);
//		stockListTable.setHeaderVisible(true);
//		stockListTable.setLinesVisible(true);
//		for (int i = 0; i < HEADER.length; ++i) {
//			TableColumn tblclmnNewColumn = new TableColumn(stockListTable,
//					SWT.CENTER);
//			tblclmnNewColumn.setWidth(HEADER_SIZE[i]);
//			tblclmnNewColumn.setText(HEADER[i]);
//			tblclmnNewColumn.setResizable(false);
//			tblclmnNewColumn.setAlignment(SWT.CENTER);
//			System.err.println("Header creaking");
//		}
//		try {
//			createTableItem();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		_shell = new Shell(parentShell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		_shell.setSize(638, 432);
		_shell.setText("搜素结果");
		_shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		topComposite = new Composite(_shell, SWT.NONE);
		topComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
		topComposite.setLayout(new FormLayout());

	
//		createOptionComposite();

		createStockListComposite();
	}
	public void createTableItem() throws SQLException{
		ResultSet rs = SQLdb.query();
	}
	public void createOptionComposite() {
		optionComposite = new Composite(topComposite, SWT.NONE);
		fd_optionComposite = new FormData();
		fd_optionComposite.top = new FormAttachment(0, 30);
		fd_optionComposite.bottom = new FormAttachment(100, -10);
		fd_optionComposite.right = new FormAttachment(0, 331);
		fd_optionComposite.left = new FormAttachment(0, 10);
		optionComposite.setLayoutData(fd_optionComposite);
		optionComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));

		createSelectComposite();
		
//		createCollectComposite();
		
		
	}
	public void createSelectComposite() {

		selectComposite = new Composite(optionComposite, SWT.NONE);
		selectComposite.setSize(321, 204);
		selectComposite.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));

		createSelectHeadComposite();
		
		createConditionTable();
		
		operation();
	}
	public void initSql(){
//		if(SQLdbs == null){
//			SQLdbs = new ArrayList<SQLdb>();
//		}
		if(sourceNames == null){
			sourceNames = new ArrayList<String>();
		}
		
		CatchTongHuaShunTodb ths = new CatchTongHuaShunTodb();
		CatchXueQiuTodb xueqiu = new CatchXueQiuTodb();
		
//		SQLdbs.add(new SQLdb(ths));
//		SQLdbs.add(new SQLdb(xueqiu));
		
		sourceNames.add(ths.getSourceName());
		sourceNames.add(xueqiu.getSourceName());
		
//		SQLdb = SQLdbs.get(0);
	}
	public void createSelectHeadComposite(){
		selectHeadComposite = new Composite(selectComposite, SWT.NONE);
		selectHeadComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		selectHeadComposite.setBounds(0, 0, 321, 30);
		
		lblSelect = new CLabel(selectHeadComposite, SWT.SHADOW_NONE);
		lblSelect.setLocation(0, 0);
		lblSelect.setSize(80, 30);
		lblSelect.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblSelect.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12,
				SWT.BOLD));
		lblSelect.setText("选股");
		lblSelect.setAlignment(SWT.CENTER);
		
		final Combo combo = new Combo(selectHeadComposite, SWT.READ_ONLY);
		combo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		combo.setToolTipText("选取数据来源");
		combo.setBounds(230, 2, 88, 25);
		
		String[] arr = new String[sourceNames.size()];
		sourceNames.toArray(arr);
		combo.setItems(arr);
		combo.select(0);
		combo.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				int index = combo.getSelectionIndex();
//				SQLdb = SQLdbs.get(index);
//				try {
//					setExtreValue();
//				} catch (SQLException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				for(TableItem ti : conditionItem){
					ti.setChecked(false);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void createConditionTable() {
		conditionTable = new Table(selectComposite, SWT.CHECK
				| SWT.FULL_SELECTION | SWT.MULTI);
		conditionTable.setLocation(0, 30);
		conditionTable.setSize(321, 134);
		conditionTable.setHeaderVisible(true);
		conditionTable.setLinesVisible(true);

		TableColumn tcCondition = new TableColumn(conditionTable, SWT.NONE);
		tcCondition.setResizable(false);
		tcCondition.setWidth(117);
		tcCondition.setText("筛选条件");

		TableColumn tcMin = new TableColumn(conditionTable, SWT.NONE);
		tcMin.setResizable(false);
		tcMin.setWidth(102);
		tcMin.setText("最小值");

		TableColumn tcMax = new TableColumn(conditionTable, SWT.NONE);
		tcMax.setResizable(false);
		tcMax.setWidth(102);
		tcMax.setText("最大值");

		conditionItem = new ArrayList<TableItem>();
		minEditorItem = new ArrayList<TableEditor>();
		maxEditortem = new ArrayList<TableEditor>();
//		textItem = new ArrayList<Condition>();
		
		for (int i = 0; i < CHOICE_CONDITION.length; ++i) {
			TableItem tableItem_1 = new TableItem(conditionTable, SWT.NONE);
			tableItem_1.setText(new String[] { CHOICE_CONDITION[i], "0.00",
					"100.00" });
			tableItem_1.setText(CHOICE_CONDITION[i]);
			// tableItem_1.set
			
			final TableEditor editorMin = new TableEditor(conditionTable);
			final TableEditor editorMax = new TableEditor(conditionTable);

			// 创建一个文本框，用于输入文字
			final Text textMin = new Text(conditionTable, SWT.NONE);
			final Text textMax = new Text(conditionTable, SWT.NONE);

			// 将文本框当前值，设置为表格中的值
			textMin.setText(tableItem_1.getText(1));
			textMax.setText(tableItem_1.getText(2));
			// 设置编辑单元格水平填充
			editorMin.grabHorizontal = true;
			editorMax.grabHorizontal = true;
			// 关键方法，将编辑单元格与文本框绑定到表格的第一列
			editorMin.setEditor(textMin, tableItem_1, 1);
			editorMax.setEditor(textMax, tableItem_1, 2);
			// 当文本框改变值时，注册文本框改变事件，该事件改变表格中的数据。
			// 否则即使改变的文本框的值，对表格中的数据也不会影响
			textMin.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					editorMin.getItem().setText(1, textMin.getText());
				}
			});
			textMax.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					editorMax.getItem().setText(2, textMax.getText());
				}
			});
			
			conditionItem.add(tableItem_1);
			minEditorItem.add(editorMin);
//			minTextItem.add(textMin);
			maxEditortem.add(editorMax);
//			maxTextItem.add(textMax);
//			textItem.add(new Condition(i, textMin, textMax));
		}
		
//		try {
//			setExtreValue();
//		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
//	// 设置筛选条件的最大值最小值
//		public void setExtreValue() throws SQLException {
//				for (int i = 0; i < conditionItem.size(); ++i) {
//					Text mintext = (Text) textItem.get(i).getMin();
//					Text maxtext = (Text) textItem.get(i).getMax();
//					
//					ResultSet rs = SQLdb.queryExtre(TABLE_COL_NAME[i]);
//					String min = rs.getString(1);
//					String max = rs.getString(2);
//					TableItem tim = conditionItem.get(i);
//					if(min == null || max == null){
//						tim.setGrayed(true);
//						min = "--";
//						max = "--";
//					}
//					else {
//						tim.setGrayed(false);
//					}
//					
//					
//					mintext.setText(min);
//					maxtext.setText(max);
//				}
//			}
		

	public void operation() {
		lblCollectCondition = new CLabel(selectComposite, SWT.CENTER);
		lblCollectCondition.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		lblCollectCondition.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_SELECTION));
		lblCollectCondition.setBounds(10, 180, 67, 23);
		lblCollectCondition.setText("收藏条件");
//		lblCollectCondition.addMouseTrackListener(new CursorListener(_shell));
//		collectListerner();
//		
		lblResetCondition = new CLabel(selectComposite, SWT.CENTER);
		lblResetCondition.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_SELECTION));
		lblResetCondition.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		lblResetCondition.setBounds(125, 180, 67, 23);
		lblResetCondition.setText("重置条件");
//		lblResetCondition.addMouseTrackListener(new CursorListener(_shell));
//		reset();

		lblStartReseach = new CLabel(selectComposite, SWT.CENTER);
		lblStartReseach.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		lblStartReseach.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_SELECTION));
		lblStartReseach.setBounds(244, 180, 67, 23);
		lblStartReseach.setText("开始搜索");
		
//		lblStartReseach.addMouseTrackListener(new CursorListener(_shell));
//		startReseach();
	}
	public void reset(){
		lblResetCondition.addMouseListener(new MouseListenerAdapt() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
//				try {
//					setExtreValue();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				for(TableItem ti : conditionItem){
					ti.setChecked(false);
				}
			}
		});
	}
	//	lblCollectCondition.addMouseTrackListener(new CursorListener(shell));
	public void collectListerner(){
		lblCollectCondition.addMouseListener(new MouseListenerAdapt() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
//				ConditionNameDialog nameDialog = new ConditionNameDialog(_shell);
//				nameDialog.open();
//				String name = nameDialog.getName();
//				if(name == null){
//					return ;
//				}
//				setChecked();
//				CollectionCondition cc = 
//						new CollectionCondition(name, textItem);
//				try {
//					collectCondCtrl.addCollection(cc);
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				collectCondCtrl.save();
//				try {
//					showCollection();
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
		});
	}
//	//显示收藏条件
//		public void showCollection() throws JSONException{
//			if(collCompArray != null){
//				for(CollectCondition cctemp : collCompArray){
//					cctemp.dispose();
//				}
//			}
//			collCompArray = null;
//			collCompArray = new ArrayList<CollectCondition>();
//			
//			collectionComposite.setVisible(true);
//			collectionComposite.layout(true);
//			
//			JSONArray conditionArray = collectCondCtrl.getConditionArray();
//			for(int i = 0; i < conditionArray.length(); ++i){
//				final JSONObject jo = conditionArray.getJSONObject(i);
//				String name = jo.getString("name");
//				
//				CollectCondition cc = new CollectCondition(collectionComposite, i);
//				cc.setSize(321, 30);
//				cc.setLocation(0, 35*i + 5);
//				CLabel lblCondition = cc.getLblCollectCondition();
//				lblCondition.setText(name);
//				lblCondition.addMouseTrackListener(new CursorListener(cc));
//				cc.setVisible(true);
//				
//				lblCondition.addMouseListener(new MouseListenerAdapt() {
//					
//					@Override
//					public void mouseUp(MouseEvent arg0) {
//						// TODO Auto-generated method stub
//						try {
//							showCondition(jo);
//						} catch (JSONException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				});
//				
//				Label lblDelete = cc.getLblDelete();
//				lblDelete.addMouseListener(new MouseListenerAdapt() {
//					
//					@Override
//					public void mouseUp(MouseEvent e) {
//						// TODO Auto-generated method stub
//						Label lbl = (Label) e.getSource();
//						int index = (int) lbl.getData("index");
//						try {
//							collectCondCtrl.deleteConllection(index);
//						} catch (JSONException e2) {
//							// TODO Auto-generated catch block
//							e2.printStackTrace();
//						}
//						collectCondCtrl.save();
//						try {
//							showCollection();
//						} catch (JSONException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//					}
//				});
//				
//				collCompArray.add(cc);
//			}
//		}
//		//显示选中的收藏的条件
//		public void showCondition(JSONObject jo) throws JSONException{
//			JSONArray ja = jo.getJSONArray("condition");
//			
//			for(int i = 0; i < conditionItem.size(); ++i){
//				conditionItem.get(i).setChecked(false);
//				
//				JSONObject jotemp = ja.getJSONObject(i);
//				Boolean isChosen = jotemp.getBoolean("isChosen");
//				if(!isChosen){
//					continue;
//				}
//				conditionItem.get(i).setChecked(isChosen);
//				textItem.get(i).setIsChosen(isChosen);
//				((Text) textItem.get(i).getMin())
//					.setText(jotemp.getString("min"));
//				((Text) textItem.get(i).getMax())
//					.setText(jotemp.getString("max"));
//			}
//		}
//	public void setChecked(){
//		for(int i = 0; i < conditionItem.size(); ++i){
//			textItem.get(i).setIsChosen(
//					conditionItem.get(i).getChecked());
//		}
//	}
	public void createStockListComposite() {
//		lblResultCount = new CLabel(topComposite, SWT.NONE);
//		lblResultCount.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.BOLD));
//		lblResultCount.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
//		FormData fd_lblResultCount = new FormData();
//		fd_lblResultCount.right = new FormAttachment(optionComposite, 273, SWT.RIGHT);
//		fd_lblResultCount.top = new FormAttachment(optionComposite, 0, SWT.TOP);
//		fd_lblResultCount.left = new FormAttachment(optionComposite, 6);
//		lblResultCount.setLayoutData(fd_lblResultCount);
//		lblResultCount.setText("共有 0 股符合条件");
		
		stockListComposite = new Composite(topComposite, SWT.NONE);
		FormData fd_stockListComposite = new FormData();
		
		stockListComposite.setLayoutData(fd_stockListComposite);
		stockListComposite.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		stockListComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		stockListTable = new Table(stockListComposite, SWT.BORDER
				| SWT.FULL_SELECTION);
		stockListTable.setHeaderVisible(true);
		stockListTable.setLinesVisible(true);
		for (int i = 0; i < HEADER.length; ++i) {
			TableColumn tblclmnNewColumn = new TableColumn(stockListTable,
					SWT.CENTER);
			tblclmnNewColumn.setWidth(HEADER_SIZE[i]);
			tblclmnNewColumn.setText(HEADER[i]);
			tblclmnNewColumn.setResizable(false);
			tblclmnNewColumn.setAlignment(SWT.CENTER);
		}

		try {
			createTableItem();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void clearTableItem() {
		stockListTable.removeAll();
	}

	public void showResult(ResultSet rs) throws SQLException {

//		clearTableItem();
		System.out.println("Table showing");
		int index = 1;
		while (rs.next()) {
			TableItem tableItem = new TableItem(stockListTable, SWT.CENTER);
			String[] text = new String[8];

			text[0] = String.valueOf(index++);

			for (int i = 1; i < 8; ++i) {
				String str = rs.getString(i);
				if (str == null)
					str = "--";
				text[i] = str;
				System.out.print("Table:"+str+"  \r\n");
			}
			tableItem.setText(text);
			
		}
		rs.close();

//		lblResultCount.setText("共有 " + (index - 1) + " 股符合条件");
	}

	public void startReseach() {
		
				
				ResultSet rs = reseach();
				try {
					showResult(rs);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	//开始搜索
	public ResultSet reseach(){
		ResultSet rs = null;
		String querySql = "";
		ArrayList<Filter> filters=_filterConditions.getFilters();
		for(int i = 0; i < filters.size(); ++i){
			Filter flt = filters.get(i);
			
			querySql += flt.getRealName()+flt.get_sign()+flt.get_Value()+ " and ";
		
		}
		if(querySql == ""){
			querySql = "pe>100 and pe<0";
		}
		else{
			querySql = querySql.substring(0, querySql.length() - 5);
			querySql = "(" + querySql + ")" ;
		}
		
		rs = SQLdb.query(querySql);
		System.out.println("My sql query is:"+querySql);
		return rs;
	}
}
