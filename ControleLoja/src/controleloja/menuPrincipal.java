
package controleloja;

import carrinho.realizarCompra;
import java.util.InputMismatchException;
import java.util.Scanner;
import pessoas.FolhaImprimir;
import pessoas.FolhaPagamento;
import pessoas.menuCliente;
import pessoas.menuFunc;
import produtos.menuProd;

public class menuPrincipal {
    
    public static void menuP(){
        
        Scanner sc = new Scanner(System.in);
        
        int op = 0;
        
        menuPrinc[] menuP = menuPrinc.values();
        System.out.println("\n===== MENU PRINCIPAL =====");
        for(int i=0;i<menuP.length;i++){
            System.out.println((i+1)+" - "+menuP[i]);
        }
        
        //////////////////////////////////////////////////////
        try{
            System.out.print("Informe a opção: ");
            op = sc.nextInt();
        }catch(Exception e){
            
        } finally{
            
        }
        /////////////////////////////////////////////////////
        
        switch((op)){
            case 1:
                menuProd.menu();
                break;
            case 2:
                menuCliente.menuCliente();
                break;
            case 3:
                menuFunc.menu();
                break;
            case 4:
                realizarCompra.comprar();
                break;
            case 5:
                FolhaPagamento.pagamento();
                FolhaPagamento.atcarga();
                FolhaImprimir.folhapag();
                menuPrincipal.menuP();
                break;
            case 6:
                
                break;
            default:
                System.err.println("Opção Inválida!");
                menuPrincipal.menuP();
                break;
        }
    }
    
}
