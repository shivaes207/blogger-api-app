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
import android.widget.ScrollView;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import java.io.InputStream;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class SocialMediaActivity extends  AppCompatActivity  { 
	
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout fb;
	private LinearLayout linear4;
	private LinearLayout yt;
	private LinearLayout linear6;
	private LinearLayout it;
	private LinearLayout linear8;
	private LinearLayout tg;
	private LinearLayout linear10;
	private LinearLayout tt;
	private LinearLayout linear11;
	private LinearLayout gh;
	private ImageView imageview1;
	private TextView textview1;
	private ImageView imageview2;
	private TextView textview2;
	private ImageView imageview3;
	private TextView textview3;
	private ImageView imageview4;
	private TextView textview4;
	private ImageView imageview5;
	private TextView textview5;
	private ImageView imageview6;
	private TextView textview6;
	
	private Intent i = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.social_media);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		fb = (LinearLayout) findViewById(R.id.fb);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		yt = (LinearLayout) findViewById(R.id.yt);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		it = (LinearLayout) findViewById(R.id.it);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		tg = (LinearLayout) findViewById(R.id.tg);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		tt = (LinearLayout) findViewById(R.id.tt);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		gh = (LinearLayout) findViewById(R.id.gh);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview1 = (TextView) findViewById(R.id.textview1);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		textview2 = (TextView) findViewById(R.id.textview2);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		textview3 = (TextView) findViewById(R.id.textview3);
		imageview4 = (ImageView) findViewById(R.id.imageview4);
		textview4 = (TextView) findViewById(R.id.textview4);
		imageview5 = (ImageView) findViewById(R.id.imageview5);
		textview5 = (TextView) findViewById(R.id.textview5);
		imageview6 = (ImageView) findViewById(R.id.imageview6);
		textview6 = (TextView) findViewById(R.id.textview6);
		
		fb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setData(Uri.parse("https://www.facebook.com/shivatechnicworld1/"));
				startActivity(i);
			}
		});
		
		yt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setData(Uri.parse("https://www.youtube.com/c/ShivaTechnicWorld/"));
				startActivity(i);
			}
		});
		
		it.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setData(Uri.parse("https://www.instagram.com/shivatechnicworld/"));
				startActivity(i);
			}
		});
		
		tg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setData(Uri.parse("https://telegram.me/shivatechnicworld/"));
				startActivity(i);
			}
		});
		
		tt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setData(Uri.parse("https://www.twitter.com/shivatechnicwo1/"));
				startActivity(i);
			}
		});
		
		gh.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setData(Uri.parse("https://github.com/shivatechnicworld"));
				startActivity(i);
			}
		});
	}
	
	private void initializeLogic() {
		_rippleRoundStroke(fb, "#FFFFFF", "#BDBDBD", 7, 1, "#000000");
		_rippleRoundStroke(yt, "#FFFFFF", "#BDBDBD", 7, 1, "#000000");
		_rippleRoundStroke(it, "#FFFFFF", "#BDBDBD", 7, 1, "#000000");
		_rippleRoundStroke(tg, "#FFFFFF", "#BDBDBD", 7, 1, "#000000");
		_rippleRoundStroke(tt, "#FFFFFF", "#BDBDBD", 7, 1, "#000000");
		_rippleRoundStroke(gh, "#FFFFFF", "#BDBDBD", 7, 1, "#000000");
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