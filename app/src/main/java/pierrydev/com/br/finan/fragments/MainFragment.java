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

import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.ScaleInAnimationAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.Collections;
import java.util.List;

import at.markushi.ui.CircleButton;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import pierrydev.com.br.finan.R;
import pierrydev.com.br.finan.controllers.LancamentoController;
import pierrydev.com.br.finan.domain.entities.Lancamento;
import pierrydev.com.br.finan.repositories.LancamentoRepository;
import pierrydev.com.br.finan.services.LancamentoService;
import pierrydev.com.br.finan.utilities.Progress;
import pierrydev.com.br.finan.views.adapters.GoogleCardsAdapter;

@EFragment(R.layout.fragment_main)
public class MainFragment extends Fragment {

    private ActionBar _actionBar;
    private List<Lancamento> lancs;
    private LancamentoController _lancamentoController;
    private GoogleCardsAdapter googleCardsAdapter;
    private int pagina = 0;

    @ViewById
    CircleButton addItem;

    @ViewById
    ListView lvDivida;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return null;
    }

    @AfterViews
    public void init() {
        _actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        _lancamentoController = new LancamentoController(new LancamentoService(new LancamentoRepository(getActivity())));
        getListView(Progress.getShow(getActivity()));
        initRodape();
    }

    @Click
    public void addItem() {
        _actionBar.setSelectedNavigationItem(2);
    }

    @Background
    public void getListView(ProgressDialog dialog) {
        lancs = _lancamentoController.get(0);
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

    public void initRodape(){
        View footerView = ((LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.widget_rodape_button, null, false);
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
        lancs.addAll(_lancamentoController.get(pagina));
        dialog.dismiss();
        notifyAdapter();
    }

    @UiThread
    public void notifyAdapter() {
        googleCardsAdapter.notifyDataSetChanged();
    }

    @UiThread
    public void setAdapterList(List<Lancamento> models) {
        googleCardsAdapter = new GoogleCardsAdapter(getActivity(), models);
        ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(googleCardsAdapter);
        scaleInAnimationAdapter.setAbsListView(lvDivida);
        lvDivida.setAdapter(googleCardsAdapter);
    }

    @ItemClick
    public void lvDividaItemClicked(final int pos){
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setMessage("Deseja excluir o " + lancs.get(pos).getDescricao() + "?");
        alert.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                _lancamentoController.remove((int)lancs.get(pos).getIdLancamento());
                lancs.remove(pos);
                notifyAdapter();
            }
        });
        alert.setNegativeButton("Cancelar", null);
        alert.setCancelable(true);
        alert.show();
    }
}
