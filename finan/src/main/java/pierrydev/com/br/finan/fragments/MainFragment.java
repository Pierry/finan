package pierrydev.com.br.finan.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import at.markushi.ui.CircleButton;
import com.nhaarman.listviewanimations.appearance.simple.ScaleInAnimationAdapter;
import java.util.Collections;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import pierrydev.com.br.finan.R;
import pierrydev.com.br.finan.domain.contracts.services.IEntryService;
import pierrydev.com.br.finan.domain.contracts.services.ILocalService;
import pierrydev.com.br.finan.domain.entities.Entry;
import pierrydev.com.br.finan.services.EntryService;
import pierrydev.com.br.finan.services.LocalService;
import pierrydev.com.br.finan.utilities.Progress;
import pierrydev.com.br.finan.views.adapters.GoogleCardsAdapter;

@EFragment(R.layout.fragment_main)
public class MainFragment extends Fragment {

  private ActionBar _actionBar;
  private List<Entry> lancs;
  private GoogleCardsAdapter googleCardsAdapter;
  private int pagina = 0;

  @ViewById CircleButton addItem;
  @ViewById ListView lvDivida;

  @Bean(EntryService.class) IEntryService entryService;
  @Bean(LocalService.class) ILocalService localService;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return null;
  }

  @AfterViews
  public void init() {
    _actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
    getListView(Progress.getShow(getActivity()));
    initRodape();
  }

  @Click
  public void addItem() {
    _actionBar.setSelectedNavigationItem(2);
  }

  @Background
  public void getListView(ProgressDialog dialog) {
    lancs = entryService.get();
    Collections.reverse(lancs);
    if (lancs == null) {
      if (dialog == null) {
        dialog.dismiss();
      }
    } else if (lancs.size() == 0) {
      dialog.dismiss();
    } else {
      this.setAdapterList(lancs);
      dialog.dismiss();
    }
  }

  public void initRodape() {
    View footerView =
        ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
            R.layout.widget_rodape_button, null, false);
    Button btnFooter = (Button) footerView.findViewById(R.id.btnPagina);
    btnFooter.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        carregarMais(Progress.get(getActivity()));
      }
    });
    lvDivida.addFooterView(footerView);
  }

  @Background
  public void carregarMais(ProgressDialog dialog) {
    pagina = pagina + 1;
    lancs.addAll(entryService.get());
    dialog.dismiss();
    notifyAdapter();
  }

  @UiThread
  public void notifyAdapter() {
    googleCardsAdapter.notifyDataSetChanged();
  }

  @UiThread
  public void setAdapterList(List<Entry> models) {
    googleCardsAdapter = new GoogleCardsAdapter(getActivity(), models, localService);
    ScaleInAnimationAdapter scaleInAnimationAdapter =
        new ScaleInAnimationAdapter(googleCardsAdapter);
    scaleInAnimationAdapter.setAbsListView(lvDivida);
    lvDivida.setAdapter(googleCardsAdapter);
  }

  @ItemClick
  public void lvDividaItemClicked(final int pos) {
    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
    alert.setMessage("Deseja excluir o " + lancs.get(pos).description + "?");
    alert.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        entryService.remove(lancs.get(pos));
        lancs.remove(pos);
        notifyAdapter();
      }
    });
    alert.setNegativeButton("Cancelar", null);
    alert.setCancelable(true);
    alert.show();
  }
}
