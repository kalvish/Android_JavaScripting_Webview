package com.kalvish.android.js;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * 
 * @author kalan
 *
 */
public class JavaScriptingActivity extends Activity {

	WebView webView;

	// Here is the script
	// <script language="JavaScript">
	// function callingFunction() {
	// window.scripter.onLike('Who clicked me?');
	//alert(String(result));
	//document.getElementById('btnClicking').value = result;
	// }
	// </script>
	String script = "<script language=\"JavaScript\">"
			+ "function callingFunction() { "
			+ "var result = window.scripter.onClickMe('Who clicked me?'); alert(String(result)); document.getElementById('btnClicking').value = result;" + "}"
			+ "</script>";

	// Here is a html button
	String html = "<div id=\"btnClick\"><input type=\"button\" id=\"btnClicking\" value=\"Click Me\" onclick=\"callingFunction();\"/></div>";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		webView = (WebView) findViewById(R.id.webview);

		WebSettings webSettings = webView.getSettings();

		// Enabling java script support. Because java script is not enabled by
		// default.
		webSettings.setJavaScriptEnabled(true);

		webView.setWebChromeClient(new WebChromeClient());

		// Adding the Java/Javascript interface
		webView.addJavascriptInterface(new JavaScriptHandler(), "scripter");
	}

	@Override
	protected void onResume() {
		webView.loadDataWithBaseURL("yourBaseUrl", script + /*scriptOnclickFunction+*/ html, "text/html",
				"utf-8", null);
		super.onResume();
	}

	// Javascript handler 
	final class JavaScriptHandler {

		public String onClickMe(String message) {
			
			//Alert a java toast in java world
			Toast msg = Toast.makeText(JavaScriptingActivity.this, message,
					Toast.LENGTH_LONG);
			msg.show();
			
			//Call beck result to web world
			String result = "Clicked";
			return result;
		}
	}
}