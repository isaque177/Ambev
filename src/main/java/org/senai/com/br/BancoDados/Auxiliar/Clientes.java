package org.senai.com.br.BancoDados.Auxiliar;

import org.senai.com.br.BancoDados.Conexao;

import java.sql.*;
import java.util.Scanner;

public class Clientes extends Conexao {
    private int id;
    private String nome;
    private String email;

    // Construtor
    public Clientes() {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // Método para adicionar cliente via console
    public void adicionarClienteViaConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o email do cliente: ");
        String email = scanner.nextLine();

        String query = "INSERT INTO Clientes (nome, email) VALUES (?, ?)";

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query)) {

            pstmt.setString(1, nome);
            pstmt.setString(2, email);

            pstmt.executeUpdate();

            System.out.println("Cliente adicionado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar cliente: " + e.getMessage());
        }

        scanner.close();
    }

    // Método para remover cliente via console
    public void removerClienteViaConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do cliente que deseja remover: ");
        int id = scanner.nextInt();

        String query = "DELETE FROM Clientes WHERE id = ?";

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query)) {

            pstmt.setInt(1, id);

            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Cliente removido com sucesso!");
            } else {
                System.out.println("Nenhum cliente encontrado com esse ID.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao remover cliente: " + e.getMessage());
        }

        scanner.close();
    }


    public void atualizarClienteViaConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do cliente que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consumir o newline

        System.out.print("Digite o novo nome do cliente: ");
        String novoNome = scanner.nextLine();

        System.out.print("Digite o novo email do cliente: ");
        String novoEmail = scanner.nextLine();

        String query = "UPDATE Clientes SET nome = ?, email = ? WHERE id = ?";

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query)) {

            pstmt.setString(1, novoNome);
            pstmt.setString(2, novoEmail);
            pstmt.setInt(3, id);

            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Cliente atualizado com sucesso!");
            } else {
                System.out.println("Nenhum cliente encontrado com esse ID.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
        }

        scanner.close();
    }
}
