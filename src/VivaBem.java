import java.util.Scanner;

public class VivaBem {
    static RepositorioCadastroArray rep = new RepositorioCadastroArray(10);

    public static void main(String[] args) {
        CadastroVivaBem();
    }

    private static void CadastroVivaBem() {
        int op = 9;

        while (op != 0) {
            System.out.println("\n\tFarmácia Viva Bem");
            System.out.println(
                    "\n\t| 1 Cadastrar\n\t| 2 Consultar cadastro\n\t| 3 Atualizar dados cadastrais\n\t| 4 Remover cadastro\n\t| 0 Para firnalizar o programa");
            Scanner sc = new Scanner(System.in);
            op = sc.nextInt();

            switch (op) {
                case 1:
                    InserirCadastro();
                    break;
                case 2:
                    ConsultarCadastro();
                    break;
                case 3:
                    AtualizarCadastro();
                    break;
                case 4:
                    RemoverCadastro();
                    break;
                case 0:
                    System.out.println("Programa encerrado!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nOpção inválida, tente novamente.\n");
                    break;
            }
        }
    }

    private static void InserirCadastro() {
        Scanner sc = new Scanner(System.in);
        System.out.println("| Cadastro:\n");
        System.out.println("Digite o CPF (somente números):");
        String cpf = sc.next();

        System.out.println("Digite o Nome:");
        String nome = sc.next();

        System.out.println("Digite o Telefone:");
        String telefone = sc.next();

        Cadastrar c1 = new Cadastrar(telefone, nome, cpf);
        rep.inserir(c1);

        System.out.println("\n0 Voltar");
        Scanner scan = new Scanner(System.in);
        int op = scan.nextInt();

        switch (op) {
            case 0:
                CadastroVivaBem();
                break;
            default:
                System.out.println("Opção inválida!");
                break;

        }
    }

    private static void ConsultarCadastro() {
        System.out.println("| Consultar:\n");
        System.out.println("Digite o CPF Cadastrado: ");
        Scanner sc = new Scanner(System.in);

        String cpf = sc.next();
        String CpfCadastrado = "" + cpf;

        Cadastrar Consultar = rep.procurar(CpfCadastrado);

        if (Consultar == null) {
            System.out.println("\nCPF não cadastrado.");
        } else {
            System.out.println("CPF: " + Consultar.getCpf());
            System.out.println("Nome: " + Consultar.getNome());
            System.out.println("Telefone: " + Consultar.getTelefone());
        }

        System.out.println("\n0 Voltar ");
        Scanner scan = new Scanner(System.in);
        int op = scan.nextInt();

        switch (op) {
            case 0:
                CadastroVivaBem();
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private static void AtualizarCadastro() {
        System.out.println("| Atualizar Dados Cadastrados\n");
        System.out.println("Digite o CPF cadastrado: ");
        Scanner sc = new Scanner(System.in);

        String cpf = sc.next();

        Cadastrar c = new Cadastrar(null, null, cpf);
        rep.atualizar(c);

        if (rep.atualizar(c) == false) {
            System.out.println("\nImpossível atualizar, CPF não cadastrado.");
        } else {
            System.out.println("Nome: ");
            String nome = sc.next();

            System.out.println("Telefone:");
            String telefone = sc.next();

            Cadastrar c1 = new Cadastrar(telefone, nome, cpf);
            rep.atualizar(c1);
            System.out.println("\nCadastro atualizado.");
        }
        System.out.println("\n0 Voltar");
        Scanner scan = new Scanner(System.in);
        int op = scan.nextInt();

        switch (op) {
            case 0:
                CadastroVivaBem();
                break;
            default:
                System.out.println("\nOpção inválida, tente novamente.\n");
                break;
        }
    }

    private static void RemoverCadastro() {
        System.out.println("| Remover Cadastro\n");
        System.out.println("Digite o CPF (somente números): ");
        Scanner sc = new Scanner(System.in);

        String cpf = sc.next();

        String CpfCadastrado = "" + cpf;
        rep.remover(CpfCadastrado);

        System.out.println("\n0 Voltar");
        Scanner scan = new Scanner(System.in);
        int opcao = scan.nextInt();

        switch (opcao) {
            case 0:
                CadastroVivaBem();
                break;
            default:
                System.out.println("\nOpção inválida, tente novamente.\n");
                break;
        }
    }
}

class Cadastrar {
    private String telefone;
    private String nome;
    private String cpf;

    public Cadastrar(String telefone, String nome, String cpf) {
        this.telefone = telefone;
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}

class RepositorioCadastroArray {
    private Cadastrar[] Cadastrar;
    private int indice;

    public RepositorioCadastroArray(int tamanho) {
        Cadastrar = new Cadastrar[tamanho];
        indice = 0;
    }

    private int procurarIndice(String cpf) {
        int i = 0;
        boolean achou;
        for (achou = false; !achou && i < indice;) {
            if (Cadastrar[i].getCpf().equals(cpf)) {
                achou = true;
            } else {
                i++;
            }
        }
        if (!achou)
            i = -1;
        return i;
    }

    public Cadastrar procurar(String cpf) {
        Cadastrar c = null;
        int i = procurarIndice(cpf);
        if (i == -1) {
            return null;
        } else {
            c = Cadastrar[i];
            return c;
        }
    }

    public boolean inserir(Cadastrar c) {
        if (indice < Cadastrar.length) {
            if (c != null) {
                if (procurar(c.getCpf()) == null) {
                    Cadastrar[indice] = c;
                    indice = indice + 1;
                    System.out.println("Cadastro efetuado com sucesso.");

                    return true;
                } else
                    System.out.println("\nCPF já cadastrado.");

            } else {
                System.out.println("\nCadastro inválido.");
            }
        } else
            System.out.println("\nRepositório cheio!!");
        return false;
    }

    public boolean atualizar(Cadastrar c) {
        int i = procurarIndice(c.getCpf());
        if (i != -1) {
            Cadastrar[i] = c;
            return true;
        } else {
            return false;
        }
    }

    public boolean remover(String cpf) {
        int i = procurarIndice(cpf);
        if (i != -1) {
            indice = indice - 1;
            Cadastrar[i] = Cadastrar[indice];
            Cadastrar[indice] = null;
            System.out.println("\nCadastro removido do sistema.");
            return true;
        } else {
            System.out.println("\nImpossível remover, CPF cadastrado.");
            return false;
        }
    }
}