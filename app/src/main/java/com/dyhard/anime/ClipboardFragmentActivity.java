package com.dyhard.anime;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import android.text.util.Linkify;
import android.text.method.LinkMovementMethod;

public class ClipboardFragmentActivity extends Fragment {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private ListView listview1;
	private TextView textview2;
	private LinearLayout linear3;
	
	private DatabaseReference notice = _firebase.getReference("notice");
	private ChildEventListener _notice_child_listener;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.clipboard_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		linear2 = _view.findViewById(R.id.linear2);
		linear7 = _view.findViewById(R.id.linear7);
		linear8 = _view.findViewById(R.id.linear8);
		listview1 = _view.findViewById(R.id.listview1);
		textview2 = _view.findViewById(R.id.textview2);
		linear3 = _view.findViewById(R.id.linear3);
		
		_notice_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				notice.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						listmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								listmap.add(_map);
							}
						} catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(listmap));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
						Collections.reverse(listmap);
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
		notice.addChildEventListener(_notice_child_listener);
	}
	
	private void initializeLogic() {
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
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.not_ce, null);
			}
			
			final LinearLayout linear10 = _view.findViewById(R.id.linear10);
			final ImageView imageview2 = _view.findViewById(R.id.imageview2);
			final LinearLayout linear20 = _view.findViewById(R.id.linear20);
			final LinearLayout name_linear = _view.findViewById(R.id.name_linear);
			final LinearLayout msg_linear = _view.findViewById(R.id.msg_linear);
			final LinearLayout linear33 = _view.findViewById(R.id.linear33);
			final LinearLayout linear32 = _view.findViewById(R.id.linear32);
			final ImageView options_icone = _view.findViewById(R.id.options_icone);
			final LinearLayout linear35 = _view.findViewById(R.id.linear35);
			final LinearLayout linear34 = _view.findViewById(R.id.linear34);
			final TextView name = _view.findViewById(R.id.name);
			final ImageView img_verified = _view.findViewById(R.id.img_verified);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView time = _view.findViewById(R.id.time);
			final LinearLayout msg_text_linear = _view.findViewById(R.id.msg_text_linear);
			final TextView message = _view.findViewById(R.id.message);
			
			message.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/daloo.ttf"), 0);
			time.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/font_righteous.ttf"), 0);
			time.setText(_data.get((int)_position).get("date").toString());
			message.setText(_data.get((int)_position).get("msg").toString());
			int[] colorsCRNHR = { Color.parseColor("#020E1E"), Color.parseColor("#020E1E") }; android.graphics.drawable.GradientDrawable CRNHR = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, colorsCRNHR);
			CRNHR.setCornerRadii(new float[]{(int)35,(int)35,(int)35,(int)35,(int)35,(int)35,(int)35,(int)35});
			CRNHR.setStroke((int) 2, Color.parseColor("#FFFFFF"));
			linear10.setElevation((float) 0);
			linear10.setBackground(CRNHR);
			Linkify.addLinks(message, Linkify.WEB_URLS);
			Linkify.addLinks(message, Linkify.WEB_URLS);
			 message.setMovementMethod(LinkMovementMethod.getInstance());
			
			return _view;
		}
	}
}