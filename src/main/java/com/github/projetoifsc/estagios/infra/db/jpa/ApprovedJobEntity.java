package com.github.projetoifsc.estagios.infra.db.jpa;

import jakarta.persistence.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "approved_jobs", uniqueConstraints = {
        @UniqueConstraint(name = "UniqueJobAndOrgApproval", columnNames = {"job_id", "org_id"})
})
@EntityListeners(AuditingEntityListener.class)
class ApprovedJobEntity {

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
    private OrganizationEntity organization;

    @Column(name = "org_id")
    private long orgId;

    @LastModifiedDate
    @Column(name = "last_approved_at")
    private LocalDateTime lastApprovedAt;


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

    public LocalDateTime getLastApprovedAt() {
        return lastApprovedAt;
    }

    public void setLastApprovedAt(LocalDateTime lastApprovedAt) {
        this.lastApprovedAt = lastApprovedAt;
    }

}
