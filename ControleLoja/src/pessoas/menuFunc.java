/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoas;

import controleloja.menuPrincipal;
import java.util.Scanner;

/**
 *
 * @author STI
 */
public class menuFunc {
    
    static Scanner sc = new Scanner(System.in);
    static int op = 0;
    
    public static void menu(){
        
        enumFunc[] menuP = enumFunc.values();
        System.out.println("\n===== MENU FUNCIONÁRIO =====");
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
                InserirFunc.insert();
                menuFunc.menu();
                break;
            case 2:
                RemoverFunc.removerFunc();
                menuFunc.menu();
                break;
            case 3:
                AtualizarFunc.atuaizarFunc();
                menuFunc.menu();
                break;
            case 4:
                MostrarFunc.mostrarFunc();
                menuFunc.menu();
                break;
            case 5:
                ContraCheque.gerar();
                menuFunc.menu();
                break;
            case 6:
                menuPrincipal.menuP();
                break;
            default:
                System.out.println("Opção Inválida!");
                menuFunc.menu();
                break;
        }
    }
    
}
