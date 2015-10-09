package carRace;

import java.util.ArrayList;

/**
 * レースに出る車のクラス
 * このクラスを複数同時に動かす。
 */
class RacingCar extends Thread{
	
	/** レースに出る車のID */
	private int carID;
	
	
	/** レースの周回数 */
	private int round;
	
	/** レース中、コンソールの表示が見やすくなるように、タブを入れるためのArrayList */
	private static ArrayList<String> printOffset = new ArrayList<String>();

	/**
	 * @param carID レースに出る車のID
	 * @param round レースの周回数
	 * フィールドに保存。createOffsetメソッド実行。
	 */
	public RacingCar(int carID,int round){
		this.carID = carID;
		this.round = round;
		createOffset(carID);
	}

	/**
	 * @param carID
	 * 表示を見やすくするための複数個のタブのまとまりを作成する。
	 */
	private void createOffset(int carID) {
		String str = "";
		for(int i=0;i<carID-1;i++){
			str += "\t\t\t";
		}
		printOffset.add(str);
	}

	//別スレッドで動くメソッド
	@Override
	public void run(){

		long totalTime = goAround();
		
		JudgeMan.setFinishTime(carID, totalTime);
		JudgeMan.addRanking(carID);
	}

	/**
	 * サーキットを周回する。round数、周回したらゴール
	 * @return このメソッドの処理にかかった時間(スタートしてからゴールするまでの時間)
	 */
	private long goAround() {
		
		long startTime = System.currentTimeMillis();
		
		for (int i = 1; i <=round ; i++){
			try{
				//ランダムミリ秒数スリープで進度に差を出す
				Thread.sleep((long)(Math.random() * 1000));
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			GasStand.getGas();
			System.out.println(printOffset.get(carID-1) + this.carID+"号車  "+i+" 周目通過");
		}
		
		System.out.println(printOffset.get(carID-1) + this.carID+"号車 GOAL！！");
		
		return System.currentTimeMillis() - startTime;
	}
}