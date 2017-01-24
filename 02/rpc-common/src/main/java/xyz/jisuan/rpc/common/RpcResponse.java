package xyz.jisuan.rpc.common;

import java.io.Serializable;

/**
 * <p>project：sample-rpc<p>
 * <ul>
 * <li>author:破浪(polang)</li>
 * <li>time:17/1/24 下午4:06</li>
 * <li>function:</li>
 * </ul>
 */
public class RpcResponse implements Serializable{

	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
