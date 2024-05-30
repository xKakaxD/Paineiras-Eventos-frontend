package trabalho.frontend;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import trabalho.backend.BancoDeDados;
import trabalho.dominio.Conta;
import trabalho.dominio.PersistenceService;

/**
 * 
 * O controller da aplicação, onde a mágica acontece
 *
 */
public class ContasController implements Initializable {

	private PersistenceService service = new BancoDeDados();

	@FXML
	private TableView<Conta> tblContas;
	@FXML
	private TableColumn<Conta, String> clCred;
	@FXML
	private TableColumn<Conta, String> clDesc;
	@FXML
	private TableColumn<Conta, Date> clVenc;
	@FXML
	private TextField txtConsc;
	@FXML
	private TextField txtDesc;
	@FXML
	private DatePicker dpVencimento;
	@FXML
	private Button btnSalvar;
	@FXML
	private Button btnAtualizar;
	@FXML
	private Button btnApagar;
	@FXML
	private Button btnLimpart;

//	 Esse método é chamado ao inicializar a aplicação, igual um construtor. Ele vem da interface Initializable
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configuraColunas();
		configuraBindings();
		atualizaDadosTabela();
	}

	// métodos públicos chamados quando o botão é clicado

	public void salvar() {
	    Conta c = new Conta();   
	    pegaValores(c);   // Preenche a nova instância com os valores dos campos da tela
	    service.grava(c.hashCode() + "", c);     // Salva a nova conta utilizando o serviço de persistência
	    atualizaDadosTabela();     // Atualiza os dados na tabela para refletir a adição da nova conta
	}


	public void atualizar() {
		Conta contaSelecionada = tblContas.getSelectionModel().getSelectedItem();   // Obtém a conta atualmente selecionada na tabela
	    if (contaSelecionada != null) {
	        pegaValores(contaSelecionada); // Pega os valores dos campos da tela e atualiza a conta selecionada
	        service.grava(contaSelecionada.hashCode() + "", contaSelecionada); // Salva a conta atualizada
	        atualizaDadosTabela(); // Atualiza a tabela para refletir as mudanças
	    }
	}

	public void apagar() {
		Conta contaSelecionada = tblContas.getSelectionModel().getSelectedItem();
	    if (contaSelecionada != null) {
	        service.apagar(contaSelecionada.hashCode() + "", contaSelecionada); // Remove a conta do serviço de persistência
	        atualizaDadosTabela(); // Atualiza a tabela para refletir as mudanças
	    }
	}

	public void limpar() {
		tblContas.getSelectionModel().select(null);
		txtConsc.setText("");
		txtDesc.setText("");
		dpVencimento.setValue(null);
	}

	// métodos privados do controller

	// método utilitário para pega a data que foi selecionada (que usa o tipo novo
	// do java 8 LocalDateTime)
	private Date dataSelecionada() {
		LocalDateTime time = dpVencimento.getValue().atStartOfDay();
		return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
	}

	// chamado quando acontece alguma operação de atualização dos dados
	// chamado quando acontece alguma operação de atualização dos dados
	private void atualizaDadosTabela() {
		tblContas.getItems().setAll(service.listarElementos(Conta.class));
		limpar();
	}

	// pega os valores entrados pelo usuário e adiciona no objeto conta
	private void pegaValores(Conta c) {
		c.setCredor(txtConsc.getText());
		c.setDescricao(txtDesc.getText());
		c.setDataVencimento(dataSelecionada());
	}

	// configura as colunas para mostrar as propriedades da classe Conta
	private void configuraColunas() {
		clCred.setCellValueFactory(new PropertyValueFactory<>("credor"));
		clDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		clVenc.setCellValueFactory(new PropertyValueFactory<>("dataVencimento"));
	}

	// configura a lógica da tela
	private void configuraBindings() {
		// esse binding só e false quando os campos da tela estão preenchidos
		BooleanBinding camposPreenchidos = txtConsc.textProperty().isEmpty().or(txtDesc.textProperty().isEmpty())
				.or(dpVencimento.valueProperty().isNull());
		// indica se há algo selecionado na tabela
		BooleanBinding algoSelecionado = tblContas.getSelectionModel().selectedItemProperty().isNull();	
		// quando algo é selecionado na tabela, preenchemos os campos de entrada com os valores para o usuário editar
	    tblContas.getSelectionModel().selectedItemProperty().addListener((b, o, n) -> {
	        if (n != null) {
	            // Preenche os campos da tela com os valores da conta selecionada
	            txtConsc.setText(n.getCredor());
	            txtDesc.setText(n.getDescricao());
	            if (n.getDataVencimento() != null) {
	                dpVencimento.setValue(n.getDataVencimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	            } else {
	                dpVencimento.setValue(null);
	            }
	        } else {
	            // Limpa os campos se nada estiver selecionado
	            limpar();
	        }
	    });
		// alguns botões só são habilitados se algo foi selecionado na tabela
		btnApagar.disableProperty().bind(algoSelecionado);
		btnAtualizar.disableProperty().bind(algoSelecionado);
		btnLimpart.disableProperty().bind(algoSelecionado);
		// o botão salvar só é habilitado se as informações foram preenchidas e não tem
		// nada na tela
		btnSalvar.disableProperty().bind(algoSelecionado.not().or(camposPreenchidos));
		// quando algo é selecionado na tabela, preenchemos os campos de entrada com os
		// valores para o
		// usuário editar
		tblContas.getSelectionModel().selectedItemProperty().addListener((b, o, n) -> {
			if (n != null) {
				LocalDate data = null;
			}
		});
	}

}