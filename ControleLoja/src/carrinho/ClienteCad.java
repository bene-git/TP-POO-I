package carrinho;

import conexoes.ConexaoSqlite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import pessoas.Clientes;

public class ClienteCad {
    
    static Scanner sc = new Scanner(System.in);
    
    public static void atualizarClient(double saldo, String umRg, double ad, int parc, double salfiel){
        
        int cont = 0;
        
        Clientes c = new Clientes();
        
        realizarCompra rc = new realizarCompra();
        
        ConexaoSqlite conexaoBanco = new ConexaoSqlite();
        
        PreparedStatement inserir = null;
            
        ResultSet resultSet = null;
        Statement statement = null;
        String sqlselect = "SELECT * FROM cliente";
        statement = conexaoBanco.criarTabela();
        
        do{
            System.out.print("Quant. Parcelas: ");
            c.setQtd_parc(sc.nextInt());
            if(c.getQtd_parc()>saldo)
                System.out.println("Nº Parcelas Inválidas!\nValor Da Parcela Muito Baixo!");
        }while(c.getQtd_parc()>saldo);
        
        if(salfiel>1000){
            c.setFiel(1);
        } else {
            c.setFiel(0);
        }
        
        String sqlatualizar = "UPDATE cliente"
                + " SET "
                + "saldo = ?,"
                + "qtd = ?,"
                + "fiel = ?"
                + " WHERE rg = ?";
        
        try{
            
            inserir = conexaoBanco.inserirTabela(sqlatualizar);

            inserir.setDouble(1, saldo+ad);
            inserir.setInt(2, c.getQtd_parc()+parc);
            inserir.setInt(3, c.getFiel());
            inserir.setString(4, umRg);
            
            inserir.executeUpdate();
            
            System.out.println("\nCliente Atualizado!\n");
            
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
