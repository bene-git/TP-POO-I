package produtos;

import conexoes.ConexaoSqlite;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Recuperar {
    
    static Produtos pd = new Produtos();
    
    public static void recuperar(){
        
        ConexaoSqlite conexaoBanco = new ConexaoSqlite();
        ResultSet resultSet = null;
        Statement statement = null;
        
        String sqlselect = "SELECT * FROM tbl_produtos";
        
        statement = conexaoBanco.criarTabela();
        
        try{
            resultSet = statement.executeQuery(sqlselect);
            System.out.println("============== PRODUTOS CADASTRADOS ==============");
            while(resultSet.next()){
                System.out.println("ID do Produto    = "+resultSet.getString("id"));
                System.out.println("Nome do Produto  = "+resultSet.getString("nome"));
                System.out.println("Descrição Prod   = "+resultSet.getString("desc"));
                System.out.println("Preço do Produto = "+resultSet.getFloat("prec")+" R$");
                System.out.println("Quant no Estoque = "+resultSet.getInt("quant"));
                System.out.println("==================================================");
            }
        }catch(SQLException e){
            System.out.println("ERRO! ao mostrar :(");
        }finally{
            try{
                resultSet.close();
                statement.close();
                conexaoBanco.desconectar();
            }catch(SQLException ex){
                System.out.println("ERRO de Fechamento!");
            }
        }
        System.out.println("");
    }
}