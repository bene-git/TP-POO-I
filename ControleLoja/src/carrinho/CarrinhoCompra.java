
package carrinho;

public class CarrinhoCompra {
    
    private String id;
    private String nome;
    private int quant;
    private double valorItem;
    
    public CarrinhoCompra(String umId, String umNome, int umaQuant, double vi){
        this.id = umId;
        this.nome = umNome;
        this.quant = umaQuant;
        this.valorItem = vi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public double getValorItem() {
        return valorItem;
    }

    public void setValorItem(double valorItem) {
        this.valorItem = valorItem;
    }
    
}
