# string-regex-benchmarks

A simple JMH benchmark comparing the performance of various `String` and `Pattern` operations.
string searching using `String.indexOf(String,int)` versus regular expression `Pattern` and `Matcher#find()`.

## Takeaways

As a rule of thumb, `String` is faster than `Pattern`. Depending on the use case, a *lot* faster.

The measurement includes the following benchmarks:

* `StringMatchBenchmark.regexMatch` -- Determine if a series of short `String` object fully matches each element of a list of `Pattern` objects.
* `StringMatchBenchmark.stringMatch` -- Determine if a series of short `String` objects equals each element of a list of `String` objects.
* `StringSearchBenchmark.regexSearch` -- Determine the number of times the exact `String` "Yorick" appears in the text of Hamlet using `Pattern`.
* `StringSearchBenchmark.stringSearch` -- Determine the number of times the exact `String` "Yorick" appears in the text of Hamlet using `String`.

Here are the overall results:

```
Benchmark                            Mode  Cnt          Score          Error  Units
StringMatchBenchmark.regexMatch     thrpt   15    3675382.169 ±     9559.442  ops/s
StringMatchBenchmark.stringMatch    thrpt   15  110346323.370 ± 12741407.378  ops/s
StringSearchBenchmark.regexSearch   thrpt   15       4642.166 ±        5.847  ops/s
StringSearchBenchmark.stringSearch  thrpt   15      12705.885 ±       19.777  ops/s
```

Briefly:

* `stringMatch` is 30x faster than `regexMatch`
* `stringSearch` is 2.75x faster than `regexSearch`

## Verbatim Results

