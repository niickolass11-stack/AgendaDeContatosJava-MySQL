import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.util.List;

public class TelaAgenda {

    static DefaultTableModel modeloPadrao = new DefaultTableModel();

    public static void main(String[] args) {
        
        JFrame janela = new JFrame();

        janela.setTitle("Agenda de contatos");
        janela.setSize(840, 500);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // permite posicionar os elemento manualmente
        janela.setLayout(null);

        // Cria o texto "Nome:"
        JLabel nomeLabel = new JLabel("Nome: ");
        
        JLabel telefoneLabel = new JLabel("Telefone: ");
        
        JLabel emailLabel = new JLabel("E-Mail: ");
        
        // Cria o campo onde o usuario podera digitar o nome 
        JTextField nomeCampo = new JTextField();
        
        JTextField telefoneCampo = new JTextField();
        
        JTextField emailCampo = new JTextField();

        JButton adicionarBotao = new JButton("Adicionar");
        JButton atualizarBotao = new JButton("Atualizar");                
        JButton excluirBotao = new JButton("Excluir");

        // Define a posiçao e o tamanho do texto 
        nomeLabel.setBounds(50, 50, 100, 30); 
        
        telefoneLabel.setBounds(50, 100, 100, 30);
        
        emailLabel.setBounds(50, 150, 100, 30);
        
        // Define a posiçao e o tamanho do campo
        nomeCampo.setBounds(120, 50, 300, 30);
        
        telefoneCampo.setBounds(120, 100, 300, 30);
        
        emailCampo.setBounds(120, 150, 300, 30);

        adicionarBotao.setBounds(120, 200, 300, 30);
        atualizarBotao.setBounds(120, 250, 300, 30);
        excluirBotao.setBounds(120, 300, 300, 30);

        // Adiciona os componentes a janela
        janela.add(nomeLabel);
        janela.add(nomeCampo);
        
        janela.add(telefoneLabel);
        janela.add(telefoneCampo);
        
        janela.add(emailLabel);
        janela.add(emailCampo);
        
        janela.add(adicionarBotao);
        janela.add(atualizarBotao);        
        janela.add(excluirBotao);

        
        JTable tabela = new JTable();

        modeloPadrao.addColumn("ID");
        modeloPadrao.addColumn("Nome");
        modeloPadrao.addColumn("Telefone");
        modeloPadrao.addColumn("Email");
        
        tabela.setModel(modeloPadrao);

        // Coloca a JTable dentro do ScollPane
        JScrollPane scrollPane = new JScrollPane(tabela);

        scrollPane.setBounds(50, 350, 700, 100);

        janela.add(scrollPane);
        
        adicionarBotao.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {

                String nome = nomeCampo.getText();
                String telefone = telefoneCampo.getText();
                String email = emailCampo.getText();

                ContatoDAO.adicionarContato(nome, telefone, email);

                carregarTabela();

            }
        });

      
        excluirBotao.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {

               int linhaSelecionada = tabela.getSelectedRow();

                if (linhaSelecionada == -1) {

                    System.out.println("Selecione uma linha para remover");

                }else {

                int id = (int) tabela.getValueAt(linhaSelecionada, 0);

                ContatoDAO.removerContatos(id);

                carregarTabela();

                }

            }
        
        });

        atualizarBotao.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {

                String nome = nomeCampo.getText();
                String telefone = telefoneCampo.getText();
                String email = emailCampo.getText();

                ContatoDAO.atualizarContato(0, nome, telefone, email);
                

                int linhaSelecionada = tabela.getSelectedRow();

                if (linhaSelecionada == -1) {

                    System.out.println("Selecione uma linha primeiro");
                
                }else{

                int id = (int) tabela.getValueAt(linhaSelecionada, 0);

                ContatoDAO.atualizarContato(id, nome, telefone, email);

                carregarTabela();

                
            }
            
        }
            
            
        });

        carregarTabela();
         
   
        janela.setVisible(true);
    
    }

    
    public static void carregarTabela() {

        modeloPadrao.setRowCount(0);

        List<Contato> contatos = ContatoDAO.listarContatos();

        for (Contato contato : contatos) {

            modeloPadrao.addRow(new Object[] {

                contato.getId(),
                contato.getNome(),
                contato.getTelefone(),
                contato.getEmail()
            });
        
        }
        
    }

}
    
    

