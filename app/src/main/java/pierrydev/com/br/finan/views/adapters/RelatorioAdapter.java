package pierrydev.com.br.finan.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhaarman.listviewanimations.ArrayAdapter;

import java.util.List;

import pierrydev.com.br.finan.R;
import pierrydev.com.br.finan.controllers.LancamentoController;
import pierrydev.com.br.finan.controllers.LocalController;
import pierrydev.com.br.finan.domain.entities.Lancamento;
import pierrydev.com.br.finan.repositories.LancamentoRepository;
import pierrydev.com.br.finan.repositories.LocalRepository;
import pierrydev.com.br.finan.services.LancamentoService;
import pierrydev.com.br.finan.services.LocalService;
import pierrydev.com.br.finan.utilities.FormatarValor;
import pierrydev.com.br.finan.views.viewmodels.LancamentoVm;
import pierrydev.com.br.finan.views.viewmodels.RelatorioVm;

public class RelatorioAdapter extends
        ArrayAdapter<Lancamento> {

    private LayoutInflater inflater;
    private LocalController _localController;
    private LancamentoController _lancamentoController;
    private Context context;

    public RelatorioAdapter(Context context, List<Lancamento> models) {
        super(models);
        this.context = context;
        inflater = LayoutInflater.from(context);
        _localController = new LocalController(new LocalService(new LocalRepository(context)));
        _lancamentoController = new LancamentoController(new LancamentoService(new LancamentoRepository(context)));
    }

    @Override
    public View getView(int position, View cv, ViewGroup parent) {
        RelatorioVm vm;

        if (cv == null) {
            cv = inflater.inflate(R.layout.list_bg_relatorio, null);
            vm = new RelatorioVm();
            cv.setTag(vm);
        } else {
            vm = (RelatorioVm) cv.getTag();
        }

        double valorTotal = getItem(position).getValor();
        String local = _localController.getById((int)getItem(position).getLocal()).getDescricao();
        vm.tvDescription = detail(cv, R.id.tvDescription, local);
        vm.tvMoney = detail(cv, R.id.tvMoney, FormatarValor.getValor(valorTotal));
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
        } else if (local.toLowerCase().contains("combustivel")
                || local.toLowerCase().contains("combustível")
                || local.toLowerCase().contains("gasolina")
                || local.toLowerCase().contains("posto")
                || local.toLowerCase().contains("auto")
                || local.toLowerCase().contains("veiculo")
                || local.toLowerCase().contains("transporte")
                || local.toLowerCase().contains("carro")) {
            vm.ivImg.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_transp));
        } else if (local.toLowerCase().contains("lazer")
                || local.toLowerCase().contains("festa")
                || local.toLowerCase().contains("hobby")){
            vm.ivImg.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_lazer));
        } else if (local.toLowerCase().contains("vestuario")
                || local.toLowerCase().contains("roupa")
                || local.toLowerCase().contains("acessorio")){
            vm.ivImg.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_vestuario));
        } else if (local.toLowerCase().contains("viage")
                || local.toLowerCase().contains("ferias")){
            vm.ivImg.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_viagens));
        } else if (local.toLowerCase().contains("saude")
                || local.toLowerCase().contains("hospital")
                || local.toLowerCase().contains("medico")){
            vm.ivImg.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_saude));
        } else if (local.toLowerCase().contains("educação")
                || local.toLowerCase().contains("educacao")
                || local.toLowerCase().contains("faculdade")
                || local.toLowerCase().contains("curso")
                || local.toLowerCase().contains("aula")
                || local.toLowerCase().contains("escola")){
            vm.ivImg.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_educacao));
        } else if (local.toLowerCase().contains("moradia")
                || local.toLowerCase().contains("casa")){
            vm.ivImg.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_moradia));
        } else {
            vm.ivImg.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_entrada));
        }

        return cv;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    private TextView detail(View v, int resId, String text) {
        TextView tv = (TextView) v.findViewById(resId);
        tv.setText(text);
        return tv;
    }

}
