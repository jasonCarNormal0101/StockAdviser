package ui;

/*
 * TableDemo.java requires no other files.
 */

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import Connect.GetSelectedShares;
import Connect.Share;
import entity.FilterConditions;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

public class TableDemo extends JDialog {

	/**
	 * TableDemo is just like SimpleTableDemo, except that it uses a custom
	 * TableModel.
	 */

	private boolean DEBUG = true;
	private FilterConditions _filterConditions;
	ArrayList<Share> _result = new ArrayList<Share>();

	public TableDemo(FilterConditions filterConditions) {
		super();
		_filterConditions = filterConditions;

		GetSelectedShares getSelectedShares = new GetSelectedShares();

		try {
			_result = getSelectedShares.getSelectedShares(filterConditions
					.toStringArray());
		} catch (IOException e) {
			Share failedShare=new Share();
			failedShare.setNetRate("");
			failedShare.setPe("");
			failedShare.setPrice("");
			failedShare.setShareCode(0);
			failedShare.setUpAndDownRange("");
			failedShare.setPredictPe("无网络");
		    ArrayList<Share> failedUIList=new ArrayList<Share>();
		    failedUIList.add(failedShare);
            _result=failedUIList;
		}
		JTable table = new JTable(new MyTableModel(_result));
		table.setPreferredScrollableViewportSize(new Dimension(500, 400));

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);

		// Add the scroll pane to this panel.
		add(scrollPane);

	}

	class MyTableModel extends AbstractTableModel {
		private String[] columnNames = { "股票代码", "股票简称", "涨跌幅", "现价", "市盈率",
				"预计市盈率", "市净率" };
		private ArrayList<Share> myresult=new ArrayList<Share>();

		// private Object[][] data ={};
		public MyTableModel(ArrayList<Share> result) {
			myresult = result;
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return myresult.size();
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			return myresult.get(row).getdata(col);
		}

		/*
		 * JTable uses this method to determine the default renderer/ editor for
		 * each cell. If we didn't implement this method, then the last column
		 * would contain text ("true"/"false"), rather than a check box.
		 */
//		public Class getColumnClass(int c) {
//			return getValueAt(0, c).getClass();
//		}

		/*
		 * Don't need to implement this method unless your table's editable.
		 */
		public boolean isCellEditable(int row, int col) {
			// Note that the data/cell address is constant,
			// no matter where the cell appears onscreen.
			if (col < 2) {
				return false;
			} else {
				return true;
			}
		}

		/*
		 * Don't need to implement this method unless your table's data can
		 * change.
		 */
		// public void setValueAt(Object value, int row, int col) {
		// if (DEBUG) {
		// System.out.println("Setting value at " + row + "," + col
		// + " to " + value + " (an instance of "
		// + value.getClass() + ")");
		// }
		//
		// data[row][col] = value;
		// fireTableCellUpdated(row, col);
		//
		// if (DEBUG) {
		// System.out.println("New value of data:");
		// printDebugData();
		// }
		// }

		// private void printDebugData() {
		// int numRows = getRowCount();
		// int numCols = getColumnCount();
		//
		// for (int i = 0; i < numRows; i++) {
		// System.out.print("    row " + i + ":");
		// for (int j = 0; j < numCols; j++) {
		// System.out.print("  " + data[i][j]);
		// }
		// System.out.println();
		// }
		// System.out.println("--------------------------");
		// }
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	public static void createAndShowGUI(FilterConditions filterConditions) {
		// Create and set up the window.
		TableDemo dlg = new TableDemo(filterConditions);
		dlg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Create and set up the content pane.
		// TableDemo newContentPane = new TableDemo(filterConditions);
		// newContentPane.setOpaque(true); // content panes must be opaque
		// dlg.setContentPane(newContentPane);

		// Display the window.
		dlg.pack();
		dlg.setVisible(true);
	}

	// public static void main(String[] args) {
	// //Schedule a job for the event-dispatching thread:
	// //creating and showing this application's GUI.
	// javax.swing.SwingUtilities.invokeLater(new Runnable() {
	// public void run() {
	// createAndShowGUI();
	// }
	// });
	// }
}
