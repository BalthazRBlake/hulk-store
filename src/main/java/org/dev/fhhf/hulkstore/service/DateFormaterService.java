package org.dev.fhhf.hulkstore.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateFormaterService {

	public Date giveFormat(Date date){
		
		try {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = format.format(date);
		date = format.parse (dateString);
		} catch(ParseException ex) {
			System.out.println("Bad Date Format   :::   ");
		}
		
		return date;
	}
}
