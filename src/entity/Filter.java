package entity;

public class Filter {
	

		private String _name;
		private String _sign;// 符号
		private float _Value;// 数值
		// private Boolean _activated;// 是否激活

		public Filter(String sign, float value) {
			_sign = sign;
			_Value = value;
		}

		public Filter(String name, String sign, float value) {
			_name = name;
			_sign = sign;
			_Value = value;

		};

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
			return _Value;
		}

		public void set_Value(float _Value) {
			this._Value = _Value;
		}

	}


