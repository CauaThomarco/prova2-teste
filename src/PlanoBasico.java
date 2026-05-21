import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PlanoBasico extends Plano implements ControladorPresenca {
    private Map<String, LocalDate> ultimoAcessoPorCliente;

    public PlanoBasico(String nome) {
        super(nome);
        this.ultimoAcessoPorCliente = new HashMap<>();
    }

    @Override
    public boolean podeAssociarAtividade(Atividade atividade) {
        return atividade.getTipo() == TipoAtividade.INDIVIDUAL;
    }

    @Override
    public void registrarPresenca(String nomeParticipante) {
        LocalDate hoje = LocalDate.now();
        LocalDate ultimoAcesso = ultimoAcessoPorCliente.get(nomeParticipante);

        if (ultimoAcesso != null && ultimoAcesso.equals(hoje)) {
            System.out.println("Acesso negado: '" + nomeParticipante + "' já utilizou o Plano Básico hoje.");
        } else {
            ultimoAcessoPorCliente.put(nomeParticipante, hoje);
            System.out.println("Acesso registrado: '" + nomeParticipante + "' acessou a academia (Plano Básico) em " + hoje + ".");
        }
    }

    public Map<String, LocalDate> getUltimoAcessoPorCliente() { return ultimoAcessoPorCliente; }

    @Override
    public String getDescricao() {
        return "Plano Básico | Apenas atividades individuais | 1 acesso por dia";
    }
}