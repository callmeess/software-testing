package com.PrivayChat.scruber.Interfaces;

import com.PrivayChat.scruber.Enums.ScrubbingModes;

public interface IScrub {
    String scrubPrompt(String prompt , ScrubbingModes scrubbingMode);
}