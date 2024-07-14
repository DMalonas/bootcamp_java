package org.d3;

public class Journalist implements ResearchInterface {
    private String expertise;
    private String organisation;
    private int yearsOfExperience;


    @Override
    public boolean worksAbroad() {
        return false;
    }

    public void goThere() {
        System.out.println("Went there");
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
