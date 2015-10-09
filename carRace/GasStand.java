package carRace;

/**
 * ガソリンスタンド。
 * synchronizedの効果を確かめるためのクラス。
 *
 */
class GasStand{
	
	
	/**
	 * インスタンスを生成させないためだけにある。
	 */
	private GasStand(){
		//インスタンス生成させない
	}

	/**
	 * ガソリンの残量を保持する。
	 */
	private static int totalGas;

	/**
	 * GasStandの初期設定。
	 * totalGasの初期値入力のみ。
	 * @param gas
	 */
	public static void initGasStand(int gas){
		totalGas = gas;
	}

	/**
	 * 車に給油するメソッド。(totalGasの値を1減らすだけ)
	 * synchronized修飾子を付けないとtotalGasを正常に計算できないようになっている。
	 */
	synchronized public static void getGas(){
		int temp = totalGas;
		try{
			//補給時間0.1秒スリープ
			Thread.sleep(100);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		totalGas = temp -1;
	}

	/**
	 * totalGasの残量をコンソールに出力する。
	 */
	public static void printTotalGas(){
		System.out.println("\n■ガソリンスタンド ガス総量:"+totalGas+" L\n");
	}
}