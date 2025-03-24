package com.romlab.report_ms.services;

import com.romlab.report_ms.helpers.ReportHelper;
import com.romlab.report_ms.models.CompanyDTO;
import com.romlab.report_ms.models.WebSiteDTO;
import com.romlab.report_ms.repositories.CompaniesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final CompaniesRepository companiesRepository;
    private final ReportHelper reportHelper;

    @Override
    public String makeReport(String name) {
        return reportHelper.readTemplate(companiesRepository.getByName(name).orElseThrow());
    }

    @Override
    public String saveReport(String nameReport) {
        var format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var placeHolders = reportHelper.getPlaceHoldersFromTemplate(nameReport);
        var webSites = Stream.of(placeHolders.get(3));
        var company = CompanyDTO.builder()
                .name(placeHolders.get(0))
                .foundationDate(LocalDate.parse(placeHolders.get(1), format))
                .founder(placeHolders.get(2))
                .webSites(List.of(webSites.map(WebSiteDTO::new).toArray(WebSiteDTO[]::new)))
                .build();
        companiesRepository.postByName(company);
        return "Saved";
    }

    @Override
    public void deleteReport(String name) {
        companiesRepository.deleteByName(name);
    }
}
