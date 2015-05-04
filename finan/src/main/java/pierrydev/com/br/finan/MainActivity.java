package pierrydev.com.br.finan;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import com.mikepenz.materialdrawer.Drawer;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import pierrydev.com.br.finan.views.adapters.ViewPagerMainAdapter;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {

  private ActionBar mActionBar;
  private ViewPagerMainAdapter _mainAdapter;
  private FragmentManager fm;
  private Drawer.Result mResult;

  @ViewById ViewPager pager;

  @AfterViews
  public void init() {
    mActionBar = getSupportActionBar();
    mActionBar.setTitle("Finan");
    mActionBar.setDisplayShowTitleEnabled(true);
    mActionBar.setDisplayHomeAsUpEnabled(true);
    mActionBar.setHomeButtonEnabled(true);

    instanceViewPager();
  }

  @UiThread
  public void instanceViewPager() {
    mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    mActionBar.setStackedBackgroundDrawable(
        new ColorDrawable(getResources().getColor(R.color.teal)));
    mActionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal)));
    fm = getSupportFragmentManager();
    ViewPager.SimpleOnPageChangeListener pageChangeListener =
        new ViewPager.SimpleOnPageChangeListener() {
          @Override
          public void onPageSelected(int position) {
            super.onPageSelected(position);
          }
        };

    pager.setOnPageChangeListener(pageChangeListener);
    _mainAdapter = new ViewPagerMainAdapter(fm);
    pager.setAdapter(_mainAdapter);
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

  @OptionsItem(android.R.id.home)
  public void homeSelected(MenuItem item) {
    mResult.getActionBarDrawerToggle().onOptionsItemSelected(item);
  }
}
