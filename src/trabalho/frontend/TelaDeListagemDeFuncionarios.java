package trabalho.frontend;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import trabalho.dominio.Usuario;

public class TelaDeListagemDeFuncionarios extends StackPane{
	TelaPrincipal telaPrincipal = null;
	public TelaDeListagemDeFuncionarios(TelaPrincipal telaPrincipal) {
		List<Usuario> pessoas = telaPrincipal.service.listarElementos(Usuario.class);

        TableView<Usuario> tabela = new TableView<>();
        TableColumn<Usuario, Integer> colunaId = new TableColumn<>("ID");
        TableColumn<Usuario, String> colunaNome = new TableColumn<>("Nome");
        TableColumn<Usuario, String> colunaCpf = new TableColumn<>("Cpf");
        TableColumn<Usuario, String> colunaEmail = new TableColumn<>("E-mail");
        TableColumn<Usuario, String> colunaEndereco = new TableColumn<>("Endereço");
        TableColumn<Usuario, String> colunaAniversario = new TableColumn<>("Aniversário");
        TableColumn<Usuario, String> colunaGenero = new TableColumn<>("Gênero");
        
        colunaId.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colunaAniversario.setCellValueFactory(new PropertyValueFactory<>("dataAniversario"));
        colunaGenero.setCellValueFactory(new PropertyValueFactory<>("generoString"));

        tabela.setItems(FXCollections.observableArrayList(pessoas));
        tabela.getColumns().addAll(colunaId,colunaNome, colunaCpf, colunaEmail, colunaEndereco, colunaAniversario, colunaGenero);
        getChildren().add(tabela);
	
	}
	
}