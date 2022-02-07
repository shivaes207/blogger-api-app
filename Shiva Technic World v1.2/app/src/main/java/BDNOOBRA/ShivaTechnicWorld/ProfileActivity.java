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
import android.widget.ScrollView;
import de.hdodenhof.circleimageview.*;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import java.io.InputStream;
import android.content.Intent;
import android.net.Uri;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import android.view.View;
import com.bumptech.glide.Glide;
import android.content.ClipData;
import android.content.ClipboardManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class ProfileActivity extends  AppCompatActivity  { 
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private LinearLayout linear1;
	private LinearLayout profile;
	private ScrollView vscroll1;
	private CircleImageView circleimageview1;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private ImageView imageview10;
	private TextView user_name;
	private ImageView imageview11;
	private TextView email;
	private ImageView imageview12;
	private TextView uid;
	private LinearLayout linear6;
	private LinearLayout linear22;
	private LinearLayout linear19;
	private TextView textview22;
	private LinearLayout linear25;
	private LinearLayout linear23;
	private ImageView imageview9;
	private LinearLayout linear26;
	private TextView textview25;
	private TextView textview26;
	private ImageView imageview8;
	private LinearLayout linear24;
	private TextView textview23;
	private TextView textview24;
	
	private Intent i = new Intent();
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
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
	private TimerTask timer;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.profile);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		profile = (LinearLayout) findViewById(R.id.profile);
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		circleimageview1 = (CircleImageView) findViewById(R.id.circleimageview1);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		imageview10 = (ImageView) findViewById(R.id.imageview10);
		user_name = (TextView) findViewById(R.id.user_name);
		imageview11 = (ImageView) findViewById(R.id.imageview11);
		email = (TextView) findViewById(R.id.email);
		imageview12 = (ImageView) findViewById(R.id.imageview12);
		uid = (TextView) findViewById(R.id.uid);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear22 = (LinearLayout) findViewById(R.id.linear22);
		linear19 = (LinearLayout) findViewById(R.id.linear19);
		textview22 = (TextView) findViewById(R.id.textview22);
		linear25 = (LinearLayout) findViewById(R.id.linear25);
		linear23 = (LinearLayout) findViewById(R.id.linear23);
		imageview9 = (ImageView) findViewById(R.id.imageview9);
		linear26 = (LinearLayout) findViewById(R.id.linear26);
		textview25 = (TextView) findViewById(R.id.textview25);
		textview26 = (TextView) findViewById(R.id.textview26);
		imageview8 = (ImageView) findViewById(R.id.imageview8);
		linear24 = (LinearLayout) findViewById(R.id.linear24);
		textview23 = (TextView) findViewById(R.id.textview23);
		textview24 = (TextView) findViewById(R.id.textview24);
		auth = FirebaseAuth.getInstance();
		
		linear5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.showMessage(getApplicationContext(), "uid copied");
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", uid.getText().toString()));
			}
		});
		
		linear25.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setData(Uri.parse("mailto:shivatechnicworldofficial@gmail.com"));
				i.putExtra("subject", "");
				i.putExtra("body", "");
				startActivity(i);
			}
		});
		
		linear23.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		_users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					user_name.setText(_childValue.get("user name").toString());
					email.setText(_childValue.get("email").toString());
					uid.setText(_childValue.get("uid").toString());
					timer = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("profile pic").toString())).into(circleimageview1);
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
		int[] colorsCRNTZ = { Color.parseColor("#80FFF7"), Color.parseColor("#008BEB") }; android.graphics.drawable.GradientDrawable CRNTZ = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, colorsCRNTZ);
		CRNTZ.setCornerRadii(new float[]{(int)0,(int)0,(int)0,(int)0,(int)39,(int)39,(int)39,(int)39});
		CRNTZ.setStroke((int) 0, Color.parseColor("#000000"));
		profile.setElevation((float) 5);
		profile.setBackground(CRNTZ);
		
		//Paste this code in (add source directly block) asd block
		//Milz
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