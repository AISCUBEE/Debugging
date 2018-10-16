// before debugging
package hotel.checkout;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;
import hotel.entities.Booking;
import hotel.entities.Guest;
import hotel.entities.Hotel;
import hotel.entities.Room;
import hotel.entities.RoomType;
import hotel.entities.ServiceType;
import hotel.service.RecordServiceCTL;

class CheckoutCTLTest {

	
	
	@Before
	void setUp() throws Exception {

	}


	@After
	void tearDown() throws Exception {
	}

	@Test
	void testCheckOut() {
		int creditCardNumber = 2;
        int ccv = 2;
        String guestname = "Ashmit";
        String guestAddress = "Nepal";
        int guestPhone = 524875987;
        int occupantNumber = 1;
        CreditCardType type = CreditCardType.MASTERCARD;
        RoomType roomType = RoomType.SINGLE;
        Date arrivalDate = new Date(2018, 11, 01);
        int stayLength = 1;

        CreditCard creditCard = new CreditCard(type, creditCardNumber, ccv);
        Room room = new Room(1, roomType);
        Guest guest = new Guest(guestname, guestAddress, guestPhone);
        Hotel hotel = new Hotel();
        hotel.addRoom(roomType, 1);
        Booking booking = new Booking(guest, room, arrivalDate, stayLength, occupantNumber, creditCard);
        CheckoutCTL checkoutCTL = new CheckoutCTL(hotel);
        hotel.book(room, guest, arrivalDate, stayLength, occupantNumber, creditCard);
        long confirmationNumber = booking.getConfirmationNumber();
        hotel.checkin(confirmationNumber);
        RecordServiceCTL recordService = new RecordServiceCTL(hotel);
        recordService.roomNumberEntered(room.getId());
        recordService.serviceDetailsEntered(ServiceType.BAR_FRIDGE, 100);
		checkoutCTL.changeStateRoom();
		checkoutCTL.roomIdEntered(room.getId());
		checkoutCTL.chargesAccepted(true);
		checkoutCTL.creditDetailsEntered(CreditCardType.MASTERCARD, 2, 2);
		 
	}

}
