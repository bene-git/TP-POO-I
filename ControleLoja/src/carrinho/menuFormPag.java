
package carrinho;

import conexoes.ConexaoSqlite;
import controleloja.menuPrincipal;
import java.util.Scanner;

public class menuFormPag {
    static Scanner sc = new Scanner(System.in);
    static int op = 0;
    static ConexaoSqlite conexaoBanco = new ConexaoSqlite();
    
    public static void menu(){
        
        enumFormPag[] menuP = enumFormPag.values();
        System.out.println("\n===== FORMAS DE PAGAMENTO =====");
        for(int i=0;i<menuP.length;i++){
            System.out.println((i+1)+" - "+menuP[i]);
        }
        
        System.out.print("::> Informe a opção: ");
        op =  sc.nextInt();

        casos(op);
    }
    
    public static void casos(int opc){
        switch((opc)){
            case 1:
                FormaPagamento.avista();
                menuPrincipal.menuP();
                break;
            case 2:
                FormaPagamento.mista();
                menuPrincipal.menuP();
                break;
            case 3:
                FormaPagamento.parcelada();
                menuPrincipal.menuP();
                break;
            case 4:
                menuFormPag.menu();
                break;
            default:
                System.out.println("Opção Inválida!");
                menuFormPag.menu();
                break;
        }
    }
    
}
