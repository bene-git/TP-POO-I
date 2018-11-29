package pessoas;

import conexoes.ConexaoSqlite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class FolhaPagamento {
    
    static Scanner sc = new Scanner(System.in);
    
    static ArrayList<ArrayCarg> arraycarg = new ArrayList();
    
    public static void pagamento(){
        
        ConexaoSqlite conexaoBanco = new ConexaoSqlite();
        ResultSet resultSet = null;
        Statement statement = null;
        PreparedStatement inserir = null;
        
        int chr;
        
        String sqlselect = "SELECT * FROM tbl_funcionario";
        
        statement = conexaoBanco.criarTabela();
        int i = 0;
        
        try{
            resultSet = statement.executeQuery(sqlselect);
            System.out.println("========== HORAS TRABALHADAS POR ==========");
            while(resultSet.next()){
                i++;
                System.out.println("====== Funcionário 0"+i+" ======");
                System.out.println("Nome     = "+resultSet.getString("nome"));
                System.out.println("RG       = "+resultSet.getString("rg"));
                System.out.print("Horas Trabalhadas:  ");
                chr = sc.nextInt(); 
                ArrayCarg ac = new ArrayCarg(chr, resultSet.getString("rg"));
                arraycarg.add(ac);
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
    }
    
    public static void atcarga(){
        
        ConexaoSqlite conexaoBanco = new ConexaoSqlite();
        PreparedStatement inserir = null;
        String sqlatualizar = "UPDATE tbl_funcionario"
                + " SET "
                + "carg = ?"
                + " WHERE rg = ?";
        for(ArrayCarg ac: arraycarg){
            try{

                inserir = conexaoBanco.inserirTabela(sqlatualizar);
                inserir.setInt(1, ac.getCarghr());
                inserir.setString(2, ac.getRgg());

                inserir.executeUpdate();

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
}
