package com.techja.lesson28api.view.viewmodel;

import com.techja.lesson28api.App;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;


@RunWith(RobolectricTestRunner.class)
@Config(application = App.class,sdk=23)
public class M001LoginModelTest extends TestCase {
    private M001LoginModel mModel;
    private MockWebServer webServer;
    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
        mModel=Mockito.spy(M001LoginModel.class);
        webServer=new MockWebServer();

        M001LoginModel.BASE_URL=webServer.url("/").toString();
        System.out.println("URL: "+ M001LoginModel.BASE_URL);
    }
    @Test
    public void testLoginSuccess(){
        webServer.enqueue(new MockResponse().
                setBody("{\n" +
                        "    \"success\": true,\n" +
                        "    \"expires_at\": \"2021-04-14 04:03:10 UTC\",\n" +
                        "    \"request_token\": \"41486462fa346d0f64c06a5e054168cb24947141\"\n" +
                        "}").
                setResponseCode(200));
        mModel.login("NeosThanh","anhpham2m");
    }

    @After
    protected void tearDown(){

    }
}