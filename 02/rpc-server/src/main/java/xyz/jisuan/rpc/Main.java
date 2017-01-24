package xyz.jisuan.rpc;

import xyz.jisuan.rpc.api.HelloService;
import xyz.jisuan.rpc.common.RpcRequest;
import xyz.jisuan.rpc.common.RpcResponse;
import xyz.jisuan.rpc.impl.HelloServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
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

	private static HelloService helloService = new HelloServiceImpl();

	public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InterruptedException {
		ServerSocket serverSocket = new ServerSocket(11111);
		System.out.println("server started.....");

		Socket socket = serverSocket.accept();
		ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
		Object obj = objectInputStream.readObject();

		if(obj instanceof RpcRequest) {
			RpcRequest rpcRequest = (RpcRequest) obj;
			String service = rpcRequest.getService();
			switch (service) {
				case "HelloService":
					Method method = HelloService.class.getMethod(rpcRequest.getMethod(), rpcRequest.getParameters());
					Object invoke = method.invoke(helloService, rpcRequest.getArgs());
					RpcResponse rpcResponse = new RpcResponse();
					rpcResponse.setData(invoke);

					ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
					objectOutputStream.writeObject(rpcResponse);
					objectOutputStream.flush();

			}
		}
		socket.close();
		serverSocket.close();
	}
}
