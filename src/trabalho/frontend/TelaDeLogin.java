package trabalho.frontend;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import trabalho.backend.BancoDeDados;
import trabalho.dominio.Usuario;

public class TelaDeLogin extends StackPane {
	private TelaPrincipal telaPrincipal;
	public TelaDeLogin(TelaPrincipal principal) {
		this.telaPrincipal = principal;
		// Criando elementos da tela de login
		Label labelUsuario = new Label("Usuário(cpf):");
		TextField cpf = new TextField();
		Label labelSenha = new Label("Senha:");
		PasswordField senha = new PasswordField(); // Campo de senha
		Button btnLogin = new Button("Login");
		btnLogin.setOnAction(e -> entrar(cpf, senha));
		// Criando layout da tela de login
		GridPane tabelaPainel = new GridPane();
		tabelaPainel.setPadding(new Insets(10, 10, 10, 10));
		tabelaPainel.setVgap(5);
		tabelaPainel.setHgap(5);

		// Adicionando elementos ao layout
		tabelaPainel.add(labelUsuario, 0, 1);
		tabelaPainel.add(cpf, 1, 1);
		tabelaPainel.add(labelSenha, 0, 2);
		tabelaPainel.add(senha, 1, 2); // Adicionando campo de senha
		tabelaPainel.add(btnLogin, 1, 3);

		getChildren().add(tabelaPainel);
	}

	private void entrar(TextField textFieldCpf, PasswordField passwordFieldsenha) {
		String cpf = textFieldCpf.getText();
		String senha = passwordFieldsenha.getText();
		Usuario usuario = telaPrincipal.service.recupera(cpf);
		if (usuario != null) {
			if (usuario.getSenha().equals(senha)) {
				telaPrincipal.setUsuarioLogado(usuario);
				telaPrincipal.getRoot().setCenter(new TelaDeEntrada(telaPrincipal));
			} else {
				
			}
		} else {
			
		}
	}

}