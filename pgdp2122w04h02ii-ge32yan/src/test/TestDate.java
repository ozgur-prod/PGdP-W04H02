package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pgdp.searchengine.util.Date;

public class TestDate {

    // ***********************************
    // DO NOT DELETE OR CHANGE THE FOLLOWING LINES
    // ***********************************

    // Damit ihr den Konsolen-Output einfach lesen könnt.
    // Ihr könnt diese Methoden einfach ignorieren.
    @BeforeEach
    void setup() {
        PinguLib.setup();
    }

    @AfterEach
    void reset() {
        PinguLib.reset();
    }
    // ***********************************

    // Fülle jede Test-Methode mit je mindestens 5 Tests für die korrespondierende Date-Methode bzw. die zu testende
    // Funktionalität im Falle von 'testDateConstructor(In)valid()' und 'testMethodCallsOnInvalidDates()'
    // Schreibe wieder über jede Methode einen Kommentar, der die Auswahl der >= 5 Tests erklärt.

    /*  Testen des Konstruktors mit gültigem Datum
     *  Ein Paar verschiedene gültige Daten werden hier abgedeckt, um zu überprüfen, ob wirklich alle gültigen Daten
     *  korrekt behandelt werden. Es muss hier nicht unbedingt so ausführlich getestet werden, wie später 'isValidDate()'.
     *  Ein Paar Randfälle (wie den 29.Februar XYZ) abzudecken, ist aber angeraten, da der Konstruktor 'isValidDate()' falsch
     *  oder gar nicht verwenden könnte.
     */
    @Test
    public void testDateConstructorValid() {
        Date date1 = new Date(12, 11, 2021);
        Date date2 = new Date(29, 2, 2024);
        Date date3 = new Date(29, 2, 2000);
        Date date4 = new Date(24, 12, 0);
        Date date5 = new Date(11, 8, -333);
    }

    // testing 5 different invalid dates with different reasons to be invalid. is the output going to be accordingly?
    @Test
    public void testDateConstructorInvalid() {
        Date date = new Date(29, 2, 2021);
        Date date2 = new Date(-5, 2, 2021);
        Date date3 = new Date(22, -8, 2021);
        Date date4 = new Date(50, 2, 1987);
        Date date5 = new Date(23, 60, 2003);
        assertEquals("Der 29.2.2021 ist kein valides Datum.\n" +
                "Der -5.2.2021 ist kein valides Datum.\n" +
                "Der 22.-8.2021 ist kein valides Datum.\n" +
                "Der 50.2.1987 ist kein valides Datum.\n" +
                "Der 23.60.2003 ist kein valides Datum.", PinguLib.getConsoleOutput());
    }

    // testing different dates with different aspects (from zulip and artemis page)
    @Test
    public void testYearsUntil() {
        Date date1 = new Date(3,9,2001);
        Date date2 = new Date(2,9,2005);
        assertEquals(3, date1.yearsUntil(date2));

        Date date3 = new Date(3,9,2001);
        Date date4 = new Date(2,9,2012);
        assertEquals(11, date3.yearsUntil(date4));

        Date date5 = new Date(20,6,2020);
        Date date6 = new Date(19,6,2019);
        assertEquals(-2, date5.yearsUntil(date6));

        Date date7 = new Date(20,6,2020);
        Date date8 = new Date(19,6,2020);
        assertEquals(-1, date7.yearsUntil(date8));

        Date date9 = new Date(20,6,2020);
        Date date10 = new Date(8,11,2010);
        assertEquals(-10, date9.yearsUntil(date10));

        Date date11 = new Date(29,2,2016);
        Date date12 = new Date(28,2,2017);
        assertEquals(0, date11.yearsUntil(date12));

        Date date13 = new Date(29,2,2024);
        Date date14 = new Date(28,2,2020);
        assertEquals(-5, date13.yearsUntil(date14));

        Date date19 = new Date(28,2,2015);
        Date date20 = new Date(29,2,2016);
        assertEquals(1, date19.yearsUntil(date20));


    }

