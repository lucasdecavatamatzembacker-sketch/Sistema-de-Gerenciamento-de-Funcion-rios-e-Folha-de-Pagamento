package model;

public class FuncionarioComissionado extends Funcionario implements Tributavel {
    private double percentualComissao;
    private double totalVendas;

    public FuncionarioComissionado(int codigo, String nome, String cpf, double salarioBase,
                                   double percentualComissao, double totalVendas) {
        super(codigo, nome, cpf, salarioBase);
        setPercentualComissao(percentualComissao);
        setTotalVendas(totalVendas);
    }

    public double getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(double percentualComissao) {
        if (percentualComissao < 0 || percentualComissao > 100) {
            throw new IllegalArgumentException("Percentual de comissão inválido. Deve estar entre 0 e 100.");
        }
        this.percentualComissao = percentualComissao;
    }

    public double getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(double totalVendas) {
        if (totalVendas < 0) {
            throw new IllegalArgumentException("O total de vendas não pode ser negativo.");
        }
        this.totalVendas = totalVendas;
    }

    public double calcularComissao() {
        return totalVendas * (percentualComissao / 100);
    }

    @Override
    public double calcularRemuneracao() {
        return getSalarioBase() + calcularComissao();
    }

    @Override
    public double calcularTributos() {
        return calcularRemuneracao() * 0.11;
    }

    @Override
    public String getTipo() {
        return "Comissionado";
    }

    @Override
    public String exibirDados() {
        return super.exibirDados() + "\n"
                + "Percentual de comissão: " + String.format("%.2f", percentualComissao) + "%\n"
                + "Total de vendas: R$ " + String.format("%.2f", totalVendas) + "\n"
                + "Comissão: R$ " + String.format("%.2f", calcularComissao()) + "\n"
                + "Tributos: R$ " + String.format("%.2f", calcularTributos());
    }
}
