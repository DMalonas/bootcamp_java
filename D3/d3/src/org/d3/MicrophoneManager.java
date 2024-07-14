package org.d3;

import java.util.List;

public class MicrophoneManager {
    private List<Microphone> microphoneList;

    public void addMicrophone(Microphone microphone) {
        microphoneList.add(microphone);
    }

    public Microphone getMicrophone(int index) {
        return this.microphoneList.get(index);
    }
}
