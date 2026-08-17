import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class ContatoDAO {

    

    public static void adicionarContato(String nome, String telefone, String email) {
        String sql = "INSERT INTO contatos (nome, telefone, email) VALUES (?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
        PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setString (1, nome);
            stmt.setString (2, telefone);
            stmt.setString(3,  email);

            stmt.executeUpdate();
            System.out.println("Contato adicionado ao banco com sucesso");
        
        } catch (SQLException e) {
            
            System.out.println("Erro ao adicionar contato");
            e.printStackTrace();
        }


    }

    public static List<Contato> listarContatos(){
        
        List<Contato> contatos = new ArrayList<>();

        String sql = "SELECT id, nome, telefone, email FROM contatos";

        try (Connection conexao = Conexao.conectar();
        
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {

                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                String telefone = resultado.getString("telefone");
                String email = resultado.getString("email");

                // System.out.println("ID: " + id + " | Nome: " + nome + " | Telefone: " + telefone + " | E-mail: " + email);

                Contato contato = new Contato();

                contato.setId(id);
                contato.setNome(nome);
                contato.setTelefone(telefone);
                contato.setEmail(email);

                contatos.add(contato);
            
            }
            
        
        } catch (SQLException e ) {

            System.out.println("Erro ao listar contatos");
            e.printStackTrace();
        }

        return contatos;
    }

    public static void removerContatos(int id){

        String sql = "DELETE FROM contatos WHERE id = ?";

        try (Connection conexao = Conexao.conectar();

        PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {

                System.out.println("Contato removido com sucesso");

            }else {

                System.out.println("Nenhum contato encontrado com esse ID");
            }
        
        } catch (SQLException e) {

            System.out.println("Erro ao remover contato");
            e.printStackTrace();
        }


    }

    public static void atualizarContato(int id, String nome, String telefone, String email){

        String sql = """
                
                UPDATE contatos
                SET nome = ?, telefone = ?, email = ?
                WHERE id = ?
                
                """;
        
        try (Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, telefone);
            stmt.setString(3, email);
            stmt.setInt(4, id);

            int linhasAfetadas = stmt.executeUpdate();

            if(linhasAfetadas > 0){

                System.out.println("Contato atualizado com sucesso");
            }else{

                System.out.println("Nenhum contato encontrado com esse ID");
            }
        
        } catch (SQLException e) {
            
            System.out.println("Erro ao atualizar contato");
            e.printStackTrace();
        }

    }
    
}
