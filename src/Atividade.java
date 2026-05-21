import java.util.ArrayList;
import java.util.List;

public abstract class Atividade {
    private String nome;
    private String horario;
    private int duracaoAula;
    private int quantidadeMaxParticipantes;
    private boolean ativa;
    private TipoAtividade tipo;
    private List<String> participantes;

    public Atividade(String nome, String horario, int duracaoAula, int quantidadeMaxParticipantes, TipoAtividade tipo) {
        this.nome = nome;
        this.horario = horario;
        this.duracaoAula = duracaoAula;
        this.quantidadeMaxParticipantes = quantidadeMaxParticipantes;
        this.tipo = tipo;
        this.ativa = true;
        this.participantes = new ArrayList<>();
    }

    public boolean adicionarParticipante(String nome) {
        if (participantes.size() < quantidadeMaxParticipantes) {
            participantes.add(nome);
            return true;
        }
        return false;
    }

    public abstract String getDescricao();

    public String getNome() { return nome; }
    public String getHorario() { return horario; }
    public int getDuracaoAula() { return duracaoAula; }
    public int getQuantidadeMaxParticipantes() { return quantidadeMaxParticipantes; }
    public boolean isAtiva() { return ativa; }
    public void setAtiva(boolean ativa) { this.ativa = ativa; }
    public TipoAtividade getTipo() { return tipo; }
    public List<String> getParticipantes() { return participantes; }

    @Override
    public String toString() {
        return String.format("[%s] %s | Horário: %s | Duração: %dmin | Vagas: %d/%d | Status: %s\n  %s",
                tipo, nome, horario, duracaoAula,
                participantes.size(), quantidadeMaxParticipantes,
                ativa ? "Ativa" : "Inativa",
                getDescricao());
    }
}