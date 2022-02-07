package BDNOOBRA.ShivaTechnicWorld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.widget.LinearLayout;
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
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.ImageView;
import android.widget.ScrollView;
import de.hdodenhof.circleimageview.*;
import android.os.Bundle;
import java.io.InputStream;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Timer;
import java.util.TimerTask;
import android.widget.AdapterView;
import android.view.View;
import com.google.gson.Gson;
import com.bumptech.glide.Glide;
import com.google.gson.reflect.TypeToken;
import android.graphics.Typeface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class HomeActivity extends  AppCompatActivity  { 
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private DrawerLayout _drawer;
	private HashMap<String, Object> map = new HashMap<>();
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
	private String blogger_id = "";
	private String blogger_gen = "";
	private String share = "";
	
	private ArrayList<HashMap<String, Object>> readypost = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> shut = new ArrayList<>();
	private ArrayList<String> allraw = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear3;
	private LinearLayout bottomBar;
	private TextView textview1;
	private ListView listview1;
	private LinearLayout loading;
	private ProgressBar progressbar1;
	private LinearLayout bottomBar_item1;
	private LinearLayout bottomBar_item3;
	private ImageView imageview1;
	private ImageView imageview3;
	private LinearLayout _drawer_linear1;
	private LinearLayout _drawer_linear2;
	private ScrollView _drawer_vscroll1;
	private CircleImageView _drawer_circleimageview1;
	private TextView _drawer_name;
	private TextView _drawer_email;
	private LinearLayout _drawer_linear3;
	private LinearLayout _drawer_profile;
	private LinearLayout _drawer_about;
	private LinearLayout _drawer_social;
	private LinearLayout _drawer_report;
	private LinearLayout _drawer_share;
	private LinearLayout _drawer_logout;
	private TextView _drawer_textview1;
	private TextView _drawer_textview3;
	private TextView _drawer_textview4;
	private TextView _drawer_textview5;
	private TextView _drawer_textview6;
	private TextView _drawer_textview7;
	
	private Intent i = new Intent();
	private RequestNetwork ra;
	private RequestNetwork.RequestListener _ra_request_listener;
	private AlertDialog.Builder d;
	private AlertDialog.Builder dexit;
	private DatabaseReference dn = _firebase.getReference("DilogNotification");
	private ChildEventListener _dn_child_listener;
	private DatabaseReference UPDATE = _firebase.getReference("UPDATEVTWO");
	private ChildEventListener _UPDATE_child_listener;
	private AlertDialog.Builder update;
	private AlertDialog.Builder notification;
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
	private AlertDialog.Builder logout;
	private FirebaseAuth auth;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private TimerTask timer;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		_app_bar = (AppBarLayout) findViewById(R.id._app_bar);
		_coordinator = (CoordinatorLayout) findViewById(R.id._coordinator);
		_toolbar = (Toolbar) findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		_drawer = (DrawerLayout) findViewById(R.id._drawer);
		ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(HomeActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = (LinearLayout) findViewById(R.id._nav_view);
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		bottomBar = (LinearLayout) findViewById(R.id.bottomBar);
		textview1 = (TextView) findViewById(R.id.textview1);
		listview1 = (ListView) findViewById(R.id.listview1);
		loading = (LinearLayout) findViewById(R.id.loading);
		progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
		bottomBar_item1 = (LinearLayout) findViewById(R.id.bottomBar_item1);
		bottomBar_item3 = (LinearLayout) findViewById(R.id.bottomBar_item3);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		_drawer_linear1 = (LinearLayout) _nav_view.findViewById(R.id.linear1);
		_drawer_linear2 = (LinearLayout) _nav_view.findViewById(R.id.linear2);
		_drawer_vscroll1 = (ScrollView) _nav_view.findViewById(R.id.vscroll1);
		_drawer_circleimageview1 = (CircleImageView) _nav_view.findViewById(R.id.circleimageview1);
		_drawer_name = (TextView) _nav_view.findViewById(R.id.name);
		_drawer_email = (TextView) _nav_view.findViewById(R.id.email);
		_drawer_linear3 = (LinearLayout) _nav_view.findViewById(R.id.linear3);
		_drawer_profile = (LinearLayout) _nav_view.findViewById(R.id.profile);
		_drawer_about = (LinearLayout) _nav_view.findViewById(R.id.about);
		_drawer_social = (LinearLayout) _nav_view.findViewById(R.id.social);
		_drawer_report = (LinearLayout) _nav_view.findViewById(R.id.report);
		_drawer_share = (LinearLayout) _nav_view.findViewById(R.id.share);
		_drawer_logout = (LinearLayout) _nav_view.findViewById(R.id.logout);
		_drawer_textview1 = (TextView) _nav_view.findViewById(R.id.textview1);
		_drawer_textview3 = (TextView) _nav_view.findViewById(R.id.textview3);
		_drawer_textview4 = (TextView) _nav_view.findViewById(R.id.textview4);
		_drawer_textview5 = (TextView) _nav_view.findViewById(R.id.textview5);
		_drawer_textview6 = (TextView) _nav_view.findViewById(R.id.textview6);
		_drawer_textview7 = (TextView) _nav_view.findViewById(R.id.textview7);
		ra = new RequestNetwork(this);
		d = new AlertDialog.Builder(this);
		dexit = new AlertDialog.Builder(this);
		update = new AlertDialog.Builder(this);
		notification = new AlertDialog.Builder(this);
		logout = new AlertDialog.Builder(this);
		auth = FirebaseAuth.getInstance();
		
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
			}
		});
		
		bottomBar_item3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), NewsActivity.class);
				startActivity(i);
				finish();
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
				loading.setVisibility(View.GONE);
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_dn_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				notification.setCancelable(false);
				notification.setTitle(_childValue.get("title").toString());
				notification.setMessage(_childValue.get("message").toString());
				notification.setPositiveButton("ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				notification.create().show();
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
		dn.addChildEventListener(_dn_child_listener);
		
		_UPDATE_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				textview1.setText(_childValue.get("u").toString());
				update.setCancelable(false);
				update.setTitle("Update");
				update.setIcon(R.drawable.announcement_b);
				update.setMessage("New Update Available Now ! Please Update To Continue .");
				update.setPositiveButton("update now", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						i.setAction(Intent.ACTION_VIEW);
						i.setData(Uri.parse(textview1.getText().toString()));
						startActivity(i);
						finishAffinity();
					}
				});
				update.create().show();
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
		UPDATE.addChildEventListener(_UPDATE_child_listener);
		
		_users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					_drawer_name.setText(_childValue.get("user name").toString());
					_drawer_email.setText(_childValue.get("email").toString());
					timer = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("profile pic").toString())).into(_drawer_circleimageview1);
								}
							});
						}
					};
					_timer.schedule(timer, (int)(100));
				}
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
		users.addChildEventListener(_users_child_listener);
		
		_drawer_profile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), ProfileActivity.class);
				startActivity(i);
				finish();
			}
		});
		
		_drawer_about.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), AboutUsActivity.class);
				startActivity(i);
				finish();
			}
		});
		
		_drawer_social.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), SocialMediaActivity.class);
				startActivity(i);
				finish();
			}
		});
		
		_drawer_report.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setData(Uri.parse("mailto:shivatechnicworldofficial@gmail.com"));
				i.putExtra("subject", "");
				i.putExtra("body", "");
				startActivity(i);
			}
		});
		
		_drawer_share.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				share = "https://app.shivatechnicworld.eu.org";
				Intent i = new Intent(android.content.Intent.ACTION_SEND);i.setType("text/plain"); i.putExtra(android.content.Intent.EXTRA_TEXT,share); startActivity(Intent.createChooser(i,"share using"));
			}
		});
		
		_drawer_logout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				logout.setTitle("Confirmation !");
				logout.setMessage("Are you sure to logout ! ");
				logout.setPositiveButton("logout", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						FirebaseAuth.getInstance().signOut();
						i.setClass(getApplicationContext(), LoginActivity.class);
						startActivity(i);
						finish();
					}
				});
				logout.setNegativeButton("no", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				logout.create().show();
			}
		});
		
		auth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task){
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		auth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task){
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
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
		loading.setVisibility(View.VISIBLE);
		textview1.setVisibility(View.GONE);
		_click_effect(_drawer_profile, "#BDBDBD");
		_click_effect(_drawer_about, "#BDBDBD");
		_click_effect(_drawer_social, "#BDBDBD");
		_click_effect(_drawer_report, "#BDBDBD");
		_click_effect(_drawer_share, "#BDBDBD");
		_click_effect(_drawer_logout, "#BDBDBD");
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
		dexit.setCancelable(false);
		dexit.setTitle("Confirmation !");
		dexit.setMessage("Are You Sure To Exit ?");
		dexit.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				finishAffinity();
			}
		});
		dexit.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				
			}
		});
		dexit.create().show();
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
	
	
	public void _CornerRadius (final String _color, final double _radius, final double _shadow, final View _view) {
		android.graphics.drawable.GradientDrawable STRING = new android.graphics.drawable.GradientDrawable();
		STRING.setColor(Color.parseColor(_color));
		STRING.setCornerRadius((int)_radius);
		_view.setBackground(STRING);
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
			_view.setElevation((int)_shadow);}
	}
	
	
	public void _click_effect (final View _view, final String _c) {
		_view.setBackground(Drawables.getSelectableDrawableFor(Color.parseColor(_c)));
		_view.setClickable(true);
		
	}
	
	public static class Drawables {
		    public static android.graphics.drawable.Drawable getSelectableDrawableFor(int color) {
			        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
				            android.graphics.drawable.StateListDrawable stateListDrawable = new android.graphics.drawable.StateListDrawable();
				            stateListDrawable.addState(
				                new int[]{android.R.attr.state_pressed},
				                new android.graphics.drawable.ColorDrawable(Color.parseColor("#ffffff"))
				            );
				            stateListDrawable.addState(
				                new int[]{android.R.attr.state_focused},
				                new android.graphics.drawable.ColorDrawable(Color.parseColor("#00ffffff"))
				            );
				            stateListDrawable.addState(
				                new int[]{},
				                new android.graphics.drawable.ColorDrawable(Color.parseColor("#00ffffff"))
				            );
				            return stateListDrawable;
				        } else {
				            android.content.res.ColorStateList pressedColor = android.content.res.ColorStateList.valueOf(color);
				            android.graphics.drawable.ColorDrawable defaultColor = new android.graphics.drawable.ColorDrawable(Color.parseColor("#00ffffff"));
				            
				android.graphics.drawable.Drawable rippleColor = getRippleColor(color);
				            return new android.graphics.drawable.RippleDrawable(
				                pressedColor,
				                defaultColor,
				                rippleColor
				            );
				        }
			    }
		
		    private static android.graphics.drawable.Drawable getRippleColor(int color) {
			        float[] outerRadii = new float[8];
			        Arrays.fill(outerRadii, 0);
			        android.graphics.drawable.shapes.RoundRectShape r = new android.graphics.drawable.shapes.RoundRectShape(outerRadii, null, null);
			        
			android.graphics.drawable.ShapeDrawable shapeDrawable = new 
			android.graphics.drawable.ShapeDrawable(r);
			        shapeDrawable.getPaint().setColor(color);
			        return shapeDrawable;
			    }
		 
		    private static int lightenOrDarken(int color, double fraction) {
			        if (canLighten(color, fraction)) {
				            return lighten(color, fraction);
				        } else {
				            return darken(color, fraction);
				        }
			    }
		 
		    private static int lighten(int color, double fraction) {
			        int red = Color.red(color);
			        int green = Color.green(color);
			        int blue = Color.blue(color);
			        red = lightenColor(red, fraction);
			        green = lightenColor(green, fraction);
			        blue = lightenColor(blue, fraction);
			        int alpha = Color.alpha(color);
			        return Color.argb(alpha, red, green, blue);
			    }
		 
		    private static int darken(int color, double fraction) {
			        int red = Color.red(color);
			        int green = Color.green(color);
			        int blue = Color.blue(color);
			        red = darkenColor(red, fraction);
			        green = darkenColor(green, fraction);
			        blue = darkenColor(blue, fraction);
			        int alpha = Color.alpha(color);
			 
			        return Color.argb(alpha, red, green, blue);
			    }
		 
		    private static boolean canLighten(int color, double fraction) {
			        int red = Color.red(color);
			        int green = Color.green(color);
			        int blue = Color.blue(color);
			        return canLightenComponent(red, fraction)
			            && canLightenComponent(green, fraction)
			            && canLightenComponent(blue, fraction);
			    }
		 
		    private static boolean canLightenComponent(int colorComponent, double fraction) {
			        int red = Color.red(colorComponent);
			        int green = Color.green(colorComponent);
			        int blue = Color.blue(colorComponent);
			        return red + (red * fraction) < 255
			            && green + (green * fraction) < 255
			            && blue + (blue * fraction) < 255;
			    }
		 
		    private static int darkenColor(int color, double fraction) {
			        return (int) Math.max(color - (color * fraction), 0);
			    }
		 
		    private static int lightenColor(int color, double fraction) {
			        return (int) Math.min(color + (color * fraction), 255);
			    }
	}
	public static class CircleDrawables {
		    public static android.graphics.drawable.Drawable getSelectableDrawableFor(int color) {
			        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
				            android.graphics.drawable.StateListDrawable stateListDrawable = new android.graphics.drawable.StateListDrawable();
				            stateListDrawable.addState(
				                new int[]{android.R.attr.state_pressed},
				                new android.graphics.drawable.ColorDrawable(Color.parseColor("#ffffff"))
				            );
				            stateListDrawable.addState(
				                new int[]{android.R.attr.state_focused},
				                new android.graphics.drawable.ColorDrawable(Color.parseColor("#00ffffff"))
				            );
				            stateListDrawable.addState(
				                new int[]{},
				                new android.graphics.drawable.ColorDrawable(Color.parseColor("#00ffffff"))
				            );
				            return stateListDrawable;
				        } else {
				            android.content.res.ColorStateList pressedColor = android.content.res.ColorStateList.valueOf(color);
				            android.graphics.drawable.ColorDrawable defaultColor = new android.graphics.drawable.ColorDrawable(Color.parseColor("#00ffffff"));
				            
				android.graphics.drawable.Drawable rippleColor = getRippleColor(color);
				            return new android.graphics.drawable.RippleDrawable(
				                pressedColor,
				                defaultColor,
				                rippleColor
				            );
				        }
			    }
		
		    private static android.graphics.drawable.Drawable getRippleColor(int color) {
			        float[] outerRadii = new float[180];
			        Arrays.fill(outerRadii, 80);
			        android.graphics.drawable.shapes.RoundRectShape r = new android.graphics.drawable.shapes.RoundRectShape(outerRadii, null, null);
			        
			android.graphics.drawable.ShapeDrawable shapeDrawable = new 
			android.graphics.drawable.ShapeDrawable(r);
			        shapeDrawable.getPaint().setColor(color);
			        return shapeDrawable;
			    }
		 
		    private static int lightenOrDarken(int color, double fraction) {
			        if (canLighten(color, fraction)) {
				            return lighten(color, fraction);
				        } else {
				            return darken(color, fraction);
				        }
			    }
		 
		    private static int lighten(int color, double fraction) {
			        int red = Color.red(color);
			        int green = Color.green(color);
			        int blue = Color.blue(color);
			        red = lightenColor(red, fraction);
			        green = lightenColor(green, fraction);
			        blue = lightenColor(blue, fraction);
			        int alpha = Color.alpha(color);
			        return Color.argb(alpha, red, green, blue);
			    }
		 
		    private static int darken(int color, double fraction) {
			        int red = Color.red(color);
			        int green = Color.green(color);
			        int blue = Color.blue(color);
			        red = darkenColor(red, fraction);
			        green = darkenColor(green, fraction);
			        blue = darkenColor(blue, fraction);
			        int alpha = Color.alpha(color);
			 
			        return Color.argb(alpha, red, green, blue);
			    }
		 
		    private static boolean canLighten(int color, double fraction) {
			        int red = Color.red(color);
			        int green = Color.green(color);
			        int blue = Color.blue(color);
			        return canLightenComponent(red, fraction)
			            && canLightenComponent(green, fraction)
			            && canLightenComponent(blue, fraction);
			    }
		 
		    private static boolean canLightenComponent(int colorComponent, double fraction) {
			        int red = Color.red(colorComponent);
			        int green = Color.green(colorComponent);
			        int blue = Color.blue(colorComponent);
			        return red + (red * fraction) < 255
			            && green + (green * fraction) < 255
			            && blue + (blue * fraction) < 255;
			    }
		 
		    private static int darkenColor(int color, double fraction) {
			        return (int) Math.max(color - (color * fraction), 0);
			    }
		 
		    private static int lightenColor(int color, double fraction) {
			        return (int) Math.min(color + (color * fraction), 255);
		}
	}
	
	public void drawableclass() {
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
				_view = _inflater.inflate(R.layout.post_list, null);
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
				author.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/spotify_from_dreamstore.ttf"), 0);
				_CornerRadius("#FFFFFF", 12, 10, linear1);
				
				
				
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