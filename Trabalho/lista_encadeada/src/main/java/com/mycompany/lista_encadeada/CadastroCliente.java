

package com.mycompany.lista_encadeada;


import javax.swing.JOptionPane;

class Cliente {
    int codigo;
    String nome;
    String dataNascimento;
    String telefone;
    Cliente proximo;
    Cliente anterior;

    public Cliente(int codigo, String nome, String dataNascimento, String telefone) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.proximo = null;
        this.anterior = null;
    }
}

class ListaClientes {
    Cliente inicio;
    Cliente fim;

    public ListaClientes() {
        inicio = null;
        fim = null;
    }

    public void adicionarCliente(int codigo, String nome, String dataNascimento, String telefone) {
        Cliente novoCliente = new Cliente(codigo, nome, dataNascimento, telefone);
        if (inicio == null) {
            inicio = novoCliente;
            fim = novoCliente;
        } else {
            fim.proximo = novoCliente;
            novoCliente.anterior = fim;
            fim = novoCliente;
        }
    }

    public void excluirCliente(int codigo) {
        Cliente atual = inicio;
        while (atual != null) {
            if (atual.codigo == codigo) {
                if (atual == inicio) {
                    inicio = inicio.proximo;
                    if (inicio != null) {
                        inicio.anterior = null;
                    }
                } else if (atual == fim) {
                    fim = fim.anterior;
                    fim.proximo = null;
                } else {
                    atual.anterior.proximo = atual.proximo;
                    atual.proximo.anterior = atual.anterior;
                }
                JOptionPane.showMessageDialog(null, "Cadastro excluído com sucesso:\n" +
                        "Código: " + atual.codigo + "\n" +
                        "Nome: " + atual.nome + "\n" +
                        "Data de Nascimento: " + atual.dataNascimento + "\n" +
                        "Telefone: " + atual.telefone);
                return;
            }
            atual = atual.proximo;
        }
        JOptionPane.showMessageDialog(null, "Cliente com código " + codigo + " não encontrado.");
    }

    public void alterarCliente(int codigo, String novoNome, String novaDataNascimento, String novoTelefone) {
        Cliente atual = inicio;
        while (atual != null) {
            if (atual.codigo == codigo) {
                atual.nome = novoNome;
                atual.dataNascimento = novaDataNascimento;
                atual.telefone = novoTelefone;
                JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso:\n" +
                        "Novo Nome: " + atual.nome + "\n" +
                        "Nova Data de Nascimento: " + atual.dataNascimento + "\n" +
                        "Novo Telefone: " + atual.telefone);
                return;
            }
            atual = atual.proximo;
        }
        JOptionPane.showMessageDialog(null, "Cliente com código " + codigo + " não encontrado.");
    }

    public void exibirCliente(int codigo) {
        Cliente atual = inicio;
        while (atual != null) {
            if (atual.codigo == codigo) {
                JOptionPane.showMessageDialog(null, "Dados do cliente:\n" +
                        "Código: " + atual.codigo + "\n" +
                        "Nome: " + atual.nome + "\n" +
                        "Data de Nascimento: " + atual.dataNascimento + "\n" +
                        "Telefone: " + atual.telefone);
                return;
            }
            atual = atual.proximo;
        }
        JOptionPane.showMessageDialog(null, "Cliente com código " + codigo + " não encontrado.");
    }

    public void exibirTodosClientes() {
        Cliente atual = inicio;
        StringBuilder clientes = new StringBuilder();
        while (atual != null) {
            clientes.append("Código: ").append(atual.codigo).append("\n")
                    .append("Nome: ").append(atual.nome).append("\n")
                    .append("Data de Nascimento: ").append(atual.dataNascimento).append("\n")
                    .append("Telefone: ").append(atual.telefone).append("\n")
                    .append("-----------------------------\n");
            atual = atual.proximo;
        }
        JOptionPane.showMessageDialog(null, clientes.toString());
    }
}

public class CadastroCliente {
    public static void main(String[] args) {
        ListaClientes lista = new ListaClientes();

        while (true) {
            String[] options = {"Adicionar cliente", "Excluir cliente", "Alterar cliente", "Recuperar e exibir cliente", "Exibir todos os clientes", "Sair"};
            int opcao = JOptionPane.showOptionDialog(null, "Menu:", "Sistema de Cadastro de Clientes",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (opcao) {
                case 0:
                    int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do cliente:"));
                    String nome = JOptionPane.showInputDialog("Digite o nome do cliente:");
                    String dataNascimento = JOptionPane.showInputDialog("Digite a data de nascimento do cliente:");
                    String telefone = JOptionPane.showInputDialog("Digite o telefone do cliente:");
                    lista.adicionarCliente(codigo, nome, dataNascimento, telefone);
                    break;
                case 1:
                    int codigoExcluir = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do cliente a ser excluído:"));
                    lista.excluirCliente(codigoExcluir);
                    break;
                case 2:
                    int codigoAlterar = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do cliente a ser alterado:"));
                    String novoNome = JOptionPane.showInputDialog("Digite o novo nome do cliente:");
                    String novaDataNascimento = JOptionPane.showInputDialog("Digite a nova data de nascimento do cliente:");
                    String novoTelefone = JOptionPane.showInputDialog("Digite o novo telefone do cliente:");
                    lista.alterarCliente(codigoAlterar, novoNome, novaDataNascimento, novoTelefone);
                    break;
                case 3:
                    int codigoExibir = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do cliente a ser exibido:"));
                    lista.exibirCliente(codigoExibir);
                    break;
                case 4:
                    lista.exibirTodosClientes();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Saindo do programa...");
                    System.exit(0);
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}
