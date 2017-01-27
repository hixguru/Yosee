package kr.yosee.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import kr.yosee.R;
import kr.yosee.adapter.TabPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tab_layout) TabLayout tabLayout;
    @BindView(R.id.view_pager) ViewPager viewPager;

    private final int TAB_SIZE = 4;
    private final int TAB_ICONS[] = {
        R.drawable.cook_icon, R.drawable.menu_icon, R.drawable.search_icon, R.drawable.man_icon
    };

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        for (int i = 0; i < TAB_SIZE; i++) {
            tabLayout.addTab(tabLayout.newTab().setIcon(TAB_ICONS[i]));
        }

        TabPagerAdapter adapter =
            new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), viewPager);

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(adapter);
    }
}
