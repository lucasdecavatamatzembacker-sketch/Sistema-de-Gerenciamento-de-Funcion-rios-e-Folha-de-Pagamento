package model;

public abstract class Funcionario implements Identificavel {
    private int codigo;
    private String nome;
    private String cpf;
    private double salarioBase;

    public Funcionario(int codigo, String nome, String cpf, double salarioBase) {
        setCodigo(codigo);
        setNome(nome);
        setCpf(cpf);
        setSalarioBase(salarioBase);
    }

    public abstract double calcularRemuneracao();

    public abstract String getTipo();

    @Override
    public int getCodigo() {
        return codigo;
    }

    private void setCodigo(int codigo) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("O código deve ser maior que zero.");
        }
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }
        this.nome = nome.trim();
    }

    public String getCpf() {
        return cpf;
    }

    private void setCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF é obrigatório.");
        }
        String somenteNumeros = limparCpf(cpf);
        if (!cpfValido(somenteNumeros)) {
            throw new IllegalArgumentException("CPF inválido: " + cpf);
        }
        this.cpf = somenteNumeros;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        if (salarioBase < 0) {
            throw new IllegalArgumentException("O salário-base não pode ser negativo.");
        }
        this.salarioBase = salarioBase;
    }

    public static String limparCpf(String cpf) {
        if (cpf == null) {
            return "";
        }
        return cpf.replace(".", "").replace("-", "").trim();
    }

    private boolean cpfValido(String cpf) {
        if (cpf.length() != 11) {
            return false;
        }
        for (int i = 0; i < cpf.length(); i++) {
            if (!Character.isDigit(cpf.charAt(i))) {
                return false;
            }
        }
        boolean todosIguais = true;
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                todosIguais = false;
                break;
            }
        }
        return !todosIguais;
    }

    public String exibirDados() {
        String dados = "Código: " + codigo + "\n"
                + "Tipo: " + getTipo() + "\n"
                + "Nome: " + nome + "\n"
                + "CPF: " + cpf + "\n"
                + "Salário-base: R$ " + String.format("%.2f", salarioBase) + "\n"
                + "Remuneração: R$ " + String.format("%.2f", calcularRemuneracao());
        return dados;
    }

    @Override
    public String toString() {
        return codigo + " - " + nome + " (" + getTipo() + ") - R$ "
                + String.format("%.2f", calcularRemuneracao());
    }
}
