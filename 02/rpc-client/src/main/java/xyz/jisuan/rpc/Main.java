package xyz.jisuan.rpc;

import xyz.jisuan.rpc.api.HelloService;
import xyz.jisuan.rpc.common.RpcRequest;
import xyz.jisuan.rpc.common.RpcResponse;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * <p>project：simple-rpc<p>
 * <ul>
 * <li>author:破浪(polang)</li>
 * <li>time:17/1/23 下午3:57</li>
 * <li>function:</li>
 * </ul>
 */
public class Main {

//	private static HelloService helloService = new HelloServiceImpl();
	private static HelloService helloService;

	static {
		helloService = (HelloService) Proxy.newProxyInstance(HelloService.class.getClassLoader(), new Class[]{HelloService.class}, new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("enter, method:"+method.getName()+",args:"+args[0].toString());

				Socket socket = new Socket("127.0.0.1", 11111);
				OutputStream outputStream = socket.getOutputStream();
				InputStream inputStream = socket.getInputStream();

				RpcRequest rpcRequest = new RpcRequest();
				rpcRequest.setService(HelloService.class.getSimpleName());
				rpcRequest.setMethod(method.getName());
				rpcRequest.setParameters(method.getParameterTypes());
				rpcRequest.setArgs(args);

				ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
				objectOutputStream.writeObject(rpcRequest);
				objectOutputStream.flush();

				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
				Object obj = objectInputStream.readObject();
				Object data = null;
				if(obj instanceof RpcResponse){
					RpcResponse rpcResponse = (RpcResponse) obj;
					data = rpcResponse.getData();
				}
				socket.close();
				return data;
			}
		});

	}

	public static void main(String[] args) {
		String result = helloService.sayHello("world");
		System.out.println("return:"+result);
	}
}
