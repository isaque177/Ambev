package org.senai.com.br.BancoDados.Tabelas;

import org.senai.com.br.BancoDados.Conexao;

import java.sql.*;

public class Tabel_Entregas extends Conexao {

    public void Tabela_Entregas() throws SQLException {

        String query = "SELECT * FROM Entregas";


        try (Connection conexao = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(query);


            System.out.printf("                                                           %n");
            System.out.printf("                       ENTREGAS                            %n");
            System.out.printf("+-------------+------------+--------------------+------------+----------------------------+%n");
            System.out.printf("| Entrega_ID  | Pedido_ID  | Transportadora_ID  | Data       | Status de Entrega          |%n");
            System.out.printf("+-------------+------------+--------------------+------------+----------------------------+%n");

            while (rs.next()) {
                int entregaId = rs.getInt("entrega_id");
                int pedidoId = rs.getInt("pedido_id");
                int transportadoraId = rs.getInt("transportadora_id");
                Date dataEntrega = rs.getDate("data_entrega");
                String status = rs.getString("status_entrega");

                System.out.printf("| %-11d | %-10d | %-18d | %-10s | %-26s |%n",
                        entregaId, pedidoId, transportadoraId, dataEntrega.toString(), status);
            }

            System.out.printf("+-------------+------------+--------------------+------------+----------------------------+%n");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
