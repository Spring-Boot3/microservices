package com.romlab.report_ms.models;

import com.romlab.report_ms.enums.Category;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebSiteDTO implements Serializable {

    private String name;

}
