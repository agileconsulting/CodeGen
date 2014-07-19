/**
 * 
 */
package it.aive.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author mbaraldi
 *
 */
public class DateDiff {
	
	    // The year difference between passed dates
	    private int yearDiff = 0;
	    // The month difference between passed dates, excluding years
	    private int monthDiff = 0;
	    // The day difference between passed dates, excluding years and months
	    private int dayDiff = 0;
	    // Total day difference between passed dates, including years and months 
	    private int dayOnly = 0;

	    private Calendar startDate = null;
	    private Calendar endDate = null;

	    private static final long DAY = 86400000;

	    public DateDiff(Calendar pStartDate, Calendar pEndDate)
	    {
	        startDate = Calendar.getInstance();
	        endDate = Calendar.getInstance();
	        
	        startDate.clear();
	        endDate.clear();

	        startDate.set(pStartDate.get(Calendar.YEAR), pStartDate.get(Calendar.MONTH), pStartDate.get(Calendar.DATE));        
	        endDate.set(pEndDate.get(Calendar.YEAR), pEndDate.get(Calendar.MONTH), pEndDate.get(Calendar.DATE));        
	    }

	    public void calculateDifference()
	    {
	        if( startDate == null || endDate == null || startDate.after(endDate) )
	            return;

	        dayOnly = (int) ((endDate.getTimeInMillis() - startDate.getTimeInMillis()) / DAY);

	        yearDiff = endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR);
	            
	        boolean bYearAdjusted = false;
	        startDate.add(Calendar.YEAR, yearDiff);
	        if( startDate.after(endDate) )
	        {
	            bYearAdjusted = true;
	            startDate.add(Calendar.YEAR, -1 );
	            yearDiff--;
	        }

	        monthDiff = endDate.get(Calendar.MONTH) - startDate.get(Calendar.MONTH);
	        if( bYearAdjusted && monthDiff <= 0 )
	            monthDiff = 11 + monthDiff;

	        startDate.add(Calendar.MONTH, monthDiff);
	        if( startDate.after(endDate) )
	        {
	            startDate.add(Calendar.MONTH, -1 );
	            monthDiff--;
	        }
	            
	        dayDiff = endDate.get(Calendar.DAY_OF_YEAR) - startDate.get(Calendar.DAY_OF_YEAR);
	        if( dayDiff < 0 )
	            dayDiff = 365 + dayDiff;

	        startDate.add(Calendar.DAY_OF_YEAR, dayDiff);
	    }

	    public int getYear()
	    {
	        return yearDiff;
	    }

	    public int getMonth()
	    {
	        return monthDiff;
	    }

	    public int getDay()
	    {
	        return dayDiff;
	    }

	    public int getDayOnly()
	    {
	        return dayOnly;
	    }

	    public static void main(String args[])
	    {
	        if( args.length != 2 )
	        {
	            System.out.println("\nInvalid usage : ");
	            System.out.println(" java DateDiff [startDate - dd/mm/yyyy] [endDate - dd/mm/yyyy]\n");
	            return;
	        }

	        Calendar sDate = Calendar.getInstance();
	        Calendar eDate = Calendar.getInstance();

	        sDate.set(Integer.parseInt(args[0].substring(6)), Integer.parseInt(args[0].substring(3,5))-1, Integer.parseInt(args[0].substring(0,2)));
	        eDate.set(Integer.parseInt(args[1].substring(6)), Integer.parseInt(args[1].substring(3,5))-1, Integer.parseInt(args[1].substring(0,2)));

	        DateDiff dateDiff = new DateDiff(sDate, eDate);
	        dateDiff.calculateDifference();
	        
	        SimpleDateFormat xlsDateFormater = new SimpleDateFormat("dd-MMM-yyyy");
	        
	        System.out.println("\nStart Date : "+xlsDateFormater.format(sDate.getTime()));
	        System.out.println("End Date   : "+xlsDateFormater.format(eDate.getTime()));

	        System.out.println("\nDateDiff : "+dateDiff.getYear()+" Year(s), "+dateDiff.getMonth()+" Month(s), "+dateDiff.getDay()+" Day(s)    ["+dateDiff.getDayOnly()+" days(s)]\n");
	    }
	}


