package controller;

public class Util {
	
	public int shareCodeTransfer(String shareCode){
		int endIndex = shareCode.indexOf('.');
		int shareCodeInt = Integer.parseInt(shareCode.substring(0, endIndex));
		return shareCodeInt;
	}

}
