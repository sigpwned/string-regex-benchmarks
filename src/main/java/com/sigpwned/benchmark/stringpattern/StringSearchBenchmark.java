package com.sigpwned.benchmark.stringpattern;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.zip.GZIPInputStream;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@Fork(value = 3) /* jvmArgsAppend = "-XX:+PrintCompilation" */
@OutputTimeUnit(TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@State(Scope.Benchmark)
public class StringSearchBenchmark {
    /**
     * Contains the text of the Shakespearean play Hamlet.
     */
    public String hamlet;

    @Setup
    public void setupStringSearchBenchmark() throws IOException {
        try (InputStream in = StringSearchBenchmark.class.getResourceAsStream("hamlet.txt")) {
            hamlet = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    private static final String stringPattern = "Yorick";

    private static final Pattern regexPattern = Pattern.compile("Yorick");

    @Benchmark
    public void stringSearch(Blackhole blackhole) {
        int count=0;
        int start=0;
        do {
            int index=hamlet.indexOf(stringPattern, start);
            count = count+1;
            start = index+1;
        } while(start > 0);
        blackhole.consume(count);
    }

    @Benchmark
    public void regexSearch(Blackhole blackhole) {
        int count=0;
        int start=0;
        Matcher m=regexPattern.matcher(hamlet);
        while(m.find()) {
            count = count+1;
        }
        blackhole.consume(count);
    }
}

