package org.senai.com.br.BancoDados.Auxiliar;

import org.senai.com.br.BancoDados.Conexao;

import java.sql.*;
import java.util.Scanner;

public class Produtos extends Conexao {
    private int produto_id;
    private String nome_produto;
    private Double preco;
    private String categoria;

    public Produtos() {
        this.produto_id = produto_id;
        this.nome_produto = nome_produto;
        this.preco = preco;
        this.categoria = categoria;
    }

    public void adicionarProdutoViaConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do produto: ");
        String nome_produto = scanner.nextLine();

        System.out.print("Digite o preço: ");
        Double preco = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Digite a categoria: ");
        String categoria = scanner.nextLine();

        String query = "INSERT INTO Produtos (nome_produto, preco, categoria) VALUES (?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query)) {

            pstmt.setString(1, nome_produto);
            pstmt.setDouble(2, preco);
            pstmt.setString(3, categoria);

            pstmt.executeUpdate();

            System.out.println("Produto adicionado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
        }

        scanner.close();
    }

    public void RemoverProdutoViaConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Qual ID do produto que você deseja remover?: ");
        int produto_id = scanner.nextInt();

        String query = "DELETE FROM Produtos WHERE produto_id = ?"; // <-- Verifique esse nome no banco!

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query)) {

            pstmt.setInt(1, produto_id);

            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Produto removido com sucesso!");
            } else {
                System.out.println("Nenhum produto encontrado com esse ID.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao remover produto: " + e.getMessage());
        }
    }
    public void upatualizarProdutoViaConsole() {
        Scanner scanner = new Scanner(System.in);

        // Pergunta pelo ID do produto
        System.out.print("Digite o ID do produto que deseja atualizar: ");
        int produto_id = scanner.nextInt();
        scanner.nextLine(); // limpar o buffer

        // Pergunta se o usuário quer alterar o nome
        System.out.print("Deseja alterar o nome do produto? (s/n): ");
        String nome_produto = null;
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o novo nome: ");
            nome_produto = scanner.nextLine();
        }

        // Pergunta se o usuário quer alterar o preço
        System.out.print("Deseja alterar o preço do produto? (s/n): ");
        Double preco = null;
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o novo preço: ");
            preco = scanner.nextDouble();
            scanner.nextLine(); // limpar o buffer
        }

        // Pergunta se o usuário quer alterar a categoria
        System.out.print("Deseja alterar a categoria do produto? (s/n): ");
        String categoria = null;
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite a nova categoria: ");
            categoria = scanner.nextLine();
        }

        // Construindo a consulta SQL dinamicamente
        StringBuilder query = new StringBuilder("UPDATE Produtos SET ");
        boolean adicionarVirgula = false;

        if (nome_produto != null) {
            query.append("nome_produto = ?");
            adicionarVirgula = true;
        }
        if (preco != null) {
            if (adicionarVirgula) query.append(", ");
            query.append("preco = ?");
            adicionarVirgula = true;
        }
        if (categoria != null) {
            if (adicionarVirgula) query.append(", ");
            query.append("categoria = ?");
        }

        // Caso nenhum campo tenha sido escolhido para atualização
        if (nome_produto == null && preco == null && categoria == null) {
            System.out.println("Nenhuma alteração foi feita.");
            return;
        }

        query.append(" WHERE produto_id = ?");

        // Executando a consulta SQL
        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query.toString())) {

            int parametroIndex = 1;

            // Atribuindo os valores dos parâmetros na consulta SQL
            if (nome_produto != null) {
                pstmt.setString(parametroIndex++, nome_produto);
            }
            if (preco != null) {
                pstmt.setDouble(parametroIndex++, preco);
            }
            if (categoria != null) {
                pstmt.setString(parametroIndex++, categoria);
            }

            // Definindo o ID do produto
            pstmt.setInt(parametroIndex, produto_id);

            // Executando a atualização no banco de dados
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Produto atualizado com sucesso!");
            } else {
                System.out.println("Produto não encontrado ou nada foi alterado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
        }

        scanner.close();
    }
}

   /* package org.senai.com.br.BancoDados.Auxiliar;

import org.senai.com.br.BancoDados.Conexao;

import java.sql.*;
        import java.util.Scanner;

public class Produtos extends Conexao {
    private int produto_id;
    private String nome_produto;
    private Double preco;
    private String categoria;

    // Método de atualização dos produtos com base na campanha e no desconto
    public void atualizarProdutosPorCampanha() {
        Scanner scanner = new Scanner(System.in);

        // Perguntar ao usuário qual campanha deseja aplicar
        System.out.print("Digite o ID da campanha que deseja aplicar o desconto: ");
        int campanha_id = scanner.nextInt();

        String query = "SELECT p.produto_id, p.preco, pr.desconto " +
                "FROM Produtos p " +
                "JOIN Promocoes pr ON p.promocao_id = pr.promocao_id " +
                "WHERE p.campanha_id = ?";

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query)) {

            pstmt.setInt(1, campanha_id);
            ResultSet rs = pstmt.executeQuery();

            // Se houver produtos relacionados à campanha
            if (rs.next()) {
                // Consulta para atualizar os produtos com o novo valor_total
                String updateSQL = "UPDATE Produtos SET valor_total = ? WHERE produto_id = ?";
                try (PreparedStatement updateStmt = conexao.prepareStatement(updateSQL)) {

                    do {
                        int produto_id = rs.getInt("produto_id");
                        double preco = rs.getDouble("preco");
                        double desconto = rs.getDouble("desconto");

                        // Calcula o valor total com o desconto
                        double valorTotal = preco - (preco * desconto / 100);

                        // Atualiza o valor_total no banco
                        updateStmt.setDouble(1, valorTotal);
                        updateStmt.setInt(2, produto_id);
                        updateStmt.executeUpdate();
                    } while (rs.next());

                    System.out.println("Produtos atualizados com sucesso com base na campanha!");

                }

            } else {
                System.out.println("Nenhum produto encontrado para esta campanha.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produtos: " + e.getMessage());
        }

        scanner.close();
    }
}*/