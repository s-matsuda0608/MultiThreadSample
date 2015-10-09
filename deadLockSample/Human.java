package deadLockSample;

public class Human extends Thread{
	private final String name;
	private final ChopSticks stick1;
	private final ChopSticks stick2;

	public Human(String name, ChopSticks stick1, ChopSticks stick2){
		this.name = name;
		this.stick1 = stick1;
		this.stick2 = stick2;
	}

	public void run(){
		System.out.println(this.name+" が、いただきます しました!!");

		for(int i = 0; i < 10; i++){
			try{
				//コンビ二弁当を食べる
				eat();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		System.out.println(this.name+" が、ごちそうさま しました!!");

	}

	private void eat() throws InterruptedException{
		
		synchronized(this.stick1){
			//片方のお箸を取得
			this.stick1.getStick(this.name);
			//デッドロックが発生しやすくするためスリープを実行
			//Thread.sleep(10);
			
			synchronized(this.stick2){
				//もう片方のお箸を取得
				this.stick2.getStick(this.name);
				//デッドロックが発生しやすくするためスリープを実行
				//Thread.sleep(10);
			}
		}
	}
}
