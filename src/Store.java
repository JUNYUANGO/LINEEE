public class Store {
    private String name;
    private String address;
    private String bush;
    private String category;

    public Store(String name, String address, String bush, String category){
        this.name = name;
        this.address = address;
        this.bush = bush;
        this.category = category;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setBush(String bush) {
        this.bush = bush;
    }

    public String getBush() {
        return bush;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
