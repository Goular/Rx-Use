package com.goular.rx.rxuse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.goular.rx.R;
import com.goular.rx.rxuse.fragments.AutoTextFragment;
import com.goular.rx.rxuse.fragments.CheckboxFragment;
import com.goular.rx.rxuse.fragments.SimpleClickFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RxBindingActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.rxbinding_toolbar)
    Toolbar rxbindingToolbar;
    @BindView(R.id.mlayout)
    LinearLayout mlayout;
    @BindView(R.id.simple_click_btn)
    RadioButton simpleClickBtn;
    @BindView(R.id.auto_text_btn)
    RadioButton autoTextBtn;
    @BindView(R.id.checkbox_btn)
    RadioButton checkboxBtn;
    @BindView(R.id.mRadioGroup)
    RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_binding);
        ButterKnife.bind(this);
        initToolBar();
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    private void initToolBar() {
        setSupportActionBar(rxbindingToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);//左上角返回键
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rxbinding_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.simple_click_btn:
                setFragment(SimpleClickFragment.getInstance());
                break;
            case R.id.auto_text_btn:
                setFragment(AutoTextFragment.getInstance());
                break;
            case R.id.checkbox_btn:
                setFragment(CheckboxFragment.getInstance());
                break;
        }
    }

    private void setFragment(Fragment instance) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mlayout, instance)
                .commit();
    }
}
