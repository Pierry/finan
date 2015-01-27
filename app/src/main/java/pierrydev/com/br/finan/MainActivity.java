package pierrydev.com.br.finan;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import pierrydev.com.br.finan.views.adapters.ViewPagerMainAdapter;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {

    private ActionBar _actionBar;
    private ViewPagerMainAdapter _mainAdapter;
    private FragmentManager fm;

    @ViewById
    ViewPager pager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _actionBar = getSupportActionBar();
        _actionBar.setDisplayShowTitleEnabled(true);
        _actionBar.setHomeButtonEnabled(true);
        _actionBar.setDisplayHomeAsUpEnabled(true);
        _actionBar.setHomeAsUpIndicator(R.drawable.ic_launcher);
        _actionBar.setTitle("Finan");

        instanceViewPager();
    }

    @UiThread
    public void instanceViewPager() {
        _actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        _actionBar.setStackedBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal)));
        _actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal)));
        fm = getSupportFragmentManager();
        ViewPager.SimpleOnPageChangeListener pageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        };

        pager.setOnPageChangeListener(pageChangeListener);
        _mainAdapter = new ViewPagerMainAdapter(fm);
        pager.setAdapter(_mainAdapter);
        _actionBar.setDisplayShowTitleEnabled(true);

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

            }
        };

        ActionBar.Tab tab = _actionBar.newTab()
                .setText("Itens")
                .setTabListener(tabListener);

        _actionBar.addTab(tab);

        tab = _actionBar.newTab()
                .setText("Relatórios")
                .setTabListener(tabListener);

        _actionBar.addTab(tab);

        tab = _actionBar.newTab()
                .setText("Add item")
                .setTabListener(tabListener);

        _actionBar.addTab(tab);

    }

}
