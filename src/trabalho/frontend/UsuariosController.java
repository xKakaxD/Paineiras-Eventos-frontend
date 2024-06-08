package trabalho.frontend;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import trabalho.backend.BancoDeDados;
import trabalho.dominio.PersistenceService;
import trabalho.dominio.Usuario;

public class UsuariosController implements Initializable {

    private PersistenceService service = new BancoDeDados();

    @FXML
    private TableView<Usuario> tblUsuarios;
    @FXML
    private TableColumn<Usuario, String> clNome;
    @FXML
    private TableColumn<Usuario, String> clCpf;
    @FXML
    private TableColumn<Usuario, String> clEmail;
    @FXML
    private TableColumn<Usuario, String> clTelefone;
    @FXML
    private TableColumn<Usuario, String> clDataNascimento;
    @FXML
    private TableColumn<Usuario, String> clDataAniversario;
    @FXML
    private TableColumn<Usuario, String> clGenero;
    @FXML
    private TableColumn<Usuario, String> clTipoUsuario;

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtDataNascimento;
    @FXML
    private TextField txtDataAniversario;
    @FXML
    private ComboBox<String> cbGenero;
    @FXML
    private ComboBox<String> cbTipoUsuario;

    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btnApagar;
    @FXML
    private Button btnLimpar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraColunas();
        configuraBindings();
        atualizaDadosTabela();
        configuraComboBoxes();
    }

    public void salvar() {
        Usuario u = new Usuario();
        pegaValores(u);
        service.grava(u.hashCode() + "", u);
        atualizaDadosTabela();
    }

    public void atualizar() {
        Usuario usuarioSelecionado = tblUsuarios.getSelectionModel().getSelectedItem();
        if (usuarioSelecionado != null) {
            pegaValores(usuarioSelecionado);
            service.atualiza(usuarioSelecionado.hashCode() + "", usuarioSelecionado);
            atualizaDadosTabela();
        }
    }

    public void apagar() {
        Usuario usuarioSelecionado = tblUsuarios.getSelectionModel().getSelectedItem();
        if (usuarioSelecionado != null) {
            service.apagar(usuarioSelecionado.hashCode() + "", usuarioSelecionado);
            atualizaDadosTabela();
        }
    }

    public void limpar() {
        tblUsuarios.getSelectionModel().select(null);
        txtNome.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        txtDataNascimento.setText("");
        txtDataAniversario.setText("");
        cbGenero.getSelectionModel().clearSelection();
        cbTipoUsuario.getSelectionModel().clearSelection();
    }

    private void atualizaDadosTabela() {
        tblUsuarios.getItems().setAll(service.listarElementos(Usuario.class));
        limpar();
    }

   /* private void pegaValores(Usuario u) {
       u.setNome(txtNome.getText());
        u.setCpf(txtCpf.getText());
        u.setEmail(txtEmail.getText());
        u.setTelefone(txtTelefone.getText());
        u.setDataNascimento(txtDataNascimento.getText());
        u.setGenero(cbGenero.getValue().charAt(0));
        u.setTipoUsuario(cbTipoUsuario.getValue().charAt(0));
    }*/
    private void pegaValores(Usuario u) {
        u.setNome(txtNome.getText());
        u.setCpf(txtCpf.getText());
        u.setEmail(txtEmail.getText());
        u.setTelefone(txtTelefone.getText());
        u.setDataNascimento(txtDataNascimento.getText());
        
        // Converte a string selecionada nos ComboBoxes para caracteres antes de chamar os setters
        String generoSelecionado = cbGenero.getValue();
        if (generoSelecionado != null && !generoSelecionado.isEmpty()) {
            char generoChar;
            switch (generoSelecionado) {
                case "Masculino":
                    generoChar = '1';
                    break;
                case "Feminino":
                    generoChar = '2';
                    break;
                default:
                    generoChar = '0';
                    break;
            }
            u.setGenero(generoChar);
        }
        
        String tipoUsuarioSelecionado = cbTipoUsuario.getValue();
        if (tipoUsuarioSelecionado != null && !tipoUsuarioSelecionado.isEmpty()) {
            char tipoUsuarioChar;
            switch (tipoUsuarioSelecionado) {
                case "Administrador":
                    tipoUsuarioChar = '1';
                    break;
                case "Funcionário":
                    tipoUsuarioChar = '2';
                    break;
                case "Morador":
                    tipoUsuarioChar = '3';
                    break;
                default:
                    tipoUsuarioChar = '0';
                    break;
            }
            u.setTipoUsuario(tipoUsuarioChar);
        }
    }

    private void configuraColunas() {
        clNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        clEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        clDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        clDataAniversario.setCellValueFactory(new PropertyValueFactory<>("dataAniversario"));
        clGenero.setCellValueFactory(new PropertyValueFactory<>("generoString"));
        clTipoUsuario.setCellValueFactory(new PropertyValueFactory<>("tipoUsuarioString"));
    }

    private void configuraBindings() {
        BooleanBinding camposPreenchidos = txtNome.textProperty().isEmpty().or(txtCpf.textProperty().isEmpty())
                .or(txtEmail.textProperty().isEmpty())
                .or(txtTelefone.textProperty().isEmpty()).or(txtDataNascimento.textProperty().isEmpty())
                .or(cbGenero.getSelectionModel().selectedItemProperty().isNull())
                .or(cbTipoUsuario.getSelectionModel().selectedItemProperty().isNull());
        BooleanBinding algoSelecionado = tblUsuarios.getSelectionModel().selectedItemProperty().isNull();
        tblUsuarios.getSelectionModel().selectedItemProperty().addListener((b, o, n) -> {
            if (n != null) {
                txtNome.setText(n.getNome());
                txtCpf.setText(n.getCpf());
                txtEmail.setText(n.getEmail());
                txtTelefone.setText(n.getTelefone());
                txtDataNascimento.setText(n.getDataNascimento());
                txtDataAniversario.setText(n.getDataAniversario());
                cbGenero.setValue(n.getGeneroString());
                cbTipoUsuario.setValue(n.getTipoUsuarioString());
            } else {
                limpar();
            }
        });
        btnApagar.disableProperty().bind(algoSelecionado);
        btnAtualizar.disableProperty().bind(algoSelecionado);
        btnLimpar.disableProperty().bind(algoSelecionado);
        btnSalvar.disableProperty().bind(algoSelecionado.not().or(camposPreenchidos));
    }

    private void configuraComboBoxes() {
        cbGenero.getItems().addAll("Masculino", "Feminino", "Outros");
        cbTipoUsuario.getItems().addAll("Administrador", "Funcionário", "Morador", "Usuário bloqueado");
    }
    
    

}
