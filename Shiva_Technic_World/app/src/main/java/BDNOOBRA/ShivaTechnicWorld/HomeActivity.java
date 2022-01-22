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
import java.util.ArrayList;
import android.widget.LinearLayout;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import java.io.InputStream;
import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.widget.AdapterView;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.bumptech.glide.Glide;
import android.graphics.Typeface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class HomeActivity extends  AppCompatActivity  { 
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String rawdata = "";
	private double ki = 0;
	private String di = "";
	private String daydates = "";
	private HashMap<String, Object> converted = new HashMap<>();
	private String finedimage = "";
	private String post = "";
	private String ptitle = "";
	private String userimage = "";
	private String postcat = "";
	private String username = "";
	private String error = "";
	private HashMap<String, Object> blogfa = new HashMap<>();
	private HashMap<String, Object> map = new HashMap<>();
	private String blogger_gen = "";
	private String blogger_id = "";
	private String backgroundColor = "";
	
	private ArrayList<HashMap<String, Object>> shut = new ArrayList<>();
	private ArrayList<String> allraw = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> readypost = new ArrayList<>();
	
	private LinearLayout linear2;
	private LinearLayout linear3;
	private AdView adview1;
	private ListView listview1;
	private LinearLayout linear1;
	private LinearLayout linear4;
	private ImageView imageview1;
	private TextView textview2;
	private ProgressBar progressbar1;
	
	private RequestNetwork ra;
	private RequestNetwork.RequestListener _ra_request_listener;
	private AlertDialog.Builder d;
	private Intent i = new Intent();
	private InterstitialAd ads;
	private AdListener _ads_ad_listener;
	private DatabaseReference updatev1 = _firebase.getReference("update v1");
	private ChildEventListener _updatev1_child_listener;
	private DatabaseReference maintenance = _firebase.getReference("maintenance");
	private ChildEventListener _maintenance_child_listener;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		adview1 = (AdView) findViewById(R.id.adview1);
		listview1 = (ListView) findViewById(R.id.listview1);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview2 = (TextView) findViewById(R.id.textview2);
		progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
		ra = new RequestNetwork(this);
		d = new AlertDialog.Builder(this);
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				try {
					map = readypost.get((int)_position);
					i.setClass(getApplicationContext(), ViewActivity.class);
					i.putExtra("data", new Gson().toJson(map));
					startActivity(i);
					
					
					
				} catch(Exception e)
				
				{ 
					error = e.toString(); 
					
					SketchwareUtil.showMessage(getApplicationContext(), error);
					
					
				};
				ads = new InterstitialAd(getApplicationContext());
				ads.setAdListener(_ads_ad_listener);
				ads.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
				ads.loadAd(new AdRequest.Builder().addTestDevice("D93F37E25526D31B5AA1EF5BD93E5120")
				.build());
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Menu();
			}
		});
		
		_ra_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				_phrase_string(_response, _tag);
				listview1.setVisibility(View.VISIBLE);
				linear1.setVisibility(View.GONE);
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				_NetWork_Response();
			}
		};
		
		_updatev1_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				i.setClass(getApplicationContext(), UpdateActivity.class);
				startActivity(i);
				finish();
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
		updatev1.addChildEventListener(_updatev1_child_listener);
		
		_maintenance_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				i.setClass(getApplicationContext(), MaintenanceActivity.class);
				startActivity(i);
				finish();
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
		maintenance.addChildEventListener(_maintenance_child_listener);
		
		_ads_ad_listener = new AdListener() {
			@Override
			public void onAdLoaded() {
				d.create().show();
			}
			
			@Override
			public void onAdFailedToLoad(int _param1) {
				final int _errorCode = _param1;
				ads = new InterstitialAd(getApplicationContext());
				ads.setAdListener(_ads_ad_listener);
				ads.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
				ads.loadAd(new AdRequest.Builder().addTestDevice("D93F37E25526D31B5AA1EF5BD93E5120")
				.build());
			}
			
			@Override
			public void onAdOpened() {
				
			}
			
			@Override
			public void onAdClosed() {
				
			}
		};
	}
	
	private void initializeLogic() {
		blogfa = new HashMap<>();
		blogfa.put("maxResults", "500");
		blogger_id = "7477999827769701270";
		blogger_gen = "https://www.googleapis.com/blogger/v3/blogs/".concat(blogger_id.concat("/posts"));
		blogfa.put("key", "AIzaSyCw7vY5LuDc2NGOdqqZu8hobS584zHbs_M");
		ra.setParams(blogfa, RequestNetworkController.REQUEST_PARAM);
		ra.startRequestNetwork(RequestNetworkController.GET, blogger_gen, "https://www.googleapis.com/blogger/v3/blogs/7477999827769701270/posts?key=AIzaSyCw7vY5LuDc2NGOdqqZu8hobS584zHbs_M", _ra_request_listener);
		listview1.setVisibility(View.GONE);
		linear1.setVisibility(View.VISIBLE);
		adview1.loadAd(new AdRequest.Builder().addTestDevice("D93F37E25526D31B5AA1EF5BD93E5120")
		.build());
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _phrase_string (final String _response, final String _tag) {
		try {
			shut.clear();
			allraw.clear();
			readypost.clear();
			
			org.json.JSONObject mainn = new org.json.JSONObject(_response);
			
			rawdata = mainn.getString("items");
			org.json.JSONArray main=new org.json.JSONArray(rawdata);
			shut = new Gson().fromJson(rawdata, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			ki = 0;
			for(int _repeat282 = 0; _repeat282 < (int)(shut.size()); _repeat282++) {
				org.json.JSONObject list = main.getJSONObject((int)ki);
				
				//get post user name and img
				
				org.json.JSONObject temp = list.getJSONObject("author");
				
				username = temp.getString("displayName");
				
				org.json.JSONObject temp2 = temp.getJSONObject("image");
				
				userimage = temp2.getString("url");
				
				//end post user name
				
				//get post title content and img
				
				daydates = list.getString("published");
				post = list.getString("content");
				ptitle=list.getString("title");
				
				try{
					postcat = list.getString("labels");
				}catch (Exception e) { postcat = ("no labels");
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				di = daydates.replace("T", "");
				try{
					
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss"); 
					
					SimpleDateFormat df2 = new SimpleDateFormat("dd MMMM yyyy hh:mm a"); 
					
					Date date = format.parse(di);
					
					di = df2.format(date); 
					
				} catch (java.text.ParseException e) {
					
					 // TODO Auto-generated catch block
					
					 e.printStackTrace(); 
				};
				converted = new HashMap<>();
				finedimage = post.replace("\\", "").replace("\n", " ").replace("\\u", " ");
				List<String> allraw = new ArrayList<String>();
				
				
				java.util.regex.Pattern pattern = java.util.regex.Pattern.compile( "\\b(((ht|f)tp(s?)\\:\\/\\/|~\\/|\\/)|i1.wp.)" + "(\\w+:\\w+@)?(([-\\w]+\\.)+(com|org|net|gov" + "|mil|biz|info|mobi|name|aero|jobs|museum" + "|travel|[a-z]{2}))(:[\\d]{1,5})?" + "(((\\/([-\\w~!$+|.,=]|%[a-f\\d]{2})+)+|\\/)+|\\?|#)?" + "((\\?([-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" + "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)" + "(&(?:[-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" + "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)*)*" + "(#([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)?\\b"); 
				java.util.regex.Matcher matcher = pattern.matcher(finedimage);
				
				 while (matcher.find()) {
					
					allraw.add(matcher.group());
					
				};
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				if (allraw.size() > 1) {
					converted.put("img", allraw.get((int)(0)));
				}
				converted.put("title", ptitle);
				converted.put("content", post);
				converted.put("date", di);
				converted.put("authorimage", userimage.replace("//", ""));
				converted.put("labels", postcat);
				converted.put("author", username);
				readypost.add(converted);
				listview1.setAdapter(new Listview1Adapter(readypost));
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
				ki++;
			}
			
			
			
		} catch(Exception e)
		
		{ 
			error = e.toString(); 
			
			d.setMessage(error.concat("At position ".concat(String.valueOf((long)(ki)))));
			d.create().show();
			
			
		};
	}
	
	
	public void _NetWork_Response () {
		final AlertDialog dialog1 = new AlertDialog.Builder(HomeActivity.this).create();
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
	
	
	public void _Menu () {
		final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(HomeActivity.this);
				
				View bottomSheetView; bottomSheetView = getLayoutInflater().inflate(R.layout.menu,null );
				bottomSheetDialog.setContentView(bottomSheetView);
				
				bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
				
		LinearLayout bg = (LinearLayout) bottomSheetView.findViewById(R.id.background);
				
				LinearLayout m1 = (LinearLayout) bottomSheetView.findViewById(R.id.menu1);
				
				LinearLayout m2 = (LinearLayout) bottomSheetView.findViewById(R.id.menu2);
				
				LinearLayout m3 = (LinearLayout) bottomSheetView.findViewById(R.id.menu3);
				
				LinearLayout m4 = (LinearLayout) bottomSheetView.findViewById(R.id.menu4);
				
				
				
				LinearLayout scrolllinear = (LinearLayout) bottomSheetView.findViewById(R.id.scrolllinear);
				bg.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFFFFFFFF));
				scrolllinear.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFEEEEEE));
		m1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				bottomSheetDialog.dismiss();
			}
		});
		m2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), MoreActivity.class);
				startActivity(i);
				finish();
			}
		});
		m3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.showMessage(getApplicationContext(), "not available");
			}
		});
		m4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setData(Uri.parse("https://shiva-technic-world-app.blogspot.com/?m=1"));
				startActivity(i);
			}
		});
		_GradientDrawable(m1, 15, 0, 0, "#EEEEEE", "#000000", true, false, 0);
		_GradientDrawable(m2, 15, 0, 0, "#EEEEEE", "#000000", true, false, 0);
		_GradientDrawable(m3, 15, 0, 0, "#EEEEEE", "#000000", true, false, 0);
		_GradientDrawable(m4, 15, 0, 0, "#EEEEEE", "#000000", true, false, 0);
		bottomSheetDialog.setCancelable(true);
		bottomSheetDialog.show();
	}
	
	
	public void _GradientDrawable (final View _view, final double _radius, final double _stroke, final double _shadow, final String _color, final String _borderColor, final boolean _ripple, final boolean _clickAnim, final double _animDuration) {
		if (_ripple) {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			gd.setStroke((int)_stroke,Color.parseColor(_borderColor));
			if (Build.VERSION.SDK_INT >= 21){
				_view.setElevation((int)_shadow);}
			android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#9E9E9E")});
			android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
			_view.setClickable(true);
			_view.setBackground(ripdrb);
		}
		else {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			gd.setStroke((int)_stroke,Color.parseColor(_borderColor));
			_view.setBackground(gd);
			if (Build.VERSION.SDK_INT >= 21){
				_view.setElevation((int)_shadow);}
		}
		if (_clickAnim) {
			_view.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()){
						case MotionEvent.ACTION_DOWN:{
							ObjectAnimator scaleX = new ObjectAnimator();
							scaleX.setTarget(_view);
							scaleX.setPropertyName("scaleX");
							scaleX.setFloatValues(0.9f);
							scaleX.setDuration((int)_animDuration);
							scaleX.start();
							
							ObjectAnimator scaleY = new ObjectAnimator();
							scaleY.setTarget(_view);
							scaleY.setPropertyName("scaleY");
							scaleY.setFloatValues(0.9f);
							scaleY.setDuration((int)_animDuration);
							scaleY.start();
							break;
						}
						case MotionEvent.ACTION_UP:{
							
							ObjectAnimator scaleX = new ObjectAnimator();
							scaleX.setTarget(_view);
							scaleX.setPropertyName("scaleX");
							scaleX.setFloatValues((float)1);
							scaleX.setDuration((int)_animDuration);
							scaleX.start();
							
							ObjectAnimator scaleY = new ObjectAnimator();
							scaleY.setTarget(_view);
							scaleY.setPropertyName("scaleY");
							scaleY.setFloatValues((float)1);
							scaleY.setDuration((int)_animDuration);
							scaleY.start();
							
							break;
						}
					}
					return false;
				}
			});
		}
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
				_view = _inflater.inflate(R.layout.cus, null);
			}
			
			final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final ImageView imageview1 = (ImageView) _view.findViewById(R.id.imageview1);
			final TextView title = (TextView) _view.findViewById(R.id.title);
			final LinearLayout linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
			final TextView date = (TextView) _view.findViewById(R.id.date);
			final TextView author = (TextView) _view.findViewById(R.id.author);
			
			try {
				title.setText(_data.get((int)_position).get("title").toString());
				date.setText(_data.get((int)_position).get("date").toString());
				author.setText(_data.get((int)_position).get("author").toString());
				Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("img").toString())).into(imageview1);
				title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/spotify_from_dreamstore.ttf"), 0);
				date.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/spotify_from_dreamstore.ttf"), 0);
				author.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/stanley_from_dreamstore.ttf"), 0);
				author.setVisibility(View.GONE);
				linear1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)15, (int)2, 0xFF000000, 0xFFFFFFFF));
				
				
				
			} catch(Exception e)
			
			{ 
				
				
				Log.e("Error: ", e.toString()); 
				
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