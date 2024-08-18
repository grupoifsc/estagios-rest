package com.github.projetoifsc.estagios.infra.db.jpa;

import jakarta.persistence.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "ies_moderated_jobs", uniqueConstraints = {
        @UniqueConstraint(name="UniqueJobAndOrgRejection", columnNames = {"job_id", "org_id"})
})
@EntityListeners(AuditingEntityListener.class)
class ModeratedJobsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", insertable = false, updatable = false)
    private JobEntity job;

    @Column(name = "job_id")
    private long jobId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id", insertable = false, updatable = false)
    private OrgEntity organization;

    @Column(name = "org_id")
    private long orgId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    private ModerationStatusEntity status;

    @Column(name = "status_id")
    private short statusId;

    @LastModifiedDate
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobEntity getJob() {
        return job;
    }

    public void setJob(JobEntity job) {
        this.job = job;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public OrgEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrgEntity organization) {
        this.organization = organization;
    }

    public ModerationStatusEntity getStatus() {
        return status;
    }

    public void setStatus(ModerationStatusEntity status) {
        this.status = status;
    }

    public short getStatusId() {
        return statusId;
    }

    public void setStatusId(short statusId) {
        this.statusId = statusId;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }


}
