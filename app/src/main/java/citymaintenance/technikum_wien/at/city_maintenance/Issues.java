package citymaintenance.technikum_wien.at.city_maintenance;

/**
 * Created by UAS on 2017.12.16..
 */

public class Issues {
    private String category_name;
    private String id_issue;
    private String description;
    private String status;

    public Issues(String category_name, String id_issue, String description, String status) {
        this.category_name = category_name;
        this.id_issue = id_issue;
        this.description = description;
        this.status = status;
    }

    public void setCategory_name(String category_name) {

        this.category_name = category_name;
    }

    public void setId_issue(String id_issue) {
        this.id_issue = id_issue;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory_name() {

        return category_name;
    }

    public String getId_issue() {
        return id_issue;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }
}
