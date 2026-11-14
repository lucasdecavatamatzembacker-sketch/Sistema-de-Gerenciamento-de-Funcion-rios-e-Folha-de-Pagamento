package model;

public class FuncionarioPJ extends Funcionario {
    private double valorPorProjeto;
    private int quantidadeProjetos;

    public FuncionarioPJ(int codigo, String nome, String cpf, double valorPorProjeto, int quantidadeProjetos) {
        super(codigo, nome, cpf, 0);
        setValorPorProjeto(valorPorProjeto);
        setQuantidadeProjetos(quantidadeProjetos);
    }

    public double getValorPorProjeto() {
        return valorPorProjeto;
    }

    public void setValorPorProjeto(double valorPorProjeto) {
        if (valorPorProjeto < 0) {
            throw new IllegalArgumentException("O valor por projeto não pode ser negativo.");
        }
        this.valorPorProjeto = valorPorProjeto;
    }

    public int getQuantidadeProjetos() {
        return quantidadeProjetos;
    }

    public void setQuantidadeProjetos(int quantidadeProjetos) {
        if (quantidadeProjetos < 0) {
            throw new IllegalArgumentException("A quantidade de projetos não pode ser negativa.");
        }
        this.quantidadeProjetos = quantidadeProjetos;
    }

    @Override
    public double calcularRemuneracao() {
        return valorPorProjeto * quantidadeProjetos;
    }

    @Override
    public String getTipo() {
        return "PJ";
    }

    @Override
    public String exibirDados() {
        return super.exibirDados() + "\n"
                + "Valor por projeto: R$ " + String.format("%.2f", valorPorProjeto) + "\n"
                + "Projetos realizados: " + quantidadeProjetos;
    }
}
