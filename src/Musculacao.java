public class Musculacao extends Atividade {
    private int quantidadeAparelhos;

    public Musculacao(String nome, String horario, int duracaoAula, int quantidadeMaxParticipantes, int quantidadeAparelhos) {
        super(nome, horario, duracaoAula, quantidadeMaxParticipantes, TipoAtividade.INDIVIDUAL);
        this.quantidadeAparelhos = quantidadeAparelhos;
    }

    public int getQuantidadeAparelhos() { return quantidadeAparelhos; }
    public void setQuantidadeAparelhos(int quantidadeAparelhos) { this.quantidadeAparelhos = quantidadeAparelhos; }

    @Override
    public String getDescricao() {
        return "Musculação | Aparelhos disponíveis: " + quantidadeAparelhos;
    }
}