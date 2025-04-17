package org.senai.com.br.BancoDados.Menu;

import org.senai.com.br.BancoDados.Auxiliar.Entregas;

import java.util.Scanner;

public class Menu_Entregas extends Menu_Principal {

    public void menuEntregas(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n╔═══════════════════════════════════╗");
            System.out.println("║           ENTREGAS                ║");
            System.out.println("╠═══════════════════════════════════╣");
            System.out.println("║ [1] Rastrear Entrega              ║");
            System.out.println("║ [2] Voltar ao Menu Armazéns       ║");
            System.out.println("╚═══════════════════════════════════╝");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    Entregas rastrear = new Entregas();
                    rastrear.rastrearEntrega();

                    break;

                case 2:
                    System.out.println("Voltando ao menu de armazéns...");
                    return;
                default:
                    System.out.println("[!] Opção inválida.");
            }
        } while (opcao != 2);
    }

}
