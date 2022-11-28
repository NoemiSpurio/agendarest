package it.prova.agendarest.service.agenda;

import java.util.List;

import it.prova.agendarest.model.Agenda;
import it.prova.agendarest.web.api.exception.PermessoNegatoException;

public interface AgendaService {

	public List<Agenda> listAll();
	
	public Agenda caricaSingolaAgenda(Long id) throws PermessoNegatoException;
	
	public void aggiorna(Agenda agendaInstance) throws PermessoNegatoException;
	
	public Agenda inserisciNuovo(Agenda agendaInstance);
	
	public void rimuovi(Long idToRemove) throws PermessoNegatoException;
}
