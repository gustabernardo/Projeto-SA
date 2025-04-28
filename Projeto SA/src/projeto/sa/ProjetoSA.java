package projeto.sa;

import java.util.Scanner;

public class ProjetoSA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Passo 1: Solicitar número de vendedores com validação
        int numVendedores = 0;
        try {
            System.out.print("Numero de vendedores: ");
            numVendedores = sc.nextInt();
            if (numVendedores <= 0) {
                System.err.println("Erro: O numero de vendedores deve ser maior que zero.");
                sc.close();
                System.exit(1);
            }
            sc.nextLine(); // Consumir nova linha após entrada numérica
        } catch (Exception e) {
            System.err.println("Erro: Entrada invalida. Deve ser um numero inteiro.");
            sc.close();
            System.exit(1);
        }

        // Declarar arrays para armazenar dados
        String[] vendedores = new String[numVendedores]; // Array para nomes dos vendedores
        double[][] vendas = new double[numVendedores][5]; // Matriz para vendas (vendedores x 5 dias)
        double[] totais = new double[numVendedores]; // Array para totais semanais
        String[] classificacoes = new String[numVendedores]; // Array para classificações
        double[] bonificacoes = new double[numVendedores]; // Array para bonificações

        // Passo 2 e 3: Registrar nomes e vendas diárias com validação
        for (int i = 0; i < numVendedores; i++) {
            // Solicitar nome do vendedor
            System.out.printf("Digite o nome do vendedor %d: ", i + 1);
            String nome = sc.nextLine();
            
            // Validar nome: apenas letras, espaços e hífens; não pode ser número
            if (!nome.matches("^[a-zA-Z\\s-]+$") || nome.matches("^[0-9]+$")) {
                System.err.println("Erro: Nome invalido. Deve conter apenas letras, espacos ou hifens e nao pode ser um numero.");
                sc.close();
                System.exit(1);
            }
            vendedores[i] = nome;

            // Solicitar vendas para os 5 dias úteis
            System.out.printf("\nVendas do vendedor %s:\n", vendedores[i]);
            String[] dias = {"Segunda", "Terca", "Quarta", "Quinta", "Sexta"};
            for (int j = 0; j < 5; j++) {
                try {
                    System.out.printf("Digite as vendas de %s: R$ ", dias[j]);
                    double venda = sc.nextDouble();
                    if (venda < 0) {
                        System.err.println("Erro: As vendas nao podem ser negativas.");
                        sc.close();
                        System.exit(1);
                    }
                    vendas[i][j] = venda;
                    totais[i] += vendas[i][j]; // Acumular total semanal
                } catch (Exception e) {
                    System.err.println("Erro: Entrada invalida para vendas. Deve ser um numero decimal valido.");
                    sc.close();
                    System.exit(1);
                }
            }
            sc.nextLine(); // Consumir nova linha após vendas
        }

        // Passo 4 e 5: Classificar vendedores e calcular bonificações
        for (int i = 0; i < numVendedores; i++) {
            double total = totais[i];
            if (total >= 5000) {
                classificacoes[i] = "Excelente";
                bonificacoes[i] = total * 0.10; // 10% de bonificação
            } else if (total >= 3000 && total <= 4999.99) {
                classificacoes[i] = "Bom";
                bonificacoes[i] = total * 0.05; // 5% de bonificação
            } else {
                classificacoes[i] = "Regular";
                bonificacoes[i] = 0; // Sem bonificação
            }
        }

        // Passo 7: Gerar relatório final no formato especificado
        System.out.println("============================================================");
        System.out.println(" RELATORIO DE VENDAS SEMANAIS");
        System.out.println("============================================================");
        System.out.println("| Vendedor        | Total Vendido | Classificacao | Bonus      |");
        System.out.println("+-----------------------------------------------------------------------------+");
        for (int i = 0; i < numVendedores; i++) {
            System.out.printf("%-16s | R$ %10.2f | %-13s | R$ %8.2f |\n",
                    vendedores[i], totais[i], classificacoes[i], bonificacoes[i]);
        }
        System.out.println("+-----------------------------------------------------------------------------+");

        // Passo 6 e 8: Identificar e exibir o melhor vendedor
        int melhorIdx = 0;
        for (int i = 1; i < numVendedores; i++) {
            if (totais[i] > totais[melhorIdx]) {
                melhorIdx = i;
            }
        }
        System.out.printf("MELHOR VENDEDOR DA SEMANA: %s (R$ %.2f)\n",
                vendedores[melhorIdx], totais[melhorIdx]);
        System.out.println("============================================================");

        sc.close();
    }
}