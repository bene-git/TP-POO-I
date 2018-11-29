
package carrinho;

public enum enumFormPag {
    
    AVISTA(1),
    MISTA(2),
    PARCELADA(3),
    VOLTAR(4);
    
    static enumFormPag valueOf(int opc) {
        switch(opc){
            case 1:
                return AVISTA ;
            case 2:
                return MISTA;
            case 3:
                return PARCELADA;
            case 4:
                return VOLTAR;
            default:
                return null;
        }
    }
    
    private int opcao;
    
    enumFormPag(int opcao){
        this.opcao = opcao;
    }
    
}
