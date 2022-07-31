public class Pessoa {
    private String nome;
    private int idade;
    private double CPF;
    private Endereco endereco;
    private Trabalho trabalho;

    public Pessoa(String nome, int idade, double CPF, Endereco endereco, Trabalho trabalho) {
        this.nome = nome;
        this.idade = idade;
        this.CPF = CPF;
        this.endereco = endereco;
        this.trabalho = trabalho;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Trabalho getTrabalho() {
        return trabalho;
    }

    public void setTrabalho(Trabalho trabalho) {
        this.trabalho = trabalho;
    }

    public double getCPF() {
        return CPF;
    }

    public void setCPF(double CPF) {
        this.CPF = CPF;
    }
}
