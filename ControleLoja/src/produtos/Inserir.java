package produtos;

import conexoes.ConexaoSqlite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Inserir{
    
    static int cont = 0;
    
    static ConexaoSqlite conexaoBanco = new ConexaoSqlite();
    static Scanner sc = new Scanner(System.in);
    static Produtos pd = new Produtos();
    
    public static void inserirProd(){
        
        do{
            cont = 0;
            System.out.println("Informe o ID do produto:");
            pd.setId(sc.next());
            
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
            if(cont != 0)
                System.err.println("ID já existe!");
        }while(cont != 0);
        
        System.out.println("Informe o NOME do produto:");
        pd.setNome(sc.nextLine());
        pd.setNome(sc.nextLine());
        
        System.out.println("Descrição do Produto:");
        pd.setDescricao(sc.nextLine());
        
        do{
            System.out.println("Informe o PREÇO do produto:");
            pd.setPreco(sc.nextFloat());
        }while(pd.getPreco()<=0);
        
        do{
            System.out.println("Informe a QUANTIDADE do produto:");
            pd.setQuantidade(sc.nextInt());
        }while(pd.getQuantidade()<0);
        
        String sqlinsert = "INSERT INTO tbl_produtos ("
            + "id,"
            + "nome,"
            + "desc,"
            + "prec,"
            + "quant"
            + ") VALUES(?,?,?,?,?)"
            + ";";
        
        PreparedStatement inserir = conexaoBanco.inserirTabela(sqlinsert);

        try{
            inserir.setString(1, pd.getId());
            inserir.setString(2, pd.getNome());
            inserir.setString(3, pd.getDescricao());
            inserir.setFloat(4, pd.getPreco());
            inserir.setInt(5, pd.getQuantidade());
            
            
            int result = inserir.executeUpdate();
            
            if(result == 1){
                System.out.println("\nProduto Inserido!\n");
            } 
        }catch(SQLException e){
            System.out.println("ERRO!");
        } finally{
            if(inserir != null){
                try {
                    inserir.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Inserir.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            conexaoBanco.desconectar();
        }
    }
}
