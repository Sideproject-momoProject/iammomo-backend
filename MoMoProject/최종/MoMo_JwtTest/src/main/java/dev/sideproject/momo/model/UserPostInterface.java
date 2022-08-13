package dev.sideproject.momo.model;

import java.time.Instant;

public interface UserPostInterface {

    Long getId();
    String getTopic();
    String getContent();
    Instant getCreate_At();

}
