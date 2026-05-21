import java.util.ArrayList;
import java.util.List;

public class Natacao extends Atividade implements Certificavel, ControladorPresenca {
    private double profundidadePiscina;
    private List<String> registrosPresenca;

    public Natacao(String nome, String horario, int duracaoAula, int quantidadeMaxParticipantes, double profundidadePiscina) {
        super(nome, horario, duracaoAula, quantidadeMaxParticipantes, TipoAtividade.COLETIVA);
        this.profundidadePiscina = profundidadePiscina;
        this.registrosPresenca = new ArrayList<>();
    }

    @Override
    public void emitirCertificado(String nomeParticipante) {
        System.out.println("==============================");
        System.out.println("         CERTIFICADO          ");
        System.out.println("==============================");
        System.out.println("Certificamos que " + nomeParticipante);
        System.out.println("concluiu o curso de Natação:");
        System.out.println("Turma: " + getNome());
        System.out.println("Horário: " + getHorario());
        System.out.println("==============================");
    }

    @Override
    public void registrarPresenca(String nomeParticipante) {
        registrosPresenca.add(nomeParticipante);
        System.out.println("Presença de '" + nomeParticipante + "' registrada na aula de Natação: " + getNome());
    }

    public List<String> getRegistrosPresenca() { return registrosPresenca; }
    public double getProfundidadePiscina() { return profundidadePiscina; }

    @Override
    public String getDescricao() {
        return String.format("Natação | Profundidade da piscina: %.1fm | Presenças registradas: %d",
                profundidadePiscina, registrosPresenca.size());
    }
}