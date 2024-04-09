package com.github.projetoifsc.estagios.infra.db.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // TODO Ver Projection (Proxy interface ou Record)
    // https://docs.spring.io/spring-data/jpa/reference/repositories/projections.html
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    Organization owner;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="areas_jobs",
        joinColumns = @JoinColumn(name="job_id"),
        inverseJoinColumns = @JoinColumn(name="area_id")
    )
    List<Area> areas;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ies_received_jobs",
        joinColumns = @JoinColumn(name = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "org_id")
    )
    List<Organization> ies;

//    @NotNull
//    @Column(nullable = false)
    String titulo;

    String id_externo_autor;

//    @NotNull
//    @Column(nullable = false)
    String descricao;

    String imagem;

    // TODO Descobrir a melhor forma de armazenar os requisitos
    String requisitos;

    // TODO LocalDateTime
    String data_inicio;
    String data_final;

    int duracao_meses;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "jobs_periods",
        joinColumns = @JoinColumn(name = "job_id", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "period_id", nullable = false)
    )
    List<Period> periodos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "study_level_id"
    //        , nullable = false
    )
    StudyLevel nivel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "format_id"
    //        , nullable = false
    )
    Format format;

//    @NotNull
//    @Min(0)
//    @Column(nullable = false)
    float remuneracao;

  //  @NotNull
  //  @Min(0)
  //  @Column(nullable = false)
    int carga_horaria_semanal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id"
    //        , nullable = false
    )
    Address address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id"
    //        , nullable = false
    )
    Contact contact;


    // TODO Criação e Atualização automáticos
    String criado_em;
    String atualizado_em;


    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public Organization getOwner() {
        return owner;
    }

    public void setOwner(Organization owner) {
            this.owner = owner;
    }
    public Job(String titulo) {
            this.titulo = titulo;
    }

    public Job() {

    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

}
