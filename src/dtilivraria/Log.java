package dtilivraria;

public class Log {
    private int id;
    private String nomeLivro;
    private int quantidadeComprada;
    private String data;

    public Log(int id, String nomeLivro, int quantidadeComprada, String data) {
        this.id = id;
        this.nomeLivro = nomeLivro;
        this.quantidadeComprada = quantidadeComprada;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public int getQuantidadeComprada() {
        return quantidadeComprada;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
    return
           "ID do Log: " + id +
           "\nLivro: " + nomeLivro +
           "\nQuantidade comprada: " + quantidadeComprada +
           "\nData da compra: " + data +
           "\n-----------------------------";
    }
}
