package com.example.hotel.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.example.hotel.model.Hotel;

@Service
public class HotelService {
	List<Hotel> hotelList=new ArrayList<Hotel>();
	Map<Hotel,Integer> hotelMap=new HashMap<>();
	
	public void createHotel(Hotel hotel) {
		System.out.println("Inside service class createHotel()");
		hotelList.add(hotel);
		hotelMap.put(hotel, hotel.getId());
		System.out.println("Displaying Hotel List Now:");
		System.out.println(hotelList.toString());
	
		
	}

}
