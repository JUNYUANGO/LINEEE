public class Tenant {
    private String name;
    private String id;
    private String apt;
    private double amount;
    private int credit;
    private String phone;


    public Tenant(String name, String id, String apt, double amount, int credit, String phone){
        this.name = name;
        this.id = id;
        this.apt = apt;
        this.amount = amount;
        this.credit = credit;
        this.phone = phone;
    }

    //setter & getter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApt() {
        return apt;
    }

    public void setApt(String apt) {
        this.apt = apt;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getPhone(){return phone;}

    public void setPhone(String phone){this.phone = phone;}
}
