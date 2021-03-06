package com.mobvoi.ai.asr.sdk;

import com.mobvoi.ai.asr.sdk.utils.CallBackMessage;
import com.mobvoi.ai.asr.sdk.utils.ConferenceSpeechListener;
import com.mobvoi.ai.asr.sdk.utils.RandomNumberUtil;
import lombok.extern.slf4j.Slf4j;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
@Slf4j
public class TestConferenceSpeechRec {

    public void start() {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    Integer randomNumber = RandomNumberUtil.getRandomNumber();

                    try {
                        ConferenceSpeechClient client = new ConferenceSpeechClient();
                        ConferenceSpeechListener listener = new ConferenceSpeechListener("12345678" + randomNumber, "sample" + randomNumber + ".docx", 50);
                        client.batchRecognize("D://1-写给云-低质量1.amr", listener);
                        listener.getLatch().await(1, TimeUnit.HOURS);
                        log.info("=========================xxx>>>"+ listener.getCallbackMessage().getCallBackJson());
                    } catch (UnsupportedAudioFileException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }.start();
        }
    }

    public static void main(String[] args) {
        new TestConferenceSpeechRec().start();

        System.out.println("222");
    }

}
