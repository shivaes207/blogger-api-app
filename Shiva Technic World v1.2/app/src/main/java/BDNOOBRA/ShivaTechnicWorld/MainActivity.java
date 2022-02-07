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
import android.widget.LinearLayout;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import android.os.Bundle;
import java.io.InputStream;
import android.content.Intent;
import android.net.Uri;
import java.util.Timer;
import java.util.TimerTask;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class MainActivity extends  AppCompatActivity  { 
	
	private Timer _timer = new Timer();
	
	private LinearLayout linear1;
	
	
	private OnCompleteListener FCM_onCompleteListener;
	private Intent i = new Intent();
	private RequestNetwork net;
	private RequestNetwork.RequestListener _net_request_listener;
	private TimerTask tim;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		net = new RequestNetwork(this);
		
		FCM_onCompleteListener = new OnCompleteListener<InstanceIdResult>() {
			@Override
			public void onComplete(Task<InstanceIdResult> task) {
				final boolean _success = task.isSuccessful();
				final String _token = task.getResult().getToken();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_net_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		PlanetView bcv = new PlanetView(this); linear1.addView(bcv);
		_animation();
		if (SketchwareUtil.isConnected(getApplicationContext())) {
			tim = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							i.setClass(getApplicationContext(), LoginActivity.class);
							startActivity(i);
							finish();
						}
					});
				}
			};
			_timer.schedule(tim, (int)(5000));
		}
		else {
			_NetWork_Response();
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _animation () {
	} 
	
	
	
	public class PlanetView extends View { 
		
		
		
		private double angle=0;
		
		private Paint myPaint; 
		
		public PlanetView(Context context){ 
			
			super(context); 
			
			myPaint = new Paint(); 
			
			angle = 0; 
			
		} 
		
		
		
		@Override protected void onDraw(Canvas canvas) { 
			
			
			
			int viewWidth = this.getMeasuredWidth(); 
			
			int viewHeight = this.getMeasuredHeight(); angle = (angle + 0.001)%360; 
			
			
			
			float x = Math.round(260*Math.sin(Math.toDegrees(angle))); 
			
			float y = Math.round(110*Math.cos(Math.toDegrees(angle))); 
			
			float x2 = Math.round(120*Math.sin(90+Math.toDegrees(angle))); 
			
			float y2 = Math.round(290*Math.cos(90+Math.toDegrees(angle))); 
			
			float x3 = Math.round(130*Math.sin(180+Math.toDegrees(angle))); 
			
			float y3 = Math.round(230*Math.cos(180+Math.toDegrees(angle)));
			
			float x4 = Math.round(120*Math.sin(270+Math.toDegrees(angle))); 
			
			float y4 = Math.round(250*Math.cos(270+Math.toDegrees(angle)));
			
			
			
			
			
			 myPaint.setStyle(android.graphics.Paint.Style.FILL); 
			
			
			
			myPaint.setColor(Color.parseColor("#eceff1")); 
			
			canvas.drawCircle(viewWidth/2, viewHeight/2, (int)(x*1.5), myPaint);
			
			myPaint.setColor(Color.parseColor("#cfd8dc")); 
			
			canvas.drawCircle(viewWidth/2, viewHeight/2, (int)(x*1.5)-25, myPaint);
			
			
			
			myPaint.setColor(Color.parseColor("#275080")); canvas.drawCircle(viewWidth/2 + x, viewHeight/2 + y, 15, myPaint);
			
			
			
			myPaint.setColor(Color.parseColor("#DA3287")); canvas.drawCircle(viewWidth/2 + x2, viewHeight/2 + y2, 20, myPaint);
			
			
			
			myPaint.setColor(Color.parseColor("#008001")); canvas.drawCircle(viewWidth/2 + x3, viewHeight/2 + y3, 30, myPaint);
			
			
			
			myPaint.setColor(Color.parseColor("#8B0000")); canvas.drawCircle(viewWidth/2 + x4, viewHeight/2 + y4, 10, myPaint);
			
			
			
			
			
			
			
			android.graphics.drawable.Drawable d = getResources().getDrawable(R.drawable.earth, null);
			
			d.setBounds((int)((viewWidth/2)-80-x/11), (int)((viewHeight/2)-80-x/11), (int)((viewWidth/2)+80+x/11), (int)((viewHeight/2)+80+x/11)); 
			
			d.draw(canvas);
			
			
			
			
			
			invalidate(); 
			
			
			
		}
	}
	
	
	public void _NetWork_Response () {
		final AlertDialog dialog1 = new AlertDialog.Builder(MainActivity.this).create();
		View inflate = getLayoutInflater().inflate(R.layout.net,null); 
		dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		dialog1.setView(inflate);
		LinearLayout linear1 = (LinearLayout) inflate.findViewById(R.id.linear1);
		
		LinearLayout linear2 = (LinearLayout) inflate.findViewById(R.id.linear2);
		
		LinearLayout linear3 = (LinearLayout) inflate.findViewById(R.id.linear3);
		
		LinearLayout linear4 = (LinearLayout) inflate.findViewById(R.id.linear4);
		
		LinearLayout linear5 = (LinearLayout) inflate.findViewById(R.id.linear5);
		
		TextView textview1 = (TextView) inflate.findViewById(R.id.textview1);
		
		TextView textview2 = (TextView) inflate.findViewById(R.id.textview2);
		
		
		ImageView imageview1 = (ImageView) inflate.findViewById(R.id.imageview1);
		linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)11, 0xFFF44336));
		_rippleRoundStroke(linear5, "#D50000", "#000000", 13, 2, "#FFFFFF");
		linear5.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
						finishAffinity();
				}
		});
		dialog1.setCancelable(false);
		dialog1.show();
	}
	
	
	public void _rippleRoundStroke (final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor(_pressed)}), GG, null);
		_view.setBackground(RE);
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