```
# JMH version: 1.37
# VM version: JDK 21.0.2, OpenJDK 64-Bit Server VM, 21.0.2+13-LTS
# VM invoker: /Users/aboothe/Library/Java/JavaVirtualMachines/corretto-21.0.2/Contents/Home/bin/java
# VM options: <none>
# Blackhole mode: compiler (auto-detected, use -Djmh.blackhole.autoDetect=false to disable)
# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: com.sigpwned.benchmark.stringsearch.StringMatchBenchmark.regexMatch

# Run progress: 0.00% complete, ETA 00:20:00
# Fork: 1 of 3
# Warmup Iteration   1: 3639540.233 ops/s
# Warmup Iteration   2: 3611069.539 ops/s
# Warmup Iteration   3: 3717279.210 ops/s
# Warmup Iteration   4: 3719908.245 ops/s
# Warmup Iteration   5: 3690431.138 ops/s
Iteration   1: 3682625.808 ops/s
Iteration   2: 3678088.263 ops/s
Iteration   3: 3677580.688 ops/s
Iteration   4: 3665887.075 ops/s
Iteration   5: 3657190.195 ops/s

# Run progress: 8.33% complete, ETA 00:18:22
# Fork: 2 of 3
# Warmup Iteration   1: 3601725.096 ops/s
# Warmup Iteration   2: 3503042.571 ops/s
# Warmup Iteration   3: 3664321.426 ops/s
# Warmup Iteration   4: 3665190.496 ops/s
# Warmup Iteration   5: 3670138.481 ops/s
Iteration   1: 3678911.814 ops/s
Iteration   2: 3671981.904 ops/s
Iteration   3: 3668238.995 ops/s
Iteration   4: 3664276.987 ops/s
Iteration   5: 3666930.988 ops/s

# Run progress: 16.67% complete, ETA 00:16:42
# Fork: 3 of 3
# Warmup Iteration   1: 3604413.479 ops/s
# Warmup Iteration   2: 3610006.740 ops/s
# Warmup Iteration   3: 3687143.259 ops/s
# Warmup Iteration   4: 3679575.068 ops/s
# Warmup Iteration   5: 3681908.317 ops/s
Iteration   1: 3686207.077 ops/s
Iteration   2: 3682756.668 ops/s
Iteration   3: 3684352.296 ops/s
Iteration   4: 3683216.184 ops/s
Iteration   5: 3682487.594 ops/s


Result "com.sigpwned.benchmark.stringsearch.StringMatchBenchmark.regexMatch":
  3675382.169 ±(99.9%) 9559.442 ops/s [Average]
  (min, avg, max) = (3657190.195, 3675382.169, 3686207.077), stdev = 8941.908
  CI (99.9%): [3665822.727, 3684941.611] (assumes normal distribution)


# JMH version: 1.37
# VM version: JDK 21.0.2, OpenJDK 64-Bit Server VM, 21.0.2+13-LTS
# VM invoker: /Users/aboothe/Library/Java/JavaVirtualMachines/corretto-21.0.2/Contents/Home/bin/java
# VM options: <none>
# Blackhole mode: compiler (auto-detected, use -Djmh.blackhole.autoDetect=false to disable)
# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: com.sigpwned.benchmark.stringsearch.StringMatchBenchmark.stringMatch

# Run progress: 25.00% complete, ETA 00:15:02
# Fork: 1 of 3
# Warmup Iteration   1: 112591704.289 ops/s
# Warmup Iteration   2: 117005967.054 ops/s
# Warmup Iteration   3: 117041223.928 ops/s
# Warmup Iteration   4: 117762842.654 ops/s
# Warmup Iteration   5: 117473204.624 ops/s
Iteration   1: 118062542.529 ops/s
Iteration   2: 118084943.353 ops/s
Iteration   3: 118612700.718 ops/s
Iteration   4: 118390764.350 ops/s
Iteration   5: 118611692.520 ops/s

# Run progress: 33.33% complete, ETA 00:13:22
# Fork: 2 of 3
# Warmup Iteration   1: 114202102.060 ops/s
# Warmup Iteration   2: 118353134.274 ops/s
# Warmup Iteration   3: 93917534.714 ops/s
# Warmup Iteration   4: 94049680.831 ops/s
# Warmup Iteration   5: 93813744.331 ops/s
Iteration   1: 93948052.046 ops/s
Iteration   2: 93888680.529 ops/s
Iteration   3: 94040183.215 ops/s
Iteration   4: 94217946.282 ops/s
Iteration   5: 94238357.432 ops/s

# Run progress: 41.67% complete, ETA 00:11:41
# Fork: 3 of 3
# Warmup Iteration   1: 114484765.008 ops/s
# Warmup Iteration   2: 118252943.302 ops/s
# Warmup Iteration   3: 118817088.224 ops/s
# Warmup Iteration   4: 116824365.536 ops/s
# Warmup Iteration   5: 118298269.436 ops/s
Iteration   1: 118921138.693 ops/s
Iteration   2: 118612881.868 ops/s
Iteration   3: 118857250.872 ops/s
Iteration   4: 118152330.968 ops/s
Iteration   5: 118555385.170 ops/s


Result "com.sigpwned.benchmark.stringsearch.StringMatchBenchmark.stringMatch":
  110346323.370 ±(99.9%) 12741407.378 ops/s [Average]
  (min, avg, max) = (93888680.529, 110346323.370, 118921138.693), stdev = 11918320.367
  CI (99.9%): [97604915.992, 123087730.747] (assumes normal distribution)


# JMH version: 1.37
# VM version: JDK 21.0.2, OpenJDK 64-Bit Server VM, 21.0.2+13-LTS
# VM invoker: /Users/aboothe/Library/Java/JavaVirtualMachines/corretto-21.0.2/Contents/Home/bin/java
# VM options: <none>
# Blackhole mode: compiler (auto-detected, use -Djmh.blackhole.autoDetect=false to disable)
# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: com.sigpwned.benchmark.stringsearch.StringSearchBenchmark.regexSearch

# Run progress: 50.00% complete, ETA 00:10:01
# Fork: 1 of 3
# Warmup Iteration   1: 4519.588 ops/s
# Warmup Iteration   2: 4546.937 ops/s
# Warmup Iteration   3: 4548.390 ops/s
# Warmup Iteration   4: 4645.894 ops/s
# Warmup Iteration   5: 4638.302 ops/s
Iteration   1: 4647.060 ops/s
Iteration   2: 4640.214 ops/s
Iteration   3: 4651.978 ops/s
Iteration   4: 4639.726 ops/s
Iteration   5: 4646.027 ops/s

# Run progress: 58.33% complete, ETA 00:08:21
# Fork: 2 of 3
# Warmup Iteration   1: 4523.458 ops/s
# Warmup Iteration   2: 4536.052 ops/s
# Warmup Iteration   3: 4551.367 ops/s
# Warmup Iteration   4: 4647.017 ops/s
# Warmup Iteration   5: 4634.860 ops/s
Iteration   1: 4646.671 ops/s
Iteration   2: 4644.399 ops/s
Iteration   3: 4643.632 ops/s
Iteration   4: 4636.438 ops/s
Iteration   5: 4636.270 ops/s

# Run progress: 66.67% complete, ETA 00:06:41
# Fork: 3 of 3
# Warmup Iteration   1: 4523.084 ops/s
# Warmup Iteration   2: 4545.962 ops/s
# Warmup Iteration   3: 4555.167 ops/s
# Warmup Iteration   4: 4622.525 ops/s
# Warmup Iteration   5: 4618.916 ops/s
Iteration   1: 4633.937 ops/s
Iteration   2: 4636.697 ops/s
Iteration   3: 4647.360 ops/s
Iteration   4: 4635.951 ops/s
Iteration   5: 4646.124 ops/s


Result "com.sigpwned.benchmark.stringsearch.StringSearchBenchmark.regexSearch":
  4642.166 ±(99.9%) 5.847 ops/s [Average]
  (min, avg, max) = (4633.937, 4642.166, 4651.978), stdev = 5.469
  CI (99.9%): [4636.319, 4648.013] (assumes normal distribution)


# JMH version: 1.37
# VM version: JDK 21.0.2, OpenJDK 64-Bit Server VM, 21.0.2+13-LTS
# VM invoker: /Users/aboothe/Library/Java/JavaVirtualMachines/corretto-21.0.2/Contents/Home/bin/java
# VM options: <none>
# Blackhole mode: compiler (auto-detected, use -Djmh.blackhole.autoDetect=false to disable)
# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: com.sigpwned.benchmark.stringsearch.StringSearchBenchmark.stringSearch

# Run progress: 75.00% complete, ETA 00:05:00
# Fork: 1 of 3
# Warmup Iteration   1: 12848.974 ops/s
# Warmup Iteration   2: 12744.352 ops/s
# Warmup Iteration   3: 12714.826 ops/s
# Warmup Iteration   4: 12719.493 ops/s
# Warmup Iteration   5: 12707.244 ops/s
Iteration   1: 12682.632 ops/s
Iteration   2: 12709.105 ops/s
Iteration   3: 12713.728 ops/s
Iteration   4: 12706.919 ops/s
Iteration   5: 12703.667 ops/s

# Run progress: 83.33% complete, ETA 00:03:20
# Fork: 2 of 3
# Warmup Iteration   1: 12862.930 ops/s
# Warmup Iteration   2: 12719.882 ops/s
# Warmup Iteration   3: 12711.925 ops/s
# Warmup Iteration   4: 12724.925 ops/s
# Warmup Iteration   5: 12720.105 ops/s
Iteration   1: 12724.147 ops/s
Iteration   2: 12717.683 ops/s
Iteration   3: 12678.829 ops/s
Iteration   4: 12727.191 ops/s
Iteration   5: 12730.817 ops/s

# Run progress: 91.67% complete, ETA 00:01:40
# Fork: 3 of 3
# Warmup Iteration   1: 12719.621 ops/s
# Warmup Iteration   2: 12717.466 ops/s
# Warmup Iteration   3: 12729.967 ops/s
# Warmup Iteration   4: 12656.025 ops/s
# Warmup Iteration   5: 12643.085 ops/s
Iteration   1: 12673.651 ops/s
Iteration   2: 12717.579 ops/s
Iteration   3: 12713.286 ops/s
Iteration   4: 12708.731 ops/s
Iteration   5: 12680.313 ops/s


Result "com.sigpwned.benchmark.stringsearch.StringSearchBenchmark.stringSearch":
  12705.885 ±(99.9%) 19.777 ops/s [Average]
  (min, avg, max) = (12673.651, 12705.885, 12730.817), stdev = 18.500
  CI (99.9%): [12686.108, 12725.663] (assumes normal distribution)


# Run complete. Total time: 00:20:03

REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
experiments, perform baseline and negative tests that provide experimental control, make sure
the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
Do not assume the numbers tell you what you want them to tell.

NOTE: Current JVM experimentally supports Compiler Blackholes, and they are in use. Please exercise
extra caution when trusting the results, look into the generated code to check the benchmark still
works, and factor in a small probability of new VM bugs. Additionally, while comparisons between
different JVMs are already problematic, the performance difference caused by different Blackhole
modes can be very significant. Please make sure you use the consistent Blackhole mode for comparisons.

Benchmark                            Mode  Cnt          Score          Error  Units
StringMatchBenchmark.regexMatch     thrpt   15    3675382.169 ±     9559.442  ops/s
StringMatchBenchmark.stringMatch    thrpt   15  110346323.370 ± 12741407.378  ops/s
StringSearchBenchmark.regexSearch   thrpt   15       4642.166 ±        5.847  ops/s
StringSearchBenchmark.stringSearch  thrpt   15      12705.885 ±       19.777  ops/s
```
