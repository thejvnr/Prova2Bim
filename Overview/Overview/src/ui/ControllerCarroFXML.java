package ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.hsqldb.lib.StringUtil;
import dao.CarroDAO;
import dao.MultaDAO;
import controller.controllerCarro;
import controller.controllerMulta;
import entidade.Carro;
import entidade.Multa;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;

public class ControllerCarroFXML implements Initializable{

    @FXML
    private TableColumn<Carro, String> colNome;

    @FXML
    private TableColumn<Carro, String> colDescr;

    @FXML
    private TextField tfRenavam;

    @FXML
    private TableView<Carro> tblCarro;

    @FXML
    private TableColumn<Carro, Integer> colRenavam;

    @FXML
    private Text id;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfNome;
    
    @FXML
    private TextField tfTipo;

    @FXML
    private TableColumn<?, ?> colTipo;

    @FXML
    private TableColumn<?, ?> colQuantidade;

    @FXML
    private TextField tfQuantidade;
    
    @FXML
	private Button alterar;
    
    @FXML
    private TableView<Multa> tblMultas;
    
    
    private Carro selectedItem;
    
    private Multa selectedMulta;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colDescr.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colRenavam.setCellValueFactory(new PropertyValueFactory<>("renavam"));
        //selectCol.setCellFactory(CheckBoxTableCell.forTableColumn(selectCol));
        colNome.setCellFactory(TextFieldTableCell.forTableColumn());
        tblCarro.setItems(listaDeCarros(new controllerCarro().listarTodos()));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tblMultas.setItems(listaDeMultas(new controllerMulta().listarMultas()));


        
		tblCarro.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Carro>() {
			@Override
			public void changed(ObservableValue<? extends Carro> observable, Carro oldValue, Carro newValue) {
				selectedItem = newValue;
				
				tfDescricao.setText(selectedItem.getDescricao());
				tfNome.setText(selectedItem.getNome());
				tfRenavam.setText(selectedItem.getRenavam() + "");
			}
		});
		
		tblMultas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Multa>() {
			@Override
			public void changed(ObservableValue<? extends Multa> observer, Multa oldMulta, Multa valorMulta) {
				selectedMulta = valorMulta;
				
				tfTipo.setText(selectedMulta.getTipo());
				tfQuantidade.setText(selectedMulta.getQuantidade() + "");
				}
			});
		
    }
    
	@FXML
	void alterar(ActionEvent event) {
		
		new CarroDAO().alterarCarro(new Carro(selectedItem.getId(), tfDescricao.getText(), tfNome.getText(), (Integer.parseInt(tfRenavam.getText()))));
		tfNome.clear();
		tfDescricao.clear();
		tfRenavam.clear();
	}

	@FXML
	void excluirMulta(ActionEvent event) {
			
		new MultaDAO().excluiMulta(selectedMulta);
		
	}
	
	@FXML
	void excluir(ActionEvent event) {
		
		new CarroDAO().excluiCarro(selectedItem);
		
	}

	@FXML
	void inserir(ActionEvent event) {
		
		Carro umCarro = new Carro();
		umCarro.setDescricao(tfDescricao.getText());
		umCarro.setNome(tfNome.getText());
		umCarro.setRenavam(Integer.parseInt(tfRenavam.getText()));
		new controllerCarro().inserir(umCarro);
        tblCarro.setItems(listaDeCarros(new controllerCarro().listarTodos()));
	}
	
	@FXML
	void inserirMulta(ActionEvent event) {
		
		Multa umaMulta= new Multa();
		umaMulta.setTipo(tfTipo.getText());
		umaMulta.setQuantidade(Integer.parseInt(tfQuantidade.getText()));
		new controllerMulta().inserirMulta(umaMulta);
        tblMultas.setItems(listaDeMultas(new controllerMulta().listarMultas()));
	}

	@FXML
	void pesquisar(ActionEvent event) {
		
		Integer renavam = null;

		if(StringUtil.isEmpty(tfRenavam.getText()) && tfRenavam.getText().matches("[0-9]+")) {
			renavam = Integer.parseInt(tfRenavam.getText());
		}
		
		ArrayList<Carro> listaDeCarros = new controllerCarro().pesquisarPorDescricao(tfNome.getText(), tfDescricao.getText(), renavam);
		
		tblCarro.setItems(listaDeCarros(listaDeCarros));
		
	}
	
	@FXML
	void pesquisarMulta(ActionEvent event) {
		
		Integer quantidade = null;

		if(StringUtil.isEmpty(tfQuantidade.getText()) && tfQuantidade.getText().matches("[0-9]+")) {
			quantidade = Integer.parseInt(tfQuantidade.getText());
		}
		
		ArrayList<Multa> listaDeMultas = new controllerMulta().pesquisarPorMulta(tfTipo.getText(), quantidade);
		
		tblMultas.setItems(listaDeMultas(listaDeMultas));
		
	}
	
    private ObservableList<Carro> listaDeCarros(ArrayList<Carro> listaDeCarros) {
    	
        return FXCollections.observableArrayList(listaDeCarros);
    }
    
    private ObservableList<Multa> listaDeMultas(ArrayList<Multa> listaDeMultas) {
    	
        return FXCollections.observableArrayList(listaDeMultas);
    }
}
















