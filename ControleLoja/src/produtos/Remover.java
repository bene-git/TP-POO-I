package produtos;

import java.util.Scanner;
import conexoes.ConexaoSqlite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static produtos.Inserir.conexaoBanco;

public class Remover {
    
    static ConexaoSqlite conexaoBanco = new ConexaoSqlite();
    static Scanner sc = new Scanner(System.in);
    static Produtos pd = new Produtos();
    
    public static void remover(){
        
        Recuperar.recuperar();
        
        int cont = 0;
        
        PreparedStatement remove = null;
        
        do{
            cont = 0;
            System.out.println("Informe o ID do produto para REMOVER:");
            pd.setId(sc.nextLine());
            
            ResultSet resultSet = null;
            Statement statement = null;
            String sqlselect = "SELECT * FROM tbl_produtos";
            statement = conexaoBanco.criarTabela();

            try{
                resultSet = statement.executeQuery(sqlselect);
                while(resultSet.next()){
                    if(resultSet.getString("id").equals(pd.getId())){
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
            
                String sqlremove = "DELETE FROM tbl_produtos"
                        + " WHERE id = ?;";

                try{

                    remove = conexaoBanco.inserirTabela(sqlremove);
                    remove.setString(1, pd.getId());

                    remove.executeUpdate();

                    System.out.println("\nProduto Removido!\n");

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
                System.err.println("ID NÃO existe!");
            }
        }while(cont == 0);
    }
}