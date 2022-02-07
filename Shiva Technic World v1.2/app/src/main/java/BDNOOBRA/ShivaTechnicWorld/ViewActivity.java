package BDNOOBRA.ShivaTechnicWorld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.os.Bundle;
import java.io.InputStream;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebViewClient;
import android.graphics.Typeface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;


public class ViewActivity extends  AppCompatActivity  { 
	
	
	private String htmlurl = "";
	private HashMap<String, Object> viewmap = new HashMap<>();
	private String share = "";
	private String error = "";
	
	private LinearLayout linear2;
	private ProgressBar progressbar1;
	private TextView textview1;
	private TextView textview2;
	private LinearLayout linear1;
	private WebView webview2;
	
	private Intent i = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.view);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		}
		else {
			initializeLogic();
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
		textview1 = (TextView) findViewById(R.id.textview1);
		textview2 = (TextView) findViewById(R.id.textview2);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		webview2 = (WebView) findViewById(R.id.webview2);
		webview2.getSettings().setJavaScriptEnabled(true);
		webview2.getSettings().setSupportZoom(true);
		
		//webviewOnProgressChanged
		webview2.setWebChromeClient(new WebChromeClient() {
				@Override public void onProgressChanged(WebView view, int _newProgress) {
					
				}
		});
		
		//OnDownloadStarted
		webview2.setDownloadListener(new DownloadListener() {
			public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
				DownloadManager.Request webview2a = new DownloadManager.Request(Uri.parse(url));
				String webview2b = CookieManager.getInstance().getCookie(url);
				webview2a.addRequestHeader("cookie", webview2b);
				webview2a.addRequestHeader("User-Agent", userAgent);
				webview2a.setDescription("Downloading file...");
				webview2a.setTitle(URLUtil.guessFileName(url, contentDisposition, mimetype));
				webview2a.allowScanningByMediaScanner(); webview2a.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); webview2a.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimetype));
				
				DownloadManager webview2c = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
				webview2c.enqueue(webview2a);
				showMessage("Downloading File....");
				BroadcastReceiver onComplete = new BroadcastReceiver() {
					public void onReceive(Context ctxt, Intent intent) {
						showMessage("Download Complete!");
						unregisterReceiver(this);
						
					}};
				registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
			}
		});
		
		webview2.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				progressbar1.setVisibility(View.VISIBLE);
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				progressbar1.setVisibility(View.GONE);
				super.onPageFinished(_param1, _param2);
			}
		});
	}
	
	private void initializeLogic() {
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/spotify_from_dreamstore.ttf"), 1);
		webview2.setBackgroundColor(Color.argb(0, 0, 0, 0));
		viewmap = new Gson().fromJson(getIntent().getStringExtra("data"), new TypeToken<HashMap<String, Object>>(){}.getType());
		try {
			textview1.setText(viewmap.get("title").toString());
			
			
			
		} catch(Exception e)
		
		{ 
			error = e.toString(); 
			
			textview1.setText("Error can't  get post title ");
			
			
		};
		try {
			textview2.setText(viewmap.get("date").toString());
			
			
			
		} catch(Exception e)
		
		{ 
			error = e.toString(); 
			
			textview2.setText("error can't  get date");
			
			
		};
		try {
			htmlurl = "<!DOCTYPE html>\n<html>\n<head>\n\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n\n<meta content=\"width=device-width,initial-scale=1.0,minimum-scale=1.0\" name=\"viewport\">\n\n</head>\n\n<body style=\"width:95%; overflow-wrap: break-word;\">\n".concat(viewmap.get("content").toString().concat("</body>\n</html>"));
			webview2.loadDataWithBaseURL(null,htmlurl, "text/html", "UTF-8", null); 
			
			
			
		} catch(Exception e)
		
		{ 
			error = e.toString(); 
			
			SketchwareUtil.showMessage(getApplicationContext(), "can't get content");
			
			
		};
		registerForContextMenu(webview2);}
	    @Override
	    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo){
		        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
		
		        final WebView.HitTestResult webViewHitTestResult = webview2.getHitTestResult();
		
		        if (webViewHitTestResult.getType() == WebView.HitTestResult.IMAGE_TYPE ||
					webViewHitTestResult.getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
			
			            contextMenu.setHeaderTitle("Download Image From Below");
			
			            contextMenu.add(0, 1, 0, "Save - Download Image")
							.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
									@Override
									public boolean onMenuItemClick(MenuItem menuItem) {
					
											String DownloadImageURL = webViewHitTestResult.getExtra();
					
											if(URLUtil.isValidUrl(DownloadImageURL)){
						
													DownloadManager.Request request = new DownloadManager.Request(Uri.parse(DownloadImageURL));
													request.allowScanningByMediaScanner();
													request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
													DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
													downloadManager.enqueue(request);
						
													Toast.makeText(ViewActivity.this,"Image Downloaded Successfully.",Toast.LENGTH_LONG).show();
											}
											else {
													Toast.makeText(ViewActivity.this,"Sorry.. Something Went Wrong.",Toast.LENGTH_LONG).show();
											}
											return false;
									}
							});
			        }
		webview2.setVerticalScrollBarEnabled(false);
		webview2.setHorizontalScrollBarEnabled(false);
		webview2.setDownloadListener(new DownloadListener() {
			public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
				DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
				String cookies = CookieManager.getInstance().getCookie(url);
				request.addRequestHeader("cookie", cookies);
				request.addRequestHeader("User-Agent", userAgent);
				request.setDescription("Downloading file...");
				request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimetype));
				request.allowScanningByMediaScanner(); request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
				java.io.File aatv = new java.io.File(Environment.getExternalStorageDirectory().getPath() + "/vpn/Ak2 vpn folder");
				
				if(!aatv.exists()){if (!aatv.mkdirs()){ Log.e("TravellerLog ::","Problem creating Image folder");}} request.setDestinationInExternalPublicDir("/vpn/Ak2 vpn folder", URLUtil.guessFileName(url, contentDisposition, mimetype));
				DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
				manager.enqueue(request);
				showMessage("Downloading File....");
				//Notif if success
				BroadcastReceiver onComplete = new BroadcastReceiver() {
					public void onReceive(Context ctxt, Intent intent) {
						showMessage("Download Complete!");
						unregisterReceiver(this);
					}};
				registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
			}
		});
		textview1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
		textview1.setMarqueeRepeatLimit(-1);
		textview1.setSingleLine(true);
		textview1.setSelected(true);
		_Webview_();
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _Webview_ () {
		FileUtil.makeDir(FileUtil.getExternalStorageDir().concat("/Downloads"));
		WebSettings webSettings = webview2.getSettings();
		webSettings.setJavaScriptEnabled(true); webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setSupportZoom(true);
		webSettings.setBuiltInZoomControls(true);
		webSettings.setDisplayZoomControls(false);
		webview2.setVerticalScrollBarEnabled(false);
		webview2.setHorizontalScrollBarEnabled(false);
		webview2.setDownloadListener(new DownloadListener() {
			public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
				DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
				String cookies = CookieManager.getInstance().getCookie(url);
				request.addRequestHeader("cookie", cookies);
				request.addRequestHeader("User-Agent", userAgent);
				request.setDescription("Downloading file to crn folder...");
				request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimetype));
				request.allowScanningByMediaScanner(); request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
				java.io.File aatv = new java.io.File(Environment.getExternalStorageDirectory().getPath() + "/crn");
				
				if(!aatv.exists()){if (!aatv.mkdirs()){ Log.e("TravellerLog ::","Problem creating Image folder");}} request.setDestinationInExternalPublicDir("/crn", URLUtil.guessFileName(url, contentDisposition, mimetype));
				
				DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
				
				manager.enqueue(request);
				
				showMessage("Downloading File to crn folder....");
				
				//Notif if success
				
				BroadcastReceiver onComplete = new BroadcastReceiver() {
					
					public void onReceive(Context ctxt, Intent intent) {
						
						showMessage("Download Complete");
						
						unregisterReceiver(this);
						
					}};
				
				registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
				
			}
			
		});
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}