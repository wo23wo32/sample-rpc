package xyz.jisuan.rpc.common;

/**
 * <p>project：sample-rpc<p>
 * <ul>
 * <li>author:破浪(polang)</li>
 * <li>time:17/1/24 下午4:03</li>
 * <li>function:</li>
 * </ul>
 */
public class RpcRequest {

	private String service;
	private String method;
	private String parameters;
	private Object[] args;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
}
