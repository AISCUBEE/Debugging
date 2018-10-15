package hotel.test;
/**
 * Test Case 2 for possible charge of room after checkout
 */

import hotel.checkin.CheckinCTL;
import hotel.checkout.CheckoutCTL;
import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;
import hotel.entities.Booking;
import hotel.entities.Guest;
import hotel.entities.Hotel;
import hotel.entities.Room;
import hotel.entities.RoomType;
import hotel.entities.ServiceType;
import hotel.service.RecordServiceCTL;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Santosh
 */
public class Test2 {

    public Test2() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

  
    @Test 
    public void testReEnterServiceAfterCheckedOut() {
        try {
            System.out.println("Test All Service charges are reported as 0.00 when checking out.");
            ServiceType serviceType = ServiceType.ROOM_SERVICE;
            double cost = 20.0;
            Hotel hotel = new Hotel();
            /*------------ for booking -------------*/
            System.out.println("************booking start*************");
            Guest guest = new Guest("name1", "address1", 1123456);
            Date arrivalDate = new Date();
            int stayLength = 2;
            int numberOfOccupants = 2;
            CreditCard creditCard = new CreditCard(CreditCardType.VISA, 1, 2);
            Room room = new Room(1, RoomType.SINGLE);
            Booking booking = room.book(guest, arrivalDate, stayLength, numberOfOccupants, creditCard);
            //booking.checkIn();
            hotel.book(room, guest, arrivalDate, 2, 1, creditCard); //creates the confirmation number for bookingss
            long confNumber = booking.getConfirmationNumber();
            System.out.println("************booking end*************");
            /*------------ for booking end -------------*/
 /*------------ for checkin -------------*/
            System.out.println("************checkin start*************");
            CheckinCTL checkin = new CheckinCTL(hotel);
            checkin.confirmationNumberEntered(confNumber);
            checkin.checkInConfirmed(true);
            System.out.println("************checkin end*************");
            /*------------ check in end -------------*/
 /*------------ check out start -------------*/
            System.out.println("************check out start*************");
            CheckoutCTL checkoutCTL = new CheckoutCTL(hotel);
            checkoutCTL.setState();
            checkoutCTL.roomIdEntered(room.getId());
            checkoutCTL.completed();
            hotel.checkout(room.getId());
            System.out.println("************check out finished*************");
            System.out.println("************record service after checkout start*************");
            RecordServiceCTL recordServiceCTL1 = new RecordServiceCTL(hotel);
            recordServiceCTL1.roomNumberEntered(room.getId());
            System.out.println("State before method call later: " + recordServiceCTL1.getState());
            ServiceType serviceType1 = ServiceType.BAR_FRIDGE;
            double cost1 = 40.0;
            recordServiceCTL1.serviceDetailsEntered(serviceType1, cost1);
            System.out.println("State after method call later: " + recordServiceCTL1.getState());
            System.out.println("************record service finished*************");
            fail("No active booking for room id: 1");
        } catch (RuntimeException ex) {
            System.out.println("Expected Result: \"PayForServiceCTL: serviceDetailsEntered : bad state : ROOM\"" );
            System.out.println("System Result: \"" + ex.getMessage() + "\"" );            
            String expectedMessage = "PayForServiceCTL: serviceDetailsEntered : bad state : ROOM";
            String systemMessage = ex.getMessage();
            assertEquals(expectedMessage, systemMessage);
        }
    }
}