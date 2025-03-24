package com.romlab.report_ms.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO implements Serializable {

    private Long id;
    private String name;
    private String founder;
    private String logo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate foundationDate;
    private List<WebSiteDTO> webSites;

}
