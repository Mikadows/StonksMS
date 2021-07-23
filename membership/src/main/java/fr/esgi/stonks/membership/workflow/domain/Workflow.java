package fr.esgi.stonks.membership.workflow.domain;

import lombok.Data;

@Data
public class Workflow {
    private String memberId;
    private String companyId;
    private String from;
    private String to;
}
