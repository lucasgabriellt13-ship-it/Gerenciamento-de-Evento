package Model;

import java.time.LocalDate;

public class Evento {

    private int codigo;
    private String nome;
    private LocalDate data;
    private String local;
    private int capacidadeMaxima;

    public Evento(int codigo, String nome, LocalDate data, String local, int capacidadeMaxima) {

        this.codigo = codigo;
        this.nome = nome;
        this.data = data;
        this.local = local;
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getData() {
        return data;
    }

    public String getLocal() {
        return local;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }
}
