package pierrydev.com.br.finan.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import pierrydev.com.br.finan.MainActivity_;
import pierrydev.com.br.finan.R;
import pierrydev.com.br.finan.controllers.LancamentoController;
import pierrydev.com.br.finan.controllers.LocalController;
import pierrydev.com.br.finan.domain.entities.Lancamento;
import pierrydev.com.br.finan.domain.entities.Local;
import pierrydev.com.br.finan.domain.entities.Usuario;
import pierrydev.com.br.finan.repositories.LancamentoRepository;
import pierrydev.com.br.finan.repositories.LocalRepository;
import pierrydev.com.br.finan.services.LancamentoService;
import pierrydev.com.br.finan.services.LocalService;
import pierrydev.com.br.finan.utilities.DateTime;

@EFragment(R.layout.fragment_add_lancamento)
public class AddLancamentoFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    @ViewById
    EditText etValor;

    @ViewById
    EditText etDescricao;

    @ViewById
    Spinner spnEstab;

    @ViewById
    Button btnEntrada;

    @ViewById
    Button btnSaida;

    @ViewById
    Button btnAdicionar;

    @ViewById
    Button btnAddLocal;

    private int tipo = 0;
    private int local = 0;
    private int idUsuario;

    private LocalController _localController;
    private LancamentoController _lancamentoController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return null;
    }

    @AfterViews
    public void spinnerAdapter() {
        _localController = new LocalController(new LocalService(new LocalRepository(getActivity())));
        _lancamentoController = new LancamentoController(new LancamentoService(new LancamentoRepository(getActivity())));
        List<Local> locais = _localController.get(0);
        List<String> locaisString = new ArrayList<>();
        if (locais != null && locais.size() > 0){
            for (Local l : locais) {
                locaisString.add(l.getDescricao());
            }
        }
        if (locais != null && locais.size() > 0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, locaisString);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnEstab.setAdapter(adapter);
            spnEstab.setOnItemSelectedListener(this);
        }
    }

    @AfterViews
    public void init(){
        Bundle bundle = getActivity().getIntent().getExtras();
        idUsuario = (int) bundle.getLong("idusuario");
        alterarTipoBotao();
    }

    @Click
    public void btnEntrada() {
        tipo = 1;
        alterarTipoBotao();
    }

    @Click
    public void btnSaida() {
        tipo = 2;
        alterarTipoBotao();
    }

    @Click
    public void btnAdicionar() {
        if (etValor.getText().toString().isEmpty()){
            etValor.setError("Valor incorreto");
            return;
        }
        if (etDescricao.getText().toString().isEmpty()){
            etDescricao.setError("Preencha corretamente");
            return;
        }
        if (tipo == 0){
            Crouton.cancelAllCroutons();
            Crouton.makeText(getActivity(), "Selecione o tipo de lançamento", Style.ALERT).show();
            return;
        }
        Lancamento lanc = new Lancamento();
        lanc.setData(DateTime.getDay() + " " + DateTime.getAbreviacao(Integer.valueOf(DateTime.getMonth())));
        lanc.setEnviado(0);
        lanc.setValor(Double.valueOf(etValor.getText().toString()));
        lanc.setDescricao(etDescricao.getText().toString());
        lanc.setLocal(local);
        lanc.setTipo(tipo);
        lanc.setIdUsuario(idUsuario);
        if(_lancamentoController.add(lanc).getIdLancamento() > 0){
            Intent main = new Intent(getActivity(), MainActivity_.class);
            Bundle bundle = new Bundle();
            bundle.putLong("idusuario", lanc.getIdUsuario());
            main.putExtras(bundle);
            startActivity(main);
        }
    }

    @Click
    public void btnAddLocal(){
        final AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.dialog_add_local, null);

        final EditText etLocal = (EditText) view.findViewById(R.id.etLocal);
        final Button btnSalvar = (Button) view.findViewById(R.id.btnSalvar);
        final Context context = getActivity();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etLocal.getText().toString().isEmpty()){
                    etLocal.setError("Preencha corretamente");
                    return;
                }
                Local local = new Local(etLocal.getText().toString());
                if (_localController.add(local).getIdLocal() > 0){
                    spinnerAdapter();
                    builder.dismiss();
                };
            }
        });
        builder.setView(view);
        builder.setCancelable(true);
        builder.show();
    }

    @UiThread
    public void alterarTipoBotao(){
        if (tipo == 1){
            btnEntrada.setTextColor(getActivity().getResources().getColor(R.color.blue));
            btnSaida.setTextColor(getActivity().getResources().getColor(R.color.grey));
        } else if (tipo == 2) {
            btnEntrada.setTextColor(getActivity().getResources().getColor(R.color.grey));
            btnSaida.setTextColor(getActivity().getResources().getColor(R.color.pomegranate));
        } else {
            btnEntrada.setTextColor(getActivity().getResources().getColor(R.color.grey));
            btnSaida.setTextColor(getActivity().getResources().getColor(R.color.grey));
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        local = position + 1;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
