import java.util.ArrayList;
import java.util.List;

public class Spinning extends Atividade implements ControladorPresenca {
    private int quantidadeBicicletas;
    private List<String> registrosPresenca;

    public Spinning(String nome, String horario, int duracaoAula, int quantidadeMaxParticipantes, int quantidadeBicicletas) {
        super(nome, horario, duracaoAula, quantidadeMaxParticipantes, TipoAtividade.COLETIVA);
        this.quantidadeBicicletas = quantidadeBicicletas;
        this.registrosPresenca = new ArrayList<>();
    }

    @Override
    public void registrarPresenca(String nomeParticipante) {
        registrosPresenca.add(nomeParticipante);
        System.out.println("Presença de '" + nomeParticipante + "' registrada na aula de Spinning: " + getNome());
    }

    public List<String> getRegistrosPresenca() { return registrosPresenca; }
    public int getQuantidadeBicicletas() { return quantidadeBicicletas; }

    @Override
    public String getDescricao() {
        return "Spinning | Bicicletas disponíveis: " + quantidadeBicicletas + " | Presenças registradas: " + registrosPresenca.size();
    }
}