package com.udacity.gradle.builditbigger;

import android.test.UiThreadTest;

import junit.framework.TestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by barbarossa on 05/01/16.
 */
public class JokeDownloaderTest extends TestCase implements JokeDownloader.IListener {
    private static final int TIMEOUT_SEC = 30;

    JokeDownloader jokeDownloader;
    CountDownLatch signal;
    String result;

    protected void setUp() throws Exception {
        super.setUp();

        signal = new CountDownLatch(1);
        jokeDownloader = new JokeDownloader(this);
    }

    @UiThreadTest
    public void testDownload() throws InterruptedException
    {
        jokeDownloader.download();
        signal.await(TIMEOUT_SEC, TimeUnit.SECONDS);

        assertFalse("Joke retrieved is null", result == null);
        assertFalse("Joke retrieved is empty", result.equals(""));
    }

    @Override
    public void onDownloadCompleted(String result)
    {
        this.result = result;
        signal.countDown();
    }
}
