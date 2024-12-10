package com.example.interfaz;

import java.sql.*;

public class CancionDAO {

    public void insertarCancion(String titulo, String autor) {
        String query = "INSERT INTO Cancion (titulo, autor) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, titulo);
            statement.setString(2, autor);
            int filasAfectadas = statement.executeUpdate();
            System.out.println(filasAfectadas + " fila(s) insertada(s).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void consultarCancion() {
        String query = "SELECT * FROM Cancion";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Titulo: " + resultSet.getString("titulo") +
                        ", Autor: " + resultSet.getString("autor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarCancion(int id, String nuevoTitulo, String nuevoAutor) {
        String query = "UPDATE Cancion SET titulo = ?, autor = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nuevoTitulo);
            statement.setString(2, nuevoAutor);
            statement.setInt(3, id);
            int filasAfectadas = statement.executeUpdate();
            System.out.println(filasAfectadas + " filas actualizadas.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarCancion(int id) {
        String query = "DELETE FROM Cancion WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int filasAfectadas = statement.executeUpdate();
            System.out.println(filasAfectadas + " fila(s) eliminada(s).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


