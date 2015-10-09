package carRace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * レースの審判。ゴールした時間を計測する人。
 * 各スレッドがこのクラスのArrayListにアクセスするのでその同期が出来るか確かめた。
 *
 */
public class JudgeMan {

	/** 各RacingCarがゴールするまでにかかった時間を保持。
	 * (リストのインデックス + 1)が車のIDに対応している。 */
	private static List<Long> totalTimeList = Collections.synchronizedList(new ArrayList<Long>());
	
	/** ゴールした車の順位が分かるリスト。(リストのインデックス+1)が順位に対応している。
	 * ゴールした順にこのリストに車のIDを追加する。 */
	private static List<Integer> ranking = Collections.synchronizedList(new ArrayList<Integer>());
	
	/** レースに参加している車の数*/
	private static int carAmount = 0;

	
	/** インスタンス生成させないためのプライベートなコンストラクタ */
	private JudgeMan(){}
	
	/**
	 * フィールドの初期化。
	 * 入力のcarAmountをフィールドのcarAmountに代入。
	 * totalTimeListに初期値の０をcarAmount分だけ追加。
	 * @param carAmount
	 */
	public static void initJudge(int carAmount) {
		for (int i=0;i<carAmount;i++){
			totalTimeList.add((long)0);
		}
		JudgeMan.carAmount = carAmount;
	}
	
	/**
	 * どの車がゴールにどれだけ時間がかかったか記録するメソッド。
	 * @param carID
	 * @param totalTime
	 */
	synchronized public static void setFinishTime(int carID,long totalTime){
		totalTimeList.set(carID-1,totalTime);
	}
	
	/**
	 * どの車が何位か記録するメソッド。
	 * @param carID
	 */
	synchronized public static void addRanking(int carID){
		ranking.add(carID);
	}
	
	/**
	 * レースの結果をコンソールに出力するメソッド。
	 */
	public static void printResult(){
		System.out.println("\n■レース結果");
		
		for(int i=0;i<carAmount;i++){
			int carID = ranking.get(i);
			long time = totalTimeList.get(carID-1);
			
			System.out.println((i + 1) + "位\t"+carID+"号車\tタイム:"+time+"ms");
		}
	}
	
}
