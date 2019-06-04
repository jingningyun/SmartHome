package com.jk184.smarthome.app.http.interceptor;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSource;

import static okhttp3.internal.platform.Platform.INFO;

/**
 * created by siwei on 2018/6/3<br/>
 * 使用方式和okhttp3.logging.HttpLoggingInterceptor一致,只是为了兼容Logger打日志的方式
 */
public class HttpLoggerInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    public enum Level {
        /**
         * No logs.
         */
        NONE,
        /**
         * Logs request and response lines.
         * <p>
         * <p>Example:
         * <pre>{@code
         * --> POST /greeting http/1.1 (3-byte body)
         *
         * <-- 200 OK (22ms, 6-byte body)
         * }</pre>
         */
        BASIC,
        /**
         * Logs request and response lines and their respective headers.
         * <p>
         * <p>Example:
         * <pre>{@code
         * --> POST /greeting http/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         * --> END POST
         *
         * <-- 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         * <-- END HTTP
         * }</pre>
         */
        HEADERS,
        /**
         * Logs request and response lines and their respective headers and bodies (if present).
         * <p>
         * <p>Example:
         * <pre>{@code
         * --> POST /greeting http/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         *
         * Hi?
         * --> END POST
         *
         * <-- 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         *
         * Hello!
         * <-- END HTTP
         * }</pre>
         */
        BODY
    }

    public interface Logger {
        void log(String message);

        /**
         * A {@link HttpLoggerInterceptor.Logger} defaults output appropriate for the current platform.
         */
        HttpLoggerInterceptor.Logger DEFAULT = new HttpLoggerInterceptor.Logger() {
            @Override
            public void log(String message) {
                Platform.get().log(INFO, message, null);
            }
        };
    }

    public HttpLoggerInterceptor() {
        this(HttpLoggerInterceptor.Logger.DEFAULT);
    }

    public HttpLoggerInterceptor(HttpLoggerInterceptor.Logger logger) {
        this.logger = logger;
    }

    private final HttpLoggerInterceptor.Logger logger;

    private volatile HttpLoggerInterceptor.Level level = HttpLoggerInterceptor.Level.NONE;

    /**
     * Change the level at which this interceptor logs.
     */
    public HttpLoggerInterceptor setLevel(HttpLoggerInterceptor.Level level) {
        if (level == null) throw new NullPointerException("level == null. Use Level.NONE instead.");
        this.level = level;
        return this;
    }

    public HttpLoggerInterceptor.Level getLevel() {
        return level;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        HttpLoggerInterceptor.Level level = this.level;

        Request request = chain.request();
        if (level == HttpLoggerInterceptor.Level.NONE) {
            return chain.proceed(request);
        }

        boolean logBody = level == HttpLoggerInterceptor.Level.BODY;
        boolean logHeaders = logBody || level == HttpLoggerInterceptor.Level.HEADERS;

        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;

        Connection connection = chain.connection();
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        StringBuffer logBuffer = new StringBuffer();
        String requestStartMessage = "--> " + request.method() + ' ' + request.url() + ' ' + protocol;
        if (!logHeaders && hasRequestBody) {
            requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
        }
        logBuffer.append(requestStartMessage).append("\n");

        if (logHeaders) {
            if (hasRequestBody) {
                // Request body headers are only present when installed as a network interceptor. Force
                // them to be included (when available) so there values are known.
                if (requestBody.contentType() != null) {
                    logBuffer.append("Content-Type: " + requestBody.contentType()).append("\n");
                }
                if (requestBody.contentLength() != -1) {
                    logBuffer.append("Content-Length: " + requestBody.contentLength()).append("\n");
                }
            }

            Headers headers = request.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                String name = headers.name(i);
                // Skip headers from the request body as they are explicitly logged above.
                if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                    logBuffer.append(name + ": " + headers.value(i)).append("\n");
                }
            }

            if (!logBody || !hasRequestBody) {
                logBuffer.append("--> END " + request.method()).append("\n");
            } else if (bodyEncoded(request.headers())) {
                logBuffer.append("--> END " + request.method() + " (encoded body omitted)").append("\n");
            } else {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);

                Charset charset = UTF8;
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }

                logBuffer.append("").append("\n");
                if (isPlaintext(buffer)) {
                    logBuffer.append(buffer.readString(charset)).append("\n");
                    logBuffer.append("--> END " + request.method()
                            + " (" + requestBody.contentLength() + "-byte body)").append("\n");
                } else {
                    logBuffer.append("--> END " + request.method() + " (binary "
                            + requestBody.contentLength() + "-byte body omitted)").append("\n");
                }
            }
        }

        long startNs = System.nanoTime();
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            logBuffer.append("<-- HTTP FAILED: " + e).append("\n");
            throw e;
        }
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
        logBuffer.append("<-- " + response.code() + ' ' + response.message() + ' '
                + response.request().url() + " (" + tookMs + "ms" + (!logHeaders ? ", "
                + bodySize + " body" : "") + ')').append("\n");

        if (logHeaders) {
            Headers headers = response.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                logBuffer.append(headers.name(i) + ": " + headers.value(i)).append("\n");
            }

            if (!logBody || !HttpHeaders.hasBody(response)) {
                logBuffer.append("<-- END HTTP").append("\n");
            } else if (bodyEncoded(response.headers())) {
                logBuffer.append("<-- END HTTP (encoded body omitted)").append("\n");
            } else {
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                Buffer buffer = source.buffer();

                Charset charset = UTF8;
                MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }

                if (!isPlaintext(buffer)) {
                    logBuffer.append("").append("\n");
                    logBuffer.append("<-- END HTTP (binary " + buffer.size() + "-byte body omitted)").append("\n");
                    return response;
                }

                if (contentLength != 0) {
                    logBuffer.append("").append("\n");
                    logBuffer.append(buffer.clone().readString(charset)).append("\n");
                }

                logBuffer.append("<-- END HTTP (" + buffer.size() + "-byte body)").append("\n");
            }
        }
        logger.log(logBuffer.toString());
        logBuffer = null;
        return response;
    }

    /**
     * Returns true if the body in question probably contains human readable text. Uses a small sample
     * of code points to detect unicode control characters commonly used in binary file signatures.
     */
    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }
}
