# Sistema de Gerenciamento de Funcionários e Folha de Pagamento

Aplicação de console em Java para cadastrar funcionários de três regimes de contratação (CLT, PJ e Comissionado), calcular a remuneração de cada um e gerar o relatório da folha de pagamento.

O projeto aplica os conceitos de Programação Orientada a Objetos: herança, polimorfismo, classes abstratas, interfaces, encapsulamento, sobrecarga de métodos e exceções personalizadas. Também separa o código em camadas (model, repository, service e view).

## Funcionalidades

O menu do sistema oferece as opções abaixo.

| Opção | Funcionalidade |
|:---:|---|
| 1 | Cadastrar funcionário (CLT, PJ ou Comissionado) |
| 2 | Listar funcionários |
| 3 | Buscar funcionário por código |
| 4 | Buscar funcionário por CPF |
| 5 | Buscar funcionário por nome (busca exata ou parcial) |
| 6 | Remover funcionário |
| 7 | Exibir dados completos de um funcionário |
| 8 | Calcular a remuneração de um funcionário |
| 9 | Gerar relatório da folha |
| 10 | Exibir o total da folha de pagamento |
| 0 | Sair |

Ao iniciar, o sistema já carrega três funcionários de exemplo, um de cada tipo, e mostra a remuneração de cada um para demonstrar o polimorfismo.

## Regras de cálculo

| Tipo | Remuneração | Tributos (11%) |
|---|---|---|
| **CLT** | salário-base + benefícios | 11% sobre o salário-base |
| **PJ** | valor por projeto × quantidade de projetos | não se aplica |
| **Comissionado** | salário-base + (total de vendas × percentual de comissão) | 11% sobre a remuneração total |

O total da folha é a soma das remunerações de todos os funcionários cadastrados.

### Exemplo

Os três funcionários carregados ao iniciar o sistema:

| Funcionário | Tipo | Dados | Remuneração | Tributos |
|---|---|---|---:|---:|
| Ana Souza | CLT | salário R$ 3.000 + benefícios R$ 500 | R$ 3.500,00 | R$ 330,00 |
| Bruno Lima | PJ | R$ 2.000 por projeto × 3 projetos | R$ 6.000,00 | — |
| Carla Dias | Comissionado | salário R$ 2.000 + 10% de R$ 10.000 em vendas | R$ 3.000,00 | R$ 330,00 |
| **Total da folha** | | | **R$ 12.500,00** | |

## Validações

O sistema recusa dados inválidos e mostra uma mensagem de erro, sem encerrar a execução:

- código menor ou igual a zero;
- nome vazio;
- CPF vazio, com quantidade de dígitos diferente de 11, com letras ou com todos os dígitos iguais (o CPF pode ser digitado com ou sem pontos e traço);
- salário-base, benefícios, valor por projeto, quantidade de projetos ou total de vendas negativos;
- percentual de comissão fora do intervalo de 0 a 100;
- código ou CPF já cadastrado (`FuncionarioJaCadastradoException`);
- busca ou remoção de funcionário inexistente (`FuncionarioNaoEncontradoException`);
- texto digitado onde se espera um número, ou opção de menu inexistente.

Valores decimais podem ser digitados com vírgula ou ponto (por exemplo, `2500,50` ou `2500.50`).

## Tecnologias

- Java 17
- Maven
- JUnit 5 (testes automatizados)

## Estrutura do projeto

```
src/
├── main/java/
│   ├── Main.java                              # Ponto de entrada: carrega os exemplos e abre o menu
│   ├── exception/
│   │   ├── FuncionarioJaCadastradoException.java
│   │   └── FuncionarioNaoEncontradoException.java
│   ├── model/
│   │   ├── Identificavel.java                 # Interface: getCodigo()
│   │   ├── Tributavel.java                    # Interface: calcularTributos()
│   │   ├── Funcionario.java                   # Classe abstrata base
│   │   ├── FuncionarioCLT.java
│   │   ├── FuncionarioPJ.java
│   │   └── FuncionarioComissionado.java
│   ├── repository/
│   │   └── FuncionarioRepository.java         # Armazena os funcionários em memória (ArrayList)
│   ├── service/
│   │   └── FuncionarioService.java            # Regras de negócio, buscas, relatório e total da folha
│   └── view/
│       └── FuncionarioView.java               # Menu e interação com o usuário pelo console
└── test/java/                                 # Testes JUnit, um arquivo por classe
```

