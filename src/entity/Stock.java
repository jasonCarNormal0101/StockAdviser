package entity;
import java.util.Arrays;




public class Stock {
	private int num;//股票代码
	private String name;//股票简称
	private float change;//涨跌幅
	private float price;//现价
	private float pe;//市盈率
	private float pre_pe;//预计市盈率
	private float pb;//市净率
	private float equity;//总股本
	
	
	public Stock(int num, String name,float change,float price,
			float pe,float pre_pe,float pb,float equity) {  
        this.num = num;  
        this.name = name;  
        this.change = change;
        this.price = price;
        this.pe = pe;
        this.pre_pe = pre_pe;
        this.pb = pb;
        this.equity = equity;
    }  
	
	public String toString(){
		return num + " " + name + " " + change + " " + price
				+ " " + pe + " " + pre_pe + " " + pb 
				+ " " + equity+"\r\n";
	}
	
	//Setter方法与Getter方法
		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public float getChange() {
			return change;
		}

		public void setChange(float change) {
			this.change = change;
		}

		public float getPrice() {
			return price;
		}

		public void setPrice(float price) {
			this.price = price;
		}

		public float getPe() {
			return pe;
		}

		public void setPe(float pe) {
			this.pe = pe;
		}

		public float getPre_pe() {
			return pre_pe;
		}

		public void setPre_pe(float pre_pe) {
			this.pre_pe = pre_pe;
		}

		public float getPb() {
			return pb;
		}

		public void setPb(float pb) {
			this.pb = pb;
		}

		public float getEquity() {
			return equity;
		}

		public void setEquity(float equity) {
			this.equity = equity;
		}
		
		
}
