package org.senai.com.br.BancoDados.Tabelas;

import org.senai.com.br.BancoDados.Conexao;

import java.sql.*;

public class Tabel_MovimentacaoEstoque extends Conexao {

    public void Tabela_movimetancaoestoque() throws SQLException {

        String query = "SELECT * FROM MovimentacaoEstoque";


        try (Connection conexao = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(query);



            System.out.printf("                                                                                  %n");
            System.out.printf("              MOVIMENTAÇÃO DE ESTOQUE                                             %n");
            System.out.printf("+----------------+-------------+----------------------+------------+-------------+%n");
            System.out.printf("| MovimentacaoID | Estoque_ID  | Tipo Movimentação     | Data       | Quantidade  |%n");
            System.out.printf("+----------------+-------------+----------------------+------------+-------------+%n");

            while (rs.next()) {
                int movimentacaoId = rs.getInt("movimentacao_id");
                int estoqueId = rs.getInt("estoque_id");
                String tipo = rs.getString("tipo_movimentacao");
                Date data = rs.getDate("data_movimentacao");
                int quantidade = rs.getInt("quantidade");

                System.out.printf("| %-14d | %-11d | %-20s | %-10s | %-11d |%n",
                        movimentacaoId, estoqueId, tipo, data.toString(), quantidade);
            }

            System.out.printf("+----------------+-------------+----------------------+------------+-------------+%n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
