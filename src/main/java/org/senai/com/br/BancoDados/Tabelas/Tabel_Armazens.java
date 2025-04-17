package org.senai.com.br.BancoDados.Tabelas;

import org.senai.com.br.BancoDados.Conexao;

import java.sql.*;

public class Tabel_Armazens extends Conexao {

    public void Tabela_Armazens() throws SQLException {
        String query = "SELECT * FROM armazens";


        try (Connection conexao = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.printf("                                                                                     %n");
            System.out.printf("                                 ARMAZÉNS                                            %n");
            System.out.printf("+-------------+----------------------------+----------------------------+------------+%n");
            System.out.printf("| Armazem_ID  | Nome do Armazém            | Localização                | Capacidade |%n");
            System.out.printf("+-------------+----------------------------+----------------------------+------------+%n");

            while (rs.next()) {
                int id = rs.getInt("armazem_id");
                String nome = rs.getString("nome_armazem");
                String localizacao = rs.getString("localizacao");
                int capacidade = rs.getInt("capacidade");

                System.out.printf("| %-11d | %-26s | %-26s | %-10d |%n",id,nome,localizacao,capacidade);
            }

            System.out.printf("+-------------+----------------------------+----------------------------+------------+%n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
