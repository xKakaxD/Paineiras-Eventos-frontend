package trabalho.frontend;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import trabalho.dominio.Organizacao;


public class TelaDeListagemDeOrganizacoes extends StackPane{
	TelaPrincipal telaPrincipal = null;
	public TelaDeListagemDeOrganizacoes(TelaPrincipal telaPrincipal) {
		List<Organizacao> organizacoes = telaPrincipal.service.listarElementos(Organizacao.class);
		
		TableView<Organizacao> tabela = new TableView<>();
		TableColumn<Organizacao, Integer> colunaId = new TableColumn<>("ID");
		TableColumn<Organizacao, Integer> colunaRazaoSocial = new TableColumn<>("Razão Social");
		TableColumn<Organizacao, Integer> colunaCnpj = new TableColumn<>("CNPJ");
		TableColumn<Organizacao, Integer> colunaEmail = new TableColumn<>("E-mail");
		TableColumn<Organizacao, Integer> colunaEndereco= new TableColumn<>("Endereço");
		
		colunaId.setCellValueFactory(new PropertyValueFactory<>("idOrganizacao"));
		colunaRazaoSocial.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
		colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colunaEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		
		 tabela.setItems(FXCollections.observableArrayList(organizacoes));
	     tabela.getColumns().addAll(colunaId,colunaRazaoSocial,colunaCnpj,colunaEmail,colunaEndereco);
	     getChildren().add(tabela);
		
	}
	
	
}
