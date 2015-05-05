package pierrydev.com.br.finan.views.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import pierrydev.com.br.finan.R;
import pierrydev.com.br.finan.fragments.*;

public class ViewPagerMainAdapter extends FragmentPagerAdapter {

  final int PAGE_COUNT = 3;
  private String tabTitles[] = new String[] { "ITEMS", "RELATÓRIOS", "ADD ITEM" };
  private Context context;

  public ViewPagerMainAdapter(FragmentManager fm, Context context) {
    super(fm);
    this.context = context;
  }

  @Override
  public Fragment getItem(int arg0) {
    switch (arg0) {
      case 0:
        return new MainFragment_();
      case 1:
        return new RelatorioFragment_();
      case 2:
        return new AddLancamentoFragment_();
    }
    return null;
  }

  private int[] imageResId = {
      R.drawable.home_ic, R.drawable.chart_ic, R.drawable.add_ic
  };

  @Override
  public CharSequence getPageTitle(int position) {
    Drawable image = context.getResources().getDrawable(imageResId[position]);
    image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
    SpannableString sb = new SpannableString(" ");
    ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
    sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    return sb;
  }

  @Override
  public int getCount() {
    return PAGE_COUNT;
  }
}
