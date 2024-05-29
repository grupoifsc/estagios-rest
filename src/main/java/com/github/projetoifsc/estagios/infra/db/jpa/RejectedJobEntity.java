package com.github.projetoifsc.estagios.infra.db.jpa;

import jakarta.persistence.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "rejected_jobs", uniqueConstraints = {
        @UniqueConstraint(name="UniqueJobAndOrgRejection", columnNames = {"job_id", "org_id"})
})
@EntityListeners(AuditingEntityListener.class)
class RejectedJobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id", insertable = false, updatable = false)
    private JobEntity job;

    @Column(name = "job_id")
    private long jobId;

    @ManyToOne
    @JoinColumn(name = "org_id", insertable = false, updatable = false)
    private OrganizationEntity organization;

    @Column(name = "org_id")
    private long orgId;

    @LastModifiedDate
    @Column(name = "last_rejected_at")
    private LocalDateTime lastRejectedAt;


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

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public LocalDateTime getLastRejectedAt() {
        return lastRejectedAt;
    }

    public void setLastRejectedAt(LocalDateTime lastRejectedAt) {
        this.lastRejectedAt = lastRejectedAt;
    }


}
