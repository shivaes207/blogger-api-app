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
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import java.io.InputStream;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class AboutUsActivity extends  AppCompatActivity  { 
	
	
	private LinearLayout linear1;
	private LinearLayout linear3;
	private LinearLayout txt_body;
	private LinearLayout linear4;
	private ImageView imageview7;
	private LinearLayout l_shiva;
	private LinearLayout l_sachin;
	private LinearLayout l_bd_noobra;
	private ImageView imageview4;
	private TextView textview1;
	private ImageView imageview5;
	private TextView textview2;
	private ImageView imageview6;
	private TextView textview3;
	
	private Intent i = new Intent();
	private AlertDialog.Builder d;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.about_us);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		txt_body = (LinearLayout) findViewById(R.id.txt_body);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		imageview7 = (ImageView) findViewById(R.id.imageview7);
		l_shiva = (LinearLayout) findViewById(R.id.l_shiva);
		l_sachin = (LinearLayout) findViewById(R.id.l_sachin);
		l_bd_noobra = (LinearLayout) findViewById(R.id.l_bd_noobra);
		imageview4 = (ImageView) findViewById(R.id.imageview4);
		textview1 = (TextView) findViewById(R.id.textview1);
		imageview5 = (ImageView) findViewById(R.id.imageview5);
		textview2 = (TextView) findViewById(R.id.textview2);
		imageview6 = (ImageView) findViewById(R.id.imageview6);
		textview3 = (TextView) findViewById(R.id.textview3);
		d = new AlertDialog.Builder(this);
		
		//OnTouch
		imageview7.setOnTouchListener(new View.OnTouchListener(){
				@Override
				public boolean onTouch(View v, MotionEvent event){
						int ev = event.getAction();
						switch (ev) {
								case MotionEvent.ACTION_DOWN:
								
								 
								
								break;
								case MotionEvent.ACTION_UP:
								
								 
								
								break;
						} return true;
				}
		});
		
		imageview7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), HomeActivity.class);
				startActivity(i);
				finish();
			}
		});
		
		l_shiva.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				d.setTitle("Shiva");
				d.setIcon(R.drawable.logojpg);
				d.setMessage("Website Designed And Admin");
				d.setPositiveButton("ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				d.create().show();
			}
		});
		
		l_sachin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				d.setTitle("Sachin Patnesar");
				d.setIcon(R.drawable.sachin_patnesar);
				d.setMessage("Sachin Patnesar Articles Writer (Author)");
				d.setPositiveButton("ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				d.create().show();
			}
		});
		
		l_bd_noobra.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				d.setTitle("BD NOOBRA");
				d.setIcon(R.drawable.bd_noobra_new);
				d.setMessage("App Designed And App Developer");
				d.setPositiveButton("ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				d.create().show();
			}
		});
	}
	
	private void initializeLogic() {
		txt_body.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)11, (int)2, 0xFF000000, 0xFFFFFFFF));
		_rippleRoundStroke(l_shiva, "#FFFFFF", "#BDBDBD", 8, 1, "#000000");
		_rippleRoundStroke(l_sachin, "#FFFFFF", "#BDBDBD", 8, 1, "#000000");
		_rippleRoundStroke(l_bd_noobra, "#FFFFFF", "#BDBDBD", 8, 1, "#000000");
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		i.setClass(getApplicationContext(), HomeActivity.class);
		startActivity(i);
		finish();
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