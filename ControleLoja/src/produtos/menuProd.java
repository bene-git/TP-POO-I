
package produtos;

import controleloja.menuPrincipal;
import java.util.Scanner;

public class menuProd {
    
    static Scanner sc = new Scanner(System.in);
    static int op = 0;
    
    public static void menu(){
        
        menuEnum[] menuP = menuEnum.values();
        System.out.println("===== MENU PRODUTOS =====");
        for(int i=0;i<menuP.length;i++){
            System.out.println((i+1)+" - "+menuP[i]);
        }
        
        System.out.print("Informe a opção: ");
        op =  sc.nextInt();
            
        casos(op);
    }
    
    public static void casos(int opc){
        switch((opc)){
            case 1:
                Inserir.inserirProd();
                menuProd.menu();
                break;
            case 2:
                Remover.remover();
                menuProd.menu();
                break;
            case 3:
                Atualizar.atuaizar();
                menuProd.menu();
                break;
            case 4:
                Recuperar.recuperar();
                menuProd.menu();
                break;
            case 5:
                menuPrincipal.menuP();
                break;
            default:
                System.out.println("Opção Inválida!");
                menuProd.menu();
                break;
        }
    }
}
