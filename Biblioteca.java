
import java.util.ArrayList;
import java.util.Scanner;
 
public class Biblioteca {
 
    // =============================================
    // FUNÇÃO AUXILIAR - busca o índice de um título
    // Usada por todas as operações abaixo
    // Retorna -1 se não encontrar
    // =============================================
    static int buscarIndice(ArrayList<String> arrayTitulos, String titulo) {
        for (int i = 0; i < arrayTitulos.size(); i++) {
            if (arrayTitulos.get(i).equalsIgnoreCase(titulo)) {
                return i;
            }
        }
        return -1;
    }
 
    public static void main(String[] args) {
 
        // Scanner usado para ler dados digitados pelo usuário
        Scanner sc = new Scanner(System.in);
 
        // Arrays sincronizados para armazenar os dados dos livros
        ArrayList<String> arrayTitulos    = new ArrayList<>();
        ArrayList<String> arrayAutores    = new ArrayList<>();
        ArrayList<Integer> arrayStatus    = new ArrayList<>();
        ArrayList<String> arrayLocatarios = new ArrayList<>();
        ArrayList<Integer> arrayPrazos    = new ArrayList<>();
 
        // Variável que armazenará a opção escolhida no menu
        int opcao;
 
        // Loop principal do sistema
        do {
 
            // Exibição do menu principal
            System.out.println("\n===== SISTEMA DE BIBLIOTECA =====");
            System.out.println("1 - Adicionar Livro");
            System.out.println("2 - Listar Livros");
            System.out.println("3 - Pesquisar Livro");
            System.out.println("4 - Realizar Empréstimo");
            System.out.println("5 - Realizar Devolução");
            System.out.println("6 - Excluir Livro");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
 
            // Verifica se o usuário digitou um número
            if (sc.hasNextInt()) {
                opcao = sc.nextInt();
                sc.nextLine(); // Limpa o ENTER do teclado
            } else {
                System.out.println("Erro: digite apenas números.");
                sc.nextLine(); // Limpa a entrada inválida
                opcao = -1;
            }
 
            switch (opcao) {
 
                // =============================================
                // CASE 1 - ADICIONAR LIVRO (já estava pronto)
                // =============================================
                case 1:
 
                    System.out.println("\n=== ADICIONAR LIVRO ===");
 
                    System.out.print("Digite o título: ");
                    String titulo = sc.nextLine().trim();
 
                    System.out.print("Digite o autor: ");
                    String autor = sc.nextLine().trim();
 
                    if (titulo.isEmpty() || autor.isEmpty()) {
                        System.out.println("Erro: preencha todos os campos.");
                    } else {
                        arrayTitulos.add(titulo);
                        arrayAutores.add(autor);
                        arrayStatus.add(0);       // 0 = disponível
                        arrayLocatarios.add("");
                        arrayPrazos.add(0);
                        System.out.println("Livro cadastrado com sucesso!");
                    }
 
                    break;
 
                // =============================================
                // CASE 2 - LISTAR LIVROS (sub-menu adicionado)
                // =============================================
                case 2:
 
                    System.out.println("\n=== LISTAR LIVROS ===");
 
                    if (arrayTitulos.size() == 0) {
                        System.out.println("Nenhum livro cadastrado.");
                        break;
                    }
 
                    // Sub-menu de filtros
                    System.out.println("1 - Todos os livros");
                    System.out.println("2 - Apenas disponíveis");
                    System.out.println("3 - Por autor");
                    System.out.print("Escolha o filtro: ");
 
                    int filtro = sc.nextInt();
                    sc.nextLine();
 
                    String autorFiltro = "";
                    if (filtro == 3) {
                        System.out.print("Digite o nome do autor: ");
                        autorFiltro = sc.nextLine().trim();
                    }
 
                    System.out.println();
                    boolean encontrouAlgum = false;
 
                    for (int i = 0; i < arrayTitulos.size(); i++) {
 
                        // Filtro: apenas disponíveis
                        if (filtro == 2 && arrayStatus.get(i) != 0) continue;
 
                        // Filtro: por autor
                        if (filtro == 3 && !arrayAutores.get(i).equalsIgnoreCase(autorFiltro)) continue;
 
                        encontrouAlgum = true;
 
                        System.out.println("[ID: " + (i + 1) + "] Título: " + arrayTitulos.get(i)
                            + " | Autor: " + arrayAutores.get(i)
                            + " | Status: " + (arrayStatus.get(i) == 0
                                ? "Disponível"
                                : "Emprestado para: " + arrayLocatarios.get(i)));
                    }
 
                    if (!encontrouAlgum) {
                        System.out.println("Nenhum livro encontrado para esse filtro.");
                    }
 
                    break;
 
                // =============================================
                // CASE 3 - PESQUISAR LIVRO POR TÍTULO
                // =============================================
                case 3:
 
                    System.out.println("\n=== PESQUISAR LIVRO ===");
 
                    System.out.print("Digite o título para busca: ");
                    String buscaTitulo = sc.nextLine().trim();
 
                    int indicePesquisa = buscarIndice(arrayTitulos, buscaTitulo);
 
                    if (indicePesquisa == -1) {
                        System.out.println("Livro não encontrado.");
                    } else {
                        System.out.println("\n--- Livro encontrado ---");
                        System.out.println("Título : " + arrayTitulos.get(indicePesquisa));
                        System.out.println("Autor  : " + arrayAutores.get(indicePesquisa));
                        System.out.println("Status : " + (arrayStatus.get(indicePesquisa) == 0
                            ? "Disponível"
                            : "Emprestado para: " + arrayLocatarios.get(indicePesquisa)));
                    }
 
                    break;
 
                // =============================================
                // CASE 4 - REALIZAR EMPRÉSTIMO
                // =============================================
                case 4:
 
                    System.out.println("\n=== REALIZAR EMPRÉSTIMO ===");
 
                    System.out.print("Digite o título do livro: ");
                    String tituloEmprestimo = sc.nextLine().trim();
 
                    System.out.print("Digite o nome do locatário: ");
                    String locatario = sc.nextLine().trim();
 
                    if (tituloEmprestimo.isEmpty() || locatario.isEmpty()) {
                        System.out.println("Erro: preencha todos os campos.");
                        break;
                    }
 
                    int indiceEmprestimo = buscarIndice(arrayTitulos, tituloEmprestimo);
 
                    if (indiceEmprestimo == -1) {
                        System.out.println("Operação falhou: livro não encontrado.");
                        break;
                    }
 
                    // Verifica se o livro já está emprestado
                    if (arrayStatus.get(indiceEmprestimo) == 1) {
                        System.out.println("Operação falhou: livro já está emprestado.");
                        break;
                    }
 
                    // Conta quantos livros o locatário já tem
                    int contador = 0;
                    for (int i = 0; i < arrayLocatarios.size(); i++) {
                        if (arrayLocatarios.get(i).equalsIgnoreCase(locatario)) {
                            contador++;
                        }
                    }
 
                    if (contador >= 3) {
                        System.out.println("Operação falhou: usuário atingiu o limite de 3 livros.");
                        break;
                    }
 
                    // Efetiva o empréstimo
                    arrayStatus.set(indiceEmprestimo, 1);
                    arrayLocatarios.set(indiceEmprestimo, locatario);
                    arrayPrazos.set(indiceEmprestimo, 7); // prazo de 7 dias
 
                    System.out.println("Empréstimo realizado com sucesso para " + locatario + "! Prazo: 7 dias.");
 
                    break;
 
                // =============================================
                // CASE 5 - REALIZAR DEVOLUÇÃO
                // =============================================
                case 5:
 
                    System.out.println("\n=== REALIZAR DEVOLUÇÃO ===");
 
                    System.out.print("Digite o título do livro a devolver: ");
                    String tituloDevolucao = sc.nextLine().trim();
 
                    int indiceDevolucao = buscarIndice(arrayTitulos, tituloDevolucao);
 
                    if (indiceDevolucao == -1) {
                        System.out.println("Operação falhou: livro não encontrado.");
                        break;
                    }
 
                    // Verifica se o livro realmente está emprestado
                    if (arrayStatus.get(indiceDevolucao) == 0) {
                        System.out.println("Este livro já está disponível.");
                        break;
                    }
 
                    System.out.print("Quantos dias se passaram desde o empréstimo? ");
                    int diasPassados = sc.nextInt();
                    sc.nextLine();
 
                    int prazo = arrayPrazos.get(indiceDevolucao);
 
                    // Calcula multa se houver atraso
                    if (diasPassados > prazo) {
                        double multa = (diasPassados - prazo) * 2.0;
                        System.out.printf("Devolução com atraso! Multa: R$ %.2f%n", multa);
                    } else {
                        System.out.println("Devolução no prazo. Sem multa!");
                    }
 
                    // Restaura os dados do livro
                    arrayStatus.set(indiceDevolucao, 0);
                    arrayLocatarios.set(indiceDevolucao, "");
                    arrayPrazos.set(indiceDevolucao, 0);
 
                    System.out.println("Livro devolvido com sucesso!");
 
                    break;
 
                // =============================================
                // CASE 6 - EXCLUIR LIVRO
                // =============================================
                case 6:
 
                    System.out.println("\n=== EXCLUIR LIVRO ===");
 
                    System.out.print("Digite o título do livro a excluir: ");
                    String tituloExcluir = sc.nextLine().trim();
 
                    int indiceExcluir = buscarIndice(arrayTitulos, tituloExcluir);
 
                    if (indiceExcluir == -1) {
                        System.out.println("Operação falhou: título não encontrado.");
                        break;
                    }
 
                    // Não permite excluir livro emprestado
                    if (arrayStatus.get(indiceExcluir) == 1) {
                        System.out.println("Operação falhou: o livro está emprestado e não pode ser excluído.");
                        break;
                    }
 
                    // Remove do mesmo índice em TODOS os arrays
                    arrayTitulos.remove(indiceExcluir);
                    arrayAutores.remove(indiceExcluir);
                    arrayStatus.remove(indiceExcluir);
                    arrayLocatarios.remove(indiceExcluir);
                    arrayPrazos.remove(indiceExcluir);
 
                    System.out.println("Livro excluído com sucesso!");
 
                    break;
 
                case 0:
                    System.out.println("Sistema encerrado.");
                    break;
 
                default:
                    System.out.println("Opção inválida.");
            }
 
        } while (opcao != 0);
 
        sc.close();
    }
}
 