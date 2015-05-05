package pierrydev.com.br.finan.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.nhaarman.listviewanimations.appearance.simple.ScaleInAnimationAdapter;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import pierrydev.com.br.finan.R;
import pierrydev.com.br.finan.domain.contracts.services.IEntryService;
import pierrydev.com.br.finan.domain.contracts.services.ILocalService;
import pierrydev.com.br.finan.domain.entities.Entry;
import pierrydev.com.br.finan.domain.entities.Local;
import pierrydev.com.br.finan.services.EntryService;
import pierrydev.com.br.finan.services.LocalService;
import pierrydev.com.br.finan.utilities.FormatarValor;
import pierrydev.com.br.finan.utilities.Progress;
import pierrydev.com.br.finan.views.adapters.RelatorioAdapter;

@EFragment(R.layout.fragment_relatorios)
public class RelatorioFragment extends Fragment {

  @ViewById TextView tvSaldo, tvEntrada, tvSaida;
  @ViewById ListView lvRelatorio;

  @Bean(EntryService.class) IEntryService entryService;
  @Bean(LocalService.class) ILocalService localService;

  private List<Entry> lancs;
  private RelatorioAdapter _relatorioAdapter;
  private List<Double> totaisLocais;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return null;
  }

  @AfterViews
  public void init() {
    getListView(Progress.getShow(getActivity()));
  }

  @UiThread
  public void initGraafico(List<Entry> lancs) {
    LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
        new DataPoint(0, 1),
        new DataPoint(1, 4),
        new DataPoint(2, 2),
        new DataPoint(3, 2),
        new DataPoint(3, 2)
    });
  }

  @Background
  public void getListView(ProgressDialog dialog) {
    lancs = entryService.get();
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

  @UiThread
  public void setAdapterList(List<Entry> models) {
    _relatorioAdapter = new RelatorioAdapter(getActivity(), models, localService, entryService);
    ScaleInAnimationAdapter scaleInAnimationAdapter =
        new ScaleInAnimationAdapter(_relatorioAdapter);
    scaleInAnimationAdapter.setAbsListView(lvRelatorio);
    lvRelatorio.setAdapter(_relatorioAdapter);
    popularValores();
  }

  @UiThread
  public void popularValores() {
    double totalEntrada = 0.0;
    double totalSaida = 0.0;
    for (Entry l : lancs) {
      if (l.entryType == 1) {
        totalEntrada = totalEntrada + l.price;
      }
      if (l.entryType == 2) {
        totalSaida = totalSaida + l.price;
      }
    }
    double saldo = totalEntrada - totalSaida;
    tvEntrada.setText(FormatarValor.getValor(totalEntrada));
    tvSaida.setText(FormatarValor.getValor(totalSaida));
    tvSaldo.setText(FormatarValor.getValor(saldo));
    initGraafico(lancs);
  }
}
