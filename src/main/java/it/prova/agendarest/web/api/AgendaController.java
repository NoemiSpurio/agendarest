package it.prova.agendarest.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.agendarest.dto.AgendaDTO;
import it.prova.agendarest.model.Agenda;
import it.prova.agendarest.service.agenda.AgendaService;
import it.prova.agendarest.web.api.exception.AgendaNotFoundException;
import it.prova.agendarest.web.api.exception.PermessoNegatoException;

@RestController
@RequestMapping("/api/agenda")
public class AgendaController {

	@Autowired
	private AgendaService agendaService;
	
	@GetMapping
	public List<AgendaDTO> listAll(){
		return AgendaDTO.createAgendaDTOListFromModelList(agendaService.listAll());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AgendaDTO inserisci(@Valid @RequestBody AgendaDTO agenda) {
		if(agenda.getId() != null)
			throw new AgendaNotFoundException("id non nullo impossibile inserire un nuovo record");
		
		Agenda result = agendaService.inserisciNuovo(agenda.buildAgendaModel());
		return AgendaDTO.buildAgendaDTOFromModel(result);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void elimina(@PathVariable(name = "id", required = true) Long id) throws PermessoNegatoException {
		agendaService.rimuovi(id);
	}
	
	@GetMapping("/{id}")
	public AgendaDTO findById(@PathVariable(name = "id", required = true) Long id) throws PermessoNegatoException {
		return AgendaDTO.buildAgendaDTOFromModel(agendaService.caricaSingolaAgenda(id));
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void aggiorna(@Valid @RequestBody AgendaDTO agenda) throws PermessoNegatoException {
		
		if(agenda.getId() == null)
			throw new AgendaNotFoundException("Per poter modificare un'agenda devi specificarne l'id!");
		
		agendaService.aggiorna(agenda.buildAgendaModel());
	}
}
