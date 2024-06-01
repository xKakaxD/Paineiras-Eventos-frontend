package trabalho.frontend;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import trabalho.dominio.Usuario;

public class TelaDeListagemDeUsuarios extends StackPane{
	TelaPrincipal telaPrincipal = null;
	public TelaDeListagemDeUsuarios(TelaPrincipal telaPrincipal) {
		List<Usuario> pessoas = telaPrincipal.service.listarElementos(Usuario.class);

        TableView<Usuario> tabela = new TableView<>();//TODO: fazer um para funcionario
        TableColumn<Usuario, Integer> colunaId = new TableColumn<>("ID");
        TableColumn<Usuario, String> colunaNome = new TableColumn<>("Nome");
        TableColumn<Usuario, String> colunaCpf = new TableColumn<>("Cpf");
        TableColumn<Usuario, String> colunaEmail = new TableColumn<>("E-mail");
        TableColumn<Usuario, String> colunaAniversario = new TableColumn<>("Aniversário");
        TableColumn<Usuario, String> colunaGenero = new TableColumn<>("Gênero");
        TableColumn<Usuario, String> colunaTipoUsuario = new TableColumn<>("Tipo do Usuário");
        
        colunaId.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaAniversario.setCellValueFactory(new PropertyValueFactory<>("dataAniversario"));
        colunaGenero.setCellValueFactory(new PropertyValueFactory<>("generoString"));
        colunaTipoUsuario.setCellValueFactory(new PropertyValueFactory<>("tipoUsuarioString"));

        tabela.setItems(FXCollections.observableArrayList(pessoas));
        tabela.getColumns().addAll(colunaId,colunaNome, colunaCpf, colunaEmail, colunaAniversario, colunaGenero, colunaTipoUsuario);
        getChildren().add(tabela);
	
	}
	
}