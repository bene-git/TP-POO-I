
package carrinho;

import conexoes.ConexaoSqlite;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import pessoas.Clientes;
import pessoas.Pessoa;
import produtos.Inserir;

public class CienteCadNot {
    
    static ConexaoSqlite conexaoBanco = new ConexaoSqlite();
    static Pessoa p = new Pessoa();
    static Clientes c = new Clientes();
    
    public static void cadCliente(String rg, double saldo, double salfiel){
        
        System.out.println("\n======= CADASTRANDO O CLIENTE =======");
        Scanner sc = new Scanner(System.in);
        System.out.print("Nome: ");
        p.setNome(sc.nextLine()); 
        p.setNome(sc.nextLine()); 
        
        System.out.print("Endereço: ");
        p.setEndereco(sc.nextLine());
        
        System.out.print("Contato:  ");
        p.setContato(sc.nextLine());
        
        System.out.print("Parcelas: ");
        c.setQtd_parc(sc.nextInt());
        
        if(salfiel>1000)
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
            inserir.setString(2, rg);
            inserir.setString(3, p.getEndereco());
            inserir.setString(4, p.getContato());
            inserir.setDouble(5, saldo);
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
