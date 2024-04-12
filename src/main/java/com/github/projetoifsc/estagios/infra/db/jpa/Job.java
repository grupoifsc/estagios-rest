package com.github.projetoifsc.estagios.infra.db.jpa;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;


// Ver a questão do ID

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "jobs")
class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // TODO Ver Projection (Proxy interface ou Record)
    // https://docs.spring.io/spring-data/jpa/reference/repositories/projections.html
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    Organization owner;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="areas_jobs",
        joinColumns = @JoinColumn(name="job_id"),
        inverseJoinColumns = @JoinColumn(name="area_id")
    )
    List<Area> areas;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ies_received_jobs",
        joinColumns = @JoinColumn(name = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "org_id")
    )
    List<Organization> ies;

    // Como pode vir das outras camadas:
    // Com um id OU com os campos marcados.. (a camada de negócio vai ter que resolver isso)
    // Ver aqui, pq vai pegar o id de uma tabela que tá relacionada com organização
    // Ver bem como vai fazer aqui!
    // A organização precisa cadastrar o endereço e aí depois cadastrar a vaga, ou só depois associar os dois
    // Ver como faz transaction corretamente com o Spring Data ! (maneja automaticamente?)
    // Como testar falhas em transactions? (Boa pergunta pra jogar para o Bruno!)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    Address address;

    // Mesma coisa do caso acima
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    Contact contact;


    // Solução: https://stackoverflow.com/questions/27930449/jpa-many-to-one-relation-need-to-save-only-id
    // Funciona, pois acusa falha de restrição de FK quando insere um valor que não existe na tabela relacionada
    // Não funciona caso, na mesma transação, deseje salvar E selecionar a tabela relacionada

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "period_id", insertable = false, updatable = false)
    Period periodo;

    @Column(name = "period_id")
    short periodId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_level_id", insertable = false, updatable = false)
    Level nivel;

    @Column(name = "study_level_id")
    short levelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "format_id", insertable = false,updatable = false)
    Format format;

    @Column(name = "format_id")
    short formatId;


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

    @CreatedDate
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    LocalDateTime updatedAt;



    public Job(String titulo) {
            this.titulo = titulo;
    }

    public Job() {
    }


    Organization getOwner() {
        return owner;
    }

    List<Area> getAreas() {
        return areas;
    }

    List<Organization> getIes() {
        return ies;
    }

    Address getAddress() {
        return address;
    }

    Contact getContact() {
        return contact;
    }

    Period getPeriodo() {
        return periodo;
    }

    Level getNivel() {
        return nivel;
    }

    Format getFormat() {
        return format;
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
                ", criado_em='" + createdAt + '\'' +
                ", atualizado_em='" + updatedAt + '\'' +
                '}';
    }

}
