package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.app.interfaces.*;
import com.github.projetoifsc.estagios.core.IJob;
import com.github.projetoifsc.estagios.core.IJobDB;
import com.github.projetoifsc.estagios.core.IJobEntryData;
import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.utils.JsonParser;
import com.github.projetoifsc.estagios.utils.Mapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
class JobDBImpl implements IJobDB {

    OrganizationRepository orgRepository;
    JobRepository jobRepository;
    AreaRepository areaRepository;
    ContactRepository contactRepository;
    AddressRepository addressRepository;
    ApprovedJobRepository approvedJobRepository;
    RejectedJobRepository rejectedJobRepository;
    Mapper mapper;

    JsonParser jsonParser;

    @Autowired
    public JobDBImpl(OrganizationRepository orgRepository, JobRepository jobRepository, AreaRepository areaRepository, ContactRepository contactRepository, AddressRepository addressRepository, ApprovedJobRepository approvedJobRepository, RejectedJobRepository rejectedJobRepository, Mapper mapper, JsonParser jsonParser) {
        this.orgRepository = orgRepository;
        this.jobRepository = jobRepository;
        this.areaRepository = areaRepository;
        this.contactRepository = contactRepository;
        this.addressRepository = addressRepository;
        this.approvedJobRepository = approvedJobRepository;
        this.rejectedJobRepository = rejectedJobRepository;
        this.mapper = mapper;
        this.jsonParser = jsonParser;
    }

    private <T, R> R getOptionalOrThrow(T input, Function<T, Optional<R>> getOptional) {
        Function<T, R> pipeline = getOptional
                .andThen(opt -> opt.orElseThrow(EntityNotFoundException::new));
        return pipeline.apply(input);
    }


    @Override
    @Transactional
    public IJob getBasicInfoById(String id) {
        var job = getOptionalOrThrow(
                Long.parseLong(id),
                jobId -> jobRepository.findById(jobId, JobBasicInfoProjection.class));
        job.getOwner().getId();
        return job;
    }

    private JobEntity findEntityById(String id) {
        return getOptionalOrThrow(
                Long.parseLong(id),
                jobId -> jobRepository.findById(jobId, JobEntity.class));
    }


    // Infelizmente, o método save estava retornando um objeto com dados que não estavam atualizados (criado_em)
    // Como não consegui arrumar, achei melhor deixar o save retornando apenas o novo id
    @Override
    public String saveAndGetId(IJobEntryData newJob) {
        var org = mapper.map(newJob.getOwner(), OrganizationEntity.class);
        newJob.setOwner(org);

        var newJobEntity = mapper.map(newJob, JobEntity.class);

        // TODO: há uma redundância na chamada ao banco de dados, talvez se conseguisse inserir apenas pelo id seria ótimo!
        var receiversId = newJob.getReceiversIds().stream()
                .map(Long::parseLong).toList();
        if(!receiversId.isEmpty()) {
            var receiversEntities = orgRepository.findAllByIdIn(receiversId, OrganizationEntity.class);
            newJobEntity.setExclusiveReceivers(receiversEntities);
        }


        // TODO: deve falhar se uma área não existir?
        var areasIds = newJob.getAreasIds()
                .stream().map(Long::parseLong).toList();
        if(!areasIds.isEmpty()) {
            var areas = areaRepository.findByIdIn(areasIds);
            newJobEntity.setAreas(areas);
        }


        // ContactId
        var contactId = newJob.getContactId();
        if(contactId != null) {
            var contact = contactRepository.findById(Long.parseLong(contactId))
                    .orElseThrow(() -> new EntityNotFoundException("Contato com id " + contactId + " não encontrado"));
            newJobEntity.setContact(contact);
        }
        else newJobEntity.setContact(null);

        var addressId = newJob.getAddressId();
        if(addressId != null) {
            var address = addressRepository.findById(Long.parseLong(addressId))
                    .orElseThrow(() -> new EntityNotFoundException("Endereço com id " + addressId + " não encontrado"));
            newJobEntity.setAddress(address);
        }
        else newJobEntity.setAddress(null);

        var savedJobEntity = jobRepository.save(newJobEntity);
        return savedJobEntity.getId();
    }

    @Override
    public void delete(String id) {
        var job = findEntityById(id);
        jobRepository.delete(job);
    }


