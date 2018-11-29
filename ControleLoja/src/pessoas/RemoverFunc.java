/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoas;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import static pessoas.InserirFunc.conexaoBanco;

/**
 *
 * @author STI
 */
public class RemoverFunc {
    
    public static void removerFunc(){
        
        Pessoa p = new Pessoa();
        
        Scanner sc = new Scanner(System.in);
        
        MostrarFunc.mostrarFunc();
        
        int cont = 0;
        
        PreparedStatement remove = null;
        
        do{
            cont = 0;
            System.out.println("Informe o RG do funcionario para REMOVER:");
            p.setRg(sc.nextLine());
            
            ResultSet resultSet = null;
            Statement statement = null;
            String sqlselect = "SELECT * FROM tbl_funcionario";
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
            
                String sqlremove = "DELETE FROM tbl_funcionario"
                        + " WHERE rg = ?;";

                try{

                    remove = conexaoBanco.inserirTabela(sqlremove);
                    remove.setString(1, p.getRg());

                    remove.executeUpdate();

                    System.out.println("\nFuncionário Removido!\n");

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
