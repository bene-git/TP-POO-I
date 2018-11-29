
package pessoas;

public enum enumCliente {
    
    INSERIR(1),
    REMOVER(2),
    ATUALIZAR(3),
    LISTAR(4),
    VOLTAR(5);

    static enumCliente valueOf(int opc) {
        switch(opc){
            case 1:
                return INSERIR;
            case 2:
                return REMOVER;
            case 3:
                return ATUALIZAR;
            case 4:
                return LISTAR;
            case 5:
                return VOLTAR;
            default:
                return null;
        }
    }
    
    private int op;
    
    enumCliente(int opc){
        this.op = opc;
    }
}