    @Override
    @Transactional
    public IJob getPublicDetails(String id) {
        var job = getOptionalOrThrow(
                Long.parseLong(id),
                parsedId -> jobRepository.findById(parsedId, JobPublicDetailsProjection.class)
        );
        if(job.getAddress() != null)
            job.getAddress().getId();
        if(job.getContact() != null)
            job.getContact().getId();
        job.getAreas();
        job.getOwner().getId();
        return job;
    }

    @Override
    @Transactional
    public IJob getPrivateDetails(String id) {
        var job = getOptionalOrThrow(
                Long.parseLong(id),
                parsedId -> jobRepository.findById(parsedId, JobPrivateDetailsProjection.class)
        );
        // Necessário chamar aqui devido ao lazy loading
        job.getOwner().getId();
        job.getExclusiveReceivers();
        job.getAreas();
        if(job.getContact() != null)
            job.getContact().getId();
        if(job.getAddress() != null)
            job.getAddress().getId();
//        jsonParser.printValue(job);
        return job;
    }

    @Override
    public List<IJob> findAllPublicJobs() {
        return jobRepository.findAllByExclusiveReceiversEmpty(JobPublicSummaryProjection.class)
                .stream().map(job -> (IJob) job).toList();
    }


    // TODO: Isto era para estar em outro lugar?
    @Override
    public List<IOrganization> getExclusiveReceiversForJob(String id) {
        return orgRepository.findAllByExclusiveReceivedJobsId(Long.parseLong(id), OrgBasicInfoProjection.class)
                .stream().map(org -> (IOrganization) org).toList();
    }


    @Override
    public void setJobApprovedByOrg(String traineeshipId, String organizationId) {
        var jobId = Long.parseLong(traineeshipId);
        var orgId = Long.parseLong(organizationId);

        var existingApprovedRecord = approvedJobRepository.findByJobIdAndOrganizationId(jobId, orgId);
        if(existingApprovedRecord.isPresent()){
            System.out.println("Job already approved by institution");
            return;
        }

        var rejectedJob = rejectedJobRepository.findByJobIdAndOrganizationId(jobId, orgId);
        rejectedJob.ifPresent(
                rejectedJobEntity -> rejectedJobRepository.delete(rejectedJobEntity));

        var approvedJob = new ApprovedJobEntity();
        approvedJob.setJobId(jobId);
        approvedJob.setOrgId(orgId);
        approvedJobRepository.save(approvedJob);

    }


    @Override
    public void setJobReprovedByOrg(String traineeshipId, String organizationId) {
        var jobId = Long.parseLong(traineeshipId);
        var orgId = Long.parseLong(organizationId);

        var existingRejectedRecord = rejectedJobRepository.findByJobIdAndOrganizationId(jobId, orgId);
        if(existingRejectedRecord.isPresent()){
            System.out.println("Job already rejected by institution");
            return;
        }

        var approvedJob = approvedJobRepository.findByJobIdAndOrganizationId(jobId, orgId);
        approvedJob.ifPresent(
                approvedJobEntity -> approvedJobRepository.delete(approvedJobEntity));

        var rejectedJob = new RejectedJobEntity();
        rejectedJob.setJobId(jobId);
        rejectedJob.setOrgId(orgId);
        rejectedJobRepository.save(rejectedJob);

    }


    @Override
    public List<IJob> getAllApprovedSummaryByOrg(String orgId) {
        return jobRepository.findAllByApprovalsOrganizationId(Long.parseLong(orgId), JobPublicSummaryProjection.class)
                .stream().map(job -> (IJob) job).toList();
    }

    @Override
    public List<IJob> getAllReprovedSummaryByOrg(String orgId) {
        return jobRepository.findAllByRejectionsOrganizationId(Long.parseLong(orgId), JobPublicSummaryProjection.class)
                .stream().map(job -> (IJob) job).toList();
    }


    // TODO: pensar melhor se é Available, Rejected, Approved, To Moderate, etc...
    public List<IJob> getAllAvailableByOrg(String orgId) {
        var id = Long.parseLong(orgId);
        return jobRepository.findDistinctByApprovalsOrganizationIdOrOwnerId(id, id, JobPublicSummaryProjection.class)
                .stream().map(job -> (IJob) job).toList();
    }


}
