public class tenant {
    private String name;
    private int id;
    private String nickname;
    private int apt;
    private double amount;
    private int credit;


    public tenant(String name, int id, String nickname, int apt, double amount, int credit){
        this.name = name;
        this.id = id;
        this.nickname = nickname;
        this.apt = apt;
        this.amount = amount;
        this.credit = credit;
    }

    //setter & getter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApt() {
        return apt;
    }

    public void setApt(int apt) {
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

}
