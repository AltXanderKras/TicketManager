package my.homework.ru;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicketManagerTest {
    @Test
    public void testFindAll() {
        TicketRepository repository = new TicketRepository();
        TicketManager manager = new TicketManager(repository);

        Ticket ticket1 = new Ticket(1, 200, "MSK", "SPB", 93);
        Ticket ticket2 = new Ticket(2, 100, "MSK", "SPB", 98);
        Ticket ticket3 = new Ticket(3, 200, "MSK", "UFA", 186);
        Ticket ticket4 = new Ticket(4, 400, "MSK", "SPB", 122);
        Ticket ticket5 = new Ticket(5, 800, "UFA", "SPB", 222);
        Ticket ticket6 = new Ticket(6, 200, "MSK", "SPB", 130);
        Ticket ticket7 = new Ticket(7, 300, "NEW", "LA", 243);
        Ticket ticket8 = new Ticket(8, 500, "MSK", "SPB", 99);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);

        Ticket[] expected = {ticket2, ticket1, ticket6, ticket4, ticket8};
        Ticket[] actual = manager.findAll("MSK", "SPB");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testFindAllNoMatches() {
        TicketRepository repository = new TicketRepository();
        TicketManager manager = new TicketManager(repository);

        Ticket ticket1 = new Ticket(1, 200, "MSK", "SPB", 93);
        Ticket ticket2 = new Ticket(2, 100, "MSK", "SPB", 98);
        Ticket ticket3 = new Ticket(3, 200, "MSK", "UFA", 186);
        Ticket ticket4 = new Ticket(4, 400, "MSK", "SPB", 122);
        Ticket ticket5 = new Ticket(5, 800, "UFA", "SPB", 222);
        Ticket ticket6 = new Ticket(6, 200, "MSK", "SPB", 130);
        Ticket ticket7 = new Ticket(7, 300, "NEW", "LA", 243);
        Ticket ticket8 = new Ticket(8, 500, "MSK", "SPB", 99);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);

        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("MSK", "NEW");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testGetById() {
        TicketRepository repository = new TicketRepository();
        TicketManager manager = new TicketManager(repository);

        Ticket ticket1 = new Ticket(1, 200, "MSK", "SPB", 93);
        Ticket ticket2 = new Ticket(2, 100, "MSK", "SPB", 98);
        Ticket ticket3 = new Ticket(3, 200, "MSK", "UFA", 186);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);

        Ticket expected = ticket2;
        Ticket actual = manager.getById(2);
        assertEquals(expected, actual);
    }

    @Test
    public void testFindAllSortByPrice() {
        TicketRepository repository = new TicketRepository();
        TicketManager manager = new TicketManager(repository);

        Ticket ticket1 = new Ticket(1, 200, "MSK", "SPB", 93);
        Ticket ticket2 = new Ticket(2, 100, "MSK", "SPB", 98);
        Ticket ticket3 = new Ticket(3, 200, "MSK", "UFA", 186);
        Ticket ticket4 = new Ticket(4, 400, "MSK", "SPB", 122);
        Ticket ticket5 = new Ticket(5, 800, "UFA", "SPB", 222);
        Ticket ticket6 = new Ticket(6, 200, "MSK", "SPB", 130);
        Ticket ticket7 = new Ticket(7, 300, "NEW", "LA", 243);
        Ticket ticket8 = new Ticket(8, 500, "MSK", "SPB", 99);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);

        Ticket[] expected = {ticket2, ticket1, ticket6, ticket4, ticket8};
        Ticket[] actual = manager.findAll("MSK", "SPB");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testFindById() {
        TicketRepository repository = new TicketRepository();
        TicketManager manager = new TicketManager(repository);

        Ticket ticket1 = new Ticket(1, 200, "MSK", "SPB", 93);
        Ticket ticket2 = new Ticket(2, 100, "MSK", "SPB", 98);
        Ticket ticket3 = new Ticket(3, 200, "MSK", "UFA", 186);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);

        Ticket expected = ticket2;
        Ticket actual = manager.getById(2);

        assertEquals(expected, actual);
    }


    @Test
    public void TestNotFoundException() {
        TicketRepository repository = new TicketRepository();
        TicketManager manager = new TicketManager(repository);

        Ticket ticket1 = new Ticket(1, 200, "MSK", "SPB", 93);
        Ticket ticket2 = new Ticket(2, 100, "MSK", "SPB", 98);
        Ticket ticket3 = new Ticket(3, 200, "MSK", "UFA", 186);
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);

        Ticket expected = ticket2;
        Ticket actual = manager.getById(2);
        assertEquals(expected, actual);

        assertThrows(NotFoundException.class, () -> {
            manager.getById(4);
        });
    }


    @Test
    public void testRemoveById() {
        TicketRepository repository = new TicketRepository();
        TicketManager manager = new TicketManager(repository);

        Ticket ticket1 = new Ticket(1, 200, "MSK", "SPB", 93);
        Ticket ticket2 = new Ticket(2, 100, "MSK", "SPB", 98);

        manager.add(ticket1);
        manager.add(ticket2);

        manager.removeById(ticket1.getId());

        Ticket[] expected = {ticket2};
        Ticket[] actual = manager.findAll(ticket1.getFromAirport(), ticket1.getToAirport());
        assertArrayEquals(expected, actual);

        assertThrows(NotFoundException.class, () -> manager.removeById(ticket1.getId()));
    }
}
