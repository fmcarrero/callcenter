package co.com.callcenter.constant;

public final class ConcurrencyConstant {
	
	protected ConcurrencyConstant() {
		throw new IllegalStateException("Utility class");
	}
	public static final int COUNT_CALLS_CONCURRENT = 10;
	public static final int MILLI_CALLS_CONCURRENT = 1000;
}
