//debugging
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author naren
 */
public class CheckoutCTLTest{
    
  
     
    @Before
    void setUp() throws Exception{
    }
    
    @After
    public void tearDown()throws Exception {
    }


    @Test
    void  testCheckOut() { 
        int creditCardNumber = 3; 
        int ccv = 1;
        String guestname = "Shyam";
        String guestAddress = "136, Railway Parade";
        int guestPhone =421567085;
        int occupantNumber = 2; 
        CreditCardType type = CreditCardType.MASTERCARD;
        RoomType roomType = RoomType.DOUBLE; 
        Date arrivalDate = new Date(2018, 12, 23);
        int stayLength = 6;
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
        recordService.serviceDetailsEntered(ServiceType.BAR_FRIDGE, 50);
        checkoutCTL.changeStateRoom(); 
        checkoutCTL.roomIdEntered(room.getId());
        checkoutCTL.chargesAccepted(true);
        checkoutCTL.creditDetailsEntered(CreditCardType.MASTERCARD, 3, 3); 


 
		  


 
	} 


 
 


 
} 

