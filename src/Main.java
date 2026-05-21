import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Atividade> atividades = new ArrayList<>();
    private static List<Plano> planos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarDados();

        int opcao;
        do {
            exibirMenu();
            opcao = lerInt("Opção: ");
            switch (opcao) {
                case 1 -> associarAtividadeAPlano();
                case 2 -> exibirTodasAtividades();
                case 3 -> exibirTodosPlanos();
                case 4 -> exibirPlanosAtivos();
                case 5 -> emitirCertificado();
                case 6 -> registrarPresencaAtividade();
                case 7 -> removerAtividadesInativas();
                case 8 -> desativarAtividade();
                case 0 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    // Cria 3 atividades e 2 planos iniciais sem interação com o usuário
    private static void inicializarDados() {
        Musculacao musculacao = new Musculacao("Musculação Manhã", "07:00", 60, 30, 25);
        Natacao natacao = new Natacao("Natação Intermediária", "09:00", 45, 20, 1.8);
        Spinning spinning = new Spinning("Spinning Noturno", "19:00", 50, 15, 15);

        atividades.add(musculacao);
        atividades.add(natacao);
        atividades.add(spinning);

        PlanoBasico basico = new PlanoBasico("Plano Básico Mensal");
        PlanoPremium premium = new PlanoPremium("Plano Premium Mensal");

        basico.associarAtividade(musculacao);
        premium.associarAtividade(musculacao);
        premium.associarAtividade(natacao);
        premium.associarAtividade(spinning);

        planos.add(basico);
        planos.add(premium);

        System.out.println("=== Sistema de Academia Inicializado ===");
        System.out.println("3 atividades e 2 planos carregados com sucesso.\n");
    }

    private static void exibirMenu() {
        System.out.println("\n========== MENU ==========");
        System.out.println("1. Associar atividade a um plano");
        System.out.println("2. Exibir todas as atividades");
        System.out.println("3. Exibir todos os planos");
        System.out.println("4. Exibir apenas planos ativos");
        System.out.println("5. Emitir certificado");
        System.out.println("6. Registrar presença em atividade");
        System.out.println("7. Remover atividades inativas");
        System.out.println("8. Desativar atividade");
        System.out.println("0. Sair");
        System.out.println("==========================");
    }

    // Funcionalidade 1
    private static void associarAtividadeAPlano() {
        if (atividades.isEmpty()) { System.out.println("Nenhuma atividade cadastrada."); return; }
        if (planos.isEmpty()) { System.out.println("Nenhum plano cadastrado."); return; }

        System.out.println("\n--- Atividades disponíveis ---");
        for (int i = 0; i < atividades.size(); i++) {
            System.out.printf("%d. %s [%s]%n", i + 1, atividades.get(i).getNome(), atividades.get(i).getTipo());
        }
        int idxAtiv = lerInt("Escolha a atividade: ") - 1;
        if (idxAtiv < 0 || idxAtiv >= atividades.size()) { System.out.println("Índice inválido."); return; }

        System.out.println("\n--- Planos disponíveis ---");
        for (int i = 0; i < planos.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, planos.get(i).getNome());
        }
        int idxPlano = lerInt("Escolha o plano: ") - 1;
        if (idxPlano < 0 || idxPlano >= planos.size()) { System.out.println("Índice inválido."); return; }

        boolean ok = planos.get(idxPlano).associarAtividade(atividades.get(idxAtiv));
        if (ok) System.out.println("Atividade associada com sucesso!");
    }

    // Funcionalidade 2
    private static void exibirTodasAtividades() {
        System.out.println("\n--- Todas as Atividades ---");
        if (atividades.isEmpty()) { System.out.println("Nenhuma atividade cadastrada."); return; }
        for (int i = 0; i < atividades.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, atividades.get(i));
        }
    }

    // Funcionalidade 3
    private static void exibirTodosPlanos() {
        System.out.println("\n--- Todos os Planos ---");
        if (planos.isEmpty()) { System.out.println("Nenhum plano cadastrado."); return; }
        for (int i = 0; i < planos.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, planos.get(i));
        }
    }

    // Funcionalidade 4
    private static void exibirPlanosAtivos() {
        System.out.println("\n--- Planos Ativos ---");
        boolean encontrou = false;
        for (Plano p : planos) {
            if (p.isAtivo()) {
                System.out.println("- " + p);
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum plano ativo encontrado.");
    }

    // Funcionalidade 5
    private static void emitirCertificado() {
        List<Atividade> certificaveis = new ArrayList<>();
        for (Atividade a : atividades) {
            if (a instanceof Certificavel) certificaveis.add(a);
        }
        if (certificaveis.isEmpty()) { System.out.println("Nenhuma atividade oferece certificado."); return; }

        System.out.println("\n--- Atividades que emitem certificado ---");
        for (int i = 0; i < certificaveis.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, certificaveis.get(i).getNome());
        }
        int idx = lerInt("Escolha a atividade: ") - 1;
        if (idx < 0 || idx >= certificaveis.size()) { System.out.println("Índice inválido."); return; }

        System.out.print("Nome do participante: ");
        String nome = scanner.nextLine().trim();
        ((Certificavel) certificaveis.get(idx)).emitirCertificado(nome);
    }

    // Funcionalidade 6
    private static void registrarPresencaAtividade() {
        List<Atividade> comPresenca = new ArrayList<>();
        for (Atividade a : atividades) {
            if (a instanceof ControladorPresenca) comPresenca.add(a);
        }
        if (comPresenca.isEmpty()) { System.out.println("Nenhuma atividade registra presença."); return; }

        System.out.println("\n--- Atividades com controle de presença ---");
        for (int i = 0; i < comPresenca.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, comPresenca.get(i).getNome());
        }
        int idx = lerInt("Escolha a atividade: ") - 1;
        if (idx < 0 || idx >= comPresenca.size()) { System.out.println("Índice inválido."); return; }

        System.out.print("Nome do participante: ");
        String nome = scanner.nextLine().trim();
        ((ControladorPresenca) comPresenca.get(idx)).registrarPresenca(nome);
    }

    // Funcionalidade 7
    private static void removerAtividadesInativas() {
        int antes = atividades.size();
        Iterator<Atividade> it = atividades.iterator();
        while (it.hasNext()) {
            Atividade a = it.next();
            if (!a.isAtiva()) {
                for (Plano p : planos) {
                    p.getAtividades().remove(a);
                }
                it.remove();
                System.out.println("Atividade removida: " + a.getNome());
            }
        }
        int removidas = antes - atividades.size();
        if (removidas == 0) System.out.println("Nenhuma atividade inativa encontrada.");
        else System.out.println(removidas + " atividade(s) inativa(s) removida(s).");
    }

    // Funcionalidade extra: desativar para viabilizar o teste da funcionalidade 7
    private static void desativarAtividade() {
        if (atividades.isEmpty()) { System.out.println("Nenhuma atividade cadastrada."); return; }
        System.out.println("\n--- Selecione a atividade para desativar ---");
        for (int i = 0; i < atividades.size(); i++) {
            System.out.printf("%d. %s [%s]%n", i + 1, atividades.get(i).getNome(),
                    atividades.get(i).isAtiva() ? "Ativa" : "Inativa");
        }
        int idx = lerInt("Escolha: ") - 1;
        if (idx < 0 || idx >= atividades.size()) { System.out.println("Índice inválido."); return; }
        atividades.get(idx).setAtiva(false);
        System.out.println("Atividade '" + atividades.get(idx).getNome() + "' desativada.");
    }

    private static int lerInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print(prompt);
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }
}