    // testing different dates with different aspects (from zulip and artemis page) and also some cheeky
    // dates to see if my method really works in all circumstances.
    @Test
    public void testDaysUntil() {
        Date date1 = new Date(3,9,2001);
        Date date2 = new Date(6,9,2001);
        assertEquals(3, date1.daysUntil(date2));

        Date date3 = new Date(3,9,2001);
        Date date4 = new Date(3,9,2002);
        assertEquals(365, date3.daysUntil(date4));

        Date date5 = new Date(20,6,2020);
        Date date6 = new Date(20,5,2020);
        assertEquals(-31, date5.daysUntil(date6));

        Date date7 = new Date(20,6,2020);
        Date date8 = new Date(20,6,2019);
        assertEquals(-366, date7.daysUntil(date8));

        Date date13 = new Date(1,1,2021);
        Date date14 = new Date(1,1,2025);
        assertEquals(1461, date13.daysUntil(date14));

        Date date15 = new Date(1,1,2020);
        Date date16 = new Date(1,1,2024);
        assertEquals(1461, date15.daysUntil(date16));

        Date date17 = new Date(1,1,2024);
        Date date18 = new Date(31,12,2024);
        assertEquals(365, date17.daysUntil(date18));

        Date date21 = new Date(31,12,2024);
        Date date22 = new Date(1,1,2024);
        assertEquals(-365, date21.daysUntil(date22));

        Date date23 = new Date(1,2,2020);
        Date date24 = new Date(1,2,2022);
        assertEquals(731, date23.daysUntil(date24));

        Date date25= new Date(1,2,2021);
        Date date26 = new Date(1,2,2023);
        assertEquals(730, date25.daysUntil(date26));

        Date date19 = new Date(2,1,2024);
        Date date20 = new Date(2,1,2030);
        assertEquals(2192, date19.daysUntil(date20));

        Date date27 = new Date(2,1,2024);
        Date date28 = new Date(2,1,2032);
        assertEquals(2922, date27.daysUntil(date28));

        Date date29 = new Date(2,1,2023);
        Date date30 = new Date(2,1,2031);
        assertEquals(2922, date29.daysUntil(date30));

        Date date31 = new Date(2,1,2030);
        Date date32 = new Date(2,1,2024);
        assertEquals(-2192, date31.daysUntil(date32));

        Date date33 = new Date(2,1,2032);
        Date date34 = new Date(2,1,2024);
        assertEquals(-2922, date33.daysUntil(date34));
    }

    // testing to see if the method really gives the current date and the negative date
    @Test
    public void testDateMillisecondsAfterNewYear1970() {
        Date date = new Date(22,11,2021);
        assertEquals(date.getYear(), Date.dateMillisecondsAfterNewYear1970(System.currentTimeMillis()).getYear());
        assertEquals(date.getMonth(), Date.dateMillisecondsAfterNewYear1970(System.currentTimeMillis()).getMonth());
        assertEquals(date.getDay(), Date.dateMillisecondsAfterNewYear1970(System.currentTimeMillis()).getDay());

        Date date2 = new Date(1,1,1970);
        assertEquals(date2.getDay(), Date.dateMillisecondsAfterNewYear1970(10000).getDay());
        assertEquals(date2.getMonth(), Date.dateMillisecondsAfterNewYear1970(10000).getMonth());
        assertEquals(date2.getYear(), Date.dateMillisecondsAfterNewYear1970(10000).getYear());

        Date date3 = new Date(31,12,1969);
        assertEquals(date3.getDay(), Date.dateMillisecondsAfterNewYear1970(-10000).getDay());
        assertEquals(date3.getMonth(), Date.dateMillisecondsAfterNewYear1970(-10000).getMonth());
        assertEquals(date3.getYear(), Date.dateMillisecondsAfterNewYear1970(-10000).getYear());

    }


