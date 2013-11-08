/*
 * Copyright 2013 Projects Exception
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.projectsexception.util;

import android.util.Log;

public class CustomLog {

    private static CustomLog mInstance;

    public static void initLog(String tag, int logLevel) {
        if (mInstance == null) {
            mInstance = new CustomLog(tag, logLevel);
        }
    }

    public static CustomLog getInstance() {
        if (mInstance == null) {
            throw new IllegalStateException("Log is not initialized, you need to call CustomLog.initLog");
        }
        return mInstance;
    }

    private final String mTag;
    private final boolean mVerbose;
    private final boolean mDebug;
    private final boolean mInfo;
    private final boolean mWarn;
    private final boolean mError;

    public CustomLog(String tag, int logLevel) {
        this.mTag = tag;
        this.mVerbose = logLevel <= Log.VERBOSE;
        this.mDebug = logLevel <= Log.DEBUG;
        this.mInfo = logLevel <= Log.INFO;
        this.mWarn = logLevel <= Log.WARN;
        this.mError = logLevel <= Log.ERROR;
    }

    public void verbose(String source, String msg) {
        verbose(source, msg, null);
    }
    
    public void verbose(String source, String msg, Throwable e) {
        log(Log.VERBOSE, source, msg, e);
    }
    
    public void verbose(String source, Throwable e) {
        log(Log.VERBOSE, source, null, e);
    }

    public void debug(String source, String msg) {
        debug(source, msg, null);     
    }

    public void debug(String source, String msg, Throwable e) {
        log(Log.DEBUG, source, msg, e); 
    }

    public void debug(String source, Throwable e) {
        log(Log.DEBUG, source, null, e); 
    }

    public void info(String source, String msg) {
        info(source, msg, null);
    }
    
    public void info(String source, String msg, Throwable e) {
        log(Log.INFO, source, msg, e);
    }
    
    public void info(String source, Throwable e) {
        log(Log.INFO, source, null, e);
    }

    public void warn(String source, String msg) {
        warn(source, msg, null);
    }
    
    public void warn(String source, String msg, Throwable e) {
        log(Log.WARN, source, msg, e);
    }
    
    public void warn(String source, Throwable e) {
        log(Log.WARN, source, null, e);
    }

    public void error(String source, String msg) {
        error(source, msg, null);
    }
    
    public void error(String source, String msg, Throwable e) {
        log(Log.ERROR, source, msg, e);
    }
    
    public void error(String source, Throwable e) {
        log(Log.ERROR, source, null, e);
    }

    public void log(int level, String source, String msg) {
        log(level, source, msg, null);
    }

    public void log(int level, String source, String msg, Throwable e) {
        if (level == Log.VERBOSE) {
            if (mVerbose) {
                if (e == null) {
                    Log.v(mTag, message(source, msg));
                } else {
                    Log.v(mTag, message(source, msg), e);
                }
            }
        } else if (level == Log.DEBUG) {
            if (mDebug) {
                if (e == null) {
                    Log.d(mTag, message(source, msg));
                } else {
                    Log.d(mTag, message(source, msg), e);
                }
            }
        } else if (level == Log.INFO) {
            if (mInfo) {
                if (e == null) {
                    Log.i(mTag, message(source, msg));
                } else {
                    Log.i(mTag, message(source, msg), e);
                }
            }
        } else if (level == Log.WARN) {
            if (mWarn) {
                if (e == null) {
                    Log.w(mTag, message(source, msg));
                } else {
                    Log.w(mTag, message(source, msg), e);
                }
            }
        } else if (level == Log.ERROR) {
            if (mError) {
                if (e == null) {
                    Log.e(mTag, message(source, msg));
                } else {
                    Log.e(mTag, message(source, msg), e);
                }
            }
        }
    }

    private static String message(String source, String msg) {
        StringBuilder sb = new StringBuilder(source);
        if (msg != null) {
            sb.append(": ");
            sb.append(msg);
            sb.append(" ");
        }
        return sb.toString();
    }
}
