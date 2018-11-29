package pessoas;

public enum enumFunc {
    
    INSERIR(1),
    REMOVER(2),
    ATUALIZAR(3),
    LISTAR(4),
    CONTRA_CHEQUE(5),
    VOLTAR(6);

    static enumFunc valueOf(int opc) {
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
                return CONTRA_CHEQUE;
            case 6:
                return VOLTAR;
            default:
                return null;
        }
    }
    
    private int op;
    
    enumFunc(int opc){
        this.op = opc;
    }
}
