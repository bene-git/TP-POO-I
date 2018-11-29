package produtos;

import conexoes.ConexaoSqlite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import static produtos.Inserir.conexaoBanco;

public class Atualizar {
    
    static Scanner sc = new Scanner(System.in);
    
    public static void atuaizar(){
        
        int cont = 0;
        
        Produtos pd = new Produtos();
        
        ConexaoSqlite conexaoBanco = new ConexaoSqlite();
        Recuperar.recuperar();
        PreparedStatement inserir = null;
        
        do{
            cont = 0;
            
            System.out.println("\nInforme o ID do produto para ALTERAR:");
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
            if(cont == 0)
                System.err.println("ID NÃO existe!");
            
        }while(cont == 0);
        
        System.out.println("Informe o novo nome do produto:");
        pd.setNome(sc.nextLine());
        pd.setNome(sc.nextLine());
        
        System.out.println("Informe a nova descrição:");
        pd.setDescricao(sc.nextLine());
        
        do{
            System.out.println("Informe o novo preco do produto:");
            pd.setPreco(sc.nextFloat());
        }while(pd.getPreco()<=0);
        
        do{
            System.out.println("Informe a nova quantidade em estoque:");
            pd.setQuantidade(sc.nextInt());
        }while(pd.getQuantidade()<0);
        
        String sqlatualizar = "UPDATE tbl_produtos"
                + " SET "
                + "nome = ?,"
                + "desc = ?,"
                + " prec = ?,"
                + " quant = ?"
                + " WHERE id = ?";
        try{
            
            inserir = conexaoBanco.inserirTabela(sqlatualizar);
            inserir.setString(1, pd.getNome());
            inserir.setString(2, pd.getDescricao());
            inserir.setFloat(3, pd.getPreco());
            inserir.setInt(4, pd.getQuantidade());
            inserir.setString(5, pd.getId());
            
            inserir.executeUpdate();
            
            System.out.println("\nPRODUTO ATUALIZADO!\n");
            
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
