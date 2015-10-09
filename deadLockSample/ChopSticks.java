package deadLockSample;

class ChopSticks{
	private final String partName;

	public ChopSticks(String partName){
		this.partName = partName;
	}

	public void getStick(String humanName){
		//取得できたお箸を表示
		System.out.println(humanName+" が、["+partName+"] を取得!!");
	}
}