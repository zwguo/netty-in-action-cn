package kite;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ThreadLearn {
	public static void main(String[] args) throws InterruptedException, IOException {
		String name = ManagementFactory.getRuntimeMXBean().getName();  
		System.out.println(name);
		
		System.in.read();
		
		Thread t1 = new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		t1.start();
		List<Thread> ts = new ArrayList<>();
		for(int i=0; i< 50; i++){
			Thread t2 = new Thread(()->{
				try {
					t1.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			ts.add(t2);
			t2.start();
		}
		t1.join();
		
//		while (true) {
//			TimeUnit.SECONDS.sleep(1);
//			for (Thread t : ts) {
//				System.out.println(t.getName() + ":" + t.getState());
//			}
//		}
	}

	/**
	 * TimeWait Blocked 不占用cpu时间
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private static void testThread() throws IOException, InterruptedException {
		System.in.read();
		List<Thread> ts = new ArrayList<>();
		for (int i = 1; i < 51; i++) {
			Thread t = getThread("" + i);
			ts.add(t);
		}
		while (true) {
			TimeUnit.SECONDS.sleep(1);
			for (Thread t : ts) {
				System.out.println(t.getName() + ":" + t.getState());
			}
		}
	}

	private static Thread getThread(String name) {
		Thread t = new Thread(() -> {
			try {
				// System.in.read();
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
		}, name);
		t.setDaemon(true);
		t.start();
		return t;
	}
}
