package com.jy.access.run;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * @desc 
 * @author liukh
 * @date 2016-12-2 下午5:28:01
 */
public class ServiceProvider {
	

	/**
	 * @desc 
	 * @author liukh
	 * @date 2016-12-2 下午5:28:01
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:spring/spring-*.xml"});  
        context.start();
        run(context);
	}
	
	public static InetAddress getInetAddress() {
		try {
			return InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			System.out.println("unknown host!");
		}
		return null;

	}

	public static String getHostName(InetAddress netAddress) {
		if (null == netAddress) {
			return null;
		}
		String hostName = netAddress.getHostName(); // get the ip address
		return hostName;
	}
	
	public static void run(ClassPathXmlApplicationContext context) {

		try {
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					try {
					System.out.println("The ServiceProvider staring .........");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}, 1000 * 10, 1000 * 10);

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	

}
