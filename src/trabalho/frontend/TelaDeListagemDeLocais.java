package trabalho.frontend;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import trabalho.dominio.Local;


public class TelaDeListagemDeLocais extends StackPane{
	TelaPrincipal telaPrincipal = null;
	public TelaDeListagemDeLocais(TelaPrincipal telaPrincipal) {
		List<Local> locais = telaPrincipal.service.listarElementos(Local.class);
		
		TableView<Local> tabela = new TableView<>();
		TableColumn<Local, Integer> colunaId = new TableColumn<>("ID");
		TableColumn<Local, String> colunaNome = new TableColumn<>("Nome");
		TableColumn<Local, String> colunaTipo = new TableColumn<>("Tipo");
		TableColumn<Local, Integer> colunaLotacao = new TableColumn<>("Lotação Máxima");
		TableColumn<Local, String> colunaLogradouro = new TableColumn<>("Logradouro");
		TableColumn<Local, String> colunaEstrutura = new TableColumn<>("Estrutura do Local");
		TableColumn<Local, String> colunaDescricao = new TableColumn<>("Descrição");
		
		
		colunaId.setCellValueFactory(new PropertyValueFactory<>("idLocal"));
		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		colunaLotacao.setCellValueFactory(new PropertyValueFactory<>("lotacaoMax"));
		colunaLogradouro.setCellValueFactory(new PropertyValueFactory<>("logradouro"));
		colunaEstrutura.setCellValueFactory(new PropertyValueFactory<>("estrutura"));
		colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		
		//javafx
		 tabela.setItems(FXCollections.observableArrayList(locais));
	     tabela.getColumns().addAll(colunaId,colunaNome,colunaTipo,colunaLotacao,colunaLogradouro,colunaEstrutura,colunaDescricao);
	     getChildren().add(tabela);
	}

}
