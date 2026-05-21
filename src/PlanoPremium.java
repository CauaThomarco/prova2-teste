public class PlanoPremium extends Plano {

    public PlanoPremium(String nome) {
        super(nome);
    }

    @Override
    public boolean podeAssociarAtividade(Atividade atividade) {
        return true;
    }

    @Override
    public String getDescricao() {
        return "Plano Premium | Todas as atividades | Acesso ilimitado";
    }
}