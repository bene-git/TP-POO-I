
package pessoas;

import conexoes.ConexaoSqlite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AtualizarCliente {
    
    static Scanner sc = new Scanner(System.in);
    
    public static void atualizarClient(){
        
        int cont = 0;
        
        Clientes c = new Clientes();
        
        ConexaoSqlite conexaoBanco = new ConexaoSqlite();
        MostrarCliente.mostrarClient();
        PreparedStatement inserir = null;
        
        do{
            cont = 0;
            
            System.out.println("\nInforme o RG do cliente para ALTERAR:");
            c.setRg(sc.nextLine());
            
            ResultSet resultSet = null;
            Statement statement = null;
            String sqlselect = "SELECT * FROM cliente";
            statement = conexaoBanco.criarTabela();

            try{
                resultSet = statement.executeQuery(sqlselect);
                while(resultSet.next()){
                    if(resultSet.getString("rg").equals(c.getRg())){
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
        
        System.out.print("Novo Nome: ");
        c.setNome(sc.nextLine());
        
         System.out.print("Novo Endereço: ");
        c.setEndereco(sc.nextLine());
        
        System.out.print("Novo Contato:  ");
        c.setContato(sc.nextLine());
        
        System.out.print("Novo Valor Divida: ");
        c.setSaldo(sc.nextDouble());
        
        System.out.print("Nova Quant. Parcelas: ");
        c.setQtd_parc(sc.nextInt());
        
        if(c.getSaldo()>1000){
            c.setFiel(1);
        } else {
            c.setFiel(0);
        }
        
        String sqlatualizar = "UPDATE cliente"
                + " SET "
                + "nome = ?,"
                + " endereco = ?,"
                + " fone = ?,"
                + "saldo = ?,"
                + "qtd = ?,"
                + "fiel = ?"
                + " WHERE rg = ?";
        
        try{
            
            inserir = conexaoBanco.inserirTabela(sqlatualizar);
            inserir.setString(1, c.getNome());
            inserir.setString(2, c.getEndereco());
            inserir.setString(3, c.getContato());
            inserir.setDouble(4, c.getSaldo());
            inserir.setInt(5, c.getQtd_parc());
            inserir.setInt(6, c.getFiel());
            inserir.setString(7, c.getRg());
            
            inserir.executeUpdate();
            
            System.out.println("\nCLIENTE ATUALIZADO!\n");
            
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
