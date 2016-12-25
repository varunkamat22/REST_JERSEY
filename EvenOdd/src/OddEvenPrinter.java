public class OddEvenPrinter {
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Object oddLock = new Object();
		Object evenLock = new Object();
		Thread even = new Thread(new EvenPrinter(oddLock, evenLock));
		Thread odd = new Thread(new OddPrinter(oddLock, evenLock));
		even.start();
		odd.start();
		even.join();
	}

}

class EvenPrinter implements Runnable {
	public Object oddLock;
	public Object evenLock;

	EvenPrinter(Object oddLock, Object evenLock) {
		this.oddLock = oddLock;
		this.evenLock = evenLock;
	}

	@Override
	public void run() {

		for (int i = 0; i < 10; i = i + 2) {
			synchronized (evenLock) {
				System.out.println(i);
				try {
					evenLock.notifyAll();
					evenLock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

}

class OddPrinter implements Runnable {
	public Object oddLock;
	public Object evenLock;

	OddPrinter(Object oddLock, Object evenLock) {
		this.oddLock = oddLock;
		this.evenLock = evenLock;
	}

	@Override
	public void run() {
		for (int i = 1; i < 10; i = i + 2) {
			synchronized (evenLock) {
				System.out.println(i);
				try {
					evenLock.notifyAll();
					evenLock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}


		}

	}
}
