package org.dev.fhhf.hulkstore.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dev.fhhf.hulkstore.exception.ParseDateFormatException;
import org.springframework.stereotype.Service;

@Service
public class DateFormaterService {

	public Date giveFormat(Date date){
		
		try {
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = format.format(date);
			date = format.parse (dateString);
			
		} catch(ParseException ex) {
			throw new ParseDateFormatException("Sucedió un error procesando la fecha", ex);
		}
		return date;
	}
}
