package trabalho.frontend;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class TelaDeEntrada extends StackPane {
	private TelaPrincipal telaPrincipal;
	public TelaDeEntrada(TelaPrincipal principal) {
		this.telaPrincipal = principal;
		// Criando elementos da tela de login
		Label labelNome = new Label("Usuário(nome):");
		Label nome = new Label(telaPrincipal.getUsuarioLogado().getNome());
		Label labelUsuario = new Label("Usuário(cpf):");
		Label cpf = new Label(telaPrincipal.getUsuarioLogado().getCpf());
		Button btnLogin = new Button("Login");
		// Criando layout da tela de login
		GridPane tabelaPainel = new GridPane();
		tabelaPainel.setPadding(new Insets(10, 10, 10, 10));
		tabelaPainel.setVgap(5);
		tabelaPainel.setHgap(5);

		// Adicionando elementos ao layout
		tabelaPainel.add(labelNome, 0, 2);
		tabelaPainel.add(nome, 1, 2); // Adicionando campo de nome
		tabelaPainel.add(labelUsuario, 0, 1);
		tabelaPainel.add(cpf, 1, 1);
		tabelaPainel.add(btnLogin, 1, 3);

		getChildren().add(tabelaPainel);
	}
}
