package pierrydev.com.br.finan.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import pierrydev.com.br.finan.fragments.*;

public class ViewPagerMainAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;

    public ViewPagerMainAdapter(FragmentManager fm) {
        super(fm);
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

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
