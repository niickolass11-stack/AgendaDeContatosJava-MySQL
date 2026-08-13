import java.util.Scanner;



public class AgendaDeContatos {

    public static void main(String[] args) {
    
    Scanner scanner = new Scanner(System.in);
    boolean estaAtivo = true;
    
    while(estaAtivo){
    
        System.out.println("Escolha uma opção");
        System.out.println("1 - Adicionar contato");
        System.out.println("2 - Remover Contato");
        System.out.println("3 - Ver lista");
        System.out.println("4 - Atualizar contato");
        System.out.println("X - Sair");

        String opcao = scanner.next().toUpperCase();
        scanner.nextLine();

        switch (opcao) {
            case "1":
                
            System.out.println("Informe o nome do contato que deseja adicionar: ");
            String nome = scanner.nextLine();

            System.out.println("Informe o telefone do contato que deseja adicionar: ");
            String telefone = scanner.nextLine();
            
            System.out.println("Informe o E-mail que deseja adicionar: ");
            String email = scanner.nextLine();
            
            ContatoDAO.adicionarContato(nome, telefone, email);
                
                break;
            case "2":
                ContatoDAO.listarContatos();
                
                System.out.println("Informe o ID do contato que deseja remover: ");
                int removerContato = scanner.nextInt();
                
                ContatoDAO.removerContatos(removerContato);
                
                break;
            
            case "3":
                
                ContatoDAO.listarContatos();

                break;
            
            
            case "4":

                ContatoDAO.listarContatos();
                
                System.out.println("Informe o ID do contato que deseja atualizar: ");
                int id = scanner.nextInt();

                scanner.nextLine();

                System.out.println("Informe o novo nome do contato: ");
                String novoNome = scanner.nextLine();

                System.out.println("Informe o novo telefone: ");
                String novoTelefone = scanner.nextLine();

                System.out.println("Informe o novo E-mail: ");
                String novoEmail = scanner.nextLine();

                ContatoDAO.atualizarContato(id, novoNome, novoTelefone, novoEmail);

                break;
            
            case "X":
                
                System.out.println("Encerrando...");
                estaAtivo = false;
                break;

        
            default:
                System.out.println("ERROR - Opção invalida");
                break;
        }

    }


    scanner.close();

}

}


    

