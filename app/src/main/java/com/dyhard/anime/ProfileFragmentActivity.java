package com.dyhard.anime;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
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
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.firebase.FirebaseApp;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class ProfileFragmentActivity extends Fragment {
	
	private HashMap<String, Object> mp = new HashMap<>();
	private String share = "";
	
	private ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	
	private LinearLayout bg_hevo;
	private LinearLayout linear3;
	private LinearLayout b_hevo_4;
	private LinearLayout bg_hevo_2;
	private LinearLayout linear4;
	private ImageView image_hevo3;
	private ImageView image_hevo4;
	private TextView text_hevo1;
	private TextView text_hevo2;
	private LinearLayout bt_hevo_pro;
	private TextView text_hevo_pro;
	private LinearLayout bt_hevo_1;
	private LinearLayout bt_hevo_2;
	private LinearLayout bt_hevo_3;
	private LinearLayout bt_hevo_4;
	private LinearLayout bt_hevo_5;
	private LinearLayout bt_hevo_6;
	private ImageView image_hevo5;
	private TextView text_hevo3;
	private ImageView image_hevo_bt1;
	private ImageView image_hevo6;
	private TextView text_hevo4;
	private ImageView image_hevo_bt2;
	private ImageView image_hevo7;
	private TextView text_hevo5;
	private ImageView image_hevo_bt3;
	private ImageView image_hevo8;
	private TextView text_hevo6;
	private ImageView image_hevo_bt4;
	private ImageView image_hevo9;
	private TextView text_hevo7;
	private ImageView image_hevo_bt5;
	private ImageView image_hevo10;
	private TextView text_hevo8;
	private ImageView image_hevo_bt6;
	
	private Intent intent = new Intent();
	private Intent i = new Intent();
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.profile_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		bg_hevo = _view.findViewById(R.id.bg_hevo);
		linear3 = _view.findViewById(R.id.linear3);
		b_hevo_4 = _view.findViewById(R.id.b_hevo_4);
		bg_hevo_2 = _view.findViewById(R.id.bg_hevo_2);
		linear4 = _view.findViewById(R.id.linear4);
		image_hevo3 = _view.findViewById(R.id.image_hevo3);
		image_hevo4 = _view.findViewById(R.id.image_hevo4);
		text_hevo1 = _view.findViewById(R.id.text_hevo1);
		text_hevo2 = _view.findViewById(R.id.text_hevo2);
		bt_hevo_pro = _view.findViewById(R.id.bt_hevo_pro);
		text_hevo_pro = _view.findViewById(R.id.text_hevo_pro);
		bt_hevo_1 = _view.findViewById(R.id.bt_hevo_1);
		bt_hevo_2 = _view.findViewById(R.id.bt_hevo_2);
		bt_hevo_3 = _view.findViewById(R.id.bt_hevo_3);
		bt_hevo_4 = _view.findViewById(R.id.bt_hevo_4);
		bt_hevo_5 = _view.findViewById(R.id.bt_hevo_5);
		bt_hevo_6 = _view.findViewById(R.id.bt_hevo_6);
		image_hevo5 = _view.findViewById(R.id.image_hevo5);
		text_hevo3 = _view.findViewById(R.id.text_hevo3);
		image_hevo_bt1 = _view.findViewById(R.id.image_hevo_bt1);
		image_hevo6 = _view.findViewById(R.id.image_hevo6);
		text_hevo4 = _view.findViewById(R.id.text_hevo4);
		image_hevo_bt2 = _view.findViewById(R.id.image_hevo_bt2);
		image_hevo7 = _view.findViewById(R.id.image_hevo7);
		text_hevo5 = _view.findViewById(R.id.text_hevo5);
		image_hevo_bt3 = _view.findViewById(R.id.image_hevo_bt3);
		image_hevo8 = _view.findViewById(R.id.image_hevo8);
		text_hevo6 = _view.findViewById(R.id.text_hevo6);
		image_hevo_bt4 = _view.findViewById(R.id.image_hevo_bt4);
		image_hevo9 = _view.findViewById(R.id.image_hevo9);
		text_hevo7 = _view.findViewById(R.id.text_hevo7);
		image_hevo_bt5 = _view.findViewById(R.id.image_hevo_bt5);
		image_hevo10 = _view.findViewById(R.id.image_hevo10);
		text_hevo8 = _view.findViewById(R.id.text_hevo8);
		image_hevo_bt6 = _view.findViewById(R.id.image_hevo_bt6);
		
		bt_hevo_pro.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setData(Uri.parse("https://dyhardx.github.io/Indus-Anime/donation.html"));
				startActivity(i);
			}
		});
		
		bt_hevo_1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("https://dyhardx.github.io/Indus-Anime/about.html"));
				startActivity(intent);
			}
		});
		
		bt_hevo_2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setData(Uri.parse("mailto:dyhardeveloper@gmail.com"));
				startActivity(i);
			}
		});
		
		bt_hevo_3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setData(Uri.parse("mailto:dyhardeveloper@gmail.com"));
				startActivity(i);
			}
		});
		
		bt_hevo_4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setData(Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSdg1DDNHz5DqPlvukZjy4qxDvTRkDc3Sp-PUtig0N8WsE6F3Q/viewform?usp=dialog"));
				startActivity(i);
			}
		});
		
		bt_hevo_5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				share = "𝗜𝗻𝗱𝘂𝘀 𝗔𝗻𝗶𝗺𝗲\nWatch Fully Hindi Anime & Movies \nWithout Ads , No Login Totaly Free.\nDownload Now 🔗....\nhttps://shorturl.at/LnHDh";
				Intent i = new Intent(android.content.Intent.ACTION_SEND);i.setType("text/plain"); i.putExtra(android.content.Intent.EXTRA_TEXT, share); startActivity(Intent.createChooser(i,"Share using"));
			}
		});
		
		bt_hevo_6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setData(Uri.parse("https://dyhardx.github.io/Indus-Anime"));
				startActivity(i);
			}
		});
	}
	
	private void initializeLogic() {
		bg_hevo.setBackgroundColor(0xFF020E1C);
		text_hevo1.setTextColor(0xFFFFFFFF);
		text_hevo2.setTextColor(0xFFFFFFFF);
		text_hevo3.setTextColor(0xFFFFFFFF);
		text_hevo4.setTextColor(0xFFFFFFFF);
		text_hevo5.setTextColor(0xFFFFFFFF);
		text_hevo6.setTextColor(0xFFFFFFFF);
		text_hevo7.setTextColor(0xFFFFFFFF);
		text_hevo8.setTextColor(0xFFFFFFFF);
		_rippleRoundStroke(bt_hevo_1, "#FF323232", "#FFFFFF", 80, 0, "#FFFFFF");
		_rippleRoundStroke(bt_hevo_2, "#FF323232", "#FFFFFF", 80, 0, "#FFFFFF");
		_rippleRoundStroke(bt_hevo_3, "#FF323232", "#FFFFFF", 80, 0, "#FFFFFF");
		_rippleRoundStroke(bt_hevo_4, "#FF323232", "#FFFFFF", 80, 0, "#FFFFFF");
		_rippleRoundStroke(bt_hevo_5, "#FF323232", "#FFFFFF", 80, 0, "#FFFFFF");
		_rippleRoundStroke(bt_hevo_6, "#FF323232", "#FFFFFF", 80, 0, "#FFFFFF");
		_rippleRoundStroke(bt_hevo_pro, "#FF4433", "#FFFFFF", 80, 0, "#FFFFFF");
		_ICC(image_hevo5, "#F3F7FB", "#FFFFFF");
		_ICC(image_hevo6, "#F3F7FB", "#FFFFFF");
		_ICC(image_hevo7, "#F3F7FB", "#FFFFFF");
		_ICC(image_hevo8, "#F3F7FB", "#FFFFFF");
		_ICC(image_hevo9, "#F3F7FB", "#FFFFFF");
		_ICC(image_hevo10, "#F3F7FB", "#FFFFFF");
		_ICC(image_hevo_bt1, "#F3F7FB", "#FFFFFF");
		_ICC(image_hevo_bt2, "#F3F7FB", "#FFFFFF");
		_ICC(image_hevo_bt3, "#F3F7FB", "#FFFFFF");
		_ICC(image_hevo_bt4, "#F3F7FB", "#FFFFFF");
		_ICC(image_hevo_bt5, "#F3F7FB", "#FFFFFF");
		_ICC(image_hevo_bt6, "#F3F7FB", "#FFFFFF");
		_ICC(image_hevo3, "#FFFFFF", "#F3F7FB");
	}
	
	public void _ActivityTransition(final View _view, final String _transitionName, final Intent _intent) {
		_view.setTransitionName(_transitionName); android.app.ActivityOptions optionsCompat = android.app.ActivityOptions.makeSceneTransitionAnimation(getActivity(), _view, _transitionName); startActivity(_intent, optionsCompat.toBundle());
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
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor(_pressed)}), GG, null);
		_view.setBackground(RE);
	}
	
}