
package carrinho;

import conexoes.ConexaoSqlite;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FormaPagamento {
    
    static Scanner sc = new Scanner(System.in);
    
    static ConexaoSqlite conexaoBanco = new ConexaoSqlite();
    
    static realizarCompra rc = new realizarCompra();
    
    public static void avista(){
        
        double dinheiro = 0, dind;
        do{
            System.out.print("\nDinheiro Recebido = ");
            dind = sc.nextDouble();

            dinheiro += dind;

            if(dinheiro>=(realizarCompra.vf-realizarCompra.desc)-0.01){
                
                System.out.printf("Troco ao Cliente = %.2f R$\n", dinheiro - (realizarCompra.vf - realizarCompra.desc ));
                
                System.out.println("Venda Feita e Recebida"
                        + "\nParabéns :)\n");
            } else {
                System.out.printf("Faltam = %.2f R$\n", (dinheiro - (realizarCompra.vf - realizarCompra.desc ))*-1);
                System.out.println("Por favor, solicite ao cliente!");

            }
        }while(dinheiro<(realizarCompra.vf-realizarCompra.desc)-0.01);
        
    }
    
    public static void mista(){
        
        String rgcliente;
        double vlrpg;
        
        System.out.print("Informe o valor de entrada: ");
        vlrpg = sc.nextDouble();
        
        System.out.print("Informe o rg do cliente: ");
        rgcliente = sc.nextLine();
        rgcliente = sc.nextLine();
        
        tst = verificaCad(rgcliente);
        
        if(tst==1){
            System.out.println("Cliente já cadastrado!");
            ClienteCad.atualizarClient((realizarCompra.vf - realizarCompra.desc)-vlrpg, rgcliente, salad, parc, (realizarCompra.vf - realizarCompra.desc));
        } else {
            System.out.println("Cadastrando Cliente...");
            CienteCadNot.cadCliente(rgcliente, (realizarCompra.vf - realizarCompra.desc)-vlrpg, (realizarCompra.vf - realizarCompra.desc));
        }
    }
    
    static int tst = 0;
    
    public static void parcelada(){
        
        String rgClient;
        
        System.out.print("Informe o rg do cliente: ");
        rgClient = sc.nextLine();
        
        tst = verificaCad(rgClient);
        
        if(tst==1){
            System.out.println("Cliente já cadastrado!");
            ClienteCad.atualizarClient(realizarCompra.vf - realizarCompra.desc, rgClient, salad, parc,(realizarCompra.vf - realizarCompra.desc));
        } else {
            System.out.println("Cadastrando Cliente...");
            CienteCadNot.cadCliente(rgClient, (realizarCompra.vf - realizarCompra.desc), (realizarCompra.vf - realizarCompra.desc));
        }
    }
    
    static double salad = 0;
    static int parc = 0;
    
    public static int verificaCad(String umRg){
        
        int cont = 0;
        
        ResultSet resultSet = null;
            Statement statement = null;
            String sqlselect = "SELECT * FROM cliente";
            statement = conexaoBanco.criarTabela();
            
            try{
                resultSet = statement.executeQuery(sqlselect);
                while(resultSet.next()){
                    if(resultSet.getString("rg").equals(umRg)){
                        cont = 1;
                        salad = resultSet.getDouble("saldo");
                        parc = resultSet.getInt("qtd");
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
            return cont;
    }
    
}
