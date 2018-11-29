
package pessoas;

import conexoes.ConexaoSqlite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ContraCheque {
    
    public static void gerar(){
        
        Scanner sc = new Scanner(System.in);
        
        String rgcli = " ";        
        int hrtrab;       
        int cont = 0;
        
        ConexaoSqlite conexaoBanco = new ConexaoSqlite();
        MostrarFunc.mostrarFunc();
        PreparedStatement inserir = null;
        
        System.out.println("\n============= GERANDO CONTRA CHEQUE =============");
        
        do{
            cont = 0;
            
            System.out.print("Informe o RG do funcionário: ");
            rgcli = sc.nextLine();
            
            ResultSet resultSet = null;
            Statement statement = null;
            String sqlselect = "SELECT * FROM tbl_funcionario";
            statement = conexaoBanco.criarTabela();

            try{
                resultSet = statement.executeQuery(sqlselect);
                while(resultSet.next()){
                    if(resultSet.getString("rg").equals(rgcli)){
                        System.out.println("Nome = "+resultSet.getString("nome"));
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
        
        System.out.print("Informe as horas trabalhas: ");
        hrtrab = sc.nextInt();
        
        String sqlatualizar = "UPDATE tbl_funcionario"
                + " SET "
                + "carg = ?,"
                + "salario = ?"
                + " WHERE rg = ?";
        try{
            
            inserir = conexaoBanco.inserirTabela(sqlatualizar);
            inserir.setInt(1, hrtrab);
            inserir.setDouble(2, hrtrab*15);
            inserir.setString(3, rgcli);
            
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
        
        mostrarcontra(rgcli);
        
    }
    
    
    public static void mostrarcontra(String rgg){
        
        ConexaoSqlite conexaoBanco = new ConexaoSqlite();
        
        PreparedStatement inserir = null;
        ResultSet resultSet = null;
        Statement statement = null;
        String sqlselect = "SELECT * FROM tbl_funcionario";
        statement = conexaoBanco.criarTabela();
        
        System.out.println("\n*************** Contra Cheque ***************\n");
        
        try{
            resultSet = statement.executeQuery(sqlselect);
            while(resultSet.next()){
                if(resultSet.getString("rg").equals(rgg)){
                    System.out.println("Nome     = "+resultSet.getString("nome"));
                    System.out.println("RG       = "+resultSet.getString("rg"));
                    System.out.println("Hs Trab. = "+resultSet.getInt("carg"));
                    System.out.println("Salário  = "+resultSet.getInt("carg")*15+" R$");
                    
                    Date dataa = new Date();
                    SimpleDateFormat forms = new SimpleDateFormat("d/M/y  -  H:m:s");
                    String dff = forms.format(dataa);

                    System.out.println("\nData / Hora - "+dff);
                    System.out.println("\n*********************************************");
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
    }
    
}
