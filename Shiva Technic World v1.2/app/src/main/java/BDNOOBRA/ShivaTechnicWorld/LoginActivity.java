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
import android.widget.ScrollView;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import de.hdodenhof.circleimageview.*;
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
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.OnProgressListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Continuation;
import android.net.Uri;
import java.io.File;
import android.content.Intent;
import android.content.ClipData;
import java.util.Timer;
import java.util.TimerTask;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import java.io.InputStream;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;


public class LoginActivity extends  AppCompatActivity  { 
	
	public final int REQ_CD_PIC = 101;
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private String shiva_tacnic_world_application_user_profile_picture_made_by_bd_noobra_yt = "";
	private HashMap<String, Object> map = new HashMap<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout login_layout;
	private LinearLayout signup_layout;
	private ImageView imageview1;
	private TextView textview2;
	private EditText login_email;
	private EditText login_password;
	private Button button1;
	private TextView textview3;
	private TextView textview4;
	private LinearLayout sign_up;
	private ImageView imageview2;
	private TextView textview6;
	private CircleImageView circleimageview1;
	private EditText user_name;
	private EditText email;
	private EditText password;
	private TextView profile_pic;
	private Button button2;
	private TextView textview8;
	
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
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
	private StorageReference user_pic = _firebase_storage.getReference("profile pic");
	private OnCompleteListener<Uri> _user_pic_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _user_pic_download_success_listener;
	private OnSuccessListener _user_pic_delete_success_listener;
	private OnProgressListener _user_pic_upload_progress_listener;
	private OnProgressListener _user_pic_download_progress_listener;
	private OnFailureListener _user_pic_failure_listener;
	private Intent pic = new Intent(Intent.ACTION_GET_CONTENT);
	private RequestNetwork login;
	private RequestNetwork.RequestListener _login_request_listener;
	private RequestNetwork signup;
	private RequestNetwork.RequestListener _signup_request_listener;
	private RequestNetwork reset;
	private RequestNetwork.RequestListener _reset_request_listener;
	private TimerTask t;
	private AlertDialog.Builder error;
	private AlertDialog.Builder successful;
	private Intent i = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.login);
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
		
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		login_layout = (LinearLayout) findViewById(R.id.login_layout);
		signup_layout = (LinearLayout) findViewById(R.id.signup_layout);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview2 = (TextView) findViewById(R.id.textview2);
		login_email = (EditText) findViewById(R.id.login_email);
		login_password = (EditText) findViewById(R.id.login_password);
		button1 = (Button) findViewById(R.id.button1);
		textview3 = (TextView) findViewById(R.id.textview3);
		textview4 = (TextView) findViewById(R.id.textview4);
		sign_up = (LinearLayout) findViewById(R.id.sign_up);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		textview6 = (TextView) findViewById(R.id.textview6);
		circleimageview1 = (CircleImageView) findViewById(R.id.circleimageview1);
		user_name = (EditText) findViewById(R.id.user_name);
		email = (EditText) findViewById(R.id.email);
		password = (EditText) findViewById(R.id.password);
		profile_pic = (TextView) findViewById(R.id.profile_pic);
		button2 = (Button) findViewById(R.id.button2);
		textview8 = (TextView) findViewById(R.id.textview8);
		auth = FirebaseAuth.getInstance();
		pic.setType("image/*");
		pic.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		login = new RequestNetwork(this);
		signup = new RequestNetwork(this);
		reset = new RequestNetwork(this);
		error = new AlertDialog.Builder(this);
		successful = new AlertDialog.Builder(this);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				login.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com", "A", _login_request_listener);
				if (SketchwareUtil.isConnected(getApplicationContext())) {
					if (login_email.getText().toString().equals("")) {
						((EditText)login_email).setError("Enter Your Email");
					}
					else {
						if (login_password.getText().toString().equals("")) {
							((EditText)login_password).setError("Enter Your Password");
						}
						else {
							_dialogloading(true, "Trying To Login. Please Wait...");
							auth.signInWithEmailAndPassword(login_email.getText().toString(), login_password.getText().toString()).addOnCompleteListener(LoginActivity.this, _auth_sign_in_listener);
						}
					}
				}
				else {
					error.setCancelable(false);
					error.setTitle("error");
					error.setMessage("something wrong . please check your internet connection and try again.");
					error.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
					error.create().show();
				}
			}
		});
		
		textview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				reset.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com", "A", _reset_request_listener);
				if (SketchwareUtil.isConnected(getApplicationContext())) {
					if (login_email.getText().toString().equals("")) {
						((EditText)login_email).setError("Enter your email");
					}
					else {
						_dialogloading(true, "please wait sending email");
						auth.sendPasswordResetEmail(login_email.getText().toString()).addOnCompleteListener(_auth_reset_password_listener);
					}
				}
				else {
					error.setCancelable(false);
					error.setTitle("error");
					error.setMessage("something wrong . please check your internet connection and try again.");
					error.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
					error.create().show();
				}
			}
		});
		
		textview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				signup_layout.setVisibility(View.VISIBLE);
				login_layout.setVisibility(View.GONE);
			}
		});
		
		circleimageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(pic, REQ_CD_PIC);
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				signup.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com", "A", _signup_request_listener);
				if (SketchwareUtil.isConnected(getApplicationContext())) {
					if (profile_pic.getText().toString().equals("")) {
						SketchwareUtil.CustomToast(getApplicationContext(), "upload profile picture", 0xFFFFFFFF, 15, 0xFF000000, 3, SketchwareUtil.CENTER);
					}
					else {
						if (user_name.getText().toString().equals("")) {
							((EditText)user_name).setError("Enter your name");
						}
						else {
							if (email.getText().toString().equals("")) {
								((EditText)email).setError("Enter your email");
							}
							else {
								if (password.getText().toString().equals("")) {
									((EditText)password).setError("Enter your password");
								}
								else {
									_dialogloading(true, "creating a account and uploading profile details. please wait...");
									auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(LoginActivity.this, _auth_create_user_listener);
								}
							}
						}
					}
				}
				else {
					error.setCancelable(false);
					error.setTitle("error");
					error.setMessage("something wrong . please check your internet connection and try again.");
					error.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
					error.create().show();
				}
			}
		});
		
		textview8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				signup_layout.setVisibility(View.GONE);
				login_layout.setVisibility(View.VISIBLE);
			}
		});
		
		_users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
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
		
		_user_pic_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				_dialogloading(true, "uploading your profile picture".concat("    ".concat(String.valueOf((long)(_progressValue)).concat("%"))));
			}
		};
		
		_user_pic_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_user_pic_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				_dialogloading(false, "");
				profile_pic.setText(_downloadUrl);
			}
		};
		
		_user_pic_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_user_pic_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_user_pic_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_login_request_listener = new RequestNetwork.RequestListener() {
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
		
		_signup_request_listener = new RequestNetwork.RequestListener() {
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
		
		_reset_request_listener = new RequestNetwork.RequestListener() {
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
				if (_success) {
					t = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									_dialogloading(false, "");
									map = new HashMap<>();
									map.put("profile pic", user_name.getText().toString());
									map.put("user name", user_name.getText().toString());
									map.put("email", email.getText().toString());
									map.put("password", password.getText().toString());
									map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
									users.push().updateChildren(map);
									map.clear();
									i.setClass(getApplicationContext(), HomeActivity.class);
									startActivity(i);
									finish();
								}
							});
						}
					};
					_timer.schedule(t, (int)(4500));
				}
				else {
					error.setCancelable(false);
					error.setTitle("error");
					error.setMessage(_errorMessage);
					error.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
					error.create().show();
				}
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					i.setClass(getApplicationContext(), HomeActivity.class);
					startActivity(i);
					finish();
				}
				else {
					error.setCancelable(false);
					error.setTitle("error");
					error.setMessage(_errorMessage);
					error.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
					error.create().show();
				}
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				if (_success) {
					_dialogloading(false, "");
					successful.setCancelable(false);
					successful.setTitle("Successful");
					successful.setMessage("Your Password Reset Email Sent In Your Email Address Please Reset Your Password And Continue");
					successful.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
					successful.create().show();
				}
			}
		};
	}
	
	private void initializeLogic() {
		signup_layout.setVisibility(View.GONE);
		profile_pic.setVisibility(View.GONE);
		if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
			i.setClass(getApplicationContext(), HomeActivity.class);
			startActivity(i);
			finish();
		}
		else {
			SketchwareUtil.showMessage(getApplicationContext(), "Login Please");
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_PIC:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				shiva_tacnic_world_application_user_profile_picture_made_by_bd_noobra_yt = _filePath.get((int)(0));
				user_pic.child(Uri.parse(shiva_tacnic_world_application_user_profile_picture_made_by_bd_noobra_yt).getLastPathSegment()).putFile(Uri.fromFile(new File(shiva_tacnic_world_application_user_profile_picture_made_by_bd_noobra_yt))).addOnFailureListener(_user_pic_failure_listener).addOnProgressListener(_user_pic_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
					@Override
					public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
						return user_pic.child(Uri.parse(shiva_tacnic_world_application_user_profile_picture_made_by_bd_noobra_yt).getLastPathSegment()).getDownloadUrl();
					}}).addOnCompleteListener(_user_pic_upload_success_listener);
				circleimageview1.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(_filePath.get((int)(0)), 1024, 1024));
				SketchwareUtil.showMessage(getApplicationContext(), "picked");
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	public void _dialogloading (final boolean _ifShow, final String _title) {
		if (_ifShow) {
			if (prog == null){
				prog = new ProgressDialog(this);
				prog.setMax(100);
				prog.setIndeterminate(true);
				prog.setCancelable(false);
				prog.setCanceledOnTouchOutside(false);
			}
			prog.setMessage(_title);
			prog.show();
			//powered by ashishtechnozone
			
		}
		else {
			if (prog != null){
				prog.dismiss();
			}
			//powered by ashishtechnozone
			
		}
	} private ProgressDialog prog; {
		//powered by ashishtechnozone
		
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