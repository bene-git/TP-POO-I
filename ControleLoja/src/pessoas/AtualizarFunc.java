
package pessoas;

import conexoes.ConexaoSqlite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AtualizarFunc {
    
    static Scanner sc = new Scanner(System.in);
    
    public static void atuaizarFunc(){
        
        int cont = 0;
        
        Pessoa p = new Pessoa();
        
        ConexaoSqlite conexaoBanco = new ConexaoSqlite();
        MostrarFunc.mostrarFunc();
        PreparedStatement inserir = null;
        
        do{
            cont = 0;
            
            System.out.println("\nInforme o RG do funcionário para ALTERAR:");
            p.setRg(sc.nextLine());
            
            ResultSet resultSet = null;
            Statement statement = null;
            String sqlselect = "SELECT * FROM tbl_funcionario";
            statement = conexaoBanco.criarTabela();

            try{
                resultSet = statement.executeQuery(sqlselect);
                while(resultSet.next()){
                    if(resultSet.getString("rg").equals(p.getRg())){
                        cont++;
                    }                
                }
            }catch(SQLException e){
                
            }finally{
                try{
                    resultSet.close();
                    statement.close();
                    conexaoBanco.desconectar();
                }catch(SQLException ex){
                    
                }
            }
            if(cont == 0)
                System.err.println("RG NÃO existe!");
            
        }while(cont == 0);
        
        System.out.print("Novo Nome: ");
        p.setNome(sc.nextLine());
        
         System.out.print("Novo Endereço: ");
        p.setEndereco(sc.nextLine());
        
        System.out.print("Novo Contato:  ");
        p.setContato(sc.nextLine());
        
         String sqlatualizar = "UPDATE tbl_funcionario"
                + " SET "
                + "nome = ?,"
                + " endereco = ?,"
                + " contat = ?"
                + " WHERE rg = ?";
        try{
            
            inserir = conexaoBanco.inserirTabela(sqlatualizar);
            inserir.setString(1, p.getNome());
            inserir.setString(2, p.getEndereco());
            inserir.setString(3, p.getContato());
            inserir.setString(4, p.getRg());
            
            inserir.executeUpdate();
            
            System.out.println("\nFUNCIONÁRIO ATUALIZADO!\n");
            
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                inserir.close();
                conexaoBanco.desconectar();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }   
    }
}
