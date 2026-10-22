package pgdp.searchengine.util;
import test.PinguLib;

public class Date {

    private final int day;
    private final int month;
    private final int year;

    public Date(int day, int month, int year) {

        if (isValidDate(day, month, year)){
            this.day = day;
            this.month = month;
            this.year = year;
        }
        else {
            System.out.println("Der " + day + "." + month + "." + year + " ist kein valides Datum.");
            this.day = -1;
            this.month = -1;
            this.year = -1;
        }

    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }




    public int yearsUntil(Date other) {

        boolean fullYear = false;

        if (this.year < other.getYear()) {
            if (isLeapYear(this.year) && daysLeftThisYear() + other.daysPassedThisYear() > 365) {
                fullYear = true;
            } else if (!isLeapYear(this.year) && daysLeftThisYear() + other.daysPassedThisYear() > 364) {
                fullYear = true;
            } else {
                fullYear = false;
            }
        }

        if (this.year > other.getYear()){
            if (isLeapYear(this.year) && daysPassedThisYear() + other.daysLeftThisYear() > 365) {
                fullYear = true;
            } else if (!isLeapYear(this.year) && daysPassedThisYear() + other.daysLeftThisYear() > 364) {
                fullYear = true;
            } else {
                fullYear = false;
            }
        }


        int yearsUntil = 0;

        if ((this.year < other.getYear()) && fullYear){
            yearsUntil = other.getYear() - this.year;
        }
        else if ((this.year < other.getYear()) && !fullYear){
            yearsUntil = (other.getYear() -1) - this.year;
        }
        else if ((this.year > other.getYear()) && fullYear) {
            yearsUntil =  (other.getYear() - 1) - this.year;
        }
        else if (this.year == other.getYear() && this.month > other.getMonth()){
            yearsUntil = -1;
        }
        else if (this.year == other.getYear() && this.month == other.getMonth() && this.day > other.getDay()) {
            yearsUntil = -1;
        }
        else{
            yearsUntil = other.getYear() - this.year;
        }

        if (!isValidDate(this.day, this.month, this.year) || !isValidDate(other.getDay(), other.getMonth(), other.getYear())){
            System.out.println("Methode auf ungültigem Datum aufgerufen!");
        }


        return yearsUntil;



    }


