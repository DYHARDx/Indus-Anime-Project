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
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnAdapterChangeListener;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
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
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class ViewActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String fontName = "";
	private String typeace = "";
	private double click = 0;
	private HashMap<String, Object> map = new HashMap<>();
	
	private RelativeLayout linear;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private ViewPager viewpager1;
	private LinearLayout linear4;
	private RelativeLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private LinearLayout linear12;
	private LinearLayout linear10;
	private LinearLayout linear11;
	private LinearLayout linear_home;
	private ImageView imageview2;
	private LinearLayout linear_serch;
	private ImageView imageview3;
	private LinearLayout linear_reels;
	private ImageView imageview6;
	private LinearLayout linear_habe;
	private ImageView imageview5;
	private LinearLayout linear_profile;
	private ImageView imageview4;
	
	private Intent lntent = new Intent();
	private TimerTask timet;
	private FragmentFragmentAdapter fragment;
	private DatabaseReference Online = _firebase.getReference("user");
	private ChildEventListener _Online_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.view);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear = findViewById(R.id.linear);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		viewpager1 = findViewById(R.id.viewpager1);
		linear4 = findViewById(R.id.linear4);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		linear7 = findViewById(R.id.linear7);
		linear8 = findViewById(R.id.linear8);
		linear9 = findViewById(R.id.linear9);
		linear12 = findViewById(R.id.linear12);
		linear10 = findViewById(R.id.linear10);
		linear11 = findViewById(R.id.linear11);
		linear_home = findViewById(R.id.linear_home);
		imageview2 = findViewById(R.id.imageview2);
		linear_serch = findViewById(R.id.linear_serch);
		imageview3 = findViewById(R.id.imageview3);
		linear_reels = findViewById(R.id.linear_reels);
		imageview6 = findViewById(R.id.imageview6);
		linear_habe = findViewById(R.id.linear_habe);
		imageview5 = findViewById(R.id.imageview5);
		linear_profile = findViewById(R.id.linear_profile);
		imageview4 = findViewById(R.id.imageview4);
		fragment = new FragmentFragmentAdapter(getApplicationContext(), getSupportFragmentManager());
		
		viewpager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int _position, float _positionOffset, int _positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageSelected(int _position) {
				if (_position == 0) {
					linear_home.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, 0xFF8A56E9));
					linear_serch.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, Color.TRANSPARENT));
					linear_habe.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, Color.TRANSPARENT));
					linear_reels.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, Color.TRANSPARENT));
					_clickAnimation(linear_home);
				}
				if (_position == 1) {
					linear_serch.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, 0xFF566FE6));
					linear_home.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, Color.TRANSPARENT));
					linear_habe.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, Color.TRANSPARENT));
					linear_profile.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, Color.TRANSPARENT));
					linear_reels.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, Color.TRANSPARENT));
					_clickAnimation(linear_serch);
				}
				if (_position == 2) {
					linear_habe.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, 0xFF03A9F4));
					linear_home.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, Color.TRANSPARENT));
					linear_serch.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, Color.TRANSPARENT));
					linear_profile.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, Color.TRANSPARENT));
					linear_reels.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, Color.TRANSPARENT));
					_clickAnimation(linear_habe);
				}
				if (_position == 3) {
					linear_profile.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, 0xFF11E3B0));
					linear_home.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, Color.TRANSPARENT));
					linear_serch.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, Color.TRANSPARENT));
					linear_habe.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, Color.TRANSPARENT));
					linear_reels.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, Color.TRANSPARENT));
					_clickAnimation(linear_profile);
				}
			}
			
			@Override
			public void onPageScrollStateChanged(int _scrollState) {
				
			}
		});
		
		linear_home.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				viewpager1.setCurrentItem((int)0);
			}
		});
		
		linear_serch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				viewpager1.setCurrentItem((int)1);
			}
		});
		
		linear_reels.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				lntent.setClass(getApplicationContext(), ReelsActivity.class);
				startActivity(lntent);
			}
		});
		
		linear_habe.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				viewpager1.setCurrentItem((int)2);
			}
		});
		
		linear_profile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				viewpager1.setCurrentItem((int)3);
			}
		});
		
		_Online_child_listener = new ChildEventListener() {
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
		Online.addChildEventListener(_Online_child_listener);
	}
	
	private void initializeLogic() {
		fragment.setTabCount(4);
		viewpager1.setAdapter(fragment);
		linear6.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, 0xFF29103C));
		linear_home.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, 0xFF8A56E9));
		_Add("#FFFFFF", imageview2);
		_Add("#FFFFFF", imageview3);
		_Add("#FFFFFF", imageview5);
		_Add("#FFFFFF", imageview4);
	}
	
	public class FragmentFragmentAdapter extends FragmentStatePagerAdapter {
		// This class is deprecated, you should migrate to ViewPager2:
		// https://developer.android.com/reference/androidx/viewpager2/widget/ViewPager2
		Context context;
		int tabCount;
		
		public FragmentFragmentAdapter(Context context, FragmentManager manager) {
			super(manager);
			this.context = context;
		}
		
		public void setTabCount(int tabCount) {
			this.tabCount = tabCount;
		}
		
		@Override
		public int getCount() {
			return tabCount;
		}
		
		@Override
		public CharSequence getPageTitle(int _position) {
			
			return null;
		}
		
		@Override
		public Fragment getItem(int _position) {
			if (_position == 0) {
				return new HomeFragmentActivity();
			}
			if (_position == 1) {
				return new ExploreFragmentActivity();
			}
			if (_position == 2) {
				return new ClipboardFragmentActivity();
			}
			if (_position == 3) {
				return new ProfileFragmentActivity();
			}
			return null;
		}
	}
	
	@Override
	public void onBackPressed() {
		_Dialog("Exit", "Do you want to exit the app?", "Cancel", "Exit", true);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		map = new HashMap<>();
		map.put("tipo", "online");
		Online.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
	}
	
	@Override
	public void onStop() {
		super.onStop();
		map = new HashMap<>();
		map.put("tipo", "offline");
		Online.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
	}
	public void _Add(final String _Colour, final ImageView _Imageview) {
		_Imageview.getDrawable().setColorFilter(Color.parseColor(_Colour), PorterDuff.Mode.SRC_IN);
	}
	
	
	public void _clickAnimation(final View _view) {
		ScaleAnimation fade_in = new ScaleAnimation(0.9f, 1f, 0.9f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
		fade_in.setDuration(300);
		fade_in.setFillAfter(true);
		_view.startAnimation(fade_in);
	}
	
	
	public void _Colors(final View _view, final String _co1, final String _co2, final String _co3, final double _lt, final double _rt, final double _lb, final double _rb, final double _str, final String _str_color, final double _ele, final String _ripple) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		int clrs[] = new int[]{
			Color.parseColor(_co1), Color.parseColor(_co2),
			Color.parseColor(_co3)
		};
		gd.setColors(clrs);
		gd.setOrientation(android.graphics.drawable.GradientDrawable.Orientation.TL_BR);
		gd.setStroke((int)_str, Color.parseColor(_str_color));
		gd.setCornerRadii(new float[] {(float)_lt, (float)_lt, (float)_rt, (float)_rt, (float)_rb, (float)_rb, (float)_lb, (float)_lb});
		_view.setElevation((int)_ele);
		android.content.res.ColorStateList clrbs = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor(_ripple)});
		android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrbs , gd, null);
		_view.setBackground(ripdrb);
	}
	
	
	public void _changeActivityFont(final String _fontname) {
		fontName = "fonts/".concat(_fontname.concat(".ttf"));
		overrideFonts(this,getWindow().getDecorView()); 
	} 
	private void overrideFonts(final android.content.Context context, final View v) {
		
		try {
			Typeface 
			typeace = Typeface.createFromAsset(getAssets(), fontName);;
			if ((v instanceof ViewGroup)) {
				ViewGroup vg = (ViewGroup) v;
				for (int i = 0;
				i < vg.getChildCount();
				i++) {
					View child = vg.getChildAt(i);
					overrideFonts(context, child);
				}
			} else {
				if ((v instanceof TextView)) {
					((TextView) v).setTypeface(typeace);
				} else {
					if ((v instanceof EditText )) {
						((EditText) v).setTypeface(typeace);
					} else {
						if ((v instanceof Button)) {
							((Button) v).setTypeface(typeace);
						}
					}
				}
			}
		}
		catch(Exception e)
		
		{
			SketchwareUtil.showMessage(getApplicationContext(), "Error Loading Font");
		};
	}
	
	
	public void _Dialog(final String _text_title, final String _text_message, final String _Button1, final String _Button2, final boolean _show) {
		if (_show) {
			final AlertDialog dialog1 = new AlertDialog.Builder(ViewActivity.this).create();
			View inflate = getLayoutInflater().inflate(R.layout.exit,null); 
			dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
			dialog1.setView(inflate);
			TextView t1 = (TextView) inflate.findViewById(R.id.t1);
			
			TextView t2 = (TextView) inflate.findViewById(R.id.t2);
			
			TextView b1 = (TextView) inflate.findViewById(R.id.b1);
			
			TextView b2 = (TextView) inflate.findViewById(R.id.b2);
			
			LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
			dialog1.setCancelable(false);
			dialog1.show();
			t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/spotify_from_dreamstore.ttf"), 1);
			t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/spotify_from_dreamstore.ttf"), 0);
			b1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/spotify_from_dreamstore.ttf"), 0);
			b2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/spotify_from_dreamstore.ttf"), 0);
			t1.setText(_text_title);
			t2.setText(_text_message);
			b1.setText(_Button1);
			b2.setText(_Button2);
			_clickAnimation(bg);
			_rippleRoundStroke(bg, "#FFFFFF", "#000000", 15, 0, "#000000");
			_rippleRoundStroke(b1, "#000000", "#EEEEEE", 20, 0, "#EEEEEE");
			_rippleRoundStroke(b2, "#2196F3", "#EEEEEE", 20, 0, "#EEEEEE");
			b1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					dialog1.dismiss();
				}
			});
			b2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					dialog1.dismiss();
					finishAffinity();
				}
			});
		}
	}
	
	
	public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
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