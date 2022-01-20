public class fee {
    private double water;
    private double electricity;

    public fee(double w, double e){
        this.water = w;
        this.electricity = e;
    }

    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public double getElectricity() {
        return electricity;
    }

    public void setElectricity(double electricity) {
        this.electricity = electricity;
    }

    public String toString(){
        return "Water: "+String.format("%.2f", getWater())+" Electricity: "+String.format("%.2f", getElectricity());
    }

    public double getFee(){
        return 2*getWater()+3*getElectricity();
    }

}