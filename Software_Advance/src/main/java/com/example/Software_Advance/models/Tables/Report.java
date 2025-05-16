/*package com.example.Software_Advance.models.Tables;

import com.example.Software_Advance.models.Enums.ReportType;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportType reportType;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date generatedDate;

    @Column(nullable = false)
    private Double totalAmount;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String details;
}*/