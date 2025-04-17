package org.senai.com.br.BancoDados.Tabelas;
import org.senai.com.br.BancoDados.Conexao;

import java.sql.*;

public class Tabel_Vendas extends  Conexao{

    public void Tabela_Vendas () throws SQLException {

        String query = "SELECT * FROM Vendas";


        try (Connection conexao = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(query);



            // Cabeçalho
            System.out.printf("                               VENDAS                                  %n");
            System.out.printf("+----------+-----------+-------------+-------------+-------------+------------+------------------+-----------+--------------+------------+%n");
            System.out.printf("| Venda_ID | Pedido_ID | Vendedor_ID | Produto_ID  | Cliente_ID  | Quantidade | Preço_Unitário   | Desconto  | Valor_Total  | Data       |%n");
            System.out.printf("+----------+-----------+-------------+-------------+-------------+------------+------------------+-----------+--------------+------------+%n");

            // Linhas da tabela
            while (rs.next()) {
                int id = rs.getInt("venda_id");
                int pedidoId = rs.getInt("pedido_id");
                int vendedorId = rs.getInt("vendedor_id");
                int produtoId = rs.getInt("produto_id");
                int clienteId = rs.getInt("cliente_id");
                int quantidade = rs.getInt("quantidade");
                int precoUnitario = rs.getInt("preco_unitario");
                double desconto = rs.getDouble("desconto");
                int valorTotal = rs.getInt("valor_total");
                Date data = rs.getDate("datas");

                System.out.printf("| %-8d | %-9d | %-11d | %-11d | %-11d | %-10d | %-16d | %-9.2f | %-12d | %-10s |%n",
                        id, pedidoId, vendedorId, produtoId, clienteId,
                        quantidade, precoUnitario, desconto, valorTotal, data.toString());
            }

            System.out.printf("+----------+-----------+-------------+-------------+-------------+------------+------------------+-----------+--------------+------------+%n");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
