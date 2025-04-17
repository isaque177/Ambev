package org.senai.com.br.BancoDados.Tabelas;

import org.senai.com.br.BancoDados.Conexao;

import java.sql.*;

public class Tabel_Pedidos extends Conexao {

    public void Tabela_Pedidos() throws SQLException {

        String query = "SELECT * FROM Pedidos";


        try (Connection conexao = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(query);


            // Cabeçalho
            System.out.printf("                                     PEDIDOS                                      %n");
            System.out.printf("+------------+------------+----------------+----------+------------+-----------+-----------+%n");
            System.out.printf("| Cliente_ID | Produto_ID | Preço Unitário | Desconto | Data       | Valor     | Total     |%n");
            System.out.printf("+------------+------------+----------------+----------+------------+-----------+-----------+%n");

            // Linhas da tabela
            while (rs.next()) {
                int clienteId = rs.getInt("cliente_id");
                int produtoId = rs.getInt("produto_id");
                double precoUnitario = rs.getDouble("preco_unitario");
                double desconto = rs.getDouble("desconto");
                Date data = rs.getDate("datas");
                double valor = rs.getDouble("valor_total");
                double total = rs.getDouble("total");

                System.out.printf("| %-10d | %-10d | %-14.2f | %-8.2f | %-10s | %-9.2f | %-9.2f |%n",
                        clienteId, produtoId, precoUnitario, desconto, data.toString(), valor, total);
            }

            System.out.printf("+------------+------------+----------------+----------+------------+-----------+-----------+%n");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

