package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.app.model.interfaces.*;
import com.github.projetoifsc.estagios.core.IJob;
import com.github.projetoifsc.estagios.core.IJobDAO;
import com.github.projetoifsc.estagios.core.IJobEntryData;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
class JobDAOImpl implements IJobDAO {

    private final OrganizationRepository orgRepository;
    private final JobRepository jobRepository;
    private final AreaRepository areaRepository;
    private final ContactRepository contactRepository;
    private final AddressRepository addressRepository;
    private final ModeratedJobRepository moderatedJobRepository;
    private final Mapper mapper;
    private final JsonParser jsonParser;

    JobDAOImpl(OrganizationRepository orgRepository, JobRepository jobRepository, AreaRepository areaRepository, ContactRepository contactRepository, AddressRepository addressRepository, ModeratedJobRepository moderatedJobRepository, Mapper mapper, JsonParser jsonParser) {
        this.orgRepository = orgRepository;
        this.jobRepository = jobRepository;
        this.areaRepository = areaRepository;
        this.contactRepository = contactRepository;
        this.addressRepository = addressRepository;
        this.moderatedJobRepository = moderatedJobRepository;
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
    public IJob getBasicInfo(String id) {
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


    /**
     * Infelizmente, o método save estava retornando um objeto com dados que não estavam atualizados (criado_em)
     * Como não consegui arrumar, achei melhor deixar o save retornando apenas o novo id
     */
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


        // TODO Logic: deve falhar se uma área não existir?
        // Sim, deve falhar!
        // Este dado não precisa estar no IJobEntry no Core!
        // Ou então... tudo deve estar no Core na verdade...
        // Para deixar bem amarradinho...
        // É um pouco irritante? Sim, mas é assim que é...
        var areasIds = newJob.getAreasIds()
                .stream().map(Long::parseLong).toList();
        if(!areasIds.isEmpty()) {
            var areas = areaRepository.findByIdIn(areasIds);
            newJobEntity.setAreas(areas);
        }


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
        return job;
    }


    @Override
    public IJob setJobApprovedByOrg(String traineeshipId, String organizationId) {
        var moderatedJobsEntity = getModeratedEntity(traineeshipId, organizationId);
        moderatedJobsEntity.setStatusId(ModerationStatusEnum.APPROVED.getId());
        moderatedJobRepository.save(moderatedJobsEntity);
        return jobRepository.findById(Long.parseLong(traineeshipId), JobPublicDetailsProjection.class).orElse(null);
    }

    private ModeratedJobsEntity getModeratedEntity(String traineeshipId, String organizationId) {
        ModeratedJobsEntity moderatedJobsEntity;

        var jobId = Long.parseLong(traineeshipId);
        var orgId = Long.parseLong(organizationId);

        var existingRecord = moderatedJobRepository.findByJobIdAndOrganizationId(jobId, orgId);

        if(existingRecord.isPresent()) {
            moderatedJobsEntity = existingRecord.get();
        } else {
            moderatedJobsEntity = new ModeratedJobsEntity();
            moderatedJobsEntity.setJobId(jobId);
            moderatedJobsEntity.setOrgId(orgId);
        }
        return moderatedJobsEntity;
    }

    @Override
    public IJob setJobRejectedByOrg(String traineeshipId, String organizationId) {
        var moderatedJobsEntity = getModeratedEntity(traineeshipId, organizationId);
        moderatedJobsEntity.setStatusId(ModerationStatusEnum.REJECTED.getId());
        moderatedJobRepository.save(moderatedJobsEntity);
        return jobRepository.findById(Long.parseLong(traineeshipId), JobPublicDetailsProjection.class).orElse(null);
    }

    @Override
    public List<IJob> findAllPublicJobsSummary() {
        return jobRepository.findAllByExclusiveReceiversEmpty(JobPublicSummaryProjection.class)
                .stream().map(job -> (IJob) job).toList();
    }


    @Override
    public List<IJob> getAllApprovedSummaryFromOrg(String orgId) {
        return jobRepository.findAllByModeratedJobsOrgIdAndModeratedJobsStatusId(
                Long.parseLong(orgId), ModerationStatusEnum.APPROVED.getId(), JobPublicSummaryProjection.class)
                .stream().map(job -> (IJob) job).toList();
    }

    @Override
    public List<IJob> getAllRejectedSummaryFromOrg(String orgId) {
        return jobRepository.findAllByModeratedJobsOrgIdAndModeratedJobsStatusId(
                        Long.parseLong(orgId), ModerationStatusEnum.REJECTED.getId(), JobPublicSummaryProjection.class)
                .stream().map(job -> (IJob) job).toList();
    }

    @Override
    public List<IJob> getAllPendingSummaryFromOrg(String orgId) {
        return jobRepository.findAllByExclusiveReceiversEmptyOrExclusiveReceiversId(Long.parseLong(orgId), JobPublicSummaryProjection.class)
                .stream().map(job -> (IJob) job).toList();
    }

    @Override
    public Page<IJob> getAllCreatedJobsSummaryFromOrg(String orgId) {
        return jobRepository.findAllByOwnerId(Long.parseLong(orgId), PageRequest.of(0, 100), JobPublicSummaryProjection.class)
                .map(job -> (IJob) job);
    }

    @Override
    public List<IJob> getAllAvailableSummaryFromOrg(String orgId) {
        var organizationId = Long.parseLong(orgId);
        var approvedStatusId = ModerationStatusEnum.APPROVED.getId();
        return jobRepository.findAllByOwnerIdOrModeratedJobsOrgIdAndModeratedJobsStatusId(
                organizationId, organizationId, approvedStatusId, JobPublicSummaryProjection.class
        ).stream().map(job -> (IJob) job).toList();
    }

    @Override
    public List<IJob> getExclusiveReceivedJobsSummaryForOrg(String orgId) {
        return jobRepository.findAllByExclusiveReceiversId(Long.parseLong(orgId), JobPublicSummaryProjection.class)
                .stream().map(r -> (IJob) r).toList();
    }

}
