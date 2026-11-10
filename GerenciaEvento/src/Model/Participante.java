package Model;

public class Participante {

    private int codigo;
    private String nome;
    private String contato;

    public Participante(int codigo, String nome, String contato) {
        this.codigo = codigo;
        this.nome = nome;
        this.contato = contato;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getContato() {
        return contato;
    }
}
