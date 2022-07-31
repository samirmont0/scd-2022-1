public class Endereco {
    private String rua;
    private String bairro;
    private String cidade;
    private double CEP;

    public Endereco(String rua, String bairro, String cidade, double CEP) {
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.CEP = CEP;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public double getCEP() {
        return CEP;
    }

    public void setCEP(double CEP) {
        this.CEP = CEP;
    }
}
