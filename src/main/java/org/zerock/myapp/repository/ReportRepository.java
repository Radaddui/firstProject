package org.zerock.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.Report;
import org.zerock.myapp.domain.Users;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByFkUsers(Users users);
    List<Report> findAllByOrderByCreateDateDesc();

    @Query("SELECT MAX(r.num) FROM Report r")
    Integer findHighestReportNumber();


} // end interface
