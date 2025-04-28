package projeto.sa;

import java.util.Scanner;

public class ProjetoSA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Passo 1 Solicitar número de vendedores
        System.out.print("Numero de vendedores: ");
        int numVendedores = sc.nextInt();
        sc.nextLine(); 

        // Declarar arrays para armazenar dados
        String[] vendedores = new String[numVendedores];
        double[][] vendas = new double[numVendedores][5]; 
        double[] totais = new double[numVendedores];
        String[] classificacoes = new String[numVendedores]; 
        double[] bonificacoes = new double[numVendedores]; 

        // Passo 2 e 3 Registrar nomes e vendas diárias
        for (int i = 0; i < numVendedores; i++) {
            // Solicitar nome do vendedor
            System.out.printf("Digite o nome do vendedor %d: ", i + 1);
            vendedores[i] = sc.nextLine();

            // Solicitar vendas para os 5 dias úteis
            System.out.printf("\nVendas do vendedor %s:\n", vendedores[i]);
            String[] dias = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta"};
            for (int j = 0; j < 5; j++) {
                System.out.printf("Digite as vendas de %s: R$ ", dias[j]);
                vendas[i][j] = sc.nextDouble();
                totais[i] += vendas[i][j]; // 
            }
            sc.nextLine();
        }

        // Passo 4 e 5 Classificar vendedores e calcular bonificações
        for (int i = 0; i < numVendedores; i++) {
            double total = totais[i];
            if (total >= 5000) {
                classificacoes[i] = "Excelente";
                bonificacoes[i] = total * 0.10; // 
            } else if (total >= 3000 && total <= 4999.99) {
                classificacoes[i] = "Bom";
                bonificacoes[i] = total * 0.05; //
            } else {
                classificacoes[i] = "Regular";
                bonificacoes[i] = 0; // 
            }
        }

        // Passo 7 Gerar relatório final no formato especificado
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

        // Passo 6 e 8 Identificar e exibir o melhor vendedor
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