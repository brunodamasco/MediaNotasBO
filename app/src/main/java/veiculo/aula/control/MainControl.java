package veiculo.aula.control;

import android.app.Activity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import veiculo.aula.R;
import veiculo.aula.model.Aluno;
import veiculo.aula.model.AlunoBO;

public class MainControl {
    private Activity activity;
    private EditText editNome;
    private EditText editNota1;
    private EditText editNota2;
    private TextView tvResultado;
    private LinearLayout layoutResultado;

    public MainControl(Activity activity) {
        this.activity = activity;
        initComponents();
    }

    private void initComponents() {
        editNome = activity.findViewById(R.id.editNome);
        editNota1 = activity.findViewById(R.id.editNota1);
        editNota2 = activity.findViewById(R.id.editNota2);
        tvResultado = activity.findViewById(R.id.tvResultado);
        layoutResultado = activity.findViewById(R.id.layoutRes);
    }

    public void salvarAction() {
        Aluno a = new Aluno();
        a.setNome(editNome.getText().toString());
        a.setNota1(editNota1.getText().toString());
        a.setNota2(editNota2.getText().toString());

        if (a.getNome().isEmpty()){
            editNome.setError("Digite o nome corretamente");
            editNome.requestFocus();
            return;
        }

        /*if (!AlunoBO.validaNome(a)) {
            editNome.setError(activity.getString(R.string.erro_nome_invalido));
            Toast.makeText(activity, R.string.erro_nome_invalido, Toast.LENGTH_LONG).show();
            return;
        }*/

        if (!AlunoBO.validaNota1(a)){
            editNota1.setError(activity.getString(R.string.erro_nota1_invalida));
            Toast.makeText(activity, R.string.erro_nota1_invalida, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!AlunoBO.validaNota2(a)){
            editNota2.setError(activity.getString(R.string.erro_nota2_invalida));
            Toast.makeText(activity, R.string.erro_nota2_invalida, Toast.LENGTH_SHORT).show();
            return;
        }

        AlunoBO aBO = new AlunoBO(a);

        // Saída Estática
        // tvResultado.setText(aBO.toString());

        // Saída Dinâmica
        TextView tvNewResultado = new TextView(activity);
        tvNewResultado.setText(aBO.toString());
        layoutResultado.addView(tvNewResultado);
    }

    public void limparForm(){
        editNome.setText("");
        editNota1.setText("");
        editNota2.setText("");
        editNome.requestFocus();
    }

    public void limparAction(){
        limparForm();
        tvResultado.setText("");
        layoutResultado.removeAllViews();
    }
}
