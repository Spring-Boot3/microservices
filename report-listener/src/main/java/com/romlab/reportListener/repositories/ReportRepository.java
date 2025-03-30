package com.romlab.reportListener.repositories;

import com.romlab.reportListener.documents.ReportDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends MongoRepository<ReportDocument, String> {

    Optional<ReportDocument> findByContentContaining(String content);
    
}
