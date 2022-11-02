package gay.efrei.utils.data;

import java.lang.reflect.Type;
import java.util.Arrays;

import com.google.common.reflect.TypeToken;

import gay.efrei.account.Account;
@SuppressWarnings("serial")
public enum DataType {
	
	
	ACCOUNTS("players", Account.class, new TypeToken<Account>() {}.getType());
	
	private String src;
	private Class<?> dataClass;
	private Type token;
	
	private DataType(String src, Class<?> dataClass, Type token) {
		this.src = src;
		this.token = token;
		this.dataClass = dataClass;
	}
	
	public Type getToken() {
		return token;
	}
	
	public Class<?> getDataClass() {
		return dataClass;
	}
	
	public String getSrc() {
		return src;
	}

	
	
	public static DataType getType(Class<?> c) {
		return Arrays.stream(values()).filter(e -> e.getDataClass().toString().equals(c.toString())).findAny().orElse(null);
	}

}
