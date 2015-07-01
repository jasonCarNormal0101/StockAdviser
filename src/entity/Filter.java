package entity;

import java.util.Collection;

import org.json.JSONException;
import org.json.JSONObject;

public class Filter {

	public static final String[] TABLE_COL_NAME = new String[] {
			"pcRadio", "curPrice", "pe", "dynamicPE", "pb" };
	private static final String[] HEADER = new String[] { "涨跌幅", "现价",
			"市盈率", "预测市盈率", "市净率" };
	private String _name;
	private String _sign;// 符号
	private float _value;// 数值

	// private Boolean _activated;// 是否激活

	// public Filter(String sign, float value) {
	// _sign = sign;
	// _value = value;
	// }

	public Filter(String name, String sign, float value) {
		_name = name;
		_sign = sign;
		_value = value;

	};

	// 转化为数据库的列名
	public String getRealName() {
		for (int i = 0; i < HEADER.length; i++) {
			if (_name.equals(HEADER[i])) {
				return TABLE_COL_NAME[i];
			}
		}
		return "";
	}

	public Filter(JSONObject filterjo) {
		try {
			_name = filterjo.getString("name");
			_sign = filterjo.getString("sign");
			_value = (float) filterjo.getDouble("value");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_sign() {
		return _sign;
	}

	public void set_sign(String _sign) {
		this._sign = _sign;
	}

	public float get_Value() {
		return _value;
	}

	public void set_value(float _value) {
		this._value = _value;
	}

	public JSONObject toJsonObject() throws JSONException {
		// TODO Auto-generated method stub
		JSONObject rtn = new JSONObject();
		rtn.put("name", _name);
		rtn.put("sign", _sign);
		rtn.put("value", _value);
		return rtn;
	}

}
