/*package com.example.Software_Advance.repositories;

import com.example.Software_Advance.models.Tables.Report;
import com.example.Software_Advance.models.Enums.ReportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByReportType(ReportType reportType);

    List<Report> findByGeneratedDateAfter(Date date);

    List<Report> findByTotalAmountGreaterThan(Double amount);

    List<Report> findByGeneratedDateBetween(Date startDate, Date endDate);
}*/