package org.senai.com.br.BancoDados.Auxiliar;

import org.senai.com.br.BancoDados.Conexao;
import org.senai.com.br.BancoDados.Status.Status_Entrega;

import java.sql.*;
import java.util.Scanner;

public class Entregas extends Conexao {
    private int entrega_Id;
    private int pedidoId;
    private int transportadoraId;
    private Date dataEntrega;
    private Status_Entrega status;

    public Entregas() {
        this.entrega_Id = entrega_Id;
        this.pedidoId = pedidoId;
        this.transportadoraId = transportadoraId;
        this.dataEntrega = dataEntrega;
        this.status = Status_Entrega.PENDENTE;
    }

    public void rastrearEntrega() {

        Scanner scanner = new Scanner(System.in);
        boolean idValido = false; // Flag para verificar se o ID é válido

        // Pergunta pelo ID da entrega até o usuário fornecer um ID válido
        while (!idValido) {
            System.out.print("Digite o ID da entrega que deseja rastrear: ");
            int entrega_id = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            // Verificar se o ID existe no banco de dados
            String query = "SELECT entrega_id, status_entrega FROM Entregas WHERE entrega_id = ?";
            try (Connection conexao = DriverManager.getConnection(url, user, password);
                 PreparedStatement pstmt = conexao.prepareStatement(query)) {
                pstmt.setInt(1, entrega_id);
                ResultSet rs = pstmt.executeQuery();

                // Verificar se o ID foi encontrado no banco
                if (rs.next()) {
                    idValido = true; // ID válido encontrado
                    this.entrega_Id = entrega_id; // Armazenar o ID
                    String statusAtual = rs.getString("status_entrega"); // Obter o status da entrega
                    System.out.println("Status atual da entrega ID " + entrega_Id + ": " + statusAtual);
                } else {
                    System.out.println("ID inválido! Tente novamente.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}