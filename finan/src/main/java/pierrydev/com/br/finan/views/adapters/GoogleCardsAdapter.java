package pierrydev.com.br.finan.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.devspark.robototextview.widget.RobotoTextView;
import java.util.List;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import pierrydev.com.br.finan.R;
import pierrydev.com.br.finan.domain.contracts.services.ILocalService;
import pierrydev.com.br.finan.domain.entities.Entry;
import pierrydev.com.br.finan.services.LocalService;
import pierrydev.com.br.finan.utilities.FormatarValor;
import pierrydev.com.br.finan.views.viewmodels.LancamentoVm;

public class GoogleCardsAdapter extends com.nhaarman.listviewanimations.ArrayAdapter<Entry> {

  private LayoutInflater inflater;
  ILocalService localService;
  private Context context;

  public GoogleCardsAdapter() {}

  public GoogleCardsAdapter(Context context, List<Entry> models, ILocalService localService) {
    super(models);
    this.context = context;
    inflater = LayoutInflater.from(context);
    this.localService = localService;
  }

  @Override
  public View getView(int position, View cv, ViewGroup parent) {
    LancamentoVm vm;

    if (cv == null) {
      cv = inflater.inflate(R.layout.list_bg_contas, null);
      vm = new LancamentoVm();
      cv.setTag(vm);
    } else {
      vm = (LancamentoVm) cv.getTag();
    }

    vm.tvDate = detail(cv, R.id.tvDate, getItem(position).dateTime);
    vm.tvDescription = detail(cv, R.id.tvDescription, getItem(position).description);
    vm.tvMoney = detail(cv, R.id.tvMoney, FormatarValor.getValor(getItem(position).price));
    String local = "";
    local = localService.getById(getItem(position).local.getId()).description;
    vm.ivImg = (ImageView) cv.findViewById(R.id.ivImg);
    if (local.toLowerCase().contains("almoço")
        || local.toLowerCase().contains("almoco")
        || local.toLowerCase().contains("jantar")
        || local.toLowerCase().contains("comida")
        || local.toLowerCase().contains("cafe")
        || local.toLowerCase().contains("café")
        || local.toLowerCase().contains("padaria")
        || local.toLowerCase().contains("lanche")) {
      vm.ivImg.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_food));
    } else if (local.toLowerCase().contains("mercado")
        || local.toLowerCase().contains("rancho")
        || local.toLowerCase().contains("compras")) {
      vm.ivImg.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_compras));
    } else if (local.toLowerCase().contains("combustivel") || local.toLowerCase()
        .contains("combustível") || local.toLowerCase().contains("gasolina") || local.toLowerCase()
        .contains("posto") || local.toLowerCase().contains("auto") || local.toLowerCase()
        .contains("veiculo") || local.toLowerCase().contains("transporte") || local.toLowerCase()
        .contains("carro")) {
      vm.ivImg.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_transp));
    } else if (local.toLowerCase().contains("lazer")
        || local.toLowerCase().contains("festa")
        || local.toLowerCase().contains("hobby")) {
      vm.ivImg.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_lazer));
    } else if (local.toLowerCase().contains("vestuario")
        || local.toLowerCase().contains("roupa")
        || local.toLowerCase().contains("acessorio")) {
      vm.ivImg.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_vestuario));
    } else if (local.toLowerCase().contains("viage") || local.toLowerCase().contains("ferias")) {
      vm.ivImg.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_viagens));
    } else if (local.toLowerCase().contains("saude")
        || local.toLowerCase().contains("hospital")
        || local.toLowerCase().contains("medico")) {
      vm.ivImg.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_saude));
    } else if (local.toLowerCase().contains("educação")
        || local.toLowerCase().contains("educacao")
        || local.toLowerCase().contains("faculdade")
        || local.toLowerCase().contains("curso")
        || local.toLowerCase().contains("aula")
        || local.toLowerCase().contains("escola")) {
      vm.ivImg.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_educacao));
    } else if (local.toLowerCase().contains("moradia") || local.toLowerCase().contains("casa")) {
      vm.ivImg.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_moradia));
    } else {
      vm.ivImg.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_entrada));
    }
    vm.tvLocal = detail(cv, R.id.tvLocal, local);
    return cv;
  }

  @Override
  public int getCount() {
    return super.getCount();
  }

  private RobotoTextView detail(View v, int resId, String text) {
    RobotoTextView tv = (RobotoTextView) v.findViewById(resId);
    tv.setText(text);
    return tv;
  }
}
