package it.prova.agendarest.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.prova.agendarest.model.Agenda;

public class AgendaDTO {

	private Long id;

	@NotBlank(message = "{descrizione.notblank}")
	private String descrizione;

	@NotNull(message = "{dataOraInizio.notnull}")
	private LocalDateTime dataOraInizio;

	@NotNull(message = "{dataOraFine.notnull}")
	private LocalDateTime dataOraFine;

	@JsonIgnoreProperties(value = {"utente"})
	private UtenteDTO utenteDTO;

	public AgendaDTO() {

	}

	public AgendaDTO(Long id, String descrizione, LocalDateTime dataOraInizio, LocalDateTime dataOraFine,
			UtenteDTO utenteDTO) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.dataOraInizio = dataOraInizio;
		this.dataOraFine = dataOraFine;
		this.utenteDTO = utenteDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDateTime getDataOraInizio() {
		return dataOraInizio;
	}

	public void setDataOraInizio(LocalDateTime dataOraInizio) {
		this.dataOraInizio = dataOraInizio;
	}

	public LocalDateTime getDataOraFine() {
		return dataOraFine;
	}

	public void setDataOraFine(LocalDateTime dataOraFine) {
		this.dataOraFine = dataOraFine;
	}

	public UtenteDTO getUtenteDTO() {
		return utenteDTO;
	}

	public void setUtenteDTO(UtenteDTO utenteDTO) {
		this.utenteDTO = utenteDTO;
	}

	public Agenda buildAgendaModel() {
		Agenda result = new Agenda(this.id, this.descrizione, this.dataOraInizio, this.dataOraFine,
				this.utenteDTO.buildUtenteModel(false));

		return result;
	}

	public static AgendaDTO buildAgendaDTOFromModel(Agenda agendaModel) {
		AgendaDTO result = new AgendaDTO(agendaModel.getId(), agendaModel.getDescrizione(),
				agendaModel.getDataOraInizio(), agendaModel.getDataOraFine(),
				UtenteDTO.buildUtenteDTOFromModel(agendaModel.getUtente()));

		return result;
	}

	public static Set<AgendaDTO> createAgendaDTOSetFromModelSet(Set<Agenda> modelSetInput) {

		return modelSetInput.stream().map(agendaEntity -> {
			return AgendaDTO.buildAgendaDTOFromModel(agendaEntity);
		}).collect(Collectors.toSet());
	}
	
	public static List<AgendaDTO> createAgendaDTOListFromModelList(List<Agenda> modelSetInput) {

		return modelSetInput.stream().map(agendaEntity -> {
			return AgendaDTO.buildAgendaDTOFromModel(agendaEntity);
		}).collect(Collectors.toList());
	}

}
