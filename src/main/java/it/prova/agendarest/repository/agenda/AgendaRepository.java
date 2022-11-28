package it.prova.agendarest.repository.agenda;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.prova.agendarest.model.Agenda;
import it.prova.agendarest.model.Utente;

public interface AgendaRepository extends CrudRepository<Agenda, Long> {

	List<Agenda> findByUtente(Utente utente);
}
