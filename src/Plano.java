import java.util.ArrayList;
import java.util.List;

public abstract class Plano {
    private String nome;
    private boolean ativo;
    private List<Atividade> atividades;

    public Plano(String nome) {
        this.nome = nome;
        this.ativo = true;
        this.atividades = new ArrayList<>();
    }

    public abstract boolean podeAssociarAtividade(Atividade atividade);

    public abstract String getDescricao();

    public boolean associarAtividade(Atividade atividade) {
        if (!podeAssociarAtividade(atividade)) {
            System.out.println("Atividade '" + atividade.getNome() + "' não pode ser associada ao plano '" + nome + "'.");
            return false;
        }
        if (atividades.contains(atividade)) {
            System.out.println("Atividade '" + atividade.getNome() + "' já está associada a este plano.");
            return false;
        }
        atividades.add(atividade);
        return true;
    }

    public String getNome() { return nome; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    public List<Atividade> getAtividades() { return atividades; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s | Status: %s\n  %s", nome, ativo ? "Ativo" : "Inativo", getDescricao()));
        if (atividades.isEmpty()) {
            sb.append("\n  Atividades: nenhuma");
        } else {
            sb.append("\n  Atividades associadas:");
            for (Atividade a : atividades) {
                sb.append("\n    - ").append(a.getNome());
            }
        }
        return sb.toString();
    }
}