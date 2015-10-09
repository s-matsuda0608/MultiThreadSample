package deadLockSample;

public class DeadLockTest{
	
	public static void main(String args[]){

		ChopSticks leftStick  = new ChopSticks("左のお箸");
		ChopSticks rightStick = new ChopSticks("右のお箸");

		Human humanA = new Human("Ａさん", leftStick, rightStick);
		Human humanB = new Human("Ｂさん", rightStick, leftStick);

		humanA.start();
		humanB.start();
		
	}
}