package com.gobue.blink.common.utils.test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertHelper {
    public static void assertDateTime(LocalDateTime start, LocalDateTime end){
        assertThat(start).isNotNull();
        assertThat(end).isNotNull();
        assertThat(start.getNano()).isEqualTo(start.getNano());  
    }
}
