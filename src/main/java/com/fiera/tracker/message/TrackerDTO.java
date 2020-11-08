package com.fiera.tracker.message;

import lombok.Data;
import lombok.ToString;

@Data @ToString
public class TrackerDTO {

private String target;
private String link;
private boolean valid;

}
