package net.daum.apis.android.conn;

import net.daum.apis.android.common.DaumOpenApiSDKException;


// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving request events.
 * The class that is interested in processing a request
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addRequestListener<code> method. When
 * the request event occurs, that object's appropriate
 * method is invoked.
 *
 * @see RequestEvent
 */
public interface RequestListener {

	/**
	 * On complete.
	 *
	 * @param object the object
	 */
	public abstract void onComplete(Object object);
	
	/**
	 * On daum opn api sdk exception.
	 *
	 * @param e the e
	 */
	public abstract void onDaumOpnApiSDKException(DaumOpenApiSDKException e);
}
