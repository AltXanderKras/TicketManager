package my.homework.ru;

import java.util.Arrays;
import java.util.Iterator;

public class TicketManager {

    private TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public void add(Ticket ticket) {
        repository.add(ticket);
    }

    public Ticket[] findAll(String fromAirport, String toAirport) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repository.findAll()) {
            if (matches(ticket, fromAirport, toAirport)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[result.length] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }

    public Ticket getById(int id) {
        Ticket ticket = repository.findById(id);
        if (ticket == null) {
            throw new NotFoundException(id);
        }
        return ticket;
    }


    public void removeById(int id) {
        boolean removed = false;
        Iterator<Ticket> iterator = repository.findAll().iterator();
        while (iterator.hasNext()) {
            Ticket ticket = iterator.next();
            if (ticket.getId() == id) {
                iterator.remove();
                removed = true;
                break;
            }
        }
        if (!removed) {
            throw new NotFoundException(id);
        }
    }

    private boolean matches(Ticket ticket, String fromAirport, String toAirport) {
        return ticket.getFromAirport().equalsIgnoreCase(fromAirport) &&
                ticket.getToAirport().equalsIgnoreCase(toAirport);
    }

}


