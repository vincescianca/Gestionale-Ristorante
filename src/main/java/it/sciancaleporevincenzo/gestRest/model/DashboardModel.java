package it.sciancaleporevincenzo.gestRest.model;

import lombok.Data;

@Data
public class DashboardModel {
    private String type;
    private String icon;
    private String title;
    private String value;
    private String footerText;
    private String footerIcon;
}
