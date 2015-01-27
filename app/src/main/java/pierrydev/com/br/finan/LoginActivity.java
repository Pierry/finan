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

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import pierrydev.com.br.finan.controllers.UsuarioController;
import pierrydev.com.br.finan.domain.entities.Usuario;
import pierrydev.com.br.finan.repositories.UsuarioRepository;
import pierrydev.com.br.finan.services.UsuarioService;

@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity {

    @ViewById
    EditText etEmail;

    @ViewById
    EditText etPass;

    @ViewById
    Button btnConnect;

    @ViewById
    Button btnSignUp;

    @ViewById
    Button btnSaveData;

    private UsuarioController _usuarioController;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
    }

    @AfterViews
    public void init(){
        _usuarioController = new UsuarioController(new UsuarioService(new UsuarioRepository(this)));
        List<Usuario> users = _usuarioController.get(0);
        for (Usuario u : users){
            if (u.getSalvo() == 1){
                Intent main = new Intent(this, MainActivity_.class);
                Bundle bundle = new Bundle();
                bundle.putLong("idusuario", u.getIdUsuario());
                main.putExtras(bundle);
                startActivity(main);
            }
        }
    }

    @Click
    public void btnConnect() {
        String user = etEmail.getText().toString();
        String pass = etPass.getText().toString();
        if (!user.isEmpty() && !pass.isEmpty()){
            Usuario usuario = _usuarioController.getByUserPass(user, pass);
            if (usuario != null){
                Intent main = new Intent(this, MainActivity_.class);
                Bundle bundle = new Bundle();
                bundle.putLong("idusuario", usuario.getIdUsuario());
                main.putExtras(bundle);
                startActivity(main);
            } else {
                etEmail.setError("Usuário incorreto");
                etPass.setError("Senha incorreto");
            }
        } else if (!user.isEmpty()){
            Usuario usuario = _usuarioController.getByUser(user);
            if (usuario != null){
                etPass.setError("senha incorreta");
            } else {
                etEmail.setError("Usuário inexistente");
            }
        } else {
            etEmail.setError("Preencha corretamente");
            etPass.setError("Prencha corretamente");
        }
    }

    @Click
    public void btnSignUp() {
        final AlertDialog builder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.activity_inscreverse, null);

        final EditText etUser = (EditText) view.findViewById(R.id.etUser);
        final EditText etPass = (EditText) view.findViewById(R.id.etPass);
        final EditText etPassConfirm = (EditText) view.findViewById(R.id.etPassConfirm);
        final Button btnSalvar = (Button) view.findViewById(R.id.btnSalvar);
        final Context context = this;

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUser.getText().toString().isEmpty()){
                    etUser.setError("Preencha corretamente");
                    return;
                }
                if (etPass.getText().toString().isEmpty()){
                    etPass.setError("Senha inválida");
                    return;
                }
                if (etPassConfirm.getText().toString().isEmpty()){
                    etPassConfirm.setError("Confirme a senha");
                    return;
                }
                if (!etPass.getText().toString().equals(etPassConfirm.getText().toString())){
                    etPass.setError("Senhas devem ser iguais");
                    return;
                }
                Usuario usuario = new Usuario(etUser.getText().toString(), etPass.getText().toString());
                if (_usuarioController.add(usuario).getIdUsuario() > 0){
                    Intent main = new Intent(context, MainActivity_.class);
                    Bundle bundle = new Bundle();
                    bundle.putLong("idusuario", usuario.getIdUsuario());
                    main.putExtras(bundle);
                    startActivity(main);
                };
            }
        });
        builder.setView(view);
        builder.setCancelable(true);
        builder.show();
    }

    @Click
    public void btnSaveData() {
        String user = etEmail.getText().toString();
        String pass = etPass.getText().toString();
        Usuario usuario = _usuarioController.getByUserPass(user, pass);
        if (usuario != null){
            usuario.setSalvo(1);
            _usuarioController.update(usuario);
            Crouton.makeText(this, "Salvo com sucesso", Style.CONFIRM).show();
        } else {
            Crouton.makeText(this, "Dados invalidos", Style.ALERT).show();
        }
    }

}
