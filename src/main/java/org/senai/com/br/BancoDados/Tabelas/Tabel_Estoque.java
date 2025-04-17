package org.senai.com.br.BancoDados.Tabelas;

import org.senai.com.br.BancoDados.Conexao;

import java.sql.*;

public class Tabel_Estoque extends Conexao {

    public void Tabela_Estoque() throws SQLException {

        String query = "SELECT * FROM Estoque";


        try (Connection conexao = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(query);



            System.out.printf("                                                        %n");
            System.out.printf("                      ESTOQUE                           %n");
            System.out.printf("+-------------+-------------+-------------+------------+%n");
            System.out.printf("| Estoque_ID  | Armazem_ID  | Produto_ID  | Quantidade |%n");
            System.out.printf("+-------------+-------------+-------------+------------+%n");

            while (rs.next()) {
                int estoqueId = rs.getInt("estoque_id");
                int armazemId = rs.getInt("armazem_id");
                int produtoId = rs.getInt("produto_id");
                int quantidade = rs.getInt("quantidade");

                System.out.printf("| %-11d | %-11d | %-11d | %-10d |%n",
                        estoqueId, armazemId, produtoId, quantidade);
            }

            System.out.printf("+-------------+-------------+-------------+------------+%n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
