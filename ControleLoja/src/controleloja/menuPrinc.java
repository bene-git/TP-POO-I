
package controleloja;

public enum menuPrinc {
    
    PRODUTOS(1),
    CLIENTES(2),
    FUNCIONARIOS(3),
    VENDAS(4),
    PAGAMENTO(5),
    SAIR(6);

    static menuPrinc valueOf(int op) {
        switch(op){
            case 1:
                return PRODUTOS;
            case 2:
                return CLIENTES;
            case 3:
                return FUNCIONARIOS;
            case 4:
                return VENDAS;
            case 5:
                return PAGAMENTO;
            case 6:
                return SAIR;
            default:
                return null;
        }
    }
    
    private int opcao;
    
    menuPrinc(int op){
        this.opcao = op;
    }
}
