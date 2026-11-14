package model;

public class FuncionarioCLT extends Funcionario implements Tributavel {
    private double beneficios;

    public FuncionarioCLT(int codigo, String nome, String cpf, double salarioBase, double beneficios) {
        super(codigo, nome, cpf, salarioBase);
        setBeneficios(beneficios);
    }

    public double getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(double beneficios) {
        if (beneficios < 0) {
            throw new IllegalArgumentException("O valor dos benefícios não pode ser negativo.");
        }
        this.beneficios = beneficios;
    }

    @Override
    public double calcularRemuneracao() {
        return getSalarioBase() + beneficios;
    }

    @Override
    public double calcularTributos() {
        return getSalarioBase() * 0.11;
    }

    @Override
    public String getTipo() {
        return "CLT";
    }

    @Override
    public String exibirDados() {
        return super.exibirDados() + "\n"
                + "Benefícios: R$ " + String.format("%.2f", beneficios) + "\n"
                + "Tributos: R$ " + String.format("%.2f", calcularTributos());
    }
}
