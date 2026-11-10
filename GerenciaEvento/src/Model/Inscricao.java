package Model;

public class Inscricao {

    private Evento evento;
    private Participante participante;

    public Inscricao(Evento evento, Participante participante) {
        this.evento = evento;
        this.participante = participante;
    }

    public Evento getEvento() {
        return evento;
    }

    public Participante getParticipante() {
        return participante;
    }
}
