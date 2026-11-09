package Controller;

import java.util.ArrayList;

import Model.Evento;
import Model.Inscricao;
import Model.Participante;

public class InscricaoController {
	private ArrayList<Inscricao> inscricoes = new ArrayList<>();
	
	private EventoController eventoController;
	private ParticipanteController participanteController;
	
	public InscricaoController(EventoController eventoController, ParticipanteController participanteController) {
		this.eventoController = eventoController;
		this.participanteController = participanteController;
	}
	
	public String realizarIncricao(int codigoEvento, int codigoParticipante) {
		Evento evento = eventoController.buscarEventoPorCodigo(codigoParticipante);
		Participante participante = participanteController.buscarParticipantePorCodigo(codigoParticipante);
		
		if (evento == null) {
			return "Evento nao encontrado";	
		}
		
		if (participante == null) {
			return "Integrante nao encontrado";
		}
		
		for(Inscricao inscricao : inscricoes) {
			if(inscricao.getEvento().getCodigo() == codigoEvento && inscricao.getParticipante().getCodigo() == codigoParticipante) {
				return "Integrante ja inscrito neste evento";
			}
		}
		
		if(contarInscritos(codigoEvento) >= evento.getCapacidadeMaxima()) {
			return "Nao existem mais vagas disponiveis";
		}
		
		Inscricao novaInscricao = new Inscricao(evento, participante);
		inscricoes.add(novaInscricao);
		
		return "Incricao realizada";
	}
	
	public int contarInscritos (int codigoEvento) {
		
		int quantidade = 0;
		
		for (Inscricao inscricao : inscricoes) {
			
			if ((inscricao.getEvento().getCodigo() == codigoEvento)) {
				quantidade++;
			}
		}
	
	return quantidade;
	}

	public int calcularVagasDisponiveis(int codigoEvento) {
	
		Evento evento =
			eventoController.buscarEventoPorCodigo(codigoEvento);
	
		if (evento == null) {
			return -1;
		}
	
		return evento.getCapacidadeMaxima()
			- contarInscritos(codigoEvento);
		}

	public String cancelarInscricao(int codigoEvento, int codigoParticipante) {
	
		for (Inscricao inscricao : inscricoes) {
		
			if (inscricao.getEvento().getCodigo() == codigoEvento && inscricao.getParticipante().getCodigo() == codigoParticipante) {
			
				inscricoes.remove(inscricao);
			
				return "Inscrição cancelada com sucesso!";
			}
		}
	
		return "Inscrição não encontrada!";
	}

	public ArrayList<Inscricao> listarInscricoesPorEvento(int codigoEvento) {
	
		ArrayList<Inscricao> resultado = new ArrayList<>();
	
		for (Inscricao inscricao : inscricoes) {
		
			if (inscricao.getEvento().getCodigo() == codigoEvento) {
				resultado.add(inscricao);
			}
		}
	
		return resultado;
		}

		public ArrayList<Inscricao> listarTodasInscricoes() {
			return new ArrayList<>(inscricoes);
		}
}


