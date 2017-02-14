package kr.yosee.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.parse.Parse;
import kr.yosee.R;
import kr.yosee.adapter.MainPagerAdapter;
import kr.yosee.model.Recipe;
import kr.yosee.util.Constants;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tab_layout) TabLayout tabLayout;
    @BindView(R.id.view_pager) ViewPager viewPager;

    private final int TAB_SIZE = 4;
    private final int TAB_ICONS[] = {
        R.drawable.cook_icon, R.drawable.menu_icon, R.drawable.search_icon, R.drawable.man_icon
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Parse.initialize(this, "USjhdBZW0Jsm8jvedZIoc4zm0OdZRvI0lMWNoRUt",
                         "eUkreRV5NNa6iruqmLnbpTqVG6F5Z3MZDT0bWJxo");

        setSupportActionBar(toolbar);
        initView();
    }

    private void initView() {
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        for (int i = 0; i < TAB_SIZE; i++) {
            tabLayout.addTab(tabLayout.newTab().setIcon(TAB_ICONS[i]));
        }

        MainPagerAdapter adapter =
            new MainPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), viewPager);

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_post:
                Intent intent = new Intent(this, UploadActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
