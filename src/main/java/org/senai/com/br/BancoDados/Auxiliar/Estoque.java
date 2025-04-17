package org.senai.com.br.BancoDados.Auxiliar;

import org.senai.com.br.BancoDados.Conexao;

import java.sql.*;
import java.util.Scanner;

public class Estoque extends Conexao {
    private int estoqueId;
    private int produtoId;
    private int armazemId;
    private int quantidade;


    public Estoque() {
        this.estoqueId = estoqueId;
        this.produtoId = produtoId;
        this.armazemId = armazemId;
        this.quantidade = quantidade;
    }


    public void adicionarEstoqueViaConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do produto: ");
        int produtoId = scanner.nextInt();
        System.out.print("Digite o ID do armazém: ");
        int armazemId = scanner.nextInt();
        System.out.print("Digite a quantidade de produto no estoque: ");
        int quantidade = scanner.nextInt();

        String query = "INSERT INTO Estoque (produto_id, armazem_id, quantidade) VALUES (?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query)) {

            pstmt.setInt(1, produtoId);
            pstmt.setInt(2, armazemId);
            pstmt.setInt(3, quantidade);

            pstmt.executeUpdate();

            System.out.println("Estoque adicionado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar estoque: " + e.getMessage());
        }

        scanner.close();
    }

    // Método para remover estoque via console
    public void removerEstoqueViaConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do estoque que deseja remover: ");
        int estoqueId = scanner.nextInt();

        String query = "DELETE FROM Estoque WHERE estoque_id = ?";

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query)) {

            pstmt.setInt(1, estoqueId);

            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Estoque removido com sucesso!");
            } else {
                System.out.println("Nenhum estoque encontrado com esse ID.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao remover estoque: " + e.getMessage());
        }

        scanner.close();
    }

    // Método para atualizar estoque via console
    public void atualizarEstoqueViaConsole() {
        Scanner scanner = new Scanner(System.in);

        // Pergunta pelo ID do estoque
        System.out.print("Digite o ID do estoque que deseja atualizar: ");
        int estoqueId = scanner.nextInt();
        scanner.nextLine(); // limpar o buffer

        // Pergunta se o usuário quer alterar o ID do produto
        System.out.print("Deseja alterar o ID do produto? (s/n): ");
        Integer produtoId = null;
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o novo ID do produto: ");
            produtoId = scanner.nextInt();
            scanner.nextLine(); // limpar o buffer
        }

        // Pergunta se o usuário quer alterar o ID do armazém
        System.out.print("Deseja alterar o ID do armazém? (s/n): ");
        Integer armazemId = null;
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o novo ID do armazém: ");
            armazemId = scanner.nextInt();
            scanner.nextLine(); // limpar o buffer
        }

        // Pergunta se o usuário quer alterar a quantidade
        System.out.print("Deseja alterar a quantidade do produto? (s/n): ");
        Integer quantidade = null;
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite a nova quantidade: ");
            quantidade = scanner.nextInt();
            scanner.nextLine(); // limpar o buffer
        }

        // Construindo a consulta SQL dinamicamente
        StringBuilder query = new StringBuilder("UPDATE Estoque SET ");
        boolean adicionarVirgula = false;

        if (produtoId != null) {
            query.append("produtoId = ?");
            adicionarVirgula = true;
        }
        if (armazemId != null) {
            if (adicionarVirgula) query.append(", ");
            query.append("armazemId = ?");
            adicionarVirgula = true;
        }
        if (quantidade != null) {
            if (adicionarVirgula) query.append(", ");
            query.append("quantidade = ?");
        }

        // Caso nenhum campo tenha sido escolhido para atualização
        if (produtoId == null && armazemId == null && quantidade == null) {
            System.out.println("Nenhuma alteração foi feita.");
            return;
        }

        query.append(" WHERE estoqueId = ?");

        // Executando a consulta SQL
        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query.toString())) {

            int parametroIndex = 1;

            // Atribuindo os valores dos parâmetros na consulta SQL
            if (produtoId != null) {
                pstmt.setInt(parametroIndex++, produtoId);
            }
            if (armazemId != null) {
                pstmt.setInt(parametroIndex++, armazemId);
            }
            if (quantidade != null) {
                pstmt.setInt(parametroIndex++, quantidade);
            }

            // Definindo o ID do estoque
            pstmt.setInt(parametroIndex, estoqueId);

            // Executando a atualização no banco de dados
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Estoque atualizado com sucesso!");
            } else {
                System.out.println("Estoque não encontrado ou nada foi alterado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar estoque: " + e.getMessage());
        }

        scanner.close();
    }
}