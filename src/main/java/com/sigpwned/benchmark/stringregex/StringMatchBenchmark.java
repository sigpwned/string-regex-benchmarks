package com.sigpwned.benchmark.stringregex;

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
public class StringMatchBenchmark {
    /**
     * List of endpoints from Swagger Petstore example API spec as literal strings
     *
     * @see <a href="https://petstore.swagger.io/v2/swagger.json">https://petstore.swagger.io/v2/swagger.json</a>
     */
    public static final String[] STRING_HAYSTACKS=new String[] {
        "/pet/{petId}/uploadImage",
        "/pet",
        "/pet/findByStatus",
        "/pet/findByTags",
        "/pet/{petId}",
        "/store/order",
        "/store/order/{orderId}",
        "/store/inventory",
        "/user/createWithArray",
        "/user/createWithList",
        "/user/{username}",
        "/user/login",
        "/user/logout",
        "/user"
    };

    /**
     * List of endpoints from Swagger Petstore example API spec as literal strings
     *
     * @see <a href="https://petstore.swagger.io/v2/swagger.json">https://petstore.swagger.io/v2/swagger.json</a>
     */
    public static final Pattern[] REGEX_HAYSTACKS=new Pattern[] {
        Pattern.compile("/pet/([^/]+)/uploadImage"),
        Pattern.compile("/pet"),
        Pattern.compile("/pet/findByStatus"),
        Pattern.compile("/pet/findByTags"),
        Pattern.compile("/pet/([^/]+)"),
        Pattern.compile("/store/order"),
        Pattern.compile("/store/order/([^/]+)"),
        Pattern.compile("/store/inventory"),
        Pattern.compile("/user/createWithArray"),
        Pattern.compile("/user/createWithList"),
        Pattern.compile("/user/([^/]+)"),
        Pattern.compile("/user/login"),
        Pattern.compile("/user/logout"),
        Pattern.compile("/user")
    };

    public static final String NEEDLE="/user";

    @Benchmark
    public void stringMatch(Blackhole blackhole) {
        int index;
        for(index=0;index<STRING_HAYSTACKS.length;index++) {
            if(STRING_HAYSTACKS[index].equals(NEEDLE)) {
                break;
            }
        }
        blackhole.consume(index);
    }

    @Benchmark
    public void regexMatch(Blackhole blackhole) {
        int index;
        for(index=0;index<REGEX_HAYSTACKS.length;index++) {
            if(REGEX_HAYSTACKS[index].matcher(NEEDLE).matches()) {
                break;
            }
        }
        blackhole.consume(index);
    }
}

