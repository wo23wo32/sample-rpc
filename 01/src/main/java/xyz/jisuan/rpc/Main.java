package xyz.jisuan.rpc;

import xyz.jisuan.rpc.api.HelloService;
import xyz.jisuan.rpc.impl.HelloServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
				HelloServiceImpl helloService = new HelloServiceImpl();
				method.invoke(helloService, args);
				return null;
			}
		});
	}

	public static void main(String[] args) {
		helloService.sayHello("world");
	}
}
