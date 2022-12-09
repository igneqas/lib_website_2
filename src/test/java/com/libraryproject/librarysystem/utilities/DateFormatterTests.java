package com.libraryproject.librarysystem.utilities;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
public class DateFormatterTests {

    @InjectMocks
    DateFormatter dateFormatter;

    @Test
    public void getFormattedDate_shouldReturnFormattedDate() throws ParseException {
        // Arrange
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH);
        Date expectedDate = formatter.parse("2020-10-25 10:23");

        // Act
        Date result = dateFormatter.getFormattedDate("2020-10-25T10:23");

        // Assert
        assertEquals(expectedDate, result);
    }

    @Test
    public void getFormattedDate_wrongDateFormat_ReturnsNewDate() throws ParseException {
        // Arrange
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH);
        Date expectedDate = formatter.parse("2020-10-25 10:23");

        // Act
        Date result = dateFormatter.getFormattedDate("2020-10-25G10:23");

        // Assert
        assertNotEquals(expectedDate, result);
    }
}
