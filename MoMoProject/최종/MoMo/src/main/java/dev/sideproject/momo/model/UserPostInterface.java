package dev.sideproject.momo.model;

import java.time.Instant;

public interface UserPostInterface {

    Long getUId();
    String getTopic();
    String getContent();
    Instant getCreate_At();

}