### Camadas

```
View  →  Service  →  Repository  →  Model
```

- **Model**: as entidades e suas próprias validações.
- **Repository**: guarda a lista de funcionários e faz as buscas.
- **Service**: aplica as regras de negócio (por exemplo, impedir código ou CPF duplicado) e lança as exceções personalizadas.
- **View**: lê as entradas do usuário, chama o service e trata os erros.

## Conceitos de POO aplicados

| Conceito | Onde aparece |
|---|---|
| **Classe abstrata** | `Funcionario` declara `calcularRemuneracao()` e `getTipo()` como abstratos |
| **Herança** | `FuncionarioCLT`, `FuncionarioPJ` e `FuncionarioComissionado` estendem `Funcionario` |
| **Polimorfismo** | Cada tipo implementa `calcularRemuneracao()` à sua maneira; o service soma a folha sem saber o tipo de cada funcionário |
| **Interfaces** | `Identificavel` (implementada por todos) e `Tributavel` (só CLT e Comissionado) |
| **Encapsulamento** | Atributos `private`, com validação nos setters |
| **Sobrecarga** | `FuncionarioService.buscarFuncionario(...)` por código (`int`), por CPF (`String`) ou por nome (`String, boolean`) |
| **Sobrescrita** | `exibirDados()` e `toString()` redefinidos nas subclasses |
| **Exceções personalizadas** | `FuncionarioJaCadastradoException` e `FuncionarioNaoEncontradoException` |

## Como executar

### Pré-requisitos

- JDK 17 ou superior
- Maven 3.8 ou superior (opcional, se você usar uma IDE)

Para conferir as versões instaladas:

```bash
java -version
mvn -version
```

### Pela IDE (IntelliJ IDEA, Eclipse ou VS Code)

1. Abra a pasta do projeto. A IDE reconhece o `pom.xml` como projeto Maven.
2. Aguarde o download das dependências.
3. Execute a classe `Main` (`src/main/java/Main.java`).

### Pelo terminal, com Maven

```bash
# Compilar
mvn compile

# Executar
java -cp target/classes Main
```

### Pelo terminal, sem Maven

```bash
# Linux / macOS / Git Bash
javac -encoding UTF-8 -d out $(find src/main/java -name "*.java")
java -cp out Main
```

```powershell
# Windows (PowerShell)
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse src\main\java\*.java).FullName
java -cp out Main
```

> Se os acentos aparecerem trocados no console do Windows, rode `chcp 65001` antes de executar o programa.

## Testes

O projeto tem 57 testes automatizados com JUnit 5, cobrindo modelos, exceções, repositório, service e view.

```bash
mvn test
```

Resultado esperado:

```
Tests run: 57, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## Exemplo de uso

```
===== SISTEMA DE FUNCIONÁRIOS =====
1 - Cadastrar funcionário
2 - Listar funcionários
...
0 - Sair
Escolha uma opção: 1
Tipo de funcionário: 1 - CLT | 2 - PJ | 3 - Comissionado
Tipo: 1
Código: 4
Nome: Diego Reis
CPF: 529.982.247-25
Salário-base: 2500
Benefícios: 300
Funcionário cadastrado com sucesso!

Escolha uma opção: 9
===== RELATÓRIO DE FUNCIONÁRIOS =====
1 | Ana Souza | CLT | Remuneração: R$ 3500,00 | Tributos: R$ 330,00
2 | Bruno Lima | PJ | Remuneração: R$ 6000,00
3 | Carla Dias | Comissionado | Remuneração: R$ 3000,00 | Tributos: R$ 330,00
4 | Diego Reis | CLT | Remuneração: R$ 2800,00 | Tributos: R$ 275,00
Total de funcionários: 4
Total da folha: R$ 15300,00
```

## Limitações

- Os dados ficam só em memória: ao encerrar o programa, os cadastros feitos são perdidos.
- A validação de CPF confere o formato (11 dígitos, não todos iguais), mas não calcula os dígitos verificadores.
