package projeto.sa;

import java.util.Scanner;

public class ProjetoSA {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            // Passo 1: Solicitar número de vendedores com validação
            int numVendedores;
            do {
                System.out.print("Numero de vendedores: ");
                numVendedores = sc.nextInt();
                if (numVendedores <= 0) {
                    System.out.println("Erro: Número de vendedores deve ser maior que zero.");
                }
            } while (numVendedores <= 0);
            sc.nextLine(); // Limpar buffer

            // Declarar arrays para armazenar dados
            String[] vendedores = new String[numVendedores];
            double[][] vendas = new double[numVendedores][5];
            double[] totais = new double[numVendedores];
            String[] classificacoes = new String[numVendedores];
            double[] bonificacoes = new double[numVendedores];

            // Passo 2 e 3: Solicitar nomes e vendas
            solicitarDadosVendedores(sc, numVendedores, vendedores, vendas, totais);

            // Passo 4 e 5: Classificar e calcular bonificações
            calcularBonificacoes(numVendedores, totais, classificacoes, bonificacoes);

            // Passo 7: Gerar relatório
            gerarRelatorio(numVendedores, vendedores, totais, classificacoes, bonificacoes);

            // Passo 6 e 8: Exibir melhor(es) vendedor(es)
            exibirMelhorVendedor(numVendedores, vendedores, totais);
        }
    }

    // Método para solicitar nomes e vendas com validação
    private static void solicitarDadosVendedores(Scanner sc, int numVendedores, 
            String[] vendedores, double[][] vendas, double[] totais) {
        String[] dias = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta"};
        for (int i = 0; i < numVendedores; i++) {
            // Solicitar nome do vendedor
            System.out.printf("Digite o nome do vendedor %d: ", i + 1);
            vendedores[i] = sc.nextLine();

            // Solicitar vendas para os 5 dias úteis com validação
            System.out.printf("\nVendas do vendedor %s:\n", vendedores[i]);
            for (int j = 0; j < 5; j++) {
                double venda;
                do {
                    System.out.printf("Digite as vendas de %s: R$ ", dias[j]);
                    venda = sc.nextDouble();
                    if (venda < 0) {
                        System.out.println("Erro: Vendas não podem ser negativas.");
                    }
                } while (venda < 0);
                vendas[i][j] = venda;
                totais[i] += vendas[i][j];
            }
            sc.nextLine(); // Limpar buffer
        }
    }

    // Método para calcular classificações e bonificações
    private static void calcularBonificacoes(int numVendedores, double[] totais, 
            String[] classificacoes, double[] bonificacoes) {
        for (int i = 0; i < numVendedores; i++) {
            double total = totais[i];
            if (total >= 5000) {
                classificacoes[i] = "Excelente";
                bonificacoes[i] = total * 0.10;
            } else if (total >= 3000 && total <= 4999.99) {
                classificacoes[i] = "Bom";
                bonificacoes[i] = total * 0.05;
            } else {
                classificacoes[i] = "Regular";
                bonificacoes[i] = 0;
            }
        }
    }

    // Método para gerar o relatório
    private static void gerarRelatorio(int numVendedores, String[] vendedores, 
            double[] totais, String[] classificacoes, double[] bonificacoes) {
        System.out.println("============================================================");
        System.out.println(" RELATÓRIO DE VENDAS SEMANAIS");
        System.out.println("============================================================");
        System.out.println("| Vendedor        | Total Vendido | Classificação | Bônus      |");
        System.out.println("+-----------------------------------------------------------------------------+");
        for (int i = 0; i < numVendedores; i++) {
            System.out.printf("%-16s | R$ %10.2f | %-13s | R$ %8.2f |\n",
                    vendedores[i], totais[i], classificacoes[i], bonificacoes[i]);
        }
        System.out.println("+-----------------------------------------------------------------------------+");
    }

    // Método para exibir o(s) melhor(es) vendedor(es)
    private static void exibirMelhorVendedor(int numVendedores, String[] vendedores, 
            double[] totais) {
        double maiorTotal = totais[0];
        int indiceMelhorVendedor = 0;
        // Encontrar o maior total
        for (int i = 1; i < numVendedores; i++) {
            if (totais[i] > maiorTotal) {
                maiorTotal = totais[i];
                indiceMelhorVendedor = i;
            }
        }
        // Exibir todos os vendedores com o maior total
        System.out.println("MELHOR(ES) VENDEDOR(ES) DA SEMANA:");
        for (int i = 0; i < numVendedores; i++) {
            if (totais[i] == maiorTotal) {
                System.out.printf("%s (R$ %.2f)\n", vendedores[i], totais[i]);
            }
        }
        System.out.println("============================================================");
    }
}