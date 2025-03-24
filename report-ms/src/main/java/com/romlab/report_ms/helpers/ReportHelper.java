package com.romlab.report_ms.helpers;

import com.romlab.report_ms.models.CompanyDTO;
import com.romlab.report_ms.models.WebSiteDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ReportHelper {

    @Value("${report.template}")
    private String reportTemplate;

    public String readTemplate(CompanyDTO companyDTO) {
        return reportTemplate
                .replace("{company}", companyDTO.getName())
                .replace("{foundation_date}", companyDTO.getFoundationDate().toString())
                .replace("{founder}", companyDTO.getFounder())
                .replace("{web_sites}", companyDTO.getWebSites().stream().map(WebSiteDTO::getName).collect(Collectors.joining(", ")));
    }

    public List<String> getPlaceHoldersFromTemplate(String reportTemplate) {
        var split = reportTemplate.split("\\{");
        return Arrays.stream(split)
                .filter(line -> !line.isEmpty())
                .map(line -> {
                    var index = line.indexOf("}");
                    return line.substring(0, index);
                })
                .collect(Collectors.toList());
//        return Arrays.stream(split)
//                .filter(s -> s.contains("}"))
//                .map(s -> s.substring(0, s.indexOf("}")))
//                .collect(Collectors.toList());
    }

}
