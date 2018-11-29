package pessoas;

import conexoes.ConexaoSqlite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import produtos.Inserir;

public class InserirFunc {
    
    static int cont = 0;
    
    static ConexaoSqlite conexaoBanco = new ConexaoSqlite();
    static Scanner sc = new Scanner(System.in);
    static Pessoa p = new Pessoa();
    
    public static void insert(){
        
        System.out.println("========== CADASTRO DE FUNCIONÁRIO ===========");
        
        System.out.print("Nome: ");
        p.setNome(sc.nextLine());
        
        do{
            cont = 0;
            
            System.out.print("RG: ");
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
            if(cont != 0)
                System.err.println("RG inválido! RG já existe!");
            
        }while(cont != 0);
        
        
        System.out.print("Endereço: ");
        p.setEndereco(sc.nextLine());
        
        System.out.print("Contato:  ");
        p.setContato(sc.nextLine());
        
        String sqlinsert = "INSERT INTO tbl_funcionario ("
            + "nome,"
            + "rg,"
            + "endereco,"
            + "contat,"
            + "carg,"
            + "salario"
            + ") VALUES(?,?,?,?,?,?)"
            + ";";
        
        PreparedStatement inserir = conexaoBanco.inserirTabela(sqlinsert);
        
        try{
            inserir.setString(1, p.getNome());
            inserir.setString(2, p.getRg());
            inserir.setString(3, p.getEndereco());
            inserir.setString(4, p.getContato());
            inserir.setInt(5, 0);
            inserir.setDouble(6, 0);
            
            int result = inserir.executeUpdate();
            
            if(result == 1){
                System.out.println("\nFuncionário Cadastrado!\n");
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
