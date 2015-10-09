package carRace;

/**
 * CarRaceを実行するmainメソッドを持つクラス
 */
public class Main {
	
	/**車の数(スレッド数)*/
	private static final int CAR_AMOUNT = 5;
	
	/** レースの周回数 */
	private static final int ROUND = 10;

	/**
	 * CarRaceを実行する。
	 */
	public static void main(String[] args) {
		CarRace race = new CarRace();
		race.startRace(CAR_AMOUNT, ROUND);
	}
}
