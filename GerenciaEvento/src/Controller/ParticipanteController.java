package Controller;

import java.util.ArrayList;

import Model.Participante;

public class ParticipanteController {
	private ArrayList<Participante> participantes = new ArrayList<>();
	
	public String cadastrarParticipante(Participante participante) {
	
	if(participante == null) {
		return "Participante Invalido";
	}
	
	if(participante.getNome() == null || participante.getNome().trim().isEmpty()) {
		return "Informe o nome do Integrante";
	}
	
	if(participante.getContato() == null || participante.getContato().trim().isEmpty()) {
		return "Informe o contato do Integrante";
	}
	
	if(buscarParticipantePorCodigo(participante.getCodigo()) != null) {
		return "Ja existe um Integrante com esse codigo";
	}
		
	participantes.add(participante);
	
	return "Integrante cadastrado com sucesso";
	
	}
	
	public ArrayList<Participante> listarParticipantes(){
		return new ArrayList<>(participantes);
	}
	
	public Participante buscarParticipantePorCodigo(int codigo) {
		for(Participante participante : participantes) {
			
			if(participante.getCodigo() == codigo) {
				return participante;
			}
		}
		return null;
	}
}
