package hotel.test;
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


public class Test1 {

    public Test1() {
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

    /**
     * Inorder to test the application
     * Firstly,I have filled all the booking details,checking and Record Services
     * While performing checkout the with 40 dollar is added to record services
     * to record service to the same room programatically.
     * Accessor method is used to get result
     * At last the final price is compared
     */
    @Test
    public void SerciveCharge_Test() {
        System.out.println("The service charge 0.0 is tested");
        Hotel hotel = new Hotel();
        /*------------ Booking Operation -------------*/
        System.out.println("#Booking Start#");
        Guest guest = new Guest("Santosh", "Reservoir", 1123456);
        Date arrivalDate = new Date();
        int stayLength = 3;
        int numberOfOccupants = 2;
        CreditCard creditCard = new CreditCard(CreditCardType.VISA, 1, 2);
        Room room = new Room(1, RoomType.SINGLE);
        Booking booking = room.book(guest, arrivalDate, stayLength, numberOfOccupants, creditCard);
        hotel.book(room, guest, arrivalDate, 2, 1, creditCard); //Confirmation Number
        long confNumber = booking.getConfirmationNumber();
        System.out.println("#Booking End#");
        /*------------ Booking End -------------*/
 /*------------ Checkin Start -------------*/
        System.out.println("#Checkin Start#");
        CheckinCTL checkin = new CheckinCTL(hotel);
        checkin.confirmationNumberEntered(confNumber);
        checkin.checkInConfirmed(true);
        System.out.println("#Check In End#");
        /*------------ Check In End -------------*/
 /*------------ Record Service -------------*/
        System.out.println("#Record Service Start#");
        RecordServiceCTL recordServiceCTL = new RecordServiceCTL(hotel);
        recordServiceCTL.roomNumberEntered(room.getId());

        System.out.println("State before method call: " + recordServiceCTL.getState());
        ServiceType serviceType = ServiceType.ROOM_SERVICE;
        double cost = 20.0;
        recordServiceCTL.serviceDetailsEntered(serviceType, cost);
        System.out.println("State after method call: " + recordServiceCTL.getState());
        System.out.println("#Record Service End#");
        /*------------ Record Service END -------------*/
 /*------------ Checkout Start -------------*/
        System.out.println("#CheckOut Start#");
        CheckoutCTL checkoutCTL = new CheckoutCTL(hotel);
        checkoutCTL.setState();
        checkoutCTL.roomIdEntered(room.getId());
        System.out.println("Actual Total cost for service: " + cost);
        double systemReturnedCost = checkoutCTL.getTotal();
        System.out.println("System returned cost for service: " + systemReturnedCost);
        checkoutCTL.completed();
        hotel.checkout(room.getId());
        System.out.println("#CheckOut ENd#");
        assertEquals(cost, systemReturnedCost, 0);
    }

}