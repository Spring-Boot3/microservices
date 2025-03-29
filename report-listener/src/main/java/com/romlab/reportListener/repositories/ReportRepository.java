package com.romlab.reportListener.repositories;

import com.romlab.reportListener.documents.ReportDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends MongoRepository<ReportDocument, String> {
}
