package carRace;

/**
 * レースをするクラス。
 * startRaceメソッドを呼び出すとレースが始まる。
 * (各スレッド(車)をスタートさせる)
 *
 */
public class CarRace{

	/**
	 * @param carAmount
	 * レースに参加する車の数(スレッド数)
	 * <br>
	 * @param round
	 * レースで周回する回数
	 */
	public void startRace(int carAmount, int round){

		System.out.println("---------- Race Start ----------");

		GasStand.initGasStand(carAmount*round);
		GasStand.printTotalGas();
		
		JudgeMan.initJudge(carAmount);

		RacingCar racingCar[] = new RacingCar[carAmount];

		for (int i = 0; i < carAmount; i++) {
			racingCar[i] = new RacingCar(i + 1, round);
			racingCar[i].start();
		}

		wait(racingCar);

		JudgeMan.printResult(); 
		GasStand.printTotalGas();

		System.out.println("---------- Race End ----------");
	}


	/**
	 * 全ての車がゴールするまで待機するメソッド。
	 * (全てのスレッドの処理が終わるまで処理を止めておく)
	 * @param racingCar
	 */
	public void wait(RacingCar[] racingCar){
		for (int i = 0; i < racingCar.length; i++) {
			try {
				racingCar[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
