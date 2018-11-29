
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

public class InserirClient {
    
    static Clientes c = new Clientes();
    
    static int cont = 0;
    
    static ConexaoSqlite conexaoBanco = new ConexaoSqlite();
    static Scanner sc = new Scanner(System.in);
    static Pessoa p = new Pessoa();
    
    public static void insert(){
        
        System.out.println("\n========== CADASTRO DE CLIENTES ===========");
        
        System.out.print("Nome: ");
        p.setNome(sc.nextLine());
        
        do{
            cont = 0;
            
            System.out.print("RG: ");
            p.setRg(sc.nextLine());
            
            ResultSet resultSet = null;
            Statement statement = null;
            String sqlselect = "SELECT * FROM cliente";
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
        
        System.out.print("Divida: ");
        c.setSaldo(sc.nextDouble());
        
        System.out.print("Parcelas: ");
        c.setQtd_parc(sc.nextInt());
        
        if(c.getSaldo()>1000)
            c.setFiel(1);
        else
            c.setFiel(0);
        
        
        String sqlinsert = "INSERT INTO cliente ("
            + "nome,"
            + "rg,"
            + "endereco,"
            + "fone,"
            + "saldo,"
            + "qtd,"
            + "fiel"
            + ") VALUES(?,?,?,?,?,?,?)"
            + ";";
        
        PreparedStatement inserir = conexaoBanco.inserirTabela(sqlinsert);
        
        try{
            
            inserir.setString(1, p.getNome());
            inserir.setString(2, p.getRg());
            inserir.setString(3, p.getEndereco());
            inserir.setString(4, p.getContato());
            inserir.setDouble(5, c.getSaldo());
            inserir.setInt(6, c.getQtd_parc());
            inserir.setInt(7, c.getFiel());
            
            int result = inserir.executeUpdate();
            
            if(result == 1){
                System.out.println("\nCliente Cadastrado!\n");
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