    public int daysUntil(Date other) {

        int totalDays = 0;

        if (this.year == other.getYear()){
            totalDays = daysLeftThisYear() - other.daysLeftThisYear();
        }
        else if (this.year < other.getYear() && other.getYear() - this.year == 1){
            totalDays = daysLeftThisYear() + other.daysPassedThisYear();
        }
        else if ((this.year < other.getYear()) && (isLeapYear(this.year) || isLeapYear(other.getYear()))
                && !(((other.getYear() - this.year) / 4) > 0)){
            totalDays = (daysLeftThisYear() + other.daysPassedThisYear() + (yearsUntil(other)-1) * 365);
        }
        else if ((this.year < other.getYear()) && (!isLeapYear(this.year) && !isLeapYear(other.getYear()))
                && !(((other.getYear() - this.year) / 4) > 0)){
            totalDays = (daysLeftThisYear() + other.daysPassedThisYear() + (yearsUntil(other)-1) * 365);
        }
        else if ((this.year < other.getYear()) && (((other.getYear() - this.year) / 4) == 1) && (isLeapYear(this.year)
                || isLeapYear(other.getYear())) && (((other.getYear() - this.year) % 4) == 0) ) {
            totalDays = (daysLeftThisYear() + other.daysPassedThisYear() + (yearsUntil(other)-1) * 365);
        }
        else if ((this.year < other.getYear()) && (((other.getYear() - this.year) / 4) == 1) && (!isLeapYear(this.year)
                && !isLeapYear(other.getYear())) && (((other.getYear() - this.year) % 4) == 0)){
            totalDays = ((daysLeftThisYear() + other.daysPassedThisYear() + (yearsUntil(other)-1) * 365)) + 1;
        }
        else if ((this.year < other.getYear()) && (((other.getYear() - this.year) / 4) >= 1) && (isLeapYear(this.year)
                || isLeapYear(other.getYear()))){
            int plus = (other.getYear() - this.year) % 4;
            if (plus == 0){
                totalDays = ((daysLeftThisYear() + other.daysPassedThisYear() + (yearsUntil(other)-1) * 365)) +
                        ((other.getYear() - this.year) / 4) -1;
            }
            else {
                totalDays = ((daysLeftThisYear() + other.daysPassedThisYear() + (yearsUntil(other)-1) * 365)) + plus-1;
            }
        }
        else if ((this.year < other.getYear()) && (((other.getYear() - this.year) / 4) >= 1) && (!isLeapYear(this.year)
                && !isLeapYear(other.getYear()))){
            int plus = (other.getYear() - this.year) % 4;
            if (plus == 0){
                totalDays = ((daysLeftThisYear() + other.daysPassedThisYear() + (yearsUntil(other)-1) * 365)) +
                        (other.getYear() - this.year) / 4;
            }
            else {
                totalDays = ((daysLeftThisYear() + other.daysPassedThisYear() + (yearsUntil(other)-1) * 365)) + plus;
            }
        }
        else if (this.year < other.getYear()){
            totalDays = (daysLeftThisYear() + other.daysPassedThisYear() + (yearsUntil(other)-1)) * 365;
        }
        else if (this.year > other.getYear() && this.year - other.getYear() == 1){
            totalDays = -daysPassedThisYear() - other.daysLeftThisYear();
        }
        else if (this.year > other.getYear() && !(((this.year - other.getYear()) / 4) > 0) && (isLeapYear(this.year)
                || isLeapYear(other.getYear()))) {
            totalDays = -daysPassedThisYear() - other.daysLeftThisYear() + (yearsUntil(other)+2) * 365;
        }
        else if (this.year > other.getYear() && !(((this.year - other.getYear()) / 4) > 0) && (!isLeapYear(this.year)
                && !isLeapYear(other.getYear()))) {
            totalDays = -daysPassedThisYear() - other.daysLeftThisYear() + (yearsUntil(other)+2) * 365;
        }
        else if (this.year > other.getYear() && (this.year - other.getYear()) / 4 == 1 && (isLeapYear(this.year)
                || isLeapYear(other.getYear())) && (this.year - other.getYear()) % 4 == 0) {
            totalDays = -daysPassedThisYear() - other.daysLeftThisYear() + (yearsUntil(other)+2) * 365;
        }
        else if (this.year > other.getYear() && (this.year - other.getYear()) / 4 == 1 && (!isLeapYear(this.year)
                && !isLeapYear(other.getYear())) && (this.year - other.getYear()) % 4 == 0){
            totalDays = (-daysPassedThisYear() - other.daysLeftThisYear() + (yearsUntil(other)+2) * 365) - 1;
        }
        else if ((this.year > other.getYear()) && (((this.year - other.getYear()) / 4) >= 1) && (isLeapYear(this.year)
                || isLeapYear(other.getYear()))){
            int plus = -((this.year - other.getYear()) % 4);
            if (plus == 0){
                totalDays = (-daysPassedThisYear() - other.daysLeftThisYear() + (yearsUntil(other)+2) * 365) -
                        ((this.year - other.getYear()) / 4) +1;
            }
            else {
                totalDays = (-daysPassedThisYear() - other.daysLeftThisYear() + (yearsUntil(other)+2) * 365) + plus+1;
            }
        }
        else if ((this.year > other.getYear()) && (((this.year - other.getYear()) / 4) >= 1) && (!isLeapYear(this.year)
                && !isLeapYear(other.getYear()))){
            int plus = -((this.year - other.getYear()) % 4);
            if (plus == 0){
                totalDays = (-daysPassedThisYear() - other.daysLeftThisYear() + (yearsUntil(other)+2) * 365) -
                        ((this.year - other.getYear()) / 4);
            }
            else {
                totalDays = (-daysPassedThisYear() - other.daysLeftThisYear() + (yearsUntil(other)+2) * 365) + plus;
            }
        }
        else if (this.year > other.getYear()){
            totalDays = -daysPassedThisYear() - other.daysLeftThisYear() + (yearsUntil(other)+2) * 365;
        }
        else {
            System.out.println("Methode auf ungültigem Datum aufgerufen!");
        }

        if (!isValidDate(this.day, this.month, this.year) || !isValidDate(other.getDay(), other.getMonth(), other.getYear())){
            System.out.println("Methode auf ungültigem Datum aufgerufen!");
        }



        return totalDays;
    }




