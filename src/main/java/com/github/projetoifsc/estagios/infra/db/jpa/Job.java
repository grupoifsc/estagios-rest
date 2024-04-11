package com.github.projetoifsc.estagios.infra.db.jpa;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "jobs")
class Job {

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

    // Ver aqui, pq vai pegar o id de uma tabela que tá relacionada com organização
    // Ver bem como vai fazer aqui!
    // A organização precisa cadastrar o endereço e aí depois cadastrar a vaga, ou só depois associar os dois
    // Ver como faz transaction corretamente com o Spring Data ! (maneja automaticamente?)
    // Como testar falhas em transactions? (Boa pergunta pra jogar para o Bruno!)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    Address address;

    // Mesma coisa do caso acima
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id")
    Contact contact;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "period_id")
    Period periodos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "study_level_id")
    Level nivel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "format_id")
    Format format;


    String titulo;

    String id_externo_autor;

    String descricao;

    String imagem;

    // TODO Descobrir a melhor forma de armazenar os requisitos
    String requisitos;

    // TODO LocalDateTime
    String data_inicio;
    String data_final;

    int duracao_meses;

    float remuneracao;

    int carga_horaria_semanal;
    // TODO Criação e Atualização automáticos
    String criado_em;
    String atualizado_em;



    public Job(String titulo) {
            this.titulo = titulo;
    }

    public Job() {
    }


    @Override
    public String toString() {
        return "Job{" +
                "titulo='" + titulo + '\'' +
                ", id_externo_autor='" + id_externo_autor + '\'' +
                ", descricao='" + descricao + '\'' +
                ", imagem='" + imagem + '\'' +
                ", requisitos='" + requisitos + '\'' +
                ", data_inicio='" + data_inicio + '\'' +
                ", data_final='" + data_final + '\'' +
                ", duracao_meses=" + duracao_meses +
                ", remuneracao=" + remuneracao +
                ", carga_horaria_semanal=" + carga_horaria_semanal +
                ", criado_em='" + criado_em + '\'' +
                ", atualizado_em='" + atualizado_em + '\'' +
                '}';
    }
}
