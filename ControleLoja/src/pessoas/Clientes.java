
package pessoas;

public class Clientes extends Pessoa {
    
    private double saldo = 0.0;
    private int qtd_parc = 0;
    private int fiel = 0;

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getQtd_parc() {
        return qtd_parc;
    }

    public void setQtd_parc(int qtd_parc) {
        this.qtd_parc = qtd_parc;
    }

    public int getFiel() {
        return fiel;
    }

    public void setFiel(int fiel) {
        this.fiel = fiel;
    }
}
