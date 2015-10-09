package syncSample;

public class SyncSample {

	public static int acsessCounter = 0;
	
	/**スレッド数 */
	public static final int THREADS_AMOUNT = 3;
	
	/**アクセスカウンタの最大値*/
	public static final int MAX_ACSESS_COUNTER = 100;

	public static void main(String[] args) {

		th[] thArray = new th[THREADS_AMOUNT];

		for(int i=0;i<THREADS_AMOUNT;i++){
			thArray[i] = new th();
			thArray[i].start();
		}
	}

	static class th extends Thread{

		public void run(){
			method();
		}

		private void method() {
			while(SyncSample.acsessCounter < MAX_ACSESS_COUNTER){
				
				//ここ変えて色々試してみる
				long sleepTime = (long)Math.random() * 100;
				
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {}
				acsess();
			}
		}
		
		//「synchronized」を付けたり外したりしてみる
		synchronized public void acsess(){
			System.out.println(getName()+" acsess");
			if (SyncSample.acsessCounter < MAX_ACSESS_COUNTER) {
				int temp = SyncSample.acsessCounter;
				SyncSample.acsessCounter = temp + 1;
				System.out.println(getName() + "のアクセス 累計 " + acsessCounter + " 回目 ");
			}
		}
	}
}
