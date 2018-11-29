
package pessoas;

import conexoes.ConexaoSqlite;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MostrarCliente {
    
    static ConexaoSqlite conexaoBanco = new ConexaoSqlite();
    static ResultSet resultSet = null;
    static Statement statement = null;
    
    public static void mostrarClient(){
        
        ConexaoSqlite conexaoBanco = new ConexaoSqlite();
        ResultSet resultSet = null;
        Statement statement = null;
        
        String sqlselect = "SELECT * FROM cliente";
        
        statement = conexaoBanco.criarTabela();
        int cnt;
        try{
            resultSet = statement.executeQuery(sqlselect);
            System.out.println("\n============== CLIENTES CADASTRADOS ==============");
            while(resultSet.next()){
                System.out.println("Nome     = "+resultSet.getString("nome"));
                System.out.println("RG       = "+resultSet.getString("rg"));
                System.out.println("Endereço = "+resultSet.getString("endereco"));
                System.out.println("Contato  = "+resultSet.getString("fone"));
                System.out.println("Divida   = "+resultSet.getDouble("saldo"));
                System.out.println("Parcelas = "+resultSet.getInt("qtd"));
                cnt = (int) resultSet.getDouble("saldo");
                if(cnt>1000){
                    System.out.println("Fidelidad = True");
                } else {
                    System.out.println("Fidelidad = False");
                }
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
