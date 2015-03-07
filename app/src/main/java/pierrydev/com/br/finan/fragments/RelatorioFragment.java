package pierrydev.com.br.finan.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.nhaarman.listviewanimations.appearance.simple.ScaleInAnimationAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import pierrydev.com.br.finan.R;
import pierrydev.com.br.finan.controllers.LancamentoController;
import pierrydev.com.br.finan.domain.entities.Lancamento;
import pierrydev.com.br.finan.repositories.LancamentoRepository;
import pierrydev.com.br.finan.services.LancamentoService;
import pierrydev.com.br.finan.utilities.FormatarValor;
import pierrydev.com.br.finan.utilities.Progress;
import pierrydev.com.br.finan.views.adapters.RelatorioAdapter;

@EFragment(R.layout.fragment_relatorios)
public class RelatorioFragment extends Fragment {

    @ViewById
    ListView lvRelatorio;

    @ViewById
    TextView tvSaldo;

    @ViewById
    TextView tvEntrada;

    @ViewById
    TextView tvSaida;

    @ViewById
    GraphView graph;

    private List<Lancamento> lancs;
    private LancamentoController _lancamentoController;
    private RelatorioAdapter _relatorioAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return null;
    }

    @AfterViews
    public void init() {

        _lancamentoController = new LancamentoController(new LancamentoService(new LancamentoRepository(getActivity())));
        getListView(Progress.getShow(getActivity()));
    }

    @UiThread
    public void initGraafico(List<Lancamento> lancs){
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);
    }

    @Background
    public void getListView(ProgressDialog dialog) {
        lancs = _lancamentoController.get();
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
    public void setAdapterList(List<Lancamento> models) {
        _relatorioAdapter = new RelatorioAdapter(getActivity(), models);
        ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(_relatorioAdapter);
        scaleInAnimationAdapter.setAbsListView(lvRelatorio);
        lvRelatorio.setAdapter(_relatorioAdapter);
        popularValores();
    }

    @UiThread
    public void popularValores(){
        double totalEntrada = 0.0;
        double totalSaida = 0.0;
        for (Lancamento l : lancs) {
            if (l.getTipo() == 1){
                totalEntrada = totalEntrada + l.getValor();
            }
            if (l.getTipo() == 2){
                totalSaida = totalSaida + l.getValor();
            }
        }
        double saldo = totalEntrada - totalSaida;
        tvEntrada.setText(FormatarValor.getValor(totalEntrada));
        tvSaida.setText(FormatarValor.getValor(totalSaida));
        tvSaldo.setText(FormatarValor.getValor(saldo));
        initGraafico(lancs);

    }

}
