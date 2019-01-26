package com.mainserver.career.center.boot.controllers;

import com.mainserver.career.center.boot.dao.FakeDao;
import com.mainserver.career.center.boot.domain.JobAnnouncement;
import com.mainserver.career.center.boot.domain.JobTitle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final FakeDao fakeDao;

    public JobController(FakeDao fakeDao) {
        this.fakeDao = fakeDao;
    }

    @PostMapping
    //TODO must be secured for admin only call
    public int postJobAnnouncement(@RequestBody JobAnnouncement jobAnnouncement) {
        fakeDao.addJobAnnouncement(jobAnnouncement);
        return jobAnnouncement.getId();
    }

    @GetMapping
    public ResponseEntity<List<JobTitle>> getJobTitles() {
        List<JobTitle> jobTitles = fakeDao.getJobAnnouncements()
                .stream()
                .map(j -> {
                    JobTitle jobTitle = new JobTitle();
                    jobTitle.setId(j.getId());
                    jobTitle.setTitle(
                            MessageFormat.format("{0} - {1}",
                                    j.getTitle(),
                                    j.getCompany()
                            )
                    );
                    return jobTitle;
                })
                .collect(Collectors.toList());

        return jobTitles.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(jobTitles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobAnnouncement> getJobAnnouncementById(@PathVariable("id") int id) {
        return fakeDao.getJobAnnouncementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}