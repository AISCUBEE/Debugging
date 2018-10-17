/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

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
import java.sql.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author naren
 */
public class TestRecordServiceCTL {
    
 
    
    @Before
    void setUp() throws Exception {
    }
    
    @After
    void tearDown() throws Exception {
    }

     @Test
    
    void testCheckOut() { 
		int creditCardNumber = 3; 
		int ccv = 1; 
		String guestname = "Shyam"; 
		String guestAddress = "Melbourne";
		int guestPhone = 45897568;
		int occupantNumber = 2; 
		CreditCardType type = CreditCardType.MASTERCARD; 
		RoomType roomType = RoomType.DOUBLE; 
		Date arrivalDate = new Date(2018, 12,12); 
		int stayLength = 3; 
		CreditCard creditCard = new CreditCard(type, creditCardNumber, ccv); 
		Room room = new Room(2, roomType); 
		Guest guest = new Guest(guestname, guestAddress, guestPhone); 
		Hotel hotel = new Hotel();
		hotel.addRoom(roomType, 2); 
		Booking booking = new Booking(guest, room, arrivalDate, stayLength, occupantNumber, creditCard); 
		CheckoutCTL checkoutCTL = new CheckoutCTL(hotel);
		hotel.book(room, guest, arrivalDate, stayLength, occupantNumber, creditCard); 
		long confirmationNumber = booking.getConfirmationNumber(); 
		hotel.checkin(confirmationNumber);
		RecordServiceCTL recordService = new RecordServiceCTL(hotel); 
		recordService.roomNumberEntered(room.getId()); 
		recordService.serviceDetailsEntered(ServiceType.BAR_FRIDGE, 40); 
		checkoutCTL.changeStateRoom();
		checkoutCTL.roomIdEntered(room.getId()); 
		checkoutCTL.chargesAccepted(true); 
		checkoutCTL.creditDetailsEntered(CreditCardType.MASTERCARD, 2, 2); 
		recordService.roomNumberEntered(room.getId()); 
		recordService.serviceDetailsEntered(ServiceType.BAR_FRIDGE,40); 


 
	        } 


 
		} 

