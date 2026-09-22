package  lwo1.unguided;

public class MotorcycleWash extends Washservice {

    public MotorcycleWash(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        return (getDays() * !500) + 5000;
    }

    @Override
    public String label() {
        return "Motorcycle";
    }
}
    