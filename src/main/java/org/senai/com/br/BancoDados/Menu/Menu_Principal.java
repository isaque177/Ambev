package org.senai.com.br.BancoDados.Menu;

//import org.senai.com.br.Banco.Tabelas.*;
import org.senai.com.br.BancoDados.Auxiliar.Produtos;
import org.senai.com.br.BancoDados.Auxiliar.Vendas;
import org.senai.com.br.BancoDados.Tabelas.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu_Principal {

    private static final String URL = "jdbc:mysql://localhost:3306/Ambev";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public void limparTela() {
        System.out.print("\033[H\033[2J");  // ANSI escape code for clearing the screen
        System.out.flush();
    }

    public void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            do {
                limparTela();
                System.out.println("\n╔═══════════════════════════════════╗");
                System.out.println("║                MENU               ║");
                System.out.println("╠═══════════════════════════════════╣");
                System.out.println("║ [1] 📋 Clientes                   ║");
                System.out.println("║ [2] 💰 Venda                      ║");
                System.out.println("║ [3] 📦 Armazens                   ║");
                System.out.println("║ [4] 📝  Campanhas                 ║");
                System.out.println("║ [5] 📋 Produtos                   ║");
                System.out.println("║ [6] ⚡ Sair                        ║");
                System.out.println("╚═══════════════════════════════════╝");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        limparTela();
                        System.out.println("\n╔═══════════════════════════════════╗");
                        System.out.println("║         MENU GERENCIAR CLIENTES   ║");
                        System.out.println("╠═══════════════════════════════════╣");
                        System.out.println("║ [1] Listar Todos os Clientes      ║");
                        System.out.println("║ [2] Atualizar Dados do Cliente    ║");
                        System.out.println("║ [3] Sair                          ║");
                        System.out.println("╚═══════════════════════════════════╝");

                        do {
                            opcao = scanner.nextInt();

                            switch (opcao) {
                                case 1:
                                    limparTela();
                                    Tabel_Cliente exibir_clientes = new Tabel_Cliente();
                                    exibir_clientes.Tabela_Cliente();
                                    System.out.println("\n╔═══════════════════════════════════╗");
                                    System.out.println("║         MENU GERENCIAR CLIENTES   ║");
                                    System.out.println("╠═══════════════════════════════════╣");
                                    System.out.println("║ [1] Listar Todos os Clientes      ║");
                                    System.out.println("║ [2] Atualizar Dados do Cliente    ║");
                                    System.out.println("║ [3] Sair                          ║");
                                    System.out.println("╚═══════════════════════════════════╝");
                                    scanner.nextLine();
                                    break;
                                case 2:
                                    // Código para atualizar cliente
                                    break;
                                case 3:
                                    System.out.println("\n⚡ Saindo...");
                                    break;
                                default:
                                    System.out.println("\n[!] Opção inválida! Tente novamente.");
                            }

                        } while (opcao != 3);

                        break;
                    case 2:
                        do {
                            limparTela();
                            System.out.println("\n╔═══════════════════════════════════╗");
                            System.out.println("║         MENU GERENCIAR VENDAS     ║");
                            System.out.println("╠═══════════════════════════════════╣");
                            System.out.println("║ [1] Listar Vendas                 ║");
                            System.out.println("║ [2] Buscar Venda por ID           ║");
                            System.out.println("║ [3] Voltar ao Menu Principal      ║");
                            System.out.println("╚═══════════════════════════════════╝");

                            opcao = scanner.nextInt();
                            scanner.nextLine(); // limpa buffer

                            switch (opcao) {
                                case 1:
                                    Tabel_Vendas exibir_vendas = new Tabel_Vendas();
                                    exibir_vendas.Tabela_Vendas();
                                    System.out.println("\n╔═══════════════════════════════════╗");
                                    System.out.println("║         MENU GERENCIAR VENDAS     ║");
                                    System.out.println("╠═══════════════════════════════════╣");
                                    System.out.println("║ [1] Listar Vendas                 ║");
                                    System.out.println("║ [2] Adicionar venda               ║");
                                    System.out.println("║ [3] Remover venda                 ║");
                                    System.out.println("║ [4] Atualizar venda               ║");
                                    System.out.println("║ [5] Voltar ao Menu Principal      ║");
                                    System.out.println("╚═══════════════════════════════════╝");
                                    scanner.nextLine(); // limpa buffer
                                    break;
                                case 2:
                                    Vendas adicionar_venda = new Vendas();
                                    adicionar_venda.adicionarVendaViaConsole();
                                    break;
                                case 3:
                                    Vendas remover_venda = new Vendas();
                                    remover_venda.removerVendaViaConsole();

                                    break;

                                case 4:
                                   Vendas atualizar_venda = new Vendas();
                                   atualizar_venda.atualizarVendaViaConsole();

                                    break;

                                case 5:
                                    System.out.println("Voltando ao menu principal...");
                                    return;

                                default:
                                    System.out.println("[!] Opção inválida. Tente novamente.");
                            }
                        } while (true);
                    case 3:

                        do {
                            limparTela();
                            System.out.println("\n╔═══════════════════════════════════╗");
                            System.out.println("║        MENU GERENCIAR ARMAZÉNS    ║");
                            System.out.println("╠═══════════════════════════════════╣");
                            System.out.println("║ [1] Exibir Transportadoras        ║");
                            System.out.println("║ [2] Exibir Movimentação Estoque   ║");
                            System.out.println("║ [3] Gerenciar Entregas            ║");
                            System.out.println("║ [4] Voltar ao Menu Principal      ║");
                            System.out.println("╚═══════════════════════════════════╝");
                            opcao = scanner.nextInt();
                            scanner.nextLine(); // limpar buffer

                            switch (opcao) {
                                case 1:
                                    Tabel_Transportadoras exibir_transportadoras = new Tabel_Transportadoras();
                                    exibir_transportadoras.Tabela_Transportadoras();
                                    System.out.println("\n╔═══════════════════════════════════╗");
                                    System.out.println("║        MENU GERENCIAR ARMAZÉNS    ║");
                                    System.out.println("╠═══════════════════════════════════╣");
                                    System.out.println("║ [1] Exibir Transportadoras        ║");
                                    System.out.println("║ [2] Exibir Movimentação Estoque   ║");
                                    System.out.println("║ [3] Gerenciar Entregas            ║");
                                    System.out.println("║ [4] Voltar ao Menu Principal      ║");
                                    System.out.println("╚═══════════════════════════════════╝");
                                    scanner.nextLine(); // limpar buffer
                                    break;
                                case 2:

                                    Tabel_MovimentacaoEstoque exibir_movimentacao = new Tabel_MovimentacaoEstoque();
                                    exibir_movimentacao.Tabela_movimetancaoestoque();

                                    break;
                                case 3:
                                    Menu_Entregas menuEntregas = new Menu_Entregas();
                                    menuEntregas.menuEntregas(scanner);
                                    break;
                                case 4:
                                    System.out.println("Voltando ao menu principal...");
                                    return;
                                default:
                                    System.out.println("[!] Opção inválida.");
                            }

                        } while (opcao != 4);


                        break;
                    case 4:
                        limparTela();
                        System.out.println("\n╔═══════════════════════════════════╗");
                        System.out.println("║            CAMPANHAS              ║");
                        System.out.println("╠═══════════════════════════════════╣");
                        System.out.println("║ [1] Exibir Investimento Marketing ║");
                        System.out.println("║ [2] Exibir Promoções              ║");
                        System.out.println("║ [3] Criar campanha                ║");
                        System.out.println("║ [4] Sair                          ║");
                        System.out.println("╚═══════════════════════════════════╝");

                        opcao = scanner.nextInt();
                        scanner.nextLine(); // limpar buffer

                        do {


                            switch (opcao) {
                                case 1:
                                    Tabel_Marketinginvestimentos marketinginvestimentos = new Tabel_Marketinginvestimentos();
                                    marketinginvestimentos.Tabela_Marketinginvestimentos();

                                    System.out.println("\n╔═══════════════════════════════════╗");
                                    System.out.println("║            CAMPANHAS              ║");
                                    System.out.println("╠═══════════════════════════════════╣");
                                    System.out.println("║ [1] Exibir Investimento Marketing ║");
                                    System.out.println("║ [2] Exibir Promoções              ║");
                                    System.out.println("║ [3] Criar campanha                ║");
                                    System.out.println("║ [4] Sair                          ║");
                                    System.out.println("╚═══════════════════════════════════╝");
                                    scanner.nextLine(); // limpar buffer
                                    break;
                                case 2:
                                    Tabel_Promocao mostrar_promocoes = new Tabel_Promocao();
                                    mostrar_promocoes.Tabela_Promocoes();
                                    break;
                                case 3:
                                    Criar_campanha campanha = new Criar_campanha();
                                    campanha.criarCampanha(scanner);
                                    break;
                                case 4:
                                    System.out.println("Voltando ao menu principal...");
                                    return;
                                default:
                                    System.out.println("[!] Opção inválida.");
                            }

                        }while (opcao != 4);

                        break;
                    case 5:

                        do {
                            limparTela();
                            System.out.println("\n╔═══════════════════════════════════╗");
                            System.out.println("║       MENU GERENCIAR PRODUTOS     ║");
                            System.out.println("╠═══════════════════════════════════╣");
                            System.out.println("║ [1] Exibir Produtos               ║");
                            System.out.println("║ [2] Adicionar Produto             ║");
                            System.out.println("║ [3] Remover Produto               ║");
                            System.out.println("║ [4] Modificar Produto             ║");
                            System.out.println("║ [5] Buscar Produto por ID         ║");
                            System.out.println("║ [6] Voltar ao Menu Principal      ║");
                            System.out.println("╚═══════════════════════════════════╝");

                            opcao = scanner.nextInt();
                            scanner.nextLine(); // limpar buffer

                            switch (opcao) {
                                case 1:
                                    Tabel_Produto exibir_produtos = new Tabel_Produto();
                                    exibir_produtos.Tabela_Produto();
                                    System.out.println("\n╔═══════════════════════════════════╗");
                                    System.out.println("║       MENU GERENCIAR PRODUTOS     ║");
                                    System.out.println("╠═══════════════════════════════════╣");
                                    System.out.println("║ [1] Exibir Produtos               ║");
                                    System.out.println("║ [2] Adicionar Produto             ║");
                                    System.out.println("║ [3] Remover Produto               ║");
                                    System.out.println("║ [4] Modificar Produto             ║");
                                    System.out.println("║ [5] Voltar ao Menu Principal      ║");
                                    System.out.println("╚═══════════════════════════════════╝");
                                    scanner.nextLine(); // limpar buffer
                                    break;
                                case 2:
                                    Produtos adicionar_produto = new Produtos();
                                    adicionar_produto.adicionarProdutoViaConsole();
                                    break;
                                case 3:
                                    Produtos remover_produto = new Produtos();
                                    remover_produto.RemoverProdutoViaConsole();
                                    break;
                                case 4:
                                    Produtos modificar_produto = new Produtos();
                                    modificar_produto.upatualizarProdutoViaConsole();
                                    break;

                                case 5:
                                    System.out.println("Voltando ao menu principal...");
                                    return;
                                default:
                                    System.out.println("[!] Opção inválida.");
                            }

                        } while (opcao != 5);
                        break;
                    case 6:
                        System.out.println("\n⚡ Saindo do Console Retro...");
                    default:
                        System.out.println("\n[!] Opção inválida! Tente novamente.");
                }

            } while (opcao != 6);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
