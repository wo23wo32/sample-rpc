package xyz.jisuan.rpc.impl;

import xyz.jisuan.rpc.api.HelloService;

/**
 * <p>project：simple-rpc<p>
 * <ul>
 * <li>author:破浪(polang)</li>
 * <li>time:17/1/23 下午3:33</li>
 * <li>function:</li>
 * </ul>
 */
public class HelloServiceImpl implements HelloService {
	public String sayHello(String name) {
		String print = "hello, " + name + "!";
		System.out.println(print);
		return print;
	}
}
