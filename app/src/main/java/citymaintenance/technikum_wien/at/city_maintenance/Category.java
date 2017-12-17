package citymaintenance.technikum_wien.at.city_maintenance;

/**
 * Created by UAS on 2017.12.14..
 */

public class Category {
    int id;
    String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
      return this.getName();
    }
}
