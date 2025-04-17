package org.senai.com.br.BancoDados.Tabelas;

import org.senai.com.br.BancoDados.Conexao;

import java.sql.*;

public class Tabel_Transportadoras extends Conexao {

    public void Tabela_Transportadoras() throws SQLException {

        String query = "SELECT * FROM Transportadoras";

        try (Connection conexao = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.printf("                                                                                   %n");
            System.out.printf("                          TRANSPORTADORAS                                          %n");
            System.out.printf("+-------------------+-------------------------+----------------------+------------+%n");
            System.out.printf("| Transportadora_ID | Nome Transportadora     | Tipo de Veículo      | Capacidade |%n");
            System.out.printf("+-------------------+-------------------------+----------------------+------------+%n");

            while (rs.next()) {
                int id = rs.getInt("transportadora_id");
                String nome = rs.getString("nome_transportadora");
                String tipoVeiculo = rs.getString("tipo_veiculo");
                int capacidade = rs.getInt("capacidade");

                System.out.printf("| %-17d | %-23s | %-20s | %-10d |%n",id,nome,tipoVeiculo,capacidade);

            }

            System.out.printf("+-------------------+-------------------------+----------------------+------------+%n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
