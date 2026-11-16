import java.util.Scanner;

import exception.FuncionarioJaCadastradoException;
import model.Funcionario;
import model.FuncionarioCLT;
import model.FuncionarioComissionado;
import model.FuncionarioPJ;
import repository.FuncionarioRepository;
import service.FuncionarioService;
import view.FuncionarioView;

public class Main {
    public static void main(String[] args) {
        FuncionarioRepository repository = new FuncionarioRepository();
        FuncionarioService service = new FuncionarioService(repository);

        Funcionario funcionario1 = new FuncionarioCLT(1, "Ana Souza", "123.456.789-09", 3000, 500);
        Funcionario funcionario2 = new FuncionarioPJ(2, "Bruno Lima", "987.654.321-00", 2000, 3);
        Funcionario funcionario3 = new FuncionarioComissionado(3, "Carla Dias", "111.222.333-44", 2000, 10, 10000);

        System.out.println("Exemplo de polimorfismo:");
        System.out.println(funcionario1.getNome() + " (CLT): " + funcionario1.calcularRemuneracao());
        System.out.println(funcionario2.getNome() + " (PJ): " + funcionario2.calcularRemuneracao());
        System.out.println(funcionario3.getNome() + " (Comissionado): " + funcionario3.calcularRemuneracao());

        try {
            service.cadastrar(funcionario1);
            service.cadastrar(funcionario2);
            service.cadastrar(funcionario3);
        } catch (FuncionarioJaCadastradoException e) {
            System.out.println("Erro ao cadastrar exemplos: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        FuncionarioView view = new FuncionarioView(service, scanner);
        view.iniciar();
        scanner.close();
    }
}
