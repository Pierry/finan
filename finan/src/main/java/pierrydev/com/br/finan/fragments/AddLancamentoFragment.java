package pierrydev.com.br.finan.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.github.pierry.simpletoast.SimpleToast;
import com.rengwuxian.materialedittext.MaterialEditText;
import java.util.ArrayList;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import pierrydev.com.br.finan.MainActivity_;
import pierrydev.com.br.finan.R;
import pierrydev.com.br.finan.domain.contracts.repositories.IEntryRepository;
import pierrydev.com.br.finan.domain.contracts.services.ILocalService;
import pierrydev.com.br.finan.domain.entities.Entry;
import pierrydev.com.br.finan.domain.entities.Local;
import pierrydev.com.br.finan.repositories.EntryRepository;
import pierrydev.com.br.finan.services.EntryService;
import pierrydev.com.br.finan.services.LocalService;
import pierrydev.com.br.finan.utilities.DateTime;

@EFragment(R.layout.fragment_add_lancamento)
public class AddLancamentoFragment extends Fragment implements AdapterView.OnItemSelectedListener {

  @ViewById MaterialEditText etValor, etDescricao;
  @ViewById Spinner spnEstab;
  @ViewById Button btnEntrada, btnSaida, btnAdicionar, btnAddLocal;

  private int tipo = 0;
  private long local = 0;
  private int idUsuario;

  @Bean(LocalService.class) ILocalService localService;
  @Bean(EntryRepository.class) IEntryRepository lancamentoRepository;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return null;
  }

  @AfterViews
  public void spinnerAdapter() {
    List<Local> locais = localService.get();
    List<String> locaisString = new ArrayList<>();
    if (locais != null && locais.size() > 0) {
      for (Local l : locais) {
        locaisString.add(l.description);
      }
    }
    if (locais != null && locais.size() > 0) {
      ArrayAdapter<String> adapter =
          new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,
              locaisString);
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      spnEstab.setAdapter(adapter);
      spnEstab.setOnItemSelectedListener(this);
    }
  }

  @AfterViews
  public void init() {
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
    if (etValor.getText().toString().isEmpty()) {
      etValor.setError("Valor incorreto");
      return;
    }
    if (etDescricao.getText().toString().isEmpty()) {
      etDescricao.setError("Preencha corretamente");
      return;
    }
    if (tipo == 0) {
      SimpleToast.warning(getActivity(), "Selecione o tipo de lançamento",
          "{fa-exclamation-circle}");
      return;
    }
    Entry lanc = new Entry();
    lanc.dateTime =
        DateTime.getDay() + " " + DateTime.getAbreviacao(Integer.valueOf(DateTime.getMonth()));
    lanc.price = Double.valueOf(etValor.getText().toString());
    lanc.description = etDescricao.getText().toString();
    lanc.local = localService.getById(local);
    lanc.entryType = tipo;
    lancamentoRepository.create(lanc);
    Intent main = new Intent(getActivity(), MainActivity_.class);
    Bundle bundle = new Bundle();
    bundle.putLong("idusuario", lanc.getId());
    main.putExtras(bundle);
    startActivity(main);
  }

  @Click
  public void btnAddLocal() {
    final AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
    LayoutInflater inflater = LayoutInflater.from(getActivity());
    View view = inflater.inflate(R.layout.dialog_add_local, null);

    final MaterialEditText etLocal = (MaterialEditText) view.findViewById(R.id.etLocal);
    final Button btnSalvar = (Button) view.findViewById(R.id.btnSalvar);
    final Context context = getActivity();

    btnSalvar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (etLocal.getText().toString().isEmpty()) {
          etLocal.setError("Preencha corretamente");
          return;
        }
        localService.create(etLocal.getText().toString());
        spinnerAdapter();
        builder.dismiss();
      }
    });
    builder.setView(view);
    builder.setCancelable(true);
    builder.show();
  }

  @UiThread
  public void alterarTipoBotao() {
    if (tipo == 1) {
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
