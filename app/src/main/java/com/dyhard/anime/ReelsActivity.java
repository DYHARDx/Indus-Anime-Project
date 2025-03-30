package com.dyhard.anime;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
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

public class ReelsActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private ArrayList<HashMap<String, Object>> listanime = new ArrayList<>();
	
	private LinearLayout body;
	private LinearLayout linear2;
	private RecyclerView recyclerview1;
	
	private Intent intent = new Intent();
	private Intent i = new Intent();
	private DatabaseReference vdb = _firebase.getReference("vshorts/videos");
	private ChildEventListener _vdb_child_listener;
	private TimerTask timer;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.reels);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
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
		body = findViewById(R.id.body);
		linear2 = findViewById(R.id.linear2);
		recyclerview1 = findViewById(R.id.recyclerview1);
		
		_vdb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				vdb.addListenerForSingleValueEvent(new ValueEventListener() {
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
						recyclerview1.setAdapter(new Recyclerview1Adapter(listanime));
						Collections.shuffle(listanime);
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
		vdb.addChildEventListener(_vdb_child_listener);
	}
	
	private void initializeLogic() {
		recyclerview1.setLayoutManager(new LinearLayoutManager(this));
		recyclerview1.setAdapter(new Recyclerview1Adapter(listanime));
		recyclerview1.setAdapter(adapter);
		
		SnapHelper snapHelper = new PagerSnapHelper(); snapHelper.attachToRecyclerView(recyclerview1); 
		recyclerview1.setLayoutManager(layout);
	}
	Recyclerview1Adapter adapter = new Recyclerview1Adapter(listanime);
	
	LinearLayoutManager layout = new LinearLayoutManager(this);
	{
	}
	
	public void _setProgressbarColor(final ProgressBar _prog, final String _color) {
		if (android.os.Build.VERSION.SDK_INT >= 21) {
			_prog.getIndeterminateDrawable().setColorFilter(Color.parseColor(_color), PorterDuff.Mode.SRC_IN);
			//KerDev
		}
	}
	
	
	public void _reverse(final ArrayList<HashMap<String, Object>> _mapname) {
		Collections.reverse(_mapname);
	}
	
	
	public void _Add(final String _Colour, final ImageView _Imageview) {
		_Imageview.getDrawable().setColorFilter(Color.parseColor(_Colour), PorterDuff.Mode.SRC_IN);
	}
	
	
	public void _Download(final String _url, final String _path) {
		try{
			DownloadManager.Request request = new DownloadManager.Request(Uri.parse( _url)); 
			request.setMimeType(This.getContentResolver().getType(Uri.parse(_url))); 
			String cookies = CookieManager.getInstance().getCookie(_url); 
			request.addRequestHeader("cookie", cookies); 
			//request.addRequestHeader("User-Agent", tab.getSettings().getUserAgentString());
			request.setDescription("Downloading..."); 
			request.setTitle(URLUtil.guessFileName(_url,"",""));
			request.allowScanningByMediaScanner(); 
			request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); 
			request.setDestinationInExternalPublicDir( _path.equals("")?Environment.DIRECTORY_DOWNLOADS:_path, URLUtil.guessFileName(_url,"","")); 
			DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE); dm.enqueue(request);
			Toast.makeText(getApplicationContext(), "Downloading", Toast.LENGTH_LONG).show();
		}catch(Exception e){showMessage(e.toString());}
	}final Context This = this; void nothing(){
		
	}
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.shorts, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final RelativeLayout layout = _view.findViewById(R.id.layout);
			final LinearLayout linear7 = _view.findViewById(R.id.linear7);
			final LinearLayout linear11 = _view.findViewById(R.id.linear11);
			final LinearLayout linear = _view.findViewById(R.id.linear);
			final LinearLayout holder = _view.findViewById(R.id.holder);
			final VideoView videoview = _view.findViewById(R.id.videoview);
			final ProgressBar progressbar4 = _view.findViewById(R.id.progressbar4);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final ImageView like = _view.findViewById(R.id.like);
			final TextView likecount = _view.findViewById(R.id.likecount);
			final ImageView imageview4 = _view.findViewById(R.id.imageview4);
			final ImageView imageview6 = _view.findViewById(R.id.imageview6);
			final ImageView imageview7 = _view.findViewById(R.id.imageview7);
			final LinearLayout linear9 = _view.findViewById(R.id.linear9);
			final LinearLayout linear10 = _view.findViewById(R.id.linear10);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final LinearLayout linear12 = _view.findViewById(R.id.linear12);
			final ImageView imageview8 = _view.findViewById(R.id.imageview8);
			final TextView user = _view.findViewById(R.id.user);
			final TextView audio = _view.findViewById(R.id.audio);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final ImageView imageview5 = _view.findViewById(R.id.imageview5);
			
			if (_data.get((int)_position).containsKey("url")) {
				RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
				_view.setLayoutParams(_lp);
				_setProgressbarColor(progressbar4, "#ffffff");
				progressbar4.setIndeterminate(true);
				linear7.getLayoutParams().height= ViewGroup.LayoutParams.MATCH_PARENT;
				
				linear11.getLayoutParams().height= ViewGroup.LayoutParams.MATCH_PARENT;
				videoview.setVideoURI(Uri.parse(listanime.get((int)_position).get("url").toString()));
				user.setText("DYHARD");
				likecount.setText(listanime.get((int)_position).get("like").toString());
				videoview.start();
				timer = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								if (videoview.isPlaying()) {
									progressbar4.setVisibility(View.GONE);
								} else {
									videoview.start();
									progressbar4.setVisibility(View.VISIBLE);
								}
							}
						});
					}
				};
				_timer.scheduleAtFixedRate(timer, (int)(100), (int)(500));
				imageview4.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						_Download(listanime.get((int)_position).get("url").toString(), "/Indus Anime/Reels/");
						SketchwareUtil.showMessage(getApplicationContext(), "Downloading......");
					}
				});
			}
			imageview6.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					i.setAction(Intent.ACTION_VIEW);
					i.setData(Uri.parse("https://www.instagram.com/dyhard.x?igsh=Y204bG9mNjlxM25k"));
					startActivity(i);
				}
			});
			like.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					SketchwareUtil.showMessage(getApplicationContext(), "You Are Not Allowed !");
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