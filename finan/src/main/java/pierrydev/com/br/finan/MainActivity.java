package pierrydev.com.br.finan;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import pierrydev.com.br.finan.views.adapters.SlidingTabLayout;
import pierrydev.com.br.finan.views.adapters.ViewPagerMainAdapter;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {

  private ActionBar mActionBar;
  private ViewPagerMainAdapter _mainAdapter;
  private FragmentManager fm;

  @ViewById ViewPager pager;
  @ViewById SlidingTabLayout sliding_tabs;

  @AfterViews
  public void init() {
    mActionBar = getSupportActionBar();
    mActionBar.setTitle("Finan");
    mActionBar.setIcon(R.drawable.ic_launcher);
    mActionBar.setDisplayShowTitleEnabled(true);

    instanceViewPager();
  }

  @UiThread
  public void instanceViewPager() {
    mActionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal)));
    ViewPager.SimpleOnPageChangeListener pageChangeListener =
        new ViewPager.SimpleOnPageChangeListener() {
          @Override
          public void onPageSelected(int position) {
            super.onPageSelected(position);
          }
        };
    fm = getSupportFragmentManager();
    pager.setOnPageChangeListener(pageChangeListener);
    _mainAdapter = new ViewPagerMainAdapter(fm, this);
    pager.setAdapter(_mainAdapter);
    sliding_tabs.setDistributeEvenly(true);
    sliding_tabs.setCustomTabView(R.layout.custom_tab, 0);
    sliding_tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
      @Override
      public int getIndicatorColor(int position) {
        return Color.WHITE;
      }
    });
    sliding_tabs.setBackgroundColor(getResources().getColor(R.color.teal));
    sliding_tabs.setViewPager(pager);
    mActionBar.setDisplayShowTitleEnabled(true);

    ActionBar.TabListener tabListener = new ActionBar.TabListener() {
      @Override
      public void onTabSelected(ActionBar.Tab tab,
          android.support.v4.app.FragmentTransaction fragmentTransaction) {
        pager.setCurrentItem(tab.getPosition());
      }

      @Override
      public void onTabUnselected(ActionBar.Tab tab,
          android.support.v4.app.FragmentTransaction fragmentTransaction) {

      }

      @Override
      public void onTabReselected(ActionBar.Tab tab,
          android.support.v4.app.FragmentTransaction fragmentTransaction) {

      }
    };

    ActionBar.Tab tab = mActionBar.newTab().setText("Itens").setTabListener(tabListener);
    mActionBar.addTab(tab);
    tab = mActionBar.newTab().setText("Relatórios").setTabListener(tabListener);
    mActionBar.addTab(tab);
    tab = mActionBar.newTab().setText("Add item").setTabListener(tabListener);
    mActionBar.addTab(tab);
  }

  public ViewPager getPager(){
    return pager;
  }

}
