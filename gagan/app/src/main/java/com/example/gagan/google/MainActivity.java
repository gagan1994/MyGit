package com.example.gagan.google;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gagan.google.adapter.TabAdapter;
import com.example.gagan.google.fragments.BasePagerFragment;
import com.example.gagan.google.utils.Constant;
import com.example.gagan.google.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.cmu.pocketsphinx.Assets;
import edu.cmu.pocketsphinx.Hypothesis;
import edu.cmu.pocketsphinx.RecognitionListener;
import edu.cmu.pocketsphinx.SpeechRecognizer;
import edu.cmu.pocketsphinx.SpeechRecognizerSetup;

import static android.icu.text.DateTimePatternGenerator.PatternInfo.OK;
import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, android.support.v7.app.ActionBar.TabListener, RecognitionListener {
    @BindView(R.id.scrollView)
    ViewPager viewPager;
    Unbinder unbinder;
    TabAdapter adapter;
    private android.support.v7.app.ActionBar actionBar;
    private List<BasePagerFragment> fragmentList;
    private SpeechRecognizer recognizer;
    public static final String TAG = "MainActivity";
    private static final String KWS_SEARCH = "wakeup";
    private static final String FORECAST_SEARCH = "forecast";
    private static final String DIGITS_SEARCH = "digits";
    private static final String PHONE_SEARCH = "phones";
    private static final String MENU_SEARCH = "menu";
    public static final int PERMISSIONS_MULTIPLE_REQUEST = 124;

    private HashMap<String, Integer> captions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        if (!Utils.hasPermissions(this, Constant.PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, Constant.PERMISSIONS, PERMISSIONS_MULTIPLE_REQUEST);
            return;
        }
        initAll();
    }

    private void initAll() {
        initVoice();
        fragmentList = Constant.getList();
        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);
        for (BasePagerFragment item : fragmentList) {
            actionBar.addTab(actionBar.newTab().setText(item.getTitle())
                    .setTabListener(this));
        }
        adapter = new TabAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
    }

    private void initVoice() {

        captions = new HashMap<>();
        captions.put(KWS_SEARCH, R.string.kws_caption);
        captions.put(MENU_SEARCH, R.string.menu_caption);
        captions.put(DIGITS_SEARCH, R.string.digits_caption);
        captions.put(PHONE_SEARCH, R.string.phone_caption);
        captions.put(FORECAST_SEARCH, R.string.forecast_caption);
        new SetupTask(this).execute();
//        recognizer = defaultSetup()
//                .setAcousticModel(new File(assetsDir, "en-us-ptm"))
//                .setDictionary(new File(assetsDir, "cmudict-en-us.dict"))
//                .getRecognizer();
//        recognizer.addListener(new SpeechModule(getApplicationContext()));
//        recognizer.addKeyphraseSearch(KWS_SEARCH, Constant.getRecognizitionKeyword());

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_MULTIPLE_REQUEST:
                for (int result : grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                    //    return;
                    }
                }
                initAll();
                break;

            default:
                break;
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        actionBar.setSelectedNavigationItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onPartialResult(Hypothesis hypothesis) {
        Utils.Log(TAG, "onPartialResult");

        if (hypothesis == null)
            return;

        String text = hypothesis.getHypstr();
        Utils.Log(TAG, "getHypstr=" + text);

        if (text.equals(Constant.getRecognizitionKeyword(getApplicationContext())))
            switchSearch(MENU_SEARCH);
        else if (text.equals(DIGITS_SEARCH))
            switchSearch(DIGITS_SEARCH);
        else if (text.equals(PHONE_SEARCH))
            switchSearch(PHONE_SEARCH);
        else if (text.equals(FORECAST_SEARCH))
            switchSearch(FORECAST_SEARCH);
        else
            Utils.Toast(getApplicationContext(), text);
    }

    /**
     * This callback is called when we stop the recognizer.
     */
    @Override
    public void onResult(Hypothesis hypothesis) {
        if (hypothesis != null) {
            String text = hypothesis.getHypstr();
            makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            Utils.Log(TAG, text);
        }
    }


    /**
     * We stop recognizer here to get a final result
     */
    @Override
    public void onEndOfSpeech() {
        if (!recognizer.getSearchName().equals(KWS_SEARCH))
            switchSearch(KWS_SEARCH);
        Utils.Log(TAG, "onEndOfSpeech");
    }


    @Override
    public void onBeginningOfSpeech() {
        Utils.Log(TAG, "onBeginningOfSpeech");
    }


    @Override
    public void onError(Exception e) {
        Utils.Log(TAG, "onError" + e.getMessage());
        e.printStackTrace();

    }

    @Override
    public void onTimeout() {
        Utils.Log(TAG, "onTimeout");
        switchSearch(KWS_SEARCH);
    }

    private void switchSearch(String searchName) {
        recognizer.stop();

        // If we are not spotting, start listening with timeout (10000 ms or 10 seconds).
        if (searchName.equals(KWS_SEARCH))
            recognizer.startListening(searchName);
        else
            recognizer.startListening(searchName, 10000);
        String caption = getResources().getString(captions.get(searchName));
        Utils.Toast(getApplicationContext(), caption);
    }


    private static class SetupTask extends AsyncTask<Void, Void, Exception> {
        WeakReference<MainActivity> activityReference;

        SetupTask(MainActivity activity) {
            this.activityReference = new WeakReference<>(activity);
        }

        @Override
        protected Exception doInBackground(Void... params) {
            try {
                Assets assets = new Assets(activityReference.get());
                File assetDir = assets.syncAssets();
                activityReference.get().setupRecognizer(assetDir);
            } catch (IOException e) {
                return e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Exception result) {
            if (result != null) {
                Utils.Toast(activityReference.get(), "Failed to init recognizer");
           /*     ((TextView) activityReference.get().findViewById(R.id.caption_text))
                        .setText("Failed to init recognizer " + result);*/
            } else {
                activityReference.get().switchSearch(KWS_SEARCH);
            }
        }
    }

    private void setupRecognizer(File assetDir) {
        try {
            recognizer = SpeechRecognizerSetup.defaultSetup()
                    .setAcousticModel(new File(assetDir, "en-us-ptm"))
                    .setDictionary(new File(assetDir, "cmudict-en-us.dict"))
                    .setRawLogDir(assetDir)
                    .getRecognizer();
            recognizer.addListener(this);
            recognizer.addKeyphraseSearch(KWS_SEARCH, Constant.getRecognizitionKeyword(getApplicationContext()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        /* In your application you might not need to add all those searches.
          They are added here for demonstration. You can leave just one.
         */

        // Create keyword-activation search.
    }

}
