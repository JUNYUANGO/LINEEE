public class store {
    private String name;
    private String category;
    private String info;
    private String businessHour;
    private String address;
    private String number;

    public store(String name, String category, String info, String businessHour, String address, String number){
        this.name = name;
        this.category = category;
        this.info = info;
        this.businessHour = businessHour;
        this.address = address;
        this.number = number;
    }

    public String getName(){
        return name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String newCategory){
        this.category = newCategory;
    }

    public String getInfo(){
        return info;
    }

    public void setInfo(String newInfo){
        this.info = newInfo;
    }

    public String getBusinessHour(){
        return businessHour;
    }

    public void setBusinessHour(String newBusinessHour){
        this.businessHour = newBusinessHour;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String newAddress){
        this.address = newAddress;
    }

    public String getNumber(){
        return number;
    }

    public void setNumber(String newNumber){
        this.number = newNumber;
    }
}
