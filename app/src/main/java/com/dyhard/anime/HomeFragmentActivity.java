package com.dyhard.anime;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnAdapterChangeListener;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import com.bumptech.glide.Glide;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class HomeFragmentActivity extends Fragment {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String fontName = "";
	private String typeace = "";
	private HashMap<String, Object> map = new HashMap<>();
	private HashMap<String, Object> mp = new HashMap<>();
	private double num = 0;
	private HashMap<String, Object> mmp = new HashMap<>();
	private double slider_count = 0;
	
	private ArrayList<HashMap<String, Object>> listmovie = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listanime = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> slidermap = new ArrayList<>();
	
	private LinearLayout linear1;
	private ScrollView vscroll1;
	private LinearLayout linear2;
	private CardView cardview1;
	private LinearLayout linear14;
	private LinearLayout linear5;
	private LinearLayout linear7;
	private LinearLayout linear10;
	private RelativeLayout linear3;
	private LinearLayout linear9;
	private LinearLayout linear4;
	private LinearLayout linear11;
	private ViewPager viewpager1;
	private LinearLayout linear_dots;
	private ImageView imageview1;
	private LinearLayout dot1;
	private LinearLayout dot2;
	private LinearLayout dot3;
	private LinearLayout dot4;
	private LinearLayout dot5;
	private LinearLayout linear12;
	private ImageView imageview4;
	private TextView textview17;
	private LinearLayout linear6;
	private RecyclerView recyclerview1;
	private TextView textview1;
	private TextView textview2;
	private TextView textview18;
	private ImageView imageview2;
	private LinearLayout linear8;
	private RecyclerView recyclerview2;
	private TextView textview3;
	private TextView textview4;
	private TextView textview19;
	private ImageView imageview3;
	
	private Intent intent = new Intent();
	private TimerTask timer;
	private DatabaseReference movie = _firebase.getReference("movie");
	private ChildEventListener _movie_child_listener;
	private Intent i = new Intent();
	private DatabaseReference episode = _firebase.getReference("episode");
	private ChildEventListener _episode_child_listener;
	private DatabaseReference slider = _firebase.getReference("other/slider");
	private ChildEventListener _slider_child_listener;
	private TimerTask slider_timer;
	private SharedPreferences data;
	private DatabaseReference scroll = _firebase.getReference("msg");
	private ChildEventListener _scroll_child_listener;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.home_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		vscroll1 = _view.findViewById(R.id.vscroll1);
		linear2 = _view.findViewById(R.id.linear2);
		cardview1 = _view.findViewById(R.id.cardview1);
		linear14 = _view.findViewById(R.id.linear14);
		linear5 = _view.findViewById(R.id.linear5);
		linear7 = _view.findViewById(R.id.linear7);
		linear10 = _view.findViewById(R.id.linear10);
		linear3 = _view.findViewById(R.id.linear3);
		linear9 = _view.findViewById(R.id.linear9);
		linear4 = _view.findViewById(R.id.linear4);
		linear11 = _view.findViewById(R.id.linear11);
		viewpager1 = _view.findViewById(R.id.viewpager1);
		linear_dots = _view.findViewById(R.id.linear_dots);
		imageview1 = _view.findViewById(R.id.imageview1);
		dot1 = _view.findViewById(R.id.dot1);
		dot2 = _view.findViewById(R.id.dot2);
		dot3 = _view.findViewById(R.id.dot3);
		dot4 = _view.findViewById(R.id.dot4);
		dot5 = _view.findViewById(R.id.dot5);
		linear12 = _view.findViewById(R.id.linear12);
		imageview4 = _view.findViewById(R.id.imageview4);
		textview17 = _view.findViewById(R.id.textview17);
		linear6 = _view.findViewById(R.id.linear6);
		recyclerview1 = _view.findViewById(R.id.recyclerview1);
		textview1 = _view.findViewById(R.id.textview1);
		textview2 = _view.findViewById(R.id.textview2);
		textview18 = _view.findViewById(R.id.textview18);
		imageview2 = _view.findViewById(R.id.imageview2);
		linear8 = _view.findViewById(R.id.linear8);
		recyclerview2 = _view.findViewById(R.id.recyclerview2);
		textview3 = _view.findViewById(R.id.textview3);
		textview4 = _view.findViewById(R.id.textview4);
		textview19 = _view.findViewById(R.id.textview19);
		imageview3 = _view.findViewById(R.id.imageview3);
		data = getContext().getSharedPreferences("data", Activity.MODE_PRIVATE);
		
		viewpager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int _position, float _positionOffset, int _positionOffsetPixels) {
				if (_position == 0) {
					dot1.setAlpha((float)(1));
					dot2.setAlpha((float)(0.5d));
					dot3.setAlpha((float)(0.5d));
					dot4.setAlpha((float)(0.5d));
					dot5.setAlpha((float)(0.5d));
				}
				if (_position == 1) {
					dot1.setAlpha((float)(0.5d));
					dot2.setAlpha((float)(1));
					dot3.setAlpha((float)(0.5d));
					dot4.setAlpha((float)(0.5d));
					dot5.setAlpha((float)(0.5d));
				}
				if (_position == 2) {
					dot1.setAlpha((float)(0.5d));
					dot2.setAlpha((float)(0.5d));
					dot3.setAlpha((float)(1));
					dot4.setAlpha((float)(0.5d));
					dot5.setAlpha((float)(0.5d));
				}
				if (_position == 3) {
					dot1.setAlpha((float)(0.5d));
					dot2.setAlpha((float)(0.5d));
					dot3.setAlpha((float)(0.5d));
					dot4.setAlpha((float)(1));
					dot5.setAlpha((float)(0.5d));
				}
				if (_position == 4) {
					dot1.setAlpha((float)(0.5d));
					dot2.setAlpha((float)(0.5d));
					dot3.setAlpha((float)(0.5d));
					dot4.setAlpha((float)(0.5d));
					dot5.setAlpha((float)(1));
				}
			}
			
			@Override
			public void onPageSelected(int _position) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int _scrollState) {
				
			}
		});
		
		linear6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getContext().getApplicationContext(), AllMovieActivity.class);
				startActivity(i);
			}
		});
		
		linear8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.showMessage(getContext().getApplicationContext(), "Swipe Left");
			}
		});
		
		recyclerview2.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int _scrollState) {
				super.onScrollStateChanged(recyclerView, _scrollState);
				
			}
			
			@Override
			public void onScrolled(RecyclerView recyclerView, int _offsetX, int _offsetY) {
				super.onScrolled(recyclerView, _offsetX, _offsetY);
				
			}
		});
		
		_movie_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				movie.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						listmovie = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								listmovie.add(_map);
							}
						} catch (Exception _e) {
							_e.printStackTrace();
						}
						recyclerview1.setAdapter(new Recyclerview1Adapter(listmovie));
						_reverse(listmovie);
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
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
		movie.addChildEventListener(_movie_child_listener);
		
		_episode_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				episode.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						listanime = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								listanime.add(_map);
							}
						} catch (Exception _e) {
							_e.printStackTrace();
						}
						recyclerview2.setAdapter(new Recyclerview2Adapter(listanime));
						_reverse(listanime);
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				episode.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						listanime = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								listanime.add(_map);
							}
						} catch (Exception _e) {
							_e.printStackTrace();
						}
						recyclerview2.setAdapter(new Recyclerview2Adapter(listanime));
						_reverse(listanime);
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				episode.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						listanime = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								listanime.add(_map);
							}
						} catch (Exception _e) {
							_e.printStackTrace();
						}
						recyclerview2.setAdapter(new Recyclerview2Adapter(listanime));
						_reverse(listanime);
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		episode.addChildEventListener(_episode_child_listener);
		
		_slider_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals("slider")) {
					if (_childValue.containsKey("slider1")) {
						data.edit().putString("img1", _childValue.get("slider1").toString()).commit();
					}
					if (_childValue.containsKey("slider2")) {
						data.edit().putString("img2", _childValue.get("slider2").toString()).commit();
					}
					if (_childValue.containsKey("slider3")) {
						data.edit().putString("img3", _childValue.get("slider3").toString()).commit();
					}
					if (_childValue.containsKey("slider4")) {
						data.edit().putString("img4", _childValue.get("slider4").toString()).commit();
					}
					if (_childValue.containsKey("slider5")) {
						data.edit().putString("img5", _childValue.get("slider5").toString()).commit();
					}
					if (_childValue.containsKey("icon1")) {
						data.edit().putString("icon1", _childValue.get("icon1").toString()).commit();
					}
					if (_childValue.containsKey("icon2")) {
						data.edit().putString("icon2", _childValue.get("icon2").toString()).commit();
					}
					if (_childValue.containsKey("icon3")) {
						data.edit().putString("icon3", _childValue.get("icon3").toString()).commit();
					}
					if (_childValue.containsKey("icon4")) {
						data.edit().putString("icon4", _childValue.get("icon4").toString()).commit();
					}
					if (_childValue.containsKey("icon5")) {
						data.edit().putString("icon5", _childValue.get("icon5").toString()).commit();
					}
					if (_childValue.containsKey("title1")) {
						data.edit().putString("title1", _childValue.get("title1").toString()).commit();
					}
					if (_childValue.containsKey("title2")) {
						data.edit().putString("title2", _childValue.get("title2").toString()).commit();
					}
					if (_childValue.containsKey("title3")) {
						data.edit().putString("title3", _childValue.get("title3").toString()).commit();
					}
					if (_childValue.containsKey("title4")) {
						data.edit().putString("title4", _childValue.get("title4").toString()).commit();
					}
					if (_childValue.containsKey("title5")) {
						data.edit().putString("title5", _childValue.get("title5").toString()).commit();
					}
					if (_childValue.containsKey("link1")) {
						data.edit().putString("link1", _childValue.get("link1").toString()).commit();
					}
					if (_childValue.containsKey("link2")) {
						data.edit().putString("link2", _childValue.get("link2").toString()).commit();
					}
					if (_childValue.containsKey("link3")) {
						data.edit().putString("link3", _childValue.get("link3").toString()).commit();
					}
					if (_childValue.containsKey("link4")) {
						data.edit().putString("link4", _childValue.get("link4").toString()).commit();
					}
					if (_childValue.containsKey("link5")) {
						data.edit().putString("link5", _childValue.get("link5").toString()).commit();
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals("slider")) {
					if (_childValue.containsKey("slider1")) {
						data.edit().putString("img1", _childValue.get("slider1").toString()).commit();
					}
					if (_childValue.containsKey("slider2")) {
						data.edit().putString("img2", _childValue.get("slider2").toString()).commit();
					}
					if (_childValue.containsKey("slider3")) {
						data.edit().putString("img3", _childValue.get("slider3").toString()).commit();
					}
					if (_childValue.containsKey("slider4")) {
						data.edit().putString("img4", _childValue.get("slider4").toString()).commit();
					}
					if (_childValue.containsKey("slider5")) {
						data.edit().putString("img5", _childValue.get("slider5").toString()).commit();
					}
					if (_childValue.containsKey("slider6")) {
						data.edit().putString("img6", _childValue.get("slider6").toString()).commit();
					}
					if (_childValue.containsKey("icon1")) {
						data.edit().putString("icon1", _childValue.get("icon1").toString()).commit();
					}
					if (_childValue.containsKey("icon2")) {
						data.edit().putString("icon2", _childValue.get("icon2").toString()).commit();
					}
					if (_childValue.containsKey("icon3")) {
						data.edit().putString("icon3", _childValue.get("icon3").toString()).commit();
					}
					if (_childValue.containsKey("icon4")) {
						data.edit().putString("icon4", _childValue.get("icon4").toString()).commit();
					}
					if (_childValue.containsKey("icon5")) {
						data.edit().putString("icon5", _childValue.get("icon5").toString()).commit();
					}
					if (_childValue.containsKey("icon6")) {
						data.edit().putString("icon6", _childValue.get("icon6").toString()).commit();
					}
					if (_childValue.containsKey("title1")) {
						data.edit().putString("title1", _childValue.get("title1").toString()).commit();
					}
					if (_childValue.containsKey("title2")) {
						data.edit().putString("title2", _childValue.get("title2").toString()).commit();
					}
					if (_childValue.containsKey("title3")) {
						data.edit().putString("title3", _childValue.get("title3").toString()).commit();
					}
					if (_childValue.containsKey("title4")) {
						data.edit().putString("title4", _childValue.get("title4").toString()).commit();
					}
					if (_childValue.containsKey("title5")) {
						data.edit().putString("title5", _childValue.get("title5").toString()).commit();
					}
					if (_childValue.containsKey("title6")) {
						data.edit().putString("title6", _childValue.get("title6").toString()).commit();
					}
					if (_childValue.containsKey("link1")) {
						data.edit().putString("link1", _childValue.get("link1").toString()).commit();
					}
					if (_childValue.containsKey("link2")) {
						data.edit().putString("link2", _childValue.get("link2").toString()).commit();
					}
					if (_childValue.containsKey("link3")) {
						data.edit().putString("link3", _childValue.get("link3").toString()).commit();
					}
					if (_childValue.containsKey("link4")) {
						data.edit().putString("link4", _childValue.get("link4").toString()).commit();
					}
					if (_childValue.containsKey("link5")) {
						data.edit().putString("link5", _childValue.get("link5").toString()).commit();
					}
					if (_childValue.containsKey("link6")) {
						data.edit().putString("link6", _childValue.get("link6").toString()).commit();
					}
				}
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
		slider.addChildEventListener(_slider_child_listener);
		
		_scroll_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.containsKey("N")) {
					textview17.setText(_childValue.get("N").toString());
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.containsKey("N")) {
					textview17.setText(_childValue.get("N").toString());
				}
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
		scroll.addChildEventListener(_scroll_child_listener);
	}
	
	private void initializeLogic() {
		cardview1.setCardBackgroundColor(Color.TRANSPARENT);
		cardview1.setRadius((float)25);
		_Add("#FFFFFF", imageview2);
		_Add("#FFFFFF", imageview3);
		_removeScollBar(vscroll1);
		_removeScollBar(recyclerview1);
		_removeScollBar(recyclerview2);
		movie.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				listmovie = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						listmovie.add(_map);
					}
				} catch (Exception _e) {
					_e.printStackTrace();
				}
				recyclerview1.setAdapter(new Recyclerview1Adapter(listmovie));
				recyclerview1.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
		episode.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				listanime = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						listanime.add(_map);
					}
				} catch (Exception _e) {
					_e.printStackTrace();
				}
				recyclerview2.setAdapter(new Recyclerview2Adapter(listanime));
				recyclerview2.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
		_Design();
		_ScrollingText(textview17);
		linear12.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)35, 0xFF05162C));
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
	
	
	public void _removeScollBar(final View _view) {
		_view.setVerticalScrollBarEnabled(false); _view.setHorizontalScrollBarEnabled(false);
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
	
	
	public void _ActivityTransition(final View _view, final String _transitionName, final Intent _intent) {
		_view.setTransitionName(_transitionName); android.app.ActivityOptions optionsCompat = android.app.ActivityOptions.makeSceneTransitionAnimation(getActivity(), _view, _transitionName); startActivity(_intent, optionsCompat.toBundle());
	}
	
	
	public void _reverse(final ArrayList<HashMap<String, Object>> _mapname) {
		Collections.reverse(_mapname);
	}
	
	
	public void _round_corner_and_ripple(final View _view, final double _radius, final double _shadow, final String _color, final boolean _ripple) {
		if (_ripple) {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			_view.setElevation((int)_shadow);
			android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#9e9e9e")});
			android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
			_view.setClickable(true);
			_view.setBackground(ripdrb);
		} else {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			_view.setBackground(gd);
			_view.setElevation((int)_shadow);
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
	
	
	public void _sliders() {
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("a", "b");
			slidermap.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("c", "d");
			slidermap.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("e", "f");
			slidermap.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("g", "h");
			slidermap.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("i", "j");
			slidermap.add(_item);
		}
		final float scaleFactor = 0.90f; viewpager1.setPageMargin(-30); viewpager1.setOffscreenPageLimit(2); viewpager1.setPageTransformer(false, new ViewPager.PageTransformer() { @Override public void transformPage(@NonNull View page1, float position) { page1.setScaleY((1 - Math.abs(position) * (1 - scaleFactor))); page1.setScaleX(scaleFactor + Math.abs(position) * (1 - scaleFactor)); } });
		viewpager1.setAdapter(new Viewpager1Adapter(slidermap));
	}
	
	
	public void _Design() {
		_sliders();
		_rippleRoundStroke(dot1, "#FFFFFF", "#FFFFFF", 360, 0, "#FFFFFF");
		_rippleRoundStroke(dot2, "#FFFFFF", "#FFFFFF", 360, 0, "#FFFFFF");
		_rippleRoundStroke(dot3, "#FFFFFF", "#FFFFFF", 360, 0, "#FFFFFF");
		_rippleRoundStroke(dot4, "#FFFFFF", "#FFFFFF", 360, 0, "#FFFFFF");
		_rippleRoundStroke(dot5, "#FFFFFF", "#FFFFFF", 360, 0, "#FFFFFF");
	}
	
	
	public void _ScrollingText(final TextView _view) {
		_view.setSingleLine(true);
		_view.setEllipsize(TextUtils.TruncateAt.MARQUEE);
		_view.setSelected(true);
	}
	
	public class Viewpager1Adapter extends PagerAdapter {
		
		Context _context;
		ArrayList<HashMap<String, Object>> _data;
		
		public Viewpager1Adapter(Context _ctx, ArrayList<HashMap<String, Object>> _arr) {
			_context = _ctx;
			_data = _arr;
		}
		
		public Viewpager1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_context = getContext().getApplicationContext();
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public boolean isViewFromObject(View _view, Object _object) {
			return _view == _object;
		}
		
		@Override
		public void destroyItem(ViewGroup _container, int _position, Object _object) {
			_container.removeView((View) _object);
		}
		
		@Override
		public int getItemPosition(Object _object) {
			return super.getItemPosition(_object);
		}
		
		@Override
		public CharSequence getPageTitle(int pos) {
			// Use the Activity Event (onTabLayoutNewTabAdded) in order to use this method
			return "page " + String.valueOf(pos);
		}
		
		@Override
		public Object instantiateItem(ViewGroup _container,  final int _position) {
			View _view = LayoutInflater.from(_context).inflate(R.layout.slider_cus, _container, false);
			
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final LinearLayout linear_custom = _view.findViewById(R.id.linear_custom);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final androidx.cardview.widget.CardView cardview2 = _view.findViewById(R.id.cardview2);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final ImageView imageview3 = _view.findViewById(R.id.imageview3);
			final TextView title1 = _view.findViewById(R.id.title1);
			final TextView textview2 = _view.findViewById(R.id.textview2);
			
			_round_corner_and_ripple(linear3, 20, 10, "#2196F3", true);
			if (_position == 0) {
				Glide.with(getContext().getApplicationContext()).load(Uri.parse(data.getString("img1", ""))).into(imageview1);
				Glide.with(getContext().getApplicationContext()).load(Uri.parse(data.getString("icon1", ""))).into(imageview3);
				title1.setText(data.getString("title1", ""));
				linear3.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						intent.setAction(Intent.ACTION_VIEW);
						intent.setData(Uri.parse(data.getString("link1", "")));
						startActivity(intent);
						requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
					}
				});
			}
			if (_position == 1) {
				Glide.with(getContext().getApplicationContext()).load(Uri.parse(data.getString("img2", ""))).into(imageview1);
				Glide.with(getContext().getApplicationContext()).load(Uri.parse(data.getString("icon2", ""))).into(imageview3);
				title1.setText(data.getString("title2", ""));
				linear3.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						intent.setAction(Intent.ACTION_VIEW);
						intent.setData(Uri.parse(data.getString("link2", "")));
						startActivity(intent);
						requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
					}
				});
			}
			if (_position == 2) {
				Glide.with(getContext().getApplicationContext()).load(Uri.parse(data.getString("img3", ""))).into(imageview1);
				Glide.with(getContext().getApplicationContext()).load(Uri.parse(data.getString("icon3", ""))).into(imageview3);
				title1.setText(data.getString("title3", ""));
				linear3.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						intent.setAction(Intent.ACTION_VIEW);
						intent.setData(Uri.parse(data.getString("link3", "")));
						startActivity(intent);
						requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
					}
				});
			}
			if (_position == 3) {
				Glide.with(getContext().getApplicationContext()).load(Uri.parse(data.getString("img4", ""))).into(imageview1);
				Glide.with(getContext().getApplicationContext()).load(Uri.parse(data.getString("icon4", ""))).into(imageview3);
				title1.setText(data.getString("title4", ""));
				linear3.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						intent.setAction(Intent.ACTION_VIEW);
						intent.setData(Uri.parse(data.getString("link4", "")));
						startActivity(intent);
						requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
					}
				});
			}
			if (_position == 4) {
				Glide.with(getContext().getApplicationContext()).load(Uri.parse(data.getString("img5", ""))).into(imageview1);
				Glide.with(getContext().getApplicationContext()).load(Uri.parse(data.getString("icon5", ""))).into(imageview3);
				title1.setText(data.getString("title5", ""));
				linear3.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						intent.setAction(Intent.ACTION_VIEW);
						intent.setData(Uri.parse(data.getString("link5", "")));
						startActivity(intent);
						requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
					}
				});
			}
			
			_container.addView(_view);
			return _view;
		}
	}
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.post, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final RelativeLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_view.setLayoutParams(_lp);
			cardview1.setRadius((float)30);
			cardview1.setCardBackgroundColor(Color.TRANSPARENT);
			textview1.setText(_data.get((int)_position).get("name").toString());
			Glide.with(getContext().getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("image").toString())).into(imageview1);
			cardview1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					_ActivityTransition(cardview1, "ig", intent);
					i.setAction(Intent.ACTION_VIEW);
					i.setData(Uri.parse(_data.get((int)_position).get("url").toString()));
					startActivity(i);
				}
			});
		}
		
		@Override
		public int getItemCount() {
			return _data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			public ViewHolder(View v) {
				super(v);
			}
		}
	}
	
	public class Recyclerview2Adapter extends RecyclerView.Adapter<Recyclerview2Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview2Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.post, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final RelativeLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			
			textview1.setText(listanime.get((int)_position).get("txt_channel").toString());
			Glide.with(getContext().getApplicationContext()).load(Uri.parse(listanime.get((int)_position).get("txt_img").toString())).into(imageview1);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_view.setLayoutParams(_lp);
			cardview1.setRadius((float)30);
			cardview1.setCardBackgroundColor(Color.TRANSPARENT);
			cardview1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					i.putExtra("text", listanime.get((int)_position).get("txt_channel").toString());
					i.putExtra("img", listanime.get((int)_position).get("txt_img").toString());
					i.putExtra("year", listanime.get((int)_position).get("year").toString());
					i.putExtra("about", listanime.get((int)_position).get("about").toString());
					i.setClass(getContext().getApplicationContext(), ViewAnimeActivity.class);
					startActivity(i);
				}
			});
		}
		
		@Override
		public int getItemCount() {
			return _data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			public ViewHolder(View v) {
				super(v);
			}
		}
	}
}