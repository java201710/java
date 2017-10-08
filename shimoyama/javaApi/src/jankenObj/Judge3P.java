package jankenObj;


class Judge3P extends Judge {

	public boolean handJudge() {
		int[] handPattern = { 0, 0, 0 };
		boolean hantei=false;

		//各じゃんけんの手の数を数える
		super.handCount(handPattern);

		if (handPattern[0] >= 1 &&
				handPattern[1] >= 1 &&
				handPattern[2] >= 1) {
			System.out.println("アイコでした。");
			System.out.println("-----------------------------");
			return true;
		}

		hantei = super.handJudge();
		
		return hantei;
	}
}
