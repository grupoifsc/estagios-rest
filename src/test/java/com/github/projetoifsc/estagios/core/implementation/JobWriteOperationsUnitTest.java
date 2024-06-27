package com.github.projetoifsc.estagios.core.implementation;

import static org.mockito.Mockito.mock;

public class JobWriteOperationsUnitTest {

//    IJobDAO jobRepository = mock();
//    IOrganizationDAO organizationRepository = mock();
//    JobReadOperations jobReadOperations = mock();
//
//    JobWriteOperations service = new JobWriteOperations(jobReadOperations, jobRepository, organizationRepository);
//
//    Mapper mapper = new Mapper();
//
//    IJob job;
//    IOrg organization;
//
//    @BeforeEach
//    void setUp() {
//        organization = new OrgImpl("1", false);
//
//        job = new IJobImpl();
//        job.setId("1");
//        job.setOwner(organization);
//
//    }
//
//
//    @Test
//    void createdTraineeshipHasOrganizationAsOwner() {
//        when(organizationRepository.findById(organization.getId()))
//                .thenReturn(organization);
//
//        when(jobRepository.saveAndGetId((IJobEntryData) job))
//                .thenReturn(job.getId());
//
//        when(jobRepository.getPrivateDetails(job.getId()))
//                .thenReturn((IJobPrivateDetails) job);
//
//        job.setOwner(null);
//        assertEquals(organization,
//                service.create(organization.getId(), (IJobEntryData) job).getOwner());
//    }
//
//
//    @Test
//    void schoolsCanBeReceivers() {
//
//        var schoolA = new OrgImpl("2", true);
//        var schoolB = new OrgImpl("3", true);
//
//        when(organizationRepository.findAllById(List.of(schoolA.getId(), schoolB.getId())))
//                .thenReturn(List.of(schoolA,schoolB));
//
//        job.setReceiversIds(List.of(schoolA.getId(), schoolB.getId()));
//
//        when(jobRepository.saveAndGetId((IJobEntryData) job))
//                .thenReturn(job.getId());
//
//        when(jobRepository.getPrivateDetails(job.getId()))
//                .thenReturn(job);
//
//        var created = service.create(organization.getId(), job);
//
//        when(organizationRepository.getExclusiveReceiversForJob(created.getId()))
//                .thenReturn(List.of(schoolA, schoolB));
//
//        var receivers = organizationRepository.getExclusiveReceiversForJob(created.getId());
//
//        assertTrue(receivers.contains(schoolA));
//        assertTrue(receivers.contains(schoolB));
//        assertEquals(2, receivers.size());
//
//    }
//
//
//    @Test
//    void whenHasInvalidReceiverThrowsInvalidReceiverException() {
//
//        var schoolA = new OrgImpl("2", true);
//        var schoolB = new OrgImpl("3", true);
//        var notSchool = new OrgImpl("4", false);
//
//        when(organizationRepository.findAllById(List.of(schoolA.getId(), schoolB.getId(),notSchool.getId())))
//                .thenReturn(List.of(schoolA,schoolB, notSchool));
//
//        job.setReceiversIds(List.of(schoolA.getId(), schoolB.getId(),notSchool.getId()));
//
//        assertThrows(InvalidReceiverException.class,
//                ()->service.create(organization.getId(), job));
//    }
//
//
//    @Test
//    void updatedTraineeshipHasOrganizationAsOwner() {
//        when(jobRepository.getBasicInfo(job.getId()))
//                .thenReturn(job);
//        when(organizationRepository.findById(organization.getId()))
//                .thenReturn(organization);
//        when(jobRepository.saveAndGetId(job))
//                .thenReturn(job.getId());
//        when(jobRepository.getPrivateDetails(job.getId()))
//                .thenReturn(job);
//
//        assertEquals(organization,
//                service.update(organization.getId(), job.getId(), job).getOwner());
//    }
//
//
//    @Test
//    void updateTraineeshipSanitizesReceiversList() {
//        when(organizationRepository.findById(organization.getId()))
//                .thenReturn(organization);
//
//        when(jobRepository.getBasicInfo(job.getId()))
//                .thenReturn(job);
//
//        var schoolA = new OrgImpl("2", true);
//        var schoolB = new OrgImpl("3", true);
//
//        when(organizationRepository.findAllById(List.of(schoolA.getId(), schoolB.getId())))
//                .thenReturn(List.of(schoolA,schoolB));
//
//        job.setReceiversIds(List.of(schoolA.getId(), schoolB.getId()));
//
//        when(jobRepository.saveAndGetId(job))
//                .thenReturn(job.getId());
//
//        when(jobRepository.getPrivateDetails(job.getId()))
//                .thenReturn(job);
//
//        var updated = service.update(organization.getId(), job.getId(), job);
//
//        when(organizationRepository.getExclusiveReceiversForJob(updated.getId()))
//                .thenReturn(List.of(schoolA, schoolB));
//
//        var receivers = organizationRepository.getExclusiveReceiversForJob(updated.getId());
//
//        assertTrue(receivers.contains(schoolA));
//        assertTrue(receivers.contains(schoolB));
//        assertEquals(2, receivers.size());
//
//    }
//
//
//    @Test
//    void whenUpdatedHasInvalidReceiverThrowsInvalidReceiverException() {
//        when(organizationRepository.findById(organization.getId()))
//                .thenReturn(organization);
//
//        when(jobRepository.getBasicInfo(job.getId()))
//                .thenReturn(job);
//
//        var schoolA = new OrgImpl("2", true);
//        var schoolB = new OrgImpl("3", true);
//        var notSchool = new OrgImpl("4", false);
//
//        when(organizationRepository.findAllById(List.of(schoolA.getId(), schoolB.getId(),notSchool.getId())))
//                .thenReturn(List.of(schoolA,schoolB, notSchool));
//
//        job.setReceiversIds(List.of(schoolA.getId(), schoolB.getId(),notSchool.getId()));
//
//        assertThrows(InvalidReceiverException.class,
//                ()->service.update(organization.getId(), job.getId(), job));
//    }
//
//
//
//    @Test
//    void tryToUpdateTraineeshipFromOtherOrganizationThrowsUnauthorized () {
//        var otherJob = new IJobImpl();
//        otherJob.setOwner(new OrgImpl("3", true));
//        when(jobRepository.getBasicInfo("2")).thenReturn(otherJob);
//        when(organizationRepository.findById(organization.getId())).thenReturn(organization);
//
//        assertThrows(UnauthorizedAccessException.class, ()->service.update(
//                organization.getId(),
//                "2",
//                job
//        ));
//    }
//
//
//    @Test
//    void tryDeleteTraineeshipFromOtherOrgThrowsUnauthorized() {
//        var otherTraineeship = new IJobImpl();
//        otherTraineeship.setOwner(new OrgImpl("3", true));
//        when(jobRepository.getBasicInfo("2")).thenReturn(otherTraineeship);
//        when(organizationRepository.findById(organization.getId())).thenReturn(organization);
//
//        assertThrows(UnauthorizedAccessException.class,
//                ()->service.delete(organization.getId(), "2")
//        );
//    }
//
//    private IJobPublicDetailsImpl getPublicJob() {
//        return mapper.map(job, IJobPublicDetailsImpl.class);
//    }
//
//
//    @Test
//    void tryApproveOrReject_IfNotIe_ThrowsException() {
//        var school = createSchool();
//        var notSchool = organization;
//        job.setOwner(new OrgImpl("3", false));
//
//        when(organizationRepository.findById(school.getId()))
//                .thenReturn(school);
//        when(organizationRepository.findById(notSchool.getId()))
//                .thenReturn(notSchool);
//        when(jobRepository.getBasicInfo(job.getId()))
//                .thenReturn(job);
//        when(jobRepository.setJobApprovedByOrg(job.getId(), school.getId()))
//                .thenReturn(getPublicJob());
//        when(jobRepository.setJobApprovedByOrg(job.getId(), notSchool.getId()))
//                .thenReturn(getPublicJob());
//        when(jobRepository.setJobRejectedByOrg(job.getId(), school.getId()))
//                .thenReturn(getPublicJob());
//        when(jobRepository.setJobRejectedByOrg(job.getId(), notSchool.getId()))
//                .thenReturn(getPublicJob());
//        when(jobReadOperations.getAllReceivedSummary(school))
//                .thenReturn(List.of(job));
//        when(jobReadOperations.getAllReceivedSummary(notSchool))
//                .thenReturn(List.of(job));
//
//
//        assertDoesNotThrow(() -> service.approve(school.getId(), List.of(job.getId())));
//        assertDoesNotThrow(() -> service.reject(school.getId(), List.of(job.getId())));
//        assertThrows(Exception.class, () -> service.approve(notSchool.getId(), List.of(job.getId())));
//        assertThrows(Exception.class, () -> service.reject(notSchool.getId(), List.of(job.getId())));
//
//    }
//
//    private IOrg createSchool() {
//        return new OrgImpl("2", true);
//    }
//
//    @Test
//    void tryApproveOrReject_IfOwner_ThrowsException() {
//        var school = createSchool();
//
//        when(organizationRepository.findById(school.getId()))
//                .thenReturn(school);
//        when(jobRepository.getBasicInfo(job.getId()))
//                .thenReturn(job);
//        when(jobRepository.setJobApprovedByOrg(job.getId(), school.getId()))
//                .thenReturn(getPublicJob());
//        when(jobRepository.setJobRejectedByOrg(job.getId(), school.getId()))
//                .thenReturn(getPublicJob());
//        when(jobReadOperations.getAllReceivedSummary(school))
//                .thenReturn(List.of(job));
//
//        job.setOwner(organization);
//        assertDoesNotThrow(() -> service.approve(school.getId(), List.of(job.getId())));
//        assertDoesNotThrow(() -> service.reject(school.getId(), List.of(job.getId())));
//
//        job.setOwner(school);
//        assertThrows(Exception.class, () -> service.approve(school.getId(), List.of(job.getId())));
//        assertThrows(Exception.class, () -> service.reject(school.getId(), List.of(job.getId())));
//
//    }
//
//    @Test
//    void tryApproveOrReject_IfNotReceiver_ThrowsException() {
//        var receiver = createSchool();
//        var notReceiver = createSchool();
//        notReceiver.setId("5");
//
//        when(organizationRepository.findById(receiver.getId()))
//                .thenReturn(receiver);
//        when(organizationRepository.findById(notReceiver.getId()))
//                .thenReturn(notReceiver);
//        when(jobRepository.getBasicInfo(job.getId()))
//                .thenReturn(job);
//        when(jobRepository.setJobApprovedByOrg(job.getId(), receiver.getId()))
//                .thenReturn(getPublicJob());
//        when(jobRepository.setJobApprovedByOrg(job.getId(), notReceiver.getId()))
//                .thenReturn(getPublicJob());
//        when(jobRepository.setJobRejectedByOrg(job.getId(), receiver.getId()))
//                .thenReturn(getPublicJob());
//        when(jobRepository.setJobRejectedByOrg(job.getId(), notReceiver.getId()))
//                .thenReturn(getPublicJob());
//        when(jobReadOperations.getAllReceivedSummary(receiver))
//                .thenReturn(List.of(job));
//        when(jobReadOperations.getAllReceivedSummary(notReceiver))
//                .thenReturn(List.of(new IJobImpl()));
//
//
//        assertDoesNotThrow(() -> service.approve(receiver.getId(), List.of(job.getId())));
//        assertDoesNotThrow(() -> service.reject(receiver.getId(), List.of(job.getId())));
//        assertThrows(Exception.class, () -> service.approve(notReceiver.getId(), List.of(job.getId())));
//        assertThrows(Exception.class, () -> service.reject(notReceiver.getId(), List.of(job.getId())));
//
//    }
//

}
