package developer.exam.live.vi.utils;

public class HomeDataItem {

    private String country_name;
    private String country_total_cases;
    private String country_recovered_cases;
    private String country_death_cases;
    private String country_new_cases;

    public HomeDataItem(String country_name, String country_total_cases, String country_recovered_cases, String country_death_cases, String country_new_cases) {
        this.country_name = country_name;
        this.country_total_cases = country_total_cases;
        this.country_recovered_cases = country_recovered_cases;
        this.country_death_cases = country_death_cases;
        this.country_new_cases = country_new_cases;
    }

    public String getCountry_name() {
        return country_name;
    }

    public String getCountry_total_cases() {
        return country_total_cases;
    }

    public String getCountry_recovered_cases() {
        return country_recovered_cases;
    }

    public String getCountry_death_cases() {
        return country_death_cases;
    }

    public String getCountry_new_cases() {
        return country_new_cases;
    }

}
