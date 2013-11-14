package com.projectsexception.util;

import android.content.Context;

import com.google.analytics.tracking.android.ExceptionParser;
import com.google.analytics.tracking.android.ExceptionReporter;
import com.google.analytics.tracking.android.GAServiceManager;
import com.google.analytics.tracking.android.Tracker;

public final class AnalyticsUtil {

    private AnalyticsUtil() {

    }

    public static void checkExceptionHandler(Context context, Tracker tracker) {
        Thread.UncaughtExceptionHandler exceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        ExceptionParser exceptionParser = null;
        if (exceptionHandler instanceof ExceptionReporter) {
            exceptionParser = ((ExceptionReporter) exceptionHandler).getExceptionParser();
        }
        if (!(exceptionParser instanceof AnalyticsExceptionParser)) {
            GAServiceManager serviceManager = GAServiceManager.getInstance();
            ExceptionReporter myHandler = new ExceptionReporter(tracker, serviceManager, exceptionHandler, context);
            myHandler.setExceptionParser(new AnalyticsExceptionParser());
            Thread.setDefaultUncaughtExceptionHandler(myHandler);
        }
    }

    static class AnalyticsExceptionParser implements ExceptionParser {
        /*
         * (non-Javadoc)
         * @see com.google.analytics.tracking.android.ExceptionParser#getDescription(java.lang.String, java.lang.Throwable)
         */
        public String getDescription(String thread, Throwable throwable) {
            return getDescription(thread, throwable, 0);
        }

        public String getDescription(String thread, Throwable throwable, int recursivity) {
            StringBuilder sb = new StringBuilder();
            if (thread == null) {
                sb.append("\nCaused By:");
            } else {
                sb.append("Thread: ").append(thread);
            }
            sb.append(" - ").append(throwable.getClass().getName());
            if (throwable.getMessage() == null) {
                sb.append(": <Null message>");
            } else {
                sb.append(": ").append(throwable.getMessage());
            }
            sb.append(" ");
            StackTraceElement[] stackTrace = throwable.getStackTrace();
            if (stackTrace != null) {
                for (StackTraceElement element : stackTrace) {
                    sb.append(element.getClassName()).append(".").append(element.getMethodName());
                    sb.append("(").append(element.getFileName()).append(":").append(element.getLineNumber()).append(") ");
                }
            }
            Throwable cause = throwable.getCause();
            if (cause != null && recursivity < 10) {
                sb.append(getDescription(null, cause, recursivity + 1));
            }
            return sb.toString();
        }
    }

}
