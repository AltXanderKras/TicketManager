package my.homework.ru;

public class Ticket implements Comparable<Ticket> {
    private int id;
    private int price;
    private String fromAirport;
    private String toAirport;
    private int duration;

    public Ticket(int id, int price, String fromAirport, String toAirport, int duration) {
        this.id = id;
        this.price = price;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public String getToAirport() {
        return toAirport;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public int compareTo(Ticket otherTicket) {
        return Integer.compare(price, otherTicket.getPrice());
    }
}