    @Test
    public void testDaysLeftThisYear() {
        Date date = new Date(3,1,2021);
        assertEquals(362, date.daysLeftThisYear());

        Date date2 = new Date(3,1,2020);
        assertEquals(363, date2.daysLeftThisYear());

        Date date3 = new Date(1,1,2005);
        assertEquals(364, date3.daysLeftThisYear());

        Date date4 = new Date(30,12,2018);
        assertEquals(1, date4.daysLeftThisYear());

        Date date5 = new Date(1,12,2020);
        assertEquals(30, date5.daysLeftThisYear());

    }


    @Test
    public void testDaysPassedThisYear() {
        Date date = new Date(3,1,2021);
        assertEquals(3, date.daysPassedThisYear());

        Date date2 = new Date(1,3,2021);
        assertEquals(60, date2.daysPassedThisYear());

        Date date3 = new Date(1,3,2020);
        assertEquals(61, date3.daysPassedThisYear());

        Date date4 = new Date(30,12,2020);
        assertEquals(365, date4.daysPassedThisYear());

        Date date5 = new Date(30,12,2017);
        assertEquals(364, date5.daysPassedThisYear());

    }

    @Test
    public void testIsLeapYear() {
        assertEquals(false,Date.isLeapYear(2021));
        assertEquals(true,Date.isLeapYear(2004));
        assertEquals(true, Date.isLeapYear(0));
        assertEquals(true, Date.isLeapYear(4));
        assertEquals(true, Date.isLeapYear(-4));


    }

    @Test
    public void testDaysInMonth() {
        assertEquals(28,Date.daysInMonth(2,2021));
        assertEquals(29,Date.daysInMonth(2,2020));
        assertEquals(31,Date.daysInMonth(1,2005));
        assertEquals(31,Date.daysInMonth(3,2000));
        assertEquals(31,Date.daysInMonth(8,1990));
    }

    @Test
    public void testDaysInYear() {
        assertEquals(365,Date.daysInYear(2021));
        assertEquals(366,Date.daysInYear(2020));
        assertEquals(365,Date.daysInYear(1997));
        assertEquals(366,Date.daysInYear(2024));
        assertEquals(365,Date.daysInYear(2005));
    }

    @Test
    public void testIsValidDate() {
        assertEquals(true,Date.isValidDate(28,2,2021));
        assertEquals(false,Date.isValidDate(29,2,2021));
        assertEquals(false,Date.isValidDate(-5,12,2021));
        assertEquals(false,Date.isValidDate(20,13,2021));
        assertEquals(false,Date.isValidDate(5,-5,2021));
    }

    // testing different invalid dates from all of the 4 methods
    @Test
    public void testMethodCallsOnInvalidDates() {
        Date date4 = new Date(40,3,2020);
        date4.daysPassedThisYear();
        assertTrue(PinguLib.getConsoleOutput().contains("Methode auf ungültigem Datum aufgerufen!"));

        Date date3 = new Date(3,20,2020);
        date3.daysLeftThisYear();
        assertTrue(PinguLib.getConsoleOutput().contains("Methode auf ungültigem Datum aufgerufen!"));

        Date date11 = new Date(20,4,2020);
        Date date12 = new Date(-4,6,2019);
        date11.daysUntil(date12);
        assertTrue(PinguLib.getConsoleOutput().contains("Methode auf ungültigem Datum aufgerufen!"));

        Date date9 = new Date(20,34,2020);
        Date date10 = new Date(20,6,2019);
        date9.daysUntil(date10);
        assertTrue(PinguLib.getConsoleOutput().contains("Methode auf ungültigem Datum aufgerufen!"));

        Date date17 = new Date(28,2,2021);
        Date date18 = new Date(30,2,2020);
        date17.yearsUntil(date18);
        assertTrue(PinguLib.getConsoleOutput().contains("Methode auf ungültigem Datum aufgerufen!"));

        Date date15 = new Date(29,2,2021);
        Date date16 = new Date(28,2,2020);
        date15.yearsUntil(date16);
        assertTrue(PinguLib.getConsoleOutput().contains("Methode auf ungültigem Datum aufgerufen!"));
    }
}