    public boolean equals(Date other) {
        return day == other.day && month == other.month && year == other.year;
    }

    public String toString() {
        return day + "." + month + "." + year;
    }




    public static Date today() {

        return dateMillisecondsAfterNewYear1970(System.currentTimeMillis());
    }





    // <|================ HILFSMETHODEN ================|>

    public static Date dateMillisecondsAfterNewYear1970(long millis) {

        long seconds = Math.abs(millis) / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        int currentDay = (int) (hours % 24)-1;
        long months = days / 31;
        int currentMonth = (int) months % 12;
        int years = (int) (days / 365);
        int currentYear = 1970 + years;

        int minusYear = 1970 - years;
        int minusMonth = (12 - currentMonth);
        int minusDay = daysInMonth(minusMonth,minusYear) - currentDay;


        Date date = new Date(currentDay,currentMonth,currentYear);
        Date someDate = new Date(currentDay,currentMonth,1970);
        Date noDate = new Date(currentDay,1,1970);
        Date nullDate = new Date(1,1,1970);

        Date minus = new Date(minusDay,minusMonth,minusYear);
        Date someDate2 = new Date(minusDay,minusMonth,1969);
        Date noDate2 = new Date(minusDay,12,1969);
        Date nullDate2 = new Date(31,12,1969);

        return millis < 0 && years == 0 && months != 0 ? someDate2 : millis < 0 && months == 0 && days != 0 ? noDate2 : millis < 0 && days == 0 ? nullDate2 :
                millis < 0 ? minus : years == 0 && months != 0 ? someDate : months == 0 && days != 0 ? noDate : days == 0 ? nullDate : date;
    }



    public int daysLeftThisYear() {

        if (!isValidDate(this.day, this.month, this.year)){
            System.out.println("Methode auf ungültigem Datum aufgerufen!");
        }

        return daysInYear(this.year) - daysPassedThisYear();
    }



    public int daysPassedThisYear() {

        int daysPassed = 0;

        switch (this.month){
            case 1: daysPassed = this.day;
                break;
            case 2: daysPassed = 31 + this.day;
                break;
            case 3: daysPassed = 59 + this.day;
                break;
            case 4: daysPassed = 90 + this.day;
                break;
            case 5: daysPassed = 120 + this.day;
                break;
            case 6: daysPassed = 151 + this.day;
                break;
            case 7: daysPassed = 181 + this.day;
                break;
            case 8: daysPassed = 212 + this.day;
                break;
            case 9: daysPassed = 243 + this.day;
                break;
            case 10: daysPassed = 273 + this.day;
                break;
            case 11: daysPassed = 304 + this.day;
                break;
            case 12: daysPassed = 334 + this.day;
                break;
            default:
                System.out.println("Methode auf ungültigem Datum aufgerufen!");
                break;
        }

        return (isLeapYear(this.year) && this.month > 2) ? (daysPassed + 1) : daysPassed;

    }






    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year / 400 == 0);
    }

    public static int daysInMonth(int month, int year) {
        return (isLeapYear(year) && month == 2) ? 29 : (!isLeapYear(year) && month == 2) ? 28 :
                (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
                        ? 31 : 30;
    }

    public static int daysInYear(int year) {
        return (isLeapYear(year)) ? 366 : 365;
    }

    public static boolean isValidDate(int day, int month, int year) {
        return (day <= daysInMonth(month,year)) && (month < 13) && (0 < month) && (0 < day);

    }



}

