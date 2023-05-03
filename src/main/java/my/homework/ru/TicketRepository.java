package my.homework.ru;

import java.util.ArrayList;
import java.util.List;

public class TicketRepository {
    private List<Ticket> tickets = new ArrayList<>();

    public List<Ticket> findAll() {
        return tickets;
    }

    public void add(Ticket ticket) {
        tickets.add(ticket);
    }

    public void removeById(int removeId) {
        Ticket removeTicket = findById(removeId);
        if (removeTicket == null) {
            throw new NotFoundException(removeId);
        }

        Ticket[] tmp = new Ticket[tickets.size() - 1];

        int index = 0;
        for (Ticket product : tickets) {
            if (product.getId() != removeId) {
                tmp[index] = product;
                index++;
            }
        }
        tickets = List.of(tmp);
    }

    public Ticket findById(int id) {
        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }
}




