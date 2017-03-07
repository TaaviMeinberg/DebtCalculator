package debtCalculator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class Calculator {
	public static void main(String[] args) {
		double debt = 500;
		Calendar startDate = new GregorianCalendar(2016,0, 15);
		Calendar endDate = new GregorianCalendar(2016, 6, 15);

		Calculator calc = new Calculator();
		// System.out.println(calc.paymentNumber(startDate, endDate));
		System.out.println(calc.paymentSum(startDate, endDate, debt));
		calc.paymentPlan(startDate, endDate);
	}

	private Integer paymentNumber(Calendar start, Calendar end) {
		int yearDiff = end.get(end.YEAR) - start.get(start.YEAR);
		int monthDiff = end.get(end.MONTH) - start.get(start.MONTH);
		int sum = yearDiff * 12 + monthDiff+1;
		if (sum != 0) {
			return sum;
		} else {
			return 1;
		}
	}

	private double paymentSum(Calendar start, Calendar end, double debt) {
		return Math.round(debt / paymentNumber(start, end));
	}

	private void paymentPlan(Calendar start, Calendar end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");
		int paymentMonths = paymentNumber(start, end);
		List<Calendar> paymentDates = new ArrayList<Calendar>();
		
		//vb saab ilma claususteta
		if (paymentMonths == 1) {
			paymentDates.add(start);
		} else if (paymentMonths == 2) {
			paymentDates.add(start);
			paymentDates.add(end);
		} else {
			for (int i = 0; i < paymentMonths; i++) {
				Calendar tmp2 = Calendar.getInstance();
				tmp2.setTime(start.getTime());
				start.add(Calendar.MONTH, 1);
				paymentDates.add(tmp2);
			}
			
			for (Calendar calendar : paymentDates) {
				System.out.println(new SimpleDateFormat("YYYY - MM - dd").format(calendar.getTime()));
			}
			
		}
	}
}
