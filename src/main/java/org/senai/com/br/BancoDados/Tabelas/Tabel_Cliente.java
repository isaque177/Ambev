package org.senai.com.br.BancoDados.Tabelas;

import org.senai.com.br.BancoDados.Conexao;

import java.sql.*;

public class Tabel_Cliente extends Conexao {

    public void Tabela_Cliente() throws SQLException {

        String query = "SELECT * FROM Clientes";


        try (Connection conexao = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(query);


            // Cabeçalho
            System.out.printf("                                                                         %n");
            System.out.printf("                               CLIENTE                                   %n");
            System.out.printf("+----+----------------------+--------------------------++---------------+%n");
            System.out.printf("| ID |             NOME                  |            EMAIL             |%n");
            System.out.printf("+----+----------------------+--------------------------++---------------+%n");

            // Linhas da tabela
            while (rs.next()) {
                int id = rs.getInt("cliente_id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                System.out.printf("| %-2d | %-33s | %-28s |%n", id, nome, email);
            }

            System.out.printf("+----+----------------------+--------------------------++----------------+%n");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

