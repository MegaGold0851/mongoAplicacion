package com.megagold.mongoennube_vuno.Core;

import com.megagold.mongoennube_vuno.Model.SCP;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class InsertarScpCore implements Initializable {

    ConexionMongo conexionMongo = new ConexionMongo();
    List<SCP> listaDeSCP = new ArrayList<>();

    @FXML
    private ComboBox<String> cbxObjetoScp;

    @FXML
    private TableColumn<SCP, Integer> colIdentificador;

    @FXML
    private TableColumn<SCP, String> colNombre;

    @FXML
    private TableColumn<SCP, String> colProcedimientos;

    @FXML
    private TableColumn<SCP, String> colSCP;

    @FXML
    private TableView<SCP> tblScp;

    @FXML
    private TextArea txaProcedimiento;

    @FXML
    private TextField txtIdentificador;

    @FXML
    private TextField txtNombre;

    String idFilaSeleccionada = "";

    @FXML
    void eliminar(ActionEvent event) {
        String id = idFilaSeleccionada;

        conexionMongo.eliminarSCP(id);
        idFilaSeleccionada = "";
        limpiar();
        refrescarTabla();
    }

    @FXML
    void guardar(ActionEvent event) {
        String id = idFilaSeleccionada;
        String nombreSCP = txtNombre.getText();
        int identificador = Integer.parseInt(txtIdentificador.getText());
        String procedimientosDeContencion = txaProcedimiento.getText();
        String opcionSeleccionada = cbxObjetoScp.getValue();

        if(id.isBlank()){
            conexionMongo.insertarSCP(nombreSCP, identificador, procedimientosDeContencion, opcionSeleccionada);
        }
        if(!id.isBlank()){
            conexionMongo.actualizarSCP(id, nombreSCP, identificador, procedimientosDeContencion, opcionSeleccionada);
        }
        limpiar();
        refrescarTabla();
    }

    public void refrescarTabla(){
        listaDeSCP = conexionMongo.obtenerSCP();

        // Crea la PropertyValueFactory para cada columna
        colIdentificador.setCellValueFactory(new PropertyValueFactory<>("identificador"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreSCP"));
        colSCP.setCellValueFactory(new PropertyValueFactory<>("claseObjeto"));
        colProcedimientos.setCellValueFactory(new PropertyValueFactory<>("procedimientosDeContencion"));

        // Crea un ObservableList a partir de la listaDeSCP
        ObservableList<SCP> listaObservable = FXCollections.observableArrayList(listaDeSCP);

        // Establece el modelo de la tabla como el ObservableList
        tblScp.setItems(listaObservable);
    }

    public void limpiar(){
        txtNombre.setText("");
        txtIdentificador.setText("");
        txaProcedimiento.setText("");
        cbxObjetoScp.setValue("");
        idFilaSeleccionada = "";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblScp.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                SCP scpSeleccionado = tblScp.getSelectionModel().getSelectedItem();
                txtNombre.setText(scpSeleccionado.getNombreSCP());
                txtIdentificador.setText(String.valueOf(scpSeleccionado.getIdentificador()));
                cbxObjetoScp.setValue(scpSeleccionado.getClaseObjeto());
                txaProcedimiento.setText(scpSeleccionado.getProcedimientosDeContencion());
                idFilaSeleccionada = scpSeleccionado.getId();
            }
        });

        ObservableList<String> opciones = FXCollections.observableArrayList(
                "Object",
                "Euclid",
                "Keter",
                "Thaumiel",
                "Safe",
                "Explained",
                "SCP-001"
        );
        cbxObjetoScp.setItems(opciones);
        listaDeSCP = conexionMongo.obtenerSCP();

        refrescarTabla();
        txaProcedimiento.setWrapText(true);
    }
}
