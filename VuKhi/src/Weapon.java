public class Weapon {
    private String name;
    private String type;
    private int number;
    private double price;
    private String dateIn;
    private String dateOut;
    private String reason;

    public Weapon(String name, String type, int number, double price, String dateIn, String dateOut) {
        this.name = name;
        this.type = type;
        this.number = number;
        this.price = price;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
    }

    public String getName() {
        return name;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getReason() {
        return reason;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDateIn() {
        return dateIn;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }

    public void setAction(String action) {
    }
}
