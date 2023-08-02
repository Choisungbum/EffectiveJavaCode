package chapter6.item34;

public enum PayrollDay {
//	34 - 9 전략 열거 타입 패턴 
	MONDAY(WEEKDAY), THUESDAY(WEEKDAY), WEDNESDAY(WEEKDAY), THURSDAY(WEEKDAY), FRIDAY(WEEKDAY)
	, SATURDAY(WEEKEND), SUNDAY(WEEKEND);
	
	private final PayType payType;
	
	PayrollDay(PayType payType) {thiis.payType = payType;}
	
	int pay(int minutesWorked, int payRate) {
		return payType.pay(minutesWorked, payRate);
	}
	// 전략 열거 타입
	enum PayType{
		WEEKDAY{
			int overtimePay(int minsWorked, int payRate) {
				return minsWorked <= MINS_PER_SHIFT ? 0 : 
					(minsWorked - MINS_PER_SHIFT) * payRate / 2;
			}
		},
		WEEKEND {
			int overtimePay(int minsWorked, int payRate) {
				return minsWorked * payRate / 2;
			}
		};
		
		abstract int overtimePay(int mins, int payRate);
		private static final int MINS_PER_SHIFT = 8 * 60;
		
		int pay(int minsWorked, int payRate) {
			int basePay = minsWorked * payRate;
			return basePay + overtimePay(minsWorked, payRate);
		}
		
	}
	
//	34 - 8 값에 따라 분기하여 코드를 공유하는 열거 타입
//	MONDAY, THUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
//	
//	private static final int MINS_PERS_SHIFT = 8 * 60;
//	
//	int pay(int minutesWorked, int payRate) {
//		int basePay = minutesWorked * payRate;
//		int overtimePay;
//		switch(this) {
//			case SATURDAY : case SUNDAY : // 주말
//				overtimePay = basePay / 2;
//				break;
//			default: // 주중
//				overtimePay = minutesWorked <= MINS_PERS_SHIFT ? 0 : (minutesWorked - MINS_PERS_SHIFT * payRate / 2);
//		}
//		
//		return basePay + overtimePay;
//		
//	}
}
