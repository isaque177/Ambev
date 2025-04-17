package org.senai.com.br.BancoDados.Auxiliar;

import org.senai.com.br.BancoDados.Conexao;

import java.sql.*;
import java.util.Scanner;

public class Pedidos extends Conexao {
    private int clienteId;
    private int produtoId;
    private double precoUnitario;
    private double desconto;
    private double valorTotal;
    private Date datas;
    private double total;

    public Pedidos() {
        this.clienteId = clienteId;
        this.produtoId = produtoId;
        this.precoUnitario = precoUnitario;
        this.desconto = desconto;
        this.valorTotal = valorTotal;
        this.datas = datas;
        this.total = total;
    }
    public void adicionarPedidoViaConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do cliente: ");
        int clienteId = scanner.nextInt();

        System.out.print("Digite o ID do produto: ");
        int produtoId = scanner.nextInt();

        System.out.print("Digite o preço unitário: ");
        double precoUnitario = scanner.nextDouble();

        System.out.print("Digite o desconto: ");
        double desconto = scanner.nextDouble();

        System.out.print("Digite o valor total: ");
        double valorTotal = scanner.nextDouble();

        System.out.print("Digite a data (AAAA-MM-DD): ");
        String dataStr = scanner.next();
        Date datas = Date.valueOf(dataStr);

        System.out.print("Digite o total final: ");
        double total = scanner.nextDouble();

        String query = "INSERT INTO Pedidos (clienteId, produtoId, precoUnitario, desconto, valorTotal, datas, total) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query)) {

            pstmt.setInt(1, clienteId);
            pstmt.setInt(2, produtoId);
            pstmt.setDouble(3, precoUnitario);
            pstmt.setDouble(4, desconto);
            pstmt.setDouble(5, valorTotal);
            pstmt.setDate(6, datas);
            pstmt.setDouble(7, total);

            pstmt.executeUpdate();
            System.out.println("Pedido adicionado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar pedido: " + e.getMessage());
        }

        scanner.close();
    }

    public void removerPedidoViaConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do cliente para remover o pedido: ");
        int clienteId = scanner.nextInt();

        String query = "DELETE FROM Pedidos WHERE clienteId = ?";

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query)) {

            pstmt.setInt(1, clienteId);

            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Pedido removido com sucesso!");
            } else {
                System.out.println("Nenhum pedido encontrado com esse ID.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao remover pedido: " + e.getMessage());
        }
    }

    public void atualizarPedidoViaConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do cliente do pedido a ser atualizado: ");
        int clienteId = scanner.nextInt();
        scanner.nextLine(); // limpa o buffer

        Double novoPrecoUnitario = null;
        Double novoDesconto = null;
        Double novoValorTotal = null;
        Double novoTotal = null;
        Integer novoProdutoId = null;
        Date novaData = null;

        System.out.print("Deseja alterar o ID do produto? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o novo ID do produto: ");
            novoProdutoId = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.print("Deseja alterar o preço unitário? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o novo preço unitário: ");
            novoPrecoUnitario = scanner.nextDouble();
            scanner.nextLine();
        }

        System.out.print("Deseja alterar o desconto? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o novo desconto: ");
            novoDesconto = scanner.nextDouble();
            scanner.nextLine();
        }

        System.out.print("Deseja alterar o valor total? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o novo valor total: ");
            novoValorTotal = scanner.nextDouble();
            scanner.nextLine();
        }

        System.out.print("Deseja alterar a data? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite a nova data (AAAA-MM-DD): ");
            novaData = Date.valueOf(scanner.nextLine());
        }

        System.out.print("Deseja alterar o total final? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o novo total: ");
            novoTotal = scanner.nextDouble();
            scanner.nextLine();
        }

        if (novoProdutoId == null && novoPrecoUnitario == null && novoDesconto == null &&
                novoValorTotal == null && novaData == null && novoTotal == null) {
            System.out.println("Nenhuma alteração foi feita.");
            return;
        }

        StringBuilder query = new StringBuilder("UPDATE Pedidos SET ");
        boolean adicionarVirgula = false;

        if (novoProdutoId != null) {
            query.append("produtoId = ?");
            adicionarVirgula = true;
        }
        if (novoPrecoUnitario != null) {
            if (adicionarVirgula) query.append(", ");
            query.append("precoUnitario = ?");
            adicionarVirgula = true;
        }
        if (novoDesconto != null) {
            if (adicionarVirgula) query.append(", ");
            query.append("desconto = ?");
            adicionarVirgula = true;
        }
        if (novoValorTotal != null) {
            if (adicionarVirgula) query.append(", ");
            query.append("valorTotal = ?");
            adicionarVirgula = true;
        }
        if (novaData != null) {
            if (adicionarVirgula) query.append(", ");
            query.append("datas = ?");
            adicionarVirgula = true;
        }
        if (novoTotal != null) {
            if (adicionarVirgula) query.append(", ");
            query.append("total = ?");
        }

        query.append(" WHERE clienteId = ?");

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query.toString())) {

            int index = 1;

            if (novoProdutoId != null) pstmt.setInt(index++, novoProdutoId);
            if (novoPrecoUnitario != null) pstmt.setDouble(index++, novoPrecoUnitario);
            if (novoDesconto != null) pstmt.setDouble(index++, novoDesconto);
            if (novoValorTotal != null) pstmt.setDouble(index++, novoValorTotal);
            if (novaData != null) pstmt.setDate(index++, novaData);
            if (novoTotal != null) pstmt.setDouble(index++, novoTotal);

            pstmt.setInt(index, clienteId);

            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Pedido atualizado com sucesso!");
            } else {
                System.out.println("Pedido não encontrado ou nada foi alterado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar pedido: " + e.getMessage());
        }

        scanner.close();
    }

}
