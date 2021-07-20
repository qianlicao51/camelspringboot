package com.study.book.optjava.jmh;

import org.HdrHistogram.Histogram;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/7/16 11:41
 */
public class BenchmarkWithHdrHistogram {
    private static final long NORMALIZER = 1_000_000;

    private static final Histogram HISTOGRAM
            = new Histogram(TimeUnit.MINUTES.toMicros(1), 2);

    @Test
    public void test() throws Exception {
        final String property = System.getProperty("user.dir");
        final List<String> values = Files.readAllLines(Paths.get(property + "/pom.xml"));
        double last = 0;
        for (final String tVal : values) {
            double parsed = Double.parseDouble(tVal);
            double gcInterval = parsed - last;
            last = parsed;
            HISTOGRAM.recordValue((long) (gcInterval * NORMALIZER));
        }
        HISTOGRAM.outputPercentileDistribution(System.out, 1000.0);
    }
}
