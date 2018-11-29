package carrinho;

import conexoes.ConexaoSqlite;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class atualizarQuantProd {
    
    public static void atuaizar(int qi, String idp){
        
        int cont = 0;
        
        ConexaoSqlite conexaoBanco = new ConexaoSqlite();
        PreparedStatement inserir = null;
        
        String sqlatualizar = "UPDATE tbl_produtos"
                + " SET "
                + " quant = ?"
                + " WHERE id = ?";
        try{
            
            inserir = conexaoBanco.inserirTabela(sqlatualizar);
            inserir.setInt(1, qi);
            inserir.setString(2, idp);
            
            inserir.executeUpdate();
            
        }catch(SQLException e){
            //e.printStackTrace();
        }finally{
            /*try{
                inserir.close();
                conexaoBanco.desconectar();
            }catch(SQLException ex){
                ex.printStackTrace();
            }*/
        }   
    }
}
