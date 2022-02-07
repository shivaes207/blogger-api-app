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
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.ImageView;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.os.Bundle;
import java.io.InputStream;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.bumptech.glide.Glide;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class NewsActivity extends  AppCompatActivity  { 
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private ArrayList<HashMap<String, Object>> map = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear3;
	private LinearLayout bottomBar;
	private ListView listview1;
	private LinearLayout loading;
	private ProgressBar progressbar1;
	private LinearLayout bottomBar_item1;
	private LinearLayout bottomBar_item3;
	private ImageView imageview1;
	private ImageView imageview3;
	
	private DatabaseReference news = _firebase.getReference("news");
	private ChildEventListener _news_child_listener;
	private Intent i = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.news);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		bottomBar = (LinearLayout) findViewById(R.id.bottomBar);
		listview1 = (ListView) findViewById(R.id.listview1);
		loading = (LinearLayout) findViewById(R.id.loading);
		progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
		bottomBar_item1 = (LinearLayout) findViewById(R.id.bottomBar_item1);
		bottomBar_item3 = (LinearLayout) findViewById(R.id.bottomBar_item3);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		
		bottomBar_item1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), HomeActivity.class);
				startActivity(i);
				finish();
			}
		});
		
		_news_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				map.add((int)0, _childValue);
				listview1.setAdapter(new Listview1Adapter(map));
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
				loading.setVisibility(View.GONE);
				listview1.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		news.addChildEventListener(_news_child_listener);
	}
	
	private void initializeLogic() {
		listview1.setVisibility(View.GONE);
		loading.setVisibility(View.VISIBLE);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _CornerRadius (final String _color, final double _radius, final double _shadow, final View _view) {
		android.graphics.drawable.GradientDrawable STRING = new android.graphics.drawable.GradientDrawable();
		STRING.setColor(Color.parseColor(_color));
		STRING.setCornerRadius((int)_radius);
		_view.setBackground(STRING);
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
			_view.setElevation((int)_shadow);}
	}
	
	
	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.custom_news, null);
			}
			
			final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final LinearLayout linear3 = (LinearLayout) _view.findViewById(R.id.linear3);
			final LinearLayout linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
			final TextView textview2 = (TextView) _view.findViewById(R.id.textview2);
			final ImageView imageview2 = (ImageView) _view.findViewById(R.id.imageview2);
			final TextView time = (TextView) _view.findViewById(R.id.time);
			final ImageView imageview1 = (ImageView) _view.findViewById(R.id.imageview1);
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			
			_CornerRadius("#FFFFFF", 12, 10, linear1);
			if (_data.get((int)_position).containsKey("image")) {
				if (_data.get((int)_position).get("image").toString().equals("")) {
					imageview2.setVisibility(View.GONE);
				}
				else {
					imageview2.setVisibility(View.VISIBLE);
					Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("image").toString())).into(imageview2);
				}
			}
			if (map.get((int)_position).containsKey("title")) {
				textview1.setText(_data.get((int)_position).get("title").toString());
			}
			if (map.get((int)_position).containsKey("message")) {
				textview2.setText(_data.get((int)_position).get("message").toString());
			}
			if (map.get((int)_position).containsKey("date")) {
				time.setText(_data.get((int)_position).get("date").toString());
			}
			
			return _view;
		}
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