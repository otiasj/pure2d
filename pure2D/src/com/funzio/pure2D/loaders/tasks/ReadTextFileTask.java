/**
 * 
 */
package com.funzio.pure2D.loaders.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Intent;
import android.content.res.AssetManager;

/**
 * @author long
 */
public class ReadTextFileTask extends ReadFileTask {
    public static final String TAG = ReadTextFileTask.class.getSimpleName();
    public static final String CLASS_NAME = ReadTextFileTask.class.getName();
    public static final String INTENT_COMPLETE = CLASS_NAME + ".INTENT_COMPLETE";

    protected String mContent;

    public ReadTextFileTask(final String filePath) {
        super(filePath);
    }

    public ReadTextFileTask(final AssetManager assets, final String filePath) {
        super(assets, filePath);
    }

    @Override
    protected void readContent(final InputStream in) throws IOException {

        final StringBuffer sb = new StringBuffer();
        final InputStreamReader ir = new InputStreamReader(in);
        final BufferedReader br = new BufferedReader(ir);

        String line = "";
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        ir.close();

        // get the string
        mContent = sb.toString();
    }

    public String getContent() {
        return mContent;
    }

    @Override
    public Intent getCompleteIntent() {
        final Intent intent = super.getCompleteIntent();
        intent.setAction(INTENT_COMPLETE);
        return intent;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "[ReadTextFileTask]";
    }
}
