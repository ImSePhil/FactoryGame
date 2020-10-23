package framework;

import java.awt.Color;
import java.awt.Graphics2D;

public class Time {
	private int second = 0;
	private int minute = 0;
	private int hour = 7;
	private int day = 0;
	private int week = 0;
	private int month = 0;
	private int year = 1000;

	private int maxSeconds = 60;
	private int maxMinutes = 60;
	private int maxHours = 24;
	private int maxDays = 7;
	private int maxWeeks = 3;
	private int maxMonths = 12;
	private String time = "";
	private String date = "";
	private String tDate = "";

	private String[] months = new String[] { "Januar", "Februar", "März", "April", "Main", "Juni", "Juli", "August",
			"September", "Oktober", "November", "Dezember" };
	private String[] days = new String[] { "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag",
			"Sonntag" };
	
	public void update() {
		second += 10;

		if (second == maxSeconds) {
			second = 0;
			minute++;
		}

		if (minute == maxMinutes) {
			minute = 0;
			hour++;
		}

		if (hour == maxHours) {
			hour = 0;
			day++;
		}

		if (day == maxDays) {
			day = 0;
			week++;
		}
		if (week == maxWeeks) {
			week = 0;
			month++;
		}

		if (month == maxMonths) {
			month = 0;
			year++;
		}
		time = hour + ":" + minute;
		date = year + "." + month + "." + day;
		tDate = year + " " + months[month] + " " + days[day];
	}

	public void render(Graphics2D g) {
		g.setColor(new Color(0,100,100,20));
		if(hour < 8 || hour > 19)
			g.setColor(new Color(0,0,100,20));
		if(hour < 17 && hour > 10)
			g.setColor(new Color(0,100,0,20));
		
		
		g.fillRect(0, 0, Game.width, Game.height);
		g.setColor(Color.white);
		g.drawString(time, 20, 30);
		g.drawString(date, 20, 40);
		g.drawString(tDate, 20, 50);
	}
}
