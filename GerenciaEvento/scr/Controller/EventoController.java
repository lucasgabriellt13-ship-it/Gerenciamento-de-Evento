package Controller;

import java.util.ArrayList;

import Model.Evento;

public class EventoController {

    private ArrayList<Evento> eventos = new ArrayList<>();

    // Cadastrar evento
    public String cadastrarEvento(Evento evento) {

        if (evento == null) {
            return "Evento inválido!";
        }

        if (evento.getNome() == null || evento.getNome().trim().isEmpty()) {
            return "Informe o nome do evento!";
        }

        if (evento.getLocal() == null || evento.getLocal().trim().isEmpty()) {
            return "Informe o local do evento!";
        }

        if (evento.getData() == null) {
            return "Informe a data do evento!";
        }

        if (evento.getCapacidadeMaxima() < 0) {
            return "A capacidade não pode ser negativa!";
        }

        if (buscarEventoPorCodigo(evento.getCodigo()) != null) {
            return "Já existe um evento com esse código!";
        }

        eventos.add(evento);

        return "Evento cadastrado com sucesso!";
    }

    // Listar eventos
    public ArrayList<Evento> listarEventos() {
        return new ArrayList<>(eventos);
    }

    // Buscar evento pelo código
    public Evento buscarEventoPorCodigo(int codigo) {

        for (Evento evento : eventos) {

            if (evento.getCodigo() == codigo) {
                return evento;
            }
        }

        return null;
    }

    // Alterar capacidade
    public String alterarCapacidade(
        int codigo, int novaCapacidade, int inscritos) {

        Evento evento = buscarEventoPorCodigo(codigo);

        if (evento == null) {
            return "Evento não encontrado!";
        }

        if (novaCapacidade < 0) {
            return "A capacidade não pode ser negativa!";
        }

        if (novaCapacidade < inscritos) {
            return "A capacidade não pode ser menor que o número de inscritos!";
        }

        evento.setCapacidadeMaxima(novaCapacidade);

        return "Capacidade alterada com sucesso!";
    }
}
