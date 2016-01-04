package com.herong.spring.standalone;

import org.springframework.stereotype.Component;

@Component
public class TaskService {

	public void start() {
		new Thread(new Tack()).start();
	}

	public static class Tack implements Runnable {
		int i = 0;

		@Override
		public void run() {
			while (true) {
				try {
					System.out.println("run :" + i++);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (i > 100000)
					System.exit(0);
			}

		}

	}
}
