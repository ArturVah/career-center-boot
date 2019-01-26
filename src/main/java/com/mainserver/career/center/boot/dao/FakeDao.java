package com.mainserver.career.center.boot.dao;

import com.mainserver.career.center.boot.domain.JobAnnouncement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FakeDao {

    private static final List<JobAnnouncement> jobAnnouncements = new ArrayList<>();

    public List<JobAnnouncement> getJobAnnouncements() {
        return jobAnnouncements;
    }

    public Optional<JobAnnouncement> getJobAnnouncementById(int id) {
        if(jobAnnouncements.size() < id)
            return Optional.empty();
        return Optional.of(jobAnnouncements.get(id - 1));
    }

    public void addJobAnnouncement(JobAnnouncement jobAnnouncement) {
        jobAnnouncements.add(jobAnnouncement);
        jobAnnouncement.setId(jobAnnouncements.size());
    }
}
