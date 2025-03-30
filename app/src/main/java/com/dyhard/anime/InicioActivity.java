package com.dyhard.anime;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class InicioActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String package_name = "";
	private String your_version = "";
	
	private LinearLayout background;
	private LinearLayout view2;
	private LinearLayout view1;
	private ImageView imageview1;
	private LinearLayout linear6;
	private TextView textview1;
	private TextView textview2;
	private Button button1;
	private LinearLayout linear7;
	
	private Intent in = new Intent();
	private DatabaseReference update = _firebase.getReference("update");
	private ChildEventListener _update_child_listener;
	private DatabaseReference maintanence = _firebase.getReference("maintanence");
	private ChildEventListener _maintanence_child_listener;
	private DatabaseReference total = _firebase.getReference("users");
	private ChildEventListener _total_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.inicio);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		background = findViewById(R.id.background);
		view2 = findViewById(R.id.view2);
		view1 = findViewById(R.id.view1);
		imageview1 = findViewById(R.id.imageview1);
		linear6 = findViewById(R.id.linear6);
		textview1 = findViewById(R.id.textview1);
		textview2 = findViewById(R.id.textview2);
		button1 = findViewById(R.id.button1);
		linear7 = findViewById(R.id.linear7);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in.setClass(getApplicationContext(), ViewActivity.class);
				startActivity(in);
				finishAffinity();
			}
		});
		
		_update_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.contains("update")) {
					in.putExtra("Download", _childValue.get("Download").toString());
					in.setClass(getApplicationContext(), UpdateAppActivity.class);
					in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(in);
					finish();
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
		update.addChildEventListener(_update_child_listener);
		
		_maintanence_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("maintanence").toString().equals("on")) {
					in.setClass(getApplicationContext(), MaintanenceActivity.class);
					in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(in);
					finish();
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
		maintanence.addChildEventListener(_maintanence_child_listener);
		
		_total_child_listener = new ChildEventListener() {
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
		total.addChildEventListener(_total_child_listener);
	}
	
	private void initializeLogic() {
		_VersionApp("com.dyhard.anime");
		update.addChildEventListener(_update_child_listener);
		maintanence.addChildEventListener(_maintanence_child_listener);
		RelativeLayout rl = new RelativeLayout(this); RelativeLayout.LayoutParams lparams = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT); rl.setLayoutParams(lparams); background.removeAllViews(); 
		rl.addView(view2);
		rl.addView(view1);
		background.addView(rl);
		android.graphics.drawable.GradientDrawable HDFFFAJ = new android.graphics.drawable.GradientDrawable();
		int HDFFFAJADD[] = new int[]{ Color.parseColor("#FF181A20"), Color.parseColor("#00000000") };
		HDFFFAJ.setColors(HDFFFAJADD);
		HDFFFAJ.setOrientation(android.graphics.drawable.GradientDrawable.Orientation.BOTTOM_TOP);
		HDFFFAJ.setCornerRadius(0);
		view1.setBackground(HDFFFAJ);
		button1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFF06C149));
		if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
					    setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
					            | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, true);
		}
		if (Build.VERSION.SDK_INT >= 19) {
					    getWindow().getDecorView().setSystemUiVisibility(
					            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
					                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
					    );
		}
		if (Build.VERSION.SDK_INT >= 21) {
					    setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
					            | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, false);
					    getWindow().setStatusBarColor(Color.TRANSPARENT);
					    getWindow().setNavigationBarColor(Color.TRANSPARENT);
		}
	}
	private void setWindowFlag(final int bits, boolean on) {
		    Window win = getWindow();
		    WindowManager.LayoutParams winParams = win.getAttributes();
		    if (on) {
					        winParams.flags |= bits;
					    } else {
					        winParams.flags &= ~bits;
					    }
		    win.setAttributes(winParams);
	}
	{
	}
	
	public void _VersionApp(final String _package_name) {
		package_name = _package_name;
		try {
			android.content.pm.PackageInfo pinfo = getPackageManager().getPackageInfo( package_name, android.content.pm.PackageManager.GET_ACTIVITIES);
			your_version = pinfo.versionName; }
		catch (Exception e){ showMessage(e.toString()); }
		if (!your_version.equals("1.2")) {
			final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(InicioActivity.this);
			
			View bottomSheetView; bottomSheetView = getLayoutInflater().inflate(R.layout.protect_cus,null );
			bottomSheetDialog.setContentView(bottomSheetView);
			
			bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
			TextView t1 = (TextView) bottomSheetView.findViewById(R.id.t1);
			
			TextView t2 = (TextView) bottomSheetView.findViewById(R.id.t2);
			
			TextView b1 = (TextView) bottomSheetView.findViewById(R.id.b1);
			
			TextView b2 = (TextView) bottomSheetView.findViewById(R.id.b2);
			
			ImageView i1 = (ImageView) bottomSheetView.findViewById(R.id.i1);
			
			LinearLayout bg = (LinearLayout) bottomSheetView.findViewById(R.id.bg);
			t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
			t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
			b2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
			i1.setImageResource(R.drawable.ic_report_problem_grey);
			t1.setText("Warning !");
			t2.setText("Please don't change the Version Name of this app!\n\n~Indus Anime 🥹");
			b2.setText("EXIT");
			b1.setVisibility(View.GONE);
			_RoundAndBorder(i1, "#D50000", 0, "#D50000", 100);
			_rippleRoundStroke(bg, "#FFFFFF", "#000000", 15, 0, "#000000");
			_rippleRoundStroke(b2, "#D50000", "#40FFFFFF", 15, 0, "#000000");
			i1.setElevation((float)0.1d);
			_ICC(i1, "#FFFFFF", "#FFFFFF");
			b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
					finishAffinity();
				}
			});
			bottomSheetDialog.setCancelable(false);
			bottomSheetDialog.show();
		} else {
			
		}
	}
	
	
	public void _ICC(final ImageView _img, final String _c1, final String _c2) {
		_img.setImageTintList(new android.content.res.ColorStateList(new int[][] {{-android.R.attr.state_pressed},{android.R.attr.state_pressed}},new int[]{Color.parseColor(_c1), Color.parseColor(_c2)}));
	}
	
	
	public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor("#FF757575")}), GG, null);
		_view.setBackground(RE);
	}
	
	
	public void _RoundAndBorder(final View _view, final String _color1, final double _border, final String _color2, final double _round) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color1));
		gd.setCornerRadius((int) _round);
		gd.setStroke((int) _border, Color.parseColor(_color2));
		_view.setBackground(gd);
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
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}