package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.IJobDAO;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import com.github.projetoifsc.estagios.app.utils.Mapper;
import com.github.projetoifsc.estagios.core.models.IJob;
import com.github.projetoifsc.estagios.core.models.IJobEntryData;
import com.github.projetoifsc.estagios.core.models.projections.JobPrivateDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.JobPublicDetailsProjection;
import com.github.projetoifsc.estagios.core.models.projections.ModerationDetailsProjection;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public IJob getJobBasicInfo(String id) {
        var job = getOptionalOrThrow(
                Long.parseLong(id),
                jobId -> jobRepository.findById(jobId, IJob.class));
        job.getOwner().getId();
        return job;
    }

    @Override
    public List<IJob> getJobBasicInfo(List<String> traineeshipIds) {
        var longIds = traineeshipIds.stream().map(Long::parseLong).toList();
        return jobRepository.findByIdIn(longIds);
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
        var org = mapper.map(newJob.getOwner(), OrgEntity.class);
        newJob.setOwner(org);

        var newJobEntity = mapper.map(newJob, JobEntity.class);

        // TODO: há uma redundância na chamada ao banco de dados, talvez se conseguisse inserir apenas pelo id seria ótimo!
        var receiversId = newJob.getReceiversIds().stream()
                .map(Long::parseLong).toList();
        if(!receiversId.isEmpty()) {
            var receiversEntities = orgRepository.findAllByIdIn(receiversId, OrgEntity.class);
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
    public JobPublicDetailsProjection getJobPublicDetails(String id) {
        var job = getOptionalOrThrow(
                Long.parseLong(id),
                jobRepository::findByIdPublicDetails
        );
        return mapper.map(job, JobPublicDetailsDTO.class);
//        var job = getOptionalOrThrow(
//                Long.parseLong(id),
//                parsedId -> jobRepository.findById(parsedId, JobPublicDetailsProjection.class)
//        );
//        if(job.getAddress() != null)
//            job.getAddress().getId();
//        if(job.getContact() != null)
//            job.getContact().getId();
//        if(job.getAreas() != null) {
//            job.getAreas().forEach(IArea::getId);
//        }
//        if(job.getAddress() != null)
//            job.getOwner().getId();
//        job.getFormat();
//        job.getPeriod();
//        job.getLevel();
//        if(job.getOwner() != null)
//            job.getOwner().getId();
//        jsonParser.printValue(job);
//        return job;
    }


    @Override
    @Transactional
    public JobPrivateDetailsProjection getJobPrivateDetails(String id) {
        var job = getOptionalOrThrow(
                Long.parseLong(id),
                jobRepository::findByIdPublicDetails
        );
        return mapper.map(job, JobPrivateDetailsDTO.class);
        // Necessário chamar aqui devido ao lazy loading
//        if(job.getAddress() != null)
//            job.getAddress().getId();
//        if(job.getContact() != null)
//            job.getContact().getId();
//        if(job.getAreas() != null) {
//            job.getAreas().forEach(IArea::getId);
//        }
//        if(job.getAddress() != null)
//            job.getOwner().getId();
//        job.getFormat();
//        job.getPeriod();
//        job.getLevel();
//        return job;
    }


    @Override
    public JobPublicDetailsProjection setJobApprovedByOrg(String traineeshipId, String organizationId) {
        //var moderatedJobsEntity = getModeratedEntity(traineeshipId, organizationId);
        this.approve(Long.parseLong(organizationId), Long.parseLong(traineeshipId));
        return jobRepository.findById(Long.parseLong(traineeshipId), JobPublicDetailsProjection.class).orElse(null);
    }

    private void approve(long orgId, long jobId) {
        var moderatedJobsEntity = new ModeratedJobsEntity();
        moderatedJobsEntity.setOrgId(orgId);
        moderatedJobsEntity.setJobId(jobId);
        moderatedJobsEntity.setStatusId(ModerationStatusEnum.APPROVED.getId());
        moderatedJobRepository.save(moderatedJobsEntity);
    }

    private void reject(long orgId, long jobId) {
        var moderatedJobsEntity = new ModeratedJobsEntity();
        moderatedJobsEntity.setOrgId(orgId);
        moderatedJobsEntity.setJobId(jobId);
        moderatedJobsEntity.setStatusId(ModerationStatusEnum.REJECTED.getId());
        moderatedJobRepository.save(moderatedJobsEntity);
    }


    @Override
    public JobPublicDetailsProjection setJobRejectedByOrg(String traineeshipId, String organizationId) {
        this.reject(Long.parseLong(organizationId), Long.parseLong(traineeshipId));
        return jobRepository.findById(Long.parseLong(traineeshipId), JobPublicDetailsProjection.class).orElse(null);
    }

    @Override
    @Transactional
    public void setJobApprovedByOrg(List<IJob> jobs, String organizationId) {
        for(IJob job : jobs) {
            this.approve(Long.parseLong(organizationId), Long.parseLong(job.getId()));
        }
    }

    @Override
    @Transactional
    public void setJobRejectedByOrg(List<IJob> jobs, String organizationId) {
        for(IJob job : jobs) {
            this.reject(Long.parseLong(organizationId), Long.parseLong(job.getId()));
        }
    }

    @Override
    public List<JobPublicDetailsProjection> findAllPublicJobsSummary() {
        return jobRepository.findAllByExclusiveReceiversEmpty(JobPublicDetailsProjection.class);
    }


    @Override
    public List<JobPublicDetailsProjection> getAllApprovedSummaryFromOrg(String orgId) {
        return jobRepository.findAllByModeratedJobsOrgIdAndModeratedJobsStatusId(
                Long.parseLong(orgId),
                ModerationStatusEnum.APPROVED.getId(),
                        JobPublicDetailsProjection.class);
    }

    @Override
    public List<JobPublicDetailsProjection> getAllRejectedBy(String orgId) {
        var entities = jobRepository.findAllModeratedByOrgAndStatus(
                Long.parseLong(orgId),
                ModerationStatusEnum.REJECTED.getId());
        return this.mapToPublicDetailsList(entities);
    }

    private List<JobPublicDetailsProjection> mapToPublicDetailsList(List<JobEntity> entities) {
        var jobs = new ArrayList<JobPublicDetailsProjection>();
        entities.forEach(job ->
                jobs.add(mapper.map(job, JobPublicDetailsDTO.class))
        );
        return jobs;
    }

    @Override
    public List<JobPublicDetailsProjection> getAllToBeModeratedBy(String orgId) {
        var parsedId = Long.parseLong(orgId);

        var entities = jobRepository.findAllPending(parsedId);
        return this.mapToPublicDetailsList(entities);
        // TODO DB: otimizar query
//        var moderatedIds = moderatedJobRepository
//                .findAllByOrganizationId(orgLongId)
//                .map(ModeratedJobsEntity::getJobId).toList();
//        return jobRepository
//                .findAllByOwnerIdNotAndIdNotInAndExclusiveReceiversEmptyOrExclusiveReceiversId(
//                        orgLongId, moderatedIds, orgLongId,
//                        JobPublicDetailsProjection.class);
    }

    @Override
    @Transactional
    public Page<JobPrivateDetailsProjection> getAllCreatedBy(String orgId) {
        var parsedId = Long.parseLong(orgId);
        var entities = jobRepository.findAllByOwnerId(parsedId);
        if(entities.isEmpty()) {
            return new PageImpl<JobPrivateDetailsProjection>(List.of(), Pageable.ofSize(10), 10);
        }

        entities = jobRepository.findAllByOwnerIdWithReceivers(parsedId);
        var jobs = new ArrayList<JobPrivateDetailsProjection>();
        entities.forEach(job -> {
            jobs.add(mapper.map(job, JobPrivateDetailsDTO.class));
        });
        return new PageImpl<JobPrivateDetailsProjection>(jobs, Pageable.ofSize(10), 10);



//        return jobRepository.findAllByOwnerId(
//            Long.parseLong(orgId),
//        PageRequest.of(0, 100),
//            JobPrivateDetailsProjection.class);


    }

    @Override
    public List<JobPublicDetailsProjection> getAllCreatedOrApprovedBy(String orgId) {
        var organizationId = Long.parseLong(orgId);
        var entities = jobRepository.findAllCreatedOrModeratedByOrg(organizationId, ModerationStatusEnum.APPROVED.getId());
        return this.mapToPublicDetailsList(entities);
//        var approvedStatusId = ModerationStatusEnum.APPROVED.getId();
//        return jobRepository
//                .findAllByOwnerIdOrModeratedJobsOrgIdAndModeratedJobsStatusId(
//                organizationId, organizationId,
//                        approvedStatusId, JobPublicDetailsProjection.class);
    }

    @Override
    public List<JobPublicDetailsProjection> getExclusiveReceivedJobsSummaryForOrg(String orgId) {
        return jobRepository
                .findAllByExclusiveReceiversId(Long.parseLong(orgId),
                        JobPublicDetailsProjection.class);
    }

    @Override
    public boolean isJobOfferedToOrg(String jobId, String orgId) {
        var jobLongId = Long.parseLong(jobId);
        var orgLongId = Long.parseLong(orgId);
        return jobRepository
                .existsByIdAndExclusiveReceiversEmptyOrExclusiveReceiversId(
                        jobLongId, orgLongId);
    }

    @Override
    public ModerationDetailsProjection getModerationInfo(String orgId, String jobId) {
        return moderatedJobRepository
                .findByJobIdAndOrganizationId(
                        Long.parseLong(jobId),
                        Long.parseLong(orgId),
                        ModerationDetailsProjection.class )
                .orElseThrow(EntityNotFoundException::new);
    }

}
