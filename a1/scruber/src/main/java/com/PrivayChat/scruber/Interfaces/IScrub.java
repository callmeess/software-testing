package com.PrivayChat.scruber.Interfaces;

import com.PrivayChat.scruber.Enums.ScrubbingModes;

public interface IScrub {
    String scrub(String prompt , ScrubbingModes scrubbingMode);
}