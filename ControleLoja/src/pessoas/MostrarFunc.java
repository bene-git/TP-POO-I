
package pessoas;

import conexoes.ConexaoSqlite;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MostrarFunc {
    
    public static void mostrarFunc(){
        
        ConexaoSqlite conexaoBanco = new ConexaoSqlite();
        ResultSet resultSet = null;
        Statement statement = null;
        
        String sqlselect = "SELECT * FROM tbl_funcionario";
        
        statement = conexaoBanco.criarTabela();
        
        try{
            resultSet = statement.executeQuery(sqlselect);
            System.out.println("\n============ FUNCIONÁRIOS CADASTRADOS ============");
            while(resultSet.next()){
                System.out.println("Nome     = "+resultSet.getString("nome"));
                System.out.println("RG       = "+resultSet.getString("rg"));
                System.out.println("Endereço = "+resultSet.getString("endereco"));
                System.out.println("Contato  = "+resultSet.getString("contat"));
                System.out.println("Hs Traba = "+resultSet.getInt("carg"));
                System.out.println("Salário  = "+resultSet.getDouble("salario"));
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
