package com.libraryproject.librarysystem.utilities;

import com.libraryproject.librarysystem.utilities.interfaces.IDateFormatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class DateFormatter implements IDateFormatter {

    public Date getFormattedDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH);
        dateString = dateString.replace('T',' ');
        try {
            return formatter.parse(dateString);
        } catch (ParseException exception) {
            return new Date();
        }
    }
}
