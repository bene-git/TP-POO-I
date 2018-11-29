package controleloja;

import java.text.SimpleDateFormat;
import java.util.Date;
import pessoas.ContraCheque;

public class ControleLoja {

    public static void main(String[] args) {
        
        System.out.println("===================================================");
        System.out.println("=====================  S C J  =====================");
        System.out.println("=========== SISTEMA DE CONTROLE DE LOJA ===========");
        System.out.println("===================================================");
        System.out.println("===============   LOJAS CLICK NET   ===============");
        System.out.println("===================================================");
        
        Date data = new Date();
        SimpleDateFormat form = new SimpleDateFormat("d/M/y  -  H:m:s");
        String df = form.format(data);
        System.out.println("Valença do Piauí            "+df);
        
        System.out.println("");
        
        menuPrincipal.menuP();
        
        Date dataa = new Date();
        SimpleDateFormat forms = new SimpleDateFormat("d/M/y  -  H:m:s");
        String dff = forms.format(dataa);
        
        System.out.println("\nValença do Piauí - "+dff);
        
    }
    
}
