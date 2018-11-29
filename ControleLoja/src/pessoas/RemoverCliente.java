
package pessoas;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import static pessoas.InserirFunc.conexaoBanco;

public class RemoverCliente {
    
    public static void removerClient(){
        
        Pessoa p = new Pessoa();
        Scanner sc = new Scanner(System.in);
        
        MostrarCliente.mostrarClient();
  
        int cont = 0;
        
        PreparedStatement remove = null;
        
        do{
            cont = 0;
            System.out.println("Informe o RG do cliente para REMOVER:");
            p.setRg(sc.nextLine());
            
            ResultSet resultSet = null;
            Statement statement = null;
            String sqlselect = "SELECT * FROM cliente";
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
            
            if(cont != 0){
            
                String sqlremove = "DELETE FROM cliente"
                        + " WHERE rg = ?;";

                try{

                    remove = conexaoBanco.inserirTabela(sqlremove);
                    remove.setString(1, p.getRg());

                    remove.executeUpdate();

                    System.out.println("\nCliente Removido!\n");

                }catch(SQLException e){
                    e.printStackTrace();
                } finally{
                    try{
                        remove.close();
                        conexaoBanco.desconectar();
                    }catch(SQLException ex){
                        ex.printStackTrace();
                    }
                }
            } else {
                System.err.println("RG NÃO existe!");
            }
        }while(cont == 0);
    }
}
