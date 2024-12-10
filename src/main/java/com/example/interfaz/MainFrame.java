package com.example.interfaz;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static com.example.interfaz.CancionDAO.*;

public class MainFrame extends Application {

    private TableView<Cancion> table;
    private ObservableList<Cancion> data;

    @Override
    public void start(Stage primaryStage) {
        data = FXCollections.observableArrayList();
        // Crear la tabla
        table = new TableView<>();
        table.setEditable(true);
        // Crear columnas de la tabla
        TableColumn<Cancion, String> tituloColumn = new TableColumn<>("Título");
        tituloColumn.setCellValueFactory(cellData -> cellData.getValue().tituloProperty());

        TableColumn<Cancion, String> autorColumn = new TableColumn<>("Autor");
        autorColumn.setCellValueFactory(cellData -> cellData.getValue().autorProperty());

        table.getColumns().addAll(tituloColumn, autorColumn);



        //BOTONES CRUD
        Button btnAgregar = new Button("Agregar");
        Button btnEditar = new Button("Editar");
        Button btnEliminar = new Button("Eliminar");

        // Asignar acciones a los botones
        btnAgregar.setOnAction(e -> mostrarFormulario(null));
        btnEditar.setOnAction(e -> editarCancion());
        btnEliminar.setOnAction(e -> eliminarCancion());

        // Layout
        BorderPane root = new BorderPane();
        root.setCenter(table);
        root.setTop(createToolBar(btnAgregar, btnEditar, btnEliminar));


        // Mostrar la escena
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Gestión de Canciones");
        primaryStage.setScene(scene);
        primaryStage.show();

        }



    private ToolBar createToolBar(Button... buttons) {
        ToolBar toolBar = new ToolBar();
        toolBar.getItems().addAll(buttons);
        return toolBar;
    }
    private void mostrarFormulario(Cancion cancion) {
        TextInputDialog dialog = new TextInputDialog(cancion == null ? "" : cancion.getTitulo());
        dialog.setTitle("Agregar/Editar Canción");
        dialog.setHeaderText("Ingrese el título de la canción");

        dialog.showAndWait().ifPresent(titulo -> {
            String autor = "Autor Desconocido";  // Puedes capturar el autor también

            CancionDAO cancionDAO = new CancionDAO();

            if (cancion == null) {
                // Llamar a la base de datos para agregar
                cancionDAO.insertarCancion(titulo, autor);  // Llamada al método
                data.add(new Cancion(titulo, autor));
            } else {
                cancion.setTitulo(titulo);  // Modificar canción
                cancionDAO.actualizarCancion(cancion.getId(), titulo, autor);  // Llamada al método
                table.refresh();
            }
        });
    }
    private void editarCancion() {
        Cancion selectedCancion = table.getSelectionModel().getSelectedItem();
        if (selectedCancion != null) {
            mostrarFormulario(selectedCancion);
        } else {
            mostrarMensaje("Seleccione una canción para editar");
        }
    }
    private void eliminarCancion() {
        Cancion selectedCancion = table.getSelectionModel().getSelectedItem();
        if (selectedCancion != null) {
            data.remove(selectedCancion);  // Eliminar canción
        } else {
            mostrarMensaje("Seleccione una canción para eliminar");
        }
    }
    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    public static void main(String[] args) {
        launch(args);
    }
}