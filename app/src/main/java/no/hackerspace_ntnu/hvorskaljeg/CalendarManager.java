package no.hackerspace_ntnu.hvorskaljeg;

import java.util.Date;
import java.util.List;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;

/**
 * This is where we do everything related to downloading and parsing our calendar.
 * As the app grows, we will probably have to split this into several classes.
 */
public class CalendarManager {

  ICalendar calendar = Biweekly.parse(CALENDAR).first();

  /**
   * @return the chronologically first lecture that haven't ended yet.
   */
  VEvent getNextLecture() {
    Date now = new Date();

    List<VEvent> allLectures = calendar.getEvents();
    VEvent ourLecture = null;
    Date ourEnd = null;

    for (VEvent lecture : allLectures) {
      Date end = lecture.getDateEnd().getValue();
      if (end.after(now) && (ourLecture == null || end.before(ourEnd))) {
        ourLecture = lecture;
        ourEnd = end;
      }
    }

    return ourLecture;
  }

  // Copypasted from https://ntnu.1024.no/2017/host/YOURCALENDARNAMEHERE/ical/forelesninger
  // This is temporary, later we will download this file when the app opens
  private static String CALENDAR = "BEGIN:VCALENDAR\n" +
      "VERSION:2.0\n" +
      "METHOD:PUBLISH\n" +
      "PRODID:-//PYVOBJECT//NONSGML Version 1//EN\n" +
      "BEGIN:VTIMEZONE\n" +
      "TZID:CET\n" +
      "BEGIN:STANDARD\n" +
      "DTSTART:20001029T030000\n" +
      "RRULE:FREQ=YEARLY;BYDAY=-1SU;BYMONTH=10\n" +
      "TZNAME:CET\n" +
      "TZOFFSETFROM:+0200\n" +
      "TZOFFSETTO:+0100\n" +
      "END:STANDARD\n" +
      "BEGIN:DAYLIGHT\n" +
      "DTSTART:20000326T020000\n" +
      "RRULE:FREQ=YEARLY;BYDAY=-1SU;BYMONTH=3\n" +
      "TZNAME:CEST\n" +
      "TZOFFSETFROM:+0100\n" +
      "TZOFFSETTO:+0200\n" +
      "END:DAYLIGHT\n" +
      "END:VTIMEZONE\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90914-20170821@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170821T101500\n" +
      "DTEND;TZID=CET:20170821T120000\n" +
      "DESCRIPTION:Forelesning - Datateknologi\\, programmeringsprosjekt (TDT4113)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:EL3\n" +
      "SUMMARY:Proglab 2\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90914-20170828@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170828T101500\n" +
      "DTEND;TZID=CET:20170828T120000\n" +
      "DESCRIPTION:Forelesning - Datateknologi\\, programmeringsprosjekt (TDT4113)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:EL3\n" +
      "SUMMARY:Proglab 2\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90914-20170904@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170904T101500\n" +
      "DTEND;TZID=CET:20170904T120000\n" +
      "DESCRIPTION:Forelesning - Datateknologi\\, programmeringsprosjekt (TDT4113)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:EL3\n" +
      "SUMMARY:Proglab 2\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90914-20170911@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170911T101500\n" +
      "DTEND;TZID=CET:20170911T120000\n" +
      "DESCRIPTION:Forelesning - Datateknologi\\, programmeringsprosjekt (TDT4113)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:EL3\n" +
      "SUMMARY:Proglab 2\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90914-20170918@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170918T101500\n" +
      "DTEND;TZID=CET:20170918T120000\n" +
      "DESCRIPTION:Forelesning - Datateknologi\\, programmeringsprosjekt (TDT4113)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:EL3\n" +
      "SUMMARY:Proglab 2\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90914-20170925@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170925T101500\n" +
      "DTEND;TZID=CET:20170925T120000\n" +
      "DESCRIPTION:Forelesning - Datateknologi\\, programmeringsprosjekt (TDT4113)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:EL3\n" +
      "SUMMARY:Proglab 2\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90914-20171002@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171002T101500\n" +
      "DTEND;TZID=CET:20171002T120000\n" +
      "DESCRIPTION:Forelesning - Datateknologi\\, programmeringsprosjekt (TDT4113)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:EL3\n" +
      "SUMMARY:Proglab 2\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90914-20171009@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171009T101500\n" +
      "DTEND;TZID=CET:20171009T120000\n" +
      "DESCRIPTION:Forelesning - Datateknologi\\, programmeringsprosjekt (TDT4113)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:EL3\n" +
      "SUMMARY:Proglab 2\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90914-20171016@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171016T101500\n" +
      "DTEND;TZID=CET:20171016T120000\n" +
      "DESCRIPTION:Forelesning - Datateknologi\\, programmeringsprosjekt (TDT4113)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:EL3\n" +
      "SUMMARY:Proglab 2\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90914-20171023@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171023T101500\n" +
      "DTEND;TZID=CET:20171023T120000\n" +
      "DESCRIPTION:Forelesning - Datateknologi\\, programmeringsprosjekt (TDT4113)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:EL3\n" +
      "SUMMARY:Proglab 2\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90914-20171030@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171030T101500\n" +
      "DTEND;TZID=CET:20171030T120000\n" +
      "DESCRIPTION:Forelesning - Datateknologi\\, programmeringsprosjekt (TDT4113)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:EL3\n" +
      "SUMMARY:Proglab 2\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90914-20171106@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171106T101500\n" +
      "DTEND;TZID=CET:20171106T120000\n" +
      "DESCRIPTION:Forelesning - Datateknologi\\, programmeringsprosjekt (TDT4113)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:EL3\n" +
      "SUMMARY:Proglab 2\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90914-20171113@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171113T101500\n" +
      "DTEND;TZID=CET:20171113T120000\n" +
      "DESCRIPTION:Forelesning - Datateknologi\\, programmeringsprosjekt (TDT4113)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:EL3\n" +
      "SUMMARY:Proglab 2\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90914-20171120@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171120T101500\n" +
      "DTEND;TZID=CET:20171120T120000\n" +
      "DESCRIPTION:Forelesning - Datateknologi\\, programmeringsprosjekt (TDT4113)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:EL3\n" +
      "SUMMARY:Proglab 2\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90922-20170821@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170821T151500\n" +
      "DTEND;TZID=CET:20170821T180000\n" +
      "DESCRIPTION:Forlesning/Øving - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90922-20170828@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170828T151500\n" +
      "DTEND;TZID=CET:20170828T180000\n" +
      "DESCRIPTION:Forlesning/Øving - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90922-20170904@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170904T151500\n" +
      "DTEND;TZID=CET:20170904T180000\n" +
      "DESCRIPTION:Forlesning/Øving - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90922-20170911@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170911T151500\n" +
      "DTEND;TZID=CET:20170911T180000\n" +
      "DESCRIPTION:Forlesning/Øving - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90922-20170918@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170918T151500\n" +
      "DTEND;TZID=CET:20170918T180000\n" +
      "DESCRIPTION:Forlesning/Øving - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90922-20170925@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170925T151500\n" +
      "DTEND;TZID=CET:20170925T180000\n" +
      "DESCRIPTION:Forlesning/Øving - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90922-20171002@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171002T151500\n" +
      "DTEND;TZID=CET:20171002T180000\n" +
      "DESCRIPTION:Forlesning/Øving - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90922-20171009@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171009T151500\n" +
      "DTEND;TZID=CET:20171009T180000\n" +
      "DESCRIPTION:Forlesning/Øving - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90922-20171016@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171016T151500\n" +
      "DTEND;TZID=CET:20171016T180000\n" +
      "DESCRIPTION:Forlesning/Øving - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90922-20171023@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171023T151500\n" +
      "DTEND;TZID=CET:20171023T180000\n" +
      "DESCRIPTION:Forlesning/Øving - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90922-20171030@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171030T151500\n" +
      "DTEND;TZID=CET:20171030T180000\n" +
      "DESCRIPTION:Forlesning/Øving - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90922-20171106@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171106T151500\n" +
      "DTEND;TZID=CET:20171106T180000\n" +
      "DESCRIPTION:Forlesning/Øving - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90922-20171113@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171113T151500\n" +
      "DTEND;TZID=CET:20171113T180000\n" +
      "DESCRIPTION:Forlesning/Øving - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90922-20171120@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171120T151500\n" +
      "DTEND;TZID=CET:20171120T180000\n" +
      "DESCRIPTION:Forlesning/Øving - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90925-20170823@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170823T081500\n" +
      "DTEND;TZID=CET:20170823T100000\n" +
      "DESCRIPTION:Forelesning - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90925-20170830@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170830T081500\n" +
      "DTEND;TZID=CET:20170830T100000\n" +
      "DESCRIPTION:Forelesning - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90925-20170906@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170906T081500\n" +
      "DTEND;TZID=CET:20170906T100000\n" +
      "DESCRIPTION:Forelesning - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90925-20170913@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170913T081500\n" +
      "DTEND;TZID=CET:20170913T100000\n" +
      "DESCRIPTION:Forelesning - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90925-20170920@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170920T081500\n" +
      "DTEND;TZID=CET:20170920T100000\n" +
      "DESCRIPTION:Forelesning - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90925-20170927@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170927T081500\n" +
      "DTEND;TZID=CET:20170927T100000\n" +
      "DESCRIPTION:Forelesning - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90925-20171004@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171004T081500\n" +
      "DTEND;TZID=CET:20171004T100000\n" +
      "DESCRIPTION:Forelesning - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90925-20171011@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171011T081500\n" +
      "DTEND;TZID=CET:20171011T100000\n" +
      "DESCRIPTION:Forelesning - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90925-20171018@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171018T081500\n" +
      "DTEND;TZID=CET:20171018T100000\n" +
      "DESCRIPTION:Forelesning - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90925-20171025@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171025T081500\n" +
      "DTEND;TZID=CET:20171025T100000\n" +
      "DESCRIPTION:Forelesning - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90925-20171101@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171101T081500\n" +
      "DTEND;TZID=CET:20171101T100000\n" +
      "DESCRIPTION:Forelesning - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90925-20171108@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171108T081500\n" +
      "DTEND;TZID=CET:20171108T100000\n" +
      "DESCRIPTION:Forelesning - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90925-20171115@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171115T081500\n" +
      "DTEND;TZID=CET:20171115T100000\n" +
      "DESCRIPTION:Forelesning - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90925-20171122@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171122T081500\n" +
      "DTEND;TZID=CET:20171122T100000\n" +
      "DESCRIPTION:Forelesning - Algoritmer og datastrukturer (TDT4120)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:AlgDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90938-20170823@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170823T121500\n" +
      "DTEND;TZID=CET:20170823T140000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90938-20170830@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170830T121500\n" +
      "DTEND;TZID=CET:20170830T140000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90938-20170906@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170906T121500\n" +
      "DTEND;TZID=CET:20170906T140000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90938-20170913@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170913T121500\n" +
      "DTEND;TZID=CET:20170913T140000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90938-20170920@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170920T121500\n" +
      "DTEND;TZID=CET:20170920T140000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90938-20170927@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170927T121500\n" +
      "DTEND;TZID=CET:20170927T140000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90938-20171004@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171004T121500\n" +
      "DTEND;TZID=CET:20171004T140000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90938-20171011@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171011T121500\n" +
      "DTEND;TZID=CET:20171011T140000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90938-20171018@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171018T121500\n" +
      "DTEND;TZID=CET:20171018T140000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90938-20171025@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171025T121500\n" +
      "DTEND;TZID=CET:20171025T140000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90938-20171101@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171101T121500\n" +
      "DTEND;TZID=CET:20171101T140000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90938-20171108@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171108T121500\n" +
      "DTEND;TZID=CET:20171108T140000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90938-20171115@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171115T121500\n" +
      "DTEND;TZID=CET:20171115T140000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90938-20171122@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171122T121500\n" +
      "DTEND;TZID=CET:20171122T140000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90939-20170823@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170823T141500\n" +
      "DTEND;TZID=CET:20170823T150000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90939-20170830@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170830T141500\n" +
      "DTEND;TZID=CET:20170830T150000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90939-20170906@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170906T141500\n" +
      "DTEND;TZID=CET:20170906T150000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90939-20170913@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170913T141500\n" +
      "DTEND;TZID=CET:20170913T150000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90939-20170920@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170920T141500\n" +
      "DTEND;TZID=CET:20170920T150000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90939-20170927@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170927T141500\n" +
      "DTEND;TZID=CET:20170927T150000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90939-20171004@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171004T141500\n" +
      "DTEND;TZID=CET:20171004T150000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90939-20171011@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171011T141500\n" +
      "DTEND;TZID=CET:20171011T150000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90939-20171018@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171018T141500\n" +
      "DTEND;TZID=CET:20171018T150000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90939-20171025@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171025T141500\n" +
      "DTEND;TZID=CET:20171025T150000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90939-20171101@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171101T141500\n" +
      "DTEND;TZID=CET:20171101T150000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90939-20171108@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171108T141500\n" +
      "DTEND;TZID=CET:20171108T150000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90939-20171115@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171115T141500\n" +
      "DTEND;TZID=CET:20171115T150000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-90939-20171122@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171122T141500\n" +
      "DTEND;TZID=CET:20171122T150000\n" +
      "DESCRIPTION:Forelesning - Datamaskiner og digitalteknikk (TDT4160)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:DigDat\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91696-20170822@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170822T141500\n" +
      "DTEND;TZID=CET:20170822T160000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91696-20170829@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170829T141500\n" +
      "DTEND;TZID=CET:20170829T160000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91696-20170905@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170905T141500\n" +
      "DTEND;TZID=CET:20170905T160000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91696-20170912@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170912T141500\n" +
      "DTEND;TZID=CET:20170912T160000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91696-20170919@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170919T141500\n" +
      "DTEND;TZID=CET:20170919T160000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91696-20170926@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170926T141500\n" +
      "DTEND;TZID=CET:20170926T160000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91696-20171003@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171003T141500\n" +
      "DTEND;TZID=CET:20171003T160000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91696-20171010@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171010T141500\n" +
      "DTEND;TZID=CET:20171010T160000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91696-20171017@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171017T141500\n" +
      "DTEND;TZID=CET:20171017T160000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91696-20171024@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171024T141500\n" +
      "DTEND;TZID=CET:20171024T160000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91696-20171031@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171031T141500\n" +
      "DTEND;TZID=CET:20171031T160000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91696-20171107@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171107T141500\n" +
      "DTEND;TZID=CET:20171107T160000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91696-20171114@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171114T141500\n" +
      "DTEND;TZID=CET:20171114T160000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91696-20171121@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171121T141500\n" +
      "DTEND;TZID=CET:20171121T160000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:F1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91700-20170824@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170824T081500\n" +
      "DTEND;TZID=CET:20170824T100000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:A1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91700-20170831@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170831T081500\n" +
      "DTEND;TZID=CET:20170831T100000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:A1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91700-20170907@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170907T081500\n" +
      "DTEND;TZID=CET:20170907T100000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:A1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91700-20170914@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170914T081500\n" +
      "DTEND;TZID=CET:20170914T100000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:A1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91700-20170921@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170921T081500\n" +
      "DTEND;TZID=CET:20170921T100000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:A1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91700-20170928@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20170928T081500\n" +
      "DTEND;TZID=CET:20170928T100000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:A1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91700-20171005@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171005T081500\n" +
      "DTEND;TZID=CET:20171005T100000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:A1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91700-20171012@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171012T081500\n" +
      "DTEND;TZID=CET:20171012T100000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:A1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91700-20171019@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171019T081500\n" +
      "DTEND;TZID=CET:20171019T100000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:A1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91700-20171026@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171026T081500\n" +
      "DTEND;TZID=CET:20171026T100000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:A1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91700-20171102@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171102T081500\n" +
      "DTEND;TZID=CET:20171102T100000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:A1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91700-20171109@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171109T081500\n" +
      "DTEND;TZID=CET:20171109T100000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:A1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91700-20171116@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171116T081500\n" +
      "DTEND;TZID=CET:20171116T100000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:A1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "BEGIN:VEVENT\n" +
      "UID:lecture-91700-20171123@ntnu.1024.no\n" +
      "DTSTART;TZID=CET:20171123T081500\n" +
      "DTEND;TZID=CET:20171123T100000\n" +
      "DESCRIPTION:Forelesning - Statistikk (TMA4240)\n" +
      "DTSTAMP:20170823T143918Z\n" +
      "LOCATION:A1\n" +
      "SUMMARY:Statistikk\n" +
      "END:VEVENT\n" +
      "X-WR-CALDESC:buruds timeplan for høsten 2017\\, forelesninger\n" +
      "X-WR-CALNAME:2017/fall/burud\n" +
      "END:VCALENDAR";
}
