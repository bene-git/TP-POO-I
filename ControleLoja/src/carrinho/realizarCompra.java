
package carrinho;

import conexoes.ConexaoSqlite;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class realizarCompra {
    
    static Scanner sc = new Scanner(System.in);
    
    static ArrayList<CarrinhoCompra> carrim = new ArrayList();
    
    static String id, nome;
    static int quant;
    static double prit;
    static double vf = 0;
    static double desc = 0;
            
    static int cq = 0;
    
    public static void comprar(){
        
        int cnt = 0;
        int opn = 0;
     
        String op;
        ConexaoSqlite conexaoBanco = new ConexaoSqlite();
        ResultSet resultSet = null;
        Statement statement = null;
        
        String sqlselect = "SELECT * FROM tbl_produtos";
        statement = conexaoBanco.criarTabela();
        
        produtos.Recuperar.recuperar();
        
        System.out.println("\n======== REALIZAR COMPRA ========");
        
        do{
            do{

                vf = 0;
                desc = 0;
                
                cnt = 0;

                System.out.print("Informe o id do produto: ");
                id = sc.next();

                try{
                    resultSet = statement.executeQuery(sqlselect);

                    while(resultSet.next()){
                        if(resultSet.getString("id").equals(id)){
                            System.out.println("Nome do Produto  = "+resultSet.getString("nome"));
                            nome = resultSet.getString("nome");
                            prit = resultSet.getDouble("prec");
                            cq = resultSet.getInt("quant");
                            cnt++;
                        }
                    }
                }catch(SQLException e){
                    System.out.println("ERRO! ao mostrar :(");
                }finally{
                    /*try{
                        resultSet.close();
                        statement.close();
                        conexaoBanco.desconectar();
                    }catch(SQLException ex){
                        System.out.println("ERRO de Fechamento!");
                    }*/
                }
                if(cnt == 0)
                    System.err.println("ID não existe!");
            }while(cnt == 0);
            
            
            
            do{
                
                System.out.print("Informe a quantidade: ");
                quant = sc.nextInt();
                if(cq==0){
                    System.out.println("Produto Indisponível");
                    System.out.println("Informe [0] para voltar!");
                } else{
                    if(quant>cq)
                        System.out.println("Quantidade Excedida!");
                }
                
            }while(quant>cq);

            CarrinhoCompra cc = new CarrinhoCompra(id, nome, quant, prit);

            carrim.add(cc);

            vf = vf + (quant*prit);
            
            atualizarQuantProd.atuaizar(cq-quant, id);
            
            System.out.println("Continuar Comprando:"
                    + "\n[S] - Sim"
                    + "\n[N] - Não");
            op = sc.next();
            
        }while(op.equals("S")||op.equals("s"));
        
        if(vf>1000&&vf<=3000){
            desc = vf * 0.04;
        }
        if(vf>3000){
            desc = vf * 0.07;
        }

        mostarCompra();
        
    }
    
    public static void mostarCompra(){
        
        System.out.println("\n========== LISTA DE PRODUTOS ==========");
        int i = 0;
        for(CarrinhoCompra c: carrim){
            i++;
            System.out.println("===== Produto 0"+i+" =====");
            System.out.println("ID = "+c.getId());
            System.out.println("Nome = "+c.getNome());
            System.out.println("Quant = "+c.getQuant());
            System.out.printf("Prec = %.2f R$\n",c.getValorItem());
            System.out.printf("Valor = %.2f R$\n",c.getQuant()*c.getValorItem());
        }
        
        System.out.println("\n========== VALORES DA COMPRA ===========");
        System.out.printf("Total da Compra = %.2f R$\n",vf);
        System.out.printf("Desconto Compra = %.2f R$\n",desc);
        System.out.printf("Valor Final = %.2f R$\n",(vf - desc));
        System.out.println("");
        
        carrim.clear();
        
        menuFormPag.menu();
        
    }
}
