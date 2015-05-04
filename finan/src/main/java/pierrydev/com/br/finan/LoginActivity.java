package pierrydev.com.br.finan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.github.pierry.simpletoast.SimpleToast;
import com.rengwuxian.materialedittext.MaterialEditText;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import pierrydev.com.br.finan.domain.contracts.services.IUserService;
import pierrydev.com.br.finan.domain.entities.User;
import pierrydev.com.br.finan.services.UserService;

@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity {

  @ViewById MaterialEditText etEmail;
  @ViewById EditText etPass;
  @ViewById Button btnConnect, btnSignUp, btnSaveData;

  @Bean(UserService.class) IUserService userService;

  @AfterViews void init() {
    List<User> users = userService.get();
    for (User u : users) {
      if (u.saved) {
        Intent main = new Intent(this, MainActivity_.class);
        Bundle bundle = new Bundle();
        bundle.putLong("idusuario", u.getId());
        main.putExtras(bundle);
        startActivity(main);
      }
    }
  }

  @Click void btnConnect() {
    String user = etEmail.getText().toString();
    String pass = etPass.getText().toString();
    if (!user.isEmpty() && !pass.isEmpty()) {
      User usuario = userService.auth(user, pass);
      if (usuario != null) {
        Intent main = new Intent(this, MainActivity_.class);
        Bundle bundle = new Bundle();
        bundle.putLong("idusuario", usuario.getId());
        main.putExtras(bundle);
        startActivity(main);
      } else {
        etEmail.setError("Usuário incorreto");
        etPass.setError("Senha incorreto");
      }
    } else {
      etEmail.setError("Preencha corretamente");
      etPass.setError("Prencha corretamente");
    }
  }

  @Click void btnSignUp() {
    final AlertDialog builder = new AlertDialog.Builder(this).create();
    LayoutInflater inflater = LayoutInflater.from(this);
    View view = inflater.inflate(R.layout.activity_inscreverse, null);

    final EditText etUser = (EditText) view.findViewById(R.id.etEmail);
    final EditText etPass = (EditText) view.findViewById(R.id.etPass);
    final EditText etPassConfirm = (EditText) view.findViewById(R.id.etPassConfirm);
    final Button btnSalvar = (Button) view.findViewById(R.id.btnSalvar);
    final Context context = this;

    btnSalvar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (etUser.getText().toString().isEmpty()) {
          etUser.setError("Preencha corretamente");
          return;
        }
        if (etPass.getText().toString().isEmpty()) {
          etPass.setError("Senha inválida");
          return;
        }
        if (etPassConfirm.getText().toString().isEmpty()) {
          etPassConfirm.setError("Confirme a senha");
          return;
        }
        if (!etPass.getText().toString().equals(etPassConfirm.getText().toString())) {
          etPass.setError("Senhas devem ser iguais");
          return;
        }
        userService.create(etUser.getText().toString(), etPass.getText().toString());
        User user = userService.auth(etUser.getText().toString(), etPass.getText().toString());
        Intent main = new Intent(context, MainActivity_.class);
        Bundle bundle = new Bundle();
        bundle.putLong("idusuario", user.getId());
        main.putExtras(bundle);
        startActivity(main);
      }
    });
    builder.setView(view);
    builder.setCancelable(true);
    builder.show();
  }

  @Click void btnSaveData() {
    String user = etEmail.getText().toString();
    String pass = etPass.getText().toString();
    User usuario = userService.auth(user, pass);
    if (usuario != null) {
      userService.saveProfile(usuario);
      SimpleToast.ok(this, "Salvo com sucesso", "{fa-check-circle}");
    } else {
      SimpleToast.error(this, "Dados invalidos", "{fa-times-circle}");
    }
  }
}
