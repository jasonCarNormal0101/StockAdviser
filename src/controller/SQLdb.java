package controller;

import interfac.DBCatcher;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mybase.DBbase;

import org.apache.http.client.ClientProtocolException;
//import org.eclipse.ui.Saveable;
import org.json.JSONArray;
import org.json.JSONException;

public class SqlDB{
	
	public static final String[] TABLE_COL_NAME = 
			new String[]{"pcRadio", "curPrice", "pe", "dynamicPE", "pb"};
	
	private DBCatcher crawer;
	private DBbase DB;


	private JSONArray stockJA;
	private String tableName;
	private Connection connection;
	private Statement statement;
	public SqlDB(DBCatcher crawStocks){
		DB = DBbase.Instance();
		statement = DB.getStatement();
		connection = DB.getConnection();
		this.crawer = crawStocks;
		
		this.tableName = crawStocks.getTableName();
		
		createTable();
	}
	
	public void execute(){
		crawer.catching();
		this.stockJA = crawer.getDataArray();
		this.tableName = crawer.getTableName();

		try {
			insertData();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public void update(){
		
		crawer.update();

		this.stockJA = crawer.getDataArray();
		try{
			queryExtre("code");
			executeSQL("drop table " + tableName);
			connection.commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		createTable();
		
		try {
			insertData();
		} catch (JSONException e) {

			e.printStackTrace();
		}
	}
	public Boolean executeSQL(String sql) throws SQLException{
		return statement.execute(sql);

	}
	
	
	public ResultSet query(String conditionSql){
		String querySql = "select * from " + tableName;
		if(conditionSql != ""){
			querySql += " where " + conditionSql;
		}
		ResultSet rs = null;
		try {
			rs = statement.executeQuery(querySql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet query() {
		String querySql = "select * from " + tableName;
		ResultSet rs = null;
		try {
			rs = statement.executeQuery(querySql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet queryExtre(String colName) {
		String sqlString = "select min("+colName+"),max("+colName+") from "+tableName;
		ResultSet rs = null;
		try {
			rs = statement.executeQuery(sqlString);
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs; 
	}
	
	//创建表
	public void createTable(){
		try {
			executeSQL(createTableSQL());
		} catch (SQLException e) {
		}
	}
	
	public String createTableSQL(){
		String sql = "create table " + tableName 
				+ "( code varchar(15), shortName varchar(60), "
				+ "pcRadio float(2), curPrice float(2), "
				+ "pe float(2), dynamicPE float(2), pb float(2))";
		
		return sql;
	}

	
	public void insert(String valueSql){
		try {
			executeSQL("insert into " + tableName + " values(" + valueSql + ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void insert(JSONArray ja){
		
		insert(jsonArray2insertsql(ja));
	}
	public String jsonArray2insertsql(JSONArray ja){
		String insertsql = "";
		try {
			insertsql = "'" + codeNum(ja.getString(0)) + "','" + ja.getString(1) + "',"
				+ emptyValue2null(ja.getString(2)) + "," + emptyValue2null(ja.getString(3)) + "," 
				+ emptyValue2null(ja.getString(4)) + "," + emptyValue2null(ja.getString(5)) + ","
				+ emptyValue2null(ja.getString(6));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return insertsql;
	}
	
	public String codeNum(String code){
		Pattern pattern = Pattern.compile("\\D");  
        Matcher matcher = pattern.matcher(code);  
        String codeStr = matcher.replaceAll("");
        return codeStr;
	}
	
	public String emptyValue2null(String str){
		if(str.equals("--")){
			return "NULL";
		}
		return str;
	}
	
	public void insertData() throws JSONException{
		int len = stockJA.length();
		for(int i = 0; i < len; ++i){
			JSONArray ja = stockJA.getJSONArray(i);
			insert(ja);
		}
		try {
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getCount(){
		int count = 0;
		try {
			ResultSet rs = query();	
			while(rs.next()){
				count++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	public void shutdow(){
		
		try {
			statement.close();
			connection.commit();
			connection.close();
			DriverManager.getConnection("jdbc:derby:;shutdown=true");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Committedtransactionandclosedconnection"); 
		
	}
	
	public Connection getConnection() {
		return connection;
	}

	public Statement getStatement() {
		return statement;
	}

	public JSONArray getStockArray() {
		return stockJA;
	}
}
