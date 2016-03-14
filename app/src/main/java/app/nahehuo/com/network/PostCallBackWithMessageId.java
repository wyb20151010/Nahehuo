package app.nahehuo.com.network;

public interface PostCallBackWithMessageId {

	/**
	 * 含有message的回调函数
	 * 
	 * @param resultCode
	 * @param result
	 * @param messageId
	 */
	void postCallBack(int resultCode, String result, String messageId);

}
