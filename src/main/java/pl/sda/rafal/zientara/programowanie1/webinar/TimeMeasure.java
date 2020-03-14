package pl.sda.rafal.zientara.programowanie1.webinar;

public class TimeMeasure {

    private long start;
    private long end;

    public void startMeasure() {
        start = System.nanoTime();
    }

    public void endMeasure() {
        end = System.nanoTime();

        long diff = end - start;
        System.out.println("nanos:" + diff);
    }

}
