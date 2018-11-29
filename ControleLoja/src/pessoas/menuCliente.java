
package pessoas;

import controleloja.menuPrincipal;
import java.util.Scanner;
import static pessoas.menuFunc.casos;

public class menuCliente {
    
    static Scanner sc = new Scanner(System.in);
    static int op = 0;
    
    public static void menuCliente(){
        
        enumCliente[] menuP = enumCliente.values();
        System.out.println("\n======= MENU CLIENTE =======");
        for(int i=0;i<menuP.length;i++){
            System.out.println((i+1)+" - "+menuP[i]);
        }
        
        System.out.print("Informe a opção: ");
        op =  sc.nextInt();
        
        casosCliente(op);
    }
    
    public static void casosCliente(int opc){
        switch((opc)){
            case 1:
                InserirClient.insert();
                menuCliente.menuCliente();
                break;
            case 2:
                RemoverCliente.removerClient();
                menuCliente.menuCliente();
                break;
            case 3:
                AtualizarCliente.atualizarClient();
                menuCliente.menuCliente();
                break;
            case 4:
                MostrarCliente.mostrarClient();
                menuCliente.menuCliente();
                break;
            case 5:
                menuPrincipal.menuP();
                break;
            default:
                System.out.println("Opção Inválida!");
                menuCliente.menuCliente();
                break;
        }
    }
    